import java.util.ArrayList;
import java.util.List;

public class HashKume {

    private final int m = 7;          // kova sayisi
    private final List<List<Integer>> kova = new ArrayList<>();
    private int n = 0;                // kumedeki oge sayisi

    public HashKume() {
        for (int i = 0; i < m; i++)
            kova.add(new ArrayList<>()); // her kova bos bir zincir
    }

    // x yoksa ekler ve true dondurur; varsa kume degismez, false (x >= 0)
    public boolean add(int x) {
        int i = x % m;                       // kova no
        for (int y : kova.get(i))
            if (y == x) return false;        // zaten var
        kova.get(i).add(x);
        n++;
        return true;
    }

    // Yalnizca x'in kovasi taranir; diger kovalara bakilmaz
    public boolean contains(int x) {
        int i = x % m;
        for (int y : kova.get(i))
            if (y == x) return true;
        return false;
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        HashKume s = new HashKume();
        System.out.println("add(12): " + s.add(12)); // kova 5
        System.out.println("add(25): " + s.add(25)); // kova 4
        System.out.println("add(7) : " + s.add(7));  // kova 0
        System.out.println("add(19): " + s.add(19)); // kova 5, cakisma: zincire eklenir
        System.out.println("add(12): " + s.add(12)); // zaten var: false
        System.out.println("contains(26): " + s.contains(26)); // kova 5 taranir: false
        System.out.println("contains(19): " + s.contains(19)); // true
        System.out.println("n = " + s.size());                 // 4
        for (int i = 0; i < s.m; i++)
            System.out.println("kova " + i + ": " + s.kova.get(i));
    }
}
