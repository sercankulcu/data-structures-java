
class Dugum {
	int veri;
	int oncelik;
	Dugum sonraki;

	public Dugum(int veri, int oncelik) {
		this.veri = veri;
		this.oncelik = oncelik;
		this.sonraki = null;
	}
}

public class OncelikliKuyrukBagliListe {
	private Dugum bas;

	public OncelikliKuyrukBagliListe() {
		bas = null;
	}

	public void ekle(int veri, int oncelik) {
		Dugum yeniDugum = new Dugum(veri, oncelik);

		if (bas == null || oncelik < bas.oncelik) {
			yeniDugum.sonraki = bas;
			bas = yeniDugum;
		} else {
			Dugum current = bas;
			while (current.sonraki != null && current.sonraki.oncelik <= oncelik) {
				current = current.sonraki;
			}
			yeniDugum.sonraki = current.sonraki;
			current.sonraki = yeniDugum;
		}
	}

	public int cikar() {
		if (bosMu()) {
			System.out.println("Kuyruk boş, eleman çıkarılamaz.");
			return -1; // Hata durumu, -1 döndürülüyor.
		}

		int cikanVeri = bas.veri;
		bas = bas.sonraki;
		return cikanVeri;
	}

	public boolean bosMu() {
		return bas == null;
	}

	public void yazdir() {
		Dugum current = bas;
		while (current != null) {
			System.out.print("(" + current.veri + "," + current.oncelik + ") ");
			current = current.sonraki;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		OncelikliKuyrukBagliListe kuyruk = new OncelikliKuyrukBagliListe();

		kuyruk.ekle(4, 2);
		kuyruk.ekle(5, 1);
		kuyruk.ekle(2, 3);
		kuyruk.ekle(6, 1);
		kuyruk.ekle(1, 4);
		kuyruk.ekle(3, 2);

		System.out.println("Kuyruğun Elemanları:");
		kuyruk.yazdir();

		System.out.println("En Yüksek Öncelikli Eleman Çıkarıldı: " + kuyruk.cikar());
		System.out.println("Kuyruğun Elemanları:");
		kuyruk.yazdir();
	}
}

