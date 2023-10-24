import java.util.Arrays;
import java.util.List;

public class ArraysAsListOrnegi {
	public static void main(String[] args) {
		// Diziyi List'e dönüştürme
		String[] sehirDizisi = {"Ankara", "İstanbul", "İzmir", "Bursa"};
		List<String> sehirListesi = Arrays.asList(sehirDizisi);

		// Liste içeriğini görüntüleme
		System.out.println("Şehirler: " + sehirListesi);

		// Liste elemanlarına erişme
		String birinciSehir = sehirListesi.get(0);
		System.out.println("Birinci Şehir: " + birinciSehir);

		// Liste içeriğini değiştirme (bu işlem diziyi değiştirir)
		sehirListesi.set(2, "Antalya");
		System.out.println("Değiştirilmiş Şehirler: " + sehirListesi);

		// Liste elemanlarını diziyi etkiler
		System.out.println("Dizi İçeriği: " + Arrays.toString(sehirDizisi));
	}
}
