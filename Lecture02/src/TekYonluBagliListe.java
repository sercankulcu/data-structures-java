
public class TekYonluBagliListe {

	//Düğüm sınıfı
	class Dugum {
		int veri;
		Dugum sonraki;

		Dugum(int veri) {
			this.veri = veri;
			this.sonraki = null;
		}
	}

	Dugum bas;

	// Tek Yönlü Bağlı Liste Başlatma
	TekYonluBagliListe() {
		bas = null;
	}

	//Liste Başına Düğüm Ekleme
	void basaEkle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		yeniDugum.sonraki = bas;
		bas = yeniDugum;
	}

	// Liste Sonuna Düğüm Ekleme
	void sonaEkle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		if (bas == null) {
			bas = yeniDugum;
			return;
		}
		Dugum gecici = bas;
		while (gecici.sonraki != null) {
			gecici = gecici.sonraki;
		}
		gecici.sonraki = yeniDugum;
	}

	//Belirli Bir Konuma Düğüm Ekleme
	void konumaEkle(int veri, int konum) {
		Dugum yeniDugum = new Dugum(veri);
		if (konum == 0) {
			yeniDugum.sonraki = bas;
			bas = yeniDugum;
			return;
		}
		Dugum gecici = bas;
		for (int i = 0; i < konum - 1; i++) {
			if (gecici == null) {
				System.out.println("Belirtilen konum bulunamadı.");
				return;
			}
			gecici = gecici.sonraki;
		}
		if (gecici == null) {
			System.out.println("Belirtilen konum bulunamadı.");
			return;
		}
		yeniDugum.sonraki = gecici.sonraki;
		gecici.sonraki = yeniDugum;
	}

	//İlk Elemanı Silen Fonksiyon
	void ilkElemaniSil() {
		if (bas == null) {
			System.out.println("Liste zaten boş.");
			return;
		}
		Dugum silinecekDugum = bas;
		bas = bas.sonraki;
		silinecekDugum.sonraki = null;
		silinecekDugum = null; // Silinen düğümü bellekten temizle
	}

	//Son Elemanı Silen Fonksiyon
	void sonElemaniSil() {
		if (bas == null) {
			System.out.println("Liste zaten boş.");
			return;
		}
		if (bas.sonraki == null) {
			// Liste sadece bir eleman içeriyorsa, bu elemanı siler
			bas = null;
			return;
		}
		Dugum gecici = bas;
		while (gecici.sonraki.sonraki != null) {
			gecici = gecici.sonraki;
		}
		gecici.sonraki = null;
	}

	//Belirli Bir Elemanı Silen Fonksiyon
	void elemaniSil(int silinecekVeri) {
		if (bas == null) {
			System.out.println("Liste zaten boş.");
			return;
		}

		if (bas.veri == silinecekVeri) {
			bas = bas.sonraki;
			return;
		}

		Dugum onceki = bas;
		Dugum gecici = bas.sonraki;

		while (gecici != null) {
			if (gecici.veri == silinecekVeri) {
				onceki.sonraki = gecici.sonraki;
				return;
			}
			onceki = gecici;
			gecici = gecici.sonraki;
		}

		System.out.println("Belirtilen veri bulunamadı.");
	}

	//Belirli Bir Elemanı Arayan Fonksiyon
	boolean elemaniAra(int arananVeri) {
		Dugum gecici = bas;
		int konum = 0;
		while (gecici != null) {
			konum++;
			if (gecici.veri == arananVeri) {
				System.out.println("Belirtilen eleman " + konum + " konumda bulundu.");
				return true;
			}
			gecici = gecici.sonraki;
		}
		System.out.println("Belirtilen eleman bulunamadı.");
		return false;
	}

	// Liste Uzunluğunu Hesaplayan Fonksiyon
	int listeUzunlugu() {
		int uzunluk = 0;
		Dugum gecici = bas;
		while (gecici != null) {
			uzunluk++;
			gecici = gecici.sonraki;
		}
		System.out.println("Bağlı Liste Uzunluğu: " + uzunluk);
		return uzunluk;
	}

	// Liste Üzerinde Dolaşma ve Verileri Yazdırma
	void listeyiYazdir() {
		Dugum gecici = bas;
		System.out.print("Bağlı Liste: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " -> ");
			gecici = gecici.sonraki;
		}
		System.out.println("null");
	}

	//Liste Ters Çevirme Fonksiyonu
	void listeyiTersCevir() {
		Dugum onceki = null;
		Dugum suanki = bas;
		Dugum sonraki = null;

		while (suanki != null) {
			sonraki = suanki.sonraki;
			suanki.sonraki = onceki;
			onceki = suanki;
			suanki = sonraki;
		}

		bas = onceki;
	}

	//Tek Yönlü Bağlı Listede Orta Elemanı Bulma
	int ortaElemaniBul() {
		if (bas == null) {
			return -1; // Liste boşsa, -1 döndür
		}
		Dugum yavas = bas;
		Dugum hizli = bas;

		while (hizli != null && hizli.sonraki != null) {
			yavas = yavas.sonraki;         // Yavaş pointer bir adım ilerler
			hizli = hizli.sonraki.sonraki; // Hızlı pointer iki adım ilerler
		}

		System.out.println("Orta Eleman: " + yavas.veri);
		return yavas.veri;
	}

	//Tek Yönlü Bağlı Listeyi Kopyala
	TekYonluBagliListe kopyala() {
		TekYonluBagliListe kopyaListe = new TekYonluBagliListe();
		Dugum gecici = bas;

		while (gecici != null) {
			kopyaListe.sonaEkle(gecici.veri);
			gecici = gecici.sonraki;
		}

		kopyaListe.listeyiYazdir();
		return kopyaListe;
	}

	//Tek Yönlü Bağlı Listeden Tekrarlayanları Kaldırma
	void tekrarlayanlariKaldir() {
		if (bas == null) {
			return;
		}

		TekYonluBagliListe kume = new TekYonluBagliListe();
		Dugum onceki = null;
		Dugum gecici = bas;

		while (gecici != null) {
			int veri = gecici.veri;
			if (kume.elemaniAra(veri)) {
				// Tekrarlayan elemanı listeden çıkar
				onceki.sonraki = gecici.sonraki;
			} else {
				kume.sonaEkle(veri);
				onceki = gecici;
			}
			gecici = gecici.sonraki;
		}
	}

	//İki Sıralı Listeyi Birleştirme
	static TekYonluBagliListe birlestir(TekYonluBagliListe liste1, TekYonluBagliListe liste2) {
		TekYonluBagliListe birlesmisListe = new TekYonluBagliListe();
		Dugum gecici1 = liste1.bas;
		Dugum gecici2 = liste2.bas;

		while (gecici1 != null && gecici2 != null) {
			if (gecici1.veri < gecici2.veri) {
				birlesmisListe.sonaEkle(gecici1.veri);
				gecici1 = gecici1.sonraki;
			} else {
				birlesmisListe.sonaEkle(gecici2.veri);
				gecici2 = gecici2.sonraki;
			}
		}

		while (gecici1 != null) {
			birlesmisListe.sonaEkle(gecici1.veri);
			gecici1 = gecici1.sonraki;
		}

		while (gecici2 != null) {
			birlesmisListe.sonaEkle(gecici2.veri);
			gecici2 = gecici2.sonraki;
		}

		return birlesmisListe;
	}

	//Tek Yönlü Bağlı Listeyi Sıralama
	void sırala() {
		if (bas == null) {
			return; // Liste boşsa sıralama yapmaya gerek yok
		}

		Dugum suanki = bas;
		while (suanki != null) {
			Dugum diger = suanki.sonraki;
			while (diger != null) {
				if (suanki.veri > diger.veri) {
					// Elemanları değiştir
					int gecici = suanki.veri;
					suanki.veri = diger.veri;
					diger.veri = gecici;
				}
				diger = diger.sonraki;
			}
			suanki = suanki.sonraki;
		}
	}


	public static void main(String[] args) {
		TekYonluBagliListe liste = new TekYonluBagliListe();

		// Düğümleri ekleyelim
		liste.sonaEkle(1);
		liste.sonaEkle(2);
		liste.sonaEkle(3);
		liste.sonaEkle(4);
		liste.konumaEkle(5, 0);
		liste.listeyiYazdir();
		liste.basaEkle(5);
		liste.listeyiYazdir();
		liste.konumaEkle(6, 2);
		liste.ortaElemaniBul();
		liste.listeUzunlugu();
		// Liste üzerinde dolaşarak verileri yazdıralım
		liste.listeyiYazdir();

		liste.ilkElemaniSil();
		liste.listeyiYazdir();
		liste.sonElemaniSil();
		liste.listeyiYazdir();
		liste.elemaniSil(3);
		liste.listeyiYazdir();

		liste.elemaniAra(2);
		liste.elemaniAra(1);

		liste.listeyiTersCevir();
		liste.listeyiYazdir();

		liste.ortaElemaniBul();

		liste.kopyala();
		liste.sonaEkle(6);
		liste.listeyiYazdir();
		liste.tekrarlayanlariKaldir();
		liste.listeyiYazdir();
		liste.sırala();
		liste.listeyiYazdir();

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
		birlesmisListe.listeyiYazdir();
	}
}
