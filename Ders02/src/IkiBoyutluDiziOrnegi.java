import java.util.Scanner;

public class IkiBoyutluDiziOrnegi {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("Satır sayısını girin: ");
		int satirSayisi = scanner.nextInt();

		System.out.print("Sütun sayısını girin: ");
		int sutunSayisi = scanner.nextInt();

		int[][] ikiBoyutluDizi = new int[satirSayisi][sutunSayisi];

		for (int i = 0; i < satirSayisi; i++) {
			for (int j = 0; j < sutunSayisi; j++) {
				ikiBoyutluDizi[i][j] = i * 3 + j * 5;
			}
		}

		System.out.println("\nİki Boyutlu Dizi:");
		for (int i = 0; i < satirSayisi; i++) {
			for (int j = 0; j < sutunSayisi; j++) {
				System.out.print(ikiBoyutluDizi[i][j] + "\t");
			}
			System.out.println(); // Bir sonraki satıra geçmek için
		}

		scanner.close();
	}
}
