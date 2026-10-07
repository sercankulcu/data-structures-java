import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    // Agirlikli en kisa yol (agirliklar >= 0). adj.get(u): {v, agirlik} ciftleri
    static long[] dijkstra(List<List<int[]>> adj, int s, int[] onceki) {
        long[] d = new long[adj.size()];
        Arrays.fill(d, Long.MAX_VALUE);  d[s] = 0;  Arrays.fill(onceki, -1);
        PriorityQueue<long[]> pq =                            // {dugum, mesafe}
            new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{s, 0});
        while (!pq.isEmpty()) {
            long[] e = pq.poll();  int u = (int) e[0];
            if (e[1] > d[u]) continue;                        // bayat kayit: atla
            for (int[] k : adj.get(u)) {
                int v = k[0];  long alt = d[u] + k[1];        // gevsetme
                if (alt < d[v]) { d[v] = alt; onceki[v] = u; pq.add(new long[]{v, alt}); }
            }
        }
        return d;
    }

    // onceki[] zincirini hedeften kaynaga izleyerek yolu kurar
    static List<Integer> yol(int[] onceki, int s, int t) {
        LinkedList<Integer> y = new LinkedList<>();
        for (int v = t; v != -1; v = onceki[v])
            y.addFirst(v);
        if (y.getFirst() != s) return List.of();              // t ulasilamaz
        return y;                                             // s ... t
    }

    // Harf adli yonsuz agirlikli cizge kurar: "AB4" -> A-B kenari, agirlik 4
    static List<List<int[]>> cizgeKur(int n, String[] kenarlar) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (String k : kenarlar) {
            int u = k.charAt(0) - 'A', v = k.charAt(1) - 'A';
            int w = Integer.parseInt(k.substring(2));
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});                  // yonsuz: iki yonde de
        }
        return adj;
    }

    static void calistir(List<List<int[]>> adj, int s) {
        int n = adj.size();
        int[] onceki = new int[n];
        long[] d = dijkstra(adj, s, onceki);
        for (int t = 0; t < n; t++) {
            StringBuilder sb = new StringBuilder();
            for (int v : yol(onceki, s, t))
                sb.append(sb.length() == 0 ? "" : " -> ").append((char) ('A' + v));
            System.out.println("  " + (char) ('A' + t) + ": d = " + d[t] + "  yol: " + sb);
        }
    }

    public static void main(String[] args) {
        // Adim adim izlenen cizge (gezinme orneginin agirlikli hali)
        String[] g1 = {"AB4", "AF7", "AI3", "BC1", "BE2", "CD7", "CE3",
                       "DG3", "DH5", "EG2", "FG2"};
        System.out.println("dijkstra(A), 9 dugumlu cizge:");
        calistir(cizgeKur(9, g1), 0);                         // H: 16, A -> B -> E -> G -> D -> H

        // "Agirlikli en kisa yol" ve "Yolu geri kurma" slaytlarindaki cizge
        String[] g2 = {"AB4", "AC2", "BC5", "BD10", "CE3", "ED4", "DF11"};
        System.out.println("dijkstra(A), 6 dugumlu cizge:");
        calistir(cizgeKur(6, g2), 0);                         // F: 20, A -> C -> E -> D -> F
    }
}
