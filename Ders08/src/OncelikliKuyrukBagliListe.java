// Dugum sinifi: Her bir dugumun veri, oncelik ve bir sonraki dugum bilgilerini tutar
class Dugum {
	int veri; // Dugumdeki veri
	int oncelik; // Dugumun oncelik degeri
	Dugum sonraki; // Bir sonraki dugume isaretci

	// Dugum yapici metodu: Veri ve oncelik atanir, sonraki baslangicta null olur
	public Dugum(int veri, int oncelik) {
		this.veri = veri;
		this.oncelik = oncelik;
		this.sonraki = null;
	}
}

// Oncelikli kuyruğu bagli liste yapisi ile gerceklestiren sinif
public class OncelikliKuyrukBagliListe {
	private Dugum bas; // Kuyrugun basini temsil eder

	// Kuyruk yapici metodu: Baslangicta kuyruk bos
	public OncelikliKuyrukBagliListe() {
		bas = null;
	}

	// Kuyruga eleman ekler
	public void ekle(int veri, int oncelik) {
		Dugum yeniDugum = new Dugum(veri, oncelik);

		// Kuyruk bos veya yeni elemanin onceligi mevcut bastan daha dusukse
		if (bas == null || oncelik < bas.oncelik) {
			yeniDugum.sonraki = bas;
			bas = yeniDugum;
		} else {
			Dugum simdiki = bas;

			// Mevcut listeyi dolasarak yeni dugumun yerini bulur
			while (simdiki.sonraki != null && simdiki.sonraki.oncelik <= oncelik) {
				simdiki = simdiki.sonraki;
			}

			// Yeni dugum uygun yere yerlestirilir
			yeniDugum.sonraki = simdiki.sonraki;
			simdiki.sonraki = yeniDugum;
		}
	}

	// Kuyruktan en yuksek oncelikli elemani cikarir
	public int cikar() {
		if (bosMu()) {
			System.out.println("Kuyruk bos, eleman cikartilamaz.");
			return -1; // Hata durumu, -1 donderilir
		}

		int cikanVeri = bas.veri; // En yuksek oncelikli eleman
		bas = bas.sonraki; // Bas dugumu guncelle
		return cikanVeri;
	}

	// Kuyrugun bos olup olmadigini kontrol eder
	public boolean bosMu() {
		return bas == null;
	}

	// Kuyruktaki elemanlari yazdirir
	public void yazdir() {
		Dugum simdiki = bas;
		while (simdiki != null) {
			System.out.print("(" + simdiki.veri + "," + simdiki.oncelik + ") ");
			simdiki = simdiki.sonraki;
		}
		System.out.println();
	}

	// Ana metot: Programin giris noktasi
	public static void main(String[] args) {
		OncelikliKuyrukBagliListe kuyruk = new OncelikliKuyrukBagliListe();

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
