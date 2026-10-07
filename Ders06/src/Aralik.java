import java.util.Iterator;
import java.util.NoSuchElementException;

// Kendi Iterable sinifimiz: [bas, son) araligindaki tam sayilari uretir.
// for-each dongusu gizlice iterator() cagirir; hasNext/next ile gezer.
public class Aralik implements Iterable<Integer> {
	private final int bas, son;

	Aralik(int b, int s) {
		bas = b;
		son = s;
	}

	@Override
	public Iterator<Integer> iterator() {
		// Isimsiz ic sinif: her iterator() cagrisi kendi imlecini (k) tutar
		return new Iterator<Integer>() {
			int k = bas;

			@Override
			public boolean hasNext() {
				return k < son;
			}

			@Override
			public Integer next() {
				// Sozlesme: oge yokken next() -> NoSuchElementException
				if (k >= son)
					throw new NoSuchElementException();
				return k++;
			}
		};
	}

	public static void main(String[] args) {
		// for-each: derleyici bunu iterator()/hasNext()/next() dongusune cevirir
		for (int x : new Aralik(2, 5)) {
			System.out.println("x = " + x);
		}

		// Ayni gezinme, okuyucuyu (iterator) acikca kullanarak
		Iterator<Integer> it = new Aralik(2, 5).iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		// Ogeler bittikten sonra next() cagrilirsa
		try {
			it.next();
		} catch (NoSuchElementException e) {
			System.out.println("Oge kalmadi: NoSuchElementException");
		}
	}
}
