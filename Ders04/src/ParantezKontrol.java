import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Parantez kontrolu: dize yalnizca ( ) [ ] { } karakterlerinden olusur.
 * Eslesmemis acik parantezler yiginda tutulur; en son acilan ilk kapanmalidir (LIFO).
 * Her karakter bir kez islenir -> O(n).
 */
public class ParantezKontrol {

    static boolean gecerliMi(String s) {
        Deque<Character> yigin = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                yigin.push(c);
            } else {
                if (yigin.isEmpty()) return false;   // fazla kapanis
                char acik = yigin.pop();
                if (!eslesir(acik, c)) return false; // tip ya da sira yanlis
            }
        }
        return yigin.isEmpty();                      // kapanmamis parantez kaldi mi?
    }

    static boolean eslesir(char a, char k) {
        return (a == '(' && k == ')')
            || (a == '[' && k == ']')
            || (a == '{' && k == '}');
    }

    public static void main(String[] args) {
        String[] ornekler = { "{{[]}()}[]", "{[()]}", "([)]", "(()", "())", "{[()()]}", "{[(])}" };
        for (String s : ornekler)
            System.out.println(s + " -> " + (gecerliMi(s) ? "gecerli" : "gecersiz"));
    }
}
