
class SetDugum {
	int veri;
	SetDugum ata;
	int sira;
	public SetDugum(int veri) {
		this.veri = veri;
		this.ata = this;
		this.sira = 0;
	}
}

public class DisjointSetBagliListe {
	SetDugum find(SetDugum node) {
		if (node != node.ata) {
			// yol kısaltma
			node.ata = find(node.ata);
		}
		return node.ata;
	}

	void union(SetDugum x, SetDugum y) {
		SetDugum kokX = find(x);
		SetDugum kokY = find(y);
		// ağaç büyüklüğüne göre birleştirme
		if (kokX != kokY) {
			if (kokX.sira < kokY.sira) {
				kokX.ata = kokY;
			} else if (kokX.sira > kokY.sira) {
				kokY.ata = kokX;
			} else {
				// siralar eşitse birini seç
				kokX.ata = kokY;
				kokY.sira++;
			}
		}
	}

	public static void main(String[] args) {
		// Create nodes for elements in the disjoint sets
		SetDugum[] dugumler = new SetDugum[5];
		for (int i = 0; i < dugumler.length; i++) {
			dugumler[i] = new SetDugum(i);
		}
		
		System.out.println("Initial sets:");
		for (int i = 0; i < dugumler.length; i++) {
			System.out.print(dugumler[i].veri + " ");
		}
		System.out.println();

		// Create disjoint set and perform union operations
		DisjointSetBagliListe ds = new DisjointSetBagliListe();
		ds.union(dugumler[0], dugumler[1]);
		ds.union(dugumler[2], dugumler[3]);
		ds.union(dugumler[0], dugumler[4]);

		// Print representative of each node after union operations
		System.out.println("Sets after union operations:");
		for (int i = 0; i < dugumler.length; i++) {
			System.out.print(ds.find(dugumler[i]).veri  + " ");
		}
		System.out.println();
	}
}
