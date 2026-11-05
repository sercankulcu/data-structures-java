import java.util.Arrays;
import java.util.Scanner;

// Ortalama O(n), ortanca O(n log n), mod O(n log n).
public class OrtalamaOrtancaMod {

	public static void main(String[] args) {
		// Klavyeden okumak icin: new Scanner(System.in)
		// Burada ornek girdi metinden okunur: boyut 5, ogeler 4 8 15 16 23
		Scanner sc = new Scanner("5 4 8 15 16 23");
		double[] dizi = diziOku(sc);
		sc.close();
		System.out.println();

		double toplam = 0;
		for (int i = 0; i < dizi.length; i++)
			toplam += dizi[i];
		double ortalama = toplam / dizi.length;
		System.out.println("Dizi: " + Arrays.toString(dizi));
		System.out.println("Ortalama: " + ortalama);              // 13.2

		// Varargs: istenen sayida arguman
		System.out.println("ortalama(4, 8, 15)            = " + ortalama(4, 8, 15));   // 9.0
		System.out.println("ortalama(7.5)                 = " + ortalama(7.5));        // 7.5
		System.out.println("ortalama()                    = " + ortalama());           // NaN
		System.out.println("ortalama(new double[] {1, 2}) = " + ortalama(new double[] {1, 2})); // 1.5

		System.out.println("ortanca({6, 2, 8, 4})    = " + ortanca(new double[] {6, 2, 8, 4}));    // 5.0
		System.out.println("ortanca({9, 3, 11, 7, 5}) = " + ortanca(new double[] {9, 3, 11, 7, 5})); // 7.0

		System.out.println("mod({4, 1, 4, 2, 1, 4}) = " + mod(new int[] {4, 1, 4, 2, 1, 4}));        // 4
	}

	// Once boyutu, sonra ogeleri okur; boyut calisma zamaninda belirlenir
	static double[] diziOku(Scanner scanner) {
		System.out.print("Dizi boyutunu girin: ");
		int boyut = scanner.nextInt();
		double[] dizi = new double[boyut];
		for (int i = 0; i < boyut; i++) {
			System.out.print((i + 1) + ". ogeyi girin: "); // kullaniciya 1'den say
			dizi[i] = scanner.nextDouble();
		}
		return dizi;
	}

	// Degisken sayida arguman: metodun icinde dizi siradan bir double[]
	static double ortalama(double... dizi) {
		if (dizi.length == 0)
			return Double.NaN;  // tanimsiz
		var toplam = 0.0;
		for (var oge : dizi)
			toplam += oge;
		return toplam / dizi.length;
	}

	// Sirali dizinin ortasindaki deger; cagiranin dizisi bozulmasin diye kopya siralanir
	static double ortanca(double[] dizi) {
		double[] s = dizi.clone();  // kopya
		Arrays.sort(s);             // O(n log n)
		int boyut = s.length;
		double ortanca;
		if (boyut % 2 == 0) {       // cift: ortadaki iki ogenin ortalamasi
			int o1 = boyut / 2 - 1, o2 = boyut / 2;
			ortanca = (s[o1] + s[o2]) / 2;
		} else {                    // tek: tam ortadaki oge
			ortanca = s[boyut / 2];
		}
		return ortanca;
	}

	// En sik tekrar eden deger (dizi bos olmamali); esitlikte en kucuk deger kalir
	static int mod(int[] dizi) {
		int[] s = dizi.clone();
		Arrays.sort(s);             // esit degerler yan yana gelir
		int enCok = s[0], enCokSayi = 1;
		int mevcutSayi = 1;
		for (int i = 1; i < s.length; i++) {
			if (s[i] == s[i - 1]) mevcutSayi++;
			else mevcutSayi = 1;    // yeni deger
			if (mevcutSayi > enCokSayi) {
				enCokSayi = mevcutSayi;
				enCok = s[i];
			}
		}
		return enCok;
	}
}
