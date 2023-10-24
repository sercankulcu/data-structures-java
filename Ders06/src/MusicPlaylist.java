import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class MusicPlaylist {
	
	LinkedList<String> playlist = new LinkedList<>();
	ListIterator<String> listIterator = playlist.listIterator();
	
	public static void main(String[] args) {
		
		MusicPlaylist musicPlaylist = new MusicPlaylist();
		Scanner scanner = new Scanner(System.in);
		boolean forward = true;
		boolean quit = false;

		while (!quit) {
			musicPlaylist.printMenu();
			int action = scanner.nextInt();
			scanner.nextLine();  // Consume the newline character

			switch (action) {
			case 0:
				System.out.println("Playlist complete.");
				quit = true;
				break;
			case 1:
				if (!forward) {
					if (musicPlaylist.listIterator.hasNext()) {
						musicPlaylist.listIterator.next();
					}
					forward = true;
				}
				if (musicPlaylist.listIterator.hasNext()) {
					System.out.println("Now playing: " + musicPlaylist.listIterator.next());
				} else {
					System.out.println("You've reached the end of the playlist.");
					forward = false;
				}
				break;
			case 2:
				if (forward) {
					if (musicPlaylist.listIterator.hasPrevious()) {
						musicPlaylist.listIterator.previous();
					}
					forward = false;
				}
				if (musicPlaylist.listIterator.hasPrevious()) {
					System.out.println("Now playing: " + musicPlaylist.listIterator.previous());
				} else {
					System.out.println("You're at the beginning of the playlist.");
					forward = true;
				}
				break;
			case 3:
				if (forward) {
					if (musicPlaylist.listIterator.hasPrevious()) {
						System.out.println("Now replaying: " + musicPlaylist.listIterator.previous());
						forward = false;
					} else {
						System.out.println("You're at the beginning of the playlist.");
					}
				} else {
					if (musicPlaylist.listIterator.hasNext()) {
						System.out.println("Now replaying: " + musicPlaylist.listIterator.next());
						forward = true;
					} else {
						System.out.println("You've reached the end of the playlist.");
					}
				}
				break;
			case 4:
				musicPlaylist.printList(musicPlaylist.playlist);
				break;
			case 5:
				musicPlaylist.printMenu();
				break;
			case 6:
				if (!musicPlaylist.playlist.isEmpty()) {
					musicPlaylist.listIterator.remove();
					if (musicPlaylist.listIterator.hasNext()) {
						System.out.println("Now playing: " + musicPlaylist.listIterator.next());
					} else if (musicPlaylist.listIterator.hasPrevious()) {
						System.out.println("Now playing: " + musicPlaylist.listIterator.previous());
					}
				} else {
					System.out.println("Your playlist is empty.");
				}
				break;
			case 7:
				System.out.print("Enter the song to add to your playlist: ");
				String song = scanner.nextLine();
				musicPlaylist.addInOrder(musicPlaylist.playlist, song);
				break;
			}
		}
		scanner.close();
	}

	private void printMenu() {
		System.out.println("\nOptions:");
		System.out.println("0 - Quit");
		System.out.println("1 - Skip forward to the next song");
		System.out.println("2 - Skip backward to the previous song");
		System.out.println("3 - Replay the current song");
		System.out.println("4 - List songs in the playlist");
		System.out.println("5 - Print available actions");
		System.out.println("6 - Remove the current song from the playlist");
		System.out.println("7 - Add a new song to the playlist");
		System.out.println("Choose an option:");
	}

	private void printList(LinkedList<String> playlist) {
		System.out.println("Playlist:");
		for (String song : playlist) {
			System.out.println(playlist.indexOf(song) + 1 + ". " + song);
		}
	}

	private void addInOrder(LinkedList<String> playlist, String song) {
		//ListIterator<String> listIterator = playlist.listIterator();
		while (listIterator.hasNext()) {
			int comparison = listIterator.next().compareTo(song);
			if (comparison == 0) {
				System.out.println(song + " is already in the playlist.");
				return;
			} else if (comparison > 0) {
				listIterator.previous();
				listIterator.add(song);
				return;
			}
		}
		listIterator.add(song);
	}
}
