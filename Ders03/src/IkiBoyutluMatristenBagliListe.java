
public class IkiBoyutluMatristenBagliListe {

	// 2D matristen oluşturulan bağlı listenin başlangıç işaretçisini döndürür
	MatrisDugum<Integer> olustur(int matris[][], int i, int j)
	{
		// i veya j sınırları aşıldıysa dön
		if (i > matris.length - 1 || j > matris[0].length - 1)
			return null;
		// Geçerli i ve j için yeni bir düğüm oluştur ve
		// alt ve sağ işaretçilerini sırasıyla ayarla
		MatrisDugum<Integer> temp = new MatrisDugum<Integer>(matris[i][j]);
		temp.sag = olustur(matris, i, j + 1);
		temp.asagi = olustur(matris, i + 1, j);
		return temp;
	}
	
	// Bağlı liste verilerini görüntüleme
	void goruntule(MatrisDugum<Integer> baslangic)
	{
		// Sağa doğru hareket etmek için işaretçi
		MatrisDugum<Integer> sag;
		// Aşağı doğru hareket etmek için işaretçi
		MatrisDugum<Integer> asagi = baslangic;

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
		IkiBoyutluMatristenBagliListe matristenBagliListe = new IkiBoyutluMatristenBagliListe();
		// İki boyutlu matris
		int matris[][] = { 	{1, 2, 3},
												{4, 5, 6},
												{7, 8, 9} };
		MatrisDugum<Integer> baslangic = matristenBagliListe.olustur(matris, 0, 0);
		matristenBagliListe.goruntule(baslangic);
	}
}
