import java.util.ArrayList;

public class ArrayListOrnegi {
	public static void main(String[] args) {
		// ArrayList oluşturma
		ArrayList<String> meyveler = new ArrayList<>();

		// Elemanlar ekleme
		meyveler.add("Elma");
		meyveler.add("Armut");
		meyveler.add("Muz");
		meyveler.add("Çilek");

		// Liste içeriğini görüntüleme
		System.out.println("Meyveler: " + meyveler);

		// Eleman sayısını alma
		int elemanSayisi = meyveler.size();
		System.out.println("Meyve Sayısı: " + elemanSayisi);

		// Belirli bir indeksteki elemana erişim
		String ikinciMeyve = meyveler.get(1);
		System.out.println("İkinci Meyve: " + ikinciMeyve);

		// Belirli bir elemanı değiştirme
		meyveler.set(2, "Kivi");
		System.out.println("Değiştirilmiş Meyveler: " + meyveler);

		// Belirli bir elemanı kaldırma
		meyveler.remove(0);
		System.out.println("Elma Kaldırıldı: " + meyveler);

		// Bir elemanın varlığını kontrol etme
		boolean armutVarMi = meyveler.contains("Armut");
		System.out.println("Armut Var Mı? " + armutVarMi);

		// Liste boş mu?
		boolean bosMu = meyveler.isEmpty();
		System.out.println("Liste Boş Mu? " + bosMu);
	}
}
