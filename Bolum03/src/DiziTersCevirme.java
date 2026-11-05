import java.util.Arrays;

// Diziyi tersine cevirme: yerinde (O(1) ek bellek) ve yeni diziye kopyalayarak (O(n) ek bellek).
public class DiziTersCevirme {

	public static void main(String[] args) {
		int[] dizi = {3, 8, 1, 9, 4, 6};
		System.out.println("Orijinal dizi : " + Arrays.toString(dizi));

		int[] yeni = tersKopya(dizi);           // dizi degismez
		System.out.println("Ters kopya    : " + Arrays.toString(yeni));
		System.out.println("Orijinal dizi : " + Arrays.toString(dizi));

		tersineCevir(dizi);                     // dizinin kendisi degisir
		System.out.println("Yerinde ters  : " + Arrays.toString(dizi)); // [6, 4, 9, 1, 8, 3]
	}

	// Iki uctan ice dogru takas: n/2 (asagi yuvarlanmis) takas -> O(n) zaman, O(1) ek bellek
	static void tersineCevir(int[] dizi) {
		int baslangic = 0;
		int bitis = dizi.length - 1;
		while (baslangic < bitis) {
			// uctaki iki ogeyi takas et
			int gecici = dizi[baslangic];
			dizi[baslangic] = dizi[bitis];
			dizi[bitis] = gecici;
			baslangic++;   // ice dogru ilerle
			bitis--;
		}
	}

	// Sondan basa yeni diziye kopyalama: O(n) zaman, O(n) ek bellek
	static int[] tersKopya(int[] dizi) {
		int n = dizi.length;
		int[] yeni = new int[n];
		for (int i = 0; i < n; i++)
			yeni[i] = dizi[n - 1 - i];
		return yeni;
	}
}
