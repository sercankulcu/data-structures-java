import java.util.Arrays;

// Dogrusal ozyineleme: her cagri en fazla bir ozyinelemeli cagri yapar.
public class DogrusalOzyineleme {

	// Ilk n ogenin toplami: n + 1 cagri, O(n)
	static int toplam(int[] a, int n) {
		if (n == 0)
			return 0;                   // taban durum
		return toplam(a, n - 1) + a[n - 1];
	}

	// a[i..j] parcasini tersine cevirir: uclari takas et, icteki parcayi cevir
	static void ters(int[] a, int i, int j) {
		if (i >= j)
			return;                    // taban: 0 ya da 1 oge
		int t = a[i]; a[i] = a[j]; a[j] = t;  // uclar
		ters(a, i + 1, j - 1);       // icteki parca
	}

	// x^n: n cagri, O(n)
	static long us(long x, int n) {
		if (n == 0) return 1;
		return x * us(x, n - 1);
	}

	static int hizliUsCagri = 0;

	// x^n, ussu yariya bolerek: floor(log2 n) + 2 cagri, O(log n)
	static long hizliUs(long x, int n) {
		hizliUsCagri++;
		if (n == 0) return 1;           // taban durum
		long y = hizliUs(x, n / 2);     // x^(n/2)
		if (n % 2 == 0) return y * y;   // n cift
		return x * y * y;               // n tek
	}

	public static void main(String[] args) {
		int[] a = {4, 3, 6, 2};
		System.out.println(toplam(a, 4));             // 15

		int[] b = {2, 7, 4, 9, 1, 6};
		ters(b, 0, b.length - 1);
		System.out.println(Arrays.toString(b));       // [6, 1, 9, 4, 7, 2]

		System.out.println(us(2, 10));                // 1024
		System.out.println(hizliUs(2, 10));           // 1024

		int[] nler = {10, 100, 1000, 1_000_000};
		for (int n : nler) {
			hizliUsCagri = 0;
			hizliUs(1, n);
			System.out.println("hizliUs n = " + n + ": " + hizliUsCagri + " cagri");
		}
		// 5, 8, 11, 21 cagri: us(x, n) ise n + 1 cagri yapar
	}
}
