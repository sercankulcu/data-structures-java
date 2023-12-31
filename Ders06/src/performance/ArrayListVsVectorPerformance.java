package performance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class ArrayListVsVectorPerformance {

	// TODO: try with different SIZE values
	final static int SIZE = 100000;

	public static void main(String[] args) {
		// Create an ArrayList and a Vector with the same number of elements.
		ArrayList<Integer> arrayList = new ArrayList<>();
		Vector<Integer> vector = new Vector<>();
		for (int i = 0; i < SIZE; i++) {
			arrayList.add(i);
			vector.add(i);
		}

		// Measure the time it takes to access the first element in the ArrayList and Vector.
		Instant start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.get(i);
		}
		Instant end = Instant.now();
		Duration arrayListAccessTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			vector.get(i);
		}
		end = Instant.now();
		Duration vectorAccessTime = Duration.between(start, end);

		// Measure the time it takes to add an element to the ArrayList and Vector.
		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.add(i);
		}
		end = Instant.now();
		Duration arrayListAddTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			vector.add(i);
		}
		end = Instant.now();
		Duration vectorAddTime = Duration.between(start, end);

		// Measure the time it takes to iterate over the ArrayList and LinkedList.
		start = Instant.now();
		Collections.sort(arrayList);
		end = Instant.now();
		Duration arrayListIterationTime = Duration.between(start, end);

		start = Instant.now();
		Collections.sort(vector);
		end = Instant.now();
		Duration vectorIterationTime = Duration.between(start, end);

		// Measure the time it takes to remove an element from the ArrayList and LinkedList.
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
			vector.remove(i);
		}
		end = Instant.now();
		Duration vectorRemoveTime = Duration.between(start, end);

		// Print the results.
		System.out.println("ArrayList access time: " + arrayListAccessTime.toMillis() + " ms");
		System.out.println("Vector access time: " + vectorAccessTime.toMillis() + " ms");
		System.out.println("ArrayList add time: " + arrayListAddTime.toMillis() + " ms");
		System.out.println("Vector add time: " + vectorAddTime.toMillis() + " ms");
		System.out.println("ArrayList iteration time: " + arrayListIterationTime.toMillis() + " ms");
		System.out.println("Vector iteration time: " + vectorIterationTime.toMillis() + " ms");
		System.out.println("ArrayList remove time: " + arrayListRemoveTime.toMillis() + " ms");
		System.out.println("Vector remove time: " + vectorRemoveTime.toMillis() + " ms");
	}
}