import java.util.HashSet;

public class BirlesimKesisim {
	public static void main(String[] args) {
		int[] dizi1 = {3, 8, 10, 23, 34, 1};
		int[] dizi2 = {10, 34, 7, 2, 8};

		// Birleşim ve kesişimi bul
		birlesimiBul(dizi1, dizi2);
		kesisimiBul(dizi1, dizi2);
	}

	public static void birlesimiBul(int[] dizi1, int[] dizi2) {
		HashSet<Integer> birlesimKumesi = new HashSet<>();

		for (int num : dizi1) {
			birlesimKumesi.add(num);
		}

		for (int num : dizi2) {
			birlesimKumesi.add(num);
		}

		System.out.println("Dizilerin birleşimi: " + birlesimKumesi);
	}

	public static void kesisimiBul(int[] dizi1, int[] dizi2) {
		HashSet<Integer> kume1 = new HashSet<>();
		HashSet<Integer> kesisimKumesi = new HashSet<>();

		for (int num : dizi1) {
			kume1.add(num);
		}

		for (int num : dizi2) {
			if (kume1.contains(num)) {
				kesisimKumesi.add(num);
			}
		}

		System.out.println("Dizilerin kesişimi: " + kesisimKumesi);
	}
}
