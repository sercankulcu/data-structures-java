import java.util.PriorityQueue;

// k'inci en buyuk oge: boyutu en cok k olan bir min-heap tutulur, O(n log k)
public class KinciEnBuyuk {

	static int kinciEnBuyuk(int[] a, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // min-heap
		for (int x : a) {
			pq.offer(x); // O(log k)
			if (pq.size() > k) pq.poll(); // en kucugu at
		}
		return pq.peek(); // k buyugun en kucugu
	}

	public static void main(String[] args) {
		int[] a = {5, 1, 9, 3, 7, 6};
		for (int k = 1; k <= 3; k++) {
			System.out.println(k + ". en buyuk: " + kinciEnBuyuk(a, k));
		}
	}
}
