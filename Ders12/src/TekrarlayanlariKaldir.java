
public class TekrarlayanlariKaldir {
	public static void main(String[] args) {
		int[] siraliDizi = {1, 2, 2, 3, 4, 4, 4, 5, 5, 6};

		int yeniUzunluk = tekrarlayanlariKaldir(siraliDizi);

		// Dizinin güncellenmiş hali
		for (int i = 0; i < yeniUzunluk; i++) {
			System.out.print(siraliDizi[i] + " ");
		}
	}

	public static int tekrarlayanlariKaldir(int[] dizi) {
		int n = dizi.length;

		if (n == 0 || n == 1) {
			return n;
		}

		int index = 0;

		for (int i = 0; i < n - 1; i++) {
			if (dizi[i] != dizi[i + 1]) {
				dizi[index++] = dizi[i];
			}
		}

		dizi[index++] = dizi[n - 1];
		return index;
	}
}
