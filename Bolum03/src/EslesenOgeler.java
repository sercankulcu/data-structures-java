// Iki dizide ortak ogeleri bulma: ic ice dongu, n*m karsilastirma -> O(n*m), n = m ise O(n^2).
public class EslesenOgeler {

	public static void main(String[] args) {
		int[] dizi1 = {3, 7, 5};
		int[] dizi2 = {5, 1, 3, 8};
		eslestir(dizi1, dizi2);   // 3 x 4 = 12 karsilastirma, 2 eslesme
	}

	static void eslestir(int[] dizi1, int[] dizi2) {
		for (int oge1 : dizi1)          // n kez
			for (int oge2 : dizi2)      // m kez
				if (oge1 == oge2)
					System.out.println("Eslesen oge: " + oge1);
	}
}
