
public class AyrikKumeDizi {
	
	private int[] ata;
	private int[] sira;

	public AyrikKumeDizi(int boyut) {
		ata = new int[boyut];
		sira = new int[boyut];
		for (int i = 0; i < boyut; i++) {
			ata[i] = i;
			sira[i] = 0;
		}
	}
	
	void listAtalari() {
		System.out.println("Küme elemanları ataları:");
		for (int i = 0; i < ata.length; i++) {
			System.out.print(find(i) + " ");
		}
		System.out.println();
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

		AyrikKumeDizi kume = new AyrikKumeDizi(5);

		kume.listAtalari();
		kume.union(0, 1);
		kume.union(2, 3);
		kume.union(0, 4);
		kume.listAtalari();
		
	}
}
