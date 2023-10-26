
public class CiftYonluBagliListe {
	
	private CiftYonluDugum bas; // Baş düğümünü saklayan değişken
	private CiftYonluDugum son; // Son düğümü saklayan değişken

	// Çift yönlü bağlı listenin başlatılması
	public CiftYonluBagliListe() {
		bas = null;
		son = null;
	}


	//Listenin başına düğüm ekleme
	public void basaEkle(int veri) {
		CiftYonluDugum yeniDugum = new CiftYonluDugum(veri); // Eklenecek yeni düğümü oluştur

		if (bas == null) {
			// Liste boşsa, yeni düğüm hem başı hem de sonu temsil eder
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Liste boş değilse, yeni düğümü listenin başına ekler
			yeniDugum.sonraki = bas; // Yeni düğümün sonraki referansını eski baş düğüm yap
			bas.onceki = yeniDugum; // Eski baş düğümün önceki referansını yeni düğüm yap
			bas = yeniDugum; // Yeni düğümü baş düğüm olarak ayarla
		}
	}


	//Listenin sonuna düğüm ekleme
	public void sonaEkle(int veri) {
		CiftYonluDugum yeniDugum = new CiftYonluDugum(veri); // Eklenecek yeni düğümü oluştur

		if (bas == null) {
			// Liste boşsa, yeni düğüm hem başı hem de sonu temsil eder
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			// Liste boş değilse, yeni düğümü listenin sonuna ekler
			yeniDugum.onceki = son; // Yeni düğümün önceki referansını eski son düğüm yap
			son.sonraki = yeniDugum; // Eski son düğümün sonraki referansını yeni düğüm yap
			son = yeniDugum; // Yeni düğümü son düğüm olarak ayarla
		}
	}


	//Belirli bir konuma eleman ekleme
	void konumaEkle(int veri, int konum) {
		CiftYonluDugum yeniDugum = new CiftYonluDugum(veri); // Eklenecek yeni düğümü oluştur

		if (konum <= 1) {
			// Konum 1 veya daha küçükse, yeni düğümü listenin başına ekle
			yeniDugum.sonraki = bas; // Yeni düğümün sonraki referansını eski baş düğüm yap
			bas.onceki = yeniDugum; // Eski baş düğümün önceki referansını yeni düğüm yap
			bas = yeniDugum; // Yeni düğümü baş düğüm olarak ayarla
		} else {
			CiftYonluDugum gecici = bas; // Geçici bir düğüm oluştur ve baş düğümle başlat
			int adim = 1;

			while (gecici != null && adim < konum - 1) {
				gecici = gecici.sonraki; // İstenilen konuma kadar ilerle
				adim++;
			}

			if (gecici == null) {
				System.out.println("Geçersiz konum. Eleman eklenemedi.");
			} else {
				// Yeni düğümü istenen konuma ekler
				yeniDugum.sonraki = gecici.sonraki; // Yeni düğümün sonraki referansını gecici'nin sonraki düğüm yap
				yeniDugum.onceki = gecici; // Yeni düğümün önceki referansını gecici yap
				if (gecici.sonraki != null) {
					gecici.sonraki.onceki = yeniDugum; // Gecici'nin sonraki düğümünün önceki referansını yeni düğüm yap
				}
				gecici.sonraki = yeniDugum; // Gecici'nin sonraki düğümünü yeni düğüm yap
			}
		}
	}


	//Çift yönlü bağlı listenin uzunluğunu bulma
	int listeUzunlugu() {
		CiftYonluDugum gecici = bas; // Geçici bir düğüm oluştur ve baş düğümle başlat
		int uzunluk = 0; // Listenin uzunluğunu saklayan değişken

		while (gecici != null) {
			uzunluk++; // Her döngü adımında uzunluğu bir artır
			gecici = gecici.sonraki; // Bir sonraki düğüme geç
		}

		System.out.println("Liste Uzunluğu: " + uzunluk); // Uzunluğu ekrana yazdır
		return uzunluk; // Uzunluğu döndür
	}


	//Listenin başındaki ilk elemanı silme
	void ilkElemaniSil() {
		if (bas == null) {
			System.out.println("Liste boş. Silme işlemi yapılamaz.");
			return; // Liste boşsa, silme işlemi yapılamaz
		}

		if (bas.sonraki != null) {
			// Eğer liste birden fazla eleman içeriyorsa, ilk elemanı sil
			bas = bas.sonraki; // Baş düğümünü bir sonraki düğüm yap
			bas.onceki = null; // Yeni baş düğümün önceki referansını null yap
		} else {
			bas = null; // Eğer liste sadece bir eleman içeriyorsa, baş düğümü null yap
		}
	}


	//Listenin sonundaki son elemanı silme
	void sonElemaniSil() {
		if (bas == null) {
			System.out.println("Liste boş. Silme işlemi yapılamaz.");
			return; // Liste boşsa, silme işlemi yapılamaz
		}

		if (bas.sonraki == null) {
			// Eğer liste sadece bir eleman içeriyorsa, baş düğümü null yap
			bas = null;
		} else {
			// Eğer liste birden fazla eleman içeriyorsa, son elemanı sil
			CiftYonluDugum gecici = bas; // Geçici bir düğüm oluştur ve baş düğümle başlat

			while (gecici.sonraki != null) {
				gecici = gecici.sonraki; // Son elemana kadar ilerle
			}

			gecici.onceki.sonraki = null; // Son elemanın önceki düğümünün sonraki referansını null yap
		}
	}


	//Belirli bir elemanı silme
	void elemaniSil(int veri) {
		if (bas == null) {
			System.out.println("Liste boş. Silme işlemi yapılamaz.");
			return; // Liste boşsa, silme işlemi yapılamaz
		}

		if (bas.veri == veri) {
			// Eğer silinecek eleman liste başındaysa
			bas = bas.sonraki; // Baş düğümünü bir sonraki düğüm yap
			if (bas != null) {
				bas.onceki = null; // Yeni baş düğümün önceki referansını null yap
			}
			return;
		}

		CiftYonluDugum gecici = bas; // Geçici bir düğüm oluştur ve baş düğümle başlat

		while (gecici != null && gecici.veri != veri) {
			gecici = gecici.sonraki; // Silinecek elemanı bulana kadar ilerle
		}

		if (gecici == null) {
			System.out.println("Belirtilen eleman listede bulunamadı.");
			return; // Silinecek eleman listede bulunamazsa, işlemi sonlandır
		}

		if (gecici.sonraki != null) {
			gecici.onceki.sonraki = gecici.sonraki; // Silinen elemanın önceki düğümünün sonraki referansını ayarla
			gecici.sonraki.onceki = gecici.onceki; // Silinen elemanın sonraki düğümünün önceki referansını ayarla
		} else {
			gecici.onceki.sonraki = null; // Eğer silinen eleman listenin son elemanıysa, sonraki referansı null yap
		}
	}


	//Çift yönlü bağlı listeyi ileri yönde yazdırma
	void listeyiIleriYazdir() {
		CiftYonluDugum gecici = bas; // Geçici bir düğüm oluştur ve baş düğümle başlat

		System.out.print("İleri Yönde: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " <-> "); // Düğümün verisini yazdır ve çift yönlü bağlantıyı göster
			gecici = gecici.sonraki; // Bir sonraki düğüme geç
		}
		System.out.println("null"); // Listenin sonunu belirtmek için "null" yazdır
	}


	//Çift yönlü bağlı listeyi geri yönde yazdırma
	void listeyiGeriYazdir() {
		CiftYonluDugum gecici = bas;

		// En son elemana ilerle
		while (gecici.sonraki != null) {
			gecici = gecici.sonraki;
		}

		System.out.print("Geri Yönde: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " <-> "); // Düğümün verisini yazdır ve çift yönlü bağlantıyı göster
			gecici = gecici.onceki; // Bir önceki düğüme geç
		}
		System.out.println("null"); // Listenin sonunu belirtmek için "null" yazdır
	}


	//Belirli bir elemanı arama
	boolean elemaniAra(int hedef) {
		CiftYonluDugum gecici = bas; // Geçici bir düğüm oluştur ve baş düğümle başlat
		int konum = 0; // Elemanın konumunu izlemek için bir değişken

		while (gecici != null) {
			konum++; // Her döngü adımında konumu artır
			if (gecici.veri == hedef) {
				System.out.println("Belirtilen eleman " + konum + " konumda bulundu."); // Eleman bulunduğunda konumu bildir
				return true; // Eleman bulunduğunda true döndür
			}
			gecici = gecici.sonraki; // Bir sonraki düğüme geç
		}

		System.out.println("Belirtilen eleman bulunamadı."); // Eleman bulunamazsa bildir
		return false; // Eleman bulunamazsa false döndür
	}


	public static void main(String[] args) {
		
		CiftYonluBagliListe liste = new CiftYonluBagliListe();

		liste.basaEkle(30);
		liste.basaEkle(20);
		liste.sonaEkle(40);
		liste.listeyiIleriYazdir(); //İleri Yönde: 20 <-> 30 <-> 40 <-> null

		liste.konumaEkle(50, 2);
		liste.listeyiIleriYazdir(); //İleri Yönde: 20 <-> 50 <-> 30 <-> 40 <-> null
		liste.listeyiGeriYazdir(); //Geri Yönde: 40 <-> 30 <-> 50 <-> 20 <-> null
		
		liste.listeUzunlugu(); //Liste Uzunluğu: 4

		liste.elemaniAra(30); //Belirtilen eleman 3 konumda bulundu.
		liste.elemaniAra(70); //Belirtilen eleman bulunamadı.

		liste.ilkElemaniSil();
		liste.listeyiIleriYazdir(); //İleri Yönde: 50 <-> 30 <-> 40 <-> null

		liste.sonElemaniSil();
		liste.listeyiIleriYazdir(); //İleri Yönde: 50 <-> 30 <-> null

		liste.elemaniSil(30);
		liste.listeyiIleriYazdir(); //İleri Yönde: 50 <-> null

		liste.elemaniSil(20); //Belirtilen eleman listede bulunamadı.
		liste.listeyiIleriYazdir(); //İleri Yönde: 50 <-> null
	}
}
