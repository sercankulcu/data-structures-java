public class AyrikKumeDizi {

    private int[] ata; // Her elemanin kendi atasini tutan dizi
    private int[] sira; // Ağaçların derinliklerini tutan dizi

    public AyrikKumeDizi(int boyut) {
        ata = new int[boyut]; // Ata dizisi olusturuluyor
        sira = new int[boyut]; // Sira dizisi olusturuluyor
        for (int i = 0; i < boyut; i++) {
            ata[i] = i; // Baslangicta her eleman kendisinin atasi
            sira[i] = 0; // Tum elemanlarin sirasi 0
        }
    }

    void atalariListele() {
        System.out.println("Kume elemanlari atalari:");
        for (int i = 0; i < ata.length; i++) {
            System.out.print(find(i) + " "); // Her elemanin atasini bul ve yazdir
        }
        System.out.println();
    }

    int find(int x) {
        if (ata[x] != x) {
            // Yol kisaltma islemi
            ata[x] = find(ata[x]);
        }
        return ata[x];
    }

    void union(int x, int y) {
        int kokX = find(x); // X'in kokunu bul
        int kokY = find(y); // Y'nin kokunu bul
        // Agac boyutuna gore birlestirme
        if (kokX != kokY) {
            if (sira[kokX] < sira[kokY]) {
                ata[kokX] = kokY; // Kucuk olan agaci digerine bagla
            } else if (sira[kokX] > sira[kokY]) {
                ata[kokY] = kokX; // Kucuk olan agaci digerine bagla
            } else {
                // Siralar esit ise birini digerine bagla ve sirayi artir
                ata[kokX] = kokY;
                sira[kokY]++;
            }
        }
    }

    public static void main(String[] args) {

        AyrikKumeDizi kume = new AyrikKumeDizi(10);

        kume.atalariListele(); // Baslangictaki atalari listele
        kume.union(0, 1); // 0 ve 1'i birlestir
        kume.union(2, 3); // 2 ve 3'u birlestir
        kume.union(0, 4); // 0 ve 4'u birlestir
        kume.union(5, 4);
        kume.union(6, 7);
        kume.union(8, 9);
        kume.atalariListele(); // Son durumu listele
        kume.union(7, 9);
        kume.atalariListele(); // Son durumu listele
    }
}
