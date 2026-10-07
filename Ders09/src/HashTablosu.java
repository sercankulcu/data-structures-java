// Ayri zincirleme ile hash tablosu: her kova bir bagli listenin ilk dugumunu tutar.
// Yuk faktoru esigi asilinca tablo yaklasik iki katina buyutulur.
public class HashTablosu {

    private static final double YUK_ESIGI = 0.75; // Java HashMap ile ayni esik

    private HashDugumu[] kovalar;   // zincirlerin ilk dugumleri
    private int kovaSayisi, buyukluk;

    public HashTablosu(int kapasite) {
        this.kovaSayisi = kapasite;
        this.kovalar = new HashDugumu[kapasite];
        this.buyukluk = 0;
    }

    // Tek yonlu bagli liste dugumu: anahtar + deger + sonraki
    private static class HashDugumu {
        private Integer anahtar;
        private String deger;
        private HashDugumu sonraki;

        public HashDugumu(Integer anahtar, String deger) {
            this.anahtar = anahtar;
            this.deger = deger;
        }
    }

    public int getBuyukluk() {
        return buyukluk;
    }

    public boolean bosMu() {
        return buyukluk == 0;
    }

    // Kova indisi: anahtar % kovalar.length negatif olabilir, floorMod her zaman 0..N-1 verir.
    // Math.abs(h) % N de guvenli degildir: Math.abs(Integer.MIN_VALUE) negatiftir.
    private int getKovaIndisi(Integer anahtar) {
        int h = anahtar.hashCode();  // Integer icin sayinin kendisi
        return Math.floorMod(h, kovalar.length);
    }

    public void yerlestir(Integer anahtar, String deger) {
        int kovaIndisi = getKovaIndisi(anahtar);
        HashDugumu ilk = kovalar[kovaIndisi];
        while (ilk != null) {    // anahtar zincirde var mi?
            if (ilk.anahtar.equals(anahtar)) {
                ilk.deger = deger;   // varsa yalnizca degeri guncelle
                return;
            }
            ilk = ilk.sonraki;
        }
        buyukluk++;
        HashDugumu dugum = new HashDugumu(anahtar, deger);
        dugum.sonraki = kovalar[kovaIndisi];
        kovalar[kovaIndisi] = dugum;   // zincirin basina ekle: O(1)

        // Yuk faktoru (lambda = n / N) esigi asildiysa tabloyu buyut
        double yukFaktoru = (double) buyukluk / kovaSayisi;
        if (yukFaktoru > YUK_ESIGI) {
            System.out.printf("lambda = %d/%d = %.2f > %.2f -> yeniden boyutlandir%n",
                    buyukluk, kovaSayisi, yukFaktoru, YUK_ESIGI);
            yazdir();
            yenidenBoyutlandir();
        }
    }

    public String getir(Integer anahtar) {
        int kovaIndisi = getKovaIndisi(anahtar);
        HashDugumu ilk = kovalar[kovaIndisi];
        while (ilk != null) {   // yalnizca bir kova taranir
            if (ilk.anahtar.equals(anahtar)) {
                return ilk.deger;        // bulundu
            }
            ilk = ilk.sonraki;
        }
        return null;                     // anahtar yok
    }

    public String sil(Integer anahtar) {
        int kovaIndisi = getKovaIndisi(anahtar);
        HashDugumu ilk = kovalar[kovaIndisi];
        HashDugumu onceki = null;        // dugumu koparmak icin onceki gerekir
        while (ilk != null && !ilk.anahtar.equals(anahtar)) {
            onceki = ilk;
            ilk = ilk.sonraki;
        }
        if (ilk == null) {
            return null;                 // anahtar yok
        }
        buyukluk--;
        if (onceki != null) {            // ortadan / sondan
            onceki.sonraki = ilk.sonraki;
        } else {                         // bastan
            kovalar[kovaIndisi] = ilk.sonraki;
        }
        return ilk.deger;
    }

    // Yeni dizi (2N + 1 kova) ayrilir, her cift yeniden hash'lenir: O(n)
    private void yenidenBoyutlandir() {
        HashDugumu[] eski = kovalar;
        int yeniN = 2 * eski.length + 1;
        kovalar = new HashDugumu[yeniN];
        buyukluk = 0;                    // yerlestir her cifti yeniden sayar
        kovaSayisi = yeniN;              // unutulursa yuk faktoru yanlis hesaplanir
        for (HashDugumu ilk : eski) {    // her eski kova
            while (ilk != null) {
                yerlestir(ilk.anahtar, ilk.deger);
                ilk = ilk.sonraki;
            }
        }
    }

    // Her kovayi zinciriyle birlikte yazdir
    public void yazdir() {
        for (int i = 0; i < kovalar.length; i++) {
            StringBuilder satir = new StringBuilder("  kova " + i + ":");
            for (HashDugumu d = kovalar[i]; d != null; d = d.sonraki) {
                satir.append(" ").append(d.anahtar).append("=").append(d.deger);
                if (d.sonraki != null) {
                    satir.append(" ->");
                }
            }
            System.out.println(satir);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Ayri zincirleme, N = 10 ===");
        HashTablosu tablo = new HashTablosu(10);
        tablo.yerlestir(105, "Murat");
        tablo.yerlestir(21, "Leyla");
        tablo.yerlestir(41, "Sena");     // 21 ile ayni kova (1): cakisma
        tablo.yerlestir(21, "Ali");      // guncelleme: "Leyla" -> "Ali"
        tablo.yazdir();
        System.out.println("buyukluk = " + tablo.getBuyukluk());
        System.out.println("getKovaIndisi(-7) = " + tablo.getKovaIndisi(-7)
                + "  (oysa -7 % 10 = " + (-7 % 10) + ")");

        System.out.println("getir(105) = " + tablo.getir(105));
        System.out.println("getir(21)  = " + tablo.getir(21));
        System.out.println("getir(88)  = " + tablo.getir(88));

        System.out.println("sil(21)  = " + tablo.sil(21));   // zincirin ortasindan
        System.out.println("sil(105) = " + tablo.sil(105));  // zincirin basindan
        System.out.println("sil(88)  = " + tablo.sil(88));   // yok
        System.out.println("buyukluk = " + tablo.getBuyukluk() + ", bos mu? " + tablo.bosMu());

        System.out.println();
        System.out.println("=== Yeniden boyutlandirma, N = 5 ===");
        HashTablosu kucuk = new HashTablosu(5);
        kucuk.yerlestir(12, "Ece");
        kucuk.yerlestir(7, "Can");
        kucuk.yerlestir(20, "Ada");
        kucuk.yerlestir(33, "Efe");      // lambda = 4/5 = 0.80 > 0.75
        System.out.println("Yeni tablo (N = 11), buyukluk = " + kucuk.getBuyukluk() + ":");
        kucuk.yazdir();
        System.out.println("getir(12) = " + kucuk.getir(12));
    }
}
