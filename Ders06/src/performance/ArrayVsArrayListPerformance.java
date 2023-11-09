package performance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class ArrayVsArrayListPerformance {
	
	final static int SIZE = 10000000;

	public static void main(String[] args) {
		// Create an array and an ArrayList with the same number of elements.
		int[] array = new int[SIZE];
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			array[i] = i;
			arrayList.add(i);
		}

		// Measure the time it takes to access the first element in the array and ArrayList.
		Instant start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			int firstArrayElement = array[i];
		}
		Instant end = Instant.now();
		Duration arrayAccessTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			Integer firstArrayListElement = arrayList.get(i);
		}
		end = Instant.now();
		Duration arrayListAccessTime = Duration.between(start, end);

		// Measure the time it takes to add an element to the array and ArrayList.
		start = Instant.now();
		array[array.length - 1] = 1000001;
		end = Instant.now();
		Duration arrayAddTime = Duration.between(start, end);

		start = Instant.now();
		arrayList.add(1000001);
		end = Instant.now();
		Duration arrayListAddTime = Duration.between(start, end);

		// Print the results.
		System.out.println("Array access time: " + arrayAccessTime.toMillis() + " ms");
		System.out.println("ArrayList access time: " + arrayListAccessTime.toMillis() + " ms");
		System.out.println("Array add time: " + arrayAddTime.toMillis() + " ms");
		System.out.println("ArrayList add time: " + arrayListAddTime.toMillis() + " ms");
	}
}