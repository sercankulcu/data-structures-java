
// AVL Agaci dugum sinifi, agac yapisinin temel yapi taslarini olusturur.
class AvlAgacDugum {

	// Agactaki dugumun degerini temsil eder
	int anahtar;
	// Dugumun sol cocugunu temsil eden referans
	AvlAgacDugum solCocuk;
	// Dugumun sag cocugunu temsil eden referans
	AvlAgacDugum sagCocuk;
	// AVL agacinda kullanilan yukseklik bilgisini saklar
	int yukseklik;

	// Yapici metod: Belirtilen anahtar degeriyle bir AVL agaci dugumu olusturur
	public AvlAgacDugum(int anahtar) {
		this.anahtar = anahtar;
		this.sagCocuk = null;
		this.solCocuk = null;
		this.yukseklik = 0;
	}
}

public class AvlAgac {

	AvlAgacDugum kok;

	public void ekle(int anahtar) {
		kok = ekle(anahtar, kok);
	}

	//AVL agacina yeni bir dugum ekler ve agaci dengeler.
	AvlAgacDugum ekle(int anahtar, AvlAgacDugum dugum) {
		// Mevcut pozisyonda hic dugum yoksa, yeni dugumu bu pozisyona ekler.
		if (dugum == null) {
			dugum = new AvlAgacDugum(anahtar);
			System.out.println("Agaca eklendi: " + anahtar); 
		}

		// Aksi takdirde, anahtara gore agacta sol veya sag yonde ilerler.
		else if (anahtar < dugum.anahtar) {
			dugum.solCocuk = ekle(anahtar, dugum.solCocuk);
		} else if (anahtar > dugum.anahtar) {
			dugum.sagCocuk = ekle(anahtar, dugum.sagCocuk);
		} else {
			System.out.println("Agacta bulundugu icin eklenemedi: " + anahtar); // Anahtar zaten agacta bulunuyor
			return null;
		}

		// Yukseklik bilgisini gunceller.
		yuksekligiGuncelle(dugum);

		// Agaci dengelemek icin dengele() fonksiyonunu cagirir.
		return dengele(dugum);
	}

	public void sil(int anahtar) {
		kok = sil(anahtar, kok);
		if (kok != null) {
			System.out.println("Agacta silindi: " + anahtar);
		}
	}

	//AVL agacindan bir dugumu siler.
	AvlAgacDugum sil(int anahtar, AvlAgacDugum dugum) {
		// Mevcut pozisyonda hic dugum yoksa, islemi sonlandirir ve bildirim gosterir.
		if (dugum == null) {
			System.out.println("Agacta bulunamadi: " + anahtar);
			return null;
		}

		// Anahtara gore agacta sol veya sag yonde ilerler.
		if (anahtar < dugum.anahtar) {
			dugum.solCocuk = sil(anahtar, dugum.solCocuk);
		} else if (anahtar > dugum.anahtar) {
			dugum.sagCocuk = sil(anahtar, dugum.sagCocuk);
		}

		// Bu noktada, "dugum" silinmek istenen dugum olur.

		// Dugumun hic cocugu yoksa --> dugumu sadece siler
		else if (dugum.solCocuk == null && dugum.sagCocuk == null) {
			dugum = null;
		}

		// Dugumun sadece bir cocugu varsa --> dugumu cocuguyla degistirir
		else if (dugum.solCocuk == null) {
			dugum = dugum.sagCocuk;
		} else if (dugum.sagCocuk == null) {
			dugum = dugum.solCocuk;
		}

		// Dugumun iki cocugu varsa
		else {
			ikiCocukluDugumuSil(dugum);
		}

		// Yukseklik bilgisini gunceller.
		yuksekligiGuncelle(dugum);

		// Agaci dengelemek icin dengele() fonksiyonunu cagirir.
		return dengele(dugum);
	}

	//iki cocuklu bir dugumu siler.
	private void ikiCocukluDugumuSil(AvlAgacDugum dugum) {
		// Sag alt agacin en kucuk dugumunu bul ("inorder successor" olarak adlandirilir)
		AvlAgacDugum siradakiDugum = minimumBul(dugum.sagCocuk);

		// inorder successor'un verilerini su anki dugume kopyala
		dugum.anahtar = siradakiDugum.anahtar;

		// inorder successor'u tekrarlayarak sil
		dugum.sagCocuk = sil(siradakiDugum.anahtar, dugum.sagCocuk);
	}

	private AvlAgacDugum minimumBul(AvlAgacDugum dugum) {
		while (dugum.solCocuk != null) {
			dugum = dugum.solCocuk;
		}
		return dugum;
	}
	
	private AvlAgacDugum maksimumBul(AvlAgacDugum dugum) {
		while (dugum.sagCocuk != null) {
			dugum = dugum.sagCocuk;
		}
		return dugum;
	}

	//Dugumun yuksekligini gunceller.
	private void yuksekligiGuncelle(AvlAgacDugum dugum) {

		// Eger dugum null ise, islem yapmadan cik
		if(dugum == null)
			return;

		int yukseklik = 0;

		// Sol cocuk varsa, sol cocugun yuksekligini hesapla
		if (dugum.solCocuk != null) {
			yukseklik = yukseklikHesapla(dugum.solCocuk);
		}

		// Sag cocuk varsa ve sag cocugun yuksekligi, sol cocugun yuksekliginden buyukse,
		// sag cocugun yuksekligini kullan
		if(dugum.sagCocuk != null)
		{
			int sagYukseklik = yukseklikHesapla(dugum.sagCocuk);
			if(yukseklik < sagYukseklik) {
				yukseklik = sagYukseklik;
			}
		}

		// Dugumun yuksekligini, en yuksek cocugun yuksekligi + 1 olarak ayarla
		dugum.yukseklik = yukseklik + 1;
	}

