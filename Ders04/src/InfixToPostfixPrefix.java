import java.util.Stack;

/*
 * "Infix, Postfix ve Prefix" terimleri, matematiksel ifadeleri veya aritmetik 
 * işlemleri farklı şekillerde ifade etmek için kullanılan notasyonlardır. Bu 
 * notasyonlar, bir ifadeyi okuma ve hesaplama kolaylığı sağlamak amacıyla 
 * kullanılır. 
 * 
 * Infix Notasyonu (Orta Eklem Notasyonu):
 * 
 * Infix notasyonu, matematiksel ifadelerin en yaygın kullanılan notasyonudur.
 * Bu notasyonda işlemler, operatörlerin iki operandı arasına yazılır, örneğin "a + b".
 * Öncelikli işlem sırası kurallarına uygun olarak işlemler yapılır.
 * 
 * Postfix Notasyonu (Ters Eklem Notasyonu):
 * 
 * Postfix notasyonunda, işlemler operandlarının hemen ardından yazılır, örneğin "ab+".
 * Postfix notasyonu, işlem önceliği sorunlarını ortadan kaldırır ve bir ifadeyi daha 
 * rahat hesaplamayı sağlar. İşlemler, operandlarının sırasına göre yapılır.
 * Daha karmaşık ifadelerde işlemlerin önceliği daha açık bir şekilde belirtilir.
 * 
 * Prefix Notasyonu (Ön Eklem Notasyonu):
 * Prefix notasyonunda, işlemler operandlarından önce yazılır, örneğin "+ab".
 * Prefix notasyonu, işlem önceliğini belirtmek için parantez kullanmaya gerek kalmadan 
 * daha karmaşık ifadeleri açıklamayı sağlar.
 * 
 * Bu notasyonlar, matematiksel ifadelerin okunması ve hesaplanması açısından farklı 
 * yaklaşımları temsil eder. Özellikle bilgisayar programlaması, hesaplama ve veritabanı 
 * sorgularında bu notasyonlar kullanılır. Infix notasyonu en yaygın kullanılan notasyon 
 * olsa da, işlem önceliği ve ifadelerin açıklığı gerektiğinde Postfix ve Prefix 
 * notasyonları da tercih edilir. 
 * 
 * */

public class InfixToPostfixPrefix {

	// İşlem önceliği fonksiyonu
	static int oncelik(char karakter) {
		switch (karakter) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}

	// Infix ifadeyi Postfix ifadeye dönüştüren fonksiyon
	static String infixToPostfix(String ifade) {
		StringBuilder postfix = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < ifade.length(); i++) {
			char karakter = ifade.charAt(i);

			if (Character.isLetterOrDigit(karakter)) {
				postfix.append(karakter);
			} else if (karakter == '(') {
				stack.push(karakter);
			} else if (karakter == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					postfix.append(stack.pop());
				}
				if (!stack.isEmpty() && stack.peek() != '(') {
					return "Geçersiz İfade";
				} else {
					stack.pop();
				}
			} else {
				while (!stack.isEmpty() && oncelik(karakter) <= oncelik(stack.peek())) {
					postfix.append(stack.pop());
				}
				stack.push(karakter);
			}
		}

		while (!stack.isEmpty()) {
			if (stack.peek() == '(') {
				return "Geçersiz İfade";
			}
			postfix.append(stack.pop());
		}

		return postfix.toString();
	}

	// Infix ifadeyi Prefix ifadeye dönüştüren fonksiyon
	static String infixToPrefix(String ifade) {
		String tersIfade = new StringBuilder(ifade).reverse().toString();
		for (int i = 0; i < tersIfade.length(); i++) {
			if (tersIfade.charAt(i) == '(') {
				tersIfade = tersIfade.substring(0, i) + ')' + tersIfade.substring(i + 1);
			} else if (tersIfade.charAt(i) == ')') {
				tersIfade = tersIfade.substring(0, i) + '(' + tersIfade.substring(i + 1);
			}
		}
		String postfix = infixToPostfix(tersIfade);
		return new StringBuilder(postfix).reverse().toString();
	}

	public static void main(String[] args) {
		String infixIfade = "A*(B+C)/D";
		String postfixIfade = infixToPostfix(infixIfade);
		String prefixIfade = infixToPrefix(infixIfade);

		System.out.println("Infix İfade: " + infixIfade);
		System.out.println("Postfix İfade: " + postfixIfade);
		System.out.println("Prefix İfade: " + prefixIfade);
	}
}
