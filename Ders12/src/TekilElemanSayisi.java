
import java.util.HashSet;

public class TekilElemanSayisi {
	
	public static void main(String[] args) {
		
		int[] dizi = {3, 5, 2, 6, 5, 3, 8, 9, 2, 1};
		System.out.println("Benzersiz eleman sayısı: " + elemanSayisi(dizi));
	}

	public static int elemanSayisi(int[] dizi) {
		
		HashSet<Integer> kume = new HashSet<>();
		for (int sayi : dizi) {
			kume.add(sayi);
		}
		return kume.size();
	}
}
