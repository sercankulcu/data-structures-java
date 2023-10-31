
public class MaxOncelikliKuyruk {
	Integer[] heap; // İnteger dizisi heap
	int n; // tamsayı n

	public MaxOncelikliKuyruk(int kapasite) {
		heap = new Integer[kapasite + 1]; // heap'i (kapsam) kapasite + 1 uzunluğunda bir İnteger dizisi olarak oluştur
		n = 0; // n'yi 0 olarak ayarla
	}

	public boolean bosMu() {
		return n == 0; // n 0'a eşitse true döndür
	}

	public int buyukluk() {
		return n; // n'yi döndür
	}

	public void ekle(int x) {
		if (n == heap.length - 1) {
			buyut(2 * heap.length); // Dizi boyutunu büyültmek gerekiyorsa büyült
		}
		n++; // Diziye bir eleman ekledikten sonra n'i artır
		heap[n] = x; // Yeni elemanı heap dizisinin sonuna ekle
		yuzdur(n);
	}

	private void yuzdur(int k) {
		while (k > 1 && heap[k / 2] < heap[k]) {
			int gecici = heap[k]; // Geçici bir değişken oluştur ve heap[k]'yi sakla
			heap[k] = heap[k / 2]; // heap[k]'yi üst düğümle değiştir
			heap[k / 2] = gecici; // Üst düğümü geçici ile değiştir
			k = k / 2; // K'yi üst düğüme hareket ettir
		}
	}


	public int sil() {
		int max = heap[1]; // Kök düğümü sakla
		yerDegistir(1, n); // Kökü son elemanla değiştir
		n--; // Eleman sayısını azalt
		batir(1); // Kökü doğru konumda düzenlemek için batırma işlemini uygula
		heap[n + 1] = null; // Eski kökü temizle
		if (n > 0 && (n == (heap.length - 1) / 4)) {
			kucult(heap.length / 2); // Dizi boyutunu küçültmek gerekiyorsa küçült
		}
		return max; // Silinen maksimum elemanı döndür
	}


	public void batir(int k) {
		while (2 * k <= n) {
			int j = 2 * k; // Daha büyük çocuğu bulmak için sol çocuğun konumunu sakla
			if (j < n && heap[j] < heap[j + 1]) {
				j++; // Sağ çocuk sol çocuktan daha büyükse, sağ çocuğun konumunu sakla
			}
			if (heap[k] >= heap[j]) {
				break; // Mevcut düğüm, en büyük çocuktan büyük veya eşitse döngüyü sonlandır
			}
			yerDegistir(k, j); // Düğümleri değiştirerek düğümü doğru konuma getir
			k = j; // İşlemi devam ettirmek için k'yi büyük çocuğun konumuna güncelle
		}
	}

	private void kucult(int kapasite) {
		Integer gecici[] = new Integer[kapasite];
		for (int i = 0; i < heap.length; i++) {
			gecici[i] = heap[i];
		}
		heap = gecici;
	}

	private void buyut(int kapasite) {
		Integer gecici[] = new Integer[kapasite];
		for(int i = 0; i < heap.length; i++) {
			gecici[i] = heap[i]; 
		}
		heap = gecici;
	}

	public void yerDegistir(int a, int b) {
		int gecici = heap[a];
		heap[a] = heap[b];
		heap[b] = gecici;
	}

	public void yazdir() {
		for(int i = 1; i <= n; i++) {
			System.out.print(heap[i] + " ");
		}
	}

	public static void main(String[] args) {
		MaxOncelikliKuyruk ok = new MaxOncelikliKuyruk(3); // 3 eleman kapasiteli yeni bir MaxOK nesnesi oluştur

		// Eleman ekleme işlemleri
		ok.ekle(4);
		ok.ekle(5);
		ok.ekle(2);
		ok.ekle(6);
		ok.ekle(1);
		ok.ekle(3);

		System.out.println(ok.buyukluk()); // Dizinin büyüklüğünü ekrana yazdır
		ok.yazdir(); // Dizi içeriğini ekrana yazdır

		System.out.println();

		ok.sil(); // Maksimum elemanı sil
		ok.yazdir(); // Dizi içeriğini ekrana yazdır

		System.out.println();

		ok.sil(); // Maksimum elemanı sil
		ok.yazdir(); // Dizi içeriğini ekrana yazdır
	}

}
