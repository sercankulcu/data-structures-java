import java.util.Stack;

public class DizgiTersCevirme {

	public static String tersCevir(String girdi) {
		Stack<Character> yigin = new Stack<>();

		// Diziyi yığına ekleyin
		for (int i = 0; i < girdi.length(); i++) {
			yigin.push(girdi.charAt(i));
		}

		// Yığından karakterleri çıkararak tersine çevirin
		StringBuilder tersi = new StringBuilder();
		while (!yigin.isEmpty()) {
			tersi.append(yigin.pop());
		}

		return tersi.toString();
	}

	public static void main(String[] args) {
		String orijinal = "Merhaba Dunya";
		String ters = tersCevir(orijinal);

		System.out.println("Orijinal Dizi: " + orijinal);
		System.out.println("Ters Çevrilmiş Dizi: " + ters);
	}
}
