
public class IkiliAgacGorsellestirme {
	
	public static void agaciYazdir(Dugum<Integer> kok, String onEk, boolean solDugum) {
		if (kok != null) {
			System.out.println(onEk + (solDugum ? "├── " : "└── ") + kok.veri);
			agaciYazdir(kok.sol, onEk + (solDugum ? "│   " : "    "), true);
			agaciYazdir(kok.sag, onEk + (solDugum ? "│   " : "    "), false);
		}
	}

	public static void main(String[] args) {
		Dugum<Integer> kok = new Dugum<Integer>(1);
		kok.sol = new Dugum<Integer>(2);
		kok.sag = new Dugum<Integer>(3);
		kok.sol.sol = new Dugum<Integer>(4);
		kok.sol.sag = new Dugum<Integer>(5);
		kok.sag.sol = new Dugum<Integer>(6);
		kok.sag.sag = new Dugum<Integer>(7);

		System.out.println("İkili Ağaç Görselleştirme:");
		agaciYazdir(kok, "", true);
	}
}
