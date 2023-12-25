
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CizgeKomsulukListesi {
	
	private class Kenar {
		private String hedefDugum;
		private int agirlik;

		public Kenar(String hedefDugum, int agirlik) {
			this.hedefDugum = hedefDugum;
			this.agirlik = agirlik;
		}

		public String toString() {
			return "(" + hedefDugum + ", Ağırlık: " + agirlik + ")";
		}
	}
	
	private Map<String, List<Kenar>> komsulukListesi;

	public CizgeKomsulukListesi() {
		komsulukListesi = new HashMap<>();
	}

	public void dugumEkle(String dugum) {
		if (!komsulukListesi.containsKey(dugum)) {
			komsulukListesi.put(dugum, new ArrayList<>());
		}
	}

	public void kenarEkle(String baslangicDugum, String hedefDugum, int agirlik) {
		if (!komsulukListesi.containsKey(baslangicDugum) || !komsulukListesi.containsKey(hedefDugum)) {
			throw new IllegalArgumentException("Başlangıç ve hedef düğümler grafiğe eklenmeli.");
		}
		Kenar yeniKenar = new Kenar(hedefDugum, agirlik);
		komsulukListesi.get(baslangicDugum).add(yeniKenar);
	}

	public List<Kenar> kenarlarıAl(String dugum) {
		if (!komsulukListesi.containsKey(dugum)) {
			throw new IllegalArgumentException("Düğüm grafikte bulunmuyor.");
		}
		return komsulukListesi.get(dugum);
	}

	public static void main(String[] args) {
		CizgeKomsulukListesi cizge = new CizgeKomsulukListesi();
		cizge.dugumEkle("A");
		cizge.dugumEkle("B");
		cizge.dugumEkle("C");

		cizge.kenarEkle("A", "B", 2);
		cizge.kenarEkle("B", "C", 3);
		cizge.kenarEkle("A", "C", 1);

		System.out.println("Kenarlar A: " + cizge.kenarlarıAl("A"));
		System.out.println("Kenarlar B: " + cizge.kenarlarıAl("B"));
		System.out.println("Kenarlar C: " + cizge.kenarlarıAl("C"));
	}
}



