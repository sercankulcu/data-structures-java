
public class BagliListeKuyruk<E> {
	
	private TekYonluDugum<E> bas;
	private TekYonluDugum<E> son;
	private int boyut;

	public void ekle(E oge) {
		TekYonluDugum<E> yeniDugum = new TekYonluDugum<>(oge);
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			son.sonraki = yeniDugum;
			son = yeniDugum;
		}
		boyut++;
	}

	public E cikar() {
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk boş");
		}
		E veri = bas.veri;
		bas = bas.sonraki;
		boyut--;
		return veri;
	}

	public E oneBak() {
		if (bosMu()) {
			throw new IllegalStateException("Kuyruk boş");
		}
		return bas.veri;
	}

	public boolean bosMu() {
		return boyut == 0;
	}

	public int boyut() {
		return boyut;
	}

	public void kuyruguGoster() {
		TekYonluDugum<E> simdiki = bas;
		System.out.print("Kuyruk Öğeleri: ");
		while (simdiki != null) {
			System.out.print(simdiki.veri + " ");
			simdiki = simdiki.sonraki;
		}
		System.out.println();
	}
	
public static void main(String[] args) {
		
		BagliListeKuyruk<String> kuyruk = new BagliListeKuyruk<>();

		// Kuyruğa (queue) öğeleri ekleyelim (enqueue)
		kuyruk.ekle("Öğe 1");
		kuyruk.ekle("Öğe 2");
		kuyruk.ekle("Öğe 3");
		kuyruk.ekle("Öğe 4");

		// Kuyruktaki öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Kuyruktan (queue) öğeleri çıkaralım (dequeue)
		String cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);

		// Kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruguGoster();

		// Kuyruğun önündeki öğeye bakalım (peek) ve çıkartmadan
		String onOge = kuyruk.oneBak();
		System.out.println("Ön Öğe: " + onOge);

		// Kuyruğun boş olup olmadığını kontrol edelim
		boolean bosMu = kuyruk.bosMu();
		System.out.println("Kuyruk Boş mu? " + bosMu);

		// Kuyruğun boyutunu alalım
		int boyut = kuyruk.boyut();
		System.out.println("Kuyruk Boyutu: " + boyut);
	}
}
