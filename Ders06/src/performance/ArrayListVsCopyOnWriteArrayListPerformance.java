package performance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListVsCopyOnWriteArrayListPerformance {

	// TODO: try with different SIZE values
	final static int SIZE = 50000;

	public static void main(String[] args) {
		// Create an ArrayList and a CopyOnWriteArrayList with the same number of elements.
		ArrayList<Integer> arrayList = new ArrayList<>();
		CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			arrayList.add(i);
			copyOnWriteArrayList.add(i);
		}

		// Measure the time it takes to access the first element in the ArrayList and CopyOnWriteArrayList.
		Instant start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.get(i);
		}
		Instant end = Instant.now();
		Duration arrayListAccessTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			copyOnWriteArrayList.get(i);
		}
		end = Instant.now();
		Duration copyOnWriteArrayListAccessTime = Duration.between(start, end);

		// Measure the time it takes to add an element to the ArrayList and CopyOnWriteArrayList.
		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.add(i);
		}
		end = Instant.now();
		Duration arrayListAddTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			copyOnWriteArrayList.add(i);
		}
		end = Instant.now();
		Duration copyOnWriteArrayListAddTime = Duration.between(start, end);

		// Measure the time it takes to iterate over the ArrayList and CopyOnWriteArrayList.
		start = Instant.now();
		Collections.sort(arrayList);
		end = Instant.now();
		Duration arrayListIterationTime = Duration.between(start, end);

		start = Instant.now();
		Collections.sort(copyOnWriteArrayList);
		end = Instant.now();
		Duration copyOnWriteArrayListIterationTime = Duration.between(start, end);

		// Measure the time it takes to remove an element from the ArrayList and CopyOnWriteArrayList.
		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
		// TODO: try with different values
			arrayList.remove(i);
		}
		end = Instant.now();
		Duration arrayListRemoveTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
		// TODO: try with different values
			copyOnWriteArrayList.remove(i);
		}
		end = Instant.now();
		Duration copyOnWriteArrayListRemoveTime = Duration.between(start, end);

		// Print the results.
		System.out.println("ArrayList access time: " + arrayListAccessTime.toMillis() + " ms");
		System.out.println("CopyOnWriteArrayList access time: " + copyOnWriteArrayListAccessTime.toMillis() + " ms");
		System.out.println("ArrayList add time: " + arrayListAddTime.toMillis() + " ms");
		System.out.println("CopyOnWriteArrayList add time: " + copyOnWriteArrayListAddTime.toMillis() + " ms");
		System.out.println("ArrayList iteration time: " + arrayListIterationTime.toMillis() + " ms");
		System.out.println("CopyOnWriteArrayList iteration time: " + copyOnWriteArrayListIterationTime.toMillis() + " ms");
		System.out.println("ArrayList remove time: " + arrayListRemoveTime.toMillis() + " ms");
		System.out.println("CopyOnWriteArrayList remove time: " + copyOnWriteArrayListRemoveTime.toMillis() + " ms");
		
	}
}