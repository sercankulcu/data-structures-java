// Ozyineleme ve maliyeti (Slayt 47, 48, 49).
public class Ozyineleme {

	static int cagriSayisi = 0;   // fib cagrilarini saymak icin

	// T(n) = T(n - 1) + c -> n cagri: O(n) zaman, O(n) yigin bellegi
	static int faktoriyel(int n) {
		if (n <= 1)
			return 1;               // taban durum
		return n * faktoriyel(n - 1);
	}

	// Her cagri iki cagri yapar; ayni alt problemler tekrar cozulur: ustel zaman
	static int fib(int n) {
		cagriSayisi++;
		if (n <= 1) return n;
		return fib(n - 1) + fib(n - 2);
	}

	// Ayni deger dongu ile: O(n) zaman, O(1) ek alan
	static int fibDongu(int n) {
		int onceki = 0, simdiki = 1;
		if (n == 0) return 0;
		for (int i = 2; i <= n; i++) {
			int yeni = onceki + simdiki;
			onceki = simdiki;
			simdiki = yeni;
		}
		return simdiki;
	}

	public static void main(String[] args) {
		System.out.println(faktoriyel(4));   // 24

		int[] nler = {4, 10, 20, 30};
		for (int n : nler) {
			cagriSayisi = 0;
			int sonuc = fib(n);
			System.out.println("fib(" + n + ") = " + sonuc + ", cagri sayisi = " + cagriSayisi
					+ ", fibDongu = " + fibDongu(n));
		}
		// fib(4) -> 9 cagri; n 10 artinca cagri sayisi yaklasik 1,6^10 = 120 kat artar
	}
}
