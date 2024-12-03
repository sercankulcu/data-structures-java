// Dugum sinifi - Tek yonlu bagli liste dugumlerini temsil eden ic sinif.
class TekYonluDugum<E> {
	
	E veri;      // Dugumun icinde saklanan veri.
	TekYonluDugum<E> sonraki;  // Dugumun bir sonraki dugumune isaret eden referans.

	TekYonluDugum(E veri) {
		this.veri = veri;
		this.sonraki = null;
	}
}

public class TekYonluBagliListe {

	TekYonluDugum<Integer> bas;  // Bagli listenin baslangic dugumunu temsil eden referans.

	// Tek Yonlu Bagli Liste Baslatma - Bagli listenin baslangic durumunu olusturan constructor.
	TekYonluBagliListe() {
		bas = null;  // Baslangicta bagli liste bos olacak.
	}

	//Liste Basina Dugum Ekleme - Verilen veriyi liste basina ekler.
	void basaEkle(int veri) {
		// Yeni bir dugum olustur ve icine veriyi yerlestir.
		TekYonluDugum<Integer> yeniDugum = new TekYonluDugum<>(veri);

		// Yeni dugumun sonraki referansini mevcut bas dugume ayarla.
		yeniDugum.sonraki = bas;

		// Liste basini yeni dugume tasi.
		bas = yeniDugum;
	}


	//Liste Sonuna Dugum Ekleme - Verilen veriyi liste sonuna ekler.
	void sonaEkle(int veri) {
		// Yeni bir dugum olustur ve icine veriyi yerlestir.
		TekYonluDugum<Integer> yeniDugum = new TekYonluDugum<>(veri);

		// Eger liste bossa, yeni dugumu listenin basi olarak ayarla ve islemi bitir.
		if (bas == null) {
			bas = yeniDugum;
			return;
		}

		// Gecici bir dugum olustur ve bu dugumu bas dugumle baslat.
		TekYonluDugum<Integer> gecici = bas;

		// Gecici dugumu listenin sonuna gitmek icin sonraki dugumleri takip ederek ilerlet.
		while (gecici.sonraki != null) {
			gecici = gecici.sonraki;
		}

		// Listenin sonunda yeni dugumu ekleyerek sona ekleme islemini tamamla.
		gecici.sonraki = yeniDugum;
	}


	//Belirli Bir Konuma Dugum Ekleme - Verilen veriyi belirtilen konuma ekler.
	void konumaEkle(int veri, int konum) {
		// Yeni bir dugum olustur ve icine veriyi yerlestir.
		TekYonluDugum<Integer> yeniDugum = new TekYonluDugum<>(veri);

		// Eger konum 0 ise, yeni dugumu listenin basina ekleyerek islemi bitir.
		if (konum == 0) {
			yeniDugum.sonraki = bas;
			bas = yeniDugum;
			return;
		}

		// Gecici bir dugum olustur ve bu dugumu bas dugumle baslat.
		TekYonluDugum<Integer> gecici = bas;

		// Belirtilen konuma gitmek icin donguyu kullanarak gecici dugumu ilerlet.
		for (int i = 0; i < konum - 1; i++) {
			if (gecici == null) {
				System.out.println("Belirtilen konum bulunamadi.");
				return;
			}
			gecici = gecici.sonraki;
		}

		// Belirtilen konum bulunamazsa, bir hata mesaji yazdir ve islemi sonlandir.
		if (gecici == null) {
			System.out.println("Belirtilen konum bulunamadi.");
			return;
		}

		// Yeni dugumu belirtilen konuma ekleyerek islemi tamamla.
		yeniDugum.sonraki = gecici.sonraki;
		gecici.sonraki = yeniDugum;
	}


	//ilk Elemani Silme - Listenin basindaki dugumu siler.
	void ilkElemaniSil() {
		// Eger liste zaten bossa, bir hata mesaji yazdir ve islemi sonlandir.
		if (bas == null) {
			System.out.println("Liste zaten bos.");
			return;
		}

		// ilk dugumu silmeden once bu dugumu bir gecici degiskene kopyala.
		TekYonluDugum<Integer> silinecekDugum = bas;

		// Listenin basini bir sonraki dugume tasi.
		bas = bas.sonraki;

		// Silinen dugumun sonraki referansini temizle.
		silinecekDugum.sonraki = null;
	}


