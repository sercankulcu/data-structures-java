
public class DaireselKuyrukDizi<E> {
	private E[] dizi; // Dairesel kuyrugun ogelerini saklayan dizi
	private int bas; // Kuyrugun baslangic (front) isaretcisi
	private int son; // Kuyrugun son (rear) isaretcisi
	private int boyut; // Kuyruktaki oge sayisi
	private int kapasite; // Kuyrugun maksimum kapasitesi
	@SuppressWarnings("unchecked")
	public DaireselKuyrukDizi(int kapasite) {
		this.kapasite = kapasite; // Kuyrugun maksimum kapasitesini ayarla
		dizi = (E[]) new Object[kapasite]; // Genel dizi olusturma (Tur guvenligi uyarisi)
		bas = son = boyut = 0; // Kuyruktaki oge sayisini sifira ayarla
	}

	public void ekle(E oge) {
		if (boyut == kapasite) {
			throw new IllegalStateException("Kuyruk dolu.");
		}
		dizi[son] = oge; // ogeyi kuyruga ekle
		boyut++; // Kuyruktaki oge sayisini artir
		son = (son + 1) % kapasite; // Dairesel kuyrukta son isaretcisini guncelle
	}

	public E cikar() {
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk bos.");
		}
		E oge = dizi[bas]; // Baslangictaki ogeyi al
		bas = (bas + 1) % kapasite; // Bas isaretcisini guncelle (dairesel kuyruk)
		boyut--; // Kuyruktaki oge sayisini azalt
		return oge; // cikarilan ogeyi dondur
	}

	public E oneBak() {
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk bos.");
		}
		return dizi[bas]; // Kuyrugun basindaki ogeyi dondur
	}

	public boolean bosMu() {
		return boyut == 0;
	}

	public int boyut() {
		return boyut;
	}

	public void kuyruguGoster() {
		if (bosMu()) {
			System.out.println("Kuyruk bos."); // Kuyruk bossa bilgilendirme mesaji
			return;
		}
		int i = bas; // Baslangic indeksi
		int sayac = 0; // ogeleri saymak icin kullanilan sayac
		System.out.print("Kuyruk ogeleri: ");
		while (sayac < boyut) {
			System.out.print(dizi[i] + " "); // Kuyruktaki ogeleri ekrana yazdir
			i = (i + 1) % kapasite; // Dairesel kuyrukta sonraki indeksi al
			sayac++;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		DaireselKuyrukDizi<String> kuyruk = new DaireselKuyrukDizi<>(5);

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

		// Kuyrugun onundeki ogeye bak (peek)
		String onOge = kuyruk.oneBak();
		System.out.println("on oge: " + onOge);

		// Kuyrugun bos olup olmadigini kontrol et
		boolean bosMu = kuyruk.bosMu();
		System.out.println("Kuyruk Bos mu? " + bosMu);

		// Kuyrugun boyutunu al
		int boyut = kuyruk.boyut();
		System.out.println("Kuyruk Boyutu: " + boyut);
		
		cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);
		
		cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);
		
		cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);
		
		cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);
	}
}
