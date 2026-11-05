import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {

    // Tembel Prim: agac tek dugumden buyur; her adimda agactan disari giden en hafif kenar eklenir.
    // adj.get(u): {v, agirlik} ciftleri. Dondurulen liste: MST kenarlari {u, v, w}.
    // Kenar yalniz agaca ilk giren ucundan kuyruga eklenir: en cok m kayit, O(m log m).
    static List<int[]> prim(List<List<int[]>> adj, int s) {
        int n = adj.size();
        boolean[] agacta = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2]));  // {u, v, w}
        List<int[]> T = new ArrayList<>();
        agacta[s] = true;
        for (int[] e : adj.get(s)) pq.add(new int[]{s, e[0], e[1]});
        while (!pq.isEmpty() && T.size() < n - 1) {
            int[] e = pq.poll();               // en hafif aday
            if (agacta[e[1]]) continue;        // bayat: dongu kurar
            agacta[e[1]] = true;
            T.add(e);
            for (int[] f : adj.get(e[1]))
                if (!agacta[f[0]]) pq.add(new int[]{e[1], f[0], f[1]});
        }
        return T; // cizge bagli degilse: yalniz s'nin bileseninin kapsayan agaci
    }

    static char ad(int i) {
        return (char) ('A' + i); // A=0 ... J=9
    }

    // Yonsuz kenar: iki listeye de eklenir
    static void kenar(List<List<int[]>> adj, String uv, int w) {
        int u = uv.charAt(0) - 'A', v = uv.charAt(1) - 'A';
        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w});
    }

    static void yazdir(List<int[]> T) {
        int toplam = 0;
        for (int[] e : T) {
            System.out.println(ad(e[0]) + "-" + ad(e[1]) + " (" + e[2] + ")");
            toplam += e[2];
        }
        System.out.println("Kenar sayisi: " + T.size() + ", toplam agirlik: " + toplam);
    }

    public static void main(String[] args) {
        // Kruskal.java ile ayni cizge: 10 dugum (A..J), 18 kenar
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < 10; i++) adj.add(new ArrayList<>());
        String[] uv = {"AB", "AD", "AE", "BC", "BD", "CH", "CI", "CJ", "DE",
                       "DF", "DG", "DH", "EF", "FG", "GH", "GI", "HI", "IJ"};
        int[] w =     { 5,    4,    1,    4,    2,    4,    1,    2,    2,
                        5,    11,   2,    1,    7,    1,    4,    6,    0};
        for (int i = 0; i < uv.length; i++) kenar(adj, uv[i], w[i]);

        System.out.println("A'dan baslayan Prim:");
        yazdir(prim(adj, 0));   // 9 kenar, toplam 14 (Kruskal ile ayni toplam)

        System.out.println("J'den baslayan Prim:");
        yazdir(prim(adj, 9));   // J-I, I-C, sonra 4 agirlikli bir kenar; toplam yine 14
        // C-B ile C-H esit agirliklidir (4); hangisinin once cikacagini PriorityQueue'nun
        // ic duzeni belirler. Esit agirliklar yuzunden MST tek olmayabilir, toplam her zaman aynidir.
    }
}
