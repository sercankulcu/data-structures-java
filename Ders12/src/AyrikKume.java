import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AyrikKume {

    // Quick-Find: id[i] = i'nin kume adi. find O(1), union O(n)
    static class QuickFind {
        private final int[] id;

        QuickFind(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) id[i] = i; // her oge kendi kumesinde
        }

        int find(int p) {
            return id[p]; // O(1)
        }

        void union(int p, int q) {
            int pid = id[p], qid = id[q];
            if (pid == qid) return;
            for (int i = 0; i < id.length; i++) // tum dizi taranir: O(n)
                if (id[i] == pid)
                    id[i] = qid;
        }
    }

    // Boyuta gore birlestirme (yol kisaltma yok)
    private final int[] ebeveyn; // ebeveyn[i]: i'nin ebeveyni; kok kendini gosterir
    private final int[] boyut;   // boyut[k]: kok k'nin agacindaki oge sayisi

    public AyrikKume(int n) {
        ebeveyn = new int[n];
        boyut = new int[n];
        for (int i = 0; i < n; i++) makeSet(i);
    }

    // i, tek ogeli bir kume olur
    private void makeSet(int i) {
        ebeveyn[i] = i;
        boyut[i] = 1;
    }

    // Ebeveyn baglantilari kok bulunana kadar izlenir
    public int find(int x) {
        while (ebeveyn[x] != x)
            x = ebeveyn[x];
        return x;
    }

    // Kucuk agacin koku buyugun kokune baglanir; esitse ikinci kok birinciye
    public void union(int x, int y) {
        int k1 = find(x), k2 = find(y);
        if (k1 == k2) return; // zaten ayni kumede
        if (boyut[k1] < boyut[k2]) {
            ebeveyn[k1] = k2;
            boyut[k2] += boyut[k1];
        } else {
            ebeveyn[k2] = k1;
            boyut[k1] += boyut[k2];
        }
    }

    public static void main(String[] args) {
        // 1) Quick-Find: 6 oge, union(0,1), union(2,3), union(1,3), union(4,3)
        QuickFind qf = new QuickFind(6);
        qf.union(0, 1);
        qf.union(2, 3);
        System.out.println("Quick-Find id: " + Arrays.toString(qf.id)); // [1, 1, 3, 3, 4, 5]
        qf.union(1, 3);
        qf.union(4, 3);
        System.out.println("Quick-Find id: " + Arrays.toString(qf.id)); // [3, 3, 3, 3, 3, 5]
        System.out.println("find(0) == find(4)? " + (qf.find(0) == qf.find(4)));

        // 2) Nesneleri indislere esleme: harf -> indis (hash tablosunda)
        char[] harfler = {'E', 'F', 'I', 'D', 'C', 'A', 'J', 'L', 'G', 'K', 'B', 'H'};
        Map<Character, Integer> indis = new HashMap<>();
        for (int i = 0; i < harfler.length; i++) indis.put(harfler[i], i); // 'E'->0, 'F'->1, ...

        AyrikKume uf = new AyrikKume(harfler.length); // 12 tekil kume
        String[] islemler = {"CK", "FE", "AJ", "AB", "CD", "DI", "LF", "CA", "AB", "HG", "HF", "HB"};
        for (String s : islemler)
            uf.union(indis.get(s.charAt(0)), indis.get(s.charAt(1)));

        System.out.println("\nBoyuta gore birlestirme sonrasi ebeveyn:");
        for (int i = 0; i < harfler.length; i++)
            System.out.print(harfler[i] + "->" + harfler[uf.ebeveyn[i]] + " ");
        System.out.println();
        // E->F F->C I->C D->C C->C A->C J->A L->F G->H K->C B->A H->F
        int h = indis.get('H'), b = indis.get('B');
        System.out.println("H ile B ayni kumede mi? " + (uf.find(h) == uf.find(b))); // true, kok C
        System.out.println("Kok: " + harfler[uf.find(h)] + ", boyut: " + uf.boyut[uf.find(h)]); // C, 12
    }
}
