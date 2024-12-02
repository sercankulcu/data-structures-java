import java.util.Arrays;
import java.util.HashSet;

public class FindPairWithSum {

	public static void main(String[] args) {
		int[] arr = {4, 2, 7, 11, 15, 1, 8, 5};
		int targetSum = 9;

		// Call the function to find a pair
		findPairByBruteForce(arr, targetSum);
		
		findPairBySorting(arr, targetSum);
		
		findPairByHashing(arr, targetSum);
	}

	public static void findPairByBruteForce(int[] arr, int targetSum) {
		int n = arr.length;

		// Check all pairs using nested loops
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] + arr[j] == targetSum) {
					System.out.println("Pair found: (" + arr[i] + ", " + arr[j] + ")");
				}
			}
		}

		System.out.println("No pair with the given sum found.");
	}
	
	public static void findPairBySorting(int[] arr, int targetSum) {
        // Step 1: Sort the array
        Arrays.sort(arr);

        // Step 2: Use two pointers
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int currentSum = arr[left] + arr[right];

            if (currentSum == targetSum) {
                System.out.println("Pair found: (" + arr[left] + ", " + arr[right] + ")");
                left++; // or right--
            } else if (currentSum < targetSum) {
                left++; // Increase the sum
            } else {
                right--; // Decrease the sum
            }
        }

        System.out.println("No pair with the given sum found.");
    }
	
	public static void findPairByHashing(int[] arr, int targetSum) {
        // Step 1: Create a HashSet to store elements
        HashSet<Integer> seen = new HashSet<>();

        // Step 2: Iterate through the array
        for (int num : arr) {
            int complement = targetSum - num;

            // Check if the complement exists in the set
            if (seen.contains(complement)) {
                System.out.println("Pair found: (" + num + ", " + complement + ")");
            }

            // Add the current number to the set
            seen.add(num);
        }

        System.out.println("No pair with the given sum found.");
    }
}
