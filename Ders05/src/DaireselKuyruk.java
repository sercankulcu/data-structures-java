
public class DaireselKuyruk<E> {
	private E[] dizi; // Dairesel kuyruğun öğelerini saklayan dizi
	private int bas; // Kuyruğun başlangıç (front) işaretçisi
	private int son; // Kuyruğun son (rear) işaretçisi
	private int boyut; // Kuyruktaki öğe sayısı
	private int kapasite; // Kuyruğun maksimum kapasitesi
	@SuppressWarnings("unchecked")
	public DaireselKuyruk(int kapasite) {
		this.kapasite = kapasite; // Kuyruğun maksimum kapasitesini ayarla
		dizi = (E[]) new Object[kapasite]; // Genel dizi oluşturma (Tür güvenliği uyarısı)
		bas = son = boyut = 0; // Kuyruktaki öğe sayısını sıfıra ayarla
	}

	public void ekle(E oge) {
		if (boyut == kapasite) {
			throw new IllegalStateException("Kuyruk dolu.");
		}
		dizi[son] = oge; // Öğeyi kuyruğa ekle
		boyut++; // Kuyruktaki öğe sayısını artır
		son = (son + 1) % kapasite; // Dairesel kuyrukta son işaretçisini güncelle
	}

	public E cikar() {
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk boş.");
		}
		E oge = (E) dizi[bas]; // Başlangıçtaki öğeyi al
		bas = (bas + 1) % kapasite; // Baş işaretçisini güncelle (dairesel kuyruk)
		boyut--; // Kuyruktaki öğe sayısını azalt
		return oge; // Çıkarılan öğeyi döndür
	}

	public E oneBak() {
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk boş.");
		}
		return (E) dizi[bas]; // Kuyruğun başındaki öğeyi döndür
	}

	public boolean bosMu() {
		return boyut == 0;
	}

	public int boyut() {
		return boyut;
	}

	public void kuyruguGoster() {
		if (bosMu()) {
			System.out.println("Kuyruk boş."); // Kuyruk boşsa bilgilendirme mesajı
			return;
		}
		int i = bas; // Başlangıç indeksi
		int sayac = 0; // Öğeleri saymak için kullanılan sayaç
		System.out.print("Kuyruk Öğeleri: ");
		while (sayac < boyut) {
			System.out.print(dizi[i] + " "); // Kuyruktaki öğeleri ekrana yazdır
			i = (i + 1) % kapasite; // Dairesel kuyrukta sonraki indeksi al
			sayac++;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		DaireselKuyruk<String> kuyruk = new DaireselKuyruk<>(5);

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

		// Kuyruğun önündeki öğeye bak (peek)
		String onOge = kuyruk.oneBak();
		System.out.println("Ön Öğe: " + onOge);

		// Kuyruğun boş olup olmadığını kontrol et
		boolean bosMu = kuyruk.bosMu();
		System.out.println("Kuyruk Boş mu? " + bosMu);

		// Kuyruğun boyutunu al
		int boyut = kuyruk.boyut();
		System.out.println("Kuyruk Boyutu: " + boyut);
		
		cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);
		
		cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);
		
		cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);
		
		cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);
	}
}
