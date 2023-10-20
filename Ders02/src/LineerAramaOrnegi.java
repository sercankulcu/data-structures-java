import java.util.Scanner;

public class LineerAramaOrnegi {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		int[] dizi = {12, 45, 78, 32, 56, 89, 65, 43, 27, 90};

		System.out.print("Aranacak elemanı girin: ");
		int arananEleman = scanner.nextInt();

		int indeks = lineerArama(dizi, arananEleman);

		if (indeks != -1) {
			System.out.println("Aranan eleman " + arananEleman + " dizinin " + indeks + ". indeksinde bulundu.");
		} else {
			System.out.println("Aranan eleman " + arananEleman + " dizide bulunamadı.");
		}

		scanner.close();
	}

	public static int lineerArama(int[] dizi, int arananEleman) {
		for (int i = 0; i < dizi.length; i++) {
			if (dizi[i] == arananEleman) {
				return i; // Eleman bulundu, indeksi döndür
			}
		}
		return -1; // Eleman bulunamadı
	}
}
