
// Cift yonlu bagli listenin dugumunu temsil eden sinif 
class CiftYonluDugum<E> {
	E veri;               // Dugumun icerdigi veriyi temsil eden degisken.
	CiftYonluDugum<E> onceki;  // Onceki dugumun referansini saklayan degisken.
	CiftYonluDugum<E> sonraki; // Sonraki dugumun referansini saklayan degisken.

	// Dugum sinifinin kurucu fonksiyonu
	CiftYonluDugum(E veri) {
		this.veri = veri;    // Dugumun veri alanina veriyi ata
		this.onceki = null;  // Onceki dugum baslangicta null olarak ayarla
		this.sonraki = null; // Sonraki dugum baslangicta null olarak ayarla
	}
}

public class CiftYonluBagliListe {

	private CiftYonluDugum<Integer> bas; // Bas dugumunu saklayan degisken
	private CiftYonluDugum<Integer> son; // Son dugumu saklayan degisken

	// cift yonlu bagli listenin baslatilmasi
	public CiftYonluBagliListe() {
		bas = null;
		son = null;
	}


	//Listenin basina dugum ekleme
	public void basaEkle(int veri) {
		CiftYonluDugum<Integer> yeniDugum = new CiftYonluDugum<Integer>(veri); // Eklenecek yeni dugumu olustur

		if (bas == null) {
			// Liste bossa, yeni dugum hem basi hem de sonu temsil eder
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Liste bos degilse, yeni dugumu listenin basina ekler
			yeniDugum.sonraki = bas; // Yeni dugumun sonraki referansini eski bas dugum yap
			bas.onceki = yeniDugum; // Eski bas dugumun onceki referansini yeni dugum yap
			bas = yeniDugum; // Yeni dugumu bas dugum olarak ayarla
		}
	}


	//Listenin sonuna dugum ekleme
	public void sonaEkle(int veri) {
		CiftYonluDugum<Integer> yeniDugum = new CiftYonluDugum<>(veri); // Eklenecek yeni dugumu olustur

		if (bas == null) {
			// Liste bossa, yeni dugum hem basi hem de sonu temsil eder
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Liste bos degilse, yeni dugumu listenin sonuna ekler
			yeniDugum.onceki = son; // Yeni dugumun onceki referansini eski son dugum yap
			son.sonraki = yeniDugum; // Eski son dugumun sonraki referansini yeni dugum yap
			son = yeniDugum; // Yeni dugumu son dugum olarak ayarla
		}
	}


	//Belirli bir konuma eleman ekleme
	void konumaEkle(int veri, int konum) {
		CiftYonluDugum<Integer> yeniDugum = new CiftYonluDugum<>(veri); // Eklenecek yeni dugumu olustur

		if (konum <= 1) {
			// Konum 1 veya daha kucukse, yeni dugumu listenin basina ekle
			yeniDugum.sonraki = bas; // Yeni dugumun sonraki referansini eski bas dugum yap
			bas.onceki = yeniDugum; // Eski bas dugumun onceki referansini yeni dugum yap
			bas = yeniDugum; // Yeni dugumu bas dugum olarak ayarla
		} else {
			CiftYonluDugum<Integer> gecici = bas; // Gecici bir dugum olustur ve bas dugumle baslat
			int adim = 1;

			while (gecici != null && adim < konum - 1) {
				gecici = gecici.sonraki; // istenilen konuma kadar ilerle
				adim++;
			}

			if (gecici == null) {
				System.out.println("Gecersiz konum. Eleman eklenemedi.");
			} else {
				// Yeni dugumu istenen konuma ekler
				yeniDugum.sonraki = gecici.sonraki; // Yeni dugumun sonraki referansini gecici'nin sonraki dugum yap
				yeniDugum.onceki = gecici; // Yeni dugumun onceki referansini gecici yap
				if (gecici.sonraki != null) {
					gecici.sonraki.onceki = yeniDugum; // Gecici'nin sonraki dugumunun onceki referansini yeni dugum yap
				}
				gecici.sonraki = yeniDugum; // Gecici'nin sonraki dugumunu yeni dugum yap
			}
		}
	}


