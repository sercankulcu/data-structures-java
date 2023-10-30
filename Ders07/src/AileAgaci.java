import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Kisi {
	String ad;
	List<Kisi> cocuklar;

	public Kisi(String ad) {
		this.ad = ad;
		this.cocuklar = new ArrayList<>();
	}
}

public class AileAgaci {
	public static void agaciGoster(Kisi kisi, String indent) {
		System.out.println(indent + kisi.ad);
		for (Kisi cocuk : kisi.cocuklar) {
			agaciGoster(cocuk, indent + "  ");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Aile Agaci Olustur:");
		System.out.print("Kisi adini girin: ");
		String anaAdi = scanner.nextLine();
		Kisi ana = new Kisi(anaAdi);

		agaciOlustur(ana);

		System.out.println("\nAile Agaci:");
		agaciGoster(ana, "");

		scanner.close();
	}

	public static void agaciOlustur(Kisi kisi) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Kisinin cocuklarinin sayisini girin (0 ifadesi cikis yapar): ");
		int cocukSayisi = scanner.nextInt();

		if (cocukSayisi > 0) {
			scanner.nextLine(); // Boş satırı tüket
			for (int i = 1; i <= cocukSayisi; i++) {
				System.out.print("Cocuk #" + i + " adini girin: ");
				String cocukAdi = scanner.nextLine();
				Kisi cocuk = new Kisi(cocukAdi);
				kisi.cocuklar.add(cocuk);
				agaciOlustur(cocuk);
			}
		}
	}
}
