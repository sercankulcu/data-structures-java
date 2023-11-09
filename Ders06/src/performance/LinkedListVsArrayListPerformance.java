package performance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListVsArrayListPerformance {
	
	final static int SIZE = 10000;

	public static void main(String[] args) {
		// Create a LinkedList and an ArrayList with the same number of elements.
		LinkedList<Integer> linkedList = new LinkedList<>();
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			linkedList.add(i);
			arrayList.add(i);
		}

		// Measure the time it takes to access the first element in the LinkedList and ArrayList.
		Instant start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			int firstLinkedListElement = linkedList.get(i);
		}
		Instant end = Instant.now();
		Duration linkedListAccessTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			Integer firstArrayListElement = arrayList.get(i);
		}
		end = Instant.now();
		Duration arrayListAccessTime = Duration.between(start, end);

		// Measure the time it takes to add an element to the LinkedList and ArrayList.
		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			linkedList.add(1000001);
		}
		end = Instant.now();
		Duration linkedListAddTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.add(1000001);
		}
		end = Instant.now();
		Duration arrayListAddTime = Duration.between(start, end);

		// Print the results.
		System.out.println("LinkedList access time: " + linkedListAccessTime.toMillis() + " ms");
		System.out.println("ArrayList access time: " + arrayListAccessTime.toMillis() + " ms");
		System.out.println("LinkedList add time: " + linkedListAddTime.toMillis() + " ms");
		System.out.println("ArrayList add time: " + arrayListAddTime.toMillis() + " ms");
	}
}