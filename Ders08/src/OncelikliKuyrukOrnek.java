import java.util.Comparator;
import java.util.PriorityQueue;

// Java'nin hazir PriorityQueue sinifi: icinde dizi tabanli ikili min-heap vardir
public class OncelikliKuyrukOrnek {

	// Acil servis hastasi: oncelik kucuk olan once
	static class Hasta {
		String ad;
		int oncelik;

		Hasta(String ad, int oncelik) {
			this.ad = ad;
			this.oncelik = oncelik;
		}
	}

	public static void main(String[] args) {
		// Varsayilan: min-heap
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(5);
		pq.offer(1);
		pq.offer(8);
		System.out.println(pq.peek()); // bakar: 1, O(1)
		System.out.println(pq.poll()); // cikarir: 1, O(log n)
		System.out.println(pq.poll()); // 5
		System.out.println(pq.size()); // 1

		// Heap sirali degildir; yalnizca kok bellidir
		PriorityQueue<Integer> p = new PriorityQueue<>();
		p.offer(3);
		p.offer(1);
		p.offer(2);
		System.out.println("3, 1, 2 eklendi: " + p);

		// Max-heap icin ters karsilastirici
		PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());
		maxPq.offer(5);
		maxPq.offer(1);
		maxPq.offer(8);
		System.out.println("maxPq.peek() = " + maxPq.peek());

		// Nesneler icin karsilastirici: oncelik kucuk olan once
		PriorityQueue<Hasta> acil = new PriorityQueue<>(Comparator.comparingInt(h -> h.oncelik));
		acil.offer(new Hasta("burkulma", 4));
		acil.offer(new Hasta("kalp krizi", 1));
		acil.offer(new Hasta("kirik", 3));
		acil.offer(new Hasta("yuksek ates", 2));
		while (!acil.isEmpty()) {
			Hasta h = acil.poll();
			System.out.println("Siradaki hasta: (" + h.oncelik + ", " + h.ad + ")");
		}
	}
}
