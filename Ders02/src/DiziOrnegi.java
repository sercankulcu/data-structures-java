
public class DiziOrnegi {
	
	public static void main(String[] args) {
		// Bir diziyi tanımlama ve başlatma
		int[] sayilar = new int[5]; // 5 elemanlı bir tamsayı dizisi tanımlandı
		
		// Diziyi ilklendirme
		sayilar[0] = 10;
		sayilar[1] = 20;
		sayilar[2] = 30;
		sayilar[3] = 40;
		sayilar[4] = 50;
		
		//Dizinin elemanlarına erişme
		System.out.println("Dizinin birinci elemanı: " + sayilar[0]);
		System.out.println("Dizinin üçüncü elemanı: " + sayilar[2]);
		
		//Dizinin tüm elemanlarını döngü kullanarak yazdırma
		System.out.print("Dizi Elemanları: ");
		for (int i = 0; i < sayilar.length; i++) {
			System.out.print(sayilar[i] + " ");
		}
	}
}
