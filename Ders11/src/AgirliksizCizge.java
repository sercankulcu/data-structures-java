import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AgirliksizCizge {

	public static Map<Integer, List<Integer>> cizge = new HashMap<>();

	public static Map<Integer, Integer> agirliksizEnKisaYol(int baslangic) {

		Map<Integer, Integer> mesafeler = new HashMap<>();
		Queue<Integer> kuyruk = new LinkedList<>();
		
		for (int dugum : cizge.keySet()) {
			mesafeler.put(dugum, Integer.MAX_VALUE); // kenar eklenmeden once tumune sonsuz degeri ata
		}
		mesafeler.put(baslangic, 0); // ilk dugum mesafe 0
		kuyruk.offer(baslangic);
		
		while (!kuyruk.isEmpty()) { // tum dugumler ziyaret edilene kadar
			int mevcutDugum = kuyruk.poll();
			for (int komsu : cizge.get(mevcutDugum)) { // tum komsulari icin dene
				if (mesafeler.get(komsu) == Integer.MAX_VALUE) {
					mesafeler.put(komsu, mesafeler.get(mevcutDugum) + 1); // agirliksiz oldugu icin 1 arttir
					kuyruk.offer(komsu);
				}
			}
		}
		return mesafeler;
	}

	public static void main(String[] args) {
		// cizgeyi olustur
		cizge.put(1, Arrays.asList(2, 3));
		cizge.put(2, Arrays.asList(1, 4, 5));
		cizge.put(3, Arrays.asList(1, 6));
		cizge.put(4, Arrays.asList(2));
		cizge.put(5, Arrays.asList(2, 7));
		cizge.put(6, Arrays.asList(3, 8));
		cizge.put(7, Arrays.asList(5));
		cizge.put(8, Arrays.asList(6));

		// Baslangic dugumu
		int baslangicDugumu = 1;

		// Agirliksiz en kisa yolu hesapla
		Map<Integer, Integer> mesafeler = agirliksizEnKisaYol(baslangicDugumu);

		// Sonuclari yazdir
		for (int dugum : mesafeler.keySet()) {
			System.out.println("Dugum " + dugum + " ile " + baslangicDugumu + " arasindaki mesafe: " + mesafeler.get(dugum));
		}
	}
}
