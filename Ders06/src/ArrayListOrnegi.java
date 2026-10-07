import java.util.ArrayList;

// ArrayList: buyuyebilen dizi. Islemler ve maliyetleri.
public class ArrayListOrnegi {
	public static void main(String[] args) {
		ArrayList<String> liste = new ArrayList<>();   // ilk eklemede 10 kapasiteli dizi
		liste.add("Ali");          // sona ekleme: amortize O(1)
		System.out.println(liste);           // [Ali]
		liste.add("Veli");
		System.out.println(liste);           // [Ali, Veli]
		liste.add(0, "Can");       // basa ekleme: tum ogeler saga kayar, O(n)
		System.out.println(liste);           // [Can, Ali, Veli]
		String s = liste.get(1);   // indisle dogrudan erisim: O(1)
		System.out.println("get(1) = " + s); // Ali
		liste.remove(0);           // bastan silme: ogeler sola kayar, O(n)
		System.out.println(liste);           // [Ali, Veli]
		System.out.println("size() = " + liste.size());   // 2 (kapasite degil)

		// Diger temel metotlar
		String eski = liste.set(1, "Ayse");        // O(1), eski ogeyi dondurur
		System.out.println("set(1): " + eski + " -> " + liste);         // Veli -> [Ali, Ayse]
		System.out.println("contains(Ali): " + liste.contains("Ali"));  // O(n), true
		System.out.println("indexOf(Veli): " + liste.indexOf("Veli"));  // yok: -1

		// Silmede kapasite kendiliginden kuculmez; trimToSize ile size'a indirilir
		liste.trimToSize();
		liste.clear();
		System.out.println("clear sonrasi bos mu? " + liste.isEmpty());   // true
	}
}
