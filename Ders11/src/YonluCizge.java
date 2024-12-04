import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YonluCizge {
	
	// Kenar sinifi: Her kenari ve agirligini temsil eder
	private class Kenar {
		private String hedef;
		private int agirlik;

		public Kenar(String hedef, int agirlik) {
			this.hedef = hedef;
			this.agirlik = agirlik;
		}

		// Kenarin metin temsili: hedef ve agirlik bilgilerini string olarak dondurur
		public String toString() {
			return "(" + hedef + ", w: " + agirlik + ")";
		}
	}
	
	// Cizgenin komsu listesi: Her dugume ait kenarlari tutan bir esleme
	private Map<String, List<Kenar>> komsuListesi;

	public YonluCizge() {
		komsuListesi = new HashMap<>(); // Cizgeyi baslat, komsu listesi bos
	}

	// Yeni bir dugum ekler
	public void dugumEkle(String dugum) {
		// Dugum daha once eklenmediyse, onu komsu listesine ekler
		if (!komsuListesi.containsKey(dugum)) {
			komsuListesi.put(dugum, new ArrayList<>());
		}
	}

	// Cizgeye yeni bir kenar ekler
	public void kenarEkle(String kaynak, String hedef, int agirlik) {
		// Kaynak veya hedef dugumu cizgede yoksa, hata firlat
		if (!komsuListesi.containsKey(kaynak) || !komsuListesi.containsKey(hedef)) {
			throw new IllegalArgumentException("Baslangic ve hedef dugumler cizgeye eklenmeli.");
		}
		// Yeni bir kenar olustur ve kaynak dugumunun komsu listesine ekle
		Kenar yeniKenar = new Kenar(hedef, agirlik);
		komsuListesi.get(kaynak).add(yeniKenar);
	}

	// Belirli bir dugumun komsularini alir
	public List<Kenar> kenarlariAl(String dugum) {
		// Dugum cizgede yoksa, hata firlat
		if (!komsuListesi.containsKey(dugum)) {
			throw new IllegalArgumentException("Dugum grafikte bulunmuyor.");
		}
		// Dugumun komsu listesini geri dondur
		return komsuListesi.get(dugum);
	}

	// Ana metod: Cizgeyi olusturur ve kenarlari ekler
	public static void main(String[] args) {
		
		YonluCizge cizge = new YonluCizge(); 
		// Cizgeye dugumleri ekle
		cizge.dugumEkle("A");
		cizge.dugumEkle("B");
		cizge.dugumEkle("C");
		cizge.dugumEkle("D");
		cizge.dugumEkle("E");

		// Cizgeye kenarlari ekle
		cizge.kenarEkle("A", "B", 2);
		cizge.kenarEkle("B", "C", 3);
		cizge.kenarEkle("A", "C", 1);
		cizge.kenarEkle("A", "D", 4);
		cizge.kenarEkle("B", "E", 5);
		cizge.kenarEkle("C", "E", 5);
		cizge.kenarEkle("D", "E", 5);
		cizge.kenarEkle("E", "A", 5);

		// Her dugum icin komsulari ekrana yazdir
		System.out.println("Kenarlar A: " + cizge.kenarlariAl("A"));
		System.out.println("Kenarlar B: " + cizge.kenarlariAl("B"));
		System.out.println("Kenarlar C: " + cizge.kenarlariAl("C"));
		System.out.println("Kenarlar D: " + cizge.kenarlariAl("D"));
		System.out.println("Kenarlar E: " + cizge.kenarlariAl("E"));
	}
}
