import java.util.ArrayDeque;
import java.util.Deque;

// Ifade agaci: ic dugumler islec, yapraklar islenen (sayi) tutar
public class IfadeAgaci {

    static class IfadeDugumu {
        char islec;       // ic dugum: '+', '-', '*', '/'
        int sayi;         // yaprak: islenen
        IfadeDugumu sol;
        IfadeDugumu sag;

        // Yaprak dugum
        IfadeDugumu(int sayi) {
            this.sayi = sayi;
        }

        // Ic dugum: islec ve iki alt ifade
        IfadeDugumu(char islec, IfadeDugumu sol, IfadeDugumu sag) {
            this.islec = islec;
            this.sol = sol;
            this.sag = sag;
        }
    }

    // Deger kok sonda mantigiyla hesaplanir: once iki alt agac, sonra islec
    static int hesapla(IfadeDugumu d) {
        if (d.sol == null && d.sag == null)
            return d.sayi;               // yaprak: islenen
        int a = hesapla(d.sol);
        int b = hesapla(d.sag);
        switch (d.islec) {               // ic dugum: islec
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default:  return a / b;
        }
    }

    static boolean yaprakMi(IfadeDugumu d) {
        return d.sol == null && d.sag == null;
    }

    // Kok basta: onek (prefix) gosterim
    static void kokBasta(IfadeDugumu d) {
        if (d == null) return;
        System.out.print((yaprakMi(d) ? String.valueOf(d.sayi) : String.valueOf(d.islec)) + " ");
        kokBasta(d.sol);
        kokBasta(d.sag);
    }

    // Kok ortada: ara ek (infix); her alt ifade paranteze alinir, yoksa oncelik kaybolur
    static void kokOrtada(IfadeDugumu d) {
        if (d == null) return;
        if (yaprakMi(d)) {
            System.out.print(d.sayi);
            return;
        }
        System.out.print("(");
        kokOrtada(d.sol);
        System.out.print(" " + d.islec + " ");
        kokOrtada(d.sag);
        System.out.print(")");
    }

    // Kok sonda: sonek (postfix) gosterim
    static void kokSonda(IfadeDugumu d) {
        if (d == null) return;
        kokSonda(d.sol);
        kokSonda(d.sag);
        System.out.print((yaprakMi(d) ? String.valueOf(d.sayi) : String.valueOf(d.islec)) + " ");
    }

    // Bosluklarla ayrilmis sonek ifadeden agac kurar (Bolum 7: yigin ile postfix)
    static IfadeDugumu sonektenKur(String ifade) {
        Deque<IfadeDugumu> yigin = new ArrayDeque<>();
        for (String parca : ifade.trim().split("\\s+")) {
            if (parca.length() == 1 && "+-*/".indexOf(parca.charAt(0)) >= 0) {
                IfadeDugumu sag = yigin.pop(); // once sag islenen cikar
                IfadeDugumu sol = yigin.pop();
                yigin.push(new IfadeDugumu(parca.charAt(0), sol, sag));
            } else {
                yigin.push(new IfadeDugumu(Integer.parseInt(parca)));
            }
        }
        return yigin.pop(); // yiginda kalan tek dugum koktur
    }

    static void yazdir(IfadeDugumu kok) {
        System.out.print("Kok basta (onek) : ");
        kokBasta(kok);
        System.out.println();
        System.out.print("Kok ortada (ara ek): ");
        kokOrtada(kok);
        System.out.println();
        System.out.print("Kok sonda (sonek): ");
        kokSonda(kok);
        System.out.println();
        System.out.println("hesapla = " + hesapla(kok));
    }

    public static void main(String[] args) {
        // Slayttaki agac: (3 + 4) * (5 - 2)
        IfadeDugumu kok = new IfadeDugumu('*',
                new IfadeDugumu('+', new IfadeDugumu(3), new IfadeDugumu(4)),
                new IfadeDugumu('-', new IfadeDugumu(5), new IfadeDugumu(2)));
        yazdir(kok); // 7 * 3 = 21

        // Agac sonek ifadeden yigin ile de kurulabilir; sayilar cok basamakli olabilir
        System.out.println();
        yazdir(sonektenKur("12 3 / 4 + 2 *")); // ((12 / 3) + 4) * 2 = 16
    }
}
