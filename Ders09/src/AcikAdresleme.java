// Acik adresleme (dogrusal yoklama) ile hash tablosu.
// Silinen yuvaya null yerine SILINDI (DEFUNCT) isareti konur; boylece yoklama zinciri kopmaz.
public class AcikAdresleme {

    // Yuvada saklanan anahtar-deger cifti
    private static class Giris {
        private final int anahtar;
        private String deger;

        Giris(int anahtar, String deger) {
            this.anahtar = anahtar;
            this.deger = deger;
        }
    }

    private static final Giris SILINDI = new Giris(0, null); // silindi isareti (tek nesne)

    private final Giris[] tablo;
    private final int N;          // yuva sayisi
    private int buyukluk;         // tablodaki cift sayisi

    public AcikAdresleme(int N) {
        this.N = N;
        this.tablo = new Giris[N];
    }

    // Arama ve eklemenin ortak adimi.
    // Bulunursa yuvanin indisi (>= 0); bulunamazsa -(uygun + 1): uygun = eklenecek ilk bos/silindi yuva.
    private int yuvaBul(int k) {
        int h = Math.floorMod(k, N), uygun = -1;
        for (int i = 0; i < N; i++) {
            int j = (h + i) % N;              // dogrusal yoklama
            if (tablo[j] == null || tablo[j] == SILINDI) {
                if (uygun == -1) uygun = j;   // ilk uygun yuva korunur
                if (tablo[j] == null) break;  // gercekten bos: k bundan otede olamaz
            } else if (tablo[j].anahtar == k) {
                return j;                     // bulundu
            }
        }
        if (uygun == -1) {
            return -(N + 1);  // tablo tamamen dolu: -(uygun + 1) burada 0 verirdi ve "bulundu" sanilirdi
        }
        return -(uygun + 1);
    }

    public String getir(int k) {
        int j = yuvaBul(k);
        return j >= 0 ? tablo[j].deger : null;
    }

    public void yerlestir(int k, String deger) {
        int j = yuvaBul(k);
        if (j >= 0) {                 // anahtar var: guncelle
            tablo[j].deger = deger;
            return;
        }
        int yer = -(j + 1);
        if (yer == N) {
            throw new IllegalStateException("Tablo dolu, " + k + " eklenemedi");
        }
        tablo[yer] = new Giris(k, deger);
        buyukluk++;
    }

    public String sil(int k) {
        int j = yuvaBul(k);
        if (j < 0) {
            return null;              // anahtar yok
        }
        String eski = tablo[j].deger;
        tablo[j] = SILINDI;           // null DEGIL: yoklama zinciri korunur
        buyukluk--;
        return eski;
    }

    public int getBuyukluk() {
        return buyukluk;
    }

    // Tabloyu yuva yuva yazdir: "-" bos, "X" silindi
    public void yazdir() {
        StringBuilder satir = new StringBuilder(" ");
        for (int j = 0; j < N; j++) {
            String hucre = tablo[j] == null ? "-" : tablo[j] == SILINDI ? "X" : String.valueOf(tablo[j].anahtar);
            satir.append(" ").append(j).append(":").append(hucre);
        }
        System.out.println(satir);
    }

    public static void main(String[] args) {
        AcikAdresleme t = new AcikAdresleme(13);
        for (int k : new int[]{18, 41, 22, 44, 59, 32, 31, 73}) {
            t.yerlestir(k, "d" + k);
        }
        System.out.println("Dogrusal yoklama sonrasi tablo (N = 13):");
        t.yazdir();

        System.out.println("sil(44) = " + t.sil(44));
        t.yazdir();
        System.out.println("getir(32) = " + t.getir(32) + "  (6'daki X atlandi, 8'de bulundu)");
        System.out.println("getir(44) = " + t.getir(44));

        t.yerlestir(57, "d57");       // 5..11 dolu, 12 bos: ilk uygun yuva 6'ya yerlesir
        System.out.println("yerlestir(57) sonrasi:");
        t.yazdir();
        System.out.println("buyukluk = " + t.getBuyukluk());

        // Tamamen dolu tablo: dongu N adimda biter, sonsuz donguye girmez
        AcikAdresleme dolu = new AcikAdresleme(3);
        dolu.yerlestir(1, "a");
        dolu.yerlestir(2, "b");
        dolu.yerlestir(3, "c");
        System.out.println("Dolu tabloda getir(4) = " + dolu.getir(4));
        try {
            dolu.yerlestir(4, "d");
        } catch (IllegalStateException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
}
