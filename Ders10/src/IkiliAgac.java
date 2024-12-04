
class IkiliAgacDugum<E extends Comparable<E>> {
	
	E veri;                 // Dugumun icerdigi veri
	IkiliAgacDugum<E> solCocuk;   // Sol cocuk dugumun referansi
	IkiliAgacDugum<E> sagCocuk;   // Sag cocuk dugumun referansi
	
	public IkiliAgacDugum(E veri) {
		this.veri = veri;       // Dugumun verisini belirle
		this.solCocuk = null;   // Sol cocuk baslangicta bos
		this.sagCocuk = null;   // Sag cocuk baslangicta bos
	}
}

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
			System.out.println("Agaca eklendi: " + kok.veri);  // Dugum agaca eklendiginde bir mesaj yazdir
			return kok;
		}
		if (veri.compareTo(kok.veri) < 0) {
			kok.solCocuk = ekle(kok.solCocuk, veri);  // Eklenecek veri, dugumun verisinden kucukse sol alt agaca ekle
		} else if (veri.compareTo(kok.veri) > 0) {
			kok.sagCocuk = ekle(kok.sagCocuk, veri);  // Eklenecek veri, dugumun verisinden buyukse sag alt agaca ekle
		}
		return kok;
	}

	public void sil(E deger) {
		kok = sil(kok, deger, true);
	}

    // Verilen dugumu agactan silen metod
    IkiliAgacDugum<E> sil(IkiliAgacDugum<E> kok, E veri, boolean yazdir) {
        if (kok == null) {
            System.out.println("Agactan silinemedi: " + veri);  // Dugum bulunamadiginda bir hata mesaji yazdir
            return kok;
        }
        if (veri.compareTo(kok.veri) < 0) {
            kok.solCocuk = sil(kok.solCocuk, veri, true);  // Silinecek veri, dugumun verisinden kucukse sol alt agacta silme islemine devam et
        } else if (veri.compareTo(kok.veri) > 0) {
            kok.sagCocuk = sil(kok.sagCocuk, veri, true);  // Silinecek veri, dugumun verisinden buyukse sag alt agacta silme islemine devam et
        } else if (kok.solCocuk != null && kok.sagCocuk != null) {
            kok.veri = minDeger(kok.sagCocuk).veri;   // Sag alt agacin en kucuk degerini al ve dugumun verisine ata
            System.out.println("Agactan silindi: " + veri);  // Dugumun silindigine dair bir mesaj yazdir
            kok.sagCocuk = sil(kok.sagCocuk, kok.veri, false);  // Sag alt agactan en kucuk degeri sildikten sonra islemi devam ettir
        } else if (kok.solCocuk == null) {
            if (yazdir)
                System.out.println("Agactan silindi: " + kok.veri);  // Dugumun silindigine dair bir mesaj yazdir
            return kok.sagCocuk;  // Eger sol cocuk yoksa, sag cocugu geri dondur ve dugumu sil
        } else if (kok.sagCocuk == null) {
            if (yazdir)
                System.out.println("Agactan silindi: " + kok.veri);  // Dugumun silindigine dair bir mesaj yazdir
            return kok.solCocuk;  // Eger sag cocuk yoksa, sol cocugu geri dondur ve dugumu sil
        }
        return kok;  // Silme islemi tamamlandiktan sonra kok dugumu geri dondur
    }

    // Verilen dugumun alt agacindaki en kucuk degeri bulan metod
    IkiliAgacDugum<E> minDeger(IkiliAgacDugum<E> dugum) {
        while (dugum.solCocuk != null) {
            dugum = dugum.solCocuk;  // Dugumu sol cocuga tasi ve en kucuk degeri ara
        }
        return dugum;  // En kucuk degeri dondur
    }

    // Verilen dugumun alt agacindaki en buyuk degeri bulan metod
    IkiliAgacDugum<E> maxDeger(IkiliAgacDugum<E> dugum) {
        while (dugum.sagCocuk != null) {
            dugum = dugum.sagCocuk;  // Dugumu sag cocuga tasi ve en buyuk degeri ara
        }
        return dugum;  // En buyuk degeri dondur
    }

	public boolean ara(E deger) {
		return ara(kok, deger);
	}

	boolean ara(IkiliAgacDugum<E> kok, E veri) {
		if (kok == null) {
			System.out.println("Agacta " + veri + " bulunamadi.");  // Dugum bulunamadiginda bir mesaj yazdir
			return false;
		}
		if (veri == kok.veri) {
			System.out.println("Agacta " + kok.veri + " bulundu.");  // Aranan veri dugumun verisiyle eslestiginde bir mesaj yazdir
			return true;
		} else if (veri.compareTo(kok.veri) < 0) {
			return ara(kok.solCocuk, veri);  // Aranan veri, dugumun verisinden kucukse sol alt agacta arama yap
		} else {
			return ara(kok.sagCocuk, veri);  // Aranan veri, dugumun verisinden buyukse sag alt agacta arama yap
		}
	}

	void kokOrtadaDolas(IkiliAgacDugum<E> kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			System.out.print(kok.veri + " ");  // Dugumun verisini yazdir
			kokOrtadaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
		}
	}

	void kokBastaDolas(IkiliAgacDugum<E> kok) {
		if (kok != null) {
			System.out.print(kok.veri + " ");  // Dugumun verisini yazdir
			kokBastaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
		}
	}
	
	void kokSondaDolas(IkiliAgacDugum<E> kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
			System.out.print(kok.veri + " ");  // Dugumun verisini yazdir
		}
	}

	public static void main(String[] args) {
		IkiliAgac<Integer> agac = new IkiliAgac<>();

		agac.ekle(50);   // Agaca dugumleri ekle
		agac.ekle(30);
		agac.ekle(20);
		agac.ekle(40);
		agac.ekle(70);
		agac.ekle(60);
		agac.ekle(80);

		System.out.println("Minimum Deger: " + agac.minDeger(agac.kok).veri);
		System.out.println("Maksimum Deger: " + agac.maxDeger(agac.kok).veri);

		System.out.print("Kok Basta Dolasma: ");
		agac.kokBastaDolas(agac.kok);  // Kok Basta Dolasma: 50 30 20 40 70 60 80
		System.out.println();

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok Ortada Dolasma: 20 30 40 50 60 70 80
		System.out.println();

		System.out.print("Kok Sonda Dolasma: ");
		agac.kokSondaDolas(agac.kok);  // Kok Sonda Dolasma: 20 40 30 60 80 70 50
		System.out.println();

		agac.ara(40);  // Agacta 40 bulundu.
		agac.ara(45);  // Agacta 45 bulunamadi.

		agac.sil(80);  // Agactan silindi: 80

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok Ortada Dolasma: 20 30 40 50 60 70
		System.out.println();

		agac.sil(70);

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok Ortada Dolasma: 20 30 40 50 60
		System.out.println();

		agac.sil(60);

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok Ortada Dolasma: 20 30 40
		System.out.println();

		agac.sil(50);

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok Ortada Dolasma: 20 30 40
		System.out.println();

		agac.sil(30); // Agactan silindi: 30
		agac.sil(20); // Agactan silindi: 20
		agac.sil(40); // Agactan silindi: 40
		agac.sil(20); // Agactan silinemedi: 20

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolas(agac.kok);  // Kok Ortada Dolasma: 
		System.out.println();
	}

}
