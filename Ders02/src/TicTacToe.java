import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        
        // 3x3'luk oyun tahtasini tanimliyoruz
        char[][] tahta = new char[3][3]; 
        char oyuncu = 'X'; // Oyuncu X ile baslar
        boolean oyunDevamEdiyor = true; // Oyun baslangicta devam ediyor kabul edilir
        tahtayiDoldur(tahta); // Tahtayi bosluklarla dolduruyoruz
        while (oyunDevamEdiyor) {
            tahtayiGoster(tahta); // Tahtayi ekrana yazdir
            hamleYap(tahta, oyuncu); // Oyuncunun hamlesini al
            oyunDevamEdiyor = oyunDevamEdiyorMu(tahta, oyuncu); // Oyun bitmediyse devam et
            oyuncu = (oyuncu == 'X') ? 'O' : 'X'; // Oyuncular sirasiyla X ve O arasinda degisir
        }
        tahtayiGoster(tahta); // Oyun bitince tahtayi son haliyle goster
    }

    // Tahtayi baslangic durumuyla doldurur (bosluklarla)
    public static void tahtayiDoldur(char[][] tahta) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tahta[i][j] = ' '; // Baslangicta her hucre boslukla doldurulur
            }
        }
    }

    // Tahtayi ekranda gosterir
    public static void tahtayiGoster(char[][] tahta) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tahta[i][j] + " | "); // Tahtadaki her bir elemani yazdir
            }
            System.out.println("\n-------------");
        }
    }

    // Oyuncudan hamle alir ve tahtayi gunceller
    public static void hamleYap(char[][] tahta, char oyuncu) {
        try (Scanner scanner = new Scanner(System.in)) {
			int satir;
			int sutun;
			do {
			    // Kullanicidan satir ve sutun bilgisi alinir
			    System.out.print("Sira " + oyuncu + " oyuncusunda. Satir ve sutun secin (1-3): ");
			    satir = scanner.nextInt() - 1; // 1-3 arasi girdi alinir, 0-2 arasi indekse cevrilir
			    sutun = scanner.nextInt() - 1; // Ayni sekilde sutun icin de gecerlidir
			} while (satir < 0 || satir > 2 || sutun < 0 || sutun > 2 || tahta[satir][sutun] != ' '); // Gecersiz veya dolu alan kontrolu
			tahta[satir][sutun] = oyuncu; // Hamle yapilir
		}
    }

    // Oyunun devam edip etmedigini kontrol eder
    public static boolean oyunDevamEdiyorMu(char[][] tahta, char oyuncu) {
        // Kazanma durumlari kontrol edilir
        if ((tahta[0][0] == oyuncu && tahta[0][1] == oyuncu && tahta[0][2] == oyuncu) ||
            (tahta[1][0] == oyuncu && tahta[1][1] == oyuncu && tahta[1][2] == oyuncu) ||
            (tahta[2][0] == oyuncu && tahta[2][1] == oyuncu && tahta[2][2] == oyuncu) ||
            (tahta[0][0] == oyuncu && tahta[1][0] == oyuncu && tahta[2][0] == oyuncu) ||
            (tahta[0][1] == oyuncu && tahta[1][1] == oyuncu && tahta[2][1] == oyuncu) ||
            (tahta[0][2] == oyuncu && tahta[1][2] == oyuncu && tahta[2][2] == oyuncu) ||
            (tahta[0][0] == oyuncu && tahta[1][1] == oyuncu && tahta[2][2] == oyuncu) ||
            (tahta[0][2] == oyuncu && tahta[1][1] == oyuncu && tahta[2][0] == oyuncu)) {
            // Eger oyuncu kazanmissa
            System.out.println("Tebrikler, " + oyuncu + " oyuncusu kazandi!");
            return false; // Oyun sona erer
        }

        // Beraberlik durumunu kontrol et
        boolean berabere = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tahta[i][j] == ' ') { // Eger bos bir alan varsa oyun devam eder
                    berabere = false;
                    break;
                }
            }
            if (!berabere) { // Eger bos alan bulunduysa dongu kirilir
                break;
            }
        }
        if (berabere) { // Eger bos alan yoksa ve kazanan yoksa oyun berabere biter
            System.out.println("Oyun berabere bitti.");
            return false;
        }
        return true; // Oyun devam ediyor
    }
}
