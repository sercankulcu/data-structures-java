import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

// Gezerken silme: for-each icinde list.remove -> ConcurrentModificationException
// (fail-fast). Dogrusu it.remove() ya da removeIf.
public class GezerkenSilme {
	static List<String> yeniListe() {
		List<String> isimler = new ArrayList<>();
		isimler.add("Ali");
		isimler.add("Can");
		isimler.add("Ayse");   // 3 yapisal degisiklik: modCount = 3
		return isimler;
	}

	public static void main(String[] args) {
		// Yanlis: liste okuyucunun disindan degistiriliyor
		List<String> isimler = yeniListe();
		try {
			for (String s : isimler) {
				if (s.startsWith("A"))
					isimler.remove(s);   // modCount artar, okuyucu habersiz
			}
		} catch (ConcurrentModificationException e) {
			System.out.println("Yanlis: ConcurrentModificationException");
		}
		// "Ali" silindi, "Can" sola kaydi ve hic gezilmeden hata olustu
		System.out.println("Yarim kalan liste: " + isimler);   // [Can, Ayse]

		// Dogru 1: okuyucunun kendi remove() metodu sayaclari esitler
		isimler = yeniListe();
		Iterator<String> it = isimler.iterator();
		while (it.hasNext()) {
			String s = it.next();
			if (s.startsWith("A"))
				it.remove();
		}
		System.out.println("it.remove() ile: " + isimler);   // [Can]

		// it.remove() her next() icin yalnizca bir kez cagrilabilir
		isimler = yeniListe();
		it = isimler.iterator();
		it.next();
		it.remove();
		try {
			it.remove();
		} catch (IllegalStateException e) {
			System.out.println("Ikinci it.remove() -> IllegalStateException");
		}

		// Dogru 2: kisaca removeIf
		isimler = yeniListe();
		isimler.removeIf(s -> s.startsWith("A"));
		System.out.println("removeIf ile: " + isimler);   // [Can]
	}
}
