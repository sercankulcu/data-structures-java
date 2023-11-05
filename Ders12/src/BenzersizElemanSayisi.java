
import java.util.HashSet;

public class BenzersizElemanSayisi {
	public static void main(String[] args) {
		int[] dizi = {3, 5, 2, 6, 5, 3, 8, 9, 2, 1};

		int benzersizSayisi = benzersizElemanSayisi(dizi);

		System.out.println("Benzersiz eleman sayısı: " + benzersizSayisi);
	}

	public static int benzersizElemanSayisi(int[] dizi) {
		HashSet<Integer> benzersizKume = new HashSet<>();

		for (int sayi : dizi) {
			benzersizKume.add(sayi);
		}

		return benzersizKume.size();
	}
}
