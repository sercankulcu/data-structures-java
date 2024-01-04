
import java.util.*;

public class AgirliksizCizgeEnKisaYol {

	public static Map<Integer, List<Integer>> cizge = new HashMap<>();

	public static Map<Integer, Integer> agirliksizEnKisaYol(int baslangic) {

		Map<Integer, Integer> mesafeler = new HashMap<>();
		Queue<Integer> kuyruk = new LinkedList<>();
		
		for (int dugum : cizge.keySet()) {
			mesafeler.put(dugum, Integer.MAX_VALUE); // sonsuz değeri ata
		}
		mesafeler.put(baslangic, 0); // ilk düğüm mesafe 0
		kuyruk.offer(baslangic);
		
		while (!kuyruk.isEmpty()) { // tüm düğümler ziyaret edilene kadar
			int mevcutDugum = kuyruk.poll();
			for (int komsu : cizge.get(mevcutDugum)) { // tüm komşuları için dene
				if (mesafeler.get(komsu) == Integer.MAX_VALUE) {
					mesafeler.put(komsu, mesafeler.get(mevcutDugum) + 1); // ağırlıksız olduğu için 1 arttır
					kuyruk.offer(komsu);
				}
			}
		}
		return mesafeler;
	}

	public static void main(String[] args) {
		// Çizgeyi oluştur
		cizge.put(1, Arrays.asList(2, 3));
		cizge.put(2, Arrays.asList(1, 4, 5));
		cizge.put(3, Arrays.asList(1, 6));
		cizge.put(4, Arrays.asList(2));
		cizge.put(5, Arrays.asList(2, 7));
		cizge.put(6, Arrays.asList(3, 8));
		cizge.put(7, Arrays.asList(5));
		cizge.put(8, Arrays.asList(6));

		// Başlangıç düğümü
		int baslangicDugumu = 1;

		// Ağırlıksız en kısa yolu hesapla
		Map<Integer, Integer> mesafeler = agirliksizEnKisaYol(baslangicDugumu);

		// Sonuçları yazdır
		for (int dugum : mesafeler.keySet()) {
			System.out.println("Düğüm " + dugum + " ile " + baslangicDugumu + " arasındaki mesafe: " + mesafeler.get(dugum));
		}
	}
}
