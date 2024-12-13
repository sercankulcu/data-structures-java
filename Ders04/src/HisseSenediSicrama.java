import java.util.Arrays; 
import java.util.Stack;

/*
 * "Stock Span Problem" veya diger bir ifadeyle "Hisse Senedi Sicrama Problemi," 
 * finansal analizde kullanilan bir problemdir. 
 * 
 * Problem, belirli bir sure boyunca hisse senedi fiyatlarini inceleyerek her gunun 
 * fiyatinin kac ardisik gun boyunca daha yuksek veya es oldugunu bulmayi hedefler. 
 * 
 * Problem aciklamasi:
 * Verilen bir dizi hisse senedi fiyatindan, her bir gun icin ardisik fiyatin 
 * kac gun boyunca daha yuksek/es oldugu hesaplanir. 
 * 
 * Ornek: 
 * Dizi: [100, 80, 60, 70, 60, 75, 85]
 * Sicrama degerleri: [1, 1, 1, 2, 1, 4, 6]
 * 
 * Aciklamasi:
 * - Gun 1: Ilk gun oldugu icin sicrama = 1.
 * - Gun 2: 80 > 100 degil, sicrama = 1.
 * - Gun 3: 60 > 80 degil, sicrama = 1.
 * - Gun 4: 70 > 60, sicrama = 2.
 * - Gun 5: 60 > 70 degil, sicrama = 1.
 * - Gun 6: 75 > 60, 70, 60; sicrama = 4.
 * - Gun 7: 85 > 75, 60, 70, 60; sicrama = 6.
 * 
 * Bu problem, gecmis performansi analiz etmek ve karar alma sureclerinde 
 * fayda saglamak icin kullanilir. 
 */

public class HisseSenediSicrama {

    // Sicrama hesaplama fonksiyonu
	public static int[] hesaplaSicrama(int[] fiyatlar) {
		int[] sicrama = new int[fiyatlar.length]; // Sicrama degerlerini tutar
		Stack<Integer> yigin = new Stack<>();    // Gecici hesaplama icin bir yigin kullanilir

		// Ilk gun, sicrama her zaman 1'dir
		yigin.push(0); 
		System.out.println("Yigina ekle " + 0);
		sicrama[0] = 1;

		// Tum fiyatlari tek tek incele
		for (int i = 1; i < fiyatlar.length; i++) {
			System.out.println("Adim " + i);
			
			// Yigin bos degilse ve mevcut fiyat, yigindaki fiyatlardan yuksekse yigindan cikar
			while (!yigin.isEmpty() && fiyatlar[i] >= fiyatlar[yigin.peek()]) {
				System.out.println("Yigindan al " + yigin.peek());
				yigin.pop();
			}

			// Sicrama degerini hesapla
			sicrama[i] = yigin.isEmpty() ? (i + 1) : (i - yigin.peek());
			System.out.println("Yigina ekle " + i + ", sicrama[" + i + "] " + sicrama[i]);

			// Mevcut indeksi yigina ekle
			yigin.push(i);
		}

		return sicrama; // Sicrama degerlerini dondur
	}

	public static void main(String[] args) {
		int[] fiyatlar = {100, 80, 60, 70, 60, 75, 85}; // Hisse senedi fiyatlari
		int[] sicrama = hesaplaSicrama(fiyatlar);       // Sicrama hesaplama

		// Sonuclari ekrana yazdir
		System.out.println("Hisse Senedi Fiyatlari: " + Arrays.toString(fiyatlar));
		System.out.println("Sicrama Degerleri: " + Arrays.toString(sicrama));
	}
}
