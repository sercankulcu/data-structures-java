import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysAsListOrnegi {
	public static void main(String[] args) {
		// Diziyi List'e donusturme (Array'i List'e ceviriyoruz)
		String[] sehirDizisi = {"Ankara", "İstanbul", "İzmir", "Bursa"};
		List<String> sehirListesi = Arrays.asList(sehirDizisi);

		// Liste icerigini goruntuleme
		System.out.println("Sehirler: " + sehirListesi);

		// Liste elemanlarina erisim (ilk sehire erisim)
		String birinciSehir = sehirListesi.get(0);
		System.out.println("Birinci Sehir: " + birinciSehir);

		// Liste icerigini degistirme (bu islem diziyi de degistirir)
		sehirListesi.set(2, "Antalya"); // Izmir'i Antalya ile degistiriyoruz
		System.out.println("Degistirilmis Sehirler: " + sehirListesi);

		// Liste elemanlarini diziyi etkiler
		System.out.println("Dizi Icerigi: " + Arrays.toString(sehirDizisi));

		// Yeni bir sehir eklemeye calisalim, ancak bu hata verecektir
		// sehirListesi.add("Konya"); // UnsupportedOperationException hatasi

		// List'e eleman eklenemez, cunku Arrays.asList() sabit uzunlukta bir liste olusturur
		// Bu durumun farkinda olarak, ArrayList'e dönüştürme işlemi yapılabilir:
		List<String> sehirListesi2 = new ArrayList<>(Arrays.asList(sehirDizisi));
		sehirListesi2.add("Konya");  // Şimdi eleman eklenebilir
		System.out.println("Yeni Sehirler: " + sehirListesi2);
	}
}
