import java.util.Scanner;

public class DiziOrnegiIkiBoyutlu {
	
	public static void main(String[] args) {
		
		// Kullanicidan satir ve sutun sayisini aliyoruz
		Scanner scanner = new Scanner(System.in);

		// Satir sayisini kullanicidan aliyoruz
		System.out.print("Satir sayisini girin: ");
		int satirSayisi = scanner.nextInt();

		// Sutun sayisini kullanicidan aliyoruz
		System.out.print("Sutun sayisini girin: ");
		int sutunSayisi = scanner.nextInt();

		// Kullanici tarafindan girilen satir ve sutun sayisina gore 2 boyutlu bir dizi olusturuyoruz
		int[][] ikiBoyutluDizi = new int[satirSayisi][sutunSayisi];

		// Diziyi dolduruyoruz
		// Her bir elemani i * 3 + j * 5 seklinde hesapliyoruz
		for (int i = 0; i < satirSayisi; i++) {
			for (int j = 0; j < sutunSayisi; j++) {
				ikiBoyutluDizi[i][j] = i * 3 + j * 5;
			}
		}

		// iki boyutlu diziyi ekrana yazdiriyoruz
		System.out.println("\niki Boyutlu Dizi:");
		diziYazdir(ikiBoyutluDizi);  // Diziyi yazdirmak icin fonksiyonu cagiriyoruz

		// Scanner nesnesini kapatiyoruz, bu kaynaklari serbest birakir
		scanner.close();

		// iki boyutlu dizinin tum elemanlarinin toplamini hesaplayan fonksiyon
		int toplam = diziElemanToplami(ikiBoyutluDizi);
		System.out.println("\nDizi elemanlarinin toplami: " + toplam);

		// 2. Fonksiyon: iki boyutlu dizideki en buyuk sayiyi bulan fonksiyon
		int enBuyukSayi = enBuyukElemaniBul(ikiBoyutluDizi);
		System.out.println("Dizideki en buyuk sayi: " + enBuyukSayi);
	}

	// verilen iki boyutlu dizinin tum elemanlarinin toplamini hesaplar
	public static int diziElemanToplami(int[][] dizi) {
		int toplam = 0;
		// Dizinin tum elemanlari uzerinde dongu ile geciyoruz
		for (int i = 0; i < dizi.length; i++) {
			for (int j = 0; j < dizi[i].length; j++) {
				toplam += dizi[i][j]; // Her elemani toplam degiskenine ekliyoruz
			}
		}
		// Hesaplanan toplam degeri geri donduruyoruz
		return toplam;
	}

	// Bu fonksiyon, verilen iki boyutlu dizideki en buyuk sayiyi bulur
	public static int enBuyukElemaniBul(int[][] dizi) {
		int enBuyuk = dizi[0][0]; // ilk elemani baslangic olarak kabul ediyoruz
		// Dizinin tum elemanlari uzerinde dongu ile geciyoruz
		for (int i = 0; i < dizi.length; i++) {
			for (int j = 0; j < dizi[i].length; j++) {
				if (dizi[i][j] > enBuyuk) {
					enBuyuk = dizi[i][j]; // Eger daha buyuk bir eleman bulursak, enBuyuk degiskenini guncelliyoruz
				}
			}
		}
		// Bulunan en buyuk sayiyi geri donduruyoruz
		return enBuyuk;
	}

	// Bu fonksiyon, verilen iki boyutlu diziyi ekrana yazdirir
	public static void diziYazdir(int[][] dizi) {
		// Dizinin her satirini ve sutununu ekrana yazdiriyoruz
		for (int i = 0; i < dizi.length; i++) {
			for (int j = 0; j < dizi[i].length; j++) {
				// Elemanlar arasinda tab karakteri kullanarak duzenli bir cikti aliyoruz
				System.out.print(dizi[i][j] + "\t");
			}
			// Satirin sonunda yeni satira geciyoruz
			System.out.println(); 
		}
	}
}
