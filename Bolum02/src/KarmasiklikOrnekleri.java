// Koddan karmasikliga: dongu kaliplari ve Buyuk O (Slayt 27, 28, 36, 37, 38, 42).
public class KarmasiklikOrnekleri {

	// O(1): n ne olursa olsun tek adim
	static int ilk(int[] a) {
		return a[0];
	}

	// O(n): govde n kez calisir
	static int toplam(int[] a) {
		int s = 0;
		for (int x : a)  // n kez
			s += x;
		return s;
	}

	// Theta(n): her durumda n - 1 karsilastirma
	static int maksimum(int[] a) {
		int enBuyuk = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > enBuyuk)
				enBuyuk = a[i];
		}
		return enBuyuk;
	}

	// O(log n): i her turda ikiye katlanir
	static int adimSay(int n) {
		int k = 0;
		for (int i = 1; i < n; i *= 2)
			k++;
		return k;
	}

	// O(n^2): en kotu durumda (tekrar yoksa) n(n-1)/2 karsilastirma
	static boolean tekrarVar(int[] a) {
		for (int i = 0; i < a.length; i++)
			for (int j = i + 1; j < a.length; j++)
				if (a[i] == a[j])
					return true;
		return false;
	}

	public static void main(String[] args) {
		int[] a = {7, 2, 9};
		System.out.println("ilk(a)      = " + ilk(a));              // 7
		System.out.println("toplam(a)   = " + toplam(a));           // 18

		int[] b = {3, 8, 2, 9, 4};
		System.out.println("maksimum(b) = " + maksimum(b));         // 9

		System.out.println("adimSay(16) = " + adimSay(16));         // 4 = log2 16
		System.out.println("adimSay(16000) = " + adimSay(16000));   // 14: 1000 kat buyuk n, ~10 tur fazla

		int[] c = {4, 7, 1, 7};
		int[] d = {4, 7, 1, 5};
		System.out.println("tekrarVar(c) = " + tekrarVar(c));       // true (a[1] == a[3])
		System.out.println("tekrarVar(d) = " + tekrarVar(d));       // false, 6 karsilastirma
	}
}
