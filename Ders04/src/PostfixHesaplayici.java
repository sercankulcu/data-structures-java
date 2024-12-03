import java.util.Stack;

public class PostfixHesaplayici {
	
	public static double postfixDegerlendir(String ifade) {
		
		// Islenenleri saklamak icin stack
		Stack<Double> islenenlerYigini = new Stack<>();

		// Ifade uzerinde her karakteri isle
		for (char sembol : ifade.toCharArray()) {
			// Eger karakter bir rakam ise, onu double'a cevirip stack'e ekle
			if (Character.isDigit(sembol)) {
				islenenlerYigini.push(Double.parseDouble(String.valueOf(sembol)));
			} else if (isOperator(sembol)) {
				// Eger karakter bir isleç ise, stack'ten islenenleri cikar,
				// islemi gerceklestirip sonucu tekrar stack'e ekle
				double islenen2 = islenenlerYigini.pop();
				double islenen1 = islenenlerYigini.pop();
				double sonuc = islemiGercekle(sembol, islenen1, islenen2);
				islenenlerYigini.push(sonuc);
			}
		}

		// Sonuc stack'in en ustunde olmalidir
		return islenenlerYigini.pop();
	}

	// Karakterin bir isleç olup olmadigini kontrol eder
	private static boolean isOperator(char karakter) {
		return karakter == '+' || karakter == '-' || karakter == '*' || karakter == '/';
	}

	// Islemi gerceklestiren fonksiyon
	private static double islemiGercekle(char islec, double islenen1, double islenen2) {
		switch (islec) {
		case '+':
			return islenen1 + islenen2;
		case '-':
			return islenen1 - islenen2;
		case '*':
			return islenen1 * islenen2;
		case '/':
			if (islenen2 != 0) {
				return islenen1 / islenen2;
			} else {
				throw new ArithmeticException("Sifira bolme hatasi");
			}
		default:
			throw new IllegalArgumentException("Gecersiz isleç: " + islec);
		}
	}

	public static void main(String[] args) {
		// Ornek postfix ifade
		String postfixIfade = "34+5*";
		// Postfix ifadesini degerlendir
		double sonuc = postfixDegerlendir(postfixIfade);
		// Sonucu ekrana yazdir
		System.out.println("Sonuc: " + sonuc);

		// Farkli postfix ifadeler ile testler
		String postfix1 = "23+5*"; // 2 + 3 = 5, 5 * 5 = 25
		System.out.println("Sonuc 1: " + postfixDegerlendir(postfix1));

		String postfix2 = "12+34+*"; // 1 + 2 = 3, 3 * (3 + 4) = 3 * 7 = 21
		System.out.println("Sonuc 2: " + postfixDegerlendir(postfix2));

		String postfix3 = "234*+"; // 3 * 4 = 12, 12 + 2 = 14
		System.out.println("Sonuc 3: " + postfixDegerlendir(postfix3));

		// Hata durumunu test et
		try {
			String postfix4 = "211-/3+"; // Sifira bolme hatasi olusturur
			// 1 - 1 = 0, 2 / 0 = hata
			System.out.println("Sonuc 4: " + postfixDegerlendir(postfix4));
		} catch (ArithmeticException e) {
			System.out.println("Hata: " + e.getMessage());
		}
	}
}
