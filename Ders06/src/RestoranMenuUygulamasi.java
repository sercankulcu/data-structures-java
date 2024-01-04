import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RestoranMenuUygulamasi {
	public static void main(String[] args) {
		List<String> anaYemekler = Arrays.asList("Kızarmış Tavuk", "Izgara Somon", "Köri Tavuku", "Sebzeli Makarna");
		List<String> icecekler = Arrays.asList("Kola", "Limonata", "Çay", "Su");
		List<String> tatlilar = Arrays.asList("Cheesecake", "Çikolatalı Mousse", "Meyve Salatası");

		Scanner scanner = new Scanner(System.in);

		System.out.println("Restoran Menüsü");
		System.out.println("Ana Yemekler: " + anaYemekler);
		System.out.println("İçecekler: " + icecekler);
		System.out.println("Tatlılar: " + tatlilar);

		boolean devam = true;
		while (devam) {
			System.out.print("\nLütfen sipariş vermek istediğiniz kategoriyi seçin (Ana Yemekler, İçecekler, Tatlılar veya Çıkış): ");
			String kategori = scanner.nextLine();

			if (kategori.equalsIgnoreCase("Çıkış")) {
				System.out.println("Sipariş verme işlemi tamamlandı. İyi günler!");
				scanner.close();
				devam = false;
			} else if (kategori.equalsIgnoreCase("Ana Yemekler") || kategori.equalsIgnoreCase("İçecekler") || kategori.equalsIgnoreCase("Tatlılar")) {
				List<String> secilenKategori = getKategoriListesi(kategori, anaYemekler, icecekler, tatlilar);
				if (secilenKategori != null) {
					System.out.println("Lütfen sipariş etmek istediğiniz ürünü seçin (örneğin: 1, 2, 3): ");
					String secim = scanner.nextLine();
					List<String> secilenSiparisler = getSecilenSiparisler(secilenKategori, secim);
					if (secilenSiparisler != null) {
						System.out.println("Siparişleriniz: " + secilenSiparisler);
					} else {
						System.out.println("Geçersiz sipariş numarası girdiniz.");
					}
				} else {
					System.out.println("Geçersiz kategori seçtiniz.");
				}
			} else {
				System.out.println("Geçersiz bir kategori adı girdiniz. Lütfen tekrar deneyin.");
			}
		}
	}

	private static List<String> getKategoriListesi(String kategori, List<String> anaYemekler, List<String> icecekler, List<String> tatlilar) {
		if (kategori.equalsIgnoreCase("Ana Yemekler")) {
			return anaYemekler;
		} else if (kategori.equalsIgnoreCase("İçecekler")) {
			return icecekler;
		} else if (kategori.equalsIgnoreCase("Tatlılar")) {
			return tatlilar;
		}
		return Collections.emptyList();
	}

	private static List<String> getSecilenSiparisler(List<String> secilenKategori, String secim) {
		List<String> siparisler = new ArrayList<>();
		String[] secilenler = secim.split(",");
		for (String s : secilenler) {
			int index = Integer.parseInt(s.trim()) - 1;
			if (index >= 0 && index < secilenKategori.size()) {
				siparisler.add(secilenKategori.get(index));
			} else {
				return Collections.emptyList();
			}
		}
		return siparisler;
	}
}