	//Son Elemani Silme - Listenin sonundaki dugumu siler.
	void sonElemaniSil() {
		// Eger liste zaten bossa, bir hata mesaji yazdir ve islemi sonlandir.
		if (bas == null) {
			System.out.println("Liste zaten bos.");
			return;
		}

		// Eger liste sadece bir eleman iceriyorsa, bu elemani sil ve islemi sonlandir.
		if (bas.sonraki == null) {
			bas = null;
			return;
		}

		// Gecici bir dugum olustur ve bu dugumu bas dugumle baslat.
		TekYonluDugum<Integer> gecici = bas;

		// Son dugume gitmek icin donguyu kullanarak gecici dugumu ilerlet.
		while (gecici.sonraki.sonraki != null) {
			gecici = gecici.sonraki;
		}

		// Son dugumun sonraki referansini temizleyerek son dugumu sil.
		gecici.sonraki = null;
	}


	//Belirli Bir Elemani Silme - Verilen veriye sahip dugumu listeden siler.
	void elemaniSil(int silinecekVeri) {
		// Eger liste zaten bossa, bir hata mesaji yazdir ve islemi sonlandir.
		if (bas == null) {
			System.out.println("Liste zaten bos.");
			return;
		}

		// Eger silinecek veri listenin basindaki dugumdeyse, bas dugumu guncelle ve islemi sonlandir.
		if (bas.veri == silinecekVeri) {
			bas = bas.sonraki;
			return;
		}

		// Gecici dugum ve onceki dugum olustur, gecici dugumu bas dugumun sonraki dugumuyle baslat.
		TekYonluDugum<Integer> onceki = bas;
		TekYonluDugum<Integer> gecici = bas.sonraki;

		// Verilen veriyi iceren dugumu aramak icin donguyu kullan.
		while (gecici != null) {
			if (gecici.veri == silinecekVeri) {
				// onceki dugumun sonraki referansini gecici dugumun sonraki dugumune ayarla ve islemi sonlandir.
				onceki.sonraki = gecici.sonraki;
				return;
			}

			// Dugumleri bir sonraki dugume tasi ve aramaya devam et.
			onceki = gecici;
			gecici = gecici.sonraki;
		}

		// Belirtilen veri bulunamazsa, bir hata mesaji yazdir.
		System.out.println("Belirtilen veri bulunamadi.");
	}


	//Belirli Bir Elemani Arama - Verilen veriyi arar ve konumunu veya bulunamadigini bildirir.
	boolean elemaniAra(int arananVeri) {
		// Gecici bir dugum olustur ve bas dugumle baslat, konumu sifirla.
		TekYonluDugum<Integer> gecici = bas;
		int konum = 0;

		// Liste boyunca dongu ile verilen veriyi ara.
		while (gecici != null) {
			konum++;
			if (gecici.veri == arananVeri) {
				// Belirtilen elemani bulursan, konumunu yazdir ve true dondur.
				System.out.println("Belirtilen eleman " + konum + " konumda bulundu.");
				return true;
			}
			// Gecici dugumu bir sonraki dugume tasi ve aramaya devam et.
			gecici = gecici.sonraki;
		}

		// Belirtilen elemani bulamazsan, bir hata mesaji yazdir ve false dondur.
		System.out.println("Belirtilen eleman bulunamadi.");
		return false;
	}


	//Liste Uzunlugunu Hesaplama - Bagli listenin uzunlugunu hesaplar ve dondurur.
	int listeUzunlugu() {
		int uzunluk = 0;  // Bagli listenin uzunlugunu saklayacak degisken.
		TekYonluDugum<Integer> gecici = bas;  // Gecici bir dugum olustur ve bas dugumle baslat.

		// Liste boyunca dongu ile dugumleri say ve uzunlugu hesapla.
		while (gecici != null) {
			uzunluk++;
			gecici = gecici.sonraki;
		}

		// Bagli listenin uzunlugunu yazdir ve degeri dondur.
		System.out.println("Bagli Liste Uzunlugu: " + uzunluk);
		return uzunluk;
	}


	//Liste uzerinde Dolasma ve Verileri Yazdirma - Bagli liste uzerinde dolasir ve verileri yazdirir.
	void listeyiYazdir() {
		TekYonluDugum<Integer> gecici = bas;  // Gecici bir dugum olustur ve bas dugumle baslat.
		System.out.print("Bagli Liste: ");  // Liste basligini yazdir.

		// Liste boyunca dongu ile dugumleri gezin ve verileri yazdirin.
		while (gecici != null) {
			System.out.print(gecici.veri + " -> ");  // Dugumun verisini yazdir.
			gecici = gecici.sonraki;  // Gecici dugumu bir sonraki dugume tasi.
		}

		// Liste sonunu belirtmek icin "null" yazdir.
		System.out.println("null");
	}


