import java.util.Stack; 

public class DiziTersCevirme {

	public static void main(String[] args) {

		// Orijinal diziyi tanimla ve yazdir
		int[] dizi = {1, 2, 3, 4, 5, 6, 7, 8, 9};

		System.out.println("Orijinal Dizi:");
		diziYazdir(dizi); // Orijinal diziyi ekrana yazdiran fonksiyon

		// Dizi ters çevrilir ve ekrana yazdirilir
		int[] tersDizi = diziTersCevir(dizi);
		System.out.println("\nTers Çevrilmiş Dizi:");
		diziYazdir(tersDizi); // Ters çevrilmiş diziyi yazdir

		// Farkli bir yöntemle diziyi ters çevir ve yazdir
		tersDizi = diziTersCevirInPlace(dizi);
		System.out.println("\nTers Çevrilmiş Dizi (in-place):");
		diziYazdir(tersDizi); // In-place ters çevirme ile diziyi yazdir

		// Stack kullanarak diziyi ters çevir ve yazdir
		tersDizi = diziTersCevirStack(dizi);
		System.out.println("\nTers Çevrilmiş Dizi (Stack kullanarak):");
		diziYazdir(tersDizi); // Stack yöntemi ile ters çevrilmiş diziyi yazdir
	}

	// Tüm diziyi ters çeviren fonksiyon
	public static int[] diziTersCevir(int[] dizi) {
		// Yeni bir dizi oluşturuluyor
		int[] tersDizi = new int[dizi.length];
		
		// Dizinin elemanları ters sırayla yeni diziye ekleniyor
		for (int i = 0; i < dizi.length; i++) {
			tersDizi[i] = dizi[dizi.length - 1 - i];
		}

		return tersDizi; // Ters çevrilmiş diziyi döndür
	}

	// Diziyi in-place (yerinde) ters çeviren fonksiyon
	public static int[] diziTersCevirInPlace(int[] dizi) {
		int uzunluk = dizi.length;

		// Dizinin ilk yarısını ve ikinci yarısını yer değiştirerek ters çevir
		for (int i = 0; i < uzunluk / 2; i++) {
			int gecici = dizi[i];
			dizi[i] = dizi[uzunluk - 1 - i];
			dizi[uzunluk - 1 - i] = gecici;
		}

		return dizi; // Ters çevrilmiş diziyi döndür
	}

	// Stack kullanarak diziyi ters çeviren fonksiyon
	public static int[] diziTersCevirStack(int[] dizi) {
		// Stack veri yapisini kullanarak diziyi ters çeviriyoruz
		Stack<Integer> stack = new Stack<>();
		
		// Dizinin tüm elemanlarini stack'e ekle
		for (int i = 0; i < dizi.length; i++) {
			stack.push(dizi[i]);
		}
		
		// Stack'ten elemanlari alarak diziyi ters sırayla doldur
		for (int i = 0; i < dizi.length; i++) {
			dizi[i] = stack.pop();
		}

		return dizi; // Stack ile ters çevrilmiş diziyi döndür
	}

	// Diziyi ekrana yazdıran fonksiyon
	public static void diziYazdir(int[] dizi) {
		// Dizinin her elemanini ekrana yazdir
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}
		System.out.println(); // Satır atlamak için
	}
}
