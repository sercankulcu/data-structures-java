public class MatrisTranspozu { 
	
	public static void main(String[] args) {
		
		// 3x4 boyutunda bir matris tanimlanip degerler atanir
		int[][] matris = {
				{1, 2, 3, 5},
				{4, 5, 6, 7},
				{7, 8, 9, 0}
		};

		// Orjinal matrisi ekrana yazdiriyoruz
		System.out.println("Orjinal Matris:");
		matrisiYazdir(matris);

		// Matrisi transpozunu aliyoruz
		int[][] transpozeMatris = matrisTranspozunuAl(matris);

		// Transpoze matrisi ekrana yazdiriyoruz
		System.out.println("\nTranspoze Matris:");
		matrisiYazdir(transpozeMatris);
	}

	// Matrisin transpozunu alir. Transpoz, satir ve sutunlarin yer degistigi yeni bir matristir.
	public static int[][] matrisTranspozunuAl(int[][] matris) {
		// Matrisi satir sayisini buluyoruz
		int satirSayisi = matris.length;
		// Matrisi sutun sayisini buluyoruz
		int sutunSayisi = matris[0].length;

		// Transpoze matrisini olusturuyoruz, boyutlari ters olacak
		int[][] transpozeMatris = new int[sutunSayisi][satirSayisi];

		// Orijinal matrisin her bir elemanini gezerek transpozeyi aliyoruz
		for (int i = 0; i < satirSayisi; i++) {
			for (int j = 0; j < sutunSayisi; j++) {
				// Transpoz matrisin [j][i] elemanina orijinal matrisin [i][j] degerini atiyoruz
				transpozeMatris[j][i] = matris[i][j];
			}
		}

		// Transpoze matrisi geri döndürüyoruz
		return transpozeMatris;
	}

	// Verilen matrisi ekrana yazdiran fonksiyon
	public static void matrisiYazdir(int[][] matris) {
		// Matrisin her bir satirini geziyoruz
		for (int i = 0; i < matris.length; i++) {
			// Satirdaki her bir sutunu geziyoruz
			for (int j = 0; j < matris[0].length; j++) {
				// Elemanlari ekrana yazdiriyoruz
				System.out.print(matris[i][j] + " ");
			}
			// Satirlar arasi yeni satir karakteri ekliyoruz
			System.out.println();
		}
	}
}
