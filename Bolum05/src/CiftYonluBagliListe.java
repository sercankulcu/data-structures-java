// Cift yonlu bagli liste: her dugum hem onceki hem sonraki dugumu gosterir.
// bas ve son tutuldugu icin uclarda ekleme ve silme O(1)'dir.
public class CiftYonluBagliListe {

	// Dugum sinifi - veri, onceki ve sonraki referanslari
	static class Dugum {
		int veri;
		Dugum onceki;
		Dugum sonraki;

		Dugum(int veri) {
			this.veri = veri;
			this.onceki = null;
			this.sonraki = null;
		}
	}

	private Dugum bas = null, son = null;   // Ilk ve son dugum

	// Basa ekleme - O(1)
	public void basaEkle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		if (bas == null) {
			bas = yeniDugum;           // Liste bossa yeni dugum hem bas hem son
			son = yeniDugum;
		} else {
			yeniDugum.sonraki = bas;   // Ileri bag
			bas.onceki = yeniDugum;    // Geri bag
			bas = yeniDugum;
		}
	}

	// Sona ekleme - son dogrudan bilinir, gezinme yok: O(1)
	public void sonaEkle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		if (bas == null) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			yeniDugum.onceki = son;    // Geri bag
			son.sonraki = yeniDugum;   // Ileri bag
			son = yeniDugum;
		}
	}

	// Belirli bir konuma (indis 0'dan baslar) ekleme - yurume O(n), baglama O(1)
	void konumaEkle(int veri, int indis) {
		if (indis == 0) { basaEkle(veri); return; }
		Dugum gecici = bas;
		for (int i = 0; i < indis - 1 && gecici != null; i++)
			gecici = gecici.sonraki;
		if (gecici == null || indis < 0) { System.out.println("Gecersiz konum."); return; }
		if (gecici == son) { sonaEkle(veri); return; }   // son da guncellenir
		Dugum yeni = new Dugum(veri);
		yeni.sonraki = gecici.sonraki;     // (1)
		yeni.onceki = gecici;              // (2)
		gecici.sonraki.onceki = yeni;      // (3) (4)'ten once yapilmali
		gecici.sonraki = yeni;             // (4)
	}

	// Bastan silme - O(1)
	void ilkOgeyiSil() {
		if (bas == null) return;   // Bos
		if (bas == son) {          // Tek dugum: bas ve son birlikte null
			bas = son = null;
		} else {
			bas = bas.sonraki;
			bas.onceki = null;     // Silinen dugume geri bag kalmasin
		}
	}

	// Sondan silme - son.onceki ile tek adimda: O(1)
	void sonOgeyiSil() {
		if (son == null) return;   // Bos
		if (bas == son) {          // Tek dugum
			bas = son = null;
		} else {
			son = son.onceki;
			son.sonraki = null;
		}
	}

	// Belirli bir ogeyi silme - arama O(n), baglama O(1)
	void ogeyiSil(int veri) {
		Dugum gecici = bas;
		while (gecici != null && gecici.veri != veri)
			gecici = gecici.sonraki;
		if (gecici == null) return;  // Bulunamadi
		if (gecici.onceki != null)
			gecici.onceki.sonraki = gecici.sonraki;
		else bas = gecici.sonraki;   // Bas siliniyor
		if (gecici.sonraki != null)
			gecici.sonraki.onceki = gecici.onceki;
		else son = gecici.onceki;    // Son siliniyor
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
		return -1;
	}

	// Ileri yonde yazdirma - bas'tan baslayip sonraki izlenir: O(n)
	void listeyiIleriYazdir() {
		Dugum gecici = bas;
		System.out.print("Ileri Yonde: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " <-> ");
			gecici = gecici.sonraki;
		}
		System.out.println("null");
	}

	// Geri yonde yazdirma - son'dan baslayip onceki izlenir: O(n)
	void listeyiGeriYazdir() {
		Dugum gecici = son;
		System.out.print("Geri Yonde: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " <-> ");
			gecici = gecici.onceki;
		}
		System.out.println("null");
	}

	public static void main(String[] args) {

		CiftYonluBagliListe liste = new CiftYonluBagliListe();
		liste.sonaEkle(10);
		liste.sonaEkle(15);
		liste.sonaEkle(25);
		liste.basaEkle(1);
		liste.listeyiIleriYazdir();   // Ileri Yonde: 1 <-> 10 <-> 15 <-> 25 <-> null

		liste.sonaEkle(30);
		liste.listeyiIleriYazdir();   // Ileri Yonde: 1 <-> 10 <-> 15 <-> 25 <-> 30 <-> null
		liste.listeyiGeriYazdir();    // Geri Yonde: 30 <-> 25 <-> 15 <-> 10 <-> 1 <-> null

		liste.sonOgeyiSil();
		liste.listeyiIleriYazdir();   // Ileri Yonde: 1 <-> 10 <-> 15 <-> 25 <-> null

		liste.konumaEkle(12, 2);
		liste.listeyiIleriYazdir();   // Ileri Yonde: 1 <-> 10 <-> 12 <-> 15 <-> 25 <-> null
		System.out.println("ogeyiAra(15): " + liste.ogeyiAra(15));  // 3

		liste.ogeyiSil(12);
		liste.ogeyiSil(15);
		liste.listeyiIleriYazdir();   // Ileri Yonde: 1 <-> 10 <-> 25 <-> null

		liste.ilkOgeyiSil();
		liste.listeyiIleriYazdir();   // Ileri Yonde: 10 <-> 25 <-> null

		liste.ogeyiSil(25);           // Son siliniyor: son guncellenmeli
		liste.listeyiGeriYazdir();    // Geri Yonde: 10 <-> null

		liste.ilkOgeyiSil();          // Tek dugum: bas ve son null
		liste.listeyiIleriYazdir();   // Ileri Yonde: null
		liste.listeyiGeriYazdir();    // Geri Yonde: null
	}
}
