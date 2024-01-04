import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraDemo {

	public static class Cizge {
		Map<Integer, Map<Integer, Integer>> dugumler = new HashMap<>();

		public void kenarEkle(int kaynak, int hedef, int agirlik) {
			// Yönsüz çizge, iki tarafa da ekle
			dugumler.computeIfAbsent(kaynak, k -> new HashMap<>()).put(hedef, agirlik);
			dugumler.computeIfAbsent(hedef, k -> new HashMap<>()).put(kaynak, agirlik);
		}
	}

	public static Map<Integer, Integer> dijkstra(Cizge cizge, int kaynak) {
		Map<Integer, Integer> mesafeler = new HashMap<>();
		Set<Integer> ziyaretEdilen = new HashSet<>();
		PriorityQueue<Integer> kuyruk = new PriorityQueue<>(Comparator.comparingInt(mesafeler::get));
		for (int dugum : cizge.dugumler.keySet()) {
			mesafeler.put(dugum, Integer.MAX_VALUE); // sonsuz değeri ata
		}
		mesafeler.put(kaynak, 0); // başlangıç düğümü 0 ata
		kuyruk.offer(kaynak);
		
		while (!kuyruk.isEmpty()) {
			int mevcutDugum = kuyruk.poll();
			ziyaretEdilen.add(mevcutDugum);
			
			for (Map.Entry<Integer, Integer> komsu : cizge.dugumler.get(mevcutDugum).entrySet()) {
				if (!ziyaretEdilen.contains(komsu.getKey())) {
					int alternatif = mesafeler.get(mevcutDugum) + komsu.getValue();
					if (alternatif < mesafeler.get(komsu.getKey())) {
						mesafeler.put(komsu.getKey(), alternatif);
						kuyruk.offer(komsu.getKey());
					}
				}
			}
		}
		return mesafeler;
	}

	public static void main(String[] args) {
		
		Cizge cizge = new Cizge();
		cizge.kenarEkle(1, 2, 3);
		cizge.kenarEkle(1, 3, 5);
		cizge.kenarEkle(2, 3, 1);
		cizge.kenarEkle(2, 4, 7);
		cizge.kenarEkle(3, 4, 2);
		cizge.kenarEkle(4, 5, 4);
		cizge.kenarEkle(5, 6, 3);
		cizge.kenarEkle(2, 6, 5);
		cizge.kenarEkle(2, 7, 2);

		int kaynak = 1;

		Map<Integer, Integer> mesafeler = dijkstra(cizge, kaynak);

		System.out.println("Başlangıç düğümü " + kaynak + " için en kısa mesafeler:");
		for (int dugum : mesafeler.keySet()) {
			System.out.println("Düğüm " + dugum + ": " + mesafeler.get(dugum));
		}
	}
}
