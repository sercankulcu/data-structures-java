
public class Dequeue<E> {
	
	private CiftYonluDugum<E> bas;
	private CiftYonluDugum<E> son;

	public void basaEkle(E veri) {
		CiftYonluDugum<E> yeniDugum = new CiftYonluDugum<E>(veri);
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			yeniDugum.sonraki = bas;
			bas.onceki = yeniDugum;
			bas = yeniDugum;
		}
	}

	public void sonaEkle(E veri) {
		CiftYonluDugum<E> yeniDugum = new CiftYonluDugum<E>(veri);
		if (bosMu()) {
			bas = yeniDugum;
			son = yeniDugum;
		} else {
			yeniDugum.onceki = son;
			son.sonraki = yeniDugum;
			son = yeniDugum;
		}
	}

	public E bastanCikar() {
		if (bosMu()) {
			throw new IllegalStateException("Dequeue boş");
		}
		E veri = bas.veri;
		bas = bas.sonraki;
		if (bas != null) {
			bas.onceki = null;
		}
		return veri;
	}

	public E sondanCikar() {
		if (bosMu()) {
			throw new IllegalStateException("Dequeue boş");
		}
		E veri = son.veri;
		son = son.onceki;
		if (son != null) {
			son.sonraki = null;
		}
		return veri;
	}

	public E basaBak() {
		if (bosMu()) {
			throw new IllegalStateException("Dequeue boş");
		}
		return bas.veri;
	}

	public E sonaBak() {
		if (bosMu()) {
			throw new IllegalStateException("Dequeue boş");
		}
		return son.veri;
	}

	public boolean bosMu() {
		return bas == null;
	}

	public void dequeueGoster() {
		if (bosMu()) {
			System.out.println("Dequeue boş.");
			return;
		}
		CiftYonluDugum<E> current = bas;
		System.out.print("Dequeue Öğeleri: ");
		while (current != null) {
			System.out.print(current.veri + " ");
			current = current.sonraki;
		}
		System.out.println();
	}
	
public static void main(String[] args) {
		
		Dequeue<String> dequeue = new Dequeue<>();

		// Dequeue'in başına ve sonuna öğeleri ekleyelim
		dequeue.basaEkle("Öğe 1");
		dequeue.sonaEkle("Öğe 2");
		dequeue.sonaEkle("Öğe 3");
		dequeue.basaEkle("Öğe 4");

		// Dequeue'in içeriğini gösterelim
		dequeue.dequeueGoster();

		// Dequeue'in başından ve sonundan öğeleri çıkaralım
		String bastanCikarilan = dequeue.bastanCikar();
		String sondanCikarilan = dequeue.sondanCikar();

		System.out.println("Baştan Çıkarılan Öğe: " + bastanCikarilan);
		System.out.println("Sondan Çıkarılan Öğe: " + sondanCikarilan);

		// Güncel içeriği gösterelim
		dequeue.dequeueGoster();

		// Dequeue'in başındaki ve sonundaki öğelere bakalım
		String basaBak = dequeue.basaBak();
		String sonaBak = dequeue.sonaBak();

		System.out.println("Baştaki Öğe: " + basaBak);
		System.out.println("Sondaki Öğe: " + sonaBak);

		// Dequeue'in boş olup olmadığını kontrol edelim
		boolean bosMu = dequeue.bosMu();
		System.out.println("Dequeue Boş mu? " + bosMu);
	}
}
