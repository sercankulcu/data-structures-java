// Tek yonlu bagli liste: bas ve son isaretcisi, boyut alani ve slaytlardaki
// islemler (ekleme, silme, arama, ters cevirme, orta oge, kopyalama,
// tekrarlari kaldirma, sirali listeleri birlestirme, siralama).
public class TekYonluBagliListe {

	// Dugum sinifi - her dugum bir veri ve sonraki dugumun referansini tutar.
	static class Dugum {
		int veri;        // Dugumde saklanan veri
		Dugum sonraki;   // Sonraki dugume referans (son dugumde null)

		Dugum(int veri) {
			this.veri = veri;
			this.sonraki = null;
		}
	}

	private Dugum bas = null, son = null;  // Ilk ve son dugum
	private int boyut = 0;                 // Oge sayisi

	public int boyut() { return boyut; }              // O(1)
	public boolean bosMu() { return boyut == 0; }     // O(1)
	public Integer ilk() { return bosMu() ? null : bas.veri; }  // Bos listede null

	// Basa ekleme - O(1)
	void basaEkle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		yeniDugum.sonraki = bas;   // Once yeni dugum eski basi gostermeli
		bas = yeniDugum;           // Sonra bas yeni dugume tasinir
		if (son == null) son = yeniDugum;  // Liste bostuysa yeni dugum ayni zamanda son
		boyut++;
	}

	// Sona ekleme - son isaretcisi sayesinde gezinme yok: O(1)
	void sonaEkle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		if (bas == null) {
			bas = son = yeniDugum;      // Tek dugum hem bas hem son
		} else {
			son.sonraki = yeniDugum;    // Eski son yeni dugumu gosterir
			son = yeniDugum;            // Son artik yeni dugum
		}
		boyut++;
	}

	// Belirli bir konuma (indis 0'dan baslar) ekleme - O(indis)
	void konumaEkle(int veri, int indis) {
		if (indis == 0) { basaEkle(veri); return; }
		Dugum gecici = bas;
		for (int i = 0; i < indis - 1 && gecici != null; i++)
			gecici = gecici.sonraki;
		if (gecici == null || indis < 0) {   // Liste indis'ten kisa ya da indis negatif
			System.out.println("Belirtilen konum bulunamadi.");
			return;
		}
		Dugum yeniDugum = new Dugum(veri);
		yeniDugum.sonraki = gecici.sonraki;   // (1) once yeni dugumun bagini kur
		gecici.sonraki = yeniDugum;           // (2) sonra onceki dugumu yeni dugume bagla
		if (gecici == son) son = yeniDugum;   // Sona eklendiyse son guncellenir
		boyut++;
	}

	// Bastan silme - silinen dugumu dondurur, bos listede null: O(1)
	Dugum ilkOgeyiSil() {
		if (bas == null)
			return null;
		Dugum gecici = bas;        // Silinecek dugum kaybolmasin
		bas = bas.sonraki;
		gecici.sonraki = null;     // Silinen dugumu listeden tamamen kopar
		if (bas == null) son = null;  // Liste bosaldiysa son da null
		boyut--;
		return gecici;
	}

	// Sondan silme - sondan bir onceki dugum bulunmali: O(n)
	void sonOgeyiSil() {
		if (bas == null) return;
		if (bas.sonraki == null) {   // Tek dugum
			bas = son = null;
			boyut--;
			return;
		}
		Dugum gecici = bas;
		while (gecici.sonraki.sonraki != null)
			gecici = gecici.sonraki;
		gecici.sonraki = null;
		son = gecici;                // Son isaretcisi geri tasinir
		boyut--;
	}

	// Belirli bir ogeyi silme - arama O(n) + baglama O(1)
	void ogeyiSil(int silinecek) {
		if (bas == null) return;
		if (bas.veri == silinecek) {
			bas = bas.sonraki;
			if (bas == null) son = null;
			boyut--;
			return;
		}
		Dugum onceki = bas;
		Dugum gecici = bas.sonraki;
		while (gecici != null) {
			if (gecici.veri == silinecek) {
				onceki.sonraki = gecici.sonraki;    // Silinecek dugum atlanir
				if (gecici == son) son = onceki;    // Son siliniyorsa son guncellenir
				boyut--;
				return;
			}
			onceki = gecici;
			gecici = gecici.sonraki;
		}
		System.out.println("Bulunamadi.");
	}

	// Arama - ogenin indisini, yoksa -1 dondurur: O(n)
	int ogeyiAra(int aranan) {
		Dugum gecici = bas;
		int konum = 0;
		while (gecici != null) {
			if (gecici.veri == aranan)
				return konum;
			konum++;
			gecici = gecici.sonraki;
		}
		return -1;   // Bulunamadi
	}

	boolean iceriyor(int v) { return ogeyiAra(v) != -1; }

	// Uzunluk - gezinerek sayar: O(n) (boyut() ise O(1))
	int listeUzunlugu() {
		int uzunluk = 0;
		Dugum gecici = bas;
		while (gecici != null) {
			uzunluk++;
			gecici = gecici.sonraki;
		}
		return uzunluk;
	}

	// Gezinme ve yazdirma - O(n)
	void listeyiYazdir() {
		Dugum gecici = bas;   // bas degismez, yalnizca gecici ilerler
		System.out.print("Bagli Liste: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " -> ");
			gecici = gecici.sonraki;
		}
		System.out.println("null");
	}

	// Ters cevirme - O(n) zaman, O(1) ek bellek
	void listeyiTersCevir() {
		son = bas;                 // Eski bas yeni son olur
		Dugum onceki = null;
		Dugum suanki = bas;
		Dugum sonraki = null;
		while (suanki != null) {
			sonraki = suanki.sonraki;  // 1) sonrakini sakla
			suanki.sonraki = onceki;   // 2) bagi ters cevir
			onceki = suanki;           // 3) onceki ilerler
			suanki = sonraki;          // 4) suanki ilerler
		}
		bas = onceki;
	}

	// Orta oge - yavas ve hizli isaretci, tek gecis: O(n)
	// Cift uzunlukta ikinci orta dondurulur.
	int ortaOgeyiBul() {
		if (bas == null)
			throw new IllegalStateException("Liste bos");
		Dugum yavas = bas, hizli = bas;
		while (hizli != null && hizli.sonraki != null) {
			yavas = yavas.sonraki;          // 1 adim
			hizli = hizli.sonraki.sonraki;  // 2 adim
		}
		return yavas.veri;
	}

	// Derin kopya - yeni dugumler olusturulur; son isaretcisiyle O(n)
	TekYonluBagliListe kopyala() {
		TekYonluBagliListe kopya = new TekYonluBagliListe();
		Dugum gecici = bas;
		while (gecici != null) {
			kopya.sonaEkle(gecici.veri);
			gecici = gecici.sonraki;
		}
		return kopya;
	}

	// Tekrarlayanlari kaldirma - gorulen degerler ikinci bir listede: O(n^2)
	void tekrarlayanlariKaldir() {
		TekYonluBagliListe gorulen = new TekYonluBagliListe();
		Dugum onceki = null, gecici = bas;
		while (gecici != null) {
			int v = gecici.veri;
			if (gorulen.iceriyor(v)) {
				// Ilk dugum hic tekrar olmaz; bu yuzden burada onceki null degildir.
				onceki.sonraki = gecici.sonraki;   // Atla
				if (gecici == son) son = onceki;
				boyut--;
			} else {
				gorulen.basaEkle(v);
				onceki = gecici;
			}
			gecici = gecici.sonraki;
		}
	}

	// Iki sirali listeyi birlestirme - son isaretcisiyle O(n + m)
	static TekYonluBagliListe birlestir(TekYonluBagliListe l1, TekYonluBagliListe l2) {
		TekYonluBagliListe sonuc = new TekYonluBagliListe();
		Dugum g1 = l1.bas, g2 = l2.bas;
		while (g1 != null && g2 != null) {
			if (g1.veri <= g2.veri) {   // Esitlikte l1 once gelir
				sonuc.sonaEkle(g1.veri);
				g1 = g1.sonraki;
			} else {
				sonuc.sonaEkle(g2.veri);
				g2 = g2.sonraki;
			}
		}
		// Kalan ogeleri ekle
		while (g1 != null) {
			sonuc.sonaEkle(g1.veri);
			g1 = g1.sonraki;
		}
		while (g2 != null) {
			sonuc.sonaEkle(g2.veri);
			g2 = g2.sonraki;
		}
		return sonuc;
	}

	// Siralama - deger takasi, dugumler yerinde kalir: O(n^2)
	void sirala() {
		Dugum suanki = bas;
		while (suanki != null) {
			Dugum diger = suanki.sonraki;
			while (diger != null) {
				if (suanki.veri > diger.veri) {
					int gecici = suanki.veri;
					suanki.veri = diger.veri;
					diger.veri = gecici;
				}
				diger = diger.sonraki;
			}
			suanki = suanki.sonraki;
		}
	}

	// Verilen degerleri sona ekleyerek liste olusturur (main icin yardimci)
	static TekYonluBagliListe olustur(int... degerler) {
		TekYonluBagliListe liste = new TekYonluBagliListe();
		for (int d : degerler)
			liste.sonaEkle(d);
		return liste;
	}

	public static void main(String[] args) {

		// Dugumleri elle olusturup baglama
		Dugum ilk = new Dugum(10);
		Dugum ikinci = new Dugum(1);
		Dugum ucuncu = new Dugum(8);
		Dugum dorduncu = new Dugum(11);
		ilk.sonraki = ikinci;
		ikinci.sonraki = ucuncu;
		ucuncu.sonraki = dorduncu;
		System.out.print("Elle kurulan: ");
		for (Dugum d = ilk; d != null; d = d.sonraki)
			System.out.print(d.veri + " -> ");
		System.out.println("null");             // 10 -> 1 -> 8 -> 11 -> null

		// Basa ekleme
		TekYonluBagliListe liste = new TekYonluBagliListe();
		liste.basaEkle(11);
		liste.basaEkle(8);
		liste.basaEkle(1);
		liste.listeyiYazdir();                  // 1 -> 8 -> 11 -> null

		// Sona ekleme, uzunluk ve arama
		liste = olustur(10, 1, 8, 11);
		liste.listeyiYazdir();                  // 10 -> 1 -> 8 -> 11 -> null
		System.out.println("listeUzunlugu(): " + liste.listeUzunlugu() + ", boyut(): " + liste.boyut()); // 4, 4
		System.out.println("ogeyiAra(8): " + liste.ogeyiAra(8));    // 2
		System.out.println("ogeyiAra(99): " + liste.ogeyiAra(99));  // -1

		// Konuma ekleme ve belirli ogeyi silme
		liste.konumaEkle(5, 2);
		liste.listeyiYazdir();                  // 10 -> 1 -> 5 -> 8 -> 11 -> null
		liste.ogeyiSil(5);
		liste.ogeyiSil(8);
		liste.listeyiYazdir();                  // 10 -> 1 -> 11 -> null

		// Bastan silme (uc kez) ve bos listede silme
		while (!liste.bosMu())
			System.out.println("ilkOgeyiSil(): " + liste.ilkOgeyiSil().veri); // 10, 1, 11
		System.out.println("Bos listede ilkOgeyiSil(): " + liste.ilkOgeyiSil()); // null

		// Sondan silme
		liste = olustur(10, 1, 8, 11);
		liste.sonOgeyiSil();
		liste.sonaEkle(5);                      // son dogru guncellendi mi?
		liste.listeyiYazdir();                  // 10 -> 1 -> 8 -> 5 -> null

		// Ters cevirme
		liste = olustur(10, 8, 1, 11);
		liste.listeyiTersCevir();
		liste.listeyiYazdir();                  // 11 -> 1 -> 8 -> 10 -> null

		// Orta oge
		System.out.println("Orta (10 8 1 11 5): " + olustur(10, 8, 1, 11, 5).ortaOgeyiBul()); // 1
		System.out.println("Orta (10 8 1 11): " + olustur(10, 8, 1, 11).ortaOgeyiBul());     // 1

		// Kopyalama: kopya degisince orijinal etkilenmez
		liste = olustur(10, 1, 8);
		TekYonluBagliListe kopya = liste.kopyala();
		kopya.sonaEkle(99);
		liste.listeyiYazdir();                  // 10 -> 1 -> 8 -> null
		kopya.listeyiYazdir();                  // 10 -> 1 -> 8 -> 99 -> null

		// Tekrarlari kaldirma
		liste = olustur(3, 5, 3, 7, 5);
		liste.tekrarlayanlariKaldir();
		liste.listeyiYazdir();                  // 3 -> 5 -> 7 -> null

		// Sirali listeleri birlestirme
		birlestir(olustur(1, 4, 7), olustur(2, 3, 8)).listeyiYazdir(); // 1 -> 2 -> 3 -> 4 -> 7 -> 8 -> null

		// Siralama
		liste = olustur(4, 1, 3, 2);
		liste.sirala();
		liste.listeyiYazdir();                  // 1 -> 2 -> 3 -> 4 -> null
	}
}
