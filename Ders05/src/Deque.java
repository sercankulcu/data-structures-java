
public class Deque<E> {

	private CiftYonluDugum<E> bas;
	private CiftYonluDugum<E> son;

	//Kuyruğun başına yeni bir eleman ekleme
	public void basaEkle(E veri) {
		// Yeni bir çift yönlü düğüm (node) oluşturulur ve bu düğüm veri ile ilklendirilir
		CiftYonluDugum<E> yeniDugum = new CiftYonluDugum<E>(veri);

		// Eğer kuyruk boşsa, yeni düğüm kuyruğun başı ve sonu olur.
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Kuyruk boş değilse, yeni düğümün sonraki işaretçisi eski başı gösterir 
			// eski başın önceki işaretçisi yeni düğümü gösterir. 
			// Ardından kuyruğun başı yeni düğüm olur.
			yeniDugum.sonraki = bas;
			bas.onceki = yeniDugum;
			bas = yeniDugum;
		}
	}

	//Kuyruğun sonuna yeni bir eleman ekleme
	public void sonaEkle(E veri) {
		// Yeni bir çift yönlü düğüm (node) oluştur ve bu düğümü veri ile ilklendir
		CiftYonluDugum<E> yeniDugum = new CiftYonluDugum<E>(veri);

		// Eğer kuyruk boşsa, yeni düğüm kuyruğun başı ve sonu olur.
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Kuyruk boş değilse, yeni düğümün önceki işaretçisi eski sonu gösterir 
			// eski sonun sonraki işaretçisi yeni düğümü gösterir. 
			// Ardından kuyruğun sonu yeni düğüm olur.
			yeniDugum.onceki = son;
			son.sonraki = yeniDugum;
			son = yeniDugum;
		}
	}

	//Kuyruğun başından bir eleman çıkarma
	public E bastanCikar() {
		// Eğer kuyruk boşsa, bir istisna (exception) fırlatılır
		if (bosMu()) {
			throw new IllegalStateException("Dequeue boş");
		}

		// Baş düğümünden (node) çıkarılacak veriyi al
		E veri = bas.veri;

		// Baş düğümü bir sonraki düğümü gösterecek şekilde güncelle
		bas = bas.sonraki;

		// Eğer baş hala bir düğümü gösteriyorsa, eski başın önceki işaretçisini null olarak ata
		if (bas != null) {
			bas.onceki = null;
		}

		// Çıkarılan veriyi döndür
		return veri;
	}

	//Kuyruğun sonundan bir eleman çıkarma
	public E sondanCikar() {
		// Eğer kuyruk boşsa, bir istisna (exception) fırlat
		if (bosMu()) {
			throw new IllegalStateException("Dequeue boş");
		}

		// Son düğümden (node) çıkarılacak veriyi al
		E veri = son.veri;

		// Son düğümü bir önceki düğümü gösterecek şekilde güncelle
		son = son.onceki;

		// Eğer son hala bir düğümü gösteriyorsa, eski sonun sonraki işaretçisini null olarak at
		if (son != null) {
			son.sonraki = null;
		}

		// Çıkarılan veriyi döndür
		return veri;
	}

	//Kuyruğun başındaki elemana bakma
	public E basaBak() {
		// Eğer kuyruk boşsa, bir istisna (exception) fırlat
		if (bosMu()) {
			throw new IllegalStateException("Dequeue boş");
		}
		// Baş düğümündeki (node) veriyi döndür
		return bas.veri;
	}

	//Kuyruğun sonundaki elemana bakma
	public E sonaBak() {
		// Eğer kuyruk boşsa, bir istisna (exception) fırlat
		if (bosMu()) {
			throw new IllegalStateException("Dequeue boş");
		}
		// Son düğümündeki (node) veriyi döndür
		return son.veri;
	}

	//Kuyruğun boş olup olmadığını kontrol etme
	public boolean bosMu() {
		// Baş işaretçisi null ise, kuyruk boştur ve true değeri döndürülür.
		return bas == null;
	}

	//Kuyruğun içeriğini gösterme
	public void dequeueGoster() {
		// Eğer kuyruk boşsa, "Dequeue boş." mesajı ver
		if (bosMu()) {
			System.out.println("Dequeue boş.");
			return;
		}

		// Kuyruğu dolaşmak için kullanılacak olan geçerli düğümü (node) baştan başlat
		CiftYonluDugum<E> simdiki = bas;

		System.out.print("Dequeue Öğeleri: ");

		// Kuyruktaki her düğümü dolaşarak verileri ekrana yazdır
		while (simdiki != null) {
			System.out.print(simdiki.veri + " ");
			simdiki = simdiki.sonraki;
		}

		// Kuyruğun tüm elemanları yazıldıktan sonra bir satır atla
		System.out.println();
	}

	public static void main(String[] args) {

		Deque<String> dequeue = new Deque<>();

		// Dequeue'in başına ve sonuna öğeleri ekleyelim
		dequeue.basaEkle("Öğe 1");
		dequeue.sonaEkle("Öğe 2");
		dequeue.sonaEkle("Öğe 3");
		dequeue.basaEkle("Öğe 4");

		// Dequeue'in içeriğini gösterelim
		dequeue.dequeueGoster();

		// Dequeue'in başından ve sonundan öğeleri çıkaralım
		String bastanCikarilan = dequeue.bastanCikar();
		String sondanCikarilan = dequeue.sondanCikar();

		System.out.println("Baştan Çıkarılan Öğe: " + bastanCikarilan);
		System.out.println("Sondan Çıkarılan Öğe: " + sondanCikarilan);

		// Güncel içeriği gösterelim
		dequeue.dequeueGoster();

		// Dequeue'in başındaki ve sonundaki öğelere bakalım
		String basaBak = dequeue.basaBak();
		String sonaBak = dequeue.sonaBak();

		System.out.println("Baştaki Öğe: " + basaBak);
		System.out.println("Sondaki Öğe: " + sonaBak);

		// Dequeue'in boş olup olmadığını kontrol edelim
		boolean bosMu = dequeue.bosMu();
		System.out.println("Dequeue Boş mu? " + bosMu);
	}
}
