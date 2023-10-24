
public class QueueLinkedListImplementation {
	
	public static void main(String[] args) {
		
		CustomQueue<String> queue = new CustomQueue<>();

		// Enqueue (add) elements to the queue
		queue.enqueue("Element 1");
		queue.enqueue("Element 2");
		queue.enqueue("Element 3");
		queue.enqueue("Element 4");

		// Display the elements in the queue
		queue.displayQueue();

		// Dequeue (remove) elements from the queue
		String dequeuedElement = queue.dequeue();
		System.out.println("Dequeued Element: " + dequeuedElement);

		// Display the updated elements in the queue
		queue.displayQueue();

		// Peek at the front element without removing it
		String frontElement = queue.peek();
		System.out.println("Front Element: " + frontElement);

		// Check if the queue is empty
		boolean isEmpty = queue.isEmpty();
		System.out.println("Is the Queue Empty? " + isEmpty);

		// Get the size of the queue
		int size = queue.size();
		System.out.println("Queue Size: " + size);
	}
}

class CustomQueue<E> {
	private Node<E> front;
	private Node<E> rear;
	private int size;

	// Node class to represent elements in the linked list
	private static class Node<E> {
		E data;
		Node<E> next;

		Node(E data) {
			this.data = data;
			this.next = null;
		}
	}

	// Enqueue (add) an element to the end of the queue
	public void enqueue(E item) {
		Node<E> newNode = new Node<>(item);
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.next = newNode;
			rear = newNode;
		}
		size++;
	}

	// Dequeue (remove) and return the front element from the queue
	public E dequeue() {
		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}
		E data = front.data;
		front = front.next;
		size--;
		return data;
	}

	// Peek at the front element without removing it
	public E peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}
		return front.data;
	}

	// Check if the queue is empty
	public boolean isEmpty() {
		return size == 0;
	}

	// Get the size of the queue
	public int size() {
		return size;
	}

	// Display the elements in the queue
	public void displayQueue() {
		Node<E> current = front;
		System.out.print("Queue Elements: ");
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();
	}
}
