// Ikili agacin dizi ile gosterimi (1 tabanli: a[0] bos birakilir)
// Dugumler seviye seviye, soldan saga diziye yerlestirilir.
// Indis i'deki dugum icin: sol cocuk 2i, sag cocuk 2i + 1, ebeveyn i / 2
public class AgacDizi {

    private final char[] a; // a[1..n] dugumleri tutar
    private final int n;    // dugum sayisi

    public AgacDizi(char[] a, int n) {
        this.a = a;
        this.n = n;
    }

    int sol(int i)     { return 2 * i; }
    int sag(int i)     { return 2 * i + 1; }
    int ebeveyn(int i) { return i / 2; }

    // Cocuk indisi n'yi asarsa o cocuk yoktur
    boolean varMi(int i) { return i >= 1 && i <= n; }

    // i'deki dugumden koke kadar olan yolu yazdirir
    void kokeYol(int i) {
        while (i >= 1) {
            System.out.print(a[i] + " ");
            i = ebeveyn(i);
        }
        System.out.println();
    }

    // Kok basta gezinme: referans yerine indis aritmetigi kullanilir
    void kokBasta(int i) {
        if (!varMi(i)) {
            return;
        }
        System.out.print(a[i] + " ");
        kokBasta(sol(i));
        kokBasta(sag(i));
    }

    void kokOrtada(int i) {
        if (!varMi(i)) {
            return;
        }
        kokOrtada(sol(i));
        System.out.print(a[i] + " ");
        kokOrtada(sag(i));
    }

    void kokSonda(int i) {
        if (!varMi(i)) {
            return;
        }
        kokSonda(sol(i));
        kokSonda(sag(i));
        System.out.print(a[i] + " ");
    }

    // Seviye sirasi: dizinin kendisi zaten seviye sirasidir
    void seviyeSirasi() {
        for (int i = 1; i <= n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    // Dugumun cocuklarini yazdirir; yoksa yaprak oldugunu soyler
    void cocuklariYaz(int i) {
        System.out.print(a[i] + " (i = " + i + "): ");
        if (!varMi(sol(i))) {
            System.out.println("cocugu yok (yaprak), cunku 2i = " + sol(i) + " > n = " + n);
            return;
        }
        System.out.print("sol = " + a[sol(i)]);
        if (varMi(sag(i))) {
            System.out.print(", sag = " + a[sag(i)]);
        }
        System.out.println(", ebeveyn = " + (i == 1 ? "yok (kok)" : String.valueOf(a[ebeveyn(i)])));
    }

    public static void main(String[] args) {
        // Slayttaki tam ikili agac: A B C D E F G H I (a[0] kullanilmaz)
        char[] a = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        AgacDizi agac = new AgacDizi(a, 9);

        System.out.println("sol(2) = " + agac.sol(2) + " -> " + a[agac.sol(2)]);
        System.out.println("sag(2) = " + agac.sag(2) + " -> " + a[agac.sag(2)]);
        System.out.println("ebeveyn(2) = " + agac.ebeveyn(2) + " -> " + a[agac.ebeveyn(2)]);
        agac.cocuklariYaz(4); // D: cocuklari H ve I
        agac.cocuklariYaz(5); // E: 10 ve 11 > 9, yaprak

        System.out.print("I'dan koke yol: ");
        agac.kokeYol(9);

        System.out.print("Kok basta : ");
        agac.kokBasta(1);
        System.out.println();
        System.out.print("Kok ortada: ");
        agac.kokOrtada(1);
        System.out.println();
        System.out.print("Kok sonda : ");
        agac.kokSonda(1);
        System.out.println();
        System.out.print("Seviye sirasi: ");
        agac.seviyeSirasi();
    }
}
