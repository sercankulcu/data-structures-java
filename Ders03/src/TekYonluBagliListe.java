
public class TekYonluBagliListe {

	TekYonluDugum<Integer> bas;  // Bağlı listenin başlangıç düğümünü temsil eden referans.

	// Tek Yönlü Bağlı Liste Başlatma - Bağlı listenin başlangıç durumunu oluşturan constructor.
	TekYonluBagliListe() {
		bas = null;  // Başlangıçta bağlı liste boş olacak.
	}

	//Liste Başına Düğüm Ekleme - Verilen veriyi liste başına ekler.
	void basaEkle(int veri) {
		// Yeni bir düğüm oluştur ve içine veriyi yerleştir.
		TekYonluDugum<Integer> yeniDugum = new TekYonluDugum<>(veri);

		// Yeni düğümün sonraki referansını mevcut baş düğüme ayarla.
		yeniDugum.sonraki = bas;

		// Liste başını yeni düğüme taşı.
		bas = yeniDugum;
	}


	//Liste Sonuna Düğüm Ekleme - Verilen veriyi liste sonuna ekler.
	void sonaEkle(int veri) {
		// Yeni bir düğüm oluştur ve içine veriyi yerleştir.
		TekYonluDugum<Integer> yeniDugum = new TekYonluDugum<>(veri);

		// Eğer liste boşsa, yeni düğümü listenin başı olarak ayarla ve işlemi bitir.
		if (bas == null) {
			bas = yeniDugum;
			return;
		}

		// Geçici bir düğüm oluştur ve bu düğümü baş düğümle başlat.
		TekYonluDugum<Integer> gecici = bas;

		// Geçici düğümü listenin sonuna gitmek için sonraki düğümleri takip ederek ilerlet.
		while (gecici.sonraki != null) {
			gecici = gecici.sonraki;
		}

		// Listenin sonunda yeni düğümü ekleyerek sona ekleme işlemini tamamla.
		gecici.sonraki = yeniDugum;
	}


	//Belirli Bir Konuma Düğüm Ekleme - Verilen veriyi belirtilen konuma ekler.
	void konumaEkle(int veri, int konum) {
		// Yeni bir düğüm oluştur ve içine veriyi yerleştir.
		TekYonluDugum<Integer> yeniDugum = new TekYonluDugum<>(veri);

		// Eğer konum 0 ise, yeni düğümü listenin başına ekleyerek işlemi bitir.
		if (konum == 0) {
			yeniDugum.sonraki = bas;
			bas = yeniDugum;
			return;
		}

		// Geçici bir düğüm oluştur ve bu düğümü baş düğümle başlat.
		TekYonluDugum<Integer> gecici = bas;

		// Belirtilen konuma gitmek için döngüyü kullanarak geçici düğümü ilerlet.
		for (int i = 0; i < konum - 1; i++) {
			if (gecici == null) {
				System.out.println("Belirtilen konum bulunamadı.");
				return;
			}
			gecici = gecici.sonraki;
		}

		// Belirtilen konum bulunamazsa, bir hata mesajı yazdır ve işlemi sonlandır.
		if (gecici == null) {
			System.out.println("Belirtilen konum bulunamadı.");
			return;
		}

		// Yeni düğümü belirtilen konuma ekleyerek işlemi tamamla.
		yeniDugum.sonraki = gecici.sonraki;
		gecici.sonraki = yeniDugum;
	}


	//İlk Elemanı Silme - Listenin başındaki düğümü siler.
	void ilkElemaniSil() {
		// Eğer liste zaten boşsa, bir hata mesajı yazdır ve işlemi sonlandır.
		if (bas == null) {
			System.out.println("Liste zaten boş.");
			return;
		}

		// İlk düğümü silmeden önce bu düğümü bir geçici değişkene kopyala.
		TekYonluDugum<Integer> silinecekDugum = bas;

		// Listenin başını bir sonraki düğüme taşı.
		bas = bas.sonraki;

		// Silinen düğümün sonraki referansını temizle.
		silinecekDugum.sonraki = null;
	}


	//Son Elemanı Silme - Listenin sonundaki düğümü siler.
	void sonElemaniSil() {
		// Eğer liste zaten boşsa, bir hata mesajı yazdır ve işlemi sonlandır.
		if (bas == null) {
			System.out.println("Liste zaten boş.");
			return;
		}

		// Eğer liste sadece bir eleman içeriyorsa, bu elemanı sil ve işlemi sonlandır.
		if (bas.sonraki == null) {
			bas = null;
			return;
		}

		// Geçici bir düğüm oluştur ve bu düğümü baş düğümle başlat.
		TekYonluDugum<Integer> gecici = bas;

		// Son düğüme gitmek için döngüyü kullanarak geçici düğümü ilerlet.
		while (gecici.sonraki.sonraki != null) {
			gecici = gecici.sonraki;
		}

		// Son düğümün sonraki referansını temizleyerek son düğümü sil.
		gecici.sonraki = null;
	}


