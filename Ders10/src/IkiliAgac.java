
public class IkiliAgac<E extends Comparable<E>> {
	IkiliAgacDugum<E> kok;

	public IkiliAgac() {
		kok = null;
	}

	public void ekle(E deger) {
		kok = ekle(kok, deger);
	}

	IkiliAgacDugum<E> ekle(IkiliAgacDugum<E> kok, E veri) {
		if (kok == null) {
			kok = new IkiliAgacDugum<>(veri);
			System.out.println("Ağaca eklendi: " + kok.veri);  // Düğüm ağaca eklendiğinde bir mesaj yazdır
			return kok;
		}
		if (veri.compareTo(kok.veri) < 0) {
			kok.solCocuk = ekle(kok.solCocuk, veri);  // Eklenecek veri, düğümün verisinden küçükse sol alt ağaca ekleme yap
		} else if (veri.compareTo(kok.veri) > 0) {
			kok.sagCocuk = ekle(kok.sagCocuk, veri);  // Eklenecek veri, düğümün verisinden büyükse sağ alt ağaca ekleme yap
		}
		return kok;
	}

	public void sil(E deger) {
		kok = sil(kok, deger);
	}

	IkiliAgacDugum<E> sil(IkiliAgacDugum<E> kok, E veri) {
		if (kok == null) {
			System.out.println("Ağaçtan silinemedi: " + veri);  // Düğüm bulunamadığında bir hata mesajı yazdır
			return kok;
		}
		if (veri.compareTo(kok.veri) < 0) {
			kok.solCocuk = sil(kok.solCocuk, veri);  // Silinecek veri, düğümün verisinden küçükse sol alt ağaçta silme işlemine devam et
		} else if (veri.compareTo(kok.veri) > 0) {
			kok.sagCocuk = sil(kok.sagCocuk, veri);  // Silinecek veri, düğümün verisinden büyükse sağ alt ağaçta silme işlemine devam et
		} else if (kok.solCocuk != null && kok.sagCocuk != null) {
			kok.veri = minDeger(kok.sagCocuk).veri;   // Sağ alt ağacın en küçük değerini al ve düğümün verisine ata
			kok.sagCocuk = sil(kok.sagCocuk, kok.veri);  // Sağ alt ağaçtan en küçük değeri sildikten sonra işlemi devam ettir
			System.out.println("Ağaçtan silindi: " + veri);  // Düğümün silindiğine dair bir mesaj yazdır
		} else if (kok.solCocuk == null) {
			System.out.println("Ağaçtan silindi: " + kok.veri);  // Düğümün silindiğine dair bir mesaj yazdır
			return kok.sagCocuk;  // Eğer sol çocuk yoksa, sağ çocuğu geri döndür ve düğümü sil
		} else if (kok.sagCocuk == null) {
			System.out.println("Ağaçtan silindi: " + kok.veri);  // Düğümün silindiğine dair bir mesaj yazdır
			return kok.solCocuk;  // Eğer sağ çocuk yoksa, sol çocuğu geri döndür ve düğümü sil
		}
		return kok;
	}


	IkiliAgacDugum<E> minDeger(IkiliAgacDugum<E> dugum) {
		while (dugum.solCocuk != null) {
			dugum = dugum.solCocuk;  // Düğümü sol çocuğa taşı ve en küçük değeri ara
		}
		return dugum;  // En küçük değeri döndür
	}

	IkiliAgacDugum<E> maxDeger(IkiliAgacDugum<E> dugum) {
		while (dugum.sagCocuk != null) {
			dugum = dugum.sagCocuk;  // Düğümü sol çocuğa taşı ve en küçük değeri ara
		}
		return dugum;  // En küçük değeri döndür
	}

	public boolean ara(E deger) {
		return ara(kok, deger);
	}

	boolean ara(IkiliAgacDugum<E> kok, E veri) {
		if (kok == null) {
			System.out.println("Ağaçta " + veri + " bulunamadı.");  // Düğüm bulunamadığında bir mesaj yazdır
			return false;
		}
		if (veri == kok.veri) {
			System.out.println("Ağaçta " + kok.veri + " bulundu.");  // Aranan veri düğümün verisiyle eşleştiğinde bir mesaj yazdır
			return true;
		} else if (veri.compareTo(kok.veri) < 0) {
			return ara(kok.solCocuk, veri);  // Aranan veri, düğümün verisinden küçükse sol alt ağaçta arama yap
		} else {
			return ara(kok.sagCocuk, veri);  // Aranan veri, düğümün verisinden büyükse sağ alt ağaçta arama yap
		}
	}


	void kokOrtadaDolas(IkiliAgacDugum<E> kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			System.out.print(kok.veri + " ");  // Düğümün verisini yazdır
			kokOrtadaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}


	void kokBastaDolas(IkiliAgacDugum<E> kok) {
		if (kok != null) {
			System.out.print(kok.veri + " ");  // Düğümün verisini yazdır
			kokBastaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}

	void kokSondaDolas(IkiliAgacDugum<E> kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
			System.out.print(kok.veri + " ");  // Düğümün verisini yazdır
		}
	}

	public static void main(String[] args) {
		IkiliAgac<Integer> agac = new IkiliAgac<>();

		agac.ekle(50);   // Ağaca düğümleri ekle
		agac.ekle(30);
		agac.ekle(20);
		agac.ekle(40);
		agac.ekle(70);
		agac.ekle(60);
		agac.ekle(80);

		System.out.println("Minimum Değer: " + agac.minDeger(agac.kok).veri);
		System.out.println("Maksimum Değer: " + agac.maxDeger(agac.kok).veri);

		System.out.print("Kök Başta Dolaşma: ");
		agac.kokBastaDolas(agac.kok);  // Kökten başlayarak ağacı dolaş
		System.out.println();

		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		System.out.print("Kök Sonda Dolaşma: ");
		agac.kokSondaDolas(agac.kok);  // Kökten başlayarak ağacı dolaş
		System.out.println();

		agac.ara(40);  // Ağaçta bir değeri ara
		agac.ara(45);  // Ağaçta 45 bulunamadı.

		agac.sil(80);  // Ağaçtan bir değeri sil

		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		agac.sil(70);

		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		agac.sil(60);

		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		agac.sil(50);

		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();

		agac.sil(30);
		//	agac.sil(20);
		//	agac.sil(40);
		agac.sil(22);  //Ağaçtan silinemedi: 22


		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kök ortada başlayarak ağacı dolaş
		System.out.println();
	}

}
