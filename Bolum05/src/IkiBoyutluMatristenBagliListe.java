// Matristen bagli yapi: her dugum sagdaki ve asagidaki dugumu gosterir.
public class IkiBoyutluMatristenBagliListe {

	// Dugum sinifi - iki baglanti: sag ve asagi
	static class Dugum {
		int veri;
		Dugum sag, asagi;

		Dugum(int veri) {
			this.veri = veri;
		}
	}

	// Sag alttan baslayarak her hucre icin tek dugum olusturur: O(satir * sutun).
	// d tablosu sayesinde sag ve alt yollar ayni dugumu paylasir; dogrudan
	// olustur(i, j+1) ve olustur(i+1, j) cagiran ozyinelemeli surum ayni hucreyi
	// defalarca olusturur (ustel sayida dugum).
	static Dugum olustur(int[][] m) {
		int r = m.length, c = m[0].length;
		Dugum[][] d = new Dugum[r][c];
		for (int i = r - 1; i >= 0; i--)
			for (int j = c - 1; j >= 0; j--) {
				Dugum n = new Dugum(m[i][j]);
				if (j + 1 < c) n.sag = d[i][j + 1];
				if (i + 1 < r) n.asagi = d[i + 1][j];
				d[i][j] = n;
			}
		return d[0][0];
	}

	// Satir satir goruntuleme - her dugum bir kez: O(satir * sutun)
	static void goruntule(Dugum baslangic) {
		Dugum asagi = baslangic;
		while (asagi != null) {       // Satirlar
			Dugum sag = asagi;
			while (sag != null) {     // Sutunlar
				System.out.print(sag.veri + " ");
				sag = sag.sag;
			}
			System.out.println();
			asagi = asagi.asagi;
		}
	}

	public static void main(String[] args) {
		int[][] matris = { { 1, 2, 3 },
		                   { 4, 5, 6 },
		                   { 7, 8, 9 } };
		Dugum baslangic = olustur(matris);
		goruntule(baslangic);   // 1 2 3 / 4 5 6 / 7 8 9

		// Paylasim kontrolu: (0,1)'in asagisi ile (1,0)'in sagi ayni dugum (5)
		System.out.println("Ayni dugum mu? " + (baslangic.sag.asagi == baslangic.asagi.sag)); // true
	}
}
