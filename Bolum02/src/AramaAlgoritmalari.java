// Dogrusal arama O(n) ile ikili arama O(log n) karsilastirmasi (Slayt 39, 40, 41).
public class AramaAlgoritmalari {

	// Bastan tek tek bakar; her adimda yalniz bir oge elenir: O(n)
	static int dogrusalArama(int[] a, int hedef) {
		for (int i = 0; i < a.length; i++)
			if (a[i] == hedef) return i;
		return -1;
	}

	// Dizi SIRALI olmali. Her tur araligi yariya indirir: O(log n)
	static int ikiliArama(int[] a, int hedef) {
		int lo = 0, hi = a.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;   // cok buyuk dizilerde lo + (hi - lo) / 2 daha guvenli
			if (a[mid] == hedef) return mid;
			if (a[mid] < hedef) lo = mid + 1;
			else hi = mid - 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

		System.out.println("dogrusalArama(a, 23) = " + dogrusalArama(a, 23));  // 5 (6 karsilastirma)
		System.out.println("ikiliArama(a, 23)    = " + ikiliArama(a, 23));     // 5 (3 tur)
		System.out.println("dogrusalArama(a, 40) = " + dogrusalArama(a, 40));  // -1 (10 karsilastirma)
		System.out.println("ikiliArama(a, 40)    = " + ikiliArama(a, 40));     // -1 (4 tur)
	}
}