	//cift yonlu bagli listenin uzunlugunu bulma
	int listeUzunlugu() {
		CiftYonluDugum<Integer> gecici = bas; // Gecici bir dugum olustur ve bas dugumle baslat
		int uzunluk = 0; // Listenin uzunlugunu saklayan degisken

		while (gecici != null) {
			uzunluk++; // Her dongu adiminda uzunlugu bir artir
			gecici = gecici.sonraki; // Bir sonraki dugume gec
		}

		System.out.println("Liste Uzunlugu: " + uzunluk); // Uzunlugu ekrana yazdir
		return uzunluk; // Uzunlugu dondur
	}


	//Listenin basindaki ilk elemani silme
	void ilkElemaniSil() {
		if (bas == null) {
			System.out.println("Liste bos. Silme islemi yapilamaz.");
			return; // Liste bossa, silme islemi yapilamaz
		}

		if (bas.sonraki != null) {
			// Eger liste birden fazla eleman iceriyorsa, ilk elemani sil
			bas = bas.sonraki; // Bas dugumunu bir sonraki dugum yap
			bas.onceki = null; // Yeni bas dugumun onceki referansini null yap
		} else {
			bas = null; // Eger liste sadece bir eleman iceriyorsa, bas dugumu null yap
		}
	}


	//Listenin sonundaki son elemani silme
	void sonElemaniSil() {
		if (bas == null) {
			System.out.println("Liste bos. Silme islemi yapilamaz.");
			return; // Liste bossa, silme islemi yapilamaz
		}

		if (bas.sonraki == null) {
			// Eger liste sadece bir eleman iceriyorsa, bas dugumu null yap
			bas = null;
		} else {
			// Eger liste birden fazla eleman iceriyorsa, son elemani sil
			CiftYonluDugum<Integer> gecici = bas; // Gecici bir dugum olustur ve bas dugumle baslat

			while (gecici.sonraki != null) {
				gecici = gecici.sonraki; // Son elemana kadar ilerle
			}

			gecici.onceki.sonraki = null; // Son elemanin onceki dugumunun sonraki referansini null yap
		}
	}


	//Belirli bir elemani silme
	void elemaniSil(int veri) {
		if (bas == null) {
			System.out.println("Liste bos. Silme islemi yapilamaz.");
			return; // Liste bossa, silme islemi yapilamaz
		}

		if (bas.veri == veri) {
			// Eger silinecek eleman liste basindaysa
			bas = bas.sonraki; // Bas dugumunu bir sonraki dugum yap
			if (bas != null) {
				bas.onceki = null; // Yeni bas dugumun onceki referansini null yap
			}
			return;
		}

		CiftYonluDugum<Integer> gecici = bas; // Gecici bir dugum olustur ve bas dugumle baslat

		while (gecici != null && gecici.veri != veri) {
			gecici = gecici.sonraki; // Silinecek elemani bulana kadar ilerle
		}

		if (gecici == null) {
			System.out.println("Belirtilen eleman listede bulunamadi.");
			return; // Silinecek eleman listede bulunamazsa, islemi sonlandir
		}

		if (gecici.sonraki != null) {
			gecici.onceki.sonraki = gecici.sonraki; // Silinen elemanin onceki dugumunun sonraki referansini ayarla
			gecici.sonraki.onceki = gecici.onceki; // Silinen elemanin sonraki dugumunun onceki referansini ayarla
		} else {
			gecici.onceki.sonraki = null; // Eger silinen eleman listenin son elemaniysa, sonraki referansi null yap
		}
	}


	//cift yonlu bagli listeyi ileri yonde yazdirma
	void listeyiIleriYazdir() {
		CiftYonluDugum<Integer> gecici = bas; // Gecici bir dugum olustur ve bas dugumle baslat

		System.out.print("ileri Yonde: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " <-> "); // Dugumun verisini yazdir ve cift yonlu baglantiyi goster
			gecici = gecici.sonraki; // Bir sonraki dugume gec
		}
		System.out.println("null"); // Listenin sonunu belirtmek icin "null" yazdir
	}


