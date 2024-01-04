import java.util.ArrayList;
import java.util.Scanner;

class Kitap {
	private String ad;
	private String yazar;

	public Kitap(String ad, String yazar) {
		this.ad = ad;
		this.yazar = yazar;
	}

	public String getAd() {
		return ad;
	}

	public String getYazar() {
		return yazar;
	}

	@Override
	public String toString() {
		return "Kitap Adı: " + ad + ", Yazar: " + yazar;
	}
}

public class KitapKutuphanesi {
	private static ArrayList<Kitap> kitapListesi = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Kitap Kütüphanesi Uygulamasına Hoş Geldiniz!");

		boolean devam = true;
		while (devam) {
			System.out.println("\n1. Yeni Kitap Ekle");
			System.out.println("2. Kütüphanedeki Kitapları Görüntüle");
			System.out.println("3. Uygulamadan Çıkış");
			System.out.print("Lütfen bir seçenek girin: ");

			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satır karakterini tüket

			switch (secim) {
			case 1:
				yeniKitapEkle();
				break;
			case 2:
				kutuphanedekiKitaplariGoruntule();
				break;
			case 3:
				System.out.println("Kitap Kütüphanesi uygulamasından çıkılıyor. İyi günler!");
				devam = false;
				break;
			default:
				System.out.println("Geçersiz seçenek. Lütfen geçerli bir seçenek girin.");
			}
		}
	}

	private static void yeniKitapEkle() {
		System.out.print("Kitap Adı: ");
		String ad = scanner.nextLine();
		System.out.print("Yazar: ");
		String yazar = scanner.nextLine();

		Kitap yeniKitap = new Kitap(ad, yazar);
		kitapListesi.add(yeniKitap);
		System.out.println("Yeni kitap kütüphaneye eklendi.");
	}

	private static void kutuphanedekiKitaplariGoruntule() {
		if (kitapListesi.isEmpty()) {
			System.out.println("Kütüphanede kitap bulunmuyor.");
			return;
		}

		System.out.println("Kütüphanedeki Kitaplar:");
		for (Kitap kitap : kitapListesi) {
			System.out.println(kitap);
		}
	}
}
