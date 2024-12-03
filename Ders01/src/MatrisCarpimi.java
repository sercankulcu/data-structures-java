public class MatrisCarpimi { 

	public static void main(String[] args) {

		// Matris A tanimlanir
		int[][] matrisA = {
				{1, 2, 3},
				{4, 5, 6}
		};

		// Matris B tanimlanir
		int[][] matrisB = {
				{7, 8},
				{9, 10},
				{11, 12}
		};

		// Matris A ve Matris B'nin carpimi hesaplanir
		int[][] carpimSonucu = matrisCarpimi(matrisA, matrisB);

		// Matris A ekrana yazdirilir
		System.out.println("Matris A:");
		matrisiYazdir(matrisA);

		// Matris B ekrana yazdirilir
		System.out.println("Matris B:");
		matrisiYazdir(matrisB);

		// Carpim sonucu ekrana yazdirilir
		System.out.println("Carpim Sonucu:");
		matrisiYazdir(carpimSonucu);
	}

	/**
	 * Iki matrisin carpimini hesaplayan metod
	 * @param matrisA Birinci matris
	 * @param matrisB Ikinci matris
	 * @return Carpim sonucu matrisi
	 */
	public static int[][] matrisCarpimi(int[][] matrisA, int[][] matrisB) {
		int aSatir = matrisA.length; // Matris A'nin satir sayisi
		int aSutun = matrisA[0].length; // Matris A'nin sutun sayisi
		int bSutun = matrisB[0].length; // Matris B'nin sutun sayisi

		// Carpim sonucunu tutacak bos matris olusturulur
		int[][] carpimSonucu = new int[aSatir][bSutun]; 

		// Her bir eleman icin carpim islemi yapilir
		for (int i = 0; i < aSatir; i++) {
			for (int j = 0; j < bSutun; j++) {
				int toplam = 0; // Toplam degeri baslatilir
				for (int k = 0; k < aSutun; k++) {
					toplam += matrisA[i][k] * matrisB[k][j]; // Ilgili elemanlarin carpimi toplama eklenir
				}
				carpimSonucu[i][j] = toplam; // Carpim sonucu matrise kaydedilir
			}
		}

		// Carpim sonucu matrisi geri dondurulur
		return carpimSonucu;
	}

	/**
	 * Matrisi ekrana yazdiran metod
	 * @param matris Yazdirilacak matris
	 */
	public static void matrisiYazdir(int[][] matris) {
		// Matris elemanlari satir satir ekrana yazdirilir
		for (int i = 0; i < matris.length; i++) {
			for (int j = 0; j < matris[0].length; j++) {
				System.out.print(matris[i][j] + " ");
			}
			System.out.println(); // Satir sonu icin yeni satir
		}
	}
}
