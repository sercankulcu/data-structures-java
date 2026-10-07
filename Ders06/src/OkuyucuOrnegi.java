import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;

// Okuyucular (iterator): Iterator, ListIterator ve Spliterator ile gezinme.
public class OkuyucuOrnegi {
	public static void main(String[] args) {
		List<String> isimler = new ArrayList<>(List.of("Ali", "Ahmet", "Mehmet"));

		// 1) Gezinmenin uc yolu: indisli for, forEach, Iterator
		System.out.println("--- indisli for ---");
		int n = isimler.size();
		for (int i = 0; i < n; i++) {
			System.out.println(i + ": " + isimler.get(i));
		}
		System.out.println("--- forEach ---");
		isimler.forEach(System.out::println);
		System.out.println("--- Iterator ---");
		Iterator<String> iterator = isimler.iterator();
		while (iterator.hasNext()) {
			String isim = iterator.next();
			System.out.println(isim);
		}
		try {
			iterator.next();   // imlec sonda
		} catch (NoSuchElementException e) {
			System.out.println("Bir kez daha next() -> NoSuchElementException");
		}

		// 2) ListIterator: ayni okuyucuyla once ileri, sonra geri
		System.out.println("--- ListIterator ileri ve geri ---");
		ListIterator<String> li = isimler.listIterator();
		while (li.hasNext()) {
			System.out.println(li.next());
		}
		// imlec artik sonda: ters yonde gez
		while (li.hasPrevious()) {
			System.out.println(li.previous());
		}

		// 3) ListIterator ile gezinirken set / add / remove
		System.out.println("--- ListIterator set, add, remove ---");
		ListIterator<String> it = isimler.listIterator();
		it.next();            // "Ali"
		it.next();            // "Ahmet"
		it.set("AHMET");      // son donen oge degisir
		System.out.println(isimler);   // [Ali, AHMET, Mehmet]
		it.add("Ayse");       // imlecin onune eklenir
		System.out.println(isimler);   // [Ali, AHMET, Ayse, Mehmet]
		System.out.println("previous() -> " + it.previous());   // Ayse
		it.remove();          // son donen ("Ayse") silinir
		System.out.println(isimler);   // [Ali, AHMET, Mehmet]
		try {
			it.remove();      // araya next/previous girmeden ikinci remove
		} catch (IllegalStateException e) {
			System.out.println("Ikinci remove() -> IllegalStateException");
		}

		// 4) Spliterator: trySplit ile ikiye bol, tryAdvance ile parca parca isle
		System.out.println("--- Spliterator ---");
		List<String> kelimeler = new ArrayList<>(List.of("Java", "Python", "C++", "Go"));
		Spliterator<String> a = kelimeler.spliterator();
		Spliterator<String> b = a.trySplit();   // b: ilk yari [0, 2), a: [2, 4)
		System.out.println("b parcasi:");
		while (b.tryAdvance(System.out::println)) {
			// her cagri bir ogeyi isler
		}
		System.out.println("a parcasi:");
		while (a.tryAdvance(System.out::println)) {
			// her cagri bir ogeyi isler
		}
	}
}
