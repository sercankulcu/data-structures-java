
import java.util.HashMap;
import java.util.Map;

public class AyrikKume {
	private Map<Integer, Integer> ebeveyn = new HashMap<>();

	public void makeSet(int eleman) {
		ebeveyn.put(eleman, eleman);
	}

	public int find(int eleman) {
		if (ebeveyn.get(eleman) == eleman) {
			return eleman;
		}
		return find(ebeveyn.get(eleman));
	}

	public void union(int eleman1, int eleman2) {
		int kok1 = find(eleman1);
		int kok2 = find(eleman2);

		if (kok1 != kok2) {
			ebeveyn.put(kok1, kok2);
		}
	}

	public static void main(String[] args) {
		AyrikKume ds = new AyrikKume();

		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);

		ds.union(1, 2);
		ds.union(3, 4);
		ds.union(4, 5);

		System.out.println("1 ve 2 aynı kümede mi? " + (ds.find(1) == ds.find(2)));
		System.out.println("1 ve 3 aynı kümede mi? " + (ds.find(1) == ds.find(3)));
	}
}
