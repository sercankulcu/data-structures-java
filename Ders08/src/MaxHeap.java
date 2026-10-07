import java.util.Arrays;

// Dizi tabanli ikili max-heap: kok heap[0], cocuklar 2i+1 ve 2i+2, ebeveyn (i-1)/2
public final class MaxHeap {
	private int[] heap = new int[4]; // baslangic kapasitesi 4
	private int n = 0; // oge sayisi

	public MaxHeap() {
	}

	// Hazir diziden asagidan yukariya heap olusturur: O(n)
	public MaxHeap(int[] a) {
		heap = Arrays.copyOf(a, Math.max(4, a.length));
		n = a.length;
		heapOlustur();
	}

	public int boyut() {
		return n;
	}

	public boolean bosMu() {
		return n == 0;
	}

	// En buyuk ogeye bakar, cikarmaz: O(1)
	public int max() {
		if (n == 0) throw new IllegalStateException("Heap bos");
		return heap[0];
	}

	// Ogeyi ilk bos konuma (heap[n]) koyar, sonra yukari suzer: O(log n)
	public void ekle(int x) {
		if (n == heap.length) buyut(2 * heap.length);
		heap[n] = x;
		n++;
		yuzdur(n - 1);
	}

	// Koku cikarir: son oge koke tasinir, sonra asagi suzulur: O(log n)
	public int silMax() {
		if (n == 0) throw new IllegalStateException("Heap bos");
		int max = heap[0];
		yerDegistir(0, n - 1);
		n--; // son oge koke geldi, max heap disinda kaldi
		batir(0);
		return max;
	}

	// Uyarlanabilir oncelikli kuyruk: i indisindeki anahtari degistirir
	public void anahtarDegistir(int i, int yeni) {
		int eski = heap[i];
		heap[i] = yeni;
		if (yeni > eski) yuzdur(i); // buyudu: yukari
		else batir(i); // kuculdu: asagi
	}

	// Son ic dugumden (n/2 - 1) koke dogru her dugumu batirir
	public void heapOlustur() {
		for (int k = n / 2 - 1; k >= 0; k--) {
			batir(k);
		}
	}

	// Up-heap (yukari suzme): oge ebeveyninden buyuk oldugu surece yukari cikar
	private void yuzdur(int i) {
		while (i > 0) {
			int ebeveyn = (i - 1) / 2;
			if (heap[ebeveyn] >= heap[i]) return;
			yerDegistir(i, ebeveyn);
			i = ebeveyn;
		}
	}

	// Down-heap (asagi suzme): oge buyuk cocukla yer degistirerek asagi iner
	private void batir(int i) {
		while (2 * i + 1 < n) { // sol cocuk var mi?
			int j = 2 * i + 1; // j: buyuk cocuk
			if (j + 1 < n && heap[j + 1] > heap[j]) j++;
			if (heap[i] >= heap[j]) return;
			yerDegistir(i, j);
			i = j; // bir seviye in
		}
	}

	// Dizi dolunca yeni diziye kopyalar (amortize O(1))
	private void buyut(int kapasite) {
		int[] yeni = new int[kapasite];
		for (int k = 0; k < n; k++) {
			yeni[k] = heap[k];
		}
		heap = yeni;
	}

	private void yerDegistir(int a, int b) {
		int gecici = heap[a];
		heap[a] = heap[b];
		heap[b] = gecici;
	}

	// Verilen dizi max-heap mi? Her dugum ebeveyniyle karsilastirilir: O(n)
	public static boolean heapMi(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int e = (i - 1) / 2; // ebeveyn
			if (a[e] < a[i]) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(heap, n));
	}

	public static void main(String[] args) {
		// Bos heap'e art arda ekleme; 5. eklemede kapasite 4'ten 8'e cikar
		MaxHeap h = new MaxHeap();
		for (int x : new int[] {4, 5, 2, 6, 1, 3}) {
			h.ekle(x);
		}
		System.out.println("4, 5, 2, 6, 1, 3 eklendi: " + h);

		// ekle(10), ekle(8) ve art arda iki silMax()
		h = new MaxHeap(new int[] {9, 3, 6, 2, 1, 5, 4});
		h.ekle(10);
		h.ekle(8);
		System.out.println("ekle(10), ekle(8):     " + h);
		System.out.println("silMax() = " + h.silMax() + "  -> " + h);
		System.out.println("silMax() = " + h.silMax() + "  -> " + h);

		// Anahtar degistirme: once buyutme (yukari), sonra kucultme (asagi)
		h = new MaxHeap(new int[] {9, 3, 6, 2, 1, 5, 4});
		h.anahtarDegistir(4, 8);
		System.out.println("anahtarDegistir(4, 8): " + h);
		h.anahtarDegistir(0, 1);
		System.out.println("anahtarDegistir(0, 1): " + h);

		// Asagidan yukariya heap olusturma
		int[] a = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		System.out.println("heapOlustur" + Arrays.toString(a) + " -> " + new MaxHeap(a));

		// heapMi kontrolu
		System.out.println("heapMi([10, 8, 9, 3, 5, 7, 4, 6]) = "
				+ heapMi(new int[] {10, 8, 9, 3, 5, 7, 4, 6}));
		System.out.println("heapMi([10, 8, 9, 3, 5, 7]) = " + heapMi(new int[] {10, 8, 9, 3, 5, 7}));
	}
}
