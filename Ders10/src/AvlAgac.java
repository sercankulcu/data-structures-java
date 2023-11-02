
public class AvlAgac {

	AvlAgacDugum kok;

	public void ekle(int anahtar) {
		kok = ekle(anahtar, kok);
	}

	//AVL ağacına yeni bir düğüm ekler ve dengelemeleri gerçekleştirir.
	AvlAgacDugum ekle(int anahtar, AvlAgacDugum dugum) {
		// Mevcut pozisyonda hiç düğüm yoksa, yeni düğümü bu pozisyona ekler.
		if (dugum == null) {
			dugum = new AvlAgacDugum(anahtar);
			System.out.println("Ağaca eklendi: " + anahtar); 
		}

		// Aksi takdirde, anahtara göre ağaçta sol veya sağ yönde ilerler.
		else if (anahtar < dugum.anahtar) {
			dugum.solCocuk = ekle(anahtar, dugum.solCocuk);
		} else if (anahtar > dugum.anahtar) {
			dugum.sagCocuk = ekle(anahtar, dugum.sagCocuk);
		} else {
			System.out.println("Ağaçta bulunduğu için eklenemedi: " + anahtar); // Anahtar zaten ağaçta bulunuyorsa bildirim
			return null;
		}

		// Yükseklik bilgisini günceller.
		yuksekligiGuncelle(dugum);

		// Ağacı dengelemek için dengele() fonksiyonunu çağırır.
		return dengele(dugum);
	}


	public void sil(int anahtar) {
		kok = sil(anahtar, kok);
		if (kok != null) {
			System.out.println("Ağaçta silindi: " + anahtar);
		}
	}

	//AVL ağacından bir düğümü siler.
	AvlAgacDugum sil(int anahtar, AvlAgacDugum dugum) {
		// Mevcut pozisyonda hiç düğüm yoksa, işlemi sonlandırır ve bildirim gösterir.
		if (dugum == null) {
			System.out.println("Ağaçta bulunamadı: " + anahtar);
			return null;
		}

		// Anahtara göre ağaçta sol veya sağ yönde ilerler.
		if (anahtar < dugum.anahtar) {
			dugum.solCocuk = sil(anahtar, dugum.solCocuk);
		} else if (anahtar > dugum.anahtar) {
			dugum.sagCocuk = sil(anahtar, dugum.sagCocuk);
		}

		// Bu noktada, "dugum" silinmek istenen düğüm olur.

		// Düğümün hiç çocuğu yoksa --> düğümü sadece siler
		else if (dugum.solCocuk == null && dugum.sagCocuk == null) {
			dugum = null;
		}

		// Düğümün sadece bir çocuğu varsa --> düğümü çocuğuyla değiştirir
		else if (dugum.solCocuk == null) {
			dugum = dugum.sagCocuk;
		} else if (dugum.sagCocuk == null) {
			dugum = dugum.solCocuk;
		}

		// Düğümün iki çocuğu varsa
		else {
			ikiCocukluDugumuSil(dugum);
		}

		// Yükseklik bilgisini günceller.
		yuksekligiGuncelle(dugum);

		// Ağacı dengelemek için dengele() fonksiyonunu çağırır.
		return dengele(dugum);
	}


	//İki çocuklu bir düğümü siler.
	private void ikiCocukluDugumuSil(AvlAgacDugum dugum) {
		// Sağ alt ağacın en küçük düğümünü bul ("inorder successor" olarak adlandırılır)
		AvlAgacDugum siradakiDugum = minimumBul(dugum.sagCocuk);

		// Inorder successor'ün verilerini şu anki düğüme kopyala
		dugum.anahtar = siradakiDugum.anahtar;

		// Inorder successor'ü tekrarlayarak sil
		dugum.sagCocuk = sil(siradakiDugum.anahtar, dugum.sagCocuk);
	}


	private AvlAgacDugum minimumBul(AvlAgacDugum dugum) {
		while (dugum.solCocuk != null) {
			dugum = dugum.solCocuk;
		}
		return dugum;
	}

