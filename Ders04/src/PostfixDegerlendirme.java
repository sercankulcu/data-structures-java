import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Postfix (sonek) ifadeyi yiginla degerlendirme.
 * Soldan saga okunur: sayi -> push; operator -> iki oge pop (once sag, sonra sol),
 * sonuc push. Sonda yiginda tek oge kalir. Parantez ve oncelik gerekmez -> O(n).
 * Varsayim: tek basamakli sayilar, bosluksuz ve gecerli ifade; tamsayi bolmesi.
 */
public class PostfixDegerlendirme {

    static int hesapla(int sol, char op, int sag) {
        switch (op) {
            case '+': return sol + sag;
            case '-': return sol - sag;
            case '*': return sol * sag;
            case '/': return sol / sag;   // sag == 0 ise ArithmeticException
            default: throw new IllegalArgumentException("Gecersiz operator: " + op);
        }
    }

    static int postfixDegerlendir(String ifade) {
        Deque<Integer> yigin = new ArrayDeque<>();
        for (char c : ifade.toCharArray()) {
            if (Character.isDigit(c)) {
                yigin.push(c - '0');
            } else {
                int sag = yigin.pop();    // ilk pop sag islenen
                int sol = yigin.pop();
                yigin.push(hesapla(sol, c, sag));
            }
        }
        return yigin.pop();
    }

    public static void main(String[] args) {
        String[] ornekler = { "412+*3/", "512+4*+3-", "52-" };
        for (String s : ornekler)
            System.out.println(s + " = " + postfixDegerlendir(s));
    }
}
