
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;

public class ArrayListDemo {

	public static void main(String[] args) {
		// replaceAll()
		List<String> isimler = new ArrayList<>();
		isimler.add("Ali");
		isimler.add("Ahmet");
		isimler.add("Mehmet");

		String ilkIsim = isimler.get(0);
		System.out.println(ilkIsim);

		isimler.replaceAll(String::toUpperCase);

		System.out.println(isimler); // [ALI, AHMET, MEHMET]

		// isimler listesi için bir ListIterator oluşturun.
		ListIterator<String> isimlerListIterator = isimler.listIterator();

		// Listeyi ileri yönde yineleyin.
		while (isimlerListIterator.hasNext()) {
			String isim = isimlerListIterator.next();
			System.out.println(isim);
		}

		// Listeyi ters yönde yineleyin.
		while (isimlerListIterator.hasPrevious()) {
			String isim = isimlerListIterator.previous();
			System.out.println(isim);
		}

		// isimler listesi için bir iterator oluşturun.
		Iterator<String> isimlerIterator = isimler.iterator();

		while (isimlerIterator.hasNext()) {
			String isim = isimlerIterator.next();
			System.out.println(isim);
		}

		// sort()
		List<Integer> sayilar = new ArrayList<>();
		sayilar.add(5);
		sayilar.add(3);
		sayilar.add(1);

		sayilar.sort(Integer::compare);

		System.out.println(sayilar); // [1, 3, 5]

		// spliterator()
		List<String> kelimeler = new ArrayList<>();
		kelimeler.add("Java");
		kelimeler.add("Python");
		kelimeler.add("C++");

		Spliterator<String> kelimeSpliterator = kelimeler.spliterator();

		while (kelimeSpliterator.tryAdvance(System.out::println)) {
			// do something with the word
		}

	}
}