import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class KumeIslemleri {

    // Ilk tekrar eden ogeyi bulur: add zaten varsa false dondurur. O(n)
    static int ilkTekrar(int[] a) {
        Set<Integer> gorulen = new HashSet<>();
        for (int x : a) {
            if (!gorulen.add(x)) // eklenemedi mi?
                return x;        // zaten vardi: tekrar
        }
        return -1;               // tekrar yok
    }

    public static void main(String[] args) {
        // 1) java.util.Set ile kume cebri
        Set<Integer> a = new HashSet<>(List.of(1, 2, 3, 4, 5));
        Set<Integer> b = new HashSet<>(List.of(3, 4, 5, 6, 7));

        // addAll, retainAll, removeAll kumeyi yerinde degistirir; a'yi korumak icin once kopyalanir
        Set<Integer> bir = new HashSet<>(a);
        bir.addAll(b);     // birlesim
        Set<Integer> kes = new HashSet<>(a);
        kes.retainAll(b);  // kesisim
        Set<Integer> fark = new HashSet<>(a);
        fark.removeAll(b); // fark A - B

        System.out.println("A = " + a + ", B = " + b);
        System.out.println("Birlesim A U B : " + bir);   // [1..7]
        System.out.println("Kesisim A n B  : " + kes);   // [3, 4, 5]
        System.out.println("Fark A - B     : " + fark);  // [1, 2]
        System.out.println("B, A'nin alt kumesi mi? " + a.containsAll(b));           // false
        System.out.println("{3, 4}, A'nin alt kumesi mi? " + a.containsAll(Set.of(3, 4))); // true
        System.out.println("add(3) ikinci kez: " + a.add(3)); // false, kume degismez

        // 2) HashSet ile ilk tekrar
        int[] dizi = {4, 7, 2, 7, 4};
        System.out.println("\nIlk tekrar eden oge: " + ilkTekrar(dizi)); // 7

        // 3) Uc Set uygulamasinin gezinme sirasi
        Set<String> hash = new HashSet<>();
        Set<String> linked = new LinkedHashSet<>();
        Set<String> tree = new TreeSet<>();
        for (String s : List.of("elma", "kiraz", "armut", "elma")) {
            hash.add(s);
            linked.add(s);
            tree.add(s);
        }
        System.out.println("\nHashSet       : " + hash);   // hash koduna bagli
        System.out.println("LinkedHashSet : " + linked);   // ekleme sirasi
        System.out.println("TreeSet       : " + tree);     // alfabetik

        // 4) Agac tabanli (sirali) kume: TreeSet ek islemleri
        TreeSet<Integer> s = new TreeSet<>(List.of(20, 10, 30, 5, 15, 25, 40));
        System.out.println("\nS = " + s);
        System.out.println("first / last : " + s.first() + " / " + s.last()); // 5 / 40
        System.out.println("floor(17)    : " + s.floor(17));   // <= 17 olan en buyuk: 15
        System.out.println("ceiling(17)  : " + s.ceiling(17)); // >= 17 olan en kucuk: 20
        System.out.println("subSet(10,30): " + s.subSet(10, 30)); // [10, 15, 20, 25]
    }
}
