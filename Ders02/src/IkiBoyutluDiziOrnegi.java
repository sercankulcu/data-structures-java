import java.util.Scanner;

public class IkiBoyutluDiziOrnegi {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("Satır sayısını girin: ");
		int satirSayisi = scanner.nextInt();

		System.out.print("Sütun sayısını girin: ");
		int sutunSayisi = scanner.nextInt();

		int[][] ikiBoyutluDizi = new int[satirSayisi][sutunSayisi];

		System.out.println("Elemanları girin:");

		for (int i = 0; i < satirSayisi; i++) {
			for (int j = 0; j < sutunSayisi; j++) {
				System.out.print("Eleman [" + i + "][" + j + "]: ");
				ikiBoyutluDizi[i][j] = scanner.nextInt();
			}
		}

		System.out.println("\nİki Boyutlu Dizi (Türkçe):");
		for (int i = 0; i < satirSayisi; i++) {
			for (int j = 0; j < sutunSayisi; j++) {
				System.out.print(ikiBoyutluDizi[i][j] + " ");
			}
			System.out.println(); // Bir sonraki satıra geçmek için
		}

		scanner.close();
	}
}