	//Belirli Bir Elemanı Silme - Verilen veriye sahip düğümü listeden siler.
	void elemaniSil(int silinecekVeri) {
		// Eğer liste zaten boşsa, bir hata mesajı yazdır ve işlemi sonlandır.
		if (bas == null) {
			System.out.println("Liste zaten boş.");
			return;
		}

		// Eğer silinecek veri listenin başındaki düğümdeyse, baş düğümü güncelle ve işlemi sonlandır.
		if (bas.veri == silinecekVeri) {
			bas = bas.sonraki;
			return;
		}

		// Geçici düğüm ve önceki düğüm oluştur, geçici düğümü baş düğümün sonraki düğümüyle başlat.
		TekYonluDugum<Integer> onceki = bas;
		TekYonluDugum<Integer> gecici = bas.sonraki;

		// Verilen veriyi içeren düğümü aramak için döngüyü kullan.
		while (gecici != null) {
			if (gecici.veri == silinecekVeri) {
				// Önceki düğümün sonraki referansını gecici düğümün sonraki düğümüne ayarla ve işlemi sonlandır.
				onceki.sonraki = gecici.sonraki;
				return;
			}

			// Düğümleri bir sonraki düğüme taşı ve aramaya devam et.
			onceki = gecici;
			gecici = gecici.sonraki;
		}

		// Belirtilen veri bulunamazsa, bir hata mesajı yazdır.
		System.out.println("Belirtilen veri bulunamadı.");
	}


	//Belirli Bir Elemanı Arama - Verilen veriyi arar ve konumunu veya bulunamadığını bildirir.
	boolean elemaniAra(int arananVeri) {
		// Geçici bir düğüm oluştur ve baş düğümle başlat, konumu sıfırla.
		TekYonluDugum<Integer> gecici = bas;
		int konum = 0;

		// Liste boyunca döngü ile verilen veriyi ara.
		while (gecici != null) {
			konum++;
			if (gecici.veri == arananVeri) {
				// Belirtilen elemanı bulursan, konumunu yazdır ve true döndür.
				System.out.println("Belirtilen eleman " + konum + " konumda bulundu.");
				return true;
			}
			// Geçici düğümü bir sonraki düğüme taşı ve aramaya devam et.
			gecici = gecici.sonraki;
		}

		// Belirtilen elemanı bulamazsan, bir hata mesajı yazdır ve false döndür.
		System.out.println("Belirtilen eleman bulunamadı.");
		return false;
	}


	//Liste Uzunluğunu Hesaplama - Bağlı listenin uzunluğunu hesaplar ve döndürür.
	int listeUzunlugu() {
		int uzunluk = 0;  // Bağlı listenin uzunluğunu saklayacak değişken.
		TekYonluDugum<Integer> gecici = bas;  // Geçici bir düğüm oluştur ve baş düğümle başlat.

		// Liste boyunca döngü ile düğümleri say ve uzunluğu hesapla.
		while (gecici != null) {
			uzunluk++;
			gecici = gecici.sonraki;
		}

		// Bağlı listenin uzunluğunu yazdır ve değeri döndür.
		System.out.println("Bağlı Liste Uzunluğu: " + uzunluk);
		return uzunluk;
	}


	//Liste Üzerinde Dolaşma ve Verileri Yazdırma - Bağlı liste üzerinde dolaşır ve verileri yazdırır.
	void listeyiYazdir() {
		TekYonluDugum<Integer> gecici = bas;  // Geçici bir düğüm oluştur ve baş düğümle başlat.
		System.out.print("Bağlı Liste: ");  // Liste başlığını yazdır.

		// Liste boyunca döngü ile düğümleri gezin ve verileri yazdırın.
		while (gecici != null) {
			System.out.print(gecici.veri + " -> ");  // Düğümün verisini yazdır.
			gecici = gecici.sonraki;  // Geçici düğümü bir sonraki düğüme taşı.
		}

		// Liste sonunu belirtmek için "null" yazdır.
		System.out.println("null");
	}


