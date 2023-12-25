
import java.util.ArrayList;
import java.util.List;

public class CizgeKenarListesi {
	
	private class Kenar {
		private String baslangic;
		private String hedef;
		private int agirlik;

		public Kenar(String baslangic, String hedef, int agirlik) {
			this.baslangic = baslangic;
			this.hedef = hedef;
			this.agirlik = agirlik;
		}

		public String toString() {
			return "(" + baslangic + " -> " + hedef + ", Ağırlık: " + agirlik + ")";
		}
	}
	
	private List<String> dugumler;
	private List<Kenar> kenarlar;

	public CizgeKenarListesi() {
		dugumler = new ArrayList<>();
		kenarlar = new ArrayList<>();
	}

	public void dugumEkle(String düğüm) {
		dugumler.add(düğüm);
	}

	public void kenarEkle(String baslangic, String hedef, int agirlik) {
		if (!dugumler.contains(baslangic) || !dugumler.contains(hedef)) {
			throw new IllegalArgumentException("Başlangıç ve hedef düğümler grafiğe eklenmeli.");
		}
		Kenar yeniKenar = new Kenar(baslangic, hedef, agirlik);
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
		CizgeKenarListesi cizge = new CizgeKenarListesi();
		cizge.dugumEkle("A");
		cizge.dugumEkle("B");
		cizge.dugumEkle("C");

		cizge.kenarEkle("A", "B", 2);
		cizge.kenarEkle("B", "C", 3);
		cizge.kenarEkle("A", "C", 1);

		System.out.println(cizge);
		System.out.println(cizge.kenarlar);
	}
}


