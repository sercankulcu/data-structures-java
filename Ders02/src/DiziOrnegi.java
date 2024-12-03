public class DiziOrnegi { 
	
	public static void main(String[] args) {
		// Bir diziyi tanimlama ve baslatma
		int[] sayilar = new int[10]; // 10 elemanli bir tamsayi dizisi tanimlandi
		
		// Diziyi ilklendirme
		sayilar[0] = 10;
		sayilar[1] = 20;
		sayilar[2] = 30;
		sayilar[3] = 40;
		sayilar[4] = 50;
		sayilar[5] = 60; // Yeni eleman
		sayilar[6] = 70; // Yeni eleman
		sayilar[7] = 80; // Yeni eleman
		sayilar[8] = 90; // Yeni eleman
		sayilar[9] = 100; // Yeni eleman
		
		// Dizinin elemanlarini ekrana yazdirma
		System.out.print("Dizi Elemanlari: ");
		diziyiYazdir(sayilar); // Diziyi yazdiran fonksiyon cagirilir
		
		// En buyuk elemani bulma
		int enBuyuk = enBuyukEleman(sayilar); // En buyuk eleman fonksiyonu cagirilir
		System.out.println("Dizinin en buyuk elemani: " + enBuyuk); // En buyuk eleman yazdirilir
	}
	
	// Diziyi ekrana yazdiran fonksiyon
	public static void diziyiYazdir(int[] dizi) {
		for (int i = 0; i < dizi.length; i++) {
			System.out.print(dizi[i] + " "); // Her bir eleman ekrana yazdirilir
		}
		System.out.println(); // Yazdirma islemi tamamlaninca alt satira gecilir
	}
	
	// Dizinin en buyuk elemanini bulan fonksiyon
	public static int enBuyukEleman(int[] dizi) {
		int enBuyuk = dizi[0]; // En buyuk eleman, dizinin ilk elemanina esit baslatilir
		for (int i = 1; i < dizi.length; i++) { // Dizinin geri kalan elemanlari kontrol edilir
			if (dizi[i] > enBuyuk) { // Eger buldugumuz eleman daha buyukse
				enBuyuk = dizi[i]; // En buyuk eleman degistirilir
			}
		}
		return enBuyuk; // En buyuk eleman dondurulur
	}
}
