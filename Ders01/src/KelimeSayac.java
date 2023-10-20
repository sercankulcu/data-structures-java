import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KelimeSayac {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Metin belgesini girin:");
		String metin = scanner.nextLine();
		scanner.close();

		Map<String, Integer> kelimeSayac = new HashMap<>();

		String[] kelimeler = metin.split("\\s+"); // Metni boşluklara göre böler

		for (String kelime : kelimeler) {
			kelime = kelime.toLowerCase(); // Kelimenin büyük-küçük harf farkını gözardı et
			kelime = kelime.replaceAll("[^a-zA-Z]", ""); // Sadece harf içeren karakterleri al

			if (kelime.length() > 0) {
				if (kelimeSayac.containsKey(kelime)) {
					kelimeSayac.put(kelime, kelimeSayac.get(kelime) + 1);
				} else {
					kelimeSayac.put(kelime, 1);
				}
			}
		}

		System.out.println("Kelime Sayacı:");
		for (Map.Entry<String, Integer> entry : kelimeSayac.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + " kez");
		}
	}
}
