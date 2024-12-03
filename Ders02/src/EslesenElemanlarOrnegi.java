import java.util.Arrays;

public class EslesenElemanlarOrnegi {

	public static void main(String[] args) {

		// Ilk diziyi ve ikinci diziyi tanimliyoruz
		int[] dizi1 = {1, 2, 3, 9, 5, 8, 12, 7};
		int[] dizi2 = {3, 4, 5, 6, 7};

		// Eslesen elemanlari bulup yazdiriyoruz
		System.out.print("Eslesen elemanlar: ");
		eslesenElemanlariBulVeYazdir(dizi1, dizi2);

		// Daha verimli bir yontemle eslesen elemanlari bulup yazdiriyoruz
		System.out.print("Eslesen elemanlar (siralama ile): ");
		eslesenElemanlariBulVeYazdirVerimli(dizi1, dizi2);
	}

	// Dizilerdeki eslesen elemanlari bulan ve yazdiran fonksiyon
	public static void eslesenElemanlariBulVeYazdir(int[] dizi1, int[] dizi2) {
		// Birinci dizinin her elemanini kontrol ediyoruz
		for (int eleman1 : dizi1) {
			// Ikinci dizinin her elemanini kontrol ediyoruz
			for (int eleman2 : dizi2) {
				// Eger elemanlar eslesiyorsa, yazdiriyoruz ve ic donguye devam etmiyoruz
				if (eleman1 == eleman2) {
					System.out.print(eleman1 + " ");
					break; // Ayni eleman bir kez bulundu, ic donguye devam etme
				}
			}
		}
		System.out.println(); // Satiri bir alt satira gecirmek icin
	}

	// Eslesen elemanlari bulup yazdiran daha verimli bir fonksiyon
	public static void eslesenElemanlariBulVeYazdirVerimli(int[] dizi1, int[] dizi2) {
		// Ilk ve ikinci diziyi siraliyoruz
		Arrays.sort(dizi1);
		Arrays.sort(dizi2);

		// Eslesen elemanlari yazdirmak icin bir mesaj
		System.out.print("Eslesen Elemanlar: ");

		// Dizi1 ve dizi2'yi iki farkli indeksle kontrol ediyoruz
		int i = 0;
		int j = 0;

		// Iki diziyi de baslangic tan sona kontrol etmek icin dongu
		while (i < dizi1.length && j < dizi2.length) {
			// Eger elemanlar eslesiyorsa, yazdiriyoruz ve her iki dizinin indeksini de ilerletiyoruz
			if (dizi1[i] == dizi2[j]) {
				System.out.print(dizi1[i] + " ");
				i++;
				j++;
			} 
			// Eger dizi1'in elemani kucukse, dizi1'in indeksini ilerletiyoruz
			else if (dizi1[i] < dizi2[j]) {
				i++;
			} 
			// Eger dizi2'nin elemani kucukse, dizi2'nin indeksini ilerletiyoruz
			else {
				j++;
			}
		}
		System.out.println(); // Satiri bir alt satira gecirmek icin
	}
}
