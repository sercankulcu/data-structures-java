
public class ÖncelikliKuyrukOrnegi {

	public static void main(String[] args) {

		ÖncelikliKuyruk<String> kuyruk = new ÖncelikliKuyruk<>();

		// Öncelikli kuyruğa (priority queue) öğeleri ekleyelim (enqueue)
		kuyruk.ekle("Öğe 1", 3); // Öncelik: 3
		kuyruk.ekle("Öğe 2", 1); // Öncelik: 1
		kuyruk.ekle("Öğe 3", 2); // Öncelik: 2
		kuyruk.ekle("Öğe 4", 1); // Öncelik: 1

		// Öncelikli kuyruktaki öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkaralım (dequeue)
		String çıkarılanOge = kuyruk.çıkar();
		System.out.println("Çıkarılan Öğe: " + çıkarılanOge);

		// Öncelikli kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkaralım (dequeue)
		çıkarılanOge = kuyruk.çıkar();
		System.out.println("Çıkarılan Öğe: " + çıkarılanOge);

		// Öncelikli kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkaralım (dequeue)
		çıkarılanOge = kuyruk.çıkar();
		System.out.println("Çıkarılan Öğe: " + çıkarılanOge);

		// Öncelikli kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruğun boş olup olmadığını kontrol edelim
		boolean bosMu = kuyruk.bosMu();
		System.out.println("Öncelikli Kuyruk Boş mu? " + bosMu);
	}
}

class ÖncelikliKuyruk<E> {

	private class Oge<E> {
		E veri;
		int öncelik;

		Oge(E veri, int öncelik) {
			this.veri = veri;
			this.öncelik = öncelik;
		}
	}

	private Oge[] dizi;
	private int boyut;
	private static final int BAŞLANGIÇ_KAPASİTESİ = 10;

	public ÖncelikliKuyruk() {
		dizi = new Oge[BAŞLANGIÇ_KAPASİTESİ];
		boyut = 0;
	}

	public void ekle(E veri, int öncelik) {
		if (boyut == dizi.length) {
			diziGenişlet();
		}

		Oge yeniOge = new Oge(veri, öncelik);
		int indeks = boyut;

		while (indeks > 0 && öncelik < dizi[indeks - 1].öncelik) {
			dizi[indeks] = dizi[indeks - 1];
			indeks--;
		}

		dizi[indeks] = yeniOge;
		boyut++;
	}

	public E çıkar() {
		if (bosMu()) {
			throw new IllegalStateException("Öncelikli kuyruk boş");
		}

		E çıkarılan = (E) dizi[0].veri;
		for (int i = 0; i < boyut - 1; i++) {
			dizi[i] = dizi[i + 1];
		}
		dizi[boyut - 1] = null;
		boyut--;
		return çıkarılan;
	}

	public boolean bosMu() {
		return boyut == 0;
	}

	private void diziGenişlet() {
		int yeniBoyut = dizi.length * 2;
		Oge[] yeniDizi = new Oge[yeniBoyut];
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
}
