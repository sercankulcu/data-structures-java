// Tek boyutlu dizi: tanimla, dongu ile doldur, yazdir.
public class DiziOrnegi {

	public static void main(String[] args) {
		int[] sayilar = new int[5];                 // 5 ogeli dizi, ogeler 0 ile baslar
		for (int i = 0; i < sayilar.length; i++)    // sinir her zaman i < length
			sayilar[i] = (i + 1) * 10;              // 10, 20, 30, 40, 50

		System.out.println("Dizinin birinci ogesi: " + sayilar[0]);
		System.out.println("Dizinin ucuncu ogesi: " + sayilar[2]);

		System.out.print("Dizi Ogeleri: ");
		for (int x : sayilar)                       // for-each: indis gerekmiyorsa
			System.out.print(x + " ");
		System.out.println();

		// En buyuk oge: her oge bir kez incelenir -> O(n)
		System.out.println("En buyuk oge: " + enBuyuk(sayilar));
	}

	// Dizinin en buyuk ogesini dondurur (dizi bos olmamali)
	static int enBuyuk(int[] dizi) {
		int max = dizi[0];
		for (int x : dizi)
			if (x > max)
				max = x;
		return max;
	}
}
