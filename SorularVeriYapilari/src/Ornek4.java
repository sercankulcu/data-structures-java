
import java.util.PriorityQueue;

public class Ornek4 {
	public static void main(String[] args) {
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add("4");
		pq.add("7");
		pq.add("8");
		pq.add("77");
		pq.add("9");
		System.out.println(pq);
		System.out.println(pq.peek() + " "); // 4
		pq.offer("2");
		System.out.println(pq.peek()); // 2
		pq.add("3");
		System.out.println(pq.peek()); // 2
		pq.offer("1");
		System.out.println(pq.peek()); // 1
		pq.remove("1");
		System.out.println(pq.poll() + " "); // 2
		System.out.println(pq.remove("2")); // false
		System.out.println(pq); // 3, 7, 4
		System.out.println(pq.poll() + " " + pq.peek()); // 3 4
	}
}