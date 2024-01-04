import java.util.Stack;
import java.util.Scanner;

public class GeriAlmaUygulamasi {
	private static Stack<String> metinler = new Stack<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Geri Alma Uygulamasına Hoş Geldiniz!");

		boolean devam = true;
		while (devam) {
			System.out.println("\n1. Metin Girin");
			System.out.println("2. Metni Geri Al");
			System.out.println("3. Çıkış");
			System.out.print("Lütfen bir seçenek girin: ");

			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satır karakterini tüket

			switch (secim) {
			case 1:
				metinGir();
				break;
			case 2:
				geriAl();
				break;
			case 3:
				System.out.println("Geri Alma uygulamasından çıkılıyor. İyi günler!");
				devam = false;
				break;
			default:
				System.out.println("Geçersiz seçenek. Lütfen geçerli bir seçenek girin.");
			}
		}
	}

	private static void metinGir() {
		System.out.print("Metin Girin: ");
		String metin = scanner.nextLine();
		metinler.push(metin);
		System.out.println("Metin başarıyla kaydedildi.");
	}

	private static void geriAl() {
		if (!metinler.isEmpty()) {
			System.out.println("Geri Alınan Metin: " + metinler.pop());
		} else {
			System.out.println("Geri alınacak metin bulunmuyor.");
		}
	}
}
