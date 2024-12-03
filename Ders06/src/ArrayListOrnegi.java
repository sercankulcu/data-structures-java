import java.util.ArrayList;

public class ArrayListOrnegi {
	public static void main(String[] args) {
		// ArrayList olusturma (String turunde elemanlar tutacak)
		ArrayList<String> meyveler = new ArrayList<>();

		// Elemanlar ekleme
		meyveler.add("Elma");
		meyveler.add("Armut");
		meyveler.add("Muz");
		meyveler.add("cilek");

		// Liste icerigini goruntuleme
		System.out.println("Meyveler: " + meyveler);

		// Eleman sayisini alma
		int elemanSayisi = meyveler.size();
		System.out.println("Meyve Sayisi: " + elemanSayisi);

		// Belirli bir indeksteki elemana erisim (1. indeks, 0 tabanli oldugu icin ikinci meyve)
		String ikinciMeyve = meyveler.get(1);
		System.out.println("ikinci Meyve: " + ikinciMeyve);

		// Belirli bir elemani degistirme (Muz yerine Kivi)
		meyveler.set(2, "Kivi");
		System.out.println("Degistirilmis Meyveler: " + meyveler);

		// Belirli bir elemani kaldirma (Elma'yi kaldiriyoruz)
		meyveler.remove(0);
		System.out.println("Elma Kaldirildi: " + meyveler);

		// Bir elemanin varligini kontrol etme (Armut mevcut mu?)
		boolean armutVarMi = meyveler.contains("Armut");
		System.out.println("Armut Var Mi? " + armutVarMi);

		// Liste bos mu? Kontrol etme
		boolean bosMu = meyveler.isEmpty();
		System.out.println("Liste Bos Mu? " + bosMu);

		// Listeyi temizleyelim (tum elemanlari kaldiralim)
		meyveler.clear();
		System.out.println("Liste Temizlendi: " + meyveler);

		// Listeyi tekrar kontrol edelim (bos mu?)
		bosMu = meyveler.isEmpty();
		System.out.println("Liste Bos Mu? (Temizlendikten Sonra): " + bosMu);
	}
}
