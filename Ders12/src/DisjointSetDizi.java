
public class DisjointSetDizi {
	private int[] ata;
	private int[] sira;

	public DisjointSetDizi(int size) {
		ata = new int[size];
		sira = new int[size];
		initializeSets();
	}

	private void initializeSets() {
		for (int i = 0; i < ata.length; i++) {
			ata[i] = i;
			sira[i] = 0;
		}
	}

	int find(int x) {
		if (ata[x] != x) {
			// yol kısaltma
			ata[x] = find(ata[x]);
		}
		return ata[x];
	}

	void union(int x, int y) {
		int kokX = find(x);
		int kokY = find(y);
		// ağaç büyüklüğüne göre birleştirme
		if (kokX != kokY) {
			if (sira[kokX] < sira[kokY]) {
				ata[kokX] = kokY;
			} else if (sira[kokX] > sira[kokY]) {
				ata[kokY] = kokX;
			} else {
				// siralar eşitse birini seç
				ata[kokX] = kokY;
				sira[kokY]++;
			}
		}
	}

	public static void main(String[] args) {

		DisjointSetDizi ds = new DisjointSetDizi(5);

		System.out.println("Initial sets:");
		for (int i = 0; i < ds.ata.length; i++) {
			System.out.print(ds.find(i) + " ");
		}
		System.out.println();

		// Perform union operations
		ds.union(0, 1);
		ds.union(2, 3);
		ds.union(0, 4);

		System.out.println("Sets after union operations:");
		for (int i = 0; i < ds.ata.length; i++) {
			System.out.print(ds.find(i) + " ");
		}
		System.out.println();
	}
}
