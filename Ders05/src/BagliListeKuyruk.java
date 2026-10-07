import java.util.NoSuchElementException;

// Bagli liste ile kuyruk: ekleme sona (arka), cikarma bastan (on)
// Tek yonlu listede bastan silmek O(1); sondan silmek O(n) olurdu.
public class BagliListeKuyruk {

	// Tek yonlu bagli liste dugumu
	static class Dugum {
		int veri;
		Dugum sonraki;
		Dugum(int veri) {
			this.veri = veri;
		}
	}

	private Dugum on, arka;      // on ve arka dugumler
	private int uzunluk;         // oge sayisi

	public boolean bosMu() {
		return uzunluk == 0;
	}

	public int uzunluk() {
		return uzunluk;
	}

	// Ondeki ogeyi dondurur, cikarmaz
	public int ilk() {
		if (bosMu())
			throw new NoSuchElementException();
		return on.veri;
	}

	// Arkaya ekleme (enqueue), O(1)
	public void enqueue(int veri) {
		Dugum gecici = new Dugum(veri);
		if (bosMu()) {
			on = gecici;             // ilk dugum hem on hem arka
		} else {
			arka.sonraki = gecici;   // eski arka yeni dugume baglanir
		}
		arka = gecici;
		uzunluk++;
	}

	// Ondeki ogeyi cikarma (dequeue), O(1)
	public int dequeue() {
		if (bosMu())
			throw new NoSuchElementException();
		int sonuc = on.veri;
		on = on.sonraki;
		if (on == null)
			arka = null;             // kuyruk bosaldi: arka silinen dugumu gostermemeli
		uzunluk--;
		return sonuc;
	}

	// Ogeleri onden arkaya yazdirir
	public void kuyruguGoster() {
		System.out.print("Kuyruk (onden arkaya):");
		for (Dugum d = on; d != null; d = d.sonraki)
			System.out.print(" " + d.veri);
		System.out.println("  uzunluk = " + uzunluk);
	}

	public static void main(String[] args) {
		BagliListeKuyruk kuyruk = new BagliListeKuyruk();

		// Slayt 22
		kuyruk.enqueue(10);
		kuyruk.enqueue(15);
		kuyruk.enqueue(20);
		kuyruk.kuyruguGoster();
		System.out.println("ilk() = " + kuyruk.ilk());

		// Slayt 23: cikis sirasi 10, 15, 20 (FIFO)
		while (!kuyruk.bosMu())
			System.out.println("dequeue() -> " + kuyruk.dequeue());
		System.out.println("on = " + kuyruk.on + ", arka = " + kuyruk.arka);

		// Bos kuyruktan cikarma: alt tasma (underflow)
		try {
			kuyruk.dequeue();
		} catch (NoSuchElementException e) {
			System.out.println("Bos kuyrukta dequeue(): NoSuchElementException");
		}
	}
}
