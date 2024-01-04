
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YonluCizge {
	
	private class Kenar {
		private String hedef;
		private int agirlik;

		public Kenar(String hedef, int agirlik) {
			this.hedef = hedef;
			this.agirlik = agirlik;
		}

		public String toString() {
			return "(" + hedef + ", w: " + agirlik + ")";
		}
	}
	
	private Map<String, List<Kenar>> komsuListesi;

	public YonluCizge() {
		komsuListesi = new HashMap<>();
	}

	public void dugumEkle(String dugum) {
		if (!komsuListesi.containsKey(dugum)) {
			komsuListesi.put(dugum, new ArrayList<>());
		}
	}

	public void kenarEkle(String kaynak, String hedef, int agirlik) {
		if (!komsuListesi.containsKey(kaynak) || !komsuListesi.containsKey(hedef)) {
			throw new IllegalArgumentException("Başlangıç ve hedef düğümler grafiğe eklenmeli.");
		}
		Kenar yeniKenar = new Kenar(hedef, agirlik);
		komsuListesi.get(kaynak).add(yeniKenar);
	}

	public List<Kenar> kenarlarıAl(String dugum) {
		if (!komsuListesi.containsKey(dugum)) {
			throw new IllegalArgumentException("Düğüm grafikte bulunmuyor.");
		}
		return komsuListesi.get(dugum);
	}

	public static void main(String[] args) {
		
		YonluCizge cizge = new YonluCizge();
		cizge.dugumEkle("A");
		cizge.dugumEkle("B");
		cizge.dugumEkle("C");
		cizge.dugumEkle("D");
		cizge.dugumEkle("E");

		cizge.kenarEkle("A", "B", 2);
		cizge.kenarEkle("B", "C", 3);
		cizge.kenarEkle("A", "C", 1);
		cizge.kenarEkle("A", "D", 4);
		cizge.kenarEkle("B", "E", 5);

		System.out.println("Kenarlar A: " + cizge.kenarlarıAl("A"));
		System.out.println("Kenarlar B: " + cizge.kenarlarıAl("B"));
		System.out.println("Kenarlar C: " + cizge.kenarlarıAl("C"));
		System.out.println("Kenarlar C: " + cizge.kenarlarıAl("D"));
		System.out.println("Kenarlar C: " + cizge.kenarlarıAl("E"));
	}
}



