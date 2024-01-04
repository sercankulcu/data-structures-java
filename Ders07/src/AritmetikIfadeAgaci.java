import java.util.Stack;

public class AritmetikIfadeAgaci {
	
	public static void main(String[] args) {
		
		AritmetikIfadeAgaci agac = new AritmetikIfadeAgaci();
		String ifade = agac.infixToPostfix("(3+5)/2*5-(4*2)");

		Dugum<Character> kok = agac.ifadeyiAritmetikAgacaCevir(ifade);

		System.out.println("İkili İfade Ağacı:");
		agac.agaciGoruntule(kok, "");

		int sonuc = agac.agacDegeriniHesapla(kok);
		System.out.println("\nKökteki Değer: " + sonuc);
	}

	public Dugum<Character> ifadeyiAritmetikAgacaCevir(String ifade) {
		Stack<Dugum<Character>> yigit = new Stack<>();
		for (char c : ifade.toCharArray()) {
			if (c == ' ') {
				continue;
			}

			if (c == '+' || c == '-' || c == '*' || c == '/') {
				Dugum<Character> dugum = new Dugum<>(c);
				dugum.sag = yigit.pop();
				dugum.sol = yigit.pop();
				yigit.push(dugum);
			} else {
				Dugum<Character> dugum = new Dugum<>(c);
				yigit.push(dugum);
			}
		}
		return yigit.pop();
	}

	public void agaciGoruntule(Dugum<Character> dugum, String onEk) {
		if (dugum != null) {
			System.out.println(onEk + dugum.veri);
			agaciGoruntule(dugum.sol, onEk + "|-- ");
			agaciGoruntule(dugum.sag, onEk + "|-- ");
		}
	}

	public int agacDegeriniHesapla(Dugum<Character> kok) {
		if (kok == null) {
			return 0;
		}

		if (kok.sol == null && kok.sag == null) {
			return Character.getNumericValue(kok.veri);
		}

		int solDeger = agacDegeriniHesapla(kok.sol);
		int sagDeger = agacDegeriniHesapla(kok.sag);

		switch (kok.veri) {
		case '+':
			return solDeger + sagDeger;
		case '-':
			return solDeger - sagDeger;
		case '*':
			return solDeger * sagDeger;
		case '/':
			if (sagDeger != 0) {
				return solDeger / sagDeger;
			} else {
				throw new ArithmeticException("Sıfıra bölme hatası");
			}
		default:
			throw new IllegalArgumentException("Geçersiz operatör: " + kok.veri);
		}
	}

	public String infixToPostfix(String infix) {
		StringBuilder postfix = new StringBuilder();
		Stack<Character> operatorStack = new Stack<>();

		for (int i = 0; i < infix.length(); i++) {
			char c = infix.charAt(i);

			if (Character.isLetterOrDigit(c)) {
				postfix.append(c);
			} else if (c == '(') {
				operatorStack.push(c);
			} else if (c == ')') {
				while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
					postfix.append(operatorStack.pop());
				}
				operatorStack.pop(); // Remove the '(' from the stack
			} else {
				while (!operatorStack.isEmpty() && oncelik(c) <= oncelik(operatorStack.peek())) {
					postfix.append(operatorStack.pop());
				}
				operatorStack.push(c);
			}
		}

		while (!operatorStack.isEmpty()) {
			if (operatorStack.peek() == '(') {
				return "Invalid infix expression";
			}
			postfix.append(operatorStack.pop());
		}

		return postfix.toString();
	}

	public int oncelik(char operator) {
		switch (operator) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		default:
			return -1;
		}
	}
}
