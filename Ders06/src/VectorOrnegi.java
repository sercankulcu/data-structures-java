import java.util.Vector;

public class VectorOrnegi {
	public static void main(String[] args) {
		// Vector olusturuluyor (Dinamik dizi benzeri bir veri yapısı)
		Vector<String> sehirler = new Vector<>();

		// Elemanlar ekleniyor
		sehirler.add("Ankara");
		sehirler.add("İstanbul");
		sehirler.add("İzmir");
		sehirler.add("Bursa");

		// Vector içeriğini ekrana yazdırıyoruz
		System.out.println("Sehirler: " + sehirler);

		// Vector eleman sayısını alıyoruz
		int elemanSayisi = sehirler.size();
		System.out.println("Sehir Sayisi: " + elemanSayisi);

		// Belirli bir indeksteki elemana erişim sağlıyoruz
		String ikinciSehir = sehirler.get(1);
		System.out.println("Ikinci Sehir: " + ikinciSehir);

		// Belirli bir elemanı değiştirme işlemi
		sehirler.set(2, "Antalya");
		System.out.println("Degistirilmis Sehirler: " + sehirler);

		// Belirli bir elemanı kaldırma işlemi
		sehirler.remove(0);
		System.out.println("Ankara Kaldirildi: " + sehirler);

		// Bir elemanın Vector içinde varlığını kontrol etme
		boolean istanbulVarMi = sehirler.contains("İstanbul");
		System.out.println("İstanbul Var Mi? " + istanbulVarMi);

		// Vector boş mu? Kontrol ediyoruz
		boolean bosMu = sehirler.isEmpty();
		System.out.println("Liste Bos Mu? " + bosMu);

		// Vector'e yeni elemanlar ekliyoruz
		sehirler.add("Adana");
		sehirler.add("Konya");
		System.out.println("Yeni Eklenen Sehirler: " + sehirler);

		// Vector'un boyutunu öğreniyoruz
		elemanSayisi = sehirler.size();
		System.out.println("Vector Boyutu: " + elemanSayisi);

		// Vector'teki tüm elemanları yazdırmak için döngü kullanıyoruz
		System.out.print("Sehirler Listesi: ");
		for (String sehir : sehirler) {
			System.out.print(sehir + " ");
		}
		System.out.println();

		// Vector'u tamamen temizliyoruz
		sehirler.clear();
		System.out.println("Vector Temizlendi: " + sehirler);

		// Vector boş mu? Kontrol ediyoruz
		bosMu = sehirler.isEmpty();
		System.out.println("Son Durum: Vector Bos Mu? " + bosMu);
	}
}
