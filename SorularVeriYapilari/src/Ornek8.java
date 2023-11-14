import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Ornek8 {
	
	public static void main(String args[]) {
		Stack<String> s = new Stack<>();
		Queue<String> q = new LinkedList<>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		
		while(!q.isEmpty())
			s.push(q.poll());
		System.out.println("s: " + s + " --- q: " + q);
		
		while(!s.isEmpty())
			q.offer(s.pop());

		System.out.println("s: " + s + " --- q: " + q);
	}
}