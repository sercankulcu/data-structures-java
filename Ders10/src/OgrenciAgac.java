

public class OgrenciAgac {
	OgrenciDugum kok;

	public OgrenciAgac() {
		kok = null;
	}

	public void ekle(Ogrenci o) {
		kok = ekle(kok, o);
	}

	OgrenciDugum ekle(OgrenciDugum kok, Ogrenci ogr) {
		if (kok == null) {
			kok = new OgrenciDugum(ogr);
			System.out.println("Ağaca eklendi: " + kok.ogr.adSoyad);  // Düğüm ağaca eklendiğinde bir mesaj yazdır
			return kok;
		}
		if (kok.ogr.ogrenciNo > ogr.ogrenciNo) {
			kok.solCocuk = ekle(kok.solCocuk, ogr);  // Eklenecek veri, düğümün verisinden küçükse sol alt ağaca ekleme yap
		} else if (kok.ogr.ogrenciNo < ogr.ogrenciNo) {
			kok.sagCocuk = ekle(kok.sagCocuk, ogr);  // Eklenecek veri, düğümün verisinden büyükse sağ alt ağaca ekleme yap
		}
		return kok;
	}


	OgrenciDugum sil(OgrenciDugum kok, int numara) {
		if (kok == null) {
			System.out.println("Ağaçtan silinemedi: " + numara);  // Düğüm bulunamadığında bir hata mesajı yazdır
			return kok;
		}
		if (kok.ogr.ogrenciNo > numara) {
			kok.solCocuk = sil(kok.solCocuk, numara);  // Silinecek veri, düğümün verisinden küçükse sol alt ağaçta silme işlemine devam et
		} else if (kok.ogr.ogrenciNo < numara) {
			kok.sagCocuk = sil(kok.sagCocuk, numara);  // Silinecek veri, düğümün verisinden büyükse sağ alt ağaçta silme işlemine devam et
		} else if (kok.solCocuk != null && kok.sagCocuk != null) {
			Ogrenci gecici = minDeger(kok.sagCocuk);
			kok.ogr.adSoyad = gecici.adSoyad;   // Sağ alt ağacın en küçük değerini al ve düğümün verisine ata
			kok.ogr.ogrenciNo = gecici.ogrenciNo;
			kok.sagCocuk = sil(kok.sagCocuk, kok.ogr.ogrenciNo);  // Sağ alt ağaçtan en küçük değeri sildikten sonra işlemi devam ettir
			System.out.println("Ağaçtan silindi: " + numara);  // Düğümün silindiğine dair bir mesaj yazdır
		} else if (kok.solCocuk == null) {
			System.out.println("Ağaçtan silindi: " + kok.ogr.ogrenciNo);  // Düğümün silindiğine dair bir mesaj yazdır
			return kok.sagCocuk;  // Eğer sol çocuk yoksa, sağ çocuğu geri döndür ve düğümü sil
		} else if (kok.sagCocuk == null) {
			System.out.println("Ağaçtan silindi: " + kok.ogr.ogrenciNo);  // Düğümün silindiğine dair bir mesaj yazdır
			return kok.solCocuk;  // Eğer sağ çocuk yoksa, sol çocuğu geri döndür ve düğümü sil
		}
		return kok;
	}


	Ogrenci minDeger(OgrenciDugum dugum) {
		while (dugum.solCocuk != null) {
			dugum = dugum.solCocuk;  // Düğümü sol çocuğa taşı ve en küçük değeri ara
		}
		return dugum.ogr;  // En küçük değeri döndür
	}

	boolean ara(OgrenciDugum kok, int numara) {
		if (kok == null) {
			System.out.println("Ağaçta " + numara + " bulunamadı.");  // Düğüm bulunamadığında bir mesaj yazdır
			return false;
		}
		if (numara == kok.ogr.ogrenciNo) {
			System.out.println("Ağaçta " + kok.ogr.ogrenciNo + " bulundu.");  // Aranan veri düğümün verisiyle eşleştiğinde bir mesaj yazdır
			return true;
		} else if (kok.ogr.ogrenciNo > numara) {
			return ara(kok.solCocuk, numara);  // Aranan veri, düğümün verisinden küçükse sol alt ağaçta arama yap
		} else {
			return ara(kok.sagCocuk, numara);  // Aranan veri, düğümün verisinden büyükse sağ alt ağaçta arama yap
		}
	}


	void kokOrtadaDolas(OgrenciDugum kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			System.out.print(kok.ogr.ogrenciNo + " ");  // Düğümün verisini yazdır
			kokOrtadaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}


	void kokBastaDolas(OgrenciDugum kok) {
		if (kok != null) {
			System.out.print(kok.ogr.ogrenciNo + " ");  // Düğümün verisini yazdır
			kokBastaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}

	void kokSondaDolas(OgrenciDugum kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
			System.out.print(kok.ogr.ogrenciNo + " ");  // Düğümün verisini yazdır
		}
	}

	public static void main(String[] args) {
		OgrenciAgac agac = new OgrenciAgac();

		agac.ekle(new Ogrenci(123, "Ali Aydın"));   // Ağaca düğümleri ekle
		agac.ekle(new Ogrenci(101, "Murat Aktaş"));
		agac.ekle(new Ogrenci(133, "Ayşe Konak"));
		agac.ekle(new Ogrenci(143, "Dilara Türk"));
		agac.ekle(new Ogrenci(93, "Mehmet Bey"));
		agac.ekle(new Ogrenci(105, "Aslı Hanım"));
		agac.ekle(new Ogrenci(173, "Demir Demirkan"));

		System.out.print("Kök Başta Dolaşma: ");
		agac.kokBastaDolas(agac.kok);  // Kökten başlayarak ağacı dolaş
		System.out.println();

		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		System.out.print("Kök Sonda Dolaşma: ");
		agac.kokSondaDolas(agac.kok);  // Kökten başlayarak ağacı dolaş
		System.out.println();

		agac.ara(agac.kok, 101);  // Ağaçta bir değeri ara
		agac.ara(agac.kok, 45);  // Ağaçta 45 bulunamadı.

		agac.kok = agac.sil(agac.kok, 101);  // Ağaçtan bir değeri sil
		agac.kok = agac.sil(agac.kok, 173);
		agac.kok = agac.sil(agac.kok, 123);
		agac.kok = agac.sil(agac.kok, 133);

		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		agac.kok = agac.sil(agac.kok, 105);
		agac.kok = agac.sil(agac.kok, 93);


		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		agac.kok = agac.sil(agac.kok, 143);
		agac.kok = agac.sil(agac.kok, 22);  //Ağaçtan silinemedi: 22


		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();
	}

}
