import java.util.Vector;

public class VectorOrnegi {
	public static void main(String[] args) {
		// Vector olusturuluyor (Dinamik dizi benzeri bir veri yapisi)
		Vector<String> sehirler = new Vector<>();

		// Elemanlar ekleniyor
		sehirler.add("Ankara");
		sehirler.add("Istanbul");
		sehirler.add("Izmir");
		sehirler.add("Bursa");

		// Vector icerigini ekrana yazdiriyoruz
		System.out.println("Sehirler: " + sehirler);

		// Vector eleman sayisini aliyoruz
		int elemanSayisi = sehirler.size();
		System.out.println("Sehir Sayisi: " + elemanSayisi);

		// Belirli bir indeksteki elemana erisim sagliyoruz
		String ikinciSehir = sehirler.get(1);
		System.out.println("Ikinci Sehir: " + ikinciSehir);

		// Belirli bir elemani degistirme islemi
		sehirler.set(2, "Antalya");
		System.out.println("Degistirilmis Sehirler: " + sehirler);

		// Belirli bir elemani kaldirma islemi
		sehirler.remove(0);
		System.out.println("Ankara Kaldirildi: " + sehirler);

		// Bir elemanin Vector icinde varligini kontrol etme
		boolean istanbulVarMi = sehirler.contains("Istanbul");
		System.out.println("Istanbul Var Mi? " + istanbulVarMi);

		// Vector bos mu? Kontrol ediyoruz
		boolean bosMu = sehirler.isEmpty();
		System.out.println("Liste Bos Mu? " + bosMu);

		// Vector'e yeni elemanlar ekliyoruz
		sehirler.add("Adana");
		sehirler.add("Konya");
		System.out.println("Yeni Eklenen Sehirler: " + sehirler);

		// Vector'un boyutunu ogreniyoruz
		elemanSayisi = sehirler.size();
		System.out.println("Vector Boyutu: " + elemanSayisi);

		// Vector'teki tum elemanlari yazdirmak icin dongu kullaniyoruz
		System.out.print("Sehirler Listesi: ");
		for (String sehir : sehirler) {
			System.out.print(sehir + " ");
		}
		System.out.println();

		// Vector'u tamamen temizliyoruz
		sehirler.clear();
		System.out.println("Vector Temizlendi: " + sehirler);

		// Vector bos mu? Kontrol ediyoruz
		bosMu = sehirler.isEmpty();
		System.out.println("Son Durum: Vector Bos Mu? " + bosMu);
	}
}
