import java.util.Scanner;

// X-O oyunu: 3x3'luk char[][] tahta. Her hamle tek bir dizi atamasidir.
public class TicTacToe {
	static Scanner scanner = new Scanner(System.in); // tek Scanner
	static final int GIRDI_YOK = Integer.MIN_VALUE;   // girdi bitti isareti

	public static void main(String[] args) {
		char[][] tahta = new char[3][3];   // 3x3'luk oyun tahtasi
		char oyuncu = 'X';                 // ilk oyuncu X
		boolean oyunDevamEdiyor = true;
		tahtaDoldur(tahta);                // tum hucreler ' '
		while (oyunDevamEdiyor) {
			tahtayiGoster(tahta);
			if (!hamleYap(tahta, oyuncu)) {    // girdi bitti
				System.out.println("\nGirdi kalmadi, oyun yarida birakildi.");
				break;
			}
			oyunDevamEdiyor = oyunDevamEdiyorMu(tahta, oyuncu);
			oyuncu = (oyuncu == 'X') ? 'O' : 'X';   // sira degisir
		}
		tahtayiGoster(tahta);
		scanner.close();
	}

	// new char[3][3] hucreleri 0 karakteri ile baslar; bos hucre ' ' ile gosterilir
	static void tahtaDoldur(char[][] t) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				t[i][j] = ' ';
	}

	static void tahtayiGoster(char[][] t) {
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++)
				System.out.print(t[i][j] + " | ");
			System.out.println("\n-------------");
		}
	}

	// Kullanici 1-3 girer, dizi 0-2; gecersiz/dolu hucre -> tekrar sor.
	// Girdi biterse false dondurur.
	static boolean hamleYap(char[][] t, char oyuncu) {
		int satir, sutun;
		do {
			System.out.print("Sira " + oyuncu + ": satir ve sutun (1-3): ");
			int s = sayiOku(), u = sayiOku();
			if (s == GIRDI_YOK || u == GIRDI_YOK)
				return false;
			satir = s - 1;                     // kullanici 1-3, dizi 0-2
			sutun = u - 1;
		} while (satir < 0 || satir > 2
				|| sutun < 0 || sutun > 2
				|| t[satir][sutun] != ' ');
		t[satir][sutun] = oyuncu;
		return true;
	}

	// Siradaki tamsayiyi okur; sayi olmayan girdiyi atlar, girdi bitince GIRDI_YOK dondurur
	static int sayiOku() {
		while (scanner.hasNext()) {
			if (scanner.hasNextInt())
				return scanner.nextInt();
			scanner.next();                    // sayi degil, atla
		}
		return GIRDI_YOK;
	}

	// 3 satir + 3 sutun + 2 capraz = 8 kazanma kosulu
	static boolean kazandi(char[][] t, char o) {
		for (int i = 0; i < 3; i++)
			if ((t[i][0] == o && t[i][1] == o && t[i][2] == o)     // satir i
			 || (t[0][i] == o && t[1][i] == o && t[2][i] == o))    // sutun i
				return true;
		return (t[0][0] == o && t[1][1] == o && t[2][2] == o)      // ana capraz
			|| (t[0][2] == o && t[1][1] == o && t[2][0] == o);     // ters capraz
	}

	static boolean oyunDevamEdiyorMu(char[][] t, char o) {
		if (kazandi(t, o)) {
			System.out.println("Tebrikler, " + o + " oyuncusu kazandi!");
			return false;
		}
		for (char[] satir : t)
			for (char c : satir)
				if (c == ' ') return true;     // bos hucre var -> devam
		System.out.println("Oyun berabere bitti.");
		return false;
	}
}
