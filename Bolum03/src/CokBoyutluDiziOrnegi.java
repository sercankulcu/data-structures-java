// Cok boyutlu diziler: 3x3 matris, uc boyutlu dizi ve duzensiz dizi (Pascal ucgeni).
public class CokBoyutluDiziOrnegi {

	public static void main(String[] args) {
		int[][] matris = new int[3][3];
		// ilk deger atama: 1..9
		for (int i = 0; i < 3; i++)          // satir
			for (int j = 0; j < 3; j++)      // sutun
				matris[i][j] = i * 3 + j + 1;

		System.out.println("Matrisin Icerigi:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(matris[i][j] + " ");
			System.out.println();            // yeni satir
		}

		ucBoyutluOrnek();
		pascalUcgeni(4);
	}

	// 3 x 3 x 3 = 27 oge; her boyut bir ic ice dongu
	static void ucBoyutluOrnek() {
		int[][][] ucBoyutluDizi = new int[3][3][3];
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				for (int z = 0; z < 3; z++)
					ucBoyutluDizi[x][y][z] = x * 9 + y * 3 + z + 1;

		System.out.println("\nUc Boyutlu Dizi:");
		for (int x = 0; x < 3; x++) {
			System.out.println("x = " + x);
			for (int y = 0; y < 3; y++) {
				for (int z = 0; z < 3; z++)
					System.out.print(ucBoyutluDizi[x][y][z] + " ");
				System.out.println();        // y satiri bitti
			}
		}
	}

	// Duzensiz dizi: her satir farkli uzunlukta ayri bir int[]
	static void pascalUcgeni(int n) {
		int[][] p = new int[n][];            // satirlar henuz yok (null)
		for (int i = 0; i < n; i++) {
			p[i] = new int[i + 1];           // i + 1 uzunluk
			p[i][0] = p[i][i] = 1;
			for (int j = 1; j < i; j++)
				p[i][j] = p[i - 1][j - 1] + p[i - 1][j];
		}

		System.out.println("\nPascal Ucgeni (duzensiz dizi):");
		for (int[] satir : p) {
			for (int x : satir)
				System.out.print(x + " ");
			System.out.println("(length = " + satir.length + ")");
		}
	}
}

/* Beklenen cikti:
Matrisin Icerigi:
1 2 3
4 5 6
7 8 9

Uc Boyutlu Dizi:
x = 0
1 2 3
4 5 6
7 8 9
x = 1
10 11 12
13 14 15
16 17 18
x = 2
19 20 21
22 23 24
25 26 27

Pascal Ucgeni (duzensiz dizi):
1 (length = 1)
1 1 (length = 2)
1 2 1 (length = 3)
1 3 3 1 (length = 4)
*/
