
public class OncelikliKuyrukDizi {
	private int[] dizi;
	private int kapasite;
	private int boyut;

	public OncelikliKuyrukDizi(int kapasite) {
		this.kapasite = kapasite;
		dizi = new int[kapasite];
		boyut = 0;
	}

	public void ekle(int eleman) {
		if (boyut == kapasite) {
			System.out.println("Kuyruk dolu, eleman eklenemedi.");
			return;
		}

		int indeks = boyut;
		dizi[indeks] = eleman;
		boyut++;

		while (indeks > 0 && dizi[indeks] > dizi[(indeks - 1) / 2]) {
			// Üst düğüm ile yer değiştirme
			int gecici = dizi[indeks];
			dizi[indeks] = dizi[(indeks - 1) / 2];
			dizi[(indeks - 1) / 2] = gecici;
			indeks = (indeks - 1) / 2;
		}
	}

	public int cikar() {
		if (boyut == 0) {
			System.out.println("Kuyruk boş, eleman çıkarılamaz.");
			return -1; // Hata durumu, -1 döndürülüyor.
		}

		int enBuyuk = dizi[0];
		boyut--;

		dizi[0] = dizi[boyut];
		int indeks = 0;
		while (true) {
			int solCocuk = 2 * indeks + 1;
			int sagCocuk = 2 * indeks + 2;
			int enBuyukCocuk = indeks;

			if (solCocuk < boyut && dizi[solCocuk] > dizi[enBuyukCocuk]) {
				enBuyukCocuk = solCocuk;
			}
			if (sagCocuk < boyut && dizi[sagCocuk] > dizi[enBuyukCocuk]) {
				enBuyukCocuk = sagCocuk;
			}

			if (enBuyukCocuk == indeks) {
				break;
			}

			// Üst düğüm ile yer değiştirme
			int gecici = dizi[indeks];
			dizi[indeks] = dizi[enBuyukCocuk];
			dizi[enBuyukCocuk] = gecici;
			indeks = enBuyukCocuk;
		}

		return enBuyuk;
	}

	public boolean bosMu() {
		return boyut == 0;
	}

	public int buyukluk() {
		return boyut;
	}

	public void yazdir() {
		for (int i = 0; i < boyut; i++) {
			System.out.print(dizi[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		OncelikliKuyrukDizi kuyruk = new OncelikliKuyrukDizi(5);

		kuyruk.ekle(4);
		kuyruk.ekle(5);
		kuyruk.ekle(2);
		kuyruk.ekle(6);
		kuyruk.ekle(1);
		kuyruk.ekle(3);

		System.out.println("Kuyruğun Büyüklüğü: " + kuyruk.buyukluk());
		System.out.println("Kuyruğun Elemanları:");
		kuyruk.yazdir();

		System.out.println("En Büyük Eleman Çıkarıldı: " + kuyruk.cikar());
		System.out.println("Kuyruğun Elemanları:");
		kuyruk.yazdir();
	}
}

