// Dairesel kuyruk (circular queue): dizi ile, bas indisi ve boyut sayaci ile
// Arka indis saklanmaz; gerektiginde (bas + boyut) % dizi.length ile hesaplanir.
public class DaireselKuyruk<E> {

	private E[] dizi;       // ogeleri saklar
	private int bas = 0;    // ilk ogenin indisi (on)
	private int boyut = 0;  // oge sayisi

	@SuppressWarnings("unchecked")
	public DaireselKuyruk(int kapasite) {
		dizi = (E[]) new Object[kapasite];
	}

	public int boyut()      { return boyut; }
	public boolean bosMu()  { return boyut == 0; }
	public boolean doluMu() { return boyut == dizi.length; }   // boyut sayaci dolu/bos ayrimini saglar
	public E ilk()          { return bosMu() ? null : dizi[bas]; }

	// Arkaya ekleme (enqueue): dolu ise hata, O(1)
	public void ekle(E oge) {
		if (doluMu())
			throw new IllegalStateException("dolu");
		int son = (bas + boyut) % dizi.length;   // sonraki bos yuva, sona gelince basa sarar
		dizi[son] = oge;
		boyut++;
	}

	// Ondeki ogeyi cikarma (dequeue), O(1)
	public E cikar() {
		if (bosMu())
			throw new IllegalStateException("bos");
		E oge = dizi[bas];
		dizi[bas] = null;               // referansi birak
		bas = (bas + 1) % dizi.length;
		boyut--;
		return oge;
	}

	// Slayt 19: dolu kuyrukta hata yerine kapasiteyi iki katina cikaran ekleme (amortize O(1))
	public void ekleBuyuterek(E oge) {
		if (boyut == dizi.length) buyut(2 * dizi.length);
		dizi[(bas + boyut) % dizi.length] = oge;
		boyut++;
	}

	// Ogeleri on sirasiyla yeni dizinin basina kopyalar (Arrays.copyOf sirayi bozardi)
	private void buyut(int yeniKap) {
		@SuppressWarnings("unchecked")
		E[] yeni = (E[]) new Object[yeniKap];
		for (int k = 0; k < boyut; k++)
			yeni[k] = dizi[(bas + k) % dizi.length];
		dizi = yeni;
		bas = 0;
	}

	// Dizinin ham icerigi (bos yuvalar "_") ve bas/boyut degerleri
	public void diziyiGoster() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < dizi.length; i++) {
			sb.append(dizi[i] == null ? "_" : dizi[i]);
			if (i < dizi.length - 1) sb.append(", ");
		}
		System.out.println("  dizi = " + sb + "]  bas = " + bas + "  boyut = " + boyut);
	}

	public static void main(String[] args) {
		// Slayt 14: N = 5
		DaireselKuyruk<Integer> kuyruk = new DaireselKuyruk<>(5);
		kuyruk.ekle(10);
		kuyruk.ekle(20);
		kuyruk.ekle(30);
		System.out.println("cikar() -> " + kuyruk.cikar());   // 10
		System.out.println("cikar() -> " + kuyruk.cikar());   // 20
		kuyruk.ekle(40);
		kuyruk.ekle(50);
		kuyruk.ekle(60);   // (2 + 3) % 5 = 0: basa sarar
		kuyruk.ekle(70);   // (2 + 4) % 5 = 1
		kuyruk.diziyiGoster();
		System.out.println("Dolu mu? " + kuyruk.doluMu() + "  ilk() = " + kuyruk.ilk());

		// Slayt 17: dolu kuyruga ekleme
		try {
			kuyruk.ekle(80);
		} catch (IllegalStateException e) {
			System.out.println("ekle(80): " + e.getMessage());
		}

		// Cikis sirasi giris sirasi ile ayni (FIFO): 30 40 50 60 70
		System.out.print("Cikis sirasi:");
		while (!kuyruk.bosMu())
			System.out.print(" " + kuyruk.cikar());
		System.out.println();
		kuyruk.diziyiGoster();

		// Slayt 19: dolu ve basa sarmis kuyrugu buyutme (dizi: C D A B, bas = 2)
		DaireselKuyruk<String> k2 = new DaireselKuyruk<>(4);
		k2.ekle("X");
		k2.ekle("Y");
		k2.ekle("A");
		k2.ekle("B");
		k2.cikar();
		k2.cikar();
		k2.ekle("C");
		k2.ekle("D");
		System.out.println("Buyutmeden once:");
		k2.diziyiGoster();
		k2.ekleBuyuterek("E");
		System.out.println("ekleBuyuterek(E) sonrasi:");
		k2.diziyiGoster();
	}
}
