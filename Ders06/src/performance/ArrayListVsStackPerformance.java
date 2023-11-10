package performance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Stack;

public class ArrayListVsStackPerformance {
	
	// TODO: try with different SIZE values
	final static int SIZE = 200000;

	public static void main(String[] args) {
		// Create an ArrayList and a Stack with the same number of elements.
		ArrayList<Integer> arrayList = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < SIZE; i++) {
			arrayList.add(i);
			stack.push(i);
		}

		// Measure the time it takes to access the first element in the ArrayList and Stack.
		Instant start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.get(i);
		}
		Instant end = Instant.now();
		Duration arrayListAccessTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			stack.peek();
		}
		end = Instant.now();
		Duration stackAccessTime = Duration.between(start, end);

		// Measure the time it takes to add an element to the ArrayList and Stack.
		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.add(i);
		}
		end = Instant.now();
		Duration arrayListAddTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			stack.push(i);
		}
		end = Instant.now();
		Duration stackAddTime = Duration.between(start, end);

		// Measure the time it takes to remove an element from the ArrayList and Stack.
		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			arrayList.remove(i);
		}
		end = Instant.now();
		Duration arrayListRemoveTime = Duration.between(start, end);

		start = Instant.now();
		for (int i = 0; i < SIZE; i++) {
			stack.pop();
		}
		end = Instant.now();
		Duration stackRemoveTime = Duration.between(start, end);

		// Print the results.
		System.out.println("ArrayList access time: " + arrayListAccessTime.toMillis() + " ms");
		System.out.println("Stack access time: " + stackAccessTime.toMillis() + " ms");
		System.out.println("ArrayList add time: " + arrayListAddTime.toMillis() + " ms");
		System.out.println("Stack add time: " + stackAddTime.toMillis() + " ms");
		System.out.println("ArrayList remove time: " + arrayListRemoveTime.toMillis() + " ms");
		System.out.println("Stack remove time: " + stackRemoveTime.toMillis() + " ms");
	}
}