	//Dugumun AVL agacinda dengesini kontrol eder ve gerektiginde rotasyonlarla dengeyi saglar.
	private AvlAgacDugum dengele(AvlAgacDugum dugum) {
		int dengeFaktoru = dengeFaktoru(dugum);

		// Sol taraf agirlikli mi?
		if (dengeFaktoru < -1) {
			if (dengeFaktoru(dugum.solCocuk) <= 0) {
				// Saga dondurme islemi
				dugum = sagaDondur(dugum);
			} else {
				// Sol-sag dondurme islemi
				dugum.solCocuk = solaDondur(dugum.solCocuk);
				dugum = sagaDondur(dugum);
			}
		}

		// Sag taraf agirlikli mi?
		if (dengeFaktoru > 1) {
			if (dengeFaktoru(dugum.sagCocuk) >= 0) {
				// Sola dondurme islemi
				dugum = solaDondur(dugum);
			} else {
				// Sag-sola dondurme islemi
				dugum.sagCocuk = sagaDondur(dugum.sagCocuk);
				dugum = solaDondur(dugum);
			}
		}
		return dugum;
	}

	//Dugumu saat yonune (saga) dondurerek AVL agacini dengelemek icin kullanilir.
	private AvlAgacDugum sagaDondur(AvlAgacDugum dugum) {
		
		AvlAgacDugum solCocuk = dugum.solCocuk;

		dugum.solCocuk = solCocuk.sagCocuk;
		solCocuk.sagCocuk = dugum;

		yuksekligiGuncelle(dugum);
		yuksekligiGuncelle(solCocuk);

		return solCocuk;
	}

	//Dugumu saat yonunun tersine (sola) dondurerek AVL agacini dengelemek icin kullanilir.
	private AvlAgacDugum solaDondur(AvlAgacDugum dugum) {
		AvlAgacDugum sagCocuk = dugum.sagCocuk;

		dugum.sagCocuk = sagCocuk.solCocuk;
		sagCocuk.solCocuk = dugum;

		yuksekligiGuncelle(dugum);
		yuksekligiGuncelle(sagCocuk);

		return sagCocuk;
	}

	private int dengeFaktoru(AvlAgacDugum dugum) {
		if(dugum != null)
			return yukseklikHesapla(dugum.sagCocuk) - yukseklikHesapla(dugum.solCocuk);
		return -1;
	}

	private int yukseklikHesapla(AvlAgacDugum dugum) {
		return dugum != null ? dugum.yukseklik : -1;
	}

	public AvlAgacDugum ara(int anahtar) {
		AvlAgacDugum dugum = kok; // Kok dugumden baslanarak gezinme islemi
		while (dugum != null) {
			if (anahtar == dugum.anahtar) {
				System.out.println("Agacta bulundu: " + anahtar); // Anahtar degeri agacta bulundugunda bildirim
				return dugum; // Dugumu dondur
			} else if (anahtar < dugum.anahtar) {
				dugum = dugum.solCocuk; // Anahtar degeri dugumun anahtarindan kucukse sola git
			} else {
				dugum = dugum.sagCocuk; // Anahtar degeri dugumun anahtarindan buyukse saga git
			}
		}

		System.out.println("Agacta bulunamadi: " + anahtar); // Anahtar degeri agacta bulunamadiginda bildirim
		return null; // Dugum bulunamazsa null deger dondur
	}

	void kokBastaDolas(AvlAgacDugum kok) {
		if (kok != null) {
			System.out.print("{" + kok.anahtar + ", " + kok.yukseklik + ", " + 
					(kok.solCocuk == null ? null : kok.solCocuk.anahtar) + ", " + 
					(kok.sagCocuk == null ? null : kok.sagCocuk.anahtar) + "} ");  // Dugumun verisini yazdir
			kokBastaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
		}
	}

	void kokOrtadaDolas(AvlAgacDugum kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			System.out.print("{" + kok.anahtar + ", " + kok.yukseklik + ", " + 
					(kok.solCocuk == null ? null : kok.solCocuk.anahtar) + ", " + 
					(kok.sagCocuk == null ? null : kok.sagCocuk.anahtar) + "} ");  // Dugumun verisini yazdir
			kokOrtadaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
		}
	}

	void kokSondaDolas(AvlAgacDugum kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
			System.out.print("{" + kok.anahtar + ", " + kok.yukseklik + ", " + 
					(kok.solCocuk == null ? null : kok.solCocuk.anahtar) + ", " + 
					(kok.sagCocuk == null ? null : kok.sagCocuk.anahtar) + "} ");  // Dugumun verisini yazdir
		}
	}

	public static void main(String[] args) {
		AvlAgac agac = new AvlAgac();

		agac.ekle(50);   // Agaca dugumleri ekle
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();

		agac.ekle(30);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();

		agac.ekle(20);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();

		agac.ekle(40);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();

		agac.ekle(70);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();

		agac.ekle(60);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();

		agac.ekle(80);
		agac.kokBastaDolas(agac.kok);
		System.out.println();

		agac.kokOrtadaDolas(agac.kok);
		System.out.println();

		agac.kokSondaDolas(agac.kok);
		System.out.println();

		agac.ara(60);  
		agac.ara(32);  

		agac.sil(60);
		agac.sil(50);
		agac.sil(30);

		agac.ara(60);

		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
	}
}