
import java.util.Stack;

public class Ornek6 {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		int n = 12;
		while (n > 0) {
			stack.push(n % 2);
			n /= 2;
		}

		while (!stack.isEmpty())
			System.out.print(stack.pop());
	}
}