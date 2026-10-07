import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;

// Vector: metotlari synchronized, eski (legacy) buyuyebilen dizi.
// Stack: Vector'dan turer, LIFO yigin. Yeni kodda ArrayDeque onerilir.
public class VectorOrnegi {
	public static void main(String[] args) {
		System.out.println("--- Vector ---");
		Vector<Integer> v = new Vector<>();
		v.add(10);
		v.add(20);
		System.out.println(v);                          // [10, 20]
		System.out.println("get(0) = " + v.get(0));     // 10
		v.remove(0);
		System.out.println(v);                          // [20]
		System.out.println("size() = " + v.size());     // 1
		System.out.println("capacity() = " + v.capacity());   // 10

		// Bilesik islem ("kontrol et, sonra yap"): tek tek senkronize metotlar
		// birlikte atomik degildir, disaridan kilit gerekir
		synchronized (v) {
			if (!v.isEmpty())
				v.remove(0);
		}
		System.out.println("Bilesik islem sonrasi: " + v);   // []

		System.out.println("--- Stack ---");
		Stack<String> yigin = new Stack<>();
		yigin.push("A");
		yigin.push("B");
		yigin.push("C");
		System.out.println(yigin);                              // [A, B, C] (ust sagda)
		System.out.println("peek() = " + yigin.peek());         // C
		System.out.println("search(A) = " + yigin.search("A")); // 3 (ustten uzaklik)
		System.out.println("pop() = " + yigin.pop());           // C
		// Vector'dan turedigi icin LIFO'yu bozan metotlar da vardir
		yigin.add(0, "X");
		System.out.println("add(0, X) sonrasi: " + yigin);     // [X, A, B]
		yigin.clear();
		System.out.println("empty() = " + yigin.empty());       // true
		try {
			yigin.pop();
		} catch (EmptyStackException e) {
			System.out.println("Bos yiginda pop() -> EmptyStackException");
		}

		// Onerilen yigin: Deque arayuzu ve ArrayDeque
		Deque<String> s = new ArrayDeque<>();
		s.push("A");
		s.push("B");
		System.out.println("ArrayDeque: " + s + ", pop() = " + s.pop());   // [B, A], B
	}
}
