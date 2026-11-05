// Cagri yigini sinirlidir: her cagri bir cerceve kaplar.
// Derinlik cok buyurse StackOverflowError olusur. Boyut JVM'e baglidir (-Xss ile degisir).
public class YiginTasmasi {

	static long topla(int n) {        // 1 + 2 + ... + n
		if (n == 0) return 0;
		return n + topla(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(topla(10_000));     // 50005000

		try {
			System.out.println(topla(1_000_000));
		} catch (StackOverflowError e) {
			// Programi durdurmamak icin hatayi yakaliyoruz; normalde program burada coker.
			System.out.println("topla(1_000_000): StackOverflowError");
		}

		// Ayni toplam dongu ile ya da formulle sabit yigin derinliginde hesaplanir.
		long n = 1_000_000;
		System.out.println(n * (n + 1) / 2);   // 500000500000
	}
}
