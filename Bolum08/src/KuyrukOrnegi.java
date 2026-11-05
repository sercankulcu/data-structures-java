import java.util.ArrayDeque;
import java.util.Queue;

// Java kutuphanesindeki kuyruk: Queue arayuzu, ArrayDeque gerceklemesi
// Kendi siniflarimizda enqueue/dequeue, kutuphanede offer/poll/peek kullanilir.
public class KuyrukOrnegi {

	public static void main(String[] args) {
		// ArrayDeque dairesel dizi ile gerceklenir
		Queue<Integer> q = new ArrayDeque<>();

		// Arkaya ekle (enqueue)
		q.offer(20);
		q.offer(10);
		q.offer(15);
		System.out.println("Kuyruk (onden arkaya): " + q);   // [20, 10, 15]

		// Ondeki ogeye bak, cikarma (peek)
		System.out.println("peek() = " + q.peek());          // 20

		// Ondeki ogeyi cikar (poll)
		System.out.println("poll() = " + q.poll() + " -> " + q);  // 20 -> [10, 15]
		System.out.println("poll() = " + q.poll() + " -> " + q);  // 10 -> [15]
		System.out.println("size() = " + q.size());          // 1
		System.out.println("poll() = " + q.poll() + " -> " + q);  // 15 -> []

		// Bos kuyrukta poll/peek hata firlatmaz, null dondurur
		System.out.println("poll() = " + q.poll() + " (bos kuyruk)");

		// remove() ise bos kuyrukta istisna firlatir
		try {
			q.remove();
		} catch (java.util.NoSuchElementException e) {
			System.out.println("remove() bos kuyrukta: NoSuchElementException");
		}
	}
}
