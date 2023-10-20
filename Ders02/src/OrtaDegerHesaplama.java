import java.util.Arrays;
import java.util.Scanner;
public class OrtaDegerHesaplama {
	
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
		scanner.close();
		
		// Diziyi sırala
		Arrays.sort(dizi);
		
		//Ortanca değeri hesapla
		double ortanca;
		if (boyut % 2 == 0) { // Çift boyutlu dizi için ortanca hesabı
			int orta1 = boyut / 2 - 1;
			int orta2 = boyut / 2;
			ortanca = (dizi[orta1] + dizi[orta2]) / 2;
		} else { // Tek boyutlu dizi için ortanca hesabı
			int orta = boyut / 2;
			ortanca = dizi[orta];
		} 
		
		// Sonucu yazdır
		System.out.println("Dizi Elemanları (sıralı): ");
		for (int i = 0; i < boyut; i++) {
			System.out.print(dizi[i] + " ");
		}
		System.out.println("\nOrtanca Değer: " + ortanca);
	}
}