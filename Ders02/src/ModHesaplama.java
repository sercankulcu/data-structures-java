import java.util.Arrays;
import java.util.Scanner;

public class ModHesaplama {
	
	public static void main(String[] args) {
		
		// Kullanıcıdan dizi boyutunu al
		Scanner scanner = new Scanner(System.in);
		System.out.print("Dizi boyutunu girin: ");
		int boyut = scanner.nextInt();
		
		// Kullanıcıdan elemanları al ve dizi oluştur
		int[] dizi = new int[boyut];
		for (int i = 0; i < boyut; i++) {
			System.out.print((i + 1) + ". elemanı girin: ");
			dizi[i] = scanner.nextInt();
		}
		
		// Diziyi sırala
		Arrays.sort(dizi);
		
		//Mod değeri ve tekrar sayısını bulma
		int enCokTekrarEden = dizi[0];
		int enCokTekrarSayisi = 1;
		int mevcutTekrarEden = dizi[0];
		int mevcutTekrarSayisi = 1;
		for (int i = 1; i < boyut; i++) {
			if (dizi[i] == dizi[i - 1]) {
				mevcutTekrarSayisi++;
			} else {
				mevcutTekrarSayisi = 1;
				mevcutTekrarEden = dizi[i];
			}
			if (mevcutTekrarSayisi > enCokTekrarSayisi) {
				enCokTekrarSayisi = mevcutTekrarSayisi;
				enCokTekrarEden = mevcutTekrarEden;
			}
		}
		
		//Sonucu yazdır
		System.out.println("Dizi Elemanları (sıralı): " + Arrays.toString(dizi));
		System.out.println("Mod Değeri: " + enCokTekrarEden + " (Tekrar Sayısı: " +
				enCokTekrarSayisi + ")");
		scanner.close();
	}
}