import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Yigin ile dize tersine cevirme: karakterler sirayla push edilir,
 * pop ile ters sirada geri alinir (LIFO). O(n) zaman, O(n) ek alan.
 */
public class DizeTersCevirme {

    static String tersCevir(String str) {
        Deque<Character> yigin = new ArrayDeque<>();
        char[] karakterler = str.toCharArray();
        for (char c : karakterler)
            yigin.push(c);
        for (int i = 0; i < str.length(); i++)
            karakterler[i] = yigin.pop();
        return new String(karakterler);
    }

    public static void main(String[] args) {
        System.out.println(tersCevir("ABCD"));
        System.out.println(tersCevir("Merhaba Dunya"));
    }
}
