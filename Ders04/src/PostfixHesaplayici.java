
import java.util.Stack;

public class PostfixHesaplayici {
	
	public static double postfixDegerlendir(String ifade) {
		
		// İşlenenleri saklamak için stack
		Stack<Double> islenenlerYigini = new Stack<>();

		// İfade üzerinde her karakteri işle
		for (char sembol : ifade.toCharArray()) {
			// Eğer karakter bir rakam ise, onu double'a çevirip stack'e ekle
			if (Character.isDigit(sembol)) {
				islenenlerYigini.push(Double.parseDouble(String.valueOf(sembol)));
			} else if (isOperator(sembol)) {
				// Eğer karakter bir işleç ise, stack'ten işlenenleri çıkar,
				// işlemi gerçekleştirip sonucu tekrar stack'e ekle
				double islenen2 = islenenlerYigini.pop();
				double islenen1 = islenenlerYigini.pop();
				double sonuc = islemiGercekle(sembol, islenen1, islenen2);
				islenenlerYigini.push(sonuc);
			}
		}

		// Sonuç stack'in en üstünde olmalıdır
		return islenenlerYigini.pop();
	}

	private static boolean isOperator(char karakter) {
		return karakter == '+' || karakter == '-' || karakter == '*' || karakter == '/';
	}

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
				throw new ArithmeticException("Sıfıra bölme hatası");
			}
		default:
			throw new IllegalArgumentException("Geçersiz işleç: " + islec);
		}
	}

	public static void main(String[] args) {
		// Örnek kullanım
		String postfixIfade = "34+5*";
		double sonuc = postfixDegerlendir(postfixIfade);
		System.out.println("Sonuç: " + sonuc);
	}
}
