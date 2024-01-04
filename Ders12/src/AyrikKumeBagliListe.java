
public class AyrikKumeBagliListe {
	
	class SetDugum {
		
		private int veri;
		private SetDugum ata;
		private int sira;
		
		public SetDugum(int veri) {
			this.veri = veri;
			this.ata = this;
			this.sira = 0;
		}
	}
	
	SetDugum[] dugumler;
	
	public AyrikKumeBagliListe(int boyut) {
		dugumler = new SetDugum[boyut];;
		for (int i = 0; i < boyut; i++) {
			dugumler[i] = new SetDugum(i);
		}
	}
	
	SetDugum at(int index) {
		return dugumler[index];
	}
	
	void listAtalari() {
		System.out.println("Küme elemanları ataları:");
		for (int i = 0; i < dugumler.length; i++) {
			System.out.print(find(dugumler[i]).veri  + " ");
		}
		System.out.println();
	}
	
	void list() {
		System.out.println("Küme elemanları:");
		for (int i = 0; i < dugumler.length; i++) {
			System.out.print(dugumler[i].veri + " ");
		}
		System.out.println();
	}

	SetDugum find(SetDugum dugum) {
		if (dugum != dugum.ata) {
			// yol kısaltma
			dugum.ata = find(dugum.ata);
		}
		return dugum.ata;
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
		
		AyrikKumeBagliListe kume = new AyrikKumeBagliListe(5);
		kume.list();
		kume.listAtalari();
		kume.union(kume.at(0), kume.at(1));
		kume.union(kume.at(2), kume.at(3));
		kume.union(kume.at(0), kume.at(4));
		kume.listAtalari();
	}
}
