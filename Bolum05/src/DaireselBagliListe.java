// Dairesel bagli liste: son dugum null yerine ilk dugumu gosterir.
// Yalnizca son tutulur; bas = son.sonraki.
public class DaireselBagliListe {

	// Dugum sinifi
	static class Dugum {
		int veri;
		Dugum sonraki;

		Dugum(int veri) {
			this.veri = veri;
			this.sonraki = null;
		}
	}

	private Dugum son;   // bas = son.sonraki
	private int boyut;   // Oge sayisi

	int boyut() { return boyut; }

	// Sona ekleme - O(1)
	void sonaEkle(int deger) {
		Dugum gecici = new Dugum(deger);
		if (son == null) {
			son = gecici;
			son.sonraki = son;            // Tek dugum kendini gosterir
		} else {
			gecici.sonraki = son.sonraki; // Yeni dugum basi gosterir
			son.sonraki = gecici;         // Eski son yeni dugumu gosterir
			son = gecici;                 // Son artik yeni dugum
		}
		boyut++;
	}

	// Basa ekleme - sona eklemenin son = gecici satiri olmayan hali: O(1)
	void basaEkle(int deger) {
		Dugum gecici = new Dugum(deger);
		if (son == null) {
			son = gecici;
			son.sonraki = son;
		} else {
			gecici.sonraki = son.sonraki;
			son.sonraki = gecici;
		}   // son degismez
		boyut++;
	}

	// Bastan silme - O(1)
	void bastanSil() {
		if (son == null) return;
		Dugum bas = son.sonraki;
		if (bas == son)   // Tek dugum
			son = null;
		else
			son.sonraki = bas.sonraki;
		boyut--;
	}

	// Sondan silme - sondan bir onceki dugumu bulmak icin gezinme: O(n)
	void sondanSil() {
		if (son == null) return;
		if (son.sonraki == son) son = null;   // Tek dugum
		else {
			Dugum gecici = son.sonraki;
			while (gecici.sonraki != son)     // Sondan bir onceki
				gecici = gecici.sonraki;
			gecici.sonraki = son.sonraki;     // Basa bagla
			son = gecici;
		}
		boyut--;
	}

	// Dondurme - eski bas sona gecer, hicbir bag degismez: O(1)
	void dondur() {
		if (son != null)
			son = son.sonraki;
	}

	// Yazdirma - halkada null yoktur, kosul != son: O(n)
	void yazdir() {
		System.out.print("Dairesel Liste: ");
		if (son == null) { System.out.println("(bos)"); return; }
		Dugum simdiki = son.sonraki;
		while (simdiki != son) {
			System.out.print(simdiki.veri + " -> ");
			simdiki = simdiki.sonraki;
		}
		System.out.println(simdiki.veri + " -> (" + son.sonraki.veri + ")");  // Son dugum ayrica yazdirilir
	}

	public static void main(String[] args) {

		DaireselBagliListe liste = new DaireselBagliListe();
		liste.sonaEkle(1);
		liste.sonaEkle(8);
		liste.sonaEkle(10);
		liste.yazdir();      // Dairesel Liste: 1 -> 8 -> 10 -> (1)

		liste.basaEkle(5);
		liste.yazdir();      // Dairesel Liste: 5 -> 1 -> 8 -> 10 -> (5)

		liste.bastanSil();
		liste.yazdir();      // Dairesel Liste: 1 -> 8 -> 10 -> (1)

		liste.sonaEkle(16);
		liste.yazdir();      // Dairesel Liste: 1 -> 8 -> 10 -> 16 -> (1)
		liste.sondanSil();
		liste.yazdir();      // Dairesel Liste: 1 -> 8 -> 10 -> (1)
		System.out.println("boyut: " + liste.boyut());   // 3

		// Round-robin: her tur bastakine hizmet et, sonra dondur
		System.out.print("Round-robin: ");
		for (int tur = 1; tur <= 4; tur++) {
			System.out.print(liste.son.sonraki.veri + " ");
			liste.dondur();
		}
		System.out.println();   // 1 8 10 1

		// Tek dugumlu ve bos liste
		DaireselBagliListe tekli = new DaireselBagliListe();
		tekli.sonaEkle(7);
		tekli.yazdir();      // Dairesel Liste: 7 -> (7)
		tekli.sondanSil();
		tekli.yazdir();      // Dairesel Liste: (bos)
	}
}