	//cift yonlu bagli listeyi geri yonde yazdirma
	void listeyiGeriYazdir() {
		CiftYonluDugum<Integer> gecici = bas;

		// En son elemana ilerle
		while (gecici.sonraki != null) {
			gecici = gecici.sonraki;
		}

		System.out.print("Geri Yonde: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " <-> "); // Dugumun verisini yazdir ve cift yonlu baglantiyi goster
			gecici = gecici.onceki; // Bir onceki dugume gec
		}
		System.out.println("null"); // Listenin sonunu belirtmek icin "null" yazdir
	}


	//Belirli bir elemani arama
	boolean elemaniAra(int hedef) {
		CiftYonluDugum<Integer> gecici = bas; // Gecici bir dugum olustur ve bas dugumle baslat
		int konum = 0; // Elemanin konumunu izlemek icin bir degisken

		while (gecici != null) {
			konum++; // Her dongu adiminda konumu artir
			if (gecici.veri == hedef) {
				System.out.println("Belirtilen eleman " + konum + " konumda bulundu."); // Eleman bulundugunda konumu bildir
				return true; // Eleman bulundugunda true dondur
			}
			gecici = gecici.sonraki; // Bir sonraki dugume gec
		}

		System.out.println("Belirtilen eleman bulunamadi."); // Eleman bulunamazsa bildir
		return false; // Eleman bulunamazsa false dondur
	}

	CiftYonluBagliListe donustur(TekYonluBagliListe liste1) {
		// Çift yönlü bağlı listeyi oluştur
		CiftYonluBagliListe liste2 = new CiftYonluBagliListe();

		// İlk düğümü işaret et
		TekYonluDugum<Integer> gecici = liste1.bas;

		// Tek yönlü listedeki her düğümü çift yönlü listeye ekleyin
		while (gecici != null) {
			liste2.sonaEkle(gecici.veri); // Çift yönlü listeye düğümü ekle
			gecici = gecici.sonraki; // Tek yönlü listede bir sonraki düğüme geç
		}

		return liste2; // Dönüştürülmüş çift yönlü listeyi döndür
	}

	public static void main(String[] args) {

		CiftYonluBagliListe liste = new CiftYonluBagliListe();

		liste.basaEkle(30);
		liste.basaEkle(20);
		liste.sonaEkle(40);
		liste.listeyiIleriYazdir(); //ileri Yonde: 20 <-> 30 <-> 40 <-> null

		liste.konumaEkle(50, 2);
		liste.listeyiIleriYazdir(); //ileri Yonde: 20 <-> 50 <-> 30 <-> 40 <-> null
		liste.listeyiGeriYazdir(); //Geri Yonde: 40 <-> 30 <-> 50 <-> 20 <-> null

		liste.listeUzunlugu(); //Liste Uzunlugu: 4

		liste.elemaniAra(30); //Belirtilen eleman 3 konumda bulundu.
		liste.elemaniAra(70); //Belirtilen eleman bulunamadi.

		liste.ilkElemaniSil();
		liste.listeyiIleriYazdir(); //ileri Yonde: 50 <-> 30 <-> 40 <-> null

		liste.sonElemaniSil();
		liste.listeyiIleriYazdir(); //ileri Yonde: 50 <-> 30 <-> null

		liste.elemaniSil(30);
		liste.listeyiIleriYazdir(); //ileri Yonde: 50 <-> null

		liste.elemaniSil(20); //Belirtilen eleman listede bulunamadi.
		liste.listeyiIleriYazdir(); //ileri Yonde: 50 <-> null
		
		
		TekYonluBagliListe tekli = new TekYonluBagliListe();

		tekli.basaEkle(5);
		tekli.sonaEkle(11);
		tekli.basaEkle(7);
		tekli.sonaEkle(17);

		CiftYonluBagliListe liste2 = new CiftYonluBagliListe();
		liste2 = liste2.donustur(tekli);

		tekli.listeyiYazdir();
		liste2.listeyiIleriYazdir();
	}
}
