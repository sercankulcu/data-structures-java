import java.util.Arrays;

// Heap sort: once diziden max-heap olusturulur, sonra en buyuk tekrar tekrar sona alinir
public class HeapSort {

	public static void heapSort(int[] a) {
		int n = a.length;
		// 1) olustur: son ic dugumden koke, O(n)
		for (int k = n / 2 - 1; k >= 0; k--) {
			batir(a, k, n);
		}
		// 2) sirala: en buyugu sona al, heap bir kuculur, O(n log n)
		for (int son = n - 1; son > 0; son--) {
			yerDegistir(a, 0, son);
			batir(a, 0, son); // heap boyutu = son
		}
	}

	// Down-heap: MaxHeap'teki batir ile ayni; dizi ve heap boyutu parametre olarak gelir
	static void batir(int[] a, int i, int n) {
		while (2 * i + 1 < n) {
			int j = 2 * i + 1; // j: buyuk cocuk
			if (j + 1 < n && a[j + 1] > a[j]) j++;
			if (a[i] >= a[j]) return;
			yerDegistir(a, i, j);
			i = j;
		}
	}

	static void yerDegistir(int[] a, int i, int j) {
		int gecici = a[i];
		a[i] = a[j];
		a[j] = gecici;
	}

	public static void main(String[] args) {
		int[][] ornekler = {
			{4, 1, 3, 2, 16, 9, 10, 14, 8, 7},
			{6, 5, 3, 4, 1, 2},
			{4, 10, 3, 5, 1}
		};
		for (int[] a : ornekler) {
			System.out.print(Arrays.toString(a) + " -> ");
			heapSort(a);
			System.out.println(Arrays.toString(a));
		}
	}
}
