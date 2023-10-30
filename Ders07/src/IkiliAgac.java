
public class IkiliAgac {

	private Dugum<Integer> kok;

	public void ikiliAgacOlustur() {
		Dugum<Integer> birinci = new Dugum<Integer>(9);   // Birinci düğümü oluştur (kök düğüm)
		Dugum<Integer> ikinci = new Dugum<Integer>(2);    // İkinci düğümü oluştur
		Dugum<Integer> ucuncu = new Dugum<Integer>(3);    // Üçüncü düğümü oluştur
		Dugum<Integer> dorduncu = new Dugum<Integer>(4);  // Dördüncü düğümü oluştur

		kok = birinci;                 // Kök düğümü belirle
		birinci.sol = ikinci;          // Birinci düğümün sol alt düğümünü ayarla
		birinci.sag = ucuncu;          // Birinci düğümün sağ alt düğümünü ayarla
		ikinci.sol = dorduncu;         // İkinci düğümün sol alt düğümünü ayarla
	}


	public void kokBastaDolas(Dugum<Integer> kok) {
		if (kok == null) {
			return;  // Eğer düğüm null ise, işlem sonlandırılır (temel durum)
		}

		System.out.print(kok.veri + " ");  // Kök düğümün verisini ekrana yazdır
		kokBastaDolas(kok.sol);  // Sol alt ağacı kökten başlayarak dolaş
		kokBastaDolas(kok.sag);  // Sağ alt ağacı kökten başlayarak dolaş
	}

	public void kokOrtadaDolas(Dugum<Integer> kok) {
		if (kok == null) {
			return;  // Eğer düğüm null ise, işlem sonlandırılır (temel durum)
		}

		kokOrtadaDolas(kok.sol);   // Sol alt ağacı kökten başlayarak dolaş
		System.out.print(kok.veri + " ");  // Kök düğümün verisini ekrana yazdır
		kokOrtadaDolas(kok.sag);   // Sağ alt ağacı kökten başlayarak dolaş
	}

	public void kokSondaDolas(Dugum<Integer> kok) {
		if (kok == null) {
			return;  // Eğer düğüm null ise, işlem sonlandırılır (temel durum)
		}

		kokSondaDolas(kok.sol);   // Sol alt ağacı kökten başlayarak dolaş
		kokSondaDolas(kok.sag);   // Sağ alt ağacı kökten başlayarak dolaş
		System.out.print(kok.veri + " ");  // Kök düğümün verisini ekrana yazdır
	}

	public void seviyeSiraliDolas(Dugum<Integer> kok) {
		int derinlik = agacDerinligi(kok);
		for (int seviye = 0; seviye <= derinlik; seviye++) {
			seviyeSiraliDolas(kok, seviye);
		}
	}

	private void seviyeSiraliDolas(Dugum<Integer> dugum, int hedefSeviye) {
    if (dugum == null) {
        return; // Düğüm boşsa işlemi sonlandır
    }
    if (hedefSeviye == 0) {
        System.out.print(dugum.veri + " "); // Hedef seviyeye ulaşıldığında düğüm değerini yazdır
    } else {
        seviyeSiraliDolas(dugum.sol, hedefSeviye - 1); // Sol ağacı hedef seviyeye kadar dolaş
        seviyeSiraliDolas(dugum.sag, hedefSeviye - 1); // Sağ ağacı hedef seviyeye kadar dolaş
    }
}

	private int agacDerinligi(Dugum<Integer> dugum) {
		if (dugum == null) {
			return 0; // Boş bir ağacın derinliği 0'dır
		} else {
			int solDerinlik = agacDerinligi(dugum.sol); // Sol alt ağacın derinliği hesaplanır
			int sagDerinlik = agacDerinligi(dugum.sag); // Sağ alt ağacın derinliği hesaplanır

			return 1 + Math.max(solDerinlik, sagDerinlik); // Sol ve sağ alt ağaçların derinliği karşılaştırılıp, maksimum alınır
		}
	}


	public static void main(String[] args) {

		IkiliAgac ia = new IkiliAgac();
		ia.ikiliAgacOlustur();

		System.out.print("Kök başta: ");
		ia.kokBastaDolas(ia.kok);
		System.out.println();

		System.out.print("Kök ortada: ");
		ia.kokOrtadaDolas(ia.kok);
		System.out.println();

		System.out.print("Kök sonda: ");
		ia.kokSondaDolas(ia.kok);
		System.out.println();
		
		System.out.print("Seviye sıralı: ");
		ia.seviyeSiraliDolas(ia.kok);
		System.out.println();

		System.out.println("Ağaç derinliği: " + ia.agacDerinligi(ia.kok));
	}
}
