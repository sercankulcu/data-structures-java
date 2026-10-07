// Splay agaci: erisilen dugum zig, zig-zig, zig-zag adimlariyla koke tasinir.
// Dugumde denge bilgisi tutulmaz; islemler amortize O(log n).
public class SplayAgac {

	static class Dugum {
		int anahtar;
		Dugum sol, sag, ebeveyn;

		Dugum(int anahtar) {
			this.anahtar = anahtar;
		}
	}

	Dugum kok;

	// dondur(a, b): b, a'nin cocugu; b'yi a'nin yerine cikarir (kirmizi-siyah koddaki ile ayni)
	void dondur(Dugum a, Dugum b) {
		Dugum ust = a.ebeveyn;
		if (b == a.sol) {               // saga dondurme
			a.sol = b.sag;
			if (b.sag != null) b.sag.ebeveyn = a;
			b.sag = a;
		} else {                        // sola dondurme
			a.sag = b.sol;
			if (b.sol != null) b.sol.ebeveyn = a;
			b.sol = a;
		}
		a.ebeveyn = b;
		b.ebeveyn = ust;
		if (ust == null) kok = b;
		else if (ust.sol == a) ust.sol = b;
		else ust.sag = b;
	}

	// x ve p ayni yonde cocuk mu? (sol-sol ya da sag-sag)
	boolean ayniYon(Dugum x, Dugum p, Dugum g) {
		return (x == p.sol) == (p == g.sol);
	}

	void splay(Dugum x) {
		while (x != kok) {
			Dugum p = x.ebeveyn, g = p.ebeveyn;
			if (g == null)                // zig
				dondur(p, x);
			else if (ayniYon(x, p, g)) {  // zig-zig
				dondur(g, p);   // once dede
				dondur(p, x);
			} else {                      // zig-zag
				dondur(p, x);   // once ebeveyn
				dondur(g, x);
			}
		}
	}

	// Normal BST aramasi; bulunan (yoksa son ziyaret edilen) dugum koke tasinir
	boolean ara(int anahtar) {
		Dugum d = kok, son = null;
		while (d != null) {
			son = d;
			if (anahtar < d.anahtar) d = d.sol;
			else if (anahtar > d.anahtar) d = d.sag;
			else break;
		}
		if (son != null) splay(son);
		return d != null;
	}

	// BST gibi ekler, splay yapmaz (yalnizca ornek agaci kurmak icin)
	Dugum bstEkle(int anahtar) {
		Dugum ebeveyn = null, d = kok;
		while (d != null) {
			ebeveyn = d;
			if (anahtar < d.anahtar) d = d.sol;
			else if (anahtar > d.anahtar) d = d.sag;
			else return d;              // zaten var
		}
		Dugum yeni = new Dugum(anahtar);
		yeni.ebeveyn = ebeveyn;
		if (ebeveyn == null) kok = yeni;
		else if (anahtar < ebeveyn.anahtar) ebeveyn.sol = yeni;
		else ebeveyn.sag = yeni;
		return yeni;
	}

	// Splay agacinda ekleme: BST gibi ekle, sonra yeni dugumu koke tasi
	void ekle(int anahtar) {
		splay(bstEkle(anahtar));
	}

	// Yapiyi yazar: anahtar(sol, sag), bos cocuk "-"
	String yapi(Dugum d) {
		if (d == null) return "-";
		if (d.sol == null && d.sag == null) return String.valueOf(d.anahtar);
		return d.anahtar + "(" + yapi(d.sol) + ", " + yapi(d.sag) + ")";
	}

	public static void main(String[] args) {
		SplayAgac agac = new SplayAgac();
		for (int k : new int[] {80, 40, 90, 20, 60, 10, 30, 50, 70})
			agac.bstEkle(k);
		System.out.println("baslangic: " + agac.yapi(agac.kok));
		// 80(40(20(10, 30), 60(50, 70)), 90)

		agac.ara(50);   // zig-zag (60, 40), sonra zig (80)
		System.out.println("ara(50):   " + agac.yapi(agac.kok));
		// 50(40(20(10, 30), -), 80(60(-, 70), 90))

		agac.ara(10);   // zig-zig (40, 20), sonra zig (50)
		System.out.println("ara(10):   " + agac.yapi(agac.kok));
		// 10(-, 50(20(-, 40(30, -)), 80(60(-, 70), 90)))

		agac.ekle(55);  // eklenen dugum de koke tasinir
		System.out.println("ekle(55):  " + agac.yapi(agac.kok));
	}
}
