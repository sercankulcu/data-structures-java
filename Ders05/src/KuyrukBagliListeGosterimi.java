// Dugum sinifi - Tek yonlu bagli liste dugumlerini temsil eden ic sinif.
class TekYonluDugum<E> {
	
	E veri;      // Dugumun icinde saklanan veri.
	TekYonluDugum<E> sonraki;  // Dugumun bir sonraki dugumune isaret eden referans.

	TekYonluDugum(E veri) {
		this.veri = veri;
		this.sonraki = null;
	}
}

public class KuyrukBagliListeGosterimi<E> {

	private TekYonluDugum<E> bas;
	private TekYonluDugum<E> son;
	private int boyut;

	public void ekle(E oge) {
		// Yeni bir TekYonluDugum ogesi olustur
		TekYonluDugum<E> yeniDugum = new TekYonluDugum<>(oge);

		// Eger liste bossa (bas degiskeni null ise), yeni dugumu basa ekle
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Eger liste bos degilse, son dugumun sonraki referansini yeni dugume bagla ve son dugumu guncelle
			son.sonraki = yeniDugum;
			son = yeniDugum;
		}
		// Liste boyutunu bir artir
		boyut++;
	}

	public E cikar() {
		// Eger kuyruk bossa, bir beklenmeyen hata firlat
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk bos");
		}

		// oge cikarma islemi: Bas dugumdeki veriyi al
		E veri = bas.veri;

		// Bas dugumunu bir sonraki dugume tasiyarak ogeyi kuyruktan cikar
		bas = bas.sonraki;

		// Kuyruktaki oge sayisini azalt
		boyut--;

		// cikarilan ogeyi dondur
		return veri;
	}

	public E oneBak() {
		// Eger kuyruk bossa, bir beklenmeyen hata firlat
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk bos");
		}

		// Kuyrugun basindaki ogeyi dondur
		return bas.veri;
	}

	public boolean bosMu() {
		// Kuyrugun bos olup olmadigini kontrol et
		return boyut == 0;
	}

	public int boyut() {
		// Kuyruktaki oge sayisini dondur
		return boyut;
	}

	public void kuyruguGoster() {
		// Baslangic dugumunu su anki dugum olarak ayarla
		TekYonluDugum<E> simdiki = bas;

		// Kuyrugun ogelerini ekrana yazdir
		System.out.print("Kuyruk ogeleri: ");
		while (simdiki != null) {
			System.out.print(simdiki.veri + " "); // su anki dugumun verisini yazdir
			simdiki = simdiki.sonraki; // Sonraki dugume gec
		}

		// Kuyrugun tum ogeleri yazdirildiginda, bir satir sonu yap
		System.out.println();
	}

	public static void main(String[] args) {

		KuyrukBagliListeGosterimi<String> kuyruk = new KuyrukBagliListeGosterimi<>();

		// Kuyruga (queue) ogeleri ekle (enqueue)
		kuyruk.ekle("oge 1");
		kuyruk.ekle("oge 2");
		kuyruk.ekle("oge 3");
		kuyruk.ekle("oge 4");

		// Kuyruktaki ogeleri goster
		kuyruk.kuyruguGoster();

		// Kuyruktan (queue) ogeleri cikar (dequeue)
		String cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);

		// Kuyruktaki guncel ogeleri goster
		kuyruk.kuyruguGoster();

		// Kuyrugun onundeki ogeyi cikartmadan bak (peek) 
		String onOge = kuyruk.oneBak();
		System.out.println("on oge: " + onOge);

		// Kuyrugun bos olup olmadigini kontrol et
		boolean bosMu = kuyruk.bosMu();
		System.out.println("Kuyruk Bos mu? " + bosMu);

		// Kuyrugun boyutunu al
		int boyut = kuyruk.boyut();
		System.out.println("Kuyruk Boyutu: " + boyut);
		
		System.out.println("Kuyruktan cikan Eleman: " + kuyruk.cikar());
		System.out.println("Kuyruktan cikan Eleman: " + kuyruk.cikar());
		
		System.out.println("Kuyruk Bos mu? " + kuyruk.bosMu());
		
		System.out.println("Kuyruktan cikan Eleman: " + kuyruk.cikar());
		
		System.out.println("Kuyruk Bos mu? " + kuyruk.bosMu());
	}
}
