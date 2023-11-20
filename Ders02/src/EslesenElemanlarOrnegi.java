import java.util.Arrays;

public class EslesenElemanlarOrnegi {

	public static void main(String[] args) {

		int[] dizi1 = {1, 2, 3, 9, 5};
		int[] dizi2 = {3, 4, 5, 6, 7};

		System.out.print("Eşleşen elemanlar: ");
		eslesenleriBulVeYazdir(dizi1, dizi2);
		
		eslesenleriBulVeYazdir2(dizi1, dizi2);
	}

	public static void eslesenleriBulVeYazdir(int[] dizi1, int[] dizi2) {
		for (int eleman1 : dizi1) {
			for (int eleman2 : dizi2) {
				if (eleman1 == eleman2) {
					System.out.print(eleman1 + " ");
					break;
				}
			}
		}
	}

	public static void eslesenleriBulVeYazdir2(int[] dizi1, int[] dizi2) {
		Arrays.sort(dizi1);
		Arrays.sort(dizi2);

		System.out.print("Eşleşen Elemanlar: ");

		int i = 0;
		int j = 0;

		while (i < dizi1.length && j < dizi2.length) {
			if (dizi1[i] == dizi2[j]) {
				System.out.print(dizi1[i] + " ");
				i++;
				j++;
			} else if (dizi1[i] < dizi2[j]) {
				i++;
			} else {
				j++;
			}
		}

		System.out.println(); // Satırı bir alt satıra geçmek için
	}
}
