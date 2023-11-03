
public class KuyrukBagliListeGosterimi<E> {

	private TekYonluDugum<E> bas;
	private TekYonluDugum<E> son;
	private int boyut;

	public void ekle(E oge) {
		// Yeni bir TekYonluDugum öğesi oluştur
		TekYonluDugum<E> yeniDugum = new TekYonluDugum<>(oge);

		// Eğer liste boşsa (baş değişkeni null ise), yeni düğümü başa ekle
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Eğer liste boş değilse, son düğümün sonraki referansını yeni düğüme bağla ve son düğümü güncelle
			son.sonraki = yeniDugum;
			son = yeniDugum;
		}
		// Liste boyutunu bir artır
		boyut++;
	}

	public E cikar() {
		// Eğer kuyruk boşsa, bir beklenmeyen hata fırlat
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk boş");
		}

		// Öğe çıkarma işlemi: Baş düğümdeki veriyi al
		E veri = bas.veri;

		// Baş düğümünü bir sonraki düğüme taşıyarak öğeyi kuyruktan çıkar
		bas = bas.sonraki;

		// Kuyruktaki öğe sayısını azalt
		boyut--;

		// Çıkarılan öğeyi döndür
		return veri;
	}

	public E oneBak() {
		// Eğer kuyruk boşsa, bir beklenmeyen hata fırlat
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk boş");
		}

		// Kuyruğun başındaki öğeyi döndür
		return bas.veri;
	}

	public boolean bosMu() {
		// Kuyruğun boş olup olmadığını kontrol et
		return boyut == 0;
	}

	public int boyut() {
		// Kuyruktaki öğe sayısını döndür
		return boyut;
	}

	public void kuyruguGoster() {
		// Başlangıç düğümünü şu anki düğüm olarak ayarla
		TekYonluDugum<E> simdiki = bas;

		// Kuyruğun öğelerini ekrana yazdır
		System.out.print("Kuyruk Öğeleri: ");
		while (simdiki != null) {
			System.out.print(simdiki.veri + " "); // Şu anki düğümün verisini yazdır
			simdiki = simdiki.sonraki; // Sonraki düğüme geç
		}

		// Kuyruğun tüm öğeleri yazdırıldığında, bir satır sonu yap
		System.out.println();
	}

	public static void main(String[] args) {

		KuyrukBagliListeGosterimi<String> kuyruk = new KuyrukBagliListeGosterimi<>();

		// Kuyruğa (queue) öğeleri ekle (enqueue)
		kuyruk.ekle("Öğe 1");
		kuyruk.ekle("Öğe 2");
		kuyruk.ekle("Öğe 3");
		kuyruk.ekle("Öğe 4");

		// Kuyruktaki öğeleri göster
		kuyruk.kuyruguGoster();

		// Kuyruktan (queue) öğeleri çıkar (dequeue)
		String cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);

		// Kuyruktaki güncel öğeleri göster
		kuyruk.kuyruguGoster();

		// Kuyruğun önündeki öğeyi çıkartmadan bak (peek) 
		String onOge = kuyruk.oneBak();
		System.out.println("Ön Öğe: " + onOge);

		// Kuyruğun boş olup olmadığını kontrol et
		boolean bosMu = kuyruk.bosMu();
		System.out.println("Kuyruk Boş mu? " + bosMu);

		// Kuyruğun boyutunu al
		int boyut = kuyruk.boyut();
		System.out.println("Kuyruk Boyutu: " + boyut);
	}
}
