
public class QueueArrayImplementation {
	
	private int maxSize;
	private int[] queueArray;
	private int front;
	private int rear;
	private int nItems;

	public QueueArrayImplementation(int size) {
		maxSize = size;
		queueArray = new int[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	public void enqueue (int value) {
		if (rear == maxSize - 1) {
			rear = -1;
		}
		queueArray[++rear] = value;
		nItems++;
	}

	public int dequeue() {
		int temp = queueArray[front++];
		if (front == maxSize) {
			front = 0;
		}
		nItems--;
		return temp;
	}

	public int peekFront() {
		return queueArray[front];
	}

	public boolean isEmpty() {
		return nItems == 0;
	}

	public boolean isFull() {
		return nItems == maxSize;
	}

	public int size() {
		return nItems;
	}

	public static void main(String[] args) {
		QueueArrayImplementation myQueue = new QueueArrayImplementation(5);

		myQueue.enqueue(10);
		myQueue.enqueue(20);
		myQueue.enqueue(30);
		myQueue.enqueue(40);

		System.out.println("Kuyruğun İlk Elemanı: " + myQueue.peekFront());

		myQueue.dequeue();
		myQueue.dequeue();

		System.out.println("Kuyruk Boş mu? " + myQueue.isEmpty());
		System.out.println("Kuyruk Dolu mu? " + myQueue.isFull());
		System.out.println("Kuyruk Boyutu: " + myQueue.size());
	}
}
