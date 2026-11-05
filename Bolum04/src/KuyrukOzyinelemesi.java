// Kuyruk ozyinelemesi: ozyinelemeli cagri metodun son isidir.
// Boyle bir metot kolayca donguye cevrilir: parametreler degisken olur, taban durum dongu kosulu.
// Not: Java derleyicisi kuyruk cagrisini kendiliginden donguye cevirmez; yigin yine buyur.
public class KuyrukOzyinelemesi {

	// Kuyruk ozyinelemeli degil: carpma, cagri donunce yapilir
	static int faktoriyel(int n) {
		if (n <= 1) return 1;
		return n * faktoriyel(n - 1);
	}

	// Kuyruk ozyinelemeli: carpma once yapilir, son is cagridir
	static int faktKuyruk(int n, int acc) {
		if (n <= 1) return acc;   // taban durum
		return faktKuyruk(n - 1, n * acc);  // son is
	}

	// Ayni hesap dongu ile: O(1) yigin derinligi
	static int faktDongu(int n) {
		int acc = 1;
		while (n > 1) {   // taban durum degilken
			acc = n * acc;  // cagrinin yeni
			n = n - 1;      //   parametreleri
		}
		return acc;       // taban: return acc
	}

	public static void main(String[] args) {
		System.out.println(faktoriyel(4));      // 24
		System.out.println(faktKuyruk(4, 1));   // 24
		System.out.println(faktDongu(4));       // 24
		System.out.println(faktDongu(12));      // 479001600 (13! int'e sigmaz)
	}
}
