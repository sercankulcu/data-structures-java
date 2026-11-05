// Matris transpozu: m x n matrisin transpozu n x m'dir. O(m*n)
public class MatrisTranspozu {

	public static void main(String[] args) {
		int[][] m = {
				{1, 2, 3},
				{4, 5, 6}
		};
		System.out.println("m (2 x 3):");
		yazdirMatris(m);

		int[][] t = transpoz(m);
		System.out.println("t (3 x 2):");
		yazdirMatris(t);
	}

	// Satirlar sutuna, sutunlar satira donusur: t[j][i] = m[i][j]
	static int[][] transpoz(int[][] m) {
		int satir = m.length;
		int sutun = m[0].length;
		int[][] t = new int[sutun][satir];
		for (int i = 0; i < satir; i++)
			for (int j = 0; j < sutun; j++)
				t[j][i] = m[i][j];
		return t;
	}

	// Her satir kendi uzunlugu kadar yazdirilir
	static void yazdirMatris(int[][] m) {
		for (int[] s : m) {
			for (int x : s)
				System.out.print(x + " ");
			System.out.println();
		}
	}
}
