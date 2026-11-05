import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// LinkedList: cift yonlu bagli liste. Uclarda O(1), indisle erisim O(n).
public class LinkedListOrnegi {
	public static void main(String[] args) {
		LinkedList<String> liste = new LinkedList<>();
		liste.add("B");            // add = addLast
		System.out.println(liste);            // [B]
		liste.addFirst("A");       // O(1), kaydirma yok
		System.out.println(liste);            // [A, B]
		liste.addLast("C");        // last sayesinde O(1)
		System.out.println(liste);            // [A, B, C]
		String s = liste.get(1);   // dugumleri yuruyerek: O(n)
		System.out.println("get(1) = " + s);  // B
		liste.removeFirst();       // O(1)
		System.out.println(liste);            // [B, C]
		System.out.println("size() = " + liste.size());   // 2

		// Indisli dongu: her get(i) yakin uctan yurur -> toplam O(n^2)
		List<String> l = new LinkedList<>(List.of("A", "B", "C", "D", "E", "F"));
		int n = l.size(), adim = 0;
		for (int i = 0; i < n; i++) {
			l.get(i);                         // node(i) yurur
			adim += Math.min(i, n - 1 - i);   // yurunen baglanti sayisi
		}
		System.out.println("Indisli dongu toplam adim: " + adim);   // 6

		// Iterator her next()'te tek baglanti ilerler -> toplam n - 1 adim, O(n)
		int adimIt = 0;
		Iterator<String> it = l.iterator();
		it.next();                            // ilk oge: first, yurume yok
		while (it.hasNext()) {
			it.next();
			adimIt++;
		}
		System.out.println("Iterator toplam adim: " + adimIt);       // 5
	}
}
