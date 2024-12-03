import java.util.Stack;

public class PalindromKontrol {

	public static boolean palindromKontrol(String metin) {
		// Bosluklari ve noktalama isaretlerini kaldirin ve metni kucuk harfe cevirin
		metin = metin.replaceAll("[\\s.,?!]", "").toLowerCase();

		// Bir yigin (stack) olusturun
		Stack<Character> yigin = new Stack<>();
		int uzunluk = metin.length();

		// Metnin ilk yarisini yigina ekle
		for (int i = 0; i < uzunluk / 2; i++) {
			yigin.push(metin.charAt(i));
		}

		// Metnin kalan kismini yigindan alinanla karsilastir
		for (int i = (uzunluk / 2) + (uzunluk % 2); i < uzunluk; i++) {
			if(metin.charAt(i) != yigin.pop()) {
				System.out.println("Bu bir palindrom degildir.");
				return false;
			}
		}
		System.out.println("Bu bir palindromdur.");
		return true;
	}

	public static void main(String[] args) {

		palindromKontrol("abba");
		palindromKontrol("kasayla b alyasak");
		palindromKontrol("kasayla balyasak");
		palindromKontrol("abcba");
		palindromKontrol("ey edip adanada pide ye");
		palindromKontrol("ey edip adanada pide ye!");
		palindromKontrol("ey edip adanade pide ye!");
	}
}
