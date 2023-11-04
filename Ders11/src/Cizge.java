
import java.util.*;

public class Cizge {

	private int dugumSayisi;
	private LinkedList<Integer> komsuListeleri[];

	public Cizge (int dugumSayisi) {
		this.dugumSayisi = dugumSayisi;
		komsuListeleri = new LinkedList[dugumSayisi];
		for (int i = 0; i < dugumSayisi; i++) {
			komsuListeleri[i] = new LinkedList<>();
		}
	}

	public void kenarEkle(int dugum, int komsuDugum) {
		komsuListeleri[dugum].add(komsuDugum);
	}

	public void BFS(int baslangicDugum) {
		boolean[] ziyaretEdildi = new boolean[dugumSayisi];
		LinkedList<Integer> kuyruk = new LinkedList<>();

		ziyaretEdildi[baslangicDugum] = true;
		kuyruk.add(baslangicDugum);

		while (kuyruk.size() != 0) {
			baslangicDugum = kuyruk.poll();
			System.out.print(baslangicDugum + " ");

			Iterator<Integer> iterator = komsuListeleri[baslangicDugum].listIterator();
			while (iterator.hasNext()) {
				int komsuDugum = iterator.next();
				if (!ziyaretEdildi[komsuDugum]) {
					ziyaretEdildi[komsuDugum] = true;
					kuyruk.add(komsuDugum);
				}
			}
		}
	}

	public void DFS(int baslangicDugum) {
		boolean[] ziyaretEdildi = new boolean[dugumSayisi];
		DFSUtil(baslangicDugum, ziyaretEdildi);
	}

	private void DFSUtil(int dugum, boolean[] ziyaretEdildi) {
		ziyaretEdildi[dugum] = true;
		System.out.print(dugum + " ");

		Iterator<Integer> iterator = komsuListeleri[dugum].listIterator();
		while (iterator.hasNext()) {
			int komsuDugum = iterator.next();
			if (!ziyaretEdildi[komsuDugum]) {
				DFSUtil(komsuDugum, ziyaretEdildi);
			}
		}
	}

	public static void main(String[] args) {
		Cizge graf = new Cizge(7);
		graf.kenarEkle(0, 1);
		graf.kenarEkle(0, 2);
		graf.kenarEkle(1, 3);
		graf.kenarEkle(1, 4);
		graf.kenarEkle(2, 5);
		graf.kenarEkle(2, 6);

		System.out.println("Başlangıç Düğümü (0) ile Genişlik İlk Arama:");
		graf.BFS(0);
		System.out.println();
		graf.DFS(0);
	}
}
