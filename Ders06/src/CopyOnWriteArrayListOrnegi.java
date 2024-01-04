import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListOrnegi {
	public static void main(String[] args) {
		// CopyOnWriteArrayList oluşturma
		CopyOnWriteArrayList<String> liste = new CopyOnWriteArrayList<>();

		// Elemanlar ekleme
		liste.add("Elma");
		liste.add("Armut");
		liste.add("Muz");

		// Liste içeriğini görüntüleme
		System.out.println("Meyveler: " + liste);

		// Iterator kullanarak elemanlara erişme
		System.out.print("Elemanlara Erişim: ");
		for (String meyve : liste) {
			System.out.print(meyve + " ");
		}
		System.out.println();

		// Eleman ekleme (bu işlem kopya bir liste oluşturur)
		liste.add("Çilek");

		// Liste içeriğini görüntüleme
		System.out.println("Güncellenmiş Meyveler: " + liste);
	}
}
