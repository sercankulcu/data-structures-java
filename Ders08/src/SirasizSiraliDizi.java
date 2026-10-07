import java.util.Arrays;

// Dizi ile iki basit min-oncelikli kuyruk: sirasiz dizi ve sirali dizi
public class SirasizSiraliDizi {

	// Sirasiz dizi: insert O(1), removeMin O(n)
	static class Sirasiz {
		int[] a = new int[10];
		int n = 0;

		void insert(int x) {
			a[n] = x;
			n++; // sona ekle
		}

		int removeMin() { // n > 0 olmali
			int enk = 0; // en kucugun indisi
			for (int k = 1; k < n; k++) {
				if (a[k] < a[enk]) enk = k;
			}
			int min = a[enk];
			a[enk] = a[n - 1]; // son oge bosluga, kaydirma yok
			n--;
			return min;
		}

		@Override
		public String toString() {
			return Arrays.toString(Arrays.copyOf(a, n));
		}
	}

	// Sirali dizi (buyukten kucuge): min hep a[n-1]; insert O(n), removeMin O(1)
	static class Sirali {
		int[] a = new int[10];
		int n = 0;

		void insert(int x) {
			int k = n - 1;
			while (k >= 0 && a[k] < x) { // x'ten kucukler
				a[k + 1] = a[k]; // bir saga kaydir
				k--;
			}
			a[k + 1] = x;
			n++;
		}

		int removeMin() { // n > 0 olmali
			n--;
			return a[n];
		}

		@Override
		public String toString() {
			return Arrays.toString(Arrays.copyOf(a, n));
		}
	}

	public static void main(String[] args) {
		Sirasiz s = new Sirasiz();
		for (int x : new int[] {5, 9, 3, 7, 4}) {
			s.insert(x);
		}
		System.out.println("Sirasiz: " + s);
		System.out.println("removeMin() = " + s.removeMin() + " -> " + s);

		Sirali t = new Sirali();
		for (int x : new int[] {9, 7, 5, 3, 2}) {
			t.insert(x);
		}
		System.out.println("Sirali:  " + t);
		t.insert(4);
		System.out.println("insert(4)     -> " + t);
		System.out.println("removeMin() = " + t.removeMin() + " -> " + t);
	}
}
