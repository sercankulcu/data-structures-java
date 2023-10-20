public class EslesenElemanlarOrnegi {
	
	public static void main(String[] args) {
		
		int[] dizi1 = {1, 2, 3, 9, 5};
		int[] dizi2 = {3, 4, 5, 6, 7};

		System.out.print("Eşleşen elemanlar: ");
		eslesenleriBulVeYazdir(dizi1, dizi2);
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
}
