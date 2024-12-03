public class IkiliAgacGorsellestirme {
	
	// Agaci belirli bir formatta yazdirma islemi
	public static void agaciYazdir(Dugum<Integer> kok, String onEk, boolean solDugum) {
		if (kok != null) {
			// Dugumun verisini ve agacin yapisini ekrana yazdir
			System.out.println(onEk + (solDugum ? "├── " : "└── ") + kok.veri);
			// Sol alt agaci yazdir
			agaciYazdir(kok.sol, onEk + (solDugum ? "│   " : "    "), true);
			// Sag alt agaci yazdir
			agaciYazdir(kok.sag, onEk + (solDugum ? "│   " : "    "), false);
		}
	}

	public static void main(String[] args) {
		// Dugumler olusturuluyor ve agac yapisi kuruluyor
		Dugum<Integer> kok = new Dugum<>(1);
		kok.sol = new Dugum<Integer>(2);
		kok.sag = new Dugum<Integer>(3);
		kok.sol.sol = new Dugum<Integer>(4);
		kok.sol.sag = new Dugum<Integer>(5);
		kok.sag.sol = new Dugum<Integer>(6);
		kok.sag.sag = new Dugum<Integer>(7);

		// Agaci ekrana yazdir
		System.out.println("Ikili Agac Gorsellestirme:");
		agaciYazdir(kok, "", true);
	}
}
