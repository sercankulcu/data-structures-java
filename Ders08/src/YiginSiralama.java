public class YiginSiralama {
	
	// Verilen diziyi yigin siralamasi (Heap Sort) kullanarak siralar
	public static void heapSort(int[] dizi) {
		int n = dizi.length;

		// Max heap olustur
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(dizi, n, i); // Her alt agaci max heap yap
		}

		// Siralama islemi
		for (int i = n - 1; i > 0; i--) {
			// Koku (en buyuk elemani) son elemanla degistir
			int gecici = dizi[0];
			dizi[0] = dizi[i];
			dizi[i] = gecici;

			// Max heap ozelligini kalan dizi icin sagla
			heapify(dizi, i, 0);
		}
	}

	// Verilen bir diziyi max heap yapisina uygun hale getirir
	public static void heapify(int[] dizi, int n, int indeks) {
		int enBuyuk = indeks; // Kendi indeksini en buyuk olarak baslat
		int solCocuk = 2 * indeks + 1; // Sol cocuk indeksi
		int sagCocuk = 2 * indeks + 2; // Sag cocuk indeksi

		// Eger sol cocuk daha buyukse, en buyugu sol cocuk yap
		if (solCocuk < n && dizi[solCocuk] > dizi[enBuyuk]) {
			enBuyuk = solCocuk;
		}

		// Eger sag cocuk daha buyukse, en buyugu sag cocuk yap
		if (sagCocuk < n && dizi[sagCocuk] > dizi[enBuyuk]) {
			enBuyuk = sagCocuk;
		}

		// Eger en buyuk eleman kok degilse, yer degistir
		if (enBuyuk != indeks) {
			int gecici = dizi[indeks];
			dizi[indeks] = dizi[enBuyuk];
			dizi[enBuyuk] = gecici;

			// Alt agac icin yeniden heapify uygula
			heapify(dizi, n, enBuyuk);
		}
	}

	public static void main(String[] args) {
		int[] dizi = {12, 11, 13, 5, 6, 7, 1, 2, 4, 15, 3};

		// Siralanmamis diziyi yazdir
		System.out.println("Siralanmamis Dizi:");
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}

		// Yigin siralamasi uygula
		heapSort(dizi);

		// Siralanmis diziyi yazdir
		System.out.println("\nSiralanmis Dizi:");
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}
	}
}
