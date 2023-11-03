
import java.util.Stack;

public class PalindromKontrol {

	public static boolean palindromKontrol(String metin) {
		// Boşlukları ve noktalama işaretlerini kaldırın ve metni küçük harfe çevirin
		metin = metin.replaceAll("[\\s.,?!]", "").toLowerCase();

		// Bir yığın (stack) oluşturun
		Stack<Character> yigin = new Stack<Character>();
		int uzunluk = metin.length();

		// Metnin ilk yarısını yığına ekle
		for (int i = 0; i < uzunluk / 2; i++) {
			yigin.push(metin.charAt(i));
		}

		// Metnin kalan kısmını yığından alınanla karşılaştır
		for (int i = (uzunluk / 2) + (uzunluk % 2); i < uzunluk; i++) {
			if(metin.charAt(i) != yigin.pop()) {
				System.out.println("Bu bir palindrom değildir.");
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
	}
}
