import java.util.ArrayList;
import java.util.List;

public class Kruskal {

    // Dizi tabanli Union-Find (rutbe + yol kisaltma)
    static class UnionFind {
        private final int[] ebeveyn, sira;

        UnionFind(int n) {
            ebeveyn = new int[n];
            sira = new int[n];
            for (int i = 0; i < n; i++) ebeveyn[i] = i;
        }

        int find(int x) {
            if (ebeveyn[x] != x) ebeveyn[x] = find(ebeveyn[x]);
            return ebeveyn[x];
        }

        boolean union(int x, int y) {
            int kx = find(x), ky = find(y);
            if (kx == ky) return false; // ayni kume: kenar dongu kurar
            if (sira[kx] < sira[ky]) ebeveyn[kx] = ky;
            else if (sira[kx] > sira[ky]) ebeveyn[ky] = kx;
            else { ebeveyn[kx] = ky; sira[ky]++; }
            return true;
        }
    }

    // Kenar {u, v, w} dizisi olarak tutulur. Dondurulen liste: MST kenarlari
    static List<int[]> kruskal(int n, List<int[]> E) {
        E.sort((a, b) -> Integer.compare(a[2], b[2])); // agirliga gore (kararli) sirala
        UnionFind uf = new UnionFind(n);
        List<int[]> T = new ArrayList<>();
        for (int[] e : E) {
            if (uf.union(e[0], e[1]))  // dongusuz
                T.add(e);
            if (T.size() == n - 1) break;
        }
        return T; // cizge bagli degilse: minimum kapsayan orman
    }

    static char ad(int i) {
        return (char) ('A' + i); // A=0 ... J=9
    }

    // "AE" -> {0, 4, w}
    static int[] kenar(String uv, int w) {
        return new int[]{uv.charAt(0) - 'A', uv.charAt(1) - 'A', w};
    }

    public static void main(String[] args) {
        // Slayttaki cizge: 10 dugum (A..J), 18 kenar
        List<int[]> E = new ArrayList<>(List.of(
            kenar("AB", 5), kenar("AD", 4), kenar("AE", 1), kenar("BC", 4),
            kenar("BD", 2), kenar("CH", 4), kenar("CI", 1), kenar("CJ", 2),
            kenar("DE", 2), kenar("DF", 5), kenar("DG", 11), kenar("DH", 2),
            kenar("EF", 1), kenar("FG", 7), kenar("GH", 1), kenar("GI", 4),
            kenar("HI", 6), kenar("IJ", 0)));

        List<int[]> T = kruskal(10, E);
        int toplam = 0;
        System.out.println("Minimum kapsayan agac kenarlari:");
        for (int[] e : T) {
            System.out.println(ad(e[0]) + "-" + ad(e[1]) + " (" + e[2] + ")");
            toplam += e[2];
        }
        System.out.println("Kenar sayisi: " + T.size() + ", toplam agirlik: " + toplam); // 9, 14
    }
}
