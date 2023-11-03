
public class MatrisCarpimi {

	public static void main(String[] args) {

		int[][] matrisA = {
				{1, 2, 3},
				{4, 5, 6}
		};

		int[][] matrisB = {
				{7, 8},
				{9, 10},
				{11, 12}
		};

		int[][] carpimSonucu = matrisCarpimi(matrisA, matrisB);

		System.out.println("Matris A:");
		matrisiYazdir(matrisA);

		System.out.println("Matris B:");
		matrisiYazdir(matrisB);

		System.out.println("Çarpım Sonucu:");
		matrisiYazdir(carpimSonucu);
	}

	public static int[][] matrisCarpimi(int[][] matrisA, int[][] matrisB) {
		int aSatir = matrisA.length; // Matris A'nın satır sayısı
		int aSutun = matrisA[0].length; // Matris A'nın sütun sayısı
		int bSutun = matrisB[0].length; // Matris B'nin sütun sayısı

		int[][] carpimSonucu = new int[aSatir][bSutun]; // Çarpım sonucunu tutacak matris

		for (int i = 0; i < aSatir; i++) {
			for (int j = 0; j < bSutun; j++) {
				int toplam = 0; // Toplam değeri sıfırla
				for (int k = 0; k < aSutun; k++) {
					toplam += matrisA[i][k] * matrisB[k][j]; // Çarpım sonucunu hesapla
				}
				carpimSonucu[i][j] = toplam; // Sonucu çarpım matrisine ekle
			}
		}

		return carpimSonucu; // Çarpım sonucunu döndür
	}


	public static void matrisiYazdir(int[][] matris) {
		for (int i = 0; i < matris.length; i++) {
			for (int j = 0; j < matris[0].length; j++) {
				System.out.print(matris[i][j] + " ");
			}
			System.out.println();
		}
	}
}
