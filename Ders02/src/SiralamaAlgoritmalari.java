import java.util.Arrays;

// Temel siralama algoritmalari: hepsi yerinde (O(1) ek bellek), en kotu durum O(n^2).
public class SiralamaAlgoritmalari {

	public static void main(String[] args) {
		int[] dizi = {64, 25, 12, 22, 11};
		secimliSirala(dizi);
		System.out.println("Secimli sirala  : " + Arrays.toString(dizi));   // [11, 12, 22, 25, 64]

		int[] a = {5, 2, 4, 1, 3};
		eklemeliSirala(a);
		System.out.println("Eklemeli sirala : " + Arrays.toString(a));      // [1, 2, 3, 4, 5]

		int[] b = {5, 1, 4, 2, 8};
		kabarcikSirala(b);
		System.out.println("Kabarcik sirala : " + Arrays.toString(b));      // [1, 2, 4, 5, 8]
	}

	// Her tur, siralanmamis kismin en kucugunu o kismin basina getirir.
	// Her durumda n(n-1)/2 karsilastirma -> O(n^2)
	static void secimliSirala(int[] dizi) {
		int n = dizi.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndis = i;
			for (int j = i + 1; j < n; j++)
				if (dizi[j] < dizi[minIndis])
					minIndis = j;
			// takas: indislerle, yerinde
			int gecici = dizi[minIndis];
			dizi[minIndis] = dizi[i];
			dizi[i] = gecici;
		}
	}

	// Siradaki oge, sirali kisimda dogru yerine kaydirilarak yerlestirilir.
	// En kotu O(n^2), zaten sirali dizide O(n)
	static void eklemeliSirala(int[] a) {
		for (int k = 1; k < a.length; k++) {
			int anahtar = a[k];
			int j = k;
			while (j > 0 && a[j - 1] > anahtar) {
				a[j] = a[j - 1];   // saga kaydir
				j--;
			}
			a[j] = anahtar;        // yerles
		}
	}

	// Komsu ogeler karsilastirilir, buyuk olan saga tasinir.
	// Takas olmayan turda durur: zaten sirali dizide O(n), en kotu O(n^2)
	static void kabarcikSirala(int[] a) {
		for (int tur = 0; tur < a.length - 1; tur++) {
			boolean takasVar = false;
			for (int j = 0; j < a.length - 1 - tur; j++) {
				if (a[j] > a[j + 1]) {
					int gecici = a[j];
					a[j] = a[j + 1];
					a[j + 1] = gecici;
					takasVar = true;
				}
			}
			if (!takasVar)
				break;             // dizi sirali
		}
	}
}
