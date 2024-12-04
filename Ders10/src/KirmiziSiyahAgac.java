
// Kirmizi-Siyah Agac (Red-Black Tree) dugum yapisi
class KirmiziSiyahDugum {
  
    int anahtar; // Dugumun anahtar degeri
    KirmiziSiyahDugum solCocuk; // Sol cocuk dugumu
    KirmiziSiyahDugum sagCocuk; // Sag cocuk dugumu
    KirmiziSiyahDugum ata; // ata dugum
    int renk; // Renk degeri (0: siyah, 1: kirmizi)

    public KirmiziSiyahDugum(int anahtar) {
        this.anahtar = anahtar;
        this.solCocuk = null;
        this.sagCocuk = null;
        this.ata = null;
        this.renk = 0;
    }
}

public class KirmiziSiyahAgac {

	final static int RED = 1;
	final static int BLACK = 0;

	KirmiziSiyahDugum kok;

	//Belirli bir anahtari arama 
	public KirmiziSiyahDugum ara(int anahtar) {
		
		KirmiziSiyahDugum dugum = kok; // Kok dugumden basla
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

	//Yeni bir dugum ekleme
	public void ekle(int anahtar) {
		KirmiziSiyahDugum dugum = kok; // Kok dugumden basla
		KirmiziSiyahDugum ata = null;

		// Anahtari agacta uygun konuma yerlestirmek icin agacta gezinme
		while (dugum != null) {
			ata = dugum;
			if (anahtar < dugum.anahtar) {
				dugum = dugum.solCocuk; // Anahtar degeri dugumun anahtarindan kucukse sola git
			} else if (anahtar > dugum.anahtar) {
				dugum = dugum.sagCocuk; // Anahtar degeri dugumun anahtarindan buyukse saga git
			} else {
				System.out.println("Agacta bulundugu icin eklenemedi: " + anahtar); // Anahtar zaten agacta bulunuyorsa bildirim
				return;
			}
		}

		// Yeni dugumu ekleyin
		KirmiziSiyahDugum yeni = new KirmiziSiyahDugum(anahtar);
		yeni.renk = RED;
		if (ata == null) {
			kok = yeni; // Eger agac bossa yeni dugumu kok yap
		} else if (anahtar < ata.anahtar) {
			ata.solCocuk = yeni; // Anahtar, atasinin anahtarindan kucukse sol cocuk olarak ekle
		} else {
			ata.sagCocuk = yeni; // Anahtar, atasinin anahtarindan buyukse sag cocuk olarak ekle
		}
		yeni.ata = ata;

		eklemeSonrasiDuzelt(yeni); // Eklemenin ardindan kirmizi-siyah duzeltilmesi islemi
		System.out.println("Agaca eklendi: " + yeni.anahtar + " " + (yeni.renk == RED ? "[R]" : "[B]")); // Eklenen dugumun bildirimi
	}

	//Dugum eklemesi sonrasinda Kirmizi-Siyah agacin uygunlugunu saglama islemi
	private void eklemeSonrasiDuzelt(KirmiziSiyahDugum dugum) {
		KirmiziSiyahDugum ata = dugum.ata;

		// Durum 1: ata null ise, koke ulastik, ozyinelemeli islemin sonuna geldik
		if (ata == null) {
			// Kokun siyah olmasini zorla (kural 2):
			dugum.renk = BLACK;
			return;
		}

		// ata dugum siyahsa, yapilacak bir sey yok
		if (ata.renk == BLACK) {
			return;
		}

		// Buradan itibaren ata kirmizi
		KirmiziSiyahDugum dede = ata.ata;

		// Durum 2:
		// Dede olmamasi, atanin kok oldugu anlamina gelir. Kokun siyah olmasini zorlamak icin
		// (kural 2), dedenin hicbir zaman null olmayacagi bir durumda asagidaki if-then blogu kaldirilabilir.
		if (dede == null) {
			// Bu metod yalnizca kirmizi dugumler uzerinde cagrildigi icin yapmamiz gereken tek sey koku siyah yapmaktir.
			ata.renk = BLACK;
			return;
		}

		// Amca dugumunu al (null/nil olabilir, bu durumda rengi siyahtir)
		KirmiziSiyahDugum amca = getirAmca(ata);

		// Durum 3: Amca kirmiziysa -> ata, dede ve amca renklerini degistir
		if (amca != null && amca.renk == RED) {
			ata.renk = BLACK;
			dede.renk = RED;
			amca.renk = BLACK;

			// simdi kirmizi olan dedeyi tekrar kontrol etmek icin ozyinelemeli olarak cagir
			// Dede kok olabilir veya kirmizi bir atasi olabilir, bu durumda daha fazla duzeltme gerekebilir...
			eklemeSonrasiDuzelt(dede);
		}

		// ata, dedenin sol cocugu ise
		else if (ata == dede.solCocuk) {
			// Durum 4a: Amca siyah ve dugum atasinin sag cocugu (ic cocugu) ise
			if (dugum == ata.sagCocuk) {
				solaDondur(ata);

				// atasi donen alt agacin yeni kok dugumune isaret etmesi icin
				// Bu adimda rengini degistirmeliyiz.
				ata = dugum;
			}

			// Durum 5a: Amca siyah ve dugum atasinin sol cocugu (dis cocugu) ise
			sagaDondur(dede);

			// Orijinal ata ve dedenin renklerini degistir
			ata.renk = BLACK;
			dede.renk = RED;
		}

		// ata, dedenin sag cocugu ise
		else {
			// Durum 4b: Amca siyah ve dugum atasinin sol cocugu (ic cocugu) ise
			if (dugum == ata.solCocuk) {
				sagaDondur(ata);

				// atasi donen alt agacin yeni kok dugumune isaret etmesi icin
				// Bu adimda rengini degistirmeliyiz.
				ata = dugum;
			}

			// Durum 5b: Amca siyah ve dugum atasinin sag cocugu (dis cocugu) ise
			solaDondur(dede);

			// Orijinal ata ve dedenin renklerini degistir
			ata.renk = BLACK;
			dede.renk = RED;
		}
	}

	//Verilen ata dugumunun amcasini donduren yardimci fonksiyon
	private KirmiziSiyahDugum getirAmca(KirmiziSiyahDugum ata) {
		KirmiziSiyahDugum dede = ata.ata;
		if (dede.solCocuk == ata) {
			return dede.sagCocuk;
		} else if (dede.sagCocuk == ata) {
			return dede.solCocuk;
		} else {
			System.out.println("ata, dedesinin bir cocugu degil");
			return null;
		}
	}

	//Belirtilen anahtari iceren dugumu agactan silen fonksiyon
	public void sil(int anahtar) {
		KirmiziSiyahDugum dugum = kok;

		// Silinecek dugumu bul
		while (dugum != null && dugum.anahtar != anahtar) {
			// Anahtara gore agaci sol veya sag yonde tara
			if (anahtar < dugum.anahtar) {
				dugum = dugum.solCocuk;
			} else {
				dugum = dugum.sagCocuk;
			}
		}

		// Dugum bulunamadi mi?
		if (dugum == null) {
			System.out.println("Agacta bulunamadi: " + anahtar);
			return;
		}

		// Bu noktada, "dugum" silinecek dugumdur

		// Bu degiskeni kullanarak, bir dugumu sildikten sonra Kirmizi-Siyah agac ozelliklerini duzeltmeye baslanacak dugumu sakla.
		KirmiziSiyahDugum yukariTasinanDugum;
		int silinenDugumRengi;

		// Dugumun hic veya bir cocugu var
		if (dugum.solCocuk == null || dugum.sagCocuk == null) {
			yukariTasinanDugum = sifirVeyaBirCocukluDugumuSil(dugum);
			silinenDugumRengi = dugum.renk;
		}

		// Dugumun iki cocugu var
		else {
			// Sag alt agacin minimum dugumunu bul ("inorder successor" veya siradaki dugum)
			KirmiziSiyahDugum siradakiDugum = minimumBul(dugum.sagCocuk);

			// Siradaki dugumun verisini mevcut dugume kopyala (rengini koru!)
			dugum.anahtar = siradakiDugum.anahtar;

			// Siradaki dugumu, 0 veya 1 cocugu olan bir dugumu siler gibi sil
			yukariTasinanDugum = sifirVeyaBirCocukluDugumuSil(siradakiDugum);
			silinenDugumRengi = siradakiDugum.renk;
		}

		if (silinenDugumRengi == BLACK) {
			silmeSonrasiDuzelt(yukariTasinanDugum);

			// Gecici NiL dugumunu kaldir
			if (yukariTasinanDugum.getClass() == NilDugum.class) {
				cocuklariYerDegistir(yukariTasinanDugum.ata, yukariTasinanDugum, null);
			}
		}
		System.out.println("Agacta silindi: " + anahtar);
	}


	//Yalnizca bir sol veya sag cocugu olan bir dugumu silen fonksiyon
	private KirmiziSiyahDugum sifirVeyaBirCocukluDugumuSil(KirmiziSiyahDugum dugum) {
		// Dugumun SADECE sol cocugu varsa --> sol cocugu ile degistirilir
		if (dugum.solCocuk != null) {
			cocuklariYerDegistir(dugum.ata, dugum, dugum.solCocuk);
			return dugum.solCocuk; // yukari tasinan dugum
		}

		// Dugumun SADECE sag cocugu varsa --> sag cocugu ile degistirilir
		else if (dugum.sagCocuk != null) {
			cocuklariYerDegistir(dugum.ata, dugum, dugum.sagCocuk);
			return dugum.sagCocuk; // yukari tasinan dugum
		}

		// Dugumun hic cocugu yoksa -->
		// * Dugum kirmizi ise --> sadece kaldirilir
		// * Dugum siyah ise --> gecici bir NiL dugum ile degistirilir (Kirmizi-Siyah kurallarini duzeltmek icin gereklidir)
		else {
			KirmiziSiyahDugum yeniCocuk = dugum.renk == BLACK ? new NilDugum() : null;
			cocuklariYerDegistir(dugum.ata, dugum, yeniCocuk);
			return yeniCocuk;
		}
	}

	//Alt agacin en kucuk dugumunu bulan fonksiyon
	private KirmiziSiyahDugum minimumBul(KirmiziSiyahDugum dugum) {
		while (dugum.solCocuk != null) {
			dugum = dugum.solCocuk;
		}
		return dugum;
	}

	//Silme isleminden sonra Kirmizi-Siyah Agacin (Red-Black Tree) ozelliklerini duzeltmek icin kullanilan islev
	private void silmeSonrasiDuzelt(KirmiziSiyahDugum dugum) {
		// Durum 1: incelenen dugum kok dugumse, islemin sonu
		if (dugum == kok) {
			// Kok dugumun siyah olmasini zorlamak icin asagidaki satir (kural 2):
			dugum.renk = BLACK;
			return;
		}

		KirmiziSiyahDugum kardes = getirKardes(dugum);

		// Durum 2: Kirmizi kardes
		if (kardes.renk == RED) {
			kirmiziKardesEleAl(dugum, kardes);
			kardes = getirKardes(dugum); // Yeni kardesi almak icin, durum 3-6'ya gecmek icin
		}

		// Durum 3+4: iki siyah cocuga sahip siyah kardes
		if (siyahMi(kardes.solCocuk) && siyahMi(kardes.sagCocuk)) {
			kardes.renk = RED;

			// Durum 3: iki siyah cocuga sahip siyah kardes + kirmizi ata
			if (dugum.ata.renk == RED) {
				dugum.ata.renk = BLACK;
			}

			// Durum 4: iki siyah cocuga sahip siyah kardes + siyah ata
			else {
				silmeSonrasiDuzelt(dugum.ata);
			}
		}

		// Durum 5+6: En az bir kirmizi cocuga sahip siyah kardes
		else {
			enAzBirKirmiziCocugaSahipSiyahKardesiEleAl(dugum, kardes);
		}
	}


	//Kirmizi kardesi ele alma islemi: Kardes dugumu siyah hale getirir ve dondurur
	private void kirmiziKardesEleAl(KirmiziSiyahDugum dugum, KirmiziSiyahDugum kardes) {
		// Yeniden renklendirme...
		kardes.renk = BLACK;
		dugum.ata.renk = RED;

		// ... ve dondurme islemi
		if (dugum == dugum.ata.solCocuk) {
			solaDondur(dugum.ata);
		} else {
			sagaDondur(dugum.ata);
		}
	}


	//En az bir kirmizi cocuga sahip siyah kardesi ele alma islemi:
	//Eger kirmizi-siyah agac ozelliklerini bozan bir durum varsa bu islev kullanilir.
	private void enAzBirKirmiziCocugaSahipSiyahKardesiEleAl(KirmiziSiyahDugum dugum, KirmiziSiyahDugum kardes) {
		boolean solCocukMu = dugum == dugum.ata.solCocuk;

		// Durum 5: En az bir kirmizi cocuga sahip siyah kardes + "dis yegeni" siyah
		// --> Kardesi ve kardesin cocugunu tekrar renklendir, ardindan kardes etrafinda dondurme yapilir
		if (solCocukMu && siyahMi(kardes.sagCocuk)) {
			kardes.solCocuk.renk = BLACK;
			kardes.renk = RED;
			sagaDondur(kardes);
			kardes = dugum.ata.sagCocuk;
		} else if (!solCocukMu && siyahMi(kardes.solCocuk)) {
			kardes.sagCocuk.renk = BLACK;
			kardes.renk = RED;
			solaDondur(kardes);
			kardes = dugum.ata.solCocuk;
		}

		// Durum 6: En az bir kirmizi cocuga sahip siyah kardes + "dis yegeni" kirmizi
		// --> Kardesi, atasi ve kardesin cocugunu tekrar renklendir, ardindan atasi etrafinda dondurme yapilir
		kardes.renk = dugum.ata.renk;
		dugum.ata.renk = BLACK;
		if (solCocukMu) {
			kardes.sagCocuk.renk = BLACK;
			solaDondur(dugum.ata);
		} else {
			kardes.solCocuk.renk = BLACK;
			sagaDondur(dugum.ata);
		}
	}


	//Kardesi getirme islemi:
	//Verilen dugumun atasindan kardes dugumunu dondurur.
	private KirmiziSiyahDugum getirKardes(KirmiziSiyahDugum dugum) {
		KirmiziSiyahDugum ata = dugum.ata;
		if (dugum == ata.solCocuk) {
			return ata.sagCocuk;
		} else if (dugum == ata.sagCocuk) {
			return ata.solCocuk;
		} else {
			System.out.println("ata, dede dugumun bir cocugu degil");
			return null;
		}
	}


	//Belirtilen dugumun siyah olup olmadigini kontrol eder.
	//Siyah bir dugum, null (NiL dugum) veya siyah renkte bir dugum olabilir.
	private boolean siyahMi(KirmiziSiyahDugum dugum) {
		return dugum == null || dugum.renk == BLACK;
	}

	//Kirmizi-Siyah agac yapisinin onemli bir bileseni olan NiL dugumunu temsil eden alt sinif.
	private static class NilDugum extends KirmiziSiyahDugum {
		private NilDugum() {
			super(0);  // NiL dugumunun anahtar degeri genellikle kullanilmaz, bu nedenle 0 olarak ayarlanabilir.
			this.renk = BLACK;  // Tum NiL dugumleri siyahtir.
		}
	}

	//Belirtilen dugumu saga dondurme islemini gerceklestirir.
	//Dondurme islemi, dugumlerin atalarini, cocuklarini ve baglantilarini gunceller.
	private void sagaDondur(KirmiziSiyahDugum dugum) {
		KirmiziSiyahDugum ata = dugum.ata;
		KirmiziSiyahDugum solCocuk = dugum.solCocuk;

		// Dugumun sol cocugunu guncelle
		dugum.solCocuk = solCocuk.sagCocuk;
		if (solCocuk.sagCocuk != null) {
			solCocuk.sagCocuk.ata = dugum;
		}

		// Sol cocugun sag cocugunu ayarla ve atalari guncelle
		solCocuk.sagCocuk = dugum;
		dugum.ata = solCocuk;

		// ata dugumun cocuklarini guncelle
		cocuklariYerDegistir(ata, dugum, solCocuk);
	}


	//Belirtilen dugumu sola dondurme islemini gerceklestirir.
	//Dondurme islemi, dugumlerin atalarini, cocuklarini ve baglantilarini gunceller.
	private void solaDondur(KirmiziSiyahDugum dugum) {
		KirmiziSiyahDugum ata = dugum.ata;
		KirmiziSiyahDugum sagCocuk = dugum.sagCocuk;

		// Dugumun sag cocugunu guncelle
		dugum.sagCocuk = sagCocuk.solCocuk;
		if (sagCocuk.solCocuk != null) {
			sagCocuk.solCocuk.ata = dugum;
		}

		// Sag cocugun sol cocugunu ayarla ve atalari guncelle
		sagCocuk.solCocuk = dugum;
		dugum.ata = sagCocuk;

		// ata dugumun cocuklarini guncelle
		cocuklariYerDegistir(ata, dugum, sagCocuk);
	}


	//ata dugumun belirli bir cocugunu baska bir dugumle degistirme islemini gerceklestirir.
	//Bu islev, agac yapisindaki baglantilari gunceller.
	private void cocuklariYerDegistir(KirmiziSiyahDugum ata, KirmiziSiyahDugum eskiCocuk, KirmiziSiyahDugum yeniCocuk) {
		if (ata == null) {
			// Eger ata dugum yoksa, yeni cocuk yeni kok olur
			kok = yeniCocuk;
		} else if (ata.solCocuk == eskiCocuk) {
			// atanin sol cocugu eski cocuk ise, sol cocugu yeni cocuk ile degistir
			ata.solCocuk = yeniCocuk;
		} else if (ata.sagCocuk == eskiCocuk) {
			// atanin sag cocugu eski cocuk ise, sag cocugu yeni cocuk ile degistir
			ata.sagCocuk = yeniCocuk;
		} else {
			// Eski cocuk, belirtilen ata dugumun cocugu degilse hata mesaji ver
			System.out.println("Dugum, atanin cocugu degil.");
			return;
		}

		if (yeniCocuk != null) {
			// Eger yeni cocuk varsa, onun atasini belirtilen ata olarak ayarla
			yeniCocuk.ata = ata;
		}
	}

	void kokBastaDolas(KirmiziSiyahDugum kok) {
		if (kok != null) {
			System.out.print("{" + kok.anahtar + (kok.renk == RED ? "[R]" : "[B]") + 
					(kok.ata != null ? kok.ata.anahtar : "null") + "} ");  // Dugumun verisini yazdir
			kokBastaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
		}
	}

	void kokOrtadaDolas(KirmiziSiyahDugum kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			System.out.print("{" + kok.anahtar + (kok.renk == RED ? "[R]" : "[B]") + 
					(kok.ata != null ? kok.ata.anahtar : "null") + "} ");  // Dugumun verisini yazdir
			kokOrtadaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
		}
	}

	void kokSondaDolas(KirmiziSiyahDugum kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol cocugu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sag cocugu ziyaret et
			System.out.print("{" + kok.anahtar + (kok.renk == RED ? "[R]" : "[B]") + 
					(kok.ata != null ? kok.ata.anahtar : "null") + "} ");  // Dugumun verisini yazdir
		}
	}
	
	public static void main(String[] args) {
		KirmiziSiyahAgac agac = new KirmiziSiyahAgac();

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
		agac.ara(50);  

		agac.sil(50);

		agac.ara(50);

		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
		agac.sil(80);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
		agac.sil(20);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
		agac.sil(30);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
		agac.sil(40);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
		agac.sil(60);
		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
	}
}