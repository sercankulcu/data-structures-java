import java.util.Stack;

public class ParantezUyumsuzluguKontrol {
	
	public static void main(String[] args) {
		
		String ifade1 = "Merhaba (Dünya)"; // Uyumlu ifade
		String ifade2 = "Merhaba [Dünya"; // Uyumsuz ifade
		String ifade3 = "Merhaba [Dünya(2)]"; // Uyumlu ifade
		String ifade4 = "Merhaba [Dünya(])"; // Uyumsuz ifade
		String ifade5 = "{(Merhaba) [Dünya]}"; // Uyumlu ifade

		System.out.println(ifade1 + " ifadesinin uyumsuzluğu: " + parantezUyumsuzluguKontrol(ifade1));
		System.out.println(ifade2 + " ifadesinin uyumsuzluğu: " + parantezUyumsuzluguKontrol(ifade2));
		System.out.println(ifade3 + " ifadesinin uyumsuzluğu: " + parantezUyumsuzluguKontrol(ifade3));
		System.out.println(ifade4 + " ifadesinin uyumsuzluğu: " + parantezUyumsuzluguKontrol(ifade4));
		System.out.println(ifade5 + " ifadesinin uyumsuzluğu: " + parantezUyumsuzluguKontrol(ifade5));
	}

	public static boolean parantezUyumsuzluguKontrol(String ifade) {
		Stack<Character> parantezStack = new Stack<>();

		for (char karakter : ifade.toCharArray()) {
			if (karakter == '(' || karakter == '[' || karakter == '{') {
				parantezStack.push(karakter);
			} else if (karakter == ')' || karakter == ']' || karakter == '}') {
				if (parantezStack.isEmpty()) {
					return false; // Uyumsuz parantez bulundu
				}

				char ustParantez = parantezStack.pop();
				if (!parantezUyumu(ustParantez, karakter)) {
					return false; // Uyumsuz parantez bulundu
				}
			}
		}

		return parantezStack.isEmpty(); // Stack boş ise ifade uyumlu
	}

	public static boolean parantezUyumu(char acilanParantez, char kapananParantez) {
		return (acilanParantez == '(' && kapananParantez == ')') ||
				(acilanParantez == '[' && kapananParantez == ']') ||
				(acilanParantez == '{' && kapananParantez == '}');
	}
}
