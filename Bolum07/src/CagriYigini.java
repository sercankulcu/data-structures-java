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

/* Beklenen cikti:
  push fakt(3)   yigin derinligi: 1
    push fakt(2)   yigin derinligi: 2
      push fakt(1)   yigin derinligi: 3
      pop  fakt(1) = 1
    pop  fakt(2) = 2
  pop  fakt(3) = 6
6
*/
