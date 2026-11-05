// Ikili arama agaci (BST): arama, ekleme, silme, en kucuk / en buyuk ve kok ortada (inorder) gezinme.
// Her islem kokten tek bir yol izler: O(h).
public class IkiliAgac<E extends Comparable<E>> {

	// Genel (generic) agac dugumu: anahtarlar compareTo ile karsilastirilir
	static class IkiliAgacDugum<E extends Comparable<E>> {
		E veri;                     // nesne
		IkiliAgacDugum<E> solCocuk; // sol cocuk
		IkiliAgacDugum<E> sagCocuk; // sag cocuk

		IkiliAgacDugum(E veri) {
			this.veri = veri;
			// cocuklar baslangicta null
		}
	}

	IkiliAgacDugum<E> kok;

	// Anahtar agacta varsa true, yoksa false
	boolean ara(IkiliAgacDugum<E> kok, E veri) {
		if (kok == null)
			return false;            // yok
		int k = veri.compareTo(kok.veri); // == degil: == yalnizca referansa bakar
		if (k == 0)
			return true;             // bulundu
		else if (k < 0)
			return ara(kok.solCocuk, veri);
		else
			return ara(kok.sagCocuk, veri);
	}

	// Aranir gibi inilir; dusulen null konuma yeni yaprak eklenir
	// kullanim: kok = ekle(kok, 5);
	IkiliAgacDugum<E> ekle(IkiliAgacDugum<E> kok, E veri) {
		if (kok == null)
			return new IkiliAgacDugum<>(veri);
		int k = veri.compareTo(kok.veri);
		if (k < 0)
			kok.solCocuk = ekle(kok.solCocuk, veri);
		else if (k > 0)
			kok.sagCocuk = ekle(kok.sagCocuk, veri);
		// k == 0: anahtar zaten var, eklenmez
		return kok;
	}

	// Uc durum: yaprak, tek cocuk, iki cocuk (yerine inorder ardil gelir)
	// kullanim: kok = sil(kok, 40);  (kok silinirse yeni kok bu atamayla gelir)
	IkiliAgacDugum<E> sil(IkiliAgacDugum<E> kok, E veri) {
		if (kok == null) return null;   // yok
		int k = veri.compareTo(kok.veri);
		if (k < 0)
			kok.solCocuk = sil(kok.solCocuk, veri);
		else if (k > 0)
			kok.sagCocuk = sil(kok.sagCocuk, veri);
		else if (kok.solCocuk == null)
			return kok.sagCocuk;  // yaprak / yalniz sag cocuk
		else if (kok.sagCocuk == null)
			return kok.solCocuk;  // yalniz sol cocuk
		else {                    // iki cocuk
			kok.veri = minDeger(kok.sagCocuk).veri;     // ardilin degerini kopyala
			kok.sagCocuk = sil(kok.sagCocuk, kok.veri); // eski ardili sil
		}
		return kok;
	}

	// On kosul: dugum != null
	IkiliAgacDugum<E> minDeger(IkiliAgacDugum<E> dugum) {
		while (dugum.solCocuk != null)
			dugum = dugum.solCocuk; // sola in
		return dugum;  // en kucuk
	}

	IkiliAgacDugum<E> maxDeger(IkiliAgacDugum<E> dugum) {
		while (dugum.sagCocuk != null)
			dugum = dugum.sagCocuk; // saga in
		return dugum;  // en buyuk
	}

	// Kok ortada (inorder) gezinme: sol -> kok -> sag, anahtarlari sirali verir
	void inorder(IkiliAgacDugum<E> d) {
		if (d == null) return;
		inorder(d.solCocuk);  // sol
		System.out.print(d.veri + " ");
		inorder(d.sagCocuk);  // sag
	}

	static IkiliAgac<Integer> kur(int... anahtarlar) {
		IkiliAgac<Integer> agac = new IkiliAgac<>();
		for (int a : anahtarlar)
			agac.kok = agac.ekle(agac.kok, a);
		return agac;
	}

	static void yazdir(String baslik, IkiliAgac<Integer> agac) {
		System.out.print(baslik);
		agac.inorder(agac.kok);
		System.out.println();
	}

	public static void main(String[] args) {
		// Agac: 6(2(1, 4(3)), 8)
		IkiliAgac<Integer> agac = kur(6, 2, 1, 4, 3, 8);
		yazdir("inorder: ", agac);                                  // 1 2 3 4 6 8
		System.out.println("min: " + agac.minDeger(agac.kok).veri);  // 1
		System.out.println("max: " + agac.maxDeger(agac.kok).veri);  // 8
		agac.kok = agac.ekle(agac.kok, 5);                           // 5, 4'un sag cocugu olur
		yazdir("ekle 5 sonrasi: ", agac);                           // 1 2 3 4 5 6 8

		// Agac: 6(4(2, 5), 8(7, 9))
		IkiliAgac<Integer> agac2 = kur(6, 4, 8, 2, 5, 7, 9);
		System.out.println("ara(kok, 5) = " + agac2.ara(agac2.kok, 5));   // true
		System.out.println("ara(kok, 10) = " + agac2.ara(agac2.kok, 10)); // false

		// Silme: her silme ayni agactan baslar: 50(40(30, 45(42)), 60(-, 70))
		int[] silinecekler = {70, 45, 60, 40};
		for (int s : silinecekler) {
			IkiliAgac<Integer> a = kur(50, 40, 60, 30, 45, 70, 42);
			a.kok = a.sil(a.kok, s);
			yazdir("sil " + s + " -> ", a);
		}
		IkiliAgac<Integer> a = kur(50, 40, 60, 30, 45, 70, 42);
		a.kok = a.sil(a.kok, 40);
		System.out.println("40'in yerine gelen ardil: " + a.kok.solCocuk.veri); // 42
		a.kok = a.sil(a.kok, 50);  // kok siliniyor: dondurulen deger koke atanmali
		System.out.println("kok 50 silindi, yeni kok: " + a.kok.veri);          // 60
	}
}
