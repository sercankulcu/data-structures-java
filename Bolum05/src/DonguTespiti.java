// Floyd dongu tespiti: yavas isaretci bir, hizli isaretci iki adim ilerler.
// Dongu varsa ikisi dongunun icinde bulusur; yoksa hizli null'a ulasir.
// Zaman O(n), ek bellek O(1). Dongunun bas dugume donmesi gerekmez.
public class DonguTespiti {

	// Dugum sinifi
	static class Dugum {
		int veri;
		Dugum sonraki;

		Dugum(int veri) {
			this.veri = veri;
			this.sonraki = null;
		}
	}

	static boolean donguVarMi(Dugum bas) {
		Dugum yavas = bas, hizli = bas;
		while (hizli != null && hizli.sonraki != null) {
			yavas = yavas.sonraki;           // 1 adim
			hizli = hizli.sonraki.sonraki;   // 2 adim
			if (yavas == hizli) return true;
		}
		return false;
	}

	// 1 -> 2 -> ... -> n listesini kurar; dongununBasi > 0 ise son dugum o dugume baglanir.
	static Dugum listeKur(int n, int dongununBasi) {
		Dugum[] d = new Dugum[n];
		for (int i = 0; i < n; i++) {
			d[i] = new Dugum(i + 1);
			if (i > 0) d[i - 1].sonraki = d[i];
		}
		if (dongununBasi > 0)
			d[n - 1].sonraki = d[dongununBasi - 1];
		return d[0];
	}

	public static void main(String[] args) {
		// 1 -> 2 -> 3 -> 4 -> 5 -> 6 ve 6 tekrar 3'u gosteriyor
		System.out.println("1..6, 6 -> 3: " + donguVarMi(listeKur(6, 3)));   // true
		// Dongusuz liste: 1 -> 2 -> ... -> 6 -> null
		System.out.println("1..6, dongu yok: " + donguVarMi(listeKur(6, 0)));  // false
		// Kenar durumlar: bos liste ve kendini gosteren tek dugum
		System.out.println("Bos liste: " + donguVarMi(null));                 // false
		System.out.println("Tek dugum, 1 -> 1: " + donguVarMi(listeKur(1, 1))); // true
	}
}
