// Bekci (sentinel) dugumlu cift yonlu liste: listenin iki ucunda veri
// tasimayan iki sabit dugum (basBekci, sonBekci) bulunur. Her gercek dugumun
// her zaman bir oncesi ve sonrasi oldugu icin null kontrolu gerekmez.
public class BekciliCiftYonluListe {

	// Dugum sinifi
	static class Dugum {
		int veri;
		Dugum onceki;
		Dugum sonraki;

		Dugum(int veri) {
			this.veri = veri;
		}
	}

	private final Dugum basBekci = new Dugum(0);   // Baslik bekcisi (veri kullanilmaz)
	private final Dugum sonBekci = new Dugum(0);   // Bitis bekcisi (veri kullanilmaz)
	private int boyut = 0;

	BekciliCiftYonluListe() {
		basBekci.sonraki = sonBekci;   // Bos liste: iki bekci birbirini gosterir
		sonBekci.onceki = basBekci;
	}

	int boyut() { return boyut; }
	boolean bosMu() { return boyut == 0; }

	// once ile sonra arasina ekleme - if yok, dort bag atamasi: O(1)
	Dugum arayaEkle(int v, Dugum once, Dugum sonra) {
		Dugum yeni = new Dugum(v);
		yeni.onceki = once;
		yeni.sonraki = sonra;
		once.sonraki = yeni;
		sonra.onceki = yeni;
		boyut++;
		return yeni;
	}

	Dugum basaEkle(int v) { return arayaEkle(v, basBekci, basBekci.sonraki); }
	Dugum sonaEkle(int v) { return arayaEkle(v, sonBekci.onceki, sonBekci); }

	// Bilinen bir dugumu silme - komsular hep var, bas/son guncellemesi yok: O(1)
	int sil(Dugum d) {
		d.onceki.sonraki = d.sonraki;
		d.sonraki.onceki = d.onceki;
		boyut--;
		return d.veri;
	}

	// Bastan ve sondan silme de sil(d) ile yapilir: O(1)
	Integer ilkOgeyiSil() { return bosMu() ? null : sil(basBekci.sonraki); }
	Integer sonOgeyiSil() { return bosMu() ? null : sil(sonBekci.onceki); }

	// Gezinme basBekci.sonraki'den baslar, sonBekci'de durur
	void yazdir() {
		System.out.print("Bekcili Liste: [bas] <-> ");
		for (Dugum g = basBekci.sonraki; g != sonBekci; g = g.sonraki)
			System.out.print(g.veri + " <-> ");
		System.out.println("[son]  (boyut = " + boyut + ")");
	}

	public static void main(String[] args) {
		BekciliCiftYonluListe liste = new BekciliCiftYonluListe();
		liste.yazdir();                  // [bas] <-> [son]  (boyut = 0)

		Dugum on = liste.basaEkle(10);
		liste.basaEkle(5);
		liste.yazdir();                  // [bas] <-> 5 <-> 10 <-> [son]  (boyut = 2)

		liste.sil(on);                   // Son gercek dugum siliniyor; ozel durum yok
		liste.yazdir();                  // [bas] <-> 5 <-> [son]  (boyut = 1)

		liste.sonaEkle(20);
		liste.sonaEkle(30);
		liste.yazdir();                  // [bas] <-> 5 <-> 20 <-> 30 <-> [son]  (boyut = 3)

		System.out.println("ilkOgeyiSil(): " + liste.ilkOgeyiSil());   // 5
		System.out.println("sonOgeyiSil(): " + liste.sonOgeyiSil());   // 30
		System.out.println("sonOgeyiSil(): " + liste.sonOgeyiSil());   // 20
		System.out.println("Bos listede: " + liste.sonOgeyiSil());     // null
		liste.yazdir();                  // [bas] <-> [son]  (boyut = 0)
	}
}
