// Cift uclu kuyruk (deque): cift yonlu bagli liste ile
// Her iki uctan ekleme ve cikarma O(1). Bos deque'de cikarma null dondurur.
public class CiftUcluKuyruk<E> {

	// Cift yonlu bagli liste dugumu
	static class Dugum<E> {
		E veri;
		Dugum<E> onceki, sonraki;
		Dugum(E veri) { this.veri = veri; }
	}

	private Dugum<E> bas, son;   // on ve arka uclar
	private int boyut = 0;

	public boolean bosMu() { return boyut == 0; }
	public int boyut()     { return boyut; }
	public E ilk()         { return bosMu() ? null : bas.veri; }
	public E son()         { return bosMu() ? null : son.veri; }

	public void basaEkle(E veri) {
		Dugum<E> d = new Dugum<>(veri);
		if (bosMu()) {
			bas = son = d;
		} else {
			d.sonraki = bas;
			bas.onceki = d;
			bas = d;
		}
		boyut++;
	}

	// basaEkle'nin aynadaki goruntusu: bas <-> son, onceki <-> sonraki
	public void sonaEkle(E veri) {
		Dugum<E> d = new Dugum<>(veri);
		if (bosMu()) {
			bas = son = d;
		} else {
			d.onceki = son;
			son.sonraki = d;
			son = d;
		}
		boyut++;
	}

	public E bastanCikar() {
		if (bosMu()) return null;
		E veri = bas.veri;
		bas = bas.sonraki;
		if (bas == null)
			son = null;       // bosaldi
		else
			bas.onceki = null;
		boyut--;
		return veri;
	}

	public E sondanCikar() {
		if (bosMu()) return null;
		E veri = son.veri;
		son = son.onceki;     // onceki sayesinde sondan bir oncekini aramaya gerek yok
		if (son == null)
			bas = null;       // bosaldi
		else
			son.sonraki = null;
		boyut--;
		return veri;
	}

	// Ogeleri bastan sona yazdirir
	public void goster() {
		System.out.print("Deque (bas -> son):");
		for (Dugum<E> d = bas; d != null; d = d.sonraki)
			System.out.print(" " + d.veri);
		System.out.println("  boyut = " + boyut);
	}

	public static void main(String[] args) {
		// Slayt 32
		CiftUcluKuyruk<Integer> deque = new CiftUcluKuyruk<>();
		deque.sonaEkle(5);
		deque.sonaEkle(8);
		deque.basaEkle(3);
		deque.sonaEkle(9);
		deque.goster();                                                  // 3 5 8 9
		System.out.println("ilk() = " + deque.ilk() + ", son() = " + deque.son());
		System.out.println("sondanCikar() -> " + deque.sondanCikar());   // 9
		System.out.println("bastanCikar() -> " + deque.bastanCikar());   // 3
		deque.goster();                                                  // 5 8

		// Slayt 36: son oge cikinca iki uc da null olmali
		System.out.println("sondanCikar() -> " + deque.sondanCikar());   // 8
		System.out.println("sondanCikar() -> " + deque.sondanCikar());   // 5
		System.out.println("Bos mu? " + deque.bosMu() + ", ilk() = " + deque.ilk() + ", son() = " + deque.son());
		System.out.println("Bos deque'de bastanCikar() -> " + deque.bastanCikar());
	}
}
