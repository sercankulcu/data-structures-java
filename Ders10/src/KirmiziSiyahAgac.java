// Kirmizi-siyah agac: ekleme ve ekleme sonrasi duzeltme (duzelt),
// silme ve silme sonrasi duzeltme ("cift siyah", silmeSonrasiDuzelt).
// Kurallar: kok siyah, NIL (null) siyah, kirmizinin cocuklari siyah,
// her yolda ayni sayida siyah dugum (siyah yukseklik).
public class KirmiziSiyahAgac {

	static final boolean KIRMIZI = true;
	static final boolean SIYAH = false;

	static class Dugum {
		int anahtar;
		Dugum sol, sag, ebeveyn;
		boolean renk;

		Dugum(int anahtar) {
			this.anahtar = anahtar;
			this.renk = KIRMIZI;   // yeni dugum kirmizi boyanir
		}
	}

	Dugum kok;

	// NIL (null) siyah sayilir
	boolean kirmizi(Dugum n) {
		return n != null && n.renk == KIRMIZI;
	}

	// x - p - g zikzak (ucgen) mi?
	boolean ucgen(Dugum x, Dugum p, Dugum g) {
		return (p == g.sol) != (x == p.sol);
	}

	// dondur(a, b): b, a'nin cocugu; b'yi a'nin yerine cikarir
	// (b soldaysa saga, sagdaysa sola dondurme; ebeveyn baglari dahil)
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
		if (ust == null) kok = b;       // a kokse b yeni kok
		else if (ust.sol == a) ust.sol = b;
		else ust.sag = b;
	}

	// BST gibi yapraga ekler, ebeveyni baglar; anahtar varsa null doner
	Dugum bstEkle(int anahtar) {
		Dugum ebeveyn = null, d = kok;
		while (d != null) {
			ebeveyn = d;
			if (anahtar < d.anahtar) d = d.sol;
			else if (anahtar > d.anahtar) d = d.sag;
			else return null;           // tekrar yok
		}
		Dugum yeni = new Dugum(anahtar);
		yeni.ebeveyn = ebeveyn;
		if (ebeveyn == null) kok = yeni;
		else if (anahtar < ebeveyn.anahtar) ebeveyn.sol = yeni;
		else ebeveyn.sag = yeni;
		return yeni;
	}

	void ekle(int anahtar) {
		Dugum x = bstEkle(anahtar);
		if (x == null) return;
		x.renk = KIRMIZI;
		duzelt(x);
	}

	void duzelt(Dugum x) {          // x: yeni, kirmizi
		while (x != kok && kirmizi(x.ebeveyn)) {
			Dugum p = x.ebeveyn, g = p.ebeveyn;     // p kirmizi, kok degil: g var
			Dugum u = (p == g.sol) ? g.sag : g.sol; // amca
			if (kirmizi(u)) {           // amca kirmizi
				p.renk = u.renk = SIYAH;
				g.renk = KIRMIZI;
				x = g;                  // sorun dedeye
			} else {                    // amca siyah
				if (ucgen(x, p, g)) {   // zikzak
					dondur(p, x);       // cizgiye cevir
					Dugum t = x; x = p; p = t;
				}
				dondur(g, p);           // cizgi
				p.renk = SIYAH; g.renk = KIRMIZI;
			}
		}
		kok.renk = SIYAH;
	}

	// Silme: once BST silmesi, sonra gerekirse "cift siyah" duzeltmesi.
	// Anahtar yoksa false doner.
	boolean sil(int anahtar) {
		Dugum z = kok;
		while (z != null && z.anahtar != anahtar)
			z = (anahtar < z.anahtar) ? z.sol : z.sag;
		if (z == null) return false;
		if (z.sol != null && z.sag != null) {   // iki cocuk: ardilin anahtarini tasi
			Dugum ardil = z.sag;
			while (ardil.sol != null) ardil = ardil.sol;
			z.anahtar = ardil.anahtar;
			z = ardil;                          // artik en fazla bir cocuklu dugum silinir
		}
		Dugum c = (z.sol != null) ? z.sol : z.sag; // z'nin yerine gecen (null olabilir)
		Dugum p = z.ebeveyn;
		yerineKoy(z, c);
		if (z.renk == SIYAH) {                  // kirmizi silinirse kural bozulmaz
			if (kirmizi(c)) c.renk = SIYAH;     // kirmizi cocuk siyaha boyanir: bitti
			else silmeSonrasiDuzelt(c, p);      // c "cift siyah" oldu
		}
		return true;
	}

	// z'yi agactan cikarir, yerine c'yi (null olabilir) baglar
	void yerineKoy(Dugum z, Dugum c) {
		if (c != null) c.ebeveyn = z.ebeveyn;
		if (z.ebeveyn == null) kok = c;
		else if (z == z.ebeveyn.sol) z.ebeveyn.sol = c;
		else z.ebeveyn.sag = c;
	}

	// x: cift siyah dugum (null olabilir), p: x'in ebeveyni.
	// k: x'in kardesi; cift siyah yuzunden k hicbir zaman null olmaz.
	void silmeSonrasiDuzelt(Dugum x, Dugum p) {
		while (x != kok && !kirmizi(x)) {
			if (x == p.sol) {
				Dugum k = p.sag;
				if (kirmizi(k)) {                   // durum 1: kardes kirmizi
					k.renk = SIYAH; p.renk = KIRMIZI;
					dondur(p, k);                   // sola dondurme
					k = p.sag;                      // yeni kardes siyah
				}
				if (!kirmizi(k.sol) && !kirmizi(k.sag)) { // durum 2: yegenler siyah
					k.renk = KIRMIZI;
					x = p;                          // cift siyah ebeveyne cikar
					p = x.ebeveyn;
				} else {
					if (!kirmizi(k.sag)) {          // durum 3: yalniz yakin yegen kirmizi
						k.sol.renk = SIYAH; k.renk = KIRMIZI;
						dondur(k, k.sol);           // saga dondurme
						k = p.sag;
					}
					k.renk = p.renk;                // durum 4: uzak yegen kirmizi
					p.renk = SIYAH;
					k.sag.renk = SIYAH;
					dondur(p, k);                   // sola dondurme
					x = kok;                        // duzeltme bitti
				}
			} else {                                // ayna: x sag cocuk
				Dugum k = p.sol;
				if (kirmizi(k)) {                   // durum 1
					k.renk = SIYAH; p.renk = KIRMIZI;
					dondur(p, k);                   // saga dondurme
					k = p.sol;
				}
				if (!kirmizi(k.sol) && !kirmizi(k.sag)) { // durum 2
					k.renk = KIRMIZI;
					x = p;
					p = x.ebeveyn;
				} else {
					if (!kirmizi(k.sol)) {          // durum 3
						k.sag.renk = SIYAH; k.renk = KIRMIZI;
						dondur(k, k.sag);           // sola dondurme
						k = p.sol;
					}
					k.renk = p.renk;                // durum 4
					p.renk = SIYAH;
					k.sol.renk = SIYAH;
					dondur(p, k);                   // saga dondurme
					x = kok;
				}
			}
		}
		if (x != null) x.renk = SIYAH;              // kirmizi + siyah: siyaha boya
	}

	// Siyah yukseklik (NIL haric); kural bozuksa -1
	int siyahYukseklik(Dugum d) {
		if (d == null) return 0;
		if (kirmizi(d) && (kirmizi(d.sol) || kirmizi(d.sag))) return -1; // ardisik kirmizi
		int sol = siyahYukseklik(d.sol), sag = siyahYukseklik(d.sag);
		if (sol < 0 || sol != sag) return -1;                             // esit degil
		return sol + (kirmizi(d) ? 0 : 1);
	}

	// Yapiyi yazar: anahtar + renk (K / S), bos cocuk "-"
	String yapi(Dugum d) {
		if (d == null) return "-";
		String s = d.anahtar + (kirmizi(d) ? "K" : "S");
		if (d.sol == null && d.sag == null) return s;
		return s + "(" + yapi(d.sol) + ", " + yapi(d.sag) + ")";
	}

	public static void main(String[] args) {
		KirmiziSiyahAgac agac = new KirmiziSiyahAgac();
		// 30: amca NIL, cizgi -> 10'da dondur; 15: amca kirmizi -> renk degistir;
		// 12: amca NIL, ucgen -> iki dondurme; 5: amca kirmizi -> renk degistir
		for (int k : new int[] {10, 20, 30, 15, 12, 5}) {
			agac.ekle(k);
			System.out.println("ekle " + k + ": " + agac.yapi(agac.kok)
				+ "  siyah yukseklik: " + agac.siyahYukseklik(agac.kok));
		}
		// Son: 20S(12K(10S(5K, -), 15S), 30S)

		// Silme: 5 kirmizi yaprak (duzeltme yok); 30 siyah yaprak (cift siyah,
		// kardes 12 kirmizi -> durum 1, ardindan durum 2); 20 tek cocuklu
		// (kirmizi cocuk 15 siyaha boyanir); 12 iki cocuklu kok (ardil 15'in
		// anahtari tasinir, cift siyah kokte biter); 99 agacta yok
		for (int k : new int[] {5, 30, 20, 12, 99}) {
			boolean silindi = agac.sil(k);
			System.out.println("sil " + k + (silindi ? "" : " (yok)") + ": " + agac.yapi(agac.kok)
				+ "  siyah yukseklik: " + agac.siyahYukseklik(agac.kok));
		}
	}
}
