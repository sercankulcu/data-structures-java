// Cagri yigini: her metot cagrisi yigina bir cerceve (parametreler, yerel degiskenler,
// donus adresi) iter; metot donunce cerceve cikarilir. Son cagrilan ilk doner (LIFO).
public class CagriYigini {

	static int derinlik = 0;   // yigindaki fakt cercevesi sayisi

	static String girinti() {
		return "  ".repeat(derinlik);
	}

	static int fakt(int n) {
		derinlik++;
		System.out.println(girinti() + "push fakt(" + n + ")   yigin derinligi: " + derinlik);
		int sonuc;
		if (n <= 1)
			sonuc = 1;
		else
			sonuc = n * fakt(n - 1);
		System.out.println(girinti() + "pop  fakt(" + n + ") = " + sonuc);
		derinlik--;
		return sonuc;
	}

	public static void main(String[] args) {
		System.out.println(fakt(3));   // 6; en buyuk derinlik 3 (main ile 4 cerceve)
	}
}
