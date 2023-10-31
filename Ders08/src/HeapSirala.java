
public class HeapSirala {
	
	public static void heapSort(int[] dizi) {
		int n = dizi.length;

		// Max heap oluştur
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(dizi, n, i);
		}

		// Heap'i sırala
		for (int i = n - 1; i > 0; i--) {
			// Kök ile son elemanı değiştir
			int gecici = dizi[0];
			dizi[0] = dizi[i];
			dizi[i] = gecici;

			// Yeniden max heap yapısını sağla
			heapify(dizi, i, 0);
		}
	}

	public static void heapify(int[] dizi, int n, int indeks) {
		int enBuyuk = indeks;
		int solCocuk = 2 * indeks + 1;
		int sagCocuk = 2 * indeks + 2;

		// Sol çocuk kökten daha büyükse
		if (solCocuk < n && dizi[solCocuk] > dizi[enBuyuk]) {
			enBuyuk = solCocuk;
		}

		// Sağ çocuk kökten daha büyükse
		if (sagCocuk < n && dizi[sagCocuk] > dizi[enBuyuk]) {
			enBuyuk = sagCocuk;
		}

		// En büyük eleman kök değilse
		if (enBuyuk != indeks) {
			int gecici = dizi[indeks];
			dizi[indeks] = dizi[enBuyuk];
			dizi[enBuyuk] = gecici;

			// Kökün altındaki alt heap'i yeniden düzenle
			heapify(dizi, n, enBuyuk);
		}
	}

	public static void main(String[] args) {
		int[] dizi = {12, 11, 13, 5, 6, 7, 1, 2, 4, 15, 3};

		System.out.println("Sıralanmamış Dizi:");
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}

		heapSort(dizi);

		System.out.println("\nSıralanmış Dizi:");
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}
	}
}

