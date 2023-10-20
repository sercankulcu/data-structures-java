import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		
		char[][] tahta = new char[3][3]; // 3x3'lük oyun tahtası
		char oyuncu = 'X'; // İlk oyuncu X ile başlar
		boolean oyunDevamEdiyor = true;
		tahtaDoldur(tahta); // Tahtayı başlangıç durumuyla doldur
		while (oyunDevamEdiyor) {
			tahtayiGoster(tahta);
			hamleYap(tahta, oyuncu);
			oyunDevamEdiyor = oyunDevamEdiyorMu(tahta, oyuncu);
			oyuncu = (oyuncu == 'X') ? 'O' : 'X'; // Oyuncu değişimi
		}
		tahtayiGoster(tahta);
	}

	public static void tahtaDoldur(char[][] tahta) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tahta[i][j] = ' ';
			}
		}
	}

	public static void tahtayiGoster(char[][] tahta) {
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(tahta[i][j] + " | ");
			}
			System.out.println("\n-------------");
		}
	}

	public static void hamleYap(char[][] tahta, char oyuncu) {
		Scanner scanner = new Scanner(System.in);
		int satir, sutun;
		do {
			System.out.print("Sıra " + oyuncu + " oyuncusunda. Satır ve sütun seçin (1-3): ");
			satir = scanner.nextInt() - 1;
			sutun = scanner.nextInt() - 1;
		} while (satir < 0 || satir > 2 || sutun < 0 || sutun > 2 || tahta[satir][sutun] != ' ');
		tahta[satir][sutun] = oyuncu;
		scanner.close();
	}

	public static boolean oyunDevamEdiyorMu(char[][] tahta, char oyuncu) {
		// Kazanan durumlarını kontrol et
		if ((tahta[0][0] == oyuncu && tahta[0][1] == oyuncu && tahta[0][2] == oyuncu) ||
				(tahta[1][0] == oyuncu && tahta[1][1] == oyuncu && tahta[1][2] == oyuncu) ||
				(tahta[2][0] == oyuncu && tahta[2][1] == oyuncu && tahta[2][2] == oyuncu) ||
				(tahta[0][0] == oyuncu && tahta[1][0] == oyuncu && tahta[2][0] == oyuncu) ||
				(tahta[0][1] == oyuncu && tahta[1][1] == oyuncu && tahta[2][1] == oyuncu) ||
				(tahta[0][2] == oyuncu && tahta[1][2] == oyuncu && tahta[2][2] == oyuncu) ||
				(tahta[0][0] == oyuncu && tahta[1][1] == oyuncu && tahta[2][2] == oyuncu) ||
				(tahta[0][2] == oyuncu && tahta[1][1] == oyuncu && tahta[2][0] == oyuncu)) {
			System.out.println("Tebrikler, " + oyuncu + " oyuncusu kazandı!");
			return false;
		}

		//Berabere durumunu kontrol et
		boolean berabere = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tahta[i][j] == ' ') {
					berabere = false;
					break;
				}
			}
			if (!berabere) {
				break;
			}
		}
		if (berabere) {
			System.out.println("Oyun berabere bitti.");
			return false;
		}
		return true;
	}
}
