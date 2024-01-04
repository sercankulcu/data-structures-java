
import java.util.HashMap;
import java.util.Map;

public class AyrikKume {
	private Map<Integer, Integer> kume = new HashMap<>();

	public void makeSet(int eleman) {
		kume.put(eleman, eleman);
	}

	public int find(int eleman) {
		if (kume.get(eleman) == eleman) {
			return eleman;
		}
		return find(kume.get(eleman));
	}

	public void union(int eleman1, int eleman2) {
		int kok1 = find(eleman1);
		int kok2 = find(eleman2);

		if (kok1 != kok2) {
			kume.put(kok1, kok2);
		}
	}

	public static void main(String[] args) {
		AyrikKume ds = new AyrikKume();

		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);

		ds.union(1, 2); // {{1, 2}{3}{4}{5}}
		ds.union(3, 4); // {{1, 2}{3, 4}{5}}
		ds.union(4, 5); // {{1, 2}{3, 4, 5}}

		System.out.println("1 ve 2 aynı kümede mi? " + (ds.find(1) == ds.find(2)));
		System.out.println("1 ve 3 aynı kümede mi? " + (ds.find(1) == ds.find(3)));
		
		ds.union(1, 5); // {{1, 2, 3, 4, 5}}
		
		System.out.println("1 ve 2 aynı kümede mi? " + (ds.find(1) == ds.find(2)));
		System.out.println("1 ve 3 aynı kümede mi? " + (ds.find(1) == ds.find(3)));
	}
}
