import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CizgeBagliListe {

	private List<LinkedList<Integer>> komsuListesi;
	private List<Boolean> ziyaretEdildi; 

	public CizgeBagliListe (int boyut) {
		komsuListesi = new ArrayList<>();
		ziyaretEdildi = new ArrayList<>();
		for (int i = 0; i < boyut; i++) {
			komsuListesi.add(new LinkedList<>());
			ziyaretEdildi.add(false);
		}
	}
	
	public void temizle() {
		for (int i = 0; i < ziyaretEdildi.size(); i++) {
			ziyaretEdildi.set(i, false);
		}
	}

	public void kenarEkle(int dugum, int komsu) {
		komsuListesi.get(dugum).add(komsu);
		komsuListesi.get(komsu).add(dugum);
	}

	public void BFS(int dugum) {
		
		LinkedList<Integer> kuyruk = new LinkedList<>();

		ziyaretEdildi.set(dugum, true);
		kuyruk.add(dugum);

		while (!kuyruk.isEmpty()) {
			dugum = kuyruk.poll();
			System.out.print(dugum + " ");

			Iterator<Integer> iterator = komsuListesi.get(dugum).listIterator();
			while (iterator.hasNext()) {
				int komsu = iterator.next();
				if (!ziyaretEdildi.get(komsu)) {
					ziyaretEdildi.set(komsu, true);
					kuyruk.add(komsu);
				}
			}
		}
	}

	public void DFS(int dugum) {
		
		ziyaretEdildi.set(dugum, true);
		System.out.print(dugum + " ");

		Iterator<Integer> iterator = komsuListesi.get(dugum).listIterator();
		while (iterator.hasNext()) {
			int komsu = iterator.next();
			if (!ziyaretEdildi.get(komsu)) {
				DFS(komsu);
			}
		}
	}

	public static void main(String[] args) {
		CizgeBagliListe cizge = new CizgeBagliListe(10);
		cizge.kenarEkle(0, 2);
		cizge.kenarEkle(0, 4);
		cizge.kenarEkle(1, 2);
		cizge.kenarEkle(1, 7);
		cizge.kenarEkle(2, 3);
		cizge.kenarEkle(3, 6);
		cizge.kenarEkle(4, 5);
		cizge.kenarEkle(5, 7);
		cizge.kenarEkle(6, 7);
		cizge.kenarEkle(7, 8);

		System.out.println("Başlangıç Düğümü (0) ile Genişlik Öncelikli Arama:");
		cizge.temizle();
		cizge.BFS(0);
		System.out.println("\nBaşlangıç Düğümü (0) ile Derinlik Öncelikli Arama:");
		cizge.temizle();
		cizge.DFS(0);
	}
}
