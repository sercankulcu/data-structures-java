import java.util.ArrayDeque;
import java.util.Queue;

// Uygulama: round robin zamanlayici
// Ondeki surec en fazla "dilim" birim calisir; bitmediyse kuyrugun arkasina doner.
public class RoundRobin {

	// Surec: adi ve kalan calisma suresi
	static class Surec {
		String ad;
		int kalan;
		Surec(String ad, int kalan) {
			this.ad = ad;
			this.kalan = kalan;
		}
	}

	static void roundRobin(Queue<Surec> q, int dilim) {
		int t = 0;
		while (!q.isEmpty()) {
			Surec p = q.poll();
			int calis = Math.min(dilim, p.kalan);
			t += calis;
			p.kalan -= calis;
			if (p.kalan > 0) q.offer(p);                  // bitmedi: arkaya don
			else System.out.println(p.ad + " t=" + t);    // bitti: tamamlanma zamani
		}
	}

	public static void main(String[] args) {
		// Slayt 38-39: kalan sureler 5, 3, 2; zaman dilimi q = 2
		Queue<Surec> q = new ArrayDeque<>();
		q.offer(new Surec("P1", 5));
		q.offer(new Surec("P2", 3));
		q.offer(new Surec("P3", 2));
		roundRobin(q, 2);   // P3 t=6, P2 t=9, P1 t=10
	}
}
