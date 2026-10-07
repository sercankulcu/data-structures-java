import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AgirliksizEnKisaYol {

    // Cizge: dugum -> komsu listesi (dugumler 1'den baslayarak numaralanmis)
    static Map<Integer, List<Integer>> cizge = new HashMap<>();

    // BFS ile tek kaynakli en kisa yol: mesafe = kenar sayisi. O(n + m)
    static Map<Integer, Integer> enKisaYol(int s) {
        final int INF = Integer.MAX_VALUE;                    // sonsuz = "ziyaret edilmedi"
        Map<Integer, Integer> mesafe = new HashMap<>();
        for (int v : cizge.keySet())
            mesafe.put(v, INF);
        mesafe.put(s, 0);
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int w = q.poll();
            for (int u : cizge.get(w))
                if (mesafe.get(u) == INF) {                   // ilk bulunan mesafe en kisadir
                    mesafe.put(u, mesafe.get(w) + 1);
                    q.offer(u);
                }
        }
        return mesafe;
    }

    public static void main(String[] args) {
        // Slayttaki yonsuz cizge (her kenar iki listede de var)
        cizge.put(1, List.of(2, 3, 4));
        cizge.put(2, List.of(1, 5));
        cizge.put(3, List.of(1, 8));
        cizge.put(4, List.of(1, 6));
        cizge.put(5, List.of(2, 8));
        cizge.put(6, List.of(4, 7));
        cizge.put(7, List.of(6, 8));
        cizge.put(8, List.of(3, 5, 7));

        int s = 1;
        Map<Integer, Integer> mesafe = enKisaYol(s);
        // Beklenen: 1:0  2:1  3:1  4:1  5:2  6:2  7:3  8:2
        for (int v = 1; v <= 8; v++)
            System.out.println("Dugum " + v + " ile " + s + " arasindaki mesafe: " + mesafe.get(v));
    }
}
