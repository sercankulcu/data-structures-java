// int anahtarli BST uzerinde sorgular: ardil, oncul, aralik sorgusu ve BST dogrulama (bstMi).
public class BstSorgulari {

	static class AgacDugumu {
		int veri;
		AgacDugumu sol, sag;

		AgacDugumu(int veri) {
			this.veri = veri;
			this.sol = null;
			this.sag = null;
		}
	}

	static AgacDugumu ekle(AgacDugumu kok, int veri) {
		if (kok == null) return new AgacDugumu(veri);
		if (veri < kok.veri)
			kok.sol = ekle(kok.sol, veri);
		else if (veri > kok.veri)
			kok.sag = ekle(kok.sag, veri);
		return kok;  // ayni anahtar eklenmez
	}

	// Ardil: x'ten buyuk en kucuk anahtar (yoksa null). O(h)
	static AgacDugumu ardil(AgacDugumu kok, int x) {
		AgacDugumu aday = null; // x'ten buyuk en kucuk
		while (kok != null) {
			if (x < kok.veri) {
				aday = kok;     // kok bir aday
				kok = kok.sol;  // daha kucugu solda
			} else
				kok = kok.sag;  // x'ten buyukler sagda
		}
		return aday;
	}

	// Oncul: x'ten kucuk en buyuk anahtar (yoksa null). ardil'in simetrigi
	static AgacDugumu oncul(AgacDugumu kok, int x) {
		AgacDugumu aday = null; // x'ten kucuk en buyuk
		while (kok != null) {
			if (x > kok.veri) {
				aday = kok;     // kok bir aday
				kok = kok.sag;  // daha buyugu sagda
			} else
				kok = kok.sol;  // x'ten kucukler solda
		}
		return aday;
	}

	// [lo, hi] araligindaki anahtarlari sirali yazar. O(h + s), s: yazilan anahtar sayisi
	static void aralik(AgacDugumu k, int lo, int hi) {
		if (k == null) return;
		if (lo < k.veri)           // solda aday var
			aralik(k.sol, lo, hi);
		if (lo <= k.veri && k.veri <= hi)
			System.out.print(k.veri + " ");
		if (k.veri < hi)           // sagda aday var
			aralik(k.sag, lo, hi);
	}

	// Her dugum, atalarindan gelen (min, max) acik araligina dusmeli. O(n)
	// ilk cagri: bstMi(kok, Long.MIN_VALUE, Long.MAX_VALUE)
	static boolean bstMi(AgacDugumu k, long min, long max) {
		if (k == null) return true;
		if (k.veri <= min || k.veri >= max)
			return false;          // aralik disinda
		return bstMi(k.sol, min, k.veri)
			&& bstMi(k.sag, k.veri, max);
	}

	static String yaz(AgacDugumu d) {
		return d == null ? "yok" : String.valueOf(d.veri);
	}

	public static void main(String[] args) {
		// Agac: 6(4(2, 5), 8(7, 9))
		AgacDugumu kok = null;
		for (int a : new int[] {6, 4, 8, 2, 5, 7, 9})
			kok = ekle(kok, a);
		System.out.println("ardil(5) = " + yaz(ardil(kok, 5)));  // 6
		System.out.println("ardil(6) = " + yaz(ardil(kok, 6)));  // 7
		System.out.println("ardil(9) = " + yaz(ardil(kok, 9)));  // yok
		System.out.println("oncul(6) = " + yaz(oncul(kok, 6)));  // 5
		System.out.println("oncul(2) = " + yaz(oncul(kok, 2)));  // yok

		// Agac: 8(4(2, 6(5, 7)), 12(10, 14))
		AgacDugumu kok2 = null;
		for (int a : new int[] {8, 4, 12, 2, 6, 10, 14, 5, 7})
			kok2 = ekle(kok2, a);
		System.out.print("aralik(kok, 5, 10): ");
		aralik(kok2, 5, 10);                                     // 5 6 7 8 10
		System.out.println();

		// Agac: 6(2(1, 4(3)), 8) gecerli bir BST
		AgacDugumu kok3 = null;
		for (int a : new int[] {6, 2, 8, 1, 4, 3})
			kok3 = ekle(kok3, a);
		System.out.println("bstMi = " + bstMi(kok3, Long.MIN_VALUE, Long.MAX_VALUE)); // true
		// 7'yi elle 4'un sagina bagla: 4 < 7 dogru ama 7, 6'nin solunda olamaz
		kok3.sol.sag.sag = new AgacDugumu(7);
		System.out.println("7 eklenince bstMi = "
			+ bstMi(kok3, Long.MIN_VALUE, Long.MAX_VALUE));  // false
	}
}
