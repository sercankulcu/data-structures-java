import java.util.Arrays;

// Ayni problem, iki algoritma: onek ortalamalari (Slayt 43, 44, 45, 46).
// a[j] = (x[0] + ... + x[j]) / (j + 1)
public class OnekOrtalamalari {

	// Her j icin toplami bastan hesaplar: 1 + 2 + ... + n = n(n+1)/2 toplama -> O(n^2)
	static double[] onekOrt1(double[] x) {
		int n = x.length;
		double[] a = new double[n];
		for (int j = 0; j < n; j++) {
			double top = 0;
			for (int i = 0; i <= j; i++)
				top += x[i];
			a[j] = top / (j + 1);
		}
		return a;
	}

	// Birikimli toplami tasir: n toplama -> O(n)
	static double[] onekOrt2(double[] x) {
		int n = x.length;
		double[] a = new double[n];
		double top = 0;
		for (int j = 0; j < n; j++) {
			top += x[j];
			a[j] = top / (j + 1);
		}
		return a;
	}

	public static void main(String[] args) {
		double[] x = {4, 8, 6, 2};
		System.out.println("onekOrt1: " + Arrays.toString(onekOrt1(x)));  // [4.0, 6.0, 6.0, 5.0], 10 toplama
		System.out.println("onekOrt2: " + Arrays.toString(onekOrt2(x)));  // ayni sonuc, 4 toplama

		// Slayt 46: toplama sayilari
		long[] nler = {10, 100, 1000, 1000000};
		for (long n : nler)
			System.out.println("n = " + n + ": onekOrt1 " + n * (n + 1) / 2 + " toplama, onekOrt2 " + n + " toplama");
	}
}
