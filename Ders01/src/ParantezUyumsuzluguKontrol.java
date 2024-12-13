import java.util.Stack;

public class ParantezUyumsuzluguKontrol {

	public static void main(String[] args) {

		// Farkli ifadeler tanimlanir ve uyumluluk kontrolleri yapilir
		String ifade1 = "Merhaba (Dunya)"; // Uyumlu ifade
		String ifade2 = "Merhaba [Dunya"; // Uyumsuz ifade
		String ifade3 = "Merhaba [Dunya(2)]"; // Uyumlu ifade
		String ifade4 = "Merhaba [Dunya(])"; // Uyumsuz ifade
		String ifade5 = "{(Merhaba) [Dunya]}"; // Uyumlu ifade

		// Her ifade icin uyumluluk sonucu ekrana yazdirilir
		System.out.println(ifade1 + " ifadesinin uyumsuzlugu: " + parantezUyumsuzluguKontrol(ifade1));
		System.out.println(ifade2 + " ifadesinin uyumsuzlugu: " + parantezUyumsuzluguKontrol(ifade2));
		System.out.println(ifade3 + " ifadesinin uyumsuzlugu: " + parantezUyumsuzluguKontrol(ifade3));
		System.out.println(ifade4 + " ifadesinin uyumsuzlugu: " + parantezUyumsuzluguKontrol(ifade4));
		System.out.println(ifade5 + " ifadesinin uyumsuzlugu: " + parantezUyumsuzluguKontrol(ifade5));
	}

	/**
	 * Verilen bir ifadede parantez uyumlulugunu kontrol eden metod.
	 * @param ifade Kontrol edilecek ifade
	 * @return Uyumlu ise true, uyumsuz ise false
	 */
	public static boolean parantezUyumsuzluguKontrol(String ifade) {
		Stack<Character> parantezStack = new Stack<>(); // Parantezleri saklamak icin stack yapisi kullanilir

		// Ifadedeki her karakter tek tek kontrol edilir
		for (char karakter : ifade.toCharArray()) {
			if (karakter == '(' || karakter == '[' || karakter == '{') {
				// Acilan parantezler stack'e eklenir
				parantezStack.push(karakter);
			} else if (karakter == ')' || karakter == ']' || karakter == '}') {
				// Kapanan parantez bulunursa
				if (parantezStack.isEmpty()) {
					// Stack bos ise uyumsuzluk var demektir
					return false;
				}

				char ustParantez = parantezStack.pop(); // Stack'ten en ustteki parantez cikarilir
				if (!parantezUyumu(ustParantez, karakter)) {
					// Acilan ve kapanan parantez uyumsuz ise hata doner
					return false;
				}
			}
		}

		// Tum karakterler kontrol edildikten sonra stack hala dolu ise uyumsuzluk vardir
		return parantezStack.isEmpty();
	}

	/**
	 * Verilen acilan ve kapanan parantezin uyumlu olup olmadigini kontrol eden metod.
	 * @param acilanParantez Acilan parantez
	 * @param kapananParantez Kapanan parantez
	 * @return Uyumlu ise true, uyumsuz ise false
	 */
	public static boolean parantezUyumu(char acilanParantez, char kapananParantez) {
		return (acilanParantez == '(' && kapananParantez == ')') || 
				(acilanParantez == '[' && kapananParantez == ']') || 
				(acilanParantez == '{' && kapananParantez == '}');
	}
}
