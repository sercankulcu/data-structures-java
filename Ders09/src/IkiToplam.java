import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Toplami hedef olan iki indisi bulma: kaba kuvvet O(n^2) ile esleme kullanan O(n) cozum.
public class IkiToplam {

    // Tum ciftleri dener: O(n^2)
    static int[] ikiToplamKabaKuvvet(int[] a, int hedef) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == hedef) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Tek gecis: gorulen degerler indisleriyle eslemede tutulur, O(n) zaman ve O(n) ek bellek
    static int[] ikiToplam(int[] a, int hedef) {
        Map<Integer, Integer> gor = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int ek = hedef - a[i];        // gereken es
            if (gor.containsKey(ek)) {
                return new int[]{gor.get(ek), i};
            }
            gor.put(a[i], i);             // deger -> indis
        }
        return null;                      // cift yok
    }

    public static void main(String[] args) {
        int[] a = {8, 3, 11, 5, 7};
        System.out.println("ikiToplam(a, 12)    = " + Arrays.toString(ikiToplam(a, 12)));
        System.out.println("kaba kuvvet(a, 12)  = " + Arrays.toString(ikiToplamKabaKuvvet(a, 12)));
        System.out.println("ikiToplam(a, 100)   = " + Arrays.toString(ikiToplam(a, 100)));
    }
}
