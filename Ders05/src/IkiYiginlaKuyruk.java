import java.util.ArrayDeque;
import java.util.Deque;

// Uygulama: iki yiginla kuyruk
// gelen eklemeler, giden cikarmalar icindir. giden bosalinca gelen'deki her sey aktarilir;
// aktarim sirayi ters cevirir. Her oge en cok bir kez aktarilir: dequeue amortize O(1).
public class IkiYiginlaKuyruk {

	Deque<Integer> gelen = new ArrayDeque<>();
	Deque<Integer> giden = new ArrayDeque<>();

	void enqueue(int x) {
		gelen.push(x);
	}

	// Bos kuyrukta giden.pop() NoSuchElementException firlatir
	int dequeue() {
		if (giden.isEmpty())
			while (!gelen.isEmpty())
				giden.push(gelen.pop());
		return giden.pop();
	}

	public static void main(String[] args) {
		// Slayt 44
		IkiYiginlaKuyruk q = new IkiYiginlaKuyruk();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		System.out.print("Cikis sirasi: " + q.dequeue());   // aktarim: 3, 2, 1 giden'e
		q.enqueue(4);
		System.out.print(" " + q.dequeue());
		System.out.print(" " + q.dequeue());
		System.out.println(" " + q.dequeue());            // 1 2 3 4 = giris sirasi
	}
}
