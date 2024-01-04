import java.util.HashSet;

public class BirlesimKesisim {
	
	public static void main(String[] args) {
		
		int[] dizi1 = {3, 8, 10, 23, 34, 1};
		int[] dizi2 = {10, 34, 7, 2, 8};

		// Birleşim ve kesişimi bul
		birlesim(dizi1, dizi2);
		kesisim(dizi1, dizi2);
	}

	public static void birlesim(int[] dizi1, int[] dizi2) {
		HashSet<Integer> kume = new HashSet<>();

		for (int num : dizi1) {
			kume.add(num);
		}

		for (int num : dizi2) {
			kume.add(num);
		}

		System.out.println("Dizilerin birleşimi: " + kume);
	}

	public static void kesisim(int[] dizi1, int[] dizi2) {
		HashSet<Integer> kume = new HashSet<>();
		HashSet<Integer> kesisim = new HashSet<>();

		for (int num : dizi1) {
			kume.add(num);
		}

		for (int num : dizi2) {
			if (kume.contains(num)) {
				kesisim.add(num);
			}
		}

		System.out.println("Dizilerin kesişimi: " + kesisim);
	}
}