	//Liste Ters Çevirme - Bağlı listeyi ters çevirir.
	void listeyiTersCevir() {
		TekYonluDugum<Integer> onceki = null;  // Önceki düğümü başlangıçta null olarak ayarla.
		TekYonluDugum<Integer> suanki = bas;   // Şu anki düğümü baş düğümle başlat.
		TekYonluDugum<Integer> sonraki = null; // Sonraki düğümü başlangıçta null olarak ayarla.

		// Listenin sonuna kadar dolaşarak düğümleri ters çevir.
		while (suanki != null) {
			sonraki = suanki.sonraki;   // Sonraki düğümü sakla.
			suanki.sonraki = onceki;    // Şu anki düğümün sonraki referansını önceki düğüme çevir.
			onceki = suanki;           // Önceki düğümü şu anki düğüm yap.
			suanki = sonraki;           // Şu anki düğümü bir sonraki düğüm yap.
		}

		bas = onceki;  // Listenin başını ters çevrilmiş liste başı yap.
	}


	//Tek Yönlü Bağlı Listede Orta Elemanı Bulma
	int ortaElemaniBul() {
		if (bas == null) {
			return -1; // Liste boşsa, -1 döndür
		}

		TekYonluDugum<Integer> yavas = bas;   // Yavaş pointer baş düğümle başlar
		TekYonluDugum<Integer> hizli = bas;   // Hızlı pointer da baş düğümle başlar

		// Hızlı pointerın sonuna ulaşana kadar yavaş pointerı bir adım, hızlı pointerı iki adım ilerlet
		while (hizli != null && hizli.sonraki != null) {
			yavas = yavas.sonraki;         // Yavaş pointer bir adım ilerler
			hizli = hizli.sonraki.sonraki; // Hızlı pointer iki adım ilerler
		}

		System.out.println("Orta Eleman: " + yavas.veri); // Orta elemanı yazdır
		return yavas.veri;  // Orta elemanı döndür
	}


	//Tek Yönlü Bağlı Listeyi Kopyalama
	TekYonluBagliListe kopyala() {
		TekYonluBagliListe kopyaListe = new TekYonluBagliListe(); // Yeni bir bağlı liste oluştur
		TekYonluDugum<Integer> gecici = bas; // Geçici bir düğüm oluştur ve baş düğümle başlat

		while (gecici != null) {
			kopyaListe.sonaEkle(gecici.veri); // Geçici düğümün verisini kopya listeye ekle
			gecici = gecici.sonraki; // Bir sonraki düğüme geç
		}

		kopyaListe.listeyiYazdir(); // Kopya listeyi yazdır
		return kopyaListe; // Kopya listeyi döndür
	}


	//Tek Yönlü Bağlı Listeden Tekrarlayanları Kaldırma 
	void tekrarlayanlariKaldir() {
		if (bas == null) {
			return; // Liste boşsa işlemi sonlandır
		}

		TekYonluBagliListe kume = new TekYonluBagliListe(); // Bir küme oluştur, tekrarlayanları kontrol etmek için
		TekYonluDugum<Integer> onceki = null; // Önceki düğümü saklamak için kullanılan geçici değişken
		TekYonluDugum<Integer> gecici = bas;  // Geçici bir düğüm oluştur ve baş düğümle başlat

		while (gecici != null) {
			int veri = gecici.veri; // Şu anki düğümün verisini al
			if (kume.elemaniAra(veri)) {
				// Eğer kümede eklenen veriyi bulursan, tekrarlayan elemanı pas geç
				onceki.sonraki = gecici.sonraki;
			} else {
				kume.sonaEkle(veri); // Eğer tekrarlayan değilse, kümeye veriyi ekle
				onceki = gecici;
			}
			gecici = gecici.sonraki; // Bir sonraki düğüme geç
		}
	}


	//İki Sıralı Listeyi Birleştirme
	static TekYonluBagliListe birlestir(TekYonluBagliListe liste1, TekYonluBagliListe liste2) {
		TekYonluBagliListe birlesmisListe = new TekYonluBagliListe(); // Birleşmiş liste oluştur
		TekYonluDugum<Integer> gecici1 = liste1.bas; // İlk liste için geçici düğüm
		TekYonluDugum<Integer> gecici2 = liste2.bas; // İkinci liste için geçici düğüm

		// İki listeyi karşılaştırarak birleştir
		while (gecici1 != null && gecici2 != null) {
			if (gecici1.veri < gecici2.veri) {
				birlesmisListe.sonaEkle(gecici1.veri);
				gecici1 = gecici1.sonraki;
			} else {
				birlesmisListe.sonaEkle(gecici2.veri);
				gecici2 = gecici2.sonraki;
			}
		}

		// Eğer bir liste sona erdiyse, diğer listeyi doğrudan birleşmiş liste sonuna ekle
		while (gecici1 != null) {
			birlesmisListe.sonaEkle(gecici1.veri);
			gecici1 = gecici1.sonraki;
		}

		while (gecici2 != null) {
			birlesmisListe.sonaEkle(gecici2.veri);
			gecici2 = gecici2.sonraki;
		}

		return birlesmisListe; // Birleşmiş listeyi döndür
	}


