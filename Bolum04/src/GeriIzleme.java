import java.util.ArrayList;
import java.util.List;

// Geri izleme: her adimda bir secim yap, ozyinelemeyle ilerle, donunce secimi geri al.
public class GeriIzleme {

	// Her oge icin iki secim (al / alma): 2^n alt kume
	static void altKumeler(int[] a, int i, List<Integer> s) {
		if (i == a.length) {              // taban durum
			System.out.print(s + " "); return;
		}
		s.add(a[i]);                      // a[i]'yi al
		altKumeler(a, i + 1, s);
		s.remove(s.size() - 1);           // geri al
		altKumeler(a, i + 1, s);          // a[i]'siz dene
	}

	// Her duzeyde kalan harflerden biri secilir: n! permutasyon
	static void perm(char[] h, boolean[] kul, StringBuilder s) {
		if (s.length() == h.length) {  // taban durum
			System.out.print(s + " "); return;
		}
		for (int k = 0; k < h.length; k++) {
			if (kul[k]) continue;        // harf kullanildi
			kul[k] = true; s.append(h[k]);   // sec
			perm(h, kul, s);
			kul[k] = false;              // geri al
			s.deleteCharAt(s.length() - 1);
		}
	}

	public static void main(String[] args) {
		altKumeler(new int[]{1, 2, 3}, 0, new ArrayList<>());
		System.out.println();   // [1, 2, 3] [1, 2] [1, 3] [1] [2, 3] [2] [3] []

		char[] h = {'A', 'B', 'C'};
		perm(h, new boolean[h.length], new StringBuilder());
		System.out.println();   // ABC ACB BAC BCA CAB CBA
	}
}
