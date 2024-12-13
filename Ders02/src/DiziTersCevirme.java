import java.util.Stack; 

public class DiziTersCevirme {

	public static void main(String[] args) {

		// Orijinal diziyi tanimla ve yazdir
		int[] dizi = {1, 2, 3, 4, 5, 6, 7, 8, 9};

		System.out.println("Orijinal Dizi:");
		diziYazdir(dizi); // Orijinal diziyi ekrana yazdiran fonksiyon

		// Dizi ters cevrilir ve ekrana yazdirilir
		int[] tersDizi = diziTersCevir(dizi);
		System.out.println("\nTers Cevrilmis Dizi:");
		diziYazdir(tersDizi); // Ters cevrilmis diziyi yazdir

		// Farkli bir yontemle diziyi ters cevir ve yazdir
		tersDizi = diziTersCevirInPlace(dizi);
		System.out.println("\nTers Cevrilmis Dizi (in-place):");
		diziYazdir(tersDizi); // In-place ters cevirme ile diziyi yazdir

		// Stack kullanarak diziyi ters cevir ve yazdir
		tersDizi = diziTersCevirStack(dizi);
		System.out.println("\nTers Cevrilmis Dizi (Stack kullanarak):");
		diziYazdir(tersDizi); // Stack yontemi ile ters cevrilmis diziyi yazdir
	}

	// Tum diziyi ters ceviren fonksiyon
	public static int[] diziTersCevir(int[] dizi) {
		// Yeni bir dizi olusturuluyor
		int[] tersDizi = new int[dizi.length];
		
		// Dizinin elemanlari ters sirayla yeni diziye ekleniyor
		for (int i = 0; i < dizi.length; i++) {
			tersDizi[i] = dizi[dizi.length - 1 - i];
		}

		return tersDizi; // Ters cevrilmis diziyi dondur
	}

	// Diziyi in-place (yerinde) ters ceviren fonksiyon
	public static int[] diziTersCevirInPlace(int[] dizi) {
		int uzunluk = dizi.length;

		// Dizinin ilk yarisini ve ikinci yarisini yer degistirerek ters cevir
		for (int i = 0; i < uzunluk / 2; i++) {
			int gecici = dizi[i];
			dizi[i] = dizi[uzunluk - 1 - i];
			dizi[uzunluk - 1 - i] = gecici;
		}

		return dizi; // Ters cevrilmis diziyi dondur
	}

	// Stack kullanarak diziyi ters ceviren fonksiyon
	public static int[] diziTersCevirStack(int[] dizi) {
		// Stack veri yapisini kullanarak diziyi ters ceviriyoruz
		Stack<Integer> stack = new Stack<>();
		
		// Dizinin tum elemanlarini stack'e ekle
		for (int i = 0; i < dizi.length; i++) {
			stack.push(dizi[i]);
		}
		
		// Stack'ten elemanlari alarak diziyi ters sirayla doldur
		for (int i = 0; i < dizi.length; i++) {
			dizi[i] = stack.pop();
		}

		return dizi; // Stack ile ters cevrilmis diziyi dondur
	}

	// Diziyi ekrana yazdiran fonksiyon
	public static void diziYazdir(int[] dizi) {
		// Dizinin her elemanini ekrana yazdir
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}
		System.out.println(); // Satir atlamak icin
	}
}
