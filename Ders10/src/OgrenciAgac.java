// Uygulama: ogrenci kayitlarini ogrenci numarasina gore BST'de tutmak.
// Dugum, ogrenci nesnesinin tamamini tasir; anahtar ogrenciNo'dur.
public class OgrenciAgac {

	static class Ogrenci {
		int ogrenciNo;     // anahtar
		String adSoyad;

		Ogrenci(int ogrenciNo, String adSoyad) {
			this.ogrenciNo = ogrenciNo;
			this.adSoyad = adSoyad;
		}
	}

	static class OgrenciDugum {
		Ogrenci ogr;           // veri
		OgrenciDugum solCocuk;
		OgrenciDugum sagCocuk;

		OgrenciDugum(Ogrenci ogr) {
			this.ogr = ogr;
			// cocuklar baslangicta null
		}
	}

	OgrenciDugum kok;

	OgrenciDugum ekle(OgrenciDugum kok, Ogrenci ogr) {
		if (kok == null) return new OgrenciDugum(ogr);
		if (ogr.ogrenciNo < kok.ogr.ogrenciNo)
			kok.solCocuk = ekle(kok.solCocuk, ogr);   // soldan devam
		else if (ogr.ogrenciNo > kok.ogr.ogrenciNo)
			kok.sagCocuk = ekle(kok.sagCocuk, ogr);   // sagdan devam
		return kok;                                   // ayni numara eklenmez
	}

	// boolean yerine ogrenci nesnesini dondurur (yoksa null)
	Ogrenci ara(OgrenciDugum kok, int numara) {
		if (kok == null) return null;                    // kayit yok
		if (numara == kok.ogr.ogrenciNo) return kok.ogr; // bulundu
		if (numara < kok.ogr.ogrenciNo)
			return ara(kok.solCocuk, numara);            // soldan devam
		return ara(kok.sagCocuk, numara);                // sagdan devam
	}

	OgrenciDugum sil(OgrenciDugum kok, int numara) {
		if (kok == null) return null;                    // kayit yok
		if (numara < kok.ogr.ogrenciNo)
			kok.solCocuk = sil(kok.solCocuk, numara);
		else if (numara > kok.ogr.ogrenciNo)
			kok.sagCocuk = sil(kok.sagCocuk, numara);
		else if (kok.solCocuk == null)
			return kok.sagCocuk;
		else if (kok.sagCocuk == null)
			return kok.solCocuk;
		else {                                           // iki cocuk
			OgrenciDugum ardil = kok.sagCocuk;
			while (ardil.solCocuk != null)
				ardil = ardil.solCocuk;
			// Nesnenin alanlarini degil referansi kopyala: silinen ogrencinin
			// nesnesi baska yerde kullaniliyorsa bozulmasin
			kok.ogr = ardil.ogr;
			kok.sagCocuk = sil(kok.sagCocuk, ardil.ogr.ogrenciNo);
		}
		return kok;
	}

	// Kok ortada gezinme: kayitlar numara sirasiyla yazilir
	void inorder(OgrenciDugum d) {
		if (d == null) return;
		inorder(d.solCocuk);
		System.out.println("  " + d.ogr.ogrenciNo + " " + d.ogr.adSoyad);
		inorder(d.sagCocuk);
	}

	void araVeYaz(int numara) {
		Ogrenci o = ara(kok, numara);
		System.out.println("ara(" + numara + "): " + (o == null ? "kayit yok" : o.adSoyad));
	}

	public static void main(String[] args) {
		OgrenciAgac agac = new OgrenciAgac();
		agac.kok = agac.ekle(agac.kok, new Ogrenci(123, "Ali Aydin"));
		agac.kok = agac.ekle(agac.kok, new Ogrenci(101, "Murat Aktas"));
		agac.kok = agac.ekle(agac.kok, new Ogrenci(133, "Ayse Konak"));
		agac.kok = agac.ekle(agac.kok, new Ogrenci(143, "Dilara Turk"));
		agac.kok = agac.ekle(agac.kok, new Ogrenci(93, "Mehmet Bey"));
		agac.kok = agac.ekle(agac.kok, new Ogrenci(105, "Asli Hanim"));
		agac.kok = agac.ekle(agac.kok, new Ogrenci(173, "Demir Demirkan"));

		System.out.println("Numara sirasiyla:");
		agac.inorder(agac.kok);       // 93 101 105 123 133 143 173

		agac.araVeYaz(101);           // Murat Aktas
		agac.araVeYaz(45);            // kayit yok

		agac.kok = agac.sil(agac.kok, 123);   // kok, iki cocuklu: yerine ardil 133 gelir
		System.out.println("123 silindi, yeni kok: " + agac.kok.ogr.ogrenciNo + " " + agac.kok.ogr.adSoyad);
		agac.inorder(agac.kok);       // 93 101 105 133 143 173
	}
}
