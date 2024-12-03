
// Kırmızı-Siyah Ağaç (Red-Black Tree) düğüm yapısı
class KirmiziSiyahDugum {
  
    int anahtar; // Düğümün anahtar değeri
    KirmiziSiyahDugum solCocuk; // Sol çocuk düğümü
    KirmiziSiyahDugum sagCocuk; // Sağ çocuk düğümü
    KirmiziSiyahDugum ebeveyn; // Ebeveyn düğüm
    int renk; // Renk değeri (0: siyah, 1: kırmızı)

    public KirmiziSiyahDugum(int anahtar) {
        this.anahtar = anahtar;
    }
}

public class KirmiziSiyahAgac {

	final static int RED = 1;
	final static int BLACK = 0;

	KirmiziSiyahDugum kok;

	//Belirli bir anahtarı aramak için Kırmızı-Siyah Ağaç (Red-Black Tree) üzerinde gezinme işlemi
	public KirmiziSiyahDugum ara(int anahtar) {
		KirmiziSiyahDugum dugum = kok; // Kök düğümden başlanarak gezinme işlemi
		while (dugum != null) {
			if (anahtar == dugum.anahtar) {
				System.out.println("Ağaçta bulundu: " + anahtar); // Anahtar değeri ağaçta bulunduğunda bildirim
				return dugum; // Düğümü döndür
			} else if (anahtar < dugum.anahtar) {
				dugum = dugum.solCocuk; // Anahtar değeri düğümün anahtarından küçükse sola git
			} else {
				dugum = dugum.sagCocuk; // Anahtar değeri düğümün anahtarından büyükse sağa git
			}
		}

		System.out.println("Ağaçta bulunamadı: " + anahtar); // Anahtar değeri ağaçta bulunamadığında bildirim
		return null; // Düğüm bulunamazsa null değer döndür
	}


	//Yeni bir düğüm eklemek için Kırmızı-Siyah Ağaç (Red-Black Tree) üzerinde gezinme işlemi
	public void ekle(int anahtar) {
		KirmiziSiyahDugum dugum = kok; // Kök düğümden başlanarak gezinme işlemi
		KirmiziSiyahDugum ebeveyn = null;

		// Anahtarı ağaçta uygun konuma yerleştirmek için ağaçta gezinme
		while (dugum != null) {
			ebeveyn = dugum;
			if (anahtar < dugum.anahtar) {
				dugum = dugum.solCocuk; // Anahtar değeri düğümün anahtarından küçükse sola git
			} else if (anahtar > dugum.anahtar) {
				dugum = dugum.sagCocuk; // Anahtar değeri düğümün anahtarından büyükse sağa git
			} else {
				System.out.println("Ağaçta bulunduğu için eklenemedi: " + anahtar); // Anahtar zaten ağaçta bulunuyorsa bildirim
				return;
			}
		}

		// Yeni düğümü ekleyin
		KirmiziSiyahDugum yeni = new KirmiziSiyahDugum(anahtar);
		yeni.renk = RED;
		if (ebeveyn == null) {
			kok = yeni; // Eğer ağaç boşsa yeni düğümü kök yap
		} else if (anahtar < ebeveyn.anahtar) {
			ebeveyn.solCocuk = yeni; // Anahtar, ebeveynin anahtarından küçükse sol çocuk olarak ekle
		} else {
			ebeveyn.sagCocuk = yeni; // Anahtar, ebeveynin anahtarından büyükse sağ çocuk olarak ekle
		}
		yeni.ebeveyn = ebeveyn;

		eklemeSonrasiDuzelt(yeni); // Eklemenin ardından kırmızı-siyah düzeltilmesi işlemi
		System.out.println("Ağaca eklendi: " + yeni.anahtar + " " + (yeni.renk == RED ? "[R]" : "[B]")); // Eklenen düğümün bildirimi
	}


