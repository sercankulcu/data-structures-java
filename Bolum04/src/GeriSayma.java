// Ozyinelemeli dusunmek: taban durum ve ozyinelemeli adim.
// Yazdirma cagridan once mi, sonra mi yapilirsa sira degisir.
public class GeriSayma {

	static void geriSay(int n) {
		if (n == 0) {     // taban durum
			System.out.println("Bitti");
			return;
		}
		System.out.println(n);
		geriSay(n - 1);   // ozyinelemeli adim
	}

	// Once yazdir, sonra cagir: n, n-1, ..., 1
	static void geriSay2(int n) {
		if (n == 0) return;
		System.out.print(n + " ");
		geriSay2(n - 1);     // sonra cagri
	}

	// Once cagir, sonra yazdir: 1, 2, ..., n (yazdirma yigin cozulurken)
	static void ileriSay(int n) {
		if (n == 0) return;
		ileriSay(n - 1);     // once cagri
		System.out.print(n + " ");
	}

	public static void main(String[] args) {
		geriSay(3);          // 3 2 1 Bitti (her biri ayri satirda)
		geriSay2(4);         // 4 3 2 1
		System.out.println();
		ileriSay(4);         // 1 2 3 4
		System.out.println();
	}
}
