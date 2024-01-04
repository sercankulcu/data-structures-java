import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

class Sehir {
	private String ad;
	private int gun;

	public Sehir(String ad, int gun) {
		this.ad = ad;
		this.gun = gun;
	}

	public String getAd() {
		return ad;
	}

	public int getGun() {
		return gun;
	}

	@Override
	public String toString() {
		return "Gün " + gun + ": " + ad;
	}
}

public class YolculukPlanlama {
	private static LinkedList<Sehir> yolculukPlan = new LinkedList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Yolculuk Planlama Uygulamasına Hoş Geldiniz!");

		boolean devam = true;
		while (devam) {
			System.out.println("\n1. Yeni Şehir Ekleyin");
			System.out.println("2. Yolculuk Planını Görüntüleyin");
			System.out.println("3. Yolculuk Planını Temizleyin");
			System.out.println("4. Yolculuktan Çıkış");
			System.out.print("Lütfen bir seçenek girin: ");

			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satır karakterini tüket

			switch (secim) {
			case 1:
				yeniSehirEkle();
				break;
			case 2:
				yolculukPlaniniGoruntule();
				break;
			case 3:
				yolculukPlaniniTemizle();
				break;
			case 4:
				System.out.println("Yolculuk Planlama uygulamasından çıkılıyor. İyi yolculuklar!");
				devam = false;
				break;
			default:
				System.out.println("Geçersiz seçenek. Lütfen geçerli bir seçenek girin.");
			}
		}
	}

	private static void yeniSehirEkle() {
		System.out.print("Şehir Adı: ");
		String ad = scanner.nextLine();
		System.out.print("Kaçıncı Gün: ");
		int gun = scanner.nextInt();
		scanner.nextLine(); // Yeni satır karakterini tüket

		Sehir yeniSehir = new Sehir(ad, gun);
		yolculukPlan.add(yeniSehir);
		System.out.println("Yeni şehir yolculuk planına eklendi.");
	}

	private static void yolculukPlaniniGoruntule() {
		if (yolculukPlan.isEmpty()) {
			System.out.println("Yolculuk planı boş.");
			return;
		}

		ListIterator<Sehir> iterator = yolculukPlan.listIterator();
		System.out.println("Yolculuk Planı:");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	private static void yolculukPlaniniTemizle() {
		if (yolculukPlan.isEmpty()) {
			System.out.println("Yolculuk planı zaten boş.");
			return;
		}

		yolculukPlan.clear();
		System.out.println("Yolculuk planı temizlendi.");
	}
}
