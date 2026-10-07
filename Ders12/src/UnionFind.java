import java.util.Arrays;

public class UnionFind {

    private final int[] ebeveyn; // ebeveyn[i]: i'nin ebeveyni; kok kendini gosterir
    private final int[] sira;    // sira[k]: kok k'nin rutbesi (yukseklik ust siniri)

    // makeSet: her oge kendi kumesinin koku, rutbe 0
    public UnionFind(int n) {
        ebeveyn = new int[n];
        sira = new int[n];
        for (int i = 0; i < n; i++) ebeveyn[i] = i;
    }

    // Ozyinelemeli find + yol kisaltma: yoldaki dugumler dogrudan koke baglanir
    public int find(int x) {
        if (ebeveyn[x] != x) ebeveyn[x] = find(ebeveyn[x]);
        return ebeveyn[x];
    }

    // Dongu ile find + yol kisaltma (cok buyuk n'de yigin tasmasi olmaz)
    public int findDongu(int x) {
        int kok = x;
        while (ebeveyn[kok] != kok) kok = ebeveyn[kok]; // 1. tur: koku bul
        while (ebeveyn[x] != kok) {                     // 2. tur: yol kisaltma
            int sonraki = ebeveyn[x];
            ebeveyn[x] = kok;
            x = sonraki;
        }
        return kok;
    }

    // Rutbeye gore birlestirme. Birlesme olduysa true; zaten ayni kumedeyse false
    public boolean union(int x, int y) {
        int kx = find(x), ky = find(y);
        if (kx == ky) return false;
        if (sira[kx] < sira[ky]) ebeveyn[kx] = ky;      // alcak agac yuksege baglanir
        else if (sira[kx] > sira[ky]) ebeveyn[ky] = kx;
        else { ebeveyn[kx] = ky; sira[ky]++; }          // esit: kx, ky'nin altina; rutbe artar
        return true;
    }

    // Union-Find ile baglantili bilesen sayisi: O(n + m * alfa(n))
    static int bilesenSay(int n, int[][] kenar) {
        UnionFind uf = new UnionFind(n);
        int say = n;            // n tekil kume
        for (int[] e : kenar)
            if (uf.union(e[0], e[1]))
                say--;          // iki bilesen birlesti
        return say;
    }

    void yazdir() {
        System.out.println("ebeveyn = " + Arrays.toString(ebeveyn) + ", sira = " + Arrays.toString(sira));
    }

    public static void main(String[] args) {
        // 1) Kod izleme: 6 oge
        UnionFind uf = new UnionFind(6);
        uf.union(0, 1);  // esit rutbe: ebeveyn[0] = 1, sira[1] = 1
        uf.union(2, 3);  // ebeveyn[2] = 3, sira[3] = 1
        uf.union(0, 2);  // kokler 1 ve 3, esit: ebeveyn[1] = 3, sira[3] = 2
        uf.yazdir();     // ebeveyn = [1, 3, 3, 3, 4, 5]
        uf.union(4, 0);  // find(0) yolu kisaltir: ebeveyn[0] = 3; ebeveyn[4] = 3
        uf.yazdir();     // ebeveyn = [3, 3, 3, 3, 3, 5]
        System.out.println("union(1, 4) = " + uf.union(1, 4)); // false: zaten ayni kumede

        // 2) Ornek problem: union(0,1), union(2,3), union(1,3), union(4,5)
        UnionFind p = new UnionFind(6);
        p.union(0, 1);
        p.union(2, 3);
        p.union(1, 3);
        p.union(4, 5);
        p.yazdir();      // ebeveyn = [1, 3, 3, 3, 5, 5]: iki kume
        System.out.println("find(0) = " + p.find(0)); // 3

        // 3) Yinelemeli find: zincir 4 -> 3 -> 2 -> 1 -> 0
        UnionFind z = new UnionFind(5);
        for (int i = 1; i < 5; i++) z.ebeveyn[i] = i - 1; // kotu birlestirmelerin sonucu
        System.out.println("\nfindDongu(4) = " + z.findDongu(4)); // 0
        z.yazdir();      // ebeveyn = [0, 0, 0, 0, 0]

        // 4) Baglantili bilesen sayisi: 7 dugum, 5 kenar
        int[][] kenar = {{0, 1}, {1, 2}, {3, 4}, {0, 2}, {5, 6}};
        System.out.println("\nBilesen sayisi: " + bilesenSay(7, kenar)); // 3
    }
}
