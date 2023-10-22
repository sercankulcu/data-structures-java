
public class LinkedListStack {
	
	class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node top;

	public LinkedListStack() {
		top = null; // Initialize the top of the stack to null (empty stack).
	}

	public boolean isEmpty() {
		return top == null;
	}

	public void push(int value) {
		Node newNode = new Node(value);
		newNode.next = top;
		top = newNode;
		System.out.println("Pushed " + value + " onto the stack.");
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("Stack is empty. Cannot pop.");
			return -1; // Return a sentinel value for an empty stack.
		} else {
			int poppedValue = top.data;
			top = top.next;
			System.out.println("Popped " + poppedValue + " from the stack.");
			return poppedValue;
		}
	}

	public int peek() {
		if (isEmpty()) {
			System.out.println("Stack is empty. Cannot peek.");
			return -1; // Return a sentinel value for an empty stack.
		} else {
			return top.data;
		}
	}

	public static void main(String[] args) {
		LinkedListStack stack = new LinkedListStack();

		stack.push(10);
		stack.push(20);
		stack.push(30);

		System.out.println("Top element: " + stack.peek());

		stack.pop();
		stack.pop();

		System.out.println("Is the stack empty? " + stack.isEmpty());

		stack.pop();
		stack.pop(); // Trying to pop from an empty stack.

		System.out.println("Is the stack empty? " + stack.isEmpty());
	}
}
