
public class CiftYonluBagliListe {
	// Çift yönlü bağlı listenin düğümünü temsil eden iç içe sınıf
	class Dugum {
		int veri;
		Dugum onceki;
		Dugum sonraki;

		Dugum(int veri) {
			this.veri = veri;
			this.onceki = null;
			this.sonraki = null;
		}
	}

	private Dugum bas;
	private Dugum son;

	// Çift yönlü bağlı listenin başlatılması
	public CiftYonluBagliListe() {
		bas = null;
		son = null;
	}

	// Listenin başına düğüm eklemek
	public void basaEkle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		if (bas == null) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			yeniDugum.sonraki = bas;
			bas.onceki = yeniDugum;
			bas = yeniDugum;
		}
	}

	// Listenin sonuna düğüm eklemek
	public void sonaEkle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		if (bas == null) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			yeniDugum.onceki = son;
			son.sonraki = yeniDugum;
			son = yeniDugum;
		}
	}

	//Belirli bir konuma eleman eklemek için bir fonksiyon
	void konumaEkle(int veri, int konum) {
		Dugum yeniDugum = new Dugum(veri);
		if (konum <= 1) {
			yeniDugum.sonraki = bas;
			bas.onceki = yeniDugum;
			bas = yeniDugum;
		} else {
			Dugum gecici = bas;
			int adim = 1;
			while (gecici != null && adim < konum - 1) {
				gecici = gecici.sonraki;
				adim++;
			}
			if (gecici == null) {
				System.out.println("Geçersiz konum. Eleman eklenemedi.");
			} else {
				yeniDugum.sonraki = gecici.sonraki;
				yeniDugum.onceki = gecici;
				if (gecici.sonraki != null) {
					gecici.sonraki.onceki = yeniDugum;
				}
				gecici.sonraki = yeniDugum;
			}
		}
	}

	//Çift yönlü bağlı listenin uzunluğunu bulan bir fonksiyon
	int listeUzunlugu() {
		Dugum gecici = bas;
		int uzunluk = 0;
		while (gecici != null) {
			uzunluk++;
			gecici = gecici.sonraki;
		}
		System.out.println(uzunluk);
		return uzunluk;
	}

	//Listenin başındaki ilk elemanı silen bir fonksiyon
	void ilkElemaniSil() {
		if (bas == null) {
			System.out.println("Liste boş. Silme işlemi yapılamaz.");
			return;
		}
		if (bas.sonraki != null) {
			bas = bas.sonraki;
			bas.onceki = null;
		} else {
			bas = null;
		}
	}

	//Listenin sonundaki son elemanı silen bir fonksiyon
	void sonElemaniSil() {
		if (bas == null) {
			System.out.println("Liste boş. Silme işlemi yapılamaz.");
			return;
		}

		if (bas.sonraki == null) {
			bas = null;
		} else {
			Dugum gecici = bas;
			while (gecici.sonraki != null) {
				gecici = gecici.sonraki;
			}
			gecici.onceki.sonraki = null;
		}
	}

	//Belirli bir elemanı silen bir fonksiyon
	void elemaniSil(int veri) {
		if (bas == null) {
			System.out.println("Liste boş. Silme işlemi yapılamaz.");
			return;
		}

		if (bas.veri == veri) {
			bas = bas.sonraki;
			if (bas != null) {
				bas.onceki = null;
			}
			return;
		}

		Dugum gecici = bas;
		while (gecici != null && gecici.veri != veri) {
			gecici = gecici.sonraki;
		}

		if (gecici == null) {
			System.out.println("Belirtilen eleman listede bulunamadı.");
			return;
		}

		if (gecici.sonraki != null) {
			gecici.onceki.sonraki = gecici.sonraki;
			gecici.sonraki.onceki = gecici.onceki;
		} else {
			gecici.onceki.sonraki = null;
		}
	}

	//Çift yönlü bağlı listeyi ileri yönde yazdırmak için bir fonksiyon
	void listeyiIleriYazdir() {
		Dugum gecici = bas;

		System.out.print("İleri Yönde: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " <-> ");
			gecici = gecici.sonraki;
		}
		System.out.println("null");
	}

	//Çift yönlü bağlı listeyi geri yönde yazdırmak için bir fonksiyon
	void listeyiGeriYazdir() {
		Dugum gecici = bas;

		// En son elemana ilerle
		while (gecici.sonraki != null) {
			gecici = gecici.sonraki;
		}

		System.out.print("Geri Yönde: ");
		while (gecici != null) {
			System.out.print(gecici.veri + " <-> ");
			gecici = gecici.onceki;
		}
		System.out.println("null");
	}

	//Belirli bir elemanı arayan bir fonksiyon
	boolean elemaniAra(int hedef) {
		Dugum gecici = bas;
		int konum = 0;
		while (gecici != null) {
			konum++;
			if (gecici.veri == hedef) {
				System.out.println("Belirtilen eleman " + konum + " konumda bulundu.");
				return true;
			}
			gecici = gecici.sonraki;
		}
		System.out.println("Belirtilen eleman bulunamadı.");
		return false;
	}

	public static void main(String[] args) {
		CiftYonluBagliListe liste = new CiftYonluBagliListe();

		liste.basaEkle(30);
		liste.basaEkle(20);
		liste.sonaEkle(40);

		liste.listeyiIleriYazdir();

		liste.konumaEkle(50, 2);
		liste.listeyiGeriYazdir();
		liste.listeUzunlugu();

		liste.elemaniAra(30);
		liste.elemaniAra(70);

		liste.ilkElemaniSil();
		liste.listeyiIleriYazdir();

		liste.sonElemaniSil();
		liste.listeyiIleriYazdir();

		liste.elemaniSil(30);
		liste.listeyiIleriYazdir();

		liste.elemaniSil(20);
		liste.listeyiIleriYazdir();
	}
}
