

public class OncelikliKuyrukDizi {

	// Eleman ve oncelik bilgisini tutan Dugum sinifi
	class Dugum {
		int veri; // Elemanin degeri
		int oncelik; // Elemanin oncelik seviyesi

		// Dugum yapici metodu
		public Dugum(int veri, int oncelik) {
			this.veri = veri;
			this.oncelik = oncelik;
		}
	}

	private Dugum[] dizi; // Dugumleri tutan dizi
	private int kapasite;
	private int boyut;

	// Kuyruk yapici metodu
	public OncelikliKuyrukDizi(int kapasite) {
		this.kapasite = kapasite;
		dizi = new Dugum[kapasite];
		boyut = 0;
	}

	// Kuyruga eleman ve oncelik ekleme fonksiyonu
	public void ekle(int eleman, int oncelik) {
		if (boyut == kapasite) {
			System.out.println("Kuyruk dolu, eleman eklenemedi.");
			return;
		}

		Dugum yeniDugum = new Dugum(eleman, oncelik);
		int indeks = boyut;
		dizi[indeks] = yeniDugum;
		boyut++;

		// Yukari dogru yer degistirerek heap ozelligini saglama
		while (indeks > 0 && dizi[indeks].oncelik < dizi[(indeks - 1) / 2].oncelik) {
			// Dugumleri yer degistirme
			Dugum gecici = dizi[indeks];
			dizi[indeks] = dizi[(indeks - 1) / 2];
			dizi[(indeks - 1) / 2] = gecici;
			indeks = (indeks - 1) / 2;
		}
	}

	// Kuyruktan en yuksek oncelikli elemani cikarir
	public int cikar() {
		if (boyut == 0) {
			System.out.println("Kuyruk bos, eleman cikartilamaz.");
			return -1; // Hata durumu, -1 dondur
		}

		int enBuyuk = dizi[0].veri;
		boyut--;

		dizi[0] = dizi[boyut];
		int indeks = 0;

		// Heap ozelligini tekrar saglama
		while (true) {
			int solCocuk = 2 * indeks + 1;
			int sagCocuk = 2 * indeks + 2;
			int enOncelikliCocuk = indeks;

			if (solCocuk < boyut && dizi[solCocuk].oncelik < dizi[enOncelikliCocuk].oncelik) {
				enOncelikliCocuk = solCocuk;
			}
			if (sagCocuk < boyut && dizi[sagCocuk].oncelik < dizi[enOncelikliCocuk].oncelik) {
				enOncelikliCocuk = sagCocuk;
			}

			if (enOncelikliCocuk == indeks) {
				break;
			}

			// Dugumleri yer degistirme
			Dugum gecici = dizi[indeks];
			dizi[indeks] = dizi[enOncelikliCocuk];
			dizi[enOncelikliCocuk] = gecici;
			indeks = enOncelikliCocuk;
		}

		return enBuyuk;
	}

	// Kuyrugu yazdirma
	public void yazdir() {
		for (int i = 0; i < boyut; i++) {
			System.out.print("(" + dizi[i].veri + ", " + dizi[i].oncelik + ") ");
		}
		System.out.println();
	}

	// Kuyruk boyutunu dondurur
	public int buyukluk() {
		return boyut;
	}

	// Kuyrugun bos olup olmadigini kontrol eder
	public boolean bosMu() {
		return boyut == 0;
	}

	public static void main(String[] args) {
		OncelikliKuyrukDizi kuyruk = new OncelikliKuyrukDizi(6);

		// Kuyruga elemanlar eklenir
		kuyruk.ekle(4, 2);
		kuyruk.ekle(5, 1);
		kuyruk.ekle(2, 3);
		kuyruk.ekle(6, 1);
		kuyruk.ekle(1, 4);
		kuyruk.ekle(3, 2);

		// Kuyruktaki elemanlari yazdir
		System.out.println("Kuyrugun Elemanlari:");
		kuyruk.yazdir();

		// En yuksek oncelikli elemani cikart ve yazdir
		System.out.println("En Yuksek Oncelikli Eleman Cikartildi: " + kuyruk.cikar());
		System.out.println("Kuyrugun Elemanlari:");
		kuyruk.yazdir();
	}
}
