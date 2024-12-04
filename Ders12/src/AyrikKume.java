import java.util.HashMap;
import java.util.Map;

public class AyrikKume {
	
	private Map<Integer, Integer> kume = new HashMap<>(); // Ayrik kume temsil edilir

	// Yeni bir elemanli kume olusturur, her eleman kendi atasi olarak atanir
	public void makeSet(int eleman) {
		kume.put(eleman, eleman);
	}

	// Verilen elemanin atasini bulur
	public int find(int eleman) {
		// Eleman kendisiyle esitse, atasi bulunmus demektir
		if (kume.get(eleman) == eleman) {
			return eleman;
		}
		// Aksi takdirde atasini bulmak icin recursive olarak aramaya devam eder
		return find(kume.get(eleman));
	}

	// Iki kume birlestirilir
	public void union(int eleman1, int eleman2) {
		// Her iki elemanin atasi bulunur
		int kok1 = find(eleman1);
		int kok2 = find(eleman2);

		// Atalari farkliysa, birlestir
		if (kok1 != kok2) {
			kume.put(kok1, kok2); // Kok1'in atasini kok2 olarak degistir
		}
	}

	public static void main(String[] args) {
		AyrikKume ds = new AyrikKume(); // AyrikKume sinifindan nesne olusturulur

		// Ayrik kumelere elemanlar eklenir
		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);

		// Kumeler birlestirilir
		ds.union(1, 2); // {{1, 2}{3}{4}{5}}
		ds.union(3, 4); // {{1, 2}{3, 4}{5}}
		ds.union(4, 5); // {{1, 2}{3, 4, 5}}

		// 1 ve 2 ayni kumede mi kontrol edilir
		System.out.println("1 ve 2 ayni kumede mi? " + (ds.find(1) == ds.find(2)));
		// 1 ve 3 ayni kumede mi kontrol edilir
		System.out.println("1 ve 3 ayni kumede mi? " + (ds.find(1) == ds.find(3)));
		
		// 1 ve 5 kumeleri birlestirilir
		ds.union(1, 5); // {{1, 2, 3, 4, 5}}
		
		// 1 ve 2 ayni kumede mi kontrol edilir
		System.out.println("1 ve 2 ayni kumede mi? " + (ds.find(1) == ds.find(2)));
		// 1 ve 3 ayni kumede mi kontrol edilir
		System.out.println("1 ve 3 ayni kumede mi? " + (ds.find(1) == ds.find(3)));
	}
}
