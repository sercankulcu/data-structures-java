
import java.util.Stack;

public class Ornek1 {
	public static void main(String args[]) {
		Stack<String> stack = new Stack<>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.remove("A"));
		System.out.println(stack.pop());
		System.out.println(stack.add("D"));
		System.out.println(stack.remove("B"));
	}
}