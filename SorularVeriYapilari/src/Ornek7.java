import java.util.Arrays;

public class Ornek7<E> {
	
	final int DEFAULT_CAPACITY = 8;

	private int capacity;
	private int N; // number of items
	private Object[] items;

	public Ornek7() {
		this.N = 0;
		this.capacity = DEFAULT_CAPACITY;
		this.items = new Object[DEFAULT_CAPACITY];
	}
	
	private void shrinkStretch() {
		if (N < capacity / 4) {
			capacity /= 2;
			items = Arrays.copyOf(items, capacity);
		} else if (N > capacity / 2) {
			capacity *= 2;
			items = Arrays.copyOf(items, capacity);
		}
	}

	public void removeItem() {
		items[N--] = null;
		shrinkStretch();
	}
	
	public void addItem(E e) {
		items[N++] = e;
		shrinkStretch();
	}
	
	public static void main(String[] args) {
		Ornek7<Integer> ornek = new Ornek7<>();
		for(int i = 0; i < 20; i++) {
			ornek.addItem(i);
		}
		for(int i = 0; i < 15; i++) {
			ornek.removeItem();
		}
		System.out.println(Arrays.toString(ornek.items));
	}
}