	//Liste Ters cevirme - Bagli listeyi ters cevirir.
	void listeyiTersCevir() {
		TekYonluDugum<Integer> onceki = null;  // onceki dugumu baslangicta null olarak ayarla.
		TekYonluDugum<Integer> suanki = bas;   // su anki dugumu bas dugumle baslat.
		TekYonluDugum<Integer> sonraki = null; // Sonraki dugumu baslangicta null olarak ayarla.

		// Listenin sonuna kadar dolasarak dugumleri ters cevir.
		while (suanki != null) {
			sonraki = suanki.sonraki;   // Sonraki dugumu sakla.
			suanki.sonraki = onceki;    // su anki dugumun sonraki referansini onceki dugume cevir.
			onceki = suanki;           // onceki dugumu su anki dugum yap.
			suanki = sonraki;           // su anki dugumu bir sonraki dugum yap.
		}

		bas = onceki;  // Listenin basini ters cevrilmis liste basi yap.
	}


	//Tek Yonlu Bagli Listede Orta Elemani Bulma
	int ortaElemaniBul() {
		if (bas == null) {
			return -1; // Liste bossa, -1 dondur
		}

		TekYonluDugum<Integer> yavas = bas;   // Yavas pointer bas dugumle baslar
		TekYonluDugum<Integer> hizli = bas;   // Hizli pointer da bas dugumle baslar

		// Hizli pointerin sonuna ulasana kadar yavas pointeri bir adim, hizli pointeri iki adim ilerlet
		while (hizli != null && hizli.sonraki != null) {
			yavas = yavas.sonraki;         // Yavas pointer bir adim ilerler
			hizli = hizli.sonraki.sonraki; // Hizli pointer iki adim ilerler
		}

		System.out.println("Orta Eleman: " + yavas.veri); // Orta elemani yazdir
		return yavas.veri;  // Orta elemani dondur
	}


	//Tek Yonlu Bagli Listeyi Kopyalama
	TekYonluBagliListe kopyala() {
		TekYonluBagliListe kopyaListe = new TekYonluBagliListe(); // Yeni bir bagli liste olustur
		TekYonluDugum<Integer> gecici = bas; // Gecici bir dugum olustur ve bas dugumle baslat

		while (gecici != null) {
			kopyaListe.sonaEkle(gecici.veri); // Gecici dugumun verisini kopya listeye ekle
			gecici = gecici.sonraki; // Bir sonraki dugume gec
		}

		kopyaListe.listeyiYazdir(); // Kopya listeyi yazdir
		return kopyaListe; // Kopya listeyi dondur
	}


	//Tek Yonlu Bagli Listeden Tekrarlayanlari Kaldirma 
	void tekrarlayanlariKaldir() {
		if (bas == null) {
			return; // Liste bossa islemi sonlandir
		}

		TekYonluBagliListe kume = new TekYonluBagliListe(); // Bir kume olustur, tekrarlayanlari kontrol etmek icin
		TekYonluDugum<Integer> onceki = null; // onceki dugumu saklamak icin kullanilan gecici degisken
		TekYonluDugum<Integer> gecici = bas;  // Gecici bir dugum olustur ve bas dugumle baslat

		while (gecici != null) {
			int veri = gecici.veri; // su anki dugumun verisini al
			if (kume.elemaniAra(veri)) {
				// Eger kumede eklenen veriyi bulursan, tekrarlayan elemani pas gec
				onceki.sonraki = gecici.sonraki;
			} else {
				kume.sonaEkle(veri); // Eger tekrarlayan degilse, kumeye veriyi ekle
				onceki = gecici;
			}
			gecici = gecici.sonraki; // Bir sonraki dugume gec
		}
	}


	//iki Sirali Listeyi Birlestirme
	static TekYonluBagliListe birlestir(TekYonluBagliListe liste1, TekYonluBagliListe liste2) {
		TekYonluBagliListe birlesmisListe = new TekYonluBagliListe(); // Birlesmis liste olustur
		TekYonluDugum<Integer> gecici1 = liste1.bas; // ilk liste icin gecici dugum
		TekYonluDugum<Integer> gecici2 = liste2.bas; // ikinci liste icin gecici dugum

		// iki listeyi karsilastirarak birlestir
		while (gecici1 != null && gecici2 != null) {
			if (gecici1.veri < gecici2.veri) {
				birlesmisListe.sonaEkle(gecici1.veri);
				gecici1 = gecici1.sonraki;
			} else {
				birlesmisListe.sonaEkle(gecici2.veri);
				gecici2 = gecici2.sonraki;
			}
		}

		// Eger bir liste sona erdiyse, diger listeyi dogrudan birlesmis liste sonuna ekle
		while (gecici1 != null) {
			birlesmisListe.sonaEkle(gecici1.veri);
			gecici1 = gecici1.sonraki;
		}

		while (gecici2 != null) {
			birlesmisListe.sonaEkle(gecici2.veri);
			gecici2 = gecici2.sonraki;
		}

		return birlesmisListe; // Birlesmis listeyi dondur
	}


