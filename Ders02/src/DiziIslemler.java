import java.util.Arrays;

public class DiziIslemler {

	public static void main(String[] args) {

		// Diziyi kendimiz tanimliyoruz, burada 12 elemanli bir dizi kullandik
		int[] dizi = {12, 45, 78, 32, 56, 89, 32, 65, 43, 32, 27, 90};

		// Diziyi ekrana sirasiz olarak yazdiriyoruz
		System.out.println("Dizi Elemanlari (sirasiz): " + Arrays.toString(dizi));
		
		// Diziyi kucukten buyuge dogru siraliyoruz
		Arrays.sort(dizi);
		
		// Sirali diziyi ekrana yazdiriyoruz
		System.out.println("Dizi Elemanlari (sirali): " + Arrays.toString(dizi));

		// Mod hesaplama fonksiyonunu cagiriyoruz
		modHesapla(dizi);

		// Ortanca degerini hesaplamak icin fonksiyonu cagiriyoruz
		ortancaDegerHesapla(dizi);
		
		// Ortalama degerini hesaplamak icin fonksiyonu cagiriyoruz
		ortalamaHesapla(dizi);
	}

	// Mod hesaplama fonksiyonu
	public static void modHesapla(int[] dizi) {

		// Mod degerini ve tekrar sayisini bulma islemi
		int enCokTekrarEden = dizi[0];  // En cok tekrar eden sayi, ilk eleman ile baslatiliyor
		int enCokTekrarSayisi = 1;      // Ilk elemanin tekrar sayisi 1
		int mevcutTekrarEden = dizi[0]; // Su anda kontrol edilen en cok tekrar eden sayi
		int mevcutTekrarSayisi = 1;    // Bu sayinin tekrar sayisi

		// Diziyi gezerek mod hesaplama
		for (int i = 1; i < dizi.length; i++) {
			if (dizi[i] == dizi[i - 1]) {
				// Eger iki ardil eleman esit ise, mevcut sayinin tekrar sayisini arttiriyoruz
				mevcutTekrarSayisi++;
			} else {
				// Farkli bir eleman bulundu, mevcut sayiyi degistiriyoruz
				mevcutTekrarSayisi = 1;
				mevcutTekrarEden = dizi[i];
			}

			// En cok tekrar eden sayiyi ve tekrar sayisini guncelliyoruz
			if (mevcutTekrarSayisi > enCokTekrarSayisi) {
				enCokTekrarSayisi = mevcutTekrarSayisi;
				enCokTekrarEden = mevcutTekrarEden;
			}
		}

		// Sonucu ekrana yazdiriyoruz
		System.out.println("Mod Degeri: " + enCokTekrarEden + " (Tekrar Sayisi: " +
				enCokTekrarSayisi + ")");
	}

	// Ortanca degerini hesaplama fonksiyonu
	public static void ortancaDegerHesapla(int[] dizi) {
		// Ortanca degeri hesaplama
		double ortanca;

		// Dizinin uzunlugu cift ise, ortanca iki ortadaki elemanin ortalamasidir
		if (dizi.length % 2 == 0) { 
			int orta1 = dizi.length / 2 - 1; // Ortadaki ilk eleman
			int orta2 = dizi.length / 2;     // Ortadaki ikinci eleman
			// Ortanca degerini hesapliyoruz
			ortanca = (dizi[orta1] + dizi[orta2]) / 2.0;
		} else { // Dizinin uzunlugu tek ise, ortanca ortadaki elemandir
			int orta = dizi.length / 2;
			ortanca = dizi[orta];
		} 

		// Ortanca degeri ekrana yazdiriliyor
		System.out.println("Ortanca Deger: " + ortanca);
	}

	// Ortalama degerini hesaplama fonksiyonu
	public static void ortalamaHesapla(int[] dizi) {
		// Dizi elemanlarini topluyoruz
		double toplam = 0;
		for (int i = 0; i < dizi.length; i++) {
			toplam += dizi[i];
		}

		// Ortalamayi hesapliyoruz
		double ortalama = toplam / dizi.length;

		// Ortalamayi ekrana yazdiriyoruz
		System.out.println("Ortalama: " + ortalama);
	}
}
