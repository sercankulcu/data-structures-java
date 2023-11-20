import java.util.Stack;

public class DiziTersCevirme {

	public static void main(String[] args) {

		int[] dizi = {1, 2, 3, 4, 5, 6, 7, 8, 9};

		System.out.println("Orijinal Dizi:");
		diziYazdir(dizi);

		int[] tersDizi = diziTersCevir(dizi);
		System.out.println("\nTers Çevrilmiş Dizi:");
		diziYazdir(tersDizi);

		tersDizi = diziTersCevir2(dizi);
		System.out.println("\nTers Çevrilmiş Dizi:");
		diziYazdir(tersDizi);

		tersDizi = diziTersCevir3(dizi);
		System.out.println("\nTers Çevrilmiş Dizi:");
		diziYazdir(tersDizi);
	}

	public static int[] diziTersCevir(int[] dizi) {

		int[] tersDizi = new int[dizi.length];
		for (int i = 0; i < dizi.length; i++) {
			tersDizi[i] = dizi[dizi.length - 1 - i];
		}

		return tersDizi;
	}

	public static int[] diziTersCevir2(int[] dizi) {

		int uzunluk = dizi.length;
		for (int i = 0; i < uzunluk / 2; i++) {
			int gecici = dizi[i];
			dizi[i] = dizi[uzunluk - 1 - i];
			dizi[uzunluk - 1 - i] = gecici;
		}

		return dizi;
	}

	public static int[] diziTersCevir3(int[] dizi) {

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < dizi.length; i++) {
			stack.push(dizi[i]);
		}
		for (int i = 0; i < dizi.length; i++) {
			dizi[i] = stack.pop();
		}

		return dizi;
	}

	public static void diziYazdir(int[] dizi) {
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}
	}
}
