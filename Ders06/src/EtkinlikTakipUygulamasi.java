import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Scanner;

class Etkinlik {
	private String ad;
	private String tarih;

	public Etkinlik(String ad, String tarih) {
		this.ad = ad;
		this.tarih = tarih;
	}

	public String getAd() {
		return ad;
	}

	public String getTarih() {
		return tarih;
	}

	@Override
	public String toString() {
		return ad + " - " + tarih;
	}
}

public class EtkinlikTakipUygulamasi {
	private static CopyOnWriteArrayList<Etkinlik> etkinlikler = new CopyOnWriteArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Canlı Etkinlik Takip Uygulamasına Hoş Geldiniz!");

		while (true) {
			System.out.println("\n1. Yeni Etkinlik Ekle");
			System.out.println("2. Etkinlikleri Görüntüle");
			System.out.println("3. Etkinlikten Vazgeç");
			System.out.println("4. Çıkış");
			System.out.print("Lütfen bir seçenek girin: ");

			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satır karakterini tüket

			switch (secim) {
			case 1:
				yeniEtkinlikEkle();
				break;
			case 2:
				etkinlikleriGoruntule();
				break;
			case 3:
				etkinliktenVazgec();
				break;
			case 4:
				System.out.println("Canlı Etkinlik Takip uygulamasından çıkılıyor. İyi eğlenceler!");
				System.exit(0);
				break;
			default:
				System.out.println("Geçersiz seçenek. Lütfen geçerli bir seçenek girin.");
			}
		}
	}

	private static void yeniEtkinlikEkle() {
		System.out.print("Etkinlik Adı: ");
		String ad = scanner.nextLine();
		System.out.print("Etkinlik Tarihi: ");
		String tarih = scanner.nextLine();

		Etkinlik yeniEtkinlik = new Etkinlik(ad, tarih);
		etkinlikler.add(yeniEtkinlik);
		System.out.println("Yeni etkinlik başarıyla kaydedildi.");
	}

	private static void etkinlikleriGoruntule() {
		if (etkinlikler.isEmpty()) {
			System.out.println("Henüz etkinlik kaydedilmemiş.");
			return;
		}

		System.out.println("Etkinlikler:");
		for (Etkinlik etkinlik : etkinlikler) {
			System.out.println(etkinlik);
		}
	}

	private static void etkinliktenVazgec() {
		if (!etkinlikler.isEmpty()) {
			System.out.print("Vazgeçmek istediğiniz etkinliği seçin (0'dan başlayarak sayın): ");
			int secim = scanner.nextInt();
			if (secim >= 0 && secim < etkinlikler.size()) {
				Etkinlik vazgecilenEtkinlik = etkinlikler.get(secim);
				etkinlikler.remove(vazgecilenEtkinlik);
				System.out.println(vazgecilenEtkinlik.getAd() + " etkinliği iptal edildi.");
			} else {
				System.out.println("Geçersiz bir seçenek girdiniz.");
			}
		} else {
			System.out.println("Hiçbir etkinlik kaydedilmemiş.");
		}
	}
}
