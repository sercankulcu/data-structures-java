// cift yonlu bagli listenin dugumunu temsil eden sinif
class CiftYonluDugum<E> {
	E veri;               // Dugumun icerdigi veriyi temsil eden degisken.
	CiftYonluDugum<E> onceki;  // onceki dugumun referansini saklayan degisken.
	CiftYonluDugum<E> sonraki; // Sonraki dugumun referansini saklayan degisken.

	// Dugum sinifinin kurucu fonksiyonu
	CiftYonluDugum(E veri) {
		this.veri = veri;    // Dugumun veri alanina veriyi ata
		this.onceki = null;  // onceki dugum baslangicta null olarak ayarla
		this.sonraki = null; // Sonraki dugum baslangicta null olarak ayarla
	}
}

public class DequeBagliListe<E> {

	private CiftYonluDugum<E> bas;
	private CiftYonluDugum<E> son;

	//Kuyrugun basina yeni bir eleman ekleme
	public void basaEkle(E veri) {
		// Yeni bir cift yonlu dugum (node) olusturulur ve bu dugum veri ile ilklendirilir
		CiftYonluDugum<E> yeniDugum = new CiftYonluDugum<>(veri);
		// Eger kuyruk bossa, yeni dugum kuyrugun basi ve sonu olur.
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Kuyruk bos degilse, yeni dugumun sonraki isaretcisi eski basi gosterir 
			// eski basin onceki isaretcisi yeni dugumu gosterir. 
			// Ardindan kuyrugun basi yeni dugum olur.
			yeniDugum.sonraki = bas;
			bas.onceki = yeniDugum;
			bas = yeniDugum;
		}
	}

	//Kuyrugun sonuna yeni bir eleman ekleme
	public void sonaEkle(E veri) {
		// Yeni bir cift yonlu dugum (node) olustur ve bu dugumu veri ile ilklendir
		CiftYonluDugum<E> yeniDugum = new CiftYonluDugum<>(veri);
		// Eger kuyruk bossa, yeni dugum kuyrugun basi ve sonu olur.
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Kuyruk bos degilse, yeni dugumun onceki isaretcisi eski sonu gosterir 
			// eski sonun sonraki isaretcisi yeni dugumu gosterir. 
			// Ardindan kuyrugun sonu yeni dugum olur.
			yeniDugum.onceki = son;
			son.sonraki = yeniDugum;
			son = yeniDugum;
		}
	}

	//Kuyrugun basindan bir eleman cikarma
	public E bastanCikar() {
		// Eger kuyruk bossa, bir istisna (exception) firlatilir
		if (bosMu()) {
			throw new IllegalStateException("Dequeue bos");
		}
		// Bas dugumunden (node) cikarilacak veriyi al
		E veri = bas.veri;
		// Bas dugumu bir sonraki dugumu gosterecek sekilde guncelle
		bas = bas.sonraki;
		// Eger bas hala bir dugumu gosteriyorsa, eski basin onceki isaretcisini null olarak ata
		if (bas != null) {
			bas.onceki = null;
		}
		// cikarilan veriyi dondur
		return veri;
	}

	//Kuyrugun sonundan bir eleman cikarma
	public E sondanCikar() {
		// Eger kuyruk bossa, bir istisna (exception) firlat
		if (bosMu()) {
			throw new IllegalStateException("Dequeue bos");
		}
		// Son dugumden (node) cikarilacak veriyi al
		E veri = son.veri;
		// Son dugumu bir onceki dugumu gosterecek sekilde guncelle
		son = son.onceki;
		// Eger son hala bir dugumu gosteriyorsa, eski sonun sonraki isaretcisini null olarak at
		if (son != null) {
			son.sonraki = null;
		}
		// cikarilan veriyi dondur
		return veri;
	}

	//Kuyrugun basindaki elemana bakma
	public E basaBak() {
		// Eger kuyruk bossa, bir istisna (exception) firlat
		if (bosMu()) {
			throw new IllegalStateException("Dequeue bos");
		}
		// Bas dugumundeki (node) veriyi dondur
		return bas.veri;
	}

	//Kuyrugun sonundaki elemana bakma
	public E sonaBak() {
		// Eger kuyruk bossa, bir istisna (exception) firlat
		if (bosMu()) {
			throw new IllegalStateException("Dequeue bos");
		}
		// Son dugumundeki (node) veriyi dondur
		return son.veri;
	}

	//Kuyrugun bos olup olmadigini kontrol etme
	public boolean bosMu() {
		// Bas isaretcisi null ise, kuyruk bostur ve true degeri dondurulur.
		return bas == null;
	}

	//Kuyrugun icerigini gosterme
	public void dequeueGoster() {
		// Eger kuyruk bossa, "Dequeue bos." mesaji ver
		if (bosMu()) {
			System.out.println("Dequeue bos.");
			return;
		}

		// Kuyrugu dolasmak icin kullanilacak olan gecerli dugumu (node) bastan baslat
		CiftYonluDugum<E> simdiki = bas;

		System.out.print("Dequeue ogeleri: ");

		// Kuyruktaki her dugumu dolasarak verileri ekrana yazdir
		while (simdiki != null) {
			System.out.print(simdiki.veri + " ");
			simdiki = simdiki.sonraki;
		}

		// Kuyrugun tum elemanlari yazildiktan sonra bir satir atla
		System.out.println();
	}

	public static void main(String[] args) {

		DequeBagliListe<String> dequeue = new DequeBagliListe<>();

		// Dequeue'in basina ve sonuna ogeleri ekleyelim
		dequeue.basaEkle("oge 1");
		dequeue.sonaEkle("oge 2");
		dequeue.sonaEkle("oge 3");
		dequeue.basaEkle("oge 4");

		// Dequeue'in icerigini gosterelim
		dequeue.dequeueGoster();

		// Dequeue'in basindan ve sonundan ogeleri cikaralim
		String bastanCikarilan = dequeue.bastanCikar();
		String sondanCikarilan = dequeue.sondanCikar();

		System.out.println("Bastan cikarilan oge: " + bastanCikarilan);
		System.out.println("Sondan cikarilan oge: " + sondanCikarilan);

		// Guncel icerigi gosterelim
		dequeue.dequeueGoster();

		// Dequeue'in basindaki ve sonundaki ogelere bakalim
		String basaBak = dequeue.basaBak();
		String sonaBak = dequeue.sonaBak();

		System.out.println("Bastaki oge: " + basaBak);
		System.out.println("Sondaki oge: " + sonaBak);

		// Dequeue'in bos olup olmadigini kontrol edelim
		boolean bosMu = dequeue.bosMu();
		System.out.println("Dequeue Bos mu? " + bosMu);
	}
}
