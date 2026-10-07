import java.util.ArrayDeque;
import java.util.Queue;

// Uygulama: Josephus problemi
// Her turda ondeki k - 1 kisi arkaya gecer, k. kisi elenir. O(n*k) kuyruk islemi.
public class Josephus {

	static String josephus(Queue<String> q, int k) {
		while (q.size() > 1) {
			for (int i = 1; i < k; i++)
				q.offer(q.poll());      // ondekini arkaya
			String elenen = q.poll();
			System.out.println(elenen + " elendi");
		}
		return q.poll();
	}

	public static void main(String[] args) {
		// Slayt 40: n = 5, k = 3
		Queue<String> q = new ArrayDeque<>();
		for (String kisi : new String[] {"A", "B", "C", "D", "E"})
			q.offer(kisi);
		System.out.println("Kazanan: " + josephus(q, 3));   // C, A, E, B elenir; kazanan D

		// Alistirma: 1..7, k = 3
		System.out.println();
		Queue<String> q2 = new ArrayDeque<>();
		for (int i = 1; i <= 7; i++)
			q2.offer(String.valueOf(i));
		System.out.println("Kazanan: " + josephus(q2, 3));  // 3, 6, 2, 7, 5, 1 elenir; kazanan 4
	}
}
