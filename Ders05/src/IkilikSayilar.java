import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Uygulama: kuyruk ile 1'den n'e kadar ikilik sayilar
// x'in iki ardili x + "0" ve x + "1"dir; kuyruk sayilari artan sirada (seviye seviye) uretir.
public class IkilikSayilar {

	static String[] ikilikSayiUret(int n) {
		String[] sonuc = new String[n];
		Queue<String> q = new LinkedList<>();
		q.offer("1");
		for (int i = 0; i < n; i++) {
			sonuc[i] = q.poll();        // ondeki en kucuk sayi
			q.offer(sonuc[i] + "0");
			q.offer(sonuc[i] + "1");
		}
		return sonuc;
	}

	public static void main(String[] args) {
		// Slayt 42-43
		System.out.println("n = 3: " + Arrays.toString(ikilikSayiUret(3)));   // [1, 10, 11]
		System.out.println("n = 4: " + Arrays.toString(ikilikSayiUret(4)));   // [1, 10, 11, 100]
		System.out.println("n = 5: " + Arrays.toString(ikilikSayiUret(5)));   // [1, 10, 11, 100, 101]
	}
}
