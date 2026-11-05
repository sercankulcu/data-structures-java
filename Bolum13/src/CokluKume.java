import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CokluKume<E> {

    private final Map<E, Integer> say = new HashMap<>(); // oge -> sayac
    private int n = 0;                                   // tekrarlar dahil oge sayisi

    // Yoksa sayac 1 ile eklenir, varsa 1 artar
    public void add(E e) {
        say.merge(e, 1, Integer::sum);
        n++;
    }

    // e kac kez var? (yoksa 0)
    public int count(E e) {
        return say.getOrDefault(e, 0);
    }

    // Sayaci 1 azaltir; 0 olacaksa girdi tamamen silinir
    public boolean remove(E e) {
        Integer c = say.get(e);
        if (c == null) return false;  // e yok
        if (c == 1) say.remove(e);    // sayac 0 olacak: girdiyi sil
        else say.put(e, c - 1);
        n--;
        return true;
    }

    public int size() {
        return n;
    }

    @Override
    public String toString() {
        return say.toString();
    }

    // Coklu esleme (multimap): bir anahtar birden cok degere eslenir
    static class CokluEsleme {
        private final Map<String, List<String>> m = new HashMap<>();

        void put(String k, String v) { // yoksa bos liste ac
            m.computeIfAbsent(k, x -> new ArrayList<>()).add(v);
        }

        List<String> get(String k) {
            return m.getOrDefault(k, List.of());
        }
    }

    public static void main(String[] args) {
        CokluKume<String> c = new CokluKume<>();
        c.add("a");
        c.add("b");
        c.add("a"); // Set olsaydi degismezdi; burada sayac 2 olur
        c.add("c");
        System.out.println("M = " + c + ", size = " + c.size());   // {a=2, b=1, c=1}, 4
        System.out.println("count(\"a\") = " + c.count("a"));     // 2
        System.out.println("remove(\"a\") = " + c.remove("a"));  // sayac 1'e iner
        System.out.println("remove(\"b\") = " + c.remove("b"));  // girdi silinir
        System.out.println("remove(\"x\") = " + c.remove("x"));  // yok: false
        System.out.println("count(\"b\") = " + c.count("b"));     // 0
        System.out.println("M = " + c + ", size = " + c.size());   // {a=1, c=1}, 2

        // Ornek problem: "kavak" kelimesinin harfleri
        CokluKume<Character> harf = new CokluKume<>();
        for (char ch : "kavak".toCharArray())
            harf.add(ch);
        System.out.println("\nkavak: count('a') = " + harf.count('a') + ", size() = " + harf.size()); // 2, 5

        // Coklu esleme: ders -> ogrenciler
        CokluEsleme dersler = new CokluEsleme();
        dersler.put("BIL201", "Ali");
        dersler.put("BIL201", "Ece");
        dersler.put("BIL201", "Can");
        dersler.put("BIL203", "Ece");
        dersler.put("MAT101", "Can");
        dersler.put("MAT101", "Zeynep");
        System.out.println("\nget(\"BIL201\") = " + dersler.get("BIL201")); // [Ali, Ece, Can]
        System.out.println("get(\"FIZ101\") = " + dersler.get("FIZ101"));   // []
    }
}
