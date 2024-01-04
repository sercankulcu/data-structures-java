package performance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class LinkedListVsArrayListPerformance {

	// TODO: try with different SIZE values
	static final int SIZE = 50000;

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
			linkedList.get(i);
		}
		Instant end = Instant.now();
		Duration linkedListAccessTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.get(i);
		}
		end = Instant.now();
		Duration arrayListAccessTime = Duration.between(start, end);

		// Measure the time it takes to add an element to the LinkedList and ArrayList.
		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			linkedList.add(i);
		}
		end = Instant.now();
		Duration linkedListAddTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.add(i);
		}
		end = Instant.now();
		Duration arrayListAddTime = Duration.between(start, end);

		// Measure the time it takes to iterate over the ArrayList and LinkedList.
		start = Instant.now();
		Collections.sort(linkedList);
		end = Instant.now();
		Duration linkedListIterationTime = Duration.between(start, end);
		
		start = Instant.now();
		Collections.sort(arrayList);
		end = Instant.now();
		Duration arrayListIterationTime = Duration.between(start, end);

		// Measure the time it takes to remove an element from the ArrayList and LinkedList.
		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			// TODO: try with different values
			linkedList.remove(i);
		}
		end = Instant.now();
		Duration linkedListRemoveTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			// TODO: try with different values
			arrayList.remove(i);
		}
		end = Instant.now();
		Duration arrayListRemoveTime = Duration.between(start, end);

		// Print the results.
		System.out.println("LinkedList access time: " + linkedListAccessTime.toMillis() + " ms");
		System.out.println("ArrayList access time: " + arrayListAccessTime.toMillis() + " ms");
		System.out.println("LinkedList add time: " + linkedListAddTime.toMillis() + " ms");
		System.out.println("ArrayList add time: " + arrayListAddTime.toMillis() + " ms");
		System.out.println("LinkedList iteration time: " + linkedListIterationTime.toMillis() + " ms");
		System.out.println("ArrayList iteration time: " + arrayListIterationTime.toMillis() + " ms");
		System.out.println("LinkedList remove time: " + linkedListRemoveTime.toMillis() + " ms");
		System.out.println("ArrayList remove time: " + arrayListRemoveTime.toMillis() + " ms");
	}
}