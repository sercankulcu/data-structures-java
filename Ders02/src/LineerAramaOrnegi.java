import java.util.Scanner;

public class LineerAramaOrnegi {
	
	public static void main(String[] args) {
		
		// Kullanici dan arama yapilacak sayiyi almak icin Scanner sinifini kullaniyoruz
		Scanner scanner = new Scanner(System.in);

		// Diziye ornek degerler atiyoruz
		int[] dizi = {12, 45, 78, 32, 56, 89, 65, 43, 27, 90};

		diziYazdir(dizi);
		// Kullanici dan aranacak elemani istiyoruz
		System.out.print("\nAranacak elemani girin: ");
		int arananEleman = scanner.nextInt();

		// Lineer arama fonksiyonunu cagriyoruz
		int indeks = lineerArama(dizi, arananEleman);

		// Arama sonucuna gore kullaniciya geri bildirimde bulunuyoruz
		if (indeks != -1) {
			System.out.println("Aranan eleman " + arananEleman + " dizinin " + indeks + ". indeksinde bulundu.");
		} else {
			System.out.println("Aranan eleman " + arananEleman + " dizide bulunamadi.");
		}

		// Diziyi sirali hale getiriyoruz (Bubble Sort kullanarak)
		bubbleSort(dizi);
		
		selectionSort(dizi);

		// Siralanmis diziyi yazdiriyoruz
		System.out.print("Sirali Dizi: ");
		diziYazdir(dizi);
		
		// Kullanici dan alinan veriyi islerken Scanner'i kapatiyoruz
		scanner.close();
	}

	// Bu fonksiyon, diziyi bastan sona kadar tarayarak aranan elemani bulur
	// Eger eleman bulunursa indeksini dondurur, bulunmazsa -1 dondurur
	public static int lineerArama(int[] dizi, int arananEleman) {
		// Dizinin her bir elemanini kontrol ediyoruz
		for (int i = 0; i < dizi.length; i++) {
			// Eger dizinin i'inci elemani aranan eleman ile esitse
			if (dizi[i] == arananEleman) {
				return i; // Eleman bulundu, indeksi dondur
			}
		}
		// Eger eleman dizide bulunmazsa, -1 dondur
		return -1; 
	}

	// Bubble Sort kullanarak diziyi kucukten buyuge siralama fonksiyonu
	public static void bubbleSort(int[] dizi) {
		// Bubble Sort algoritmasi
		for (int i = 0; i < dizi.length - 1; i++) {
			for (int j = 0; j < dizi.length - 1 - i; j++) {
				// Eger dizi[j] daha buyukse, iki elemani yer degistir
				if (dizi[j] > dizi[j + 1]) {
					// Elemanlari takas yaparak yer degistiriyoruz
					int temp = dizi[j];
					dizi[j] = dizi[j + 1];
					dizi[j + 1] = temp;
				}
			}
		}
	}
	
	public static void selectionSort(int[] dizi) {
	    // Diziyi siralamak icin Selection Sort algoritmasi uygulaniyor
	    for (int i = 0; i < dizi.length - 1; i++) {
	        // Minumum degerin bulundugu indeks baslangic olarak i alinir
	        int minindex = i;

	        // i'den sonraki elemanlar ile karsilastirma yapilir
	        for (int j = i + 1; j < dizi.length; j++) {
	            // Eger j'nci eleman, mevcut minimumdan kucukse
	            if (dizi[j] < dizi[minindex]) {
	                // minindex'i guncelleyerek en kucuk degerin indeksini tutariz
	                minindex = j;
	            }
	        }

	        // Bulunan en kucuk degerin yerine, mevcut i'nci eleman yerlestirilir
	        int gecici = dizi[i];
	        dizi[i] = dizi[minindex];
	        dizi[minindex] = gecici;
	    }
	}

	// Diziyi ekrana yazdiran fonksiyon
	public static void diziYazdir(int[] dizi) {
		for (int eleman : dizi) {
			System.out.print(eleman + " ");
		}
		System.out.println(); // Satir sonu ekliyoruz
	}
}
