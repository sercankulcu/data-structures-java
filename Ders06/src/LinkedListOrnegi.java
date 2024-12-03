import java.util.LinkedList;

public class LinkedListOrnegi {
	public static void main(String[] args) {
		// LinkedList olusturuluyor
		LinkedList<String> sehirler = new LinkedList<>();

		// Listeye eleman ekleniyor
		sehirler.add("Ankara");
		sehirler.add("İstanbul");
		sehirler.add("İzmir");
		sehirler.add("Bursa");

		// Liste iceriği ekrana yazdiriliyor
		System.out.println("Sehirler: " + sehirler);

		// Liste boyutu (eleman sayısı) alınıyor
		int elemanSayisi = sehirler.size();
		System.out.println("Sehir Sayisi: " + elemanSayisi);

		// Belirli bir indeksteki elemana erisim sağlanıyor
		String ikinciSehir = sehirler.get(1);
		System.out.println("İkinci Sehir: " + ikinciSehir);

		// Belirli bir eleman degistiriliyor (İzmir -> Antalya)
		sehirler.set(2, "Antalya");
		System.out.println("Degistirilmis Sehirler: " + sehirler);

		// Belirli bir eleman (Ankara) listeden kaldiriliyor
		sehirler.remove(0); 
		System.out.println("Ankara Kaldirildi: " + sehirler);

		// Listede bir eleman var mi kontrol ediliyor (İstanbul)
		boolean istanbulVarMi = sehirler.contains("İstanbul");
		System.out.println("İstanbul Var Mi? " + istanbulVarMi);

		// Liste bos mu kontrol ediliyor
		boolean bosMu = sehirler.isEmpty();
		System.out.println("Liste Bos Mu? " + bosMu);

		// Liste basina bir eleman ekleyelim
		sehirler.addFirst("Adana");
		System.out.println("Listeye Adana Eklendi: " + sehirler);

		// Liste sonuna bir eleman ekleyelim
		sehirler.addLast("Trabzon");
		System.out.println("Listeye Trabzon Eklendi: " + sehirler);

		// Listeden son eleman kaldirilabilir
		String sonEleman = sehirler.removeLast();
		System.out.println("Son Eleman Kaldirildi: " + sonEleman);
		System.out.println("Guncellenmis Sehirler: " + sehirler);

		// Listeden basit bir eleman kaldiralim
		sehirler.remove("Bursa");
		System.out.println("Bursa Kaldirildi: " + sehirler);

		// Listeyi temizleyelim
		sehirler.clear();
		System.out.println("Liste Temizlendi: " + sehirler);
	}
}
