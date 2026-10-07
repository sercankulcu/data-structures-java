import java.util.ArrayList;
import java.util.List;

// Genel agac: bir dugumun cocuk sayisina sinir yoktur
// 1) Cocuk listesi gosterimi ile derinlik ve yukseklik
// 2) Ilk cocuk - sonraki kardes gosterimi (genel agac ikili agac gibi saklanir)
public class GenelAgac {

    // Cocuk listesi gosterimi; derinlik icin ebeveyn referansi da tutulur
    static class GenelDugum {
        String veri;
        GenelDugum ebeveyn;
        List<GenelDugum> cocuklar = new ArrayList<>();

        GenelDugum(String veri) {
            this.veri = veri;
        }

        // Yeni cocuk ekler ve onu dondurur
        GenelDugum cocukEkle(String veri) {
            GenelDugum c = new GenelDugum(veri);
            c.ebeveyn = this;
            cocuklar.add(c);
            return c;
        }
    }

    // Ilk cocuk - sonraki kardes gosterimi: her dugumde yine iki referans
    static class KardesDugum {
        String veri;
        KardesDugum ilkCocuk;       // asagi
        KardesDugum sonrakiKardes;  // saga

        KardesDugum(String veri) {
            this.veri = veri;
        }
    }

    static boolean kokMu(GenelDugum p) {
        return p.ebeveyn == null;
    }

    // Derinlik: kokten p'ye kadar olan kenar sayisi (yukari cikilir)
    static int derinlik(GenelDugum p) {
        if (kokMu(p)) return 0;
        return 1 + derinlik(p.ebeveyn);
    }

    // Yukseklik: p'den en uzak yapraga kadar olan kenar sayisi (asagi inilir)
    static int yukseklik(GenelDugum p) {
        int h = 0;
        for (GenelDugum c : p.cocuklar)
            h = Math.max(h, 1 + yukseklik(c));
        return h;
    }

    // Kok basta gezinme; girinti dugumun derinligini gosterir
    static void yazdir(GenelDugum p) {
        System.out.println("  ".repeat(derinlik(p)) + p.veri);
        for (GenelDugum c : p.cocuklar)
            yazdir(c);
    }

    // Cocuk listesi gosteriminden ilk cocuk - sonraki kardes gosterimine cevirir
    static KardesDugum kardeseCevir(GenelDugum p) {
        KardesDugum k = new KardesDugum(p.veri);
        KardesDugum onceki = null;
        for (GenelDugum c : p.cocuklar) {
            KardesDugum yeni = kardeseCevir(c);
            if (onceki == null)
                k.ilkCocuk = yeni;            // ilk cocuk asagi baglanir
            else
                onceki.sonrakiKardes = yeni;  // digerleri saga zincirlenir
            onceki = yeni;
        }
        return k;
    }

    // Kardes gosteriminde kok basta: once dugum, sonra cocuklari, sonra kardesleri
    static void kokBasta(KardesDugum k) {
        if (k == null) return;
        System.out.print(k.veri + " ");
        kokBasta(k.ilkCocuk);
        kokBasta(k.sonrakiKardes);
    }

    public static void main(String[] args) {
        // A'nin cocuklari B ve C; B'nin cocuklari D ve E; E'nin cocugu G; C'nin cocugu F
        GenelDugum a = new GenelDugum("A");
        GenelDugum b = a.cocukEkle("B");
        GenelDugum c = a.cocukEkle("C");
        b.cocukEkle("D");
        GenelDugum e = b.cocukEkle("E");
        c.cocukEkle("F");
        GenelDugum g = e.cocukEkle("G");

        System.out.println("Agac (girinti = derinlik):");
        yazdir(a);

        System.out.println("derinlik(G) = " + derinlik(g));  // 3: G -> E -> B -> A
        System.out.println("yukseklik(B) = " + yukseklik(b)); // 2: B -> E -> G
        System.out.println("yukseklik(A) = " + yukseklik(a)); // agacin yuksekligi: 3

        System.out.print("Ilk cocuk - sonraki kardes, kok basta: ");
        kokBasta(kardeseCevir(a)); // A B D E G C F
        System.out.println();
    }
}
