import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListOrnegi {
	public static void main(String[] args) {
		// CopyOnWriteArrayList olusturma (Thread-safe olan liste)
		CopyOnWriteArrayList<String> liste = new CopyOnWriteArrayList<>();

		// Elemanlar ekleme
		liste.add("Elma");
		liste.add("Armut");
		liste.add("Muz");

		// Liste icerigini goruntuleme
		System.out.println("Meyveler: " + liste);

		// Iterator kullanarak elemanlara erisim
		System.out.print("Elemanlara Erisim: ");
		for (String meyve : liste) {
			System.out.print(meyve + " ");
		}
		System.out.println();

		// Eleman ekleme (bu islem kopya bir liste olusturur)
		liste.add("Çilek");  // Bu ekleme, mevcut listeyi degistirip yeni bir liste kopyası olusturur.

		// Liste icerigini tekrar goruntuleme
		System.out.println("Guncellenmis Meyveler: " + liste);

		// Listeye yeni bir eleman ekleyelim
		liste.add("Kivi");
		System.out.println("Listeye Yeni Eleman Ekledik: " + liste);

		// Liste icindeki belirli bir elemana erisim
		String ikinciMeyve = liste.get(1);
		System.out.println("Ikinci Meyve: " + ikinciMeyve);

		// Liste icinde bir elemani kaldiralim
		liste.remove("Armut");
		System.out.println("Armut Kaldirildi: " + liste);

		// Liste boyutunu kontrol edelim
		int listeBoyutu = liste.size();
		System.out.println("Liste Boyutu: " + listeBoyutu);

		// Listeyi temizleyelim
		liste.clear();
		System.out.println("Liste Temizlendi: " + liste);

		// Liste bos mu?
		boolean listeBosMu = liste.isEmpty();
		System.out.println("Liste Bos mu? " + listeBosMu);
	}
}