	//Tek Yönlü Bağlı Listeyi Sıralama
	void sirala() {
		if (bas == null) {
			return; // Liste boşsa sıralama yapmaya gerek yok
		}

		TekYonluDugum<Integer> suanki = bas; // Şu anki düğümü baş düğümle başlat

		while (suanki != null) {
			TekYonluDugum<Integer> diger = suanki.sonraki; // Diğer düğümü şu anki düğümün bir sonraki düğümü olarak başlat

			while (diger != null) {
				if (suanki.veri > diger.veri) {
					// Eğer şu anki düğümün verisi diğer düğümün verisinden büyükse, elemanları değiştir
					int gecici = suanki.veri;
					suanki.veri = diger.veri;
					diger.veri = gecici;
				}
				diger = diger.sonraki; // Diğer düğümü bir sonraki düğüm yap
			}
			suanki = suanki.sonraki; // Şu anki düğümü bir sonraki düğüm yap
		}
	}

	public static void main(String[] args) {
		
		TekYonluBagliListe liste = new TekYonluBagliListe();
		
		// Düğümleri ekle
		liste.sonaEkle(1);
		liste.sonaEkle(2);
		liste.sonaEkle(3);
		liste.sonaEkle(4);
		liste.listeyiYazdir();  //Bağlı Liste: 1 -> 2 -> 3 -> 4 -> null
		
		liste.konumaEkle(5, 0);
		liste.listeyiYazdir(); //Bağlı Liste: 5 -> 1 -> 2 -> 3 -> 4 -> null
		
		liste.basaEkle(7);
		liste.listeyiYazdir(); //Bağlı Liste: 7 -> 5 -> 1 -> 2 -> 3 -> 4 -> null
		
		liste.konumaEkle(6, 2);
		liste.listeyiYazdir(); //Bağlı Liste: 7 -> 5 -> 6 -> 1 -> 2 -> 3 -> 4 -> null
		
		liste.ortaElemaniBul(); //Orta Eleman: 1
		
		liste.listeUzunlugu(); //Bağlı Liste Uzunluğu: 7
		
		liste.ilkElemaniSil();
		liste.listeyiYazdir(); //Bağlı Liste: 5 -> 6 -> 1 -> 2 -> 3 -> 4 -> null
		
		liste.sonElemaniSil();
		liste.listeyiYazdir(); //Bağlı Liste: 5 -> 6 -> 1 -> 2 -> 3 -> null
		
		liste.elemaniSil(3);
		liste.listeyiYazdir(); //Bağlı Liste: 5 -> 6 -> 1 -> 2 -> null

		liste.elemaniAra(2); //Belirtilen eleman 4 konumda bulundu.
		liste.elemaniAra(11); //Belirtilen eleman bulunamadı.

		liste.listeyiTersCevir();
		liste.listeyiYazdir(); //Bağlı Liste: 2 -> 1 -> 6 -> 5 -> null

		liste.kopyala();
		
		liste.sonaEkle(6);
		liste.listeyiYazdir(); //Bağlı Liste: 2 -> 1 -> 6 -> 5 -> 6 -> null
		
		liste.tekrarlayanlariKaldir();
		liste.listeyiYazdir(); //Bağlı Liste: 2 -> 1 -> 6 -> 5 -> null
		
		liste.sirala();
		liste.listeyiYazdir(); //Bağlı Liste: 1 -> 2 -> 5 -> 6 -> null

		// İlk listeye düğümleri ekleyelim
		TekYonluBagliListe liste1 = new TekYonluBagliListe();
		liste1.sonaEkle(1);
		liste1.sonaEkle(3);
		liste1.sonaEkle(5);

		// İkinci listeye düğümleri ekleyelim
		TekYonluBagliListe liste2 = new TekYonluBagliListe();
		liste2.sonaEkle(2);
		liste2.sonaEkle(4);

		// İki sıralı listeyi birleştirelim
		TekYonluBagliListe birlesmisListe = birlestir(liste1, liste2);

		// Birleştirilmiş listeyi yazdıralım
		birlesmisListe.listeyiYazdir(); //Bağlı Liste: 1 -> 2 -> 3 -> 4 -> 5 -> null
	}
}
