import java.util.HashMap; 
import java.util.Map;
import java.util.Scanner;

public class KelimeSayac {

	public static void main(String[] args) {

		// Kullanicidan metin almak icin Scanner nesnesi olusturulur.
		Scanner scanner = new Scanner(System.in);
		System.out.println("Metin belgesini girin:");
		String metin = scanner.nextLine(); // Kullanicinin girdigi metin alinir.
		scanner.close(); // Scanner kapatilir.

		// Kelimelerin sayisini tutmak icin bir HashMap kullanilir.
		Map<String, Integer> kelimeSayac = new HashMap<>();

		// Metni bosluklara gore boler ve her kelimeyi bir diziye atar.
		String[] kelimeler = metin.split("\\s+"); // \\s+ ifadesi bir veya daha fazla boslugu temsil eder.

		// Kelimeler uzerinde dongu baslatilir.
		for (String kelime : kelimeler) {
			kelime = kelime.toLowerCase(); // Kelimenin tamami kucuk harfe donusturulur.
			kelime = kelime.replaceAll("[^a-zA-Z]", ""); // Kelimedeki harf olmayan karakterler temizlenir.

			// Kelime uzunlugu 0'dan buyukse isleme alinir.
			if (kelime.length() > 0) {
				// Eger kelime daha once gorulmusse, sayaci bir artirilir.
				if (kelimeSayac.containsKey(kelime)) {
					kelimeSayac.put(kelime, kelimeSayac.get(kelime) + 1);
				} else {
					// Eger kelime daha once gorulmemisse, sayaci 1 olarak baslatilir.
					kelimeSayac.put(kelime, 1);
				}
			}
		}

		// Kelime sayaci sonucu ekrana yazdirilir.
		System.out.println("Kelime Sayaci:");
		for (Map.Entry<String, Integer> entry : kelimeSayac.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + " kez");
		}
	}
}
