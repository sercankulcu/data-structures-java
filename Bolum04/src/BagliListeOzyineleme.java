// Bagli listede ozyineleme: liste ya bostur (null) ya da bir dugum ve kalan listedir.
public class BagliListeOzyineleme {

	static class Dugum {
		int veri;
		Dugum sonraki;
		Dugum(int veri, Dugum sonraki) { this.veri = veri; this.sonraki = sonraki; }
	}

	static int uzunluk(Dugum d) {
		if (d == null) return 0;        // bos liste
		return 1 + uzunluk(d.sonraki);  // 1 + kalan
	}

	// Yazdirma cagridan once: bastan sona
	static void yazdir(Dugum d) {
		if (d == null) return;
		System.out.print(d.veri + " ");
		yazdir(d.sonraki);
	}

	// Yazdirma cagridan sonra: sondan basa (geri donusu cagri yigini saglar)
	static void tersYazdir(Dugum d) {
		if (d == null) return;
		tersYazdir(d.sonraki);  // once kalan
		System.out.print(d.veri + " ");
	}

	public static void main(String[] args) {
		Dugum bas = new Dugum(7, new Dugum(3, new Dugum(9, null)));   // bas -> 7 -> 3 -> 9 -> null

		System.out.println(uzunluk(bas));   // 3
		yazdir(bas);                        // 7 3 9
		System.out.println();
		tersYazdir(bas);                    // 9 3 7
		System.out.println();
	}
}
