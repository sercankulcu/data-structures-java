
import java.util.PriorityQueue;

class Hasta {
	String ad;
	int oncelik;

	public Hasta(String ad, int oncelik) {
		this.ad = ad;
		this.oncelik = oncelik;
	}
}

public class AcilServis {
	public static void main(String[] args) {
		PriorityQueue<Hasta> oncelikliKuyruk = new PriorityQueue<>((hasta1, hasta2) -> hasta2.oncelik - hasta1.oncelik);

		// Acil servise gelen hastaları ekleyelim
		oncelikliKuyruk.offer(new Hasta("Ahmet", 2));
		oncelikliKuyruk.offer(new Hasta("Mehmet", 5));
		oncelikliKuyruk.offer(new Hasta("Ayşe", 3));
		oncelikliKuyruk.offer(new Hasta("Zeynep", 1));
		oncelikliKuyruk.offer(new Hasta("Emre", 4));

		// Hastaları önceliklerine göre sırayla çağıralım
		while (!oncelikliKuyruk.isEmpty()) {
			Hasta hasta = oncelikliKuyruk.poll();
			System.out.println("Acil Servise Gelen Hasta: " + hasta.ad + " - Öncelik: " + hasta.oncelik);
		}
	}
}

