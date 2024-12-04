import java.util.ArrayList;

public class AyrikKumeBagliListe {
	
	// Ayrik kume elemanlarini temsil eden dugum sinifi
	class SetDugum {
		
		private int veri; // Dugumun degeri
		private SetDugum ata; // Dugumun ata (lider) dugumu
		private int sira; // Dugumun agactaki derinligi (sira)

		// SetDugum nesnesi olusturulurken veri atanir, atasi kendisi ve sira 0 olarak baslatilir
		public SetDugum(int veri) {
			this.veri = veri;
			this.ata = this;
			this.sira = 0;
		}
	}
	
	// Ayrik kume elemanlarini tutmak icin dinamik liste
	private ArrayList<SetDugum> dugumler;

	// Ayrik kume nesnesi olusturulurken dugumler dinamik olarak eklenebilir
	public AyrikKumeBagliListe() {
		dugumler = new ArrayList<>();
	}
	
	// Yeni bir eleman ekler, ancak ayni eleman varsa eklemez
	public void makeSet(int veri) {
		// Eleman zaten mevcutsa eklenmez
		for (SetDugum dugum : dugumler) {
			if (dugum.veri == veri) {
				return; // Ayni eleman bulunduysa metottan cikilir
			}
		}
		dugumler.add(new SetDugum(veri)); // Yeni dugumu ekler
	}
	
	// Verilen index'e ait dugumu dondurur
	SetDugum getir(int index) {
		return dugumler.get(index);
	}
	
	// Ayrik kumedeki elemanlarin atalarini listeler
	void atalariListele() {
		System.out.println("Kume elemanlari atalari:");
		for (SetDugum dugum : dugumler) {
			// her eleman atasi ile birlikte yazdirilir
			System.out.print(find(dugum).veri + " ");
		}
		System.out.println();
	}
	
	// Ayrik kumedeki tum elemanlari listeler
	void listele() {
		System.out.println("Kume elemanlari:");
		for (SetDugum dugum : dugumler) {
			System.out.print(dugum.veri + " ");
		}
		System.out.println();
	}

	// Dugumun liderini bulur ve yol kisaltma yapar
	SetDugum find(SetDugum dugum) {
		if (dugum != dugum.ata) {
			// Yol kisaltma yaparak atayi bulur
			dugum.ata = find(dugum.ata);
		}
		return dugum.ata;
	}

	// iki elemanin birlestirilmesi, agac buyuklugune gore yapilir
	void union(SetDugum x, SetDugum y) {
		SetDugum kokX = find(x); // x'in atasini bulur
		SetDugum kokY = find(y); // y'nin atasini bulur
		// Eger atalar farkliysa, onlari birlestirir
		if (kokX != kokY) {
			// Agacin derinligine gore birlestirme yapilir
			if (kokX.sira < kokY.sira) {
				kokX.ata = kokY; // KokX daha kucukse KokY'yi ata yapariz
			} else if (kokX.sira > kokY.sira) {
				kokY.ata = kokX; // KokY daha kucukse KokX'i ata yapariz
			} else {
				// Siralar esitse, birini secip digerini ata yapariz ve derinlik bir artirilir
				kokX.ata = kokY;
				kokY.sira++;
			}
		}
	}

	public static void main(String[] args) {
		
		// Ayrik kume nesnesi olusturuluyor
		AyrikKumeBagliListe kume = new AyrikKumeBagliListe();
		for(int i = 0; i < 10; i++) {
			kume.makeSet(i); // i. eleman ekleniyor
		}
		kume.makeSet(2); // Ayni eleman tekrar eklenmeye calisiliyor, eklenmez
		
		kume.listele(); // Ayrik kumedeki elemanlar yazdirilir
		kume.atalariListele(); // Ayrik kumedeki elemanlarin atalari yazdirilir
		
		// Kumeler birlestirilir
		kume.union(kume.getir(0), kume.getir(1)); // {0, 1} birlesir
		kume.union(kume.getir(2), kume.getir(3)); // {2, 3} birlesir
		kume.union(kume.getir(0), kume.getir(4)); // {0, 1, 4} birlesir
		kume.union(kume.getir(5), kume.getir(4));
		kume.union(kume.getir(6), kume.getir(7));
		kume.union(kume.getir(8), kume.getir(9));
		kume.atalariListele(); // Birlesimden sonra kumedeki elemanlarin atalari yazdirilir
		kume.union(kume.getir(7), kume.getir(9));
		kume.atalariListele();
	}
}
