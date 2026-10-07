// AVL agaci: her dugumde denge faktoru bf = h(sol) - h(sag) ve bf, {-1, 0, +1} icinde kalir.
// Ekleme ve silme BST gibi yapilir; ozyineleme geri donerken yol uzerindeki her dugumde dengele cagrilir.
public class AvlAgac {

	static class AvlDugum {
		int anahtar;
		AvlDugum sol, sag;
		int yukseklik;      // yaprak: 0

		AvlDugum(int anahtar) {
			this.anahtar = anahtar;
			this.yukseklik = 0;
		}
	}

	AvlDugum kok;

	// Bos alt agacin yuksekligi -1
	int h(AvlDugum d) {
		return d == null ? -1 : d.yukseklik;
	}

	// Denge faktoru: pozitif sol agir, negatif sag agir
	int bf(AvlDugum d) {
		return h(d.sol) - h(d.sag);
	}

	void guncelle(AvlDugum d) {
		d.yukseklik = 1 + Math.max(h(d.sol), h(d.sag));
	}

	// Sol agir y: x yukari cikar, y x'in sag cocugu olur
	AvlDugum sagaDondur(AvlDugum y) {
		AvlDugum x = y.sol;
		y.sol = x.sag;  // T2 -> y.sol
		x.sag = y;      // y -> x.sag
		guncelle(y);    // once alttaki
		guncelle(x);
		return x;       // yeni kok
	}

	// sagaDondur'un simetrigi (sol <-> sag)
	AvlDugum solaDondur(AvlDugum x) {
		AvlDugum y = x.sag;
		x.sag = y.sol;  // T2 -> x.sag
		y.sol = x;      // x -> y.sol
		guncelle(x);    // once alttaki
		guncelle(y);
		return y;       // yeni kok
	}

	AvlDugum dengele(AvlDugum d) {
		guncelle(d);
		if (bf(d) > 1) {        // sol agir
			if (bf(d.sol) < 0)  // LR
				d.sol = solaDondur(d.sol);
			return sagaDondur(d); // LL
		}
		if (bf(d) < -1) {       // sag agir
			if (bf(d.sag) > 0)  // RL
				d.sag = sagaDondur(d.sag);
			return solaDondur(d); // RR
		}
		return d;
	}

	AvlDugum ekle(AvlDugum d, int k) {
		if (d == null) return new AvlDugum(k);
		if (k < d.anahtar)
			d.sol = ekle(d.sol, k);
		else if (k > d.anahtar)
			d.sag = ekle(d.sag, k);
		else return d;          // tekrar yok
		return dengele(d);
	}

	// BST silmesi + donuste dengele; birden fazla atada dondurme gerekebilir
	AvlDugum sil(AvlDugum d, int k) {
		if (d == null) return null;     // yok
		if (k < d.anahtar)
			d.sol = sil(d.sol, k);
		else if (k > d.anahtar)
			d.sag = sil(d.sag, k);
		else if (d.sol == null)
			return d.sag;               // yaprak / yalniz sag cocuk
		else if (d.sag == null)
			return d.sol;               // yalniz sol cocuk
		else {                          // iki cocuk: ardil
			AvlDugum ardil = d.sag;
			while (ardil.sol != null)
				ardil = ardil.sol;
			d.anahtar = ardil.anahtar;
			d.sag = sil(d.sag, ardil.anahtar);
		}
		return dengele(d);
	}

	// Agacin yapisini yazar: anahtar[bf](sol, sag), bos cocuk "-"
	String yapi(AvlDugum d) {
		if (d == null) return "-";
		String s = d.anahtar + "[" + bf(d) + "]";
		if (d.sol == null && d.sag == null) return s;
		return s + "(" + yapi(d.sol) + ", " + yapi(d.sag) + ")";
	}

	void ekleVeYaz(int k) {
		kok = ekle(kok, k);
		System.out.println("ekle " + k + ": " + yapi(kok));
	}

	void silVeYaz(int k) {
		kok = sil(kok, k);
		System.out.println("sil " + k + ": " + yapi(kok));
	}

	public static void main(String[] args) {
		AvlAgac agac = new AvlAgac();
		agac.ekleVeYaz(10);
		agac.ekleVeYaz(20);
		agac.ekleVeYaz(30);  // RR: 10'da solaDondur -> 20(10, 30)
		agac.ekleVeYaz(40);
		agac.ekleVeYaz(50);  // RR: 30'da solaDondur
		agac.ekleVeYaz(25);  // RL: 40'ta sagaDondur, 20'de solaDondur -> 30(20(10, 25), 40(-, 50))
		System.out.println("yukseklik: " + agac.h(agac.kok));   // 2

		agac.silVeYaz(40);   // tek cocuk: 50 yukari cikar
		agac.silVeYaz(50);   // 30'da bf = 2, bf(20) = 0 -> tek sagaDondur
		agac.silVeYaz(99);   // agacta yok: degisiklik olmaz

		// Ornek problemler: 3, 2, 1 (LL) ve 10, 30, 20 (RL)
		AvlAgac ll = new AvlAgac();
		for (int k : new int[] {3, 2, 1}) ll.kok = ll.ekle(ll.kok, k);
		System.out.println("3, 2, 1   -> " + ll.yapi(ll.kok));    // 2(1, 3)
		AvlAgac rl = new AvlAgac();
		for (int k : new int[] {10, 30, 20}) rl.kok = rl.ekle(rl.kok, k);
		System.out.println("10, 30, 20 -> " + rl.yapi(rl.kok));   // 20(10, 30)
	}
}
