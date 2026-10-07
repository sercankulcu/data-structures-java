// Yigin arayuzunu dizi ile gerceklestiren sinif (Slayt 19, 20).
// Tum islemler sabit sayida adim surer: O(1).
public class DiziYigin<E> implements Yigin<E> {
	private E[] veri;         // ogeler
	private int t = -1;       // ust ogenin indisi (bos yiginda -1)

	@SuppressWarnings("unchecked")  // Java'da new E[] yazilamaz; Object[] donusturulur
	public DiziYigin(int kapasite) {
		veri = (E[]) new Object[kapasite];
	}

	public int size() { return t + 1; }
	public boolean isEmpty() { return t == -1; }

	public E top() {
		if (isEmpty()) return null;
		return veri[t];
	}

	public void push(E e) {
		if (size() == veri.length)
			throw new IllegalStateException("dolu");
		veri[++t] = e;        // once artir, sonra yaz
	}

	public E pop() {
		if (isEmpty()) return null;
		E e = veri[t];
		veri[t--] = null;     // GC icin bosalt
		return e;
	}

	public static void main(String[] args) {
		Yigin<Integer> y = new DiziYigin<>(4);   // kapasite 4, t = -1
		y.push(5); y.push(8);
		System.out.println(y.pop());             // 8 (LIFO)
		y.push(3);                               // son durum: [5, 3], t = 1
		System.out.println("top = " + y.top() + ", size = " + y.size());
	}
}
