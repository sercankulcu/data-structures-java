import java.util.Stack; 

public class DizgiTersCevirme {

    // Verilen bir dizgiyi tersine cevirir
	public static String tersCevir(String girdi) {
		Stack<Character> yigin = new Stack<>();

		// Her karakteri yigina ekle
		for (int i = 0; i < girdi.length(); i++) {
			yigin.push(girdi.charAt(i));
		}

		// Yigindan karakterleri al ve tersini olustur
		StringBuilder tersi = new StringBuilder();
		while (!yigin.isEmpty()) {
			tersi.append(yigin.pop());
		}

		return tersi.toString(); // Ters cevrilmis dizgiyi dondur
	}

	public static void main(String[] args) {
		String orijinal = "Merhaba Dunya"; // Orijinal dizgi
		String ters = tersCevir(orijinal); // Dizgiyi tersine cevir

		// Sonuclari ekrana yazdir
		System.out.println("Orijinal Dizi: " + orijinal);
		System.out.println("Ters Cevirilmis Dizi: " + ters);
	}
}
