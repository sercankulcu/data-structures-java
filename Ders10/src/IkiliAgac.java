
public class IkiliAgac {
	IkiliAgacDugum kok;

	public IkiliAgac() {
		kok = null;
	}

	public void ekle(int deger) {
		kok = ekle(kok, deger);
	}

	IkiliAgacDugum ekle(IkiliAgacDugum kok, int veri) {
		if (kok == null) {
			kok = new IkiliAgacDugum(veri);
			System.out.println("Ağaca eklendi: " + kok.veri);  // Düğüm ağaca eklendiğinde bir mesaj yazdır
			return kok;
		}

		if (veri < kok.veri) {
			kok.solCocuk = ekle(kok.solCocuk, veri);  // Eklenecek veri, düğümün verisinden küçükse sol alt ağaca ekleme yap
		} else if (veri > kok.veri) {
			kok.sagCocuk = ekle(kok.sagCocuk, veri);  // Eklenecek veri, düğümün verisinden büyükse sağ alt ağaca ekleme yap
		}

		return kok;
	}


	IkiliAgacDugum sil(IkiliAgacDugum kok, int veri) {
		if (kok == null) {
			System.out.println("Ağaçtan silinemedi: " + veri);  // Düğüm bulunamadığında bir hata mesajı yazdır
			return kok;
		}

		if (veri < kok.veri) {
			kok.solCocuk = sil(kok.solCocuk, veri);  // Silinecek veri, düğümün verisinden küçükse sol alt ağaçta silme işlemine devam et
		} else if (veri > kok.veri) {
			kok.sagCocuk = sil(kok.sagCocuk, veri);  // Silinecek veri, düğümün verisinden büyükse sağ alt ağaçta silme işlemine devam et
		} else {
			if (kok.solCocuk == null) {
				return kok.sagCocuk;  // Eğer sol çocuk yoksa, sağ çocuğu geri döndür ve düğümü sil
			} else if (kok.sagCocuk == null) {
				return kok.solCocuk;  // Eğer sağ çocuk yoksa, sol çocuğu geri döndür ve düğümü sil
			}

			kok.veri = minDeger(kok.sagCocuk);   // Sağ alt ağacın en küçük değerini al ve düğümün verisine ata
			kok.sagCocuk = sil(kok.sagCocuk, kok.veri);  // Sağ alt ağaçtan en küçük değeri sildikten sonra işlemi devam ettir
			System.out.println("Ağaçtan silindi: " + veri);  // Düğümün silindiğine dair bir mesaj yazdır
		}

		return kok;
	}


	int minDeger(IkiliAgacDugum dugum) {
		int min = dugum.veri;  // Başlangıçta en küçük değeri, düğümün kendi verisi olarak kabul et
		while (dugum.solCocuk != null) {
			min = dugum.solCocuk.veri;  // Düğümün sol çocuğunun verisini en küçük değer olarak kabul et
			dugum = dugum.solCocuk;  // Düğümü sol çocuğa taşı ve en küçük değeri ara
		}
		return min;  // En küçük değeri döndür
	}


	boolean ara(IkiliAgacDugum kok, int veri) {
		if (kok == null) {
			System.out.println("Ağaçta " + veri + " bulunamadı.");  // Düğüm bulunamadığında bir mesaj yazdır
			return false;
		}

		if (veri == kok.veri) {
			System.out.println("Ağaçta " + kok.veri + " bulundu.");  // Aranan veri düğümün verisiyle eşleştiğinde bir mesaj yazdır
			return true;
		} else if (veri < kok.veri) {
			return ara(kok.solCocuk, veri);  // Aranan veri, düğümün verisinden küçükse sol alt ağaçta arama yap
		} else {
			return ara(kok.sagCocuk, veri);  // Aranan veri, düğümün verisinden büyükse sağ alt ağaçta arama yap
		}
	}


	void kokOrtadaDolas(IkiliAgacDugum kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			System.out.print(kok.veri + " ");  // Düğümün verisini yazdır
			kokOrtadaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}


	void kokBastaDolas(IkiliAgacDugum kok) {
		if (kok != null) {
			System.out.print(kok.veri + " ");  // Düğümün verisini yazdır
			kokBastaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}

	void kokSondaDolas(IkiliAgacDugum kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
			System.out.print(kok.veri + " ");  // Düğümün verisini yazdır
		}
	}

	public static void main(String[] args) {
		IkiliAgac agac = new IkiliAgac();

		agac.ekle(50);   // Ağaca düğümleri ekle
		agac.ekle(30);
		agac.ekle(20);
		agac.ekle(40);
		agac.ekle(70);
		agac.ekle(60);
		agac.ekle(80);

		System.out.print("Kök Başta Dolaşma: ");
		agac.kokBastaDolas(agac.kok);  // Kökten başlayarak ağacı dolaş
		System.out.println();

		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		System.out.print("Kök Sonda Dolaşma: ");
		agac.kokSondaDolas(agac.kok);  // Kökten başlayarak ağacı dolaş
		System.out.println();

		agac.ara(agac.kok, 40);  // Ağaçta bir değeri ara
		agac.ara(agac.kok, 45);  // Ağaçta 45 bulunamadı.

		agac.sil(agac.kok, 30);  // Ağaçtan bir değeri sil
		agac.sil(agac.kok, 22);  //Ağaçtan silinemedi: 22


		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();
	}

}
