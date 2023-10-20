import java.util.Scanner;

public class OrtalamaHesaplama {
	
	public static void main(String[] args) {
		
		// Kullanıcıdan dizi boyutunu al
		Scanner scanner = new Scanner(System.in);
		System.out.print("Dizi boyutunu girin: ");
		int boyut = scanner.nextInt();
		
		// Kullanıcıdan elemanları al ve dizi oluştur
		double[] dizi = new double[boyut];
		for (int i = 0; i < boyut; i++) {
			System.out.print((i + 1) + ". elemanı girin: ");
			dizi[i] = scanner.nextDouble();
		}
		
		//Dizi elemanlarını topla
		double toplam = 0;
		for (int i = 0; i < boyut; i++) {
			toplam += dizi[i];
		}
		
		//Ortalama hesapla
		double ortalama = toplam / boyut;
		
		//Sonucu yazdır
		System.out.println("Dizi Elemanları: ");
		for (int i = 0; i < boyut; i++) {
			System.out.print(dizi[i] + " ");
		}
		System.out.println("\nOrtalama: " + ortalama);
		scanner.close();
	}
}