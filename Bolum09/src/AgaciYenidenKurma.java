// Kok basta ve kok ortada dizilerinden ikili agaci yeniden kurma
// Kok basta sirasinin ilk elemani koktur; kok ortada dizisinde kokun
// solundakiler sol alt agaci, sagindakiler sag alt agaci olusturur.
public class AgaciYenidenKurma {

    static class AgacDugumu {
        int veri;
        AgacDugumu sol;
        AgacDugumu sag;

        AgacDugumu(int veri) {
            this.veri = veri;
        }
    }

    int p = 0; // pre[] icinde siradaki kok

    // in[bas..son] araligindaki dugumlerden alt agaci kurar
    AgacDugumu kur(int[] pre, int[] in, int bas, int son) {
        if (bas > son) return null;       // bos aralik
        AgacDugumu k = new AgacDugumu(pre[p++]);
        int m = bas;
        while (in[m] != k.veri) m++;      // kokun kok ortadaki yeri
        k.sol = kur(pre, in, bas, m - 1); // soldakiler
        k.sag = kur(pre, in, m + 1, son); // sagdakiler
        return k;
    }

    // Kurulan agaci denetlemek icin kok sonda yazdirilir
    static void kokSonda(AgacDugumu k) {
        if (k == null) return;
        kokSonda(k.sol);
        kokSonda(k.sag);
        System.out.print(k.veri + " ");
    }

    // Agaci parantezli bicimde yazar: kok(sol, sag), bos alt agac '-'
    static void yapiYaz(AgacDugumu k) {
        if (k == null) {
            System.out.print("-");
            return;
        }
        System.out.print(k.veri);
        if (k.sol == null && k.sag == null) return;
        System.out.print("(");
        yapiYaz(k.sol);
        System.out.print(", ");
        yapiYaz(k.sag);
        System.out.print(")");
    }

    public static void main(String[] args) {
        // Slayttaki ornek (degerler tekrarsiz olmali)
        int[] pre = {1, 2, 4, 5, 3, 6};
        int[] in  = {4, 2, 5, 1, 3, 6};

        AgaciYenidenKurma kurucu = new AgaciYenidenKurma();
        AgacDugumu kok = kurucu.kur(pre, in, 0, in.length - 1);

        System.out.print("Kurulan agac: ");
        yapiYaz(kok);       // 1(2(4, 5), 3(-, 6))
        System.out.println();
        System.out.print("Kok sonda   : ");
        kokSonda(kok);      // 4 5 2 6 3 1
        System.out.println();
    }
}
