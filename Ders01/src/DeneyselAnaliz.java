// Deneysel analiz ve katlama deneyi (Slayt 25, 26).
// n her seferinde ikiye katlanir; O(n^2) bir metotta sure yaklasik 4 katina cikar.
// Not: sureler makineye, JVM'e ve yuke gore degisir; ilk satirlar JIT isinmasi
// nedeniyle gurultulu olabilir.
public class DeneyselAnaliz {

	// O(n^2): tekrarsiz dizide (en kotu durum) n(n-1)/2 karsilastirma yapar
	static boolean tekrarVar(int[] a) {
		for (int i = 0; i < a.length; i++)
			for (int j = i + 1; j < a.length; j++)
				if (a[i] == a[j])
					return true;
		return false;
	}

	public static void main(String[] args) {
		for (int n = 1000; n <= 64000; n *= 2) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) a[i] = i; // tekrarsiz
			long bas = System.nanoTime();
			tekrarVar(a);                        // olculen is
			long sure = System.nanoTime() - bas;  // ns
			System.out.println(n + " " + sure / 1e6 + " ms");
		}
	}
}
