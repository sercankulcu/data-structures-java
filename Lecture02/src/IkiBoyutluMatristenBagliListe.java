
public class IkiBoyutluMatristenBagliListe {

	// Bağlı liste düğümü
	static class Dugum {
		int veri;
		Dugum sag;
		Dugum asagi;
	};

	// 2D matristen oluşturulan bağlı listenin başlangıç işaretçisini döndürür
	static Dugum olustur(int matris[][], int i, int j)
	{
		// i veya j sınırları aşıldıysa dön
		if (i > matris.length - 1 || j > matris[0].length - 1)
			return null;
		// Geçerli i ve j için yeni bir düğüm oluştur ve
		// alt ve sağ işaretçilerini sırasıyla ayarla
		Dugum temp = new Dugum();
		temp.veri = matris[i][j];
		temp.sag = olustur(matris, i, j + 1);
		temp.asagi = olustur(matris, i + 1, j);
		return temp;
	}
	// Bağlı liste verilerini görüntülemek için yardımcı işlev
	static void goruntule(Dugum baslangic)
	{
		// Sağa doğru hareket etmek için işaretçi
		Dugum sag;
		// Aşağı doğru hareket etmek için işaretçi
		Dugum asagi = baslangic;

		int sayac = 0;
		// Aşağıdaki düğüm NULL olana kadar dön
		while (asagi != null) {
			sayac = 0;
			sag = asagi;
			// Sağa doğru hareket ederken düğüm sağ NULL olana kadar dön
			while (sag != null) {
				System.out.print(sag.veri + " --> ");
				sayac++;
				sag = sag.sag;
			}
			System.out.print("null");
			System.out.println();
			asagi = asagi.asagi;

			for(int i = 0; i < sayac; i++) {
				System.out.print("|     ");
			}System.out.println();
			for(int i = 0; i < sayac; i++) {
				System.out.print("v     ");
			}System.out.println();
		}
		for(int i = 0; i < sayac; i++) {
			System.out.print("null  ");
		}
	}

	// Ana program
	public static void main(String args[])
	{
		// 2D matris
		int matris[][] = { {1, 2, 3},
				{4, 5, 6},
				{7, 8, 9} };
		Dugum baslangic = olustur(matris, 0, 0);
		goruntule(baslangic);
	}
}
