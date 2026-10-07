import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TopolojikSiralama {

    // Yonlu cizge: komsu.get(u) yalnizca u'dan cikan kenarlarin hedeflerini tutar
    private final List<List<Integer>> komsu = new ArrayList<>();

    TopolojikSiralama(int n) {
        for (int i = 0; i < n; i++)
            komsu.add(new ArrayList<>());
    }

    void kenarEkle(int u, int v) {
        komsu.get(u).add(v);                                  // yalnizca u -> v
    }

    // Kahn algoritmasi: giris derecesi 0 olanlari kuyruktan isle. O(n + m)
    static List<Integer> kahn(List<List<Integer>> adj) {
        int n = adj.size();
        int[] giris = new int[n];                             // giris dereceleri
        for (List<Integer> l : adj)
            for (int v : l) giris[v]++;
        Queue<Integer> q = new ArrayDeque<>();
        for (int v = 0; v < n; v++)
            if (giris[v] == 0) q.add(v);
        List<Integer> sonuc = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            sonuc.add(u);
            for (int v : adj.get(u))
                if (--giris[v] == 0) q.add(v);                // u -> v silinmis sayilir
        }
        return sonuc;                                         // boyut < n -> dongu var
    }

    // 3 renkli DFS. renk: 0 beyaz, 1 gri (yolda), 2 siyah (bitti)
    boolean donguVar(int v, int[] renk) {
        renk[v] = 1;
        for (int u : komsu.get(v)) {
            if (renk[u] == 1) return true;                    // geri kenar: dongu
            if (renk[u] == 0 && donguVar(u, renk))
                return true;
        }
        renk[v] = 2;
        return false;
    }

    public static void main(String[] args) {
        // Ders on kosulu ornegi: u -> v ise u, v'den once gelmeli
        TopolojikSiralama dag = new TopolojikSiralama(6);
        int[][] onKosul = {{0, 3}, {1, 3}, {1, 4}, {2, 0}, {2, 4}, {3, 5}, {4, 5}};
        for (int[] e : onKosul) dag.kenarEkle(e[0], e[1]);
        List<Integer> sira = kahn(dag.komsu);
        System.out.println("Kahn: " + sira + " (boyut " + sira.size() + " = n, dongu yok)");  // [1, 2, 0, 4, 3, 5]

        // Dongulu yonlu cizge: 0 -> 3 -> 4 -> 0
        TopolojikSiralama g = new TopolojikSiralama(5);
        int[][] kenarlar = {{0, 1}, {1, 2}, {0, 3}, {3, 2}, {3, 4}, {4, 0}};
        for (int[] e : kenarlar) g.kenarEkle(e[0], e[1]);
        System.out.println("donguVar(0): " + g.donguVar(0, new int[5]));   // true (4 -> 0 geri kenar)
        List<Integer> eksik = kahn(g.komsu);
        System.out.println("Kahn: " + eksik + " (boyut " + eksik.size() + " < 5, topolojik sira yok)");
        System.out.println("DAG'da donguVar(0): " + dag.donguVar(0, new int[6]));  // false
    }
}
