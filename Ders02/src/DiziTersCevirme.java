
public class DiziTersCevirme {
	
	public static void main(String[] args) {
		
		int[] dizi = {1, 2, 3, 4, 5, 6, 7, 8, 9};

		System.out.println("Orijinal Dizi:");
		diziYazdir(dizi);

		int[] tersDizi = diziTersCevir(dizi);

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

	public static void diziYazdir(int[] dizi) {
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}
	}
}
