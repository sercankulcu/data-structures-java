import java.util.LinkedList;

public class LinkedListOrnegi {
	public static void main(String[] args) {
		// LinkedList oluşturma
		LinkedList<String> sehirler = new LinkedList<>();

		// Elemanlar ekleme
		sehirler.add("Ankara");
		sehirler.add("İstanbul");
		sehirler.add("İzmir");
		sehirler.add("Bursa");

		// Liste içeriğini görüntüleme
		System.out.println("Şehirler: " + sehirler);

		// Eleman sayısını alma
		int elemanSayisi = sehirler.size();
		System.out.println("Şehir Sayısı: " + elemanSayisi);

		// Belirli bir indeksteki elemana erişim
		String ikinciSehir = sehirler.get(1);
		System.out.println("İkinci Şehir: " + ikinciSehir);

		// Belirli bir elemanı değiştirme
		sehirler.set(2, "Antalya");
		System.out.println("Değiştirilmiş Şehirler: " + sehirler);

		// Belirli bir elemanı kaldırma
		sehirler.remove(0);
		System.out.println("Ankara Kaldırıldı: " + sehirler);

		// Bir elemanın varlığını kontrol etme
		boolean istanbulVarMi = sehirler.contains("İstanbul");
		System.out.println("İstanbul Var Mı? " + istanbulVarMi);

		// Liste boş mu?
		boolean bosMu = sehirler.isEmpty();
		System.out.println("Liste Boş Mu? " + bosMu);
	}
}
