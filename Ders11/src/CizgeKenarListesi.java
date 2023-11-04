
import java.util.ArrayList;
import java.util.List;

public class CizgeKenarListesi {
	
	private class Kenar {
		private String başlangıçDüğüm;
		private String hedefDüğüm;
		private int ağırlık;

		public Kenar(String başlangıçDüğüm, String hedefDüğüm, int ağırlık) {
			this.başlangıçDüğüm = başlangıçDüğüm;
			this.hedefDüğüm = hedefDüğüm;
			this.ağırlık = ağırlık;
		}

		public String toString() {
			return "(" + başlangıçDüğüm + " -> " + hedefDüğüm + ", Ağırlık: " + ağırlık + ")";
		}
	}
	
	private List<String> düğümler;
	private List<Kenar> kenarlar;

	public CizgeKenarListesi() {
		düğümler = new ArrayList<>();
		kenarlar = new ArrayList<>();
	}

	public void düğümEkle(String düğüm) {
		düğümler.add(düğüm);
	}

	public void kenarEkle(String başlangıçDüğüm, String hedefDüğüm, int ağırlık) {
		if (!düğümler.contains(başlangıçDüğüm) || !düğümler.contains(hedefDüğüm)) {
			throw new IllegalArgumentException("Başlangıç ve hedef düğümler grafiğe eklenmeli.");
		}
		Kenar yeniKenar = new Kenar(başlangıçDüğüm, hedefDüğüm, ağırlık);
		kenarlar.add(yeniKenar);
	}

	public List<Kenar> kenarlarıAl() {
		return kenarlar;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Düğümler: ");
		sb.append(String.join(", ", düğümler));
		sb.append("\nKenarlar: ");
		for (Kenar kenar : kenarlar) {
			sb.append(kenar.toString());
			sb.append(", ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		CizgeKenarListesi graf = new CizgeKenarListesi();
		graf.düğümEkle("A");
		graf.düğümEkle("B");
		graf.düğümEkle("C");

		graf.kenarEkle("A", "B", 2);
		graf.kenarEkle("B", "C", 3);
		graf.kenarEkle("A", "C", 1);

		System.out.println(graf);
		System.out.println(graf.kenarlarıAl());
	}
}


