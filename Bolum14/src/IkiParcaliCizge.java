import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class IkiParcaliCizge {

    private final int n;
    private final List<List<Integer>> komsu = new ArrayList<>();

    IkiParcaliCizge(int n) {
        this.n = n;
        for (int i = 0; i < n; i++)
            komsu.add(new ArrayList<>());
    }

    void kenarEkle(int u, int v) {
        komsu.get(u).add(v);
        komsu.get(v).add(u);
    }

    // BFS ile 2-renkleme: komsuya zit renk ver; ayni renkli iki komsu -> iki parcali degil
    boolean ikiParcaliMi() {
        int[] renk = new int[n];
        Arrays.fill(renk, -1);                                // -1: henuz boyanmadi
        Queue<Integer> q = new ArrayDeque<>();
        for (int s = 0; s < n; s++) {                         // baglantisiz cizgede her bilesen icin
            if (renk[s] != -1) continue;
            renk[s] = 0;  q.add(s);
            while (!q.isEmpty()) {
                int w = q.poll();
                for (int u : komsu.get(w))
                    if (renk[u] == -1) {
                        renk[u] = 1 - renk[w];  q.add(u);
                    } else if (renk[u] == renk[w]) return false;
            }
        }
        return true;
    }

    // k dugumlu dongu: 0-1-2-...-(k-1)-0
    static IkiParcaliCizge dongu(int k) {
        IkiParcaliCizge g = new IkiParcaliCizge(k);
        for (int i = 0; i < k; i++) g.kenarEkle(i, (i + 1) % k);
        return g;
    }

    public static void main(String[] args) {
        System.out.println("6'li dongu iki parcali mi? " + dongu(6).ikiParcaliMi());  // true (cift dongu)
        System.out.println("Ucgen iki parcali mi?      " + dongu(3).ikiParcaliMi());  // false
        System.out.println("C5 iki parcali mi?         " + dongu(5).ikiParcaliMi());  // false (tek dongu)
    }
}
