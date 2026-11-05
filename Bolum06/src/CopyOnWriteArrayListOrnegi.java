import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// CopyOnWriteArrayList: her yazmada ic dizi kopyalanir (O(n)).
// Okuyucu, olusturuldugu andaki diziyi (anlik goruntu) gezer.
public class CopyOnWriteArrayListOrnegi {
	public static void main(String[] args) {
		List<String> liste = new CopyOnWriteArrayList<>(List.of("a", "b", "c"));

		Iterator<String> it = liste.iterator();   // okuyucu dizi A'yi gorur
		System.out.println("it.next() = " + it.next());   // a
		liste.add("d");                           // yeni dizi B = kopya + "d"
		System.out.print("Eski okuyucu devam ediyor: ");
		while (it.hasNext()) {
			System.out.print(it.next() + " ");    // b c ("d" gorunmez, hata yok)
		}
		System.out.println();
		System.out.println("Yeni okuyucu: " + liste);   // [a, b, c, d]

		// Gezerken listeye yazmak serbest: dongu anlik goruntu uzerinde doner
		for (String s : liste) {
			if (s.equals("b"))
				liste.remove(s);
		}
		System.out.println("Gezerken silme sonrasi: " + liste);   // [a, c, d]

		// Okuyucu uzerinden silme desteklenmez
		try {
			Iterator<String> it2 = liste.iterator();
			it2.next();
			it2.remove();
		} catch (UnsupportedOperationException e) {
			System.out.println("it.remove() -> UnsupportedOperationException");
		}

		// Karsilastirma: ArrayList'te ayni durum fail-fast hata verir
		List<String> al = new ArrayList<>(List.of("a", "b", "c"));
		Iterator<String> it3 = al.iterator();
		it3.next();
		al.add("d");
		try {
			it3.next();
		} catch (ConcurrentModificationException e) {
			System.out.println("ArrayList -> ConcurrentModificationException");
		}
	}
}
