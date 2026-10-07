import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class CizgeGezinme {

    private final int n;                                      // dugumler: 0..n-1
    private final List<List<Integer>> komsu = new ArrayList<>();

    CizgeGezinme(int n) {
        this.n = n;
        for (int i = 0; i < n; i++)
            komsu.add(new ArrayList<>());
    }

    // Yonsuz kenar: iki listeye de eklenir
    void kenarEkle(int u, int v) {
        komsu.get(u).add(v);
        komsu.get(v).add(u);
    }

    // Dugum numarasini harfe cevirir (A=0, B=1, ..., I=8)
    static char ad(int v) { return (char) ('A' + v); }

    // Genislik oncelikli arama: kuyruk (FIFO), seviye seviye
    void bfs(int s) {
        boolean[] ziyaret = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        ziyaret[s] = true;  q.add(s);                         // kuyruga girerken isaretle
        while (!q.isEmpty()) {
            int w = q.poll();
            System.out.print(ad(w) + " ");
            for (int u : komsu.get(w))
                if (!ziyaret[u]) {
                    ziyaret[u] = true;                        // ayni dugum kuyruga iki kez girmez
                    q.add(u);
                }
        }
        System.out.println();
    }

    // Derinlik oncelikli arama (ozyinelemeli): yigini cagri yigini tutar
    void dfs(int v, boolean[] ziyaret) {
        ziyaret[v] = true;
        System.out.print(ad(v) + " ");
        for (int u : komsu.get(v))
            if (!ziyaret[u])
                dfs(u, ziyaret);
    }

    // Derinlik oncelikli arama (acik yiginla): yiginin ustu = bulundugumuz dugum
    void dfsYigin(int s) {
        boolean[] ziyaret = new boolean[n];
        Deque<Integer> yigin = new ArrayDeque<>();
        yigin.push(s);  ziyaret[s] = true;
        System.out.print(ad(s) + " ");
        while (!yigin.isEmpty()) {
            int v = yigin.peek();
            Integer yeni = null;
            for (int u : komsu.get(v))
                if (!ziyaret[u]) { yeni = u; break; }         // ilk ziyaret edilmemis komsu
            if (yeni == null) yigin.pop();                    // cikmaz: geri don
            else {
                ziyaret[yeni] = true;
                System.out.print(ad(yeni) + " ");
                yigin.push(yeni);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Slayttaki ornek cizge (komsular alfabetik sirada eklenir)
        CizgeGezinme g = new CizgeGezinme(9);
        String[] kenarlar = {"AB", "AF", "AI", "BC", "BE", "CD", "CE", "DG", "DH", "EG", "FG"};
        for (String k : kenarlar)
            g.kenarEkle(k.charAt(0) - 'A', k.charAt(1) - 'A');

        System.out.print("bfs(A):      ");
        g.bfs(0);                                             // A B F I C E G D H
        System.out.print("dfs(A):      ");
        g.dfs(0, new boolean[9]);                             // A B C D G E F H I
        System.out.println();
        System.out.print("dfsYigin(A): ");
        g.dfsYigin(0);                                        // ozyinelemeli surumle ayni sira

        // Ornek problem: H'den baslat
        System.out.print("bfs(H):      ");
        g.bfs(7);                                             // H D C G B E F A I
        System.out.print("dfs(H):      ");
        g.dfs(7, new boolean[9]);                             // H D C B A F G E I
        System.out.println();
    }
}
