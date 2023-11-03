
public class OncelikliKuyruk {

	private OncelikliKuyrukEleman[] dizi;
	private int boyut;
	final int BAŞLANGIÇ_KAPASİTESİ = 10;

	public OncelikliKuyruk() {
		dizi = new OncelikliKuyrukEleman[BAŞLANGIÇ_KAPASİTESİ];
		boyut = 0;
	}

	public void ekle(String veri, int öncelik) {
		if (boyut == dizi.length) {
			diziGenislet();
		}

		OncelikliKuyrukEleman yeniEleman = new OncelikliKuyrukEleman(veri, öncelik);
		int indeks = boyut;

		while (indeks > 0 && öncelik < dizi[indeks - 1].öncelik) {
			dizi[indeks] = dizi[indeks - 1];
			indeks--;
		}

		dizi[indeks] = yeniEleman;
		boyut++;
	}

	public String cikar() {
		if (bosMu()) {
			throw new IllegalStateException("Öncelikli kuyruk boş");
		}

		String cikarilan = dizi[0].veri;
		for (int i = 0; i < boyut - 1; i++) {
			dizi[i] = dizi[i + 1];
		}
		dizi[boyut - 1] = null;
		boyut--;
		return cikarilan;
	}

	public boolean bosMu() {
		return boyut == 0;
	}

	private void diziGenislet() {
		int yeniBoyut = dizi.length * 2;
		OncelikliKuyrukEleman[] yeniDizi = new OncelikliKuyrukEleman[yeniBoyut];
		for (int i = 0; i < boyut; i++) {
			yeniDizi[i] = dizi[i];
		}
		dizi = yeniDizi;
	}

	public void kuyruguGoster() {
		if (bosMu()) {
			System.out.println("Öncelikli Kuyruk boş.");
			return;
		}

		System.out.print("Öncelikli Kuyruk Öğeleri: ");
		for (int i = 0; i < boyut; i++) {
			System.out.print("(" + dizi[i].veri + ", Öncelik: " + dizi[i].öncelik + ") ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

		OncelikliKuyruk kuyruk = new OncelikliKuyruk();

		// Öncelikli kuyruğa (priority queue) öğeleri ekleyelim (enqueue)
		kuyruk.ekle("Öğe 1", 3); // Öncelik: 3
		kuyruk.ekle("Öğe 2", 1); // Öncelik: 1
		kuyruk.ekle("Öğe 3", 2); // Öncelik: 2
		kuyruk.ekle("Öğe 4", 1); // Öncelik: 1

		// Öncelikli kuyruktaki öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkaralım (dequeue)
		String cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);

		// Öncelikli kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkaralım (dequeue)
		cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);

		// Öncelikli kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkaralım (dequeue)
		cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);

		// Öncelikli kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruğun boş olup olmadığını kontrol edelim
		boolean bosMu = kuyruk.bosMu();
		System.out.println("Öncelikli Kuyruk Boş mu? " + bosMu);
	}
}
