// Bagli gosterimde ikili agac: ozyinelemeli gezinmeler ve agac fonksiyonlari
public class IkiliAgacOzYinelemeli {

    // Agac dugumu: veri + sol ve sag cocuk referansi (yoksa null)
    static class AgacDugumu {
        int veri;
        AgacDugumu sol;
        AgacDugumu sag;

        AgacDugumu(int veri) {
            this.veri = veri; // sol = sag = null
        }
    }

    // Kok basta: kok -> sol -> sag
    public void kokBasta(AgacDugumu kok) {
        if (kok == null) {
            return; // taban durum: bos alt agac
        }
        System.out.print(kok.veri + " ");
        kokBasta(kok.sol);
        kokBasta(kok.sag);
    }

    // Kok ortada: sol -> kok -> sag
    public void kokOrtada(AgacDugumu kok) {
        if (kok == null) {
            return;
        }
        kokOrtada(kok.sol);
        System.out.print(kok.veri + " ");
        kokOrtada(kok.sag);
    }

    // Kok sonda: sol -> sag -> kok
    public void kokSonda(AgacDugumu kok) {
        if (kok == null) {
            return;
        }
        kokSonda(kok.sol);
        kokSonda(kok.sag);
        System.out.print(kok.veri + " ");
    }

    // Dugum sayisi
    int boyut(AgacDugumu k) {
        if (k == null) return 0;
        return 1 + boyut(k.sol) + boyut(k.sag);
    }

    // Yukseklik kenar sayisidir: bos agac -1, tek dugum 0
    int yukseklik(AgacDugumu k) {
        if (k == null) return -1;
        return 1 + Math.max(yukseklik(k.sol), yukseklik(k.sag));
    }

    // Iki cocugu da null olan dugumleri sayar
    int yaprakSayisi(AgacDugumu k) {
        if (k == null) return 0;
        if (k.sol == null && k.sag == null) return 1;
        return yaprakSayisi(k.sol) + yaprakSayisi(k.sag);
    }

    // Bos alt agac Integer.MIN_VALUE dondurur; karsilastirmayi bozmaz
    public int enBuyukBul(AgacDugumu kok) {
        if (kok == null) {
            return Integer.MIN_VALUE;
        }
        int sonuc = kok.veri;
        int sol = enBuyukBul(kok.sol);
        int sag = enBuyukBul(kok.sag);
        if (sol > sonuc)
            sonuc = sol;
        if (sag > sonuc)
            sonuc = sag;
        return sonuc;
    }

    public static void main(String[] args) {
        IkiliAgacOzYinelemeli ia = new IkiliAgacOzYinelemeli();

        // Slayttaki kucuk agac: 9 kokunde, 9 -> (2 -> 4), 3
        AgacDugumu kok = new AgacDugumu(9);
        kok.sol = new AgacDugumu(2);
        kok.sag = new AgacDugumu(3);
        kok.sol.sol = new AgacDugumu(4);

        System.out.print("Kok basta : ");
        ia.kokBasta(kok);   // 9 2 4 3
        System.out.println();
        System.out.print("Kok ortada: ");
        ia.kokOrtada(kok);  // 4 2 9 3
        System.out.println();
        System.out.print("Kok sonda : ");
        ia.kokSonda(kok);   // 4 2 3 9
        System.out.println();

        System.out.println("boyut = " + ia.boyut(kok));               // 4
        System.out.println("yukseklik = " + ia.yukseklik(kok));       // 2
        System.out.println("yaprakSayisi = " + ia.yaprakSayisi(kok)); // 2 (4 ve 3)
        System.out.println("Bos agac: boyut = " + ia.boyut(null)
                + ", yukseklik = " + ia.yukseklik(null));

        // En buyuk deger ornegi: 4 -> (3 -> 5), (7 -> sag 8)
        AgacDugumu kok2 = new AgacDugumu(4);
        kok2.sol = new AgacDugumu(3);
        kok2.sag = new AgacDugumu(7);
        kok2.sol.sol = new AgacDugumu(5);
        kok2.sag.sag = new AgacDugumu(8);
        System.out.println("enBuyukBul = " + ia.enBuyukBul(kok2)); // 8
    }
}
