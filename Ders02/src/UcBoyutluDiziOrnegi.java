import java.util.Scanner;

public class UcBoyutluDiziOrnegi {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("Katman sayısını girin: ");
		int katmanSayisi = scanner.nextInt();

		System.out.print("Satır sayısını girin: ");
		int satirSayisi = scanner.nextInt();

		System.out.print("Sütun sayısını girin: ");
		int sutunSayisi = scanner.nextInt();

		int[][][] ucBoyutluDizi = new int[katmanSayisi][satirSayisi][sutunSayisi];

		for (int i = 0; i < katmanSayisi; i++) {
			for (int j = 0; j < satirSayisi; j++) {
				for (int k = 0; k < sutunSayisi; k++) {
					ucBoyutluDizi[i][j][k] = i * 2 + j * 3 + k;
				}
			}
		}

		System.out.println("\nÜç Boyutlu Dizi:");
		for (int i = 0; i < katmanSayisi; i++) {
			for (int j = 0; j < satirSayisi; j++) {
				for (int k = 0; k < sutunSayisi; k++) {
					System.out.print(ucBoyutluDizi[i][j][k] + " ");
				}
				System.out.println(); // Bir sonraki satıra geçmek için
			}
			System.out.println(); // Bir sonraki katmana geçmek için
		}

		scanner.close();
	}
}
