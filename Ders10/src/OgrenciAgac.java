class Ogrenci {
	
	int ogrenciNo;
	String adSoyad;

	// Constructor, ogrenci bilgilerini alarak nesne olusturur
	public Ogrenci(int ogrenciNo, String adSoyad) {
		this.ogrenciNo = ogrenciNo;
		this.adSoyad = adSoyad;
	}
}

class OgrenciDugum {
	
	Ogrenci ogr;                // Dugumun icerigindeki veri
	OgrenciDugum solCocuk;      // Sol cocuk dugum referansi
	OgrenciDugum sagCocuk;      // Sag cocuk dugum referansi

	// Constructor, bir ogrenci nesnesi ile dugum olusturur
	public OgrenciDugum(Ogrenci ogr) {
		this.ogr = ogr;         // Dugumun verisi
		this.solCocuk = null;   // Sol cocuk baslangicta bos
		this.sagCocuk = null;   // Sag cocuk baslangicta bos
	}
}

public class OgrenciAgac {
	OgrenciDugum kok;           // Agacin kok dugumu

	// Constructor, bos bir agac olusturur
	public OgrenciAgac() {
		kok = null;
	}

	// Agaca ogrenci ekleme metodu
	public void ekle(Ogrenci o) {
		kok = ekle(kok, o);
	}

	// Recursive ekleme islemi
	OgrenciDugum ekle(OgrenciDugum kok, Ogrenci ogr) {
		if (kok == null) {
			kok = new OgrenciDugum(ogr); // Yeni dugum olusturulup agaca eklenir
			System.out.println("Agaca eklendi: " + kok.ogr.ogrenciNo + " " + kok.ogr.adSoyad);
			return kok;
		}
		if (kok.ogr.ogrenciNo > ogr.ogrenciNo) {
			kok.solCocuk = ekle(kok.solCocuk, ogr);  // Sol alt agaca ekleme
		} else if (kok.ogr.ogrenciNo < ogr.ogrenciNo) {
			kok.sagCocuk = ekle(kok.sagCocuk, ogr);  // Sag alt agaca ekleme
		}
		return kok;
	}

	// Dugumu silme islemi
		OgrenciDugum sil(OgrenciDugum kok, int numara) {
			if (kok == null) {
				System.out.println("Agactan silinemedi: " + numara);  // Dugum bulunamadiginda mesaj
				return kok;
			}
			if (kok.ogr.ogrenciNo > numara) {
				kok.solCocuk = sil(kok.solCocuk, numara);  // Sol alt agacta silme islemi
			} else if (kok.ogr.ogrenciNo < numara) {
				kok.sagCocuk = sil(kok.sagCocuk, numara);  // Sag alt agacta silme islemi
			} else if (kok.solCocuk != null && kok.sagCocuk != null) {
				Ogrenci gecici = minDeger(kok.sagCocuk);  // Sag alt agacin en kucuk degeri
				kok.ogr.adSoyad = gecici.adSoyad;   // Dugumun verisini guncelle
				kok.ogr.ogrenciNo = gecici.ogrenciNo;
				kok.sagCocuk = sil(kok.sagCocuk, kok.ogr.ogrenciNo);  // Sag alt agactan en kucuk dugumu sil
				System.out.println("Agactan silindi: " + numara);
			} else if (kok.solCocuk == null) {
				System.out.println("Agactan silindi sol null: " + kok.ogr.ogrenciNo);
				return kok.sagCocuk;  // Sol cocuk yoksa sag cocugu geri dondur
			} else if (kok.sagCocuk == null) {
				System.out.println("Agactan silindi sag null: " + kok.ogr.ogrenciNo);
				return kok.solCocuk;  // Sag cocuk yoksa sol cocugu geri dondur
			}
			return kok;
		}

	// Agactaki en kucuk degeri bulma
	Ogrenci minDeger(OgrenciDugum dugum) {
		while (dugum.solCocuk != null) {
			dugum = dugum.solCocuk;  // En kucuk degeri bulmak icin sol cocuklara gider
		}
		return dugum.ogr;
	}

	// Agacta belirli bir ogrenciyi arama
	boolean ara(OgrenciDugum kok, int numara) {
		if (kok == null) {
			System.out.println("Agacta " + numara + " bulunamadi.");
			return false;
		}
		if (numara == kok.ogr.ogrenciNo) {
			System.out.println("Agacta " + kok.ogr.ogrenciNo + " bulundu.");
			return true;
		} else if (kok.ogr.ogrenciNo > numara) {
			return ara(kok.solCocuk, numara);  // Sol alt agacta arama
		} else {
			return ara(kok.sagCocuk, numara);  // Sag alt agacta arama
		}
	}

	// Kok ortada dolasma (in-order traversal)
	void kokOrtadaDolas(OgrenciDugum kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			System.out.print(kok.ogr.ogrenciNo + " ");  // Dugumun verisini yazdir
			kokOrtadaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
		}
	}

	void kokBastaDolas(OgrenciDugum kok) {
		if (kok != null) {
			System.out.print(kok.ogr.ogrenciNo + " ");  // Dugumun verisini yazdir
			kokBastaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
		}
	}

	void kokSondaDolas(OgrenciDugum kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
			System.out.print(kok.ogr.ogrenciNo + " ");  // Dugumun verisini yazdir
		}
	}

	public static void main(String[] args) {
		OgrenciAgac agac = new OgrenciAgac();

		agac.ekle(new Ogrenci(123, "Ali Aydin"));   // Agaca dugumleri ekle
		agac.ekle(new Ogrenci(101, "Murat Aktas"));
		agac.ekle(new Ogrenci(133, "Ayse Konak"));
		agac.ekle(new Ogrenci(143, "Dilara Turk"));
		agac.ekle(new Ogrenci(93, "Mehmet Bey"));
		agac.ekle(new Ogrenci(105, "Asli Hanim"));
		agac.ekle(new Ogrenci(173, "Demir Demirkan"));

		System.out.print("Kok Basta Dolasma: ");
		agac.kokBastaDolas(agac.kok);  // Kokten baslayarak agaci dolas
		System.out.println();

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok ortada baslayarak agaci dolas
		System.out.println();

		System.out.print("Kok Sonda Dolasma: ");
		agac.kokSondaDolas(agac.kok);  // Kokten baslayarak agaci dolas
		System.out.println();

		agac.ara(agac.kok, 101);  // Agacta bir degeri ara
		agac.ara(agac.kok, 45);  // Agacta 45 bulunamadi.

		agac.kok = agac.sil(agac.kok, 101);  // Agactan bir degeri sil
		agac.kokOrtadaDolas(agac.kok);  // Kok ortada baslayarak agaci dolas
		System.out.println();
		agac.kok = agac.sil(agac.kok, 173);
		agac.kokOrtadaDolas(agac.kok);  // Kok ortada baslayarak agaci dolas
		System.out.println();
		agac.kok = agac.sil(agac.kok, 123);
		agac.kokOrtadaDolas(agac.kok);  // Kok ortada baslayarak agaci dolas
		System.out.println();
		agac.kok = agac.sil(agac.kok, 133);

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok ortada baslayarak agaci dolas
		System.out.println();

		agac.kok = agac.sil(agac.kok, 105);
		agac.kok = agac.sil(agac.kok, 93);


		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok ortada baslayarak agaci dolas
		System.out.println();

		agac.kok = agac.sil(agac.kok, 143);
		agac.kok = agac.sil(agac.kok, 22);  //Agactan silinemedi: 22


		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok ortada baslayarak agaci dolas
		System.out.println();
	}
}
