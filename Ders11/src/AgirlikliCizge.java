
import java.util.ArrayList;
import java.util.List;

public class AgirlikliCizge {
	
	private class Kenar {
		private String kaynak;
		private String hedef;
		private int agirlik;

		public Kenar(String kaynak, String hedef, int agirlik) {
			this.kaynak = kaynak;
			this.hedef = hedef;
			this.agirlik = agirlik;
		}

		public String toString() {
			return "(" + kaynak + " -> " + hedef + ", w: " + agirlik + ")";
		}
	}
	
	private List<String> dugumler;
	private List<Kenar> kenarlar;

	public AgirlikliCizge() {
		dugumler = new ArrayList<>();
		kenarlar = new ArrayList<>();
	}

	public void dugumEkle(String dugum) {
		dugumler.add(dugum);
	}

	public void kenarEkle(String kaynak, String hedef, int agirlik) {
		if (!dugumler.contains(kaynak) || !dugumler.contains(hedef)) {
			throw new IllegalArgumentException("Başlangıç ve hedef düğümler grafiğe eklenmeli.");
		}
		Kenar yeniKenar = new Kenar(kaynak, hedef, agirlik);
		kenarlar.add(yeniKenar);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Düğümler: ");
		sb.append(String.join(", ", dugumler));
		sb.append("\nKenarlar: ");
		for (Kenar kenar : kenarlar) {
			sb.append(kenar.toString());
			sb.append(", ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		AgirlikliCizge cizge = new AgirlikliCizge();
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

		System.out.println(cizge);
		System.out.println(cizge.kenarlar);
	}
}


