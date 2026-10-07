// Konumsal liste (positional list) ADT: Goodrich-Tamassia.
// Konum (Position) ogeye bagli kalir; bekci header/trailer dugumleri sayesinde
// her ekleme iki gercek dugum arasina yapilir. Tum islemler O(1).
public class KonumsalListe<E> {

	// Konum yalnizca ogesini verir; bagli gerceklestirimde konum = dugum
	public interface Position<E> {
		E getElement();
	}

	private static class Dugum<E> implements Position<E> {
		E veri;
		Dugum<E> onceki, sonraki;

		Dugum(Dugum<E> o, E e, Dugum<E> s) {
			onceki = o;
			veri = e;
			sonraki = s;
		}

		@Override
		public E getElement() {
			if (sonraki == null)   // silinmis dugumun baglari null yapilir
				throw new IllegalStateException("Konum artik gecerli degil");
			return veri;
		}
	}

	private final Dugum<E> header = new Dugum<>(null, null, null);   // bas bekci
	private final Dugum<E> trailer = new Dugum<>(header, null, null); // son bekci
	private int size = 0;

	public KonumsalListe() {
		header.sonraki = trailer;
	}

	// Konum bu listenin gecerli bir dugumu mu? Oyleyse Dugum'e donustur
	private Dugum<E> validate(Position<E> p) {
		if (!(p instanceof Dugum<E> d))
			throw new IllegalArgumentException("Gecersiz konum");
		if (d.sonraki == null)
			throw new IllegalArgumentException("Konum artik listede degil");
		return d;
	}

	// Bekci dugumler disariya konum olarak verilmez
	private Position<E> konum(Dugum<E> d) {
		return (d == header || d == trailer) ? null : d;
	}

	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	public Position<E> first() { return konum(header.sonraki); }
	public Position<E> last() { return konum(trailer.onceki); }
	public Position<E> before(Position<E> p) { return konum(validate(p).onceki); }
	public Position<E> after(Position<E> p) { return konum(validate(p).sonraki); }

	// Yeni dugum iki komsusunu gosterir, sonra komsular yeni dugumu: 4 atama
	private Position<E> addBetween(E e, Dugum<E> o, Dugum<E> s) {
		Dugum<E> yeni = new Dugum<>(o, e, s);
		o.sonraki = yeni;
		s.onceki = yeni;
		size++;
		return yeni;
	}

	public Position<E> addFirst(E e) { return addBetween(e, header, header.sonraki); }
	public Position<E> addLast(E e) { return addBetween(e, trailer.onceki, trailer); }

	public Position<E> addBefore(Position<E> p, E e) {
		Dugum<E> d = validate(p);
		return addBetween(e, d.onceki, d);
	}

	public Position<E> addAfter(Position<E> p, E e) {
		Dugum<E> d = validate(p);
		return addBetween(e, d, d.sonraki);
	}

	public E set(Position<E> p, E e) {
		Dugum<E> d = validate(p);
		E eski = d.veri;
		d.veri = e;
		return eski;
	}

	public E remove(Position<E> p) {
		Dugum<E> d = validate(p);
		d.onceki.sonraki = d.sonraki;   // komsular birbirine baglanir
		d.sonraki.onceki = d.onceki;
		size--;
		E veri = d.veri;
		d.veri = null;                  // dugum gecersiz hale getirilir
		d.onceki = d.sonraki = null;
		return veri;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (Dugum<E> d = header.sonraki; d != trailer; d = d.sonraki) {
			sb.append(d.veri);
			if (d.sonraki != trailer)
				sb.append(", ");
		}
		return sb.append("]").toString();
	}

	public static void main(String[] args) {
		KonumsalListe<String> liste = new KonumsalListe<>();
		Position<String> p = liste.addFirst("A");
		Position<String> c = liste.addLast("C");
		System.out.println(liste);                          // [A, C]

		Position<String> b = liste.addAfter(p, "B");        // O(1): yurume yok
		System.out.println("addAfter(p, B): " + liste);     // [A, B, C]
		System.out.println("after(p) = " + liste.after(p).getElement());    // B
		System.out.println("before(c) = " + liste.before(c).getElement());  // B

		liste.addBefore(p, "X");                            // en basa
		System.out.println("addBefore(p, X): " + liste);    // [X, A, B, C]
		// Indisler kaydi ama konumlar ogeye bagli kaldi
		System.out.println("p hala " + p.getElement());     // A

		liste.set(b, "b");
		System.out.println("set(b, b): " + liste);          // [X, A, b, C]
		System.out.println("remove(p) = " + liste.remove(p));   // A
		System.out.println(liste + ", size = " + liste.size()); // [X, b, C], 3
		System.out.println("first = " + liste.first().getElement()
				+ ", last = " + liste.last().getElement());     // X, C
		try {
			liste.remove(p);                                // silinmis konum
		} catch (IllegalArgumentException e) {
			System.out.println("Silinmis konum -> IllegalArgumentException");
		}
	}
}
