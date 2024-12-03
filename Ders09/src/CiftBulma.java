import java.util.Arrays;
import java.util.HashSet;

public class CiftBulma {

	public static void main(String[] args) {
		int[] dizi = {4, 2, 7, 11, 15, 1, 8, 5};
		int hedefToplam = 9;

		// Brute-force ile cift bulma fonksiyonunu cagirma
		System.out.println("Brute force");
		bruteForceIleCiftBul(dizi, hedefToplam);

		// Siralama ile cift bulma fonksiyonunu cagirma
		System.out.println("Siralama ile");
		siralamaIleCiftBul(dizi, hedefToplam);

		// Hashing ile cift bulma fonksiyonunu cagirma
		System.out.println("Hashing ile");
		hashingIleCiftBul(dizi, hedefToplam);
	}

	public static void bruteForceIleCiftBul(int[] dizi, int hedefToplam) {
		int n = dizi.length;

		// Ic ice donguler ile tum ciftleri kontrol et
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (dizi[i] + dizi[j] == hedefToplam) {
					System.out.println("Cift bulundu: (" + dizi[i] + ", " + dizi[j] + ")");
				}
			}
		}
	}

	public static void siralamaIleCiftBul(int[] dizi, int hedefToplam) {
		// Adim 1: Diziyi sirala
		Arrays.sort(dizi);

		// Adim 2: Iki isaretci kullan
		int sol = 0;
		int sag = dizi.length - 1;

		while (sol < sag) {
			int mevcutToplam = dizi[sol] + dizi[sag];

			if (mevcutToplam == hedefToplam) {
				System.out.println("Cift bulundu: (" + dizi[sol] + ", " + dizi[sag] + ")");
				sol++; // veya sag--
			} else if (mevcutToplam < hedefToplam) {
				sol++; // Toplami arttir
			} else {
				sag--; // Toplami azalt
			}
		}
	}

	public static void hashingIleCiftBul(int[] dizi, int hedefToplam) {
		// Adim 1: Elemanlari saklamak icin bir HashSet olustur
		HashSet<Integer> gorulen = new HashSet<>();

		// Adim 2: Diziyi iterasyon ile gez
		for (int sayi : dizi) {
			int tamamlayici = hedefToplam - sayi;

			// Tamamlayicinin set'te olup olmadigini kontrol et
			if (gorulen.contains(tamamlayici)) {
				System.out.println("Cift bulundu: (" + sayi + ", " + tamamlayici + ")");
			}

			// Mevcut elemani set'e ekle
			gorulen.add(sayi);
		}
	}
}