	//Düğümün yüksekliğini günceller.
	private void yuksekligiGuncelle(AvlAgacDugum dugum) {

		// Eğer düğüm null ise, işlem yapmadan çık
		if(dugum == null)
			return;

		int yukseklik = 0;

		// Sol çocuk varsa, sol çocuğun yüksekliğini hesapla
		if (dugum.solCocuk != null) {
			yukseklik = yukseklikHesapla(dugum.solCocuk);
		}

		// Sağ çocuk varsa ve sağ çocuğun yüksekliği, sol çocuğun yüksekliğinden büyükse,
		// sağ çocuğun yüksekliğini kullan
		if(dugum.sagCocuk != null)
		{
			if(yukseklik < yukseklikHesapla(dugum.sagCocuk))
			{
				yukseklik = yukseklikHesapla(dugum.sagCocuk);
			}
		}

		// Düğümün yüksekliğini, en yüksek çocuğun yüksekliği + 1 olarak ayarla
		dugum.yukseklik = yukseklik + 1;
	}


	//Düğümün AVL ağacında dengesini kontrol eder ve gerektiğinde rotasyonlarla dengeyi sağlar.
	private AvlAgacDugum dengele(AvlAgacDugum dugum) {
		int dengeFaktoru = dengeFaktoru(dugum);

		// Sol taraf ağırlıklı mı?
		if (dengeFaktoru < -1) {
			if (dengeFaktoru(dugum.solCocuk) <= 0) {
				// Sağa döndürme işlemi
				dugum = sagaDondur(dugum);
			} else {
				// Sol-sağ döndürme işlemi
				dugum.solCocuk = solaDondur(dugum.solCocuk);
				dugum = sagaDondur(dugum);
			}
		}

		// Sağ taraf ağırlıklı mı?
		if (dengeFaktoru > 1) {
			if (dengeFaktoru(dugum.sagCocuk) >= 0) {
				// Sola döndürme işlemi
				dugum = solaDondur(dugum);
			} else {
				// Sağ-sola döndürme işlemi
				dugum.sagCocuk = sagaDondur(dugum.sagCocuk);
				dugum = solaDondur(dugum);
			}
		}

		return dugum;
	}


	//Düğümü saat yönünün tersine (sağa) döndürerek AVL ağacını dengelemek için kullanılır.
	private AvlAgacDugum sagaDondur(AvlAgacDugum dugum) {
		AvlAgacDugum solCocuk = dugum.solCocuk;

		dugum.solCocuk = solCocuk.sagCocuk;
		solCocuk.sagCocuk = dugum;

		yuksekligiGuncelle(dugum);
		yuksekligiGuncelle(solCocuk);

		return solCocuk;
	}


	//Düğümü saat yönünde (sola) döndürerek AVL ağacını dengelemek için kullanılır.
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
		AvlAgacDugum dugum = kok; // Kök düğümden başlanarak gezinme işlemi
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

	void kokBastaDolas(AvlAgacDugum kok) {
		if (kok != null) {
			System.out.print("{" + kok.anahtar + ", " + kok.yukseklik + ", " + 
					(kok.solCocuk == null ? null : kok.solCocuk.anahtar) + ", " + 
					(kok.sagCocuk == null ? null : kok.sagCocuk.anahtar) + "} ");  // Düğümün verisini yazdır
			kokBastaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokBastaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}

	void kokOrtadaDolas(AvlAgacDugum kok) {
		if (kok != null) {
			kokOrtadaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			System.out.print("{" + kok.anahtar + ", " + kok.yukseklik + ", " + 
					(kok.solCocuk == null ? null : kok.solCocuk.anahtar) + ", " + 
					(kok.sagCocuk == null ? null : kok.sagCocuk.anahtar) + "} ");  // Düğümün verisini yazdır
			kokOrtadaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
		}
	}

	void kokSondaDolas(AvlAgacDugum kok) {
		if (kok != null) {
			kokSondaDolas(kok.solCocuk);    // Sol çocuğu ziyaret et
			kokSondaDolas(kok.sagCocuk);    // Sağ çocuğu ziyaret et
			System.out.print("{" + kok.anahtar + ", " + kok.yukseklik + ", " + 
					(kok.solCocuk == null ? null : kok.solCocuk.anahtar) + ", " + 
					(kok.sagCocuk == null ? null : kok.sagCocuk.anahtar) + "} ");  // Düğümün verisini yazdır
		}
	}

	public static void main(String[] args) {
		AvlAgac agac = new AvlAgac();

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

		agac.sil(60);
		agac.sil(50);
		agac.sil(30);

		agac.ara(60);

		agac.kokOrtadaDolas(agac.kok);
		System.out.println();
	}
}