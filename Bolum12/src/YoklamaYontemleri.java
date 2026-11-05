// Acik adreslemede uc yoklama yontemi: dogrusal, karesel ve cift hash.
// Ayni anahtarlar N = 13 yuvali bos tabloya sirayla eklenir.
public class YoklamaYontemleri {

    // Dogrusal yoklama: h, h+1, h+2, ...
    static int dogrusalYerlestir(Integer[] tablo, int k) {
        int N = tablo.length;
        int h = Math.floorMod(k, N);          // ev yuvasi
        for (int i = 0; i < N; i++) {
            int j = (h + i) % N;
            if (tablo[j] == null) {           // bos mu?
                tablo[j] = k;
                return j;
            }                                 // dolu: i++
        }
        return -1;                            // yer bulunamadi
    }

    // Karesel yoklama: h, h+1, h+4, h+9, ... (her yuvayi denemeyebilir)
    static int kareselYerlestir(Integer[] tablo, int k) {
        int N = tablo.length;
        int h = Math.floorMod(k, N);
        for (int i = 0; i < N; i++) {
            int j = (h + i * i) % N;
            if (tablo[j] == null) {
                tablo[j] = k;
                return j;
            }
        }
        return -1;
    }

    // Cift hash: h, h+d, h+2d, ...  d = 7 - k mod 7 (1..7, asla 0 degil)
    static int ciftHashYerlestir(Integer[] tablo, int k) {
        int N = tablo.length;
        int h = Math.floorMod(k, N);
        int d = 7 - Math.floorMod(k, 7);      // adim
        for (int i = 0; i < N; i++) {
            int j = (h + i * d) % N;
            if (tablo[j] == null) {
                tablo[j] = k;
                return j;
            }
        }
        return -1;
    }

    static void yazdir(Integer[] tablo) {
        StringBuilder satir = new StringBuilder(" ");
        for (int j = 0; j < tablo.length; j++) {
            satir.append(" ").append(j).append(":").append(tablo[j] == null ? "-" : tablo[j]);
        }
        System.out.println(satir);
    }

    public static void main(String[] args) {
        int[] anahtarlar = {18, 41, 22, 44, 59, 32, 31, 73};
        String[] adlar = {"Dogrusal yoklama", "Karesel yoklama", "Cift hash"};

        for (int y = 0; y < 3; y++) {
            Integer[] tablo = new Integer[13];
            StringBuilder yuvalar = new StringBuilder();
            for (int k : anahtarlar) {
                int j = y == 0 ? dogrusalYerlestir(tablo, k)
                      : y == 1 ? kareselYerlestir(tablo, k)
                      : ciftHashYerlestir(tablo, k);
                yuvalar.append(" ").append(k).append("->").append(j == -1 ? "yer yok" : String.valueOf(j));
            }
            System.out.println(adlar[y] + ":" + yuvalar);
            yazdir(tablo);
        }
    }
}
