
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<T> implements Iterable<T> {
	private T[] array;
	private int size;
	private Random random;

	// construct an empty randomized queue
	public RandomizedQueue() {
		array = (T[]) new Object[1];
		size = 0;
		random = new Random();
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the randomized queue
	public int size() {
		return size;
	}

	// add the item
	public void enqueue(T item) {
		if (item == null) {
			throw new IllegalArgumentException("Cannot enqueue a null item.");
		}
		if (size == array.length) {
			resize(2 * array.length);
		}
		array[size++] = item;
	}

	// remove and return a random item
	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Cannot dequeue from an empty randomized queue.");
		}
		int randomIndex = random.nextInt(size);
		T item = array[randomIndex];
		array[randomIndex] = array[--size];
		array[size] = null;
		if (size > 0 && size == array.length / 4) {
			resize(array.length / 2);
		}
		return item;
	}

	// return a random item (but do not remove it)
	public T sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("Cannot sample from an empty randomized queue.");
		}
		int randomIndex = random.nextInt(size);
		return array[randomIndex];
	}

	// return an independent iterator over items in random order
	public Iterator<T> iterator() {
		return new RandomizedQueueIterator();
	}

	private void resize(int capacity) {
		T[] newArray = (T[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	private class RandomizedQueueIterator implements Iterator<T> {
		private final T[] shuffledArray;
		private int currentIndex;

		public RandomizedQueueIterator() {
			shuffledArray = (T[]) new Object[size];
			for (int i = 0; i < size; i++) {
				shuffledArray[i] = array[i];
			}
			for (int i = size - 1; i > 0; i--) {
				int j = random.nextInt(i + 1);
				swap(shuffledArray, i, j);
			}
			currentIndex = 0;
		}

		public boolean hasNext() {
			return currentIndex < size;
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more items to return in the iterator.");
			}
			return shuffledArray[currentIndex++];
		}

		private void swap(T[] arr, int i, int j) {
			T temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	// unit testing (required)
	public static void main(String[] args) {
		// Your unit testing code goes here
		// Example usage:
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		rq.enqueue("A");
		rq.enqueue("B");
		rq.enqueue("C");
		rq.enqueue("D");
		rq.enqueue("E");
		rq.enqueue("F");
		rq.enqueue("G");

		for (String item : rq) {
			System.out.print(item + " ");
		}
		System.out.println();

		System.out.println("Dequeue: " + rq.dequeue());
		System.out.println("Dequeue: " + rq.dequeue());
		System.out.println("Dequeue: " + rq.dequeue());
		System.out.println("Sample: " + rq.sample());
		System.out.println("Sample: " + rq.sample());
		System.out.println("Sample: " + rq.sample());

		for (String item : rq) {
			System.out.print(item + " ");
		}
	}
}
