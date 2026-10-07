import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Deque uygulamasi: kayan pencerede en buyuk
// d indis tutar; degerler onden arkaya azalir, onde pencerenin en buyugu durur.
// Her indis bir kez girer, en cok bir kez cikar: O(n) (kaba kuvvet O(n*k)).
public class KayanPencereMaksimum {

	static int[] pencereMax(int[] a, int k) {
		int[] enb = new int[a.length - k + 1];
		Deque<Integer> d = new ArrayDeque<>(); // indis
		for (int i = 0; i < a.length; i++) {
			if (!d.isEmpty() && d.peekFirst() <= i - k)
				d.pollFirst();        // pencere disi
			while (!d.isEmpty() && a[d.peekLast()] <= a[i])
				d.pollLast();         // aday degil
			d.offerLast(i);
			if (i >= k - 1) enb[i - k + 1] = a[d.peekFirst()];
		}
		return enb;
	}

	public static void main(String[] args) {
		// Slayt 37
		int[] a = {1, 3, -1, -3, 5, 3, 6};
		int k = 3;
		System.out.println("a = " + Arrays.toString(a) + ", k = " + k);
		System.out.println("enb = " + Arrays.toString(pencereMax(a, k)));   // [3, 3, 5, 5, 6]
	}
}
