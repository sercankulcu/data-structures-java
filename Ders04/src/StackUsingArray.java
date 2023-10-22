public class StackUsingArray {
	
	private int maxSize;
	private int top;
	private int[] stackArray;

	public StackUsingArray(int size) {
		maxSize = size;
		stackArray = new int[maxSize];
		top = -1; // Initialize the top pointer to -1 (empty stack).
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public boolean isFull() {
		return (top == maxSize - 1);
	}

	public void push(int value) {
		if (isFull()) {
			System.out.println("Stack is full. Cannot push " + value);
		} else {
			stackArray[++top] = value;
			System.out.println("Pushed " + value + " onto the stack.");
		}
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("Stack is empty. Cannot pop.");
			return -1; // Return a sentinel value for an empty stack.
		} else {
			int poppedValue = stackArray[top--];
			System.out.println("Popped " + poppedValue + " from the stack.");
			return poppedValue;
		}
	}

	public int peek() {
		if (isEmpty()) {
			System.out.println("Stack is empty. Cannot peek.");
			return -1; // Return a sentinel value for an empty stack.
		} else {
			return stackArray[top];
		}
	}

	public static void main(String[] args) {
		StackUsingArray stack = new StackUsingArray(5); // Create a stack with a maximum size of 5.

		stack.push(10);
		stack.push(20);
		stack.push(30);

		System.out.println("Top element: " + stack.peek());

		stack.pop();
		stack.pop();

		System.out.println("Is the stack empty? " + stack.isEmpty());

		stack.pop();
		stack.pop(); // Trying to pop from an empty stack.

		System.out.println("Is the stack full? " + stack.isFull());
	}
}
