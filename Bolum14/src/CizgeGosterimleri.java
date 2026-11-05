import java.util.ArrayList;
import java.util.List;

public class CizgeGosterimleri {

    // Komsuluk listesi: her dugum icin komsularinin listesi tutulur. Bellek O(n + m)
    static class Cizge {
        private final int n;                                      // dugumler: 0..n-1
        private final List<List<Integer>> komsu = new ArrayList<>();

        Cizge(int n) {
            this.n = n;
            for (int i = 0; i < n; i++)
                komsu.add(new ArrayList<>());                     // her dugume bos liste
        }

        void kenarEkle(int u, int v) {
            komsu.get(u).add(v);
            komsu.get(v).add(u);                                  // yonlu cizgede bu satir yok
        }

        int dugumSayisi() { return n; }
        List<Integer> komsular(int u) { return komsu.get(u); }    // O(1)
        int derece(int u) { return komsu.get(u).size(); }         // liste uzunlugu = derece
    }

    // Komsuluk matrisi: n x n boolean dizi. Bellek O(n^2)
    static class MatrisCizge {
        private final boolean[][] m;

        MatrisCizge(int n) { m = new boolean[n][n]; }             // tum hucreler false

        void kenarEkle(int u, int v) {
            m[u][v] = true;
            m[v][u] = true;                                       // yonlu cizgede yok
        }

        boolean kenarVarMi(int u, int v) { return m[u][v]; }      // O(1)
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] E = {{0, 1}, {0, 4}, {1, 2}, {1, 4}, {2, 3}, {3, 4}};

        // 1) Kenar listesi: tum kenarlar tek listede; "u'nun komsulari?" icin O(m) tarama
        System.out.print("Kenar listesi: ");
        for (int[] e : E) System.out.print("(" + e[0] + "," + e[1] + ") ");
        System.out.println();

        // 2) Komsuluk listesi: 6 kenar -> listelerde 12 kayit
        Cizge g = new Cizge(n);
        for (int[] e : E) g.kenarEkle(e[0], e[1]);
        System.out.println("Komsuluk listesi:");
        for (int u = 0; u < g.dugumSayisi(); u++)
            System.out.println("  " + u + ": " + g.komsular(u));
        System.out.println("derece(1) = " + g.derece(1));        // 3

        // 3) Komsuluk matrisi: 25 hucrenin 12'si true
        MatrisCizge mg = new MatrisCizge(n);
        for (int[] e : E) mg.kenarEkle(e[0], e[1]);
        System.out.println("Komsuluk matrisi:");
        for (int u = 0; u < n; u++) {
            System.out.print("  ");
            for (int v = 0; v < n; v++)
                System.out.print(mg.kenarVarMi(u, v) ? "1 " : "0 ");
            System.out.println();
        }
        System.out.println("2-4 kenari var mi? " + mg.kenarVarMi(2, 4));   // false, O(1)
        System.out.println("1-4 kenari var mi? " + mg.kenarVarMi(1, 4));   // true
    }
}
