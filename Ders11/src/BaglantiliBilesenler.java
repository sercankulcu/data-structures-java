import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaglantiliBilesenler {

    private final int n;
    private final List<List<Integer>> komsu = new ArrayList<>();

    BaglantiliBilesenler(int n) {
        this.n = n;
        for (int i = 0; i < n; i++)
            komsu.add(new ArrayList<>());
    }

    void kenarEkle(int u, int v) {
        komsu.get(u).add(v);
        komsu.get(v).add(u);
    }

    // Her dugume bileseninin numarasini yazar, bilesen sayisini dondurur. O(n + m)
    int bilesenler(int[] bil) {
        Arrays.fill(bil, -1);                                 // -1: henuz bir bilesene atanmadi
        int say = 0;
        for (int s = 0; s < n; s++)
            if (bil[s] == -1) dfs(s, say++, bil);             // yeni bilesen bulundu
        return say;
    }

    // v'den ulasilan tum dugumleri c numarasina boyar
    void dfs(int v, int c, int[] bil) {
        bil[v] = c;
        for (int u : komsu.get(v))
            if (bil[u] == -1) dfs(u, c, bil);
    }

    public static void main(String[] args) {
        BaglantiliBilesenler g = new BaglantiliBilesenler(6);
        g.kenarEkle(0, 1);
        g.kenarEkle(0, 2);
        g.kenarEkle(3, 4);                                    // 5 yalniz bir dugum

        int[] bil = new int[6];
        int say = g.bilesenler(bil);
        System.out.println("Bilesen sayisi: " + say);         // 3
        System.out.println("bil[] = " + Arrays.toString(bil)); // [0, 0, 0, 1, 1, 2]
        System.out.println("1 ile 2 baglantili mi? " + (bil[1] == bil[2]));  // true, O(1)
        System.out.println("2 ile 4 baglantili mi? " + (bil[2] == bil[4]));  // false
    }
}
