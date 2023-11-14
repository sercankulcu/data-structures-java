
import java.util.PriorityQueue;

public class Ornek4 {
	public static void main(String[] args) {
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add("7");
		pq.add("4");
		System.out.println(pq.peek() + " ");
		pq.offer("2");
		System.out.println(pq.peek());
		pq.add("3");
		System.out.println(pq.peek());
		pq.offer("1");
		System.out.println(pq.peek());
		pq.remove("1");
		System.out.println(pq.poll() + " ");
		System.out.println(pq.remove("2"));
		System.out.println(pq);
		System.out.println(pq.poll() + " " + pq.peek());
	}
}