import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Infix (ara ek) ifadeyi postfix (sonek) ve prefix (onek) gosterime cevirme.
 * Islenenler (harf/rakam) dogrudan ciktiya, operatorler ve '(' yigina gider.
 * Desteklenen operatorler: + - * / (hepsi soldan saga birlesir).
 *
 * Postfix: ustte onceligi buyuk YA DA ESIT operator varsa once o cikar
 *          (soldan saga birlesme: A-B+C -> AB-C+).
 * Prefix : 1. ters cevir  2. ( <-> )  3. postfix, ama esitte pop yok  4. ters cevir.
 */
public class InfixToPostfixPrefix {

    static int oncelik(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return 0;   // '('
        }
    }

    static String infixToPostfix(String ifade) {
        return donustur(ifade, true);
    }

    // Prefix icin 3. adim: esit oncelikte pop yapilmaz
    static String prefixIcinPostfix(String ifade) {
        return donustur(ifade, false);
    }

    // esitteCikar: ustteki operator esit oncelikteyse de ciktiya alinsin mi?
    static String donustur(String ifade, boolean esitteCikar) {
        StringBuilder cikti = new StringBuilder();
        Deque<Character> yigin = new ArrayDeque<>();
        for (char c : ifade.toCharArray()) {
            if (Character.isLetterOrDigit(c)) cikti.append(c);
            else if (c == '(') yigin.push(c);
            else if (c == ')') {
                while (yigin.peek() != '(')
                    cikti.append(yigin.pop());
                yigin.pop();                  // '(' atilir
            } else {                          // operator
                while (!yigin.isEmpty() &&
                       (esitteCikar ? oncelik(yigin.peek()) >= oncelik(c)
                                    : oncelik(yigin.peek()) > oncelik(c)))
                    cikti.append(yigin.pop());
                yigin.push(c);
            }
        }
        while (!yigin.isEmpty()) cikti.append(yigin.pop());
        return cikti.toString();
    }

    static String ters(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // Ters cevrilmis ifadede parantez yonlerini degistirir: ( <-> )
    static String parantezCevir(String s) {
        char[] k = s.toCharArray();
        for (int i = 0; i < k.length; i++) {
            if (k[i] == '(') k[i] = ')';
            else if (k[i] == ')') k[i] = '(';
        }
        return new String(k);
    }

    static String infixToPrefix(String ifade) {
        String t = ters(ifade);          // 1. ters cevir
        t = parantezCevir(t);            // 2. ( <-> )
        String p = prefixIcinPostfix(t); // 3. esitte pop yok
        return ters(p);                  // 4. ters cevir
    }

    public static void main(String[] args) {
        String[] ornekler = { "A*(B+C)/D", "A+B*C-D/E", "(A+B)*(C-D)/E", "A-B+C" };
        for (String s : ornekler) {
            System.out.println("Infix  : " + s);
            System.out.println("Postfix: " + infixToPostfix(s));
            System.out.println("Prefix : " + infixToPrefix(s));
            System.out.println();
        }
    }
}
