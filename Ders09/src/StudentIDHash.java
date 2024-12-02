public class StudentIDHash {

    /**
     * Calculates the hash value.
     * @param studentId School ID (in the format ab070c0de, as a String)
     * @return Hash value (an integer between 0–999)
     */
    public static int calculateHash(String studentId) {
        // Validate the school ID
        if (studentId == null || studentId.length() != 9) {
            throw new IllegalArgumentException("Invalid school ID format");
        }

        // Extract the parts
        int ab = Integer.parseInt(studentId.substring(0, 2)); // ab: Enrollment year
        int c = Integer.parseInt(studentId.substring(5, 6));  // c: Department
        int de = Integer.parseInt(studentId.substring(7, 9)); // de: Enrollment sequence number

        // Check year, department, and enrollment sequence
        if (ab < 20 || ab > 24 || (c != 6 && c != 7) || de < 1 || de > 100) {
            throw new IllegalArgumentException("Invalid school ID values");
        }

        // Apply the hash function
        int yearFactor = (ab - 20) * 100;
        int departmentFactor = (c - 6) * 500;
        int hashValue = (yearFactor + departmentFactor + de) % 1000;

        return hashValue;
    }

    public static void main(String[] args) {
        // Test data
        String student1 = "210706003"; // Example school ID
        String student2 = "240707050";

        // Call the hash function and print the results
        System.out.println("Hash value (student1): " + calculateHash(student1)); // Output: 103
        System.out.println("Hash value (student2): " + calculateHash(student2)); // Output: 950
    }
}