	//Düğüm eklemesi sonrasında Kırmızı-Siyah ağacın uygunluğunu sağlama işlemi
	private void eklemeSonrasiDuzelt(KirmiziSiyahDugum dugum) {
		KirmiziSiyahDugum ebeveyn = dugum.ebeveyn;

		// Durum 1: Ebeveyn null ise, köke ulaştık, özyinelemeli işlemin sonuna geldik
		if (ebeveyn == null) {
			// Kökün siyah olmasını zorlamak için aşağıdaki satır (kural 2):
			dugum.renk = BLACK;
			return;
		}

		// Ebeveyn siyahsa, yapılacak bir şey yok
		if (ebeveyn.renk == BLACK) {
			return;
		}

		// Buradan itibaren ebeveyn kırmızı
		KirmiziSiyahDugum dede = ebeveyn.ebeveyn;

		// Durum 2:
		// Dede olmaması, ebeveynin kök olduğu anlamına gelir. Kökün siyah olmasını zorlamak için
		// (kural 2), dedenin hiçbir zaman null olmayacağı bir durumda aşağıdaki if-then bloğu kaldırılabilir.
		if (dede == null) {
			// Bu metod yalnızca kırmızı düğümler üzerinde çağrıldığı için yapmamız gereken tek şey kökü siyah yapmaktır.
			ebeveyn.renk = BLACK;
			return;
		}

		// Amca düğümünü al (null/nil olabilir, bu durumda rengi siyahtır)
		KirmiziSiyahDugum amca = getirAmca(ebeveyn);

		// Durum 3: Amca kırmızıysa -> ebeveyn, dede ve amca renklerini değiştir
		if (amca != null && amca.renk == RED) {
			ebeveyn.renk = BLACK;
			dede.renk = RED;
			amca.renk = BLACK;

			// Şimdi kırmızı olan dedeyi tekrar kontrol etmek için özyinelemeli olarak çağır
			// Dede kök olabilir veya kırmızı bir ebeveyni olabilir, bu durumda daha fazla düzeltme gerekebilir...
			eklemeSonrasiDuzelt(dede);
		}

		// Ebeveyn, dedenin sol çocuğu ise
		else if (ebeveyn == dede.solCocuk) {
			// Durum 4a: Amca siyah ve düğüm ebeveyninin sağ çocuğu (iç çocuğu) ise
			if (dugum == ebeveyn.sagCocuk) {
				solaDondur(ebeveyn);

				// "Ebeveyn"i dönen alt ağacın yeni kök düğümüne işaret etmesi için
				// Bu adımda rengini değiştirmeliyiz.
				ebeveyn = dugum;
			}

			// Durum 5a: Amca siyah ve düğüm ebeveyninin sol çocuğu (dış çocuğu) ise
			sagaDondur(dede);

			// Orijinal ebeveyn ve dedenin renklerini değiştir
			ebeveyn.renk = BLACK;
			dede.renk = RED;
		}

		// Ebeveyn, dedenin sağ çocuğu ise
		else {
			// Durum 4b: Amca siyah ve düğüm ebeveyninin sol çocuğu (iç çocuğu) ise
			if (dugum == ebeveyn.solCocuk) {
				sagaDondur(ebeveyn);

				// "Ebeveyn"i dönen alt ağacın yeni kök düğümüne işaret etmesi için
				// Bu adımda rengini değiştirmeliyiz.
				ebeveyn = dugum;
			}

			// Durum 5b: Amca siyah ve düğüm ebeveyninin sağ çocuğu (dış çocuğu) ise
			solaDondur(dede);

			// Orijinal ebeveyn ve dedenin renklerini değiştir
			ebeveyn.renk = BLACK;
			dede.renk = RED;
		}
	}


	//Verilen ebeveyn düğümünün amcasını döndüren yardımcı fonksiyon
	private KirmiziSiyahDugum getirAmca(KirmiziSiyahDugum ebeveyn) {
		KirmiziSiyahDugum dede = ebeveyn.ebeveyn;
		if (dede.solCocuk == ebeveyn) {
			return dede.sagCocuk;
		} else if (dede.sagCocuk == ebeveyn) {
			return dede.solCocuk;
		} else {
			System.out.println("Ebeveyn, dedesinin bir çocuğu değil");
			return null;
		}
	}


	//Belirtilen anahtarı içeren düğümü ağaçtan silen fonksiyon
	public void sil(int anahtar) {
		KirmiziSiyahDugum dugum = kok;

		// Silinecek düğümü bul
		while (dugum != null && dugum.anahtar != anahtar) {
			// Anahtara göre ağacı sol veya sağ yönde tara
			if (anahtar < dugum.anahtar) {
				dugum = dugum.solCocuk;
			} else {
				dugum = dugum.sagCocuk;
			}
		}

		// Düğüm bulunamadı mı?
		if (dugum == null) {
			System.out.println("Ağaçta bulunamadı: " + anahtar);
			return;
		}

		// Bu noktada, "düğüm" silinecek düğümdür

		// Bu değişkeni kullanarak, bir düğümü sildikten sonra Kırmızı-Siyah ağaç özelliklerini düzeltmeye başlanacak düğümü sakla.
		KirmiziSiyahDugum yukariTasinanDugum;
		int silinenDugumRengi;

		// Düğümün hiç veya bir çocuğu var
		if (dugum.solCocuk == null || dugum.sagCocuk == null) {
			yukariTasinanDugum = sifirVeyaBirCocukluDugumuSil(dugum);
			silinenDugumRengi = dugum.renk;
		}

		// Düğümün iki çocuğu var
		else {
			// Sağ alt ağacın minimum düğümünü bul ("inorder successor" veya siradaki düğüm)
			KirmiziSiyahDugum siradakiDugum = minimumBul(dugum.sagCocuk);

			// Siradaki düğümün verisini mevcut düğüme kopyala (rengini koru!)
			dugum.anahtar = siradakiDugum.anahtar;

			// Siradaki düğümü, 0 veya 1 çocuğu olan bir düğümü siler gibi sil
			yukariTasinanDugum = sifirVeyaBirCocukluDugumuSil(siradakiDugum);
			silinenDugumRengi = siradakiDugum.renk;
		}

		if (silinenDugumRengi == BLACK) {
			silmeSonrasiDuzelt(yukariTasinanDugum);

			// Geçici NIL düğümünü kaldır
			if (yukariTasinanDugum.getClass() == NilDugum.class) {
				cocuklariYerDegistir(yukariTasinanDugum.ebeveyn, yukariTasinanDugum, null);
			}
		}
		System.out.println("Ağaçta silindi: " + anahtar);
	}


	//Yalnızca bir sol veya sağ çocuğu olan bir düğümü silen fonksiyon
	private KirmiziSiyahDugum sifirVeyaBirCocukluDugumuSil(KirmiziSiyahDugum dugum) {
		// Düğümün SADECE sol çocuğu varsa --> sol çocuğu ile değiştirilir
		if (dugum.solCocuk != null) {
			cocuklariYerDegistir(dugum.ebeveyn, dugum, dugum.solCocuk);
			return dugum.solCocuk; // yukarı taşınan düğüm
		}

		// Düğümün SADECE sağ çocuğu varsa --> sağ çocuğu ile değiştirilir
		else if (dugum.sagCocuk != null) {
			cocuklariYerDegistir(dugum.ebeveyn, dugum, dugum.sagCocuk);
			return dugum.sagCocuk; // yukarı taşınan düğüm
		}

		// Düğümün hiç çocuğu yoksa -->
		// * Düğüm kırmızı ise --> sadece kaldırılır
		// * Düğüm siyah ise --> geçici bir NIL düğüm ile değiştirilir (Kırmızı-Siyah kurallarını düzeltmek için gereklidir)
		else {
			KirmiziSiyahDugum yeniCocuk = dugum.renk == BLACK ? new NilDugum() : null;
			cocuklariYerDegistir(dugum.ebeveyn, dugum, yeniCocuk);
			return yeniCocuk;
		}
	}


	//Alt ağacın en küçük düğümünü bulan fonksiyon
	private KirmiziSiyahDugum minimumBul(KirmiziSiyahDugum dugum) {
		while (dugum.solCocuk != null) {
			dugum = dugum.solCocuk;
		}
		return dugum;
	}


	//Silme işleminden sonra Kırmızı-Siyah Ağacın (Red-Black Tree) özelliklerini düzeltmek için kullanılan işlev
	private void silmeSonrasiDuzelt(KirmiziSiyahDugum dugum) {
		// Durum 1: İncelenen düğüm kök düğümse, işlemin sonu
		if (dugum == kok) {
			// Kök düğümün siyah olmasını zorlamak için aşağıdaki satır (kural 2):
			dugum.renk = BLACK;
			return;
		}

		KirmiziSiyahDugum kardes = getirKardes(dugum);

		// Durum 2: Kırmızı kardeş
		if (kardes.renk == RED) {
			kirmiziKardesEleAl(dugum, kardes);
			kardes = getirKardes(dugum); // Yeni kardeşi almak için, durum 3-6'ya geçmek için
		}

		// Durum 3+4: İki siyah çocuğa sahip siyah kardeş
		if (siyahMi(kardes.solCocuk) && siyahMi(kardes.sagCocuk)) {
			kardes.renk = RED;

			// Durum 3: İki siyah çocuğa sahip siyah kardeş + kırmızı ebeveyn
			if (dugum.ebeveyn.renk == RED) {
				dugum.ebeveyn.renk = BLACK;
			}

			// Durum 4: İki siyah çocuğa sahip siyah kardeş + siyah ebeveyn
			else {
				silmeSonrasiDuzelt(dugum.ebeveyn);
			}
		}

		// Durum 5+6: En az bir kırmızı çocuğa sahip siyah kardeş
		else {
			enAzBirKirmiziCocugaSahipSiyahKardesiEleAl(dugum, kardes);
		}
	}


	//Kırmızı kardeşi ele alma işlemi: Kardeş düğümü siyah hale getirir ve döndürür
	private void kirmiziKardesEleAl(KirmiziSiyahDugum dugum, KirmiziSiyahDugum kardes) {
		// Yeniden renklendirme...
		kardes.renk = BLACK;
		dugum.ebeveyn.renk = RED;

		// ... ve döndürme işlemi
		if (dugum == dugum.ebeveyn.solCocuk) {
			solaDondur(dugum.ebeveyn);
		} else {
			sagaDondur(dugum.ebeveyn);
		}
	}


	//En az bir kırmızı çocuğa sahip siyah kardeşi ele alma işlemi:
	//Eğer kırmızı-siyah ağaç özelliklerini bozan bir durum varsa bu işlev kullanılır.
	private void enAzBirKirmiziCocugaSahipSiyahKardesiEleAl(KirmiziSiyahDugum dugum, KirmiziSiyahDugum kardes) {
		boolean solCocukMu = dugum == dugum.ebeveyn.solCocuk;

		// Durum 5: En az bir kırmızı çocuğa sahip siyah kardeş + "dış yeğeni" siyah
		// --> Kardeşi ve kardeşin çocuğunu tekrar renklendir, ardından kardeş etrafında döndürme yapılır
		if (solCocukMu && siyahMi(kardes.sagCocuk)) {
			kardes.solCocuk.renk = BLACK;
			kardes.renk = RED;
			sagaDondur(kardes);
			kardes = dugum.ebeveyn.sagCocuk;
		} else if (!solCocukMu && siyahMi(kardes.solCocuk)) {
			kardes.sagCocuk.renk = BLACK;
			kardes.renk = RED;
			solaDondur(kardes);
			kardes = dugum.ebeveyn.solCocuk;
		}

		// Durum 6: En az bir kırmızı çocuğa sahip siyah kardeş + "dış yeğeni" kırmızı
		// --> Kardeşi, ebeveyni ve kardeşin çocuğunu tekrar renklendir, ardından ebeveyn etrafında döndürme yapılır
		kardes.renk = dugum.ebeveyn.renk;
		dugum.ebeveyn.renk = BLACK;
		if (solCocukMu) {
			kardes.sagCocuk.renk = BLACK;
			solaDondur(dugum.ebeveyn);
		} else {
			kardes.solCocuk.renk = BLACK;
			sagaDondur(dugum.ebeveyn);
		}
	}


	//Kardeşi getirme işlemi:
	//Verilen düğümün ebeveyninden kardeş düğümünü döndürür.
	private KirmiziSiyahDugum getirKardes(KirmiziSiyahDugum dugum) {
		KirmiziSiyahDugum ebeveyn = dugum.ebeveyn;
		if (dugum == ebeveyn.solCocuk) {
			return ebeveyn.sagCocuk;
		} else if (dugum == ebeveyn.sagCocuk) {
			return ebeveyn.solCocuk;
		} else {
			System.out.println("Ebeveyn, büyükbaba düğümün bir çocuğu değil");
			return null;
		}
	}


	//Belirtilen düğümün siyah olup olmadığını kontrol eder.
	//Siyah bir düğüm, null (NIL düğüm) veya siyah renkte bir düğüm olabilir.
	private boolean siyahMi(KirmiziSiyahDugum dugum) {
		return dugum == null || dugum.renk == BLACK;
	}

	//Kırmızı-Siyah ağaç yapısının önemli bir bileşeni olan NIL düğümünü temsil eden alt sınıf.
	private static class NilDugum extends KirmiziSiyahDugum {
		private NilDugum() {
			super(0);  // NIL düğümünün anahtar değeri genellikle kullanılmaz, bu nedenle 0 olarak ayarlanabilir.
			this.renk = BLACK;  // Tüm NIL düğümleri siyahtır.
		}
	}

	//Belirtilen düğümü sağa döndürme işlemini gerçekleştirir.
	//Döndürme işlemi, düğümlerin ebeveynlerini, çocuklarını ve bağlantılarını günceller.
	private void sagaDondur(KirmiziSiyahDugum dugum) {
		KirmiziSiyahDugum ebeveyn = dugum.ebeveyn;
		KirmiziSiyahDugum solCocuk = dugum.solCocuk;

		// Düğümün sol çocuğunu güncelle
		dugum.solCocuk = solCocuk.sagCocuk;
		if (solCocuk.sagCocuk != null) {
			solCocuk.sagCocuk.ebeveyn = dugum;
		}

		// Sol çocuğun sağ çocuğunu ayarla ve ebeveynleri güncelle
		solCocuk.sagCocuk = dugum;
		dugum.ebeveyn = solCocuk;

		// Ebeveyn düğümün çocuklarını güncelle
		cocuklariYerDegistir(ebeveyn, dugum, solCocuk);
	}


	//Belirtilen düğümü sola döndürme işlemini gerçekleştirir.
	//Döndürme işlemi, düğümlerin ebeveynlerini, çocuklarını ve bağlantılarını günceller.
	private void solaDondur(KirmiziSiyahDugum dugum) {
		KirmiziSiyahDugum ebeveyn = dugum.ebeveyn;
		KirmiziSiyahDugum sagCocuk = dugum.sagCocuk;

		// Düğümün sağ çocuğunu güncelle
		dugum.sagCocuk = sagCocuk.solCocuk;
		if (sagCocuk.solCocuk != null) {
			sagCocuk.solCocuk.ebeveyn = dugum;
		}

		// Sağ çocuğun sol çocuğunu ayarla ve ebeveynleri güncelle
		sagCocuk.solCocuk = dugum;
		dugum.ebeveyn = sagCocuk;

		// Ebeveyn düğümün çocuklarını güncelle
		cocuklariYerDegistir(ebeveyn, dugum, sagCocuk);
	}


	//Ebeveyn düğümün belirli bir çocuğunu başka bir düğümle değiştirme işlemini gerçekleştirir.
	//Bu işlev, ağaç yapısındaki bağlantıları günceller.
	private void cocuklariYerDegistir(KirmiziSiyahDugum ebeveyn, KirmiziSiyahDugum eskiCocuk, KirmiziSiyahDugum yeniCocuk) {
		if (ebeveyn == null) {
			// Eğer ebeveyn düğüm yoksa, yeni çocuk yeni kök olur
			kok = yeniCocuk;
		} else if (ebeveyn.solCocuk == eskiCocuk) {
			// Ebeveynin sol çocuğu eski çocuk ise, sol çocuğu yeni çocuk ile değiştir
			ebeveyn.solCocuk = yeniCocuk;
		} else if (ebeveyn.sagCocuk == eskiCocuk) {
			// Ebeveynin sağ çocuğu eski çocuk ise, sağ çocuğu yeni çocuk ile değiştir
			ebeveyn.sagCocuk = yeniCocuk;
		} else {
			// Eski çocuk, belirtilen ebeveyn düğümün çocuğu değilse hata mesajı ver
			System.out.println("Düğüm, ebeveyninin çocuğu değil.");
			return;
		}

		if (yeniCocuk != null) {
			// Eğer yeni çocuk varsa, onun ebeveynini belirtilen ebeveyn olarak ayarla
			yeniCocuk.ebeveyn = ebeveyn;
		}
	}

	void kokBastaDolas(KirmiziSiyahDugum kok) {
		if (kok != null) {
			System.out.print("{" + kok.anahtar + (kok.renk == RED ? "[R]" : "[B]") + 
					(kok.ebeveyn != null ? kok.ebeveyn.anahtar : "null") + "} ");  // Düğümün verisini yazdır
			kokBastaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}

	void kokOrtadaDolas(KirmiziSiyahDugum kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			System.out.print("{" + kok.anahtar + (kok.renk == RED ? "[R]" : "[B]") + 
					(kok.ebeveyn != null ? kok.ebeveyn.anahtar : "null") + "} ");  // Düğümün verisini yazdır
			kokOrtadaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}

	void kokSondaDolas(KirmiziSiyahDugum kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
			System.out.print("{" + kok.anahtar + (kok.renk == RED ? "[R]" : "[B]") + 
					(kok.ebeveyn != null ? kok.ebeveyn.anahtar : "null") + "} ");  // Düğümün verisini yazdır
		}
	}
	
	public static void main(String[] args) {
		KirmiziSiyahAgac agac = new KirmiziSiyahAgac();

		agac.ekle(50);   // Ağaca düğümleri ekle
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

		agac.sil(50);

		agac.ara(60);

		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
	}
}