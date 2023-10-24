import java.util.Vector;
import java.util.Scanner;

class Musteri {
	private String ad;
	private String soyad;
	private String sorun;

	public Musteri(String ad, String soyad, String sorun) {
		this.ad = ad;
		this.soyad = soyad;
		this.sorun = sorun;
	}

	public String getAd() {
		return ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public String getSorun() {
		return sorun;
	}

	@Override
	public String toString() {
		return "Ad: " + ad + ", Soyad: " + soyad + ", Sorun: " + sorun;
	}
}

public class MusteriHizmetleri {
	private static Vector<Musteri> musteriListesi = new Vector<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Müşteri Hizmetleri Uygulamasına Hoş Geldiniz!");

		while (true) {
			System.out.println("\n1. Yeni Müşteri Kaydı Oluştur");
			System.out.println("2. Müşteri Sorunlarını Görüntüle");
			System.out.println("3. Uygulamadan Çıkış");
			System.out.print("Lütfen bir seçenek girin: ");

			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satır karakterini tüket

			switch (secim) {
			case 1:
				yeniMusteriKaydiOlustur();
				break;
			case 2:
				musteriSorunlariniGoruntule();
				break;
			case 3:
				System.out.println("Müşteri Hizmetleri uygulamasından çıkılıyor. İyi günler!");
				System.exit(0);
				break;
			default:
				System.out.println("Geçersiz seçenek. Lütfen geçerli bir seçenek girin.");
			}
		}
	}

	private static void yeniMusteriKaydiOlustur() {
		System.out.print("Müşteri Adı: ");
		String ad = scanner.nextLine();
		System.out.print("Müşteri Soyadı: ");
		String soyad = scanner.nextLine();
		System.out.print("Sorununuzu Açıklayın: ");
		String sorun = scanner.nextLine();

		Musteri yeniMusteri = new Musteri(ad, soyad, sorun);
		musteriListesi.add(yeniMusteri);
		System.out.println("Yeni müşteri kaydı oluşturuldu.");
	}

	private static void musteriSorunlariniGoruntule() {
		if (musteriListesi.isEmpty()) {
			System.out.println("Müşteri kaydı bulunmuyor.");
			return;
		}

		System.out.println("Müşteri Sorunları:");
		for (Musteri musteri : musteriListesi) {
			System.out.println(musteri);
		}
	}
}
