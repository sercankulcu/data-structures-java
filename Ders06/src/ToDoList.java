import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
	
	public static void main(String[] args) {
		
		ArrayList<String> toDoItems = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the To-Do List Application!");

		while (true) {
			System.out.println("\n1. Add a to-do item");
			System.out.println("2. Remove a to-do item");
			System.out.println("3. View to-do list");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			switch (choice) {
			case 1:
				System.out.print("Enter a to-do item: ");
				String newItem = scanner.nextLine();
				toDoItems.add(newItem);
				System.out.println("To-do item added successfully!");
				break;
			case 2:
				if (!toDoItems.isEmpty()) {
					System.out.println("Your To-Do List:");
					for (int i = 0; i < toDoItems.size(); i++) {
						System.out.println((i + 1) + ". " + toDoItems.get(i));
					}
					System.out.print("Enter the number of the item to remove: ");
					int itemNumber = scanner.nextInt();
					if (itemNumber >= 1 && itemNumber <= toDoItems.size()) {
						toDoItems.remove(itemNumber - 1);
						System.out.println("To-do item removed successfully!");
					} else {
						System.out.println("Invalid item number.");
					}
				} else {
					System.out.println("Your to-do list is empty.");
				}
				break;
			case 3:
				if (!toDoItems.isEmpty()) {
					System.out.println("Your To-Do List:");
					for (int i = 0; i < toDoItems.size(); i++) {
						System.out.println((i + 1) + ". " + toDoItems.get(i));
					}
				} else {
					System.out.println("Your to-do list is empty.");
				}
				break;
			case 4:
				System.out.println("Thank you for using the To-Do List Application. Have a great day!");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please enter a valid option.");
			}
		}
	}
}
