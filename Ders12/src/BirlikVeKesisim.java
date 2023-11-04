
import java.util.HashSet;
import java.util.Arrays;

public class BirlikVeKesisim {
	public static void main(String[] args) {
		int[] dizi1 = {1, 2, 3, 4, 5};
		int[] dizi2 = {3, 4, 5, 6, 7};

		int[] birlik = birliğiBul(dizi1, dizi2);
		int[] kesisim = kesisimiBul(dizi1, dizi2);

		System.out.println("Dizilerin Birliği: " + Arrays.toString(birlik));
		System.out.println("Dizilerin Kesişimi: " + Arrays.toString(kesisim));
	}

	public static int[] birliğiBul(int[] dizi1, int[] dizi2) {
		HashSet<Integer> birlikKume = new HashSet<>();
		for (int eleman : dizi1) {
			birlikKume.add(eleman);
		}
		for (int eleman : dizi2) {
			birlikKume.add(eleman);
		}

		int[] birlikDizi = new int[birlikKume.size()];
		int index = 0;
		for (int eleman : birlikKume) {
			birlikDizi[index++] = eleman;
		}

		return birlikDizi;
	}

	public static int[] kesisimiBul(int[] dizi1, int[] dizi2) {
		HashSet<Integer> kesisimKume = new HashSet<>();
		for (int eleman : dizi1) {
			if (Arrays.stream(dizi2).anyMatch(x -> x == eleman)) {
				kesisimKume.add(eleman);
			}
		}

		int[] kesisimDizi = new int[kesisimKume.size()];
		int index = 0;
		for (int eleman : kesisimKume) {
			kesisimDizi[index++] = eleman;
		}

		return kesisimDizi;
	}
}
