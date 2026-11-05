// Ikili ozyineleme ve tekrarlanan hesap: fib(n) ayni alt problemleri tekrar tekrar cozer.
// Bellekleme (memoization) her degeri bir kez hesaplar; dongu surumu O(1) ek alan kullanir.
public class FibonacciBellek {

	static long fibCagri = 0;

	// 2 F(n + 1) - 1 cagri: ustel zaman
	static int fib(int n) {
		fibCagri++;
		if (n <= 1) return n;    // taban durum
		return fib(n - 1) + fib(n - 2);
	}

	static long[] bellek = new long[91];   // 0: hesaplanmadi
	static int bellekCagri = 0;

	// Her n icin en fazla bir kez hesaplanir: O(n) zaman, O(n) bellek
	static long fibBellek(int n) {
		bellekCagri++;
		if (n <= 1) return n;
		if (bellek[n] != 0) return bellek[n];
		bellek[n] = fibBellek(n - 1) + fibBellek(n - 2);
		return bellek[n];
	}

	// O(n) zaman, O(1) ek alan
	static long fibDongu(int n) {
		long a = 0, b = 1;              // F(0), F(1)
		for (int i = 0; i < n; i++) {
			long t = a + b;  a = b;  b = t;
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(fib(4));     // 3

		int[] nler = {5, 10, 20, 30};
		for (int n : nler) {
			fibCagri = 0;
			int f = fib(n);
			System.out.println("fib(" + n + ") = " + f + ", cagri: " + fibCagri);
		}
		// 15, 177, 21891, 2692537 cagri

		bellekCagri = 0;
		System.out.println("fibBellek(40) = " + fibBellek(40) + ", cagri: " + bellekCagri);  // 102334155, 79
		System.out.println("fibDongu(90) = " + fibDongu(90));   // 2880067194370816120
	}
}