	//Tek Yonlu Bagli Listeyi Siralama
	void sirala() {
		if (bas == null) {
			return; // Liste bossa siralama yapmaya gerek yok
		}

		TekYonluDugum<Integer> suanki = bas; // su anki dugumu bas dugumle baslat

		while (suanki != null) {
			TekYonluDugum<Integer> diger = suanki.sonraki; // Diger dugumu su anki dugumun bir sonraki dugumu olarak baslat

			while (diger != null) {
				if (suanki.veri > diger.veri) {
					// Eger su anki dugumun verisi diger dugumun verisinden buyukse, elemanlari degistir
					int gecici = suanki.veri;
					suanki.veri = diger.veri;
					diger.veri = gecici;
				}
				diger = diger.sonraki; // Diger dugumu bir sonraki dugum yap
			}
			suanki = suanki.sonraki; // su anki dugumu bir sonraki dugum yap
		}
	}

	public static void main(String[] args) {
		
		TekYonluBagliListe liste = new TekYonluBagliListe();
		
		// Dugumleri ekle
		liste.sonaEkle(1);
		liste.sonaEkle(2);
		liste.sonaEkle(3);
		liste.sonaEkle(4);
		liste.listeyiYazdir();  //Bagli Liste: 1 -> 2 -> 3 -> 4 -> null
		
		liste.konumaEkle(5, 0);
		liste.listeyiYazdir(); //Bagli Liste: 5 -> 1 -> 2 -> 3 -> 4 -> null
		
		liste.basaEkle(7);
		liste.listeyiYazdir(); //Bagli Liste: 7 -> 5 -> 1 -> 2 -> 3 -> 4 -> null
		
		liste.konumaEkle(6, 2);
		liste.listeyiYazdir(); //Bagli Liste: 7 -> 5 -> 6 -> 1 -> 2 -> 3 -> 4 -> null
		
		liste.ortaElemaniBul(); //Orta Eleman: 1
		
		liste.listeUzunlugu(); //Bagli Liste Uzunlugu: 7
		
		liste.ilkElemaniSil();
		liste.listeyiYazdir(); //Bagli Liste: 5 -> 6 -> 1 -> 2 -> 3 -> 4 -> null
		
		liste.sonElemaniSil();
		liste.listeyiYazdir(); //Bagli Liste: 5 -> 6 -> 1 -> 2 -> 3 -> null
		
		liste.elemaniSil(3);
		liste.listeyiYazdir(); //Bagli Liste: 5 -> 6 -> 1 -> 2 -> null

		liste.elemaniAra(2); //Belirtilen eleman 4 konumda bulundu.
		liste.elemaniAra(11); //Belirtilen eleman bulunamadi.

		liste.listeyiTersCevir();
		liste.listeyiYazdir(); //Bagli Liste: 2 -> 1 -> 6 -> 5 -> null

		liste.kopyala();
		
		liste.sonaEkle(6);
		liste.listeyiYazdir(); //Bagli Liste: 2 -> 1 -> 6 -> 5 -> 6 -> null
		
		liste.tekrarlayanlariKaldir();
		liste.listeyiYazdir(); //Bagli Liste: 2 -> 1 -> 6 -> 5 -> null
		
		liste.sirala();
		liste.listeyiYazdir(); //Bagli Liste: 1 -> 2 -> 5 -> 6 -> null

		// ilk listeye dugumleri ekleyelim
		TekYonluBagliListe liste1 = new TekYonluBagliListe();
		liste1.sonaEkle(1);
		liste1.sonaEkle(3);
		liste1.sonaEkle(5);

		// ikinci listeye dugumleri ekleyelim
		TekYonluBagliListe liste2 = new TekYonluBagliListe();
		liste2.sonaEkle(2);
		liste2.sonaEkle(4);

		// iki sirali listeyi birlestirelim
		TekYonluBagliListe birlesmisListe = birlestir(liste1, liste2);

		// Birlestirilmis listeyi yazdiralim
		birlesmisListe.listeyiYazdir(); //Bagli Liste: 1 -> 2 -> 3 -> 4 -> 5 -> null
	}
}
