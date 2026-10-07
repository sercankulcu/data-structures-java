import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// Ozyineleme yerine acik yigin ile gezinmeler ve kuyruk ile seviye sirasi
public class IkiliAgacYigin {

    static class AgacDugumu {
        int veri;
        AgacDugumu sol;
        AgacDugumu sag;

        AgacDugumu(int veri) {
            this.veri = veri;
        }
    }

    // Kok basta: once sag itilir ki sol ustte kalip once islensin (LIFO)
    public void kokBastaYigin(AgacDugumu kok) {
        if (kok == null) return;
        Deque<AgacDugumu> yigin = new ArrayDeque<>();
        yigin.push(kok);
        while (!yigin.isEmpty()) {
            AgacDugumu gecici = yigin.pop();
            System.out.print(gecici.veri + " ");
            if (gecici.sag != null)
                yigin.push(gecici.sag); // once sag
            if (gecici.sol != null)
                yigin.push(gecici.sol); // sol ustte
        }
    }

    // Kok ortada: sola inebildigin kadar in, sonra cikar, yazdir, saga gec
    public void kokOrtadaYigin(AgacDugumu kok) {
        if (kok == null) return;
        Deque<AgacDugumu> yigin = new ArrayDeque<>();
        AgacDugumu gecici = kok;
        while (!yigin.isEmpty() || gecici != null) {
            if (gecici != null) {
                yigin.push(gecici);   // sonra yazdirilacak
                gecici = gecici.sol;
            } else {
                gecici = yigin.pop(); // solundakilerin hepsi bitti
                System.out.print(gecici.veri + " ");
                gecici = gecici.sag;
            }
        }
    }

    // Kok sonda: ustteki dugum hemen cikarilmaz, once peek ile sag cocuguna bakilir
    public void kokSondaYigin(AgacDugumu kok) {
        AgacDugumu simdiki = kok;
        Deque<AgacDugumu> yigin = new ArrayDeque<>();
        while (simdiki != null || !yigin.isEmpty()) {
            if (simdiki != null) {
                yigin.push(simdiki);
                simdiki = simdiki.sol;
            } else {
                AgacDugumu gecici = yigin.peek().sag;
                if (gecici == null) {
                    // Sag cocuk yok: iki alt agac da bitti, dugum yazdirilir
                    gecici = yigin.pop();
                    System.out.print(gecici.veri + " ");
                    // Az once yazdirilan, ustteki dugumun sag cocuguysa onun da isi bitti
                    while (!yigin.isEmpty()
                            && gecici == yigin.peek().sag) {
                        gecici = yigin.pop();
                        System.out.print(gecici.veri + " ");
                    }
                } else {
                    simdiki = gecici; // once sag alt agac gezilir
                }
            }
        }
    }

    // Seviye sirasi: kuyruk (FIFO) ile seviye seviye, soldan saga
    public void seviyeSirasi(AgacDugumu kok) {
        if (kok == null) return;
        Queue<AgacDugumu> kuyruk = new LinkedList<>();
        kuyruk.add(kok);
        while (!kuyruk.isEmpty()) {
            AgacDugumu gecici = kuyruk.poll();
            System.out.print(gecici.veri + " ");
            if (gecici.sol != null)
                kuyruk.add(gecici.sol);
            if (gecici.sag != null)
                kuyruk.add(gecici.sag);
        }
    }

    // Dort gezinmeyi alt alta yazdirir
    void hepsiniYaz(String baslik, AgacDugumu kok) {
        System.out.println(baslik);
        System.out.print("  Kok basta    : ");
        kokBastaYigin(kok);
        System.out.println();
        System.out.print("  Kok ortada   : ");
        kokOrtadaYigin(kok);
        System.out.println();
        System.out.print("  Kok sonda    : ");
        kokSondaYigin(kok);
        System.out.println();
        System.out.print("  Seviye sirasi: ");
        seviyeSirasi(kok);
        System.out.println();
    }

    public static void main(String[] args) {
        IkiliAgacYigin ia = new IkiliAgacYigin();

        // Gezinme slaytlarindaki 7 dugumlu mukemmel agac
        AgacDugumu kok = new AgacDugumu(1);
        kok.sol = new AgacDugumu(2);
        kok.sag = new AgacDugumu(3);
        kok.sol.sol = new AgacDugumu(4);
        kok.sol.sag = new AgacDugumu(5);
        kok.sag.sol = new AgacDugumu(6);
        kok.sag.sag = new AgacDugumu(7);
        ia.hepsiniYaz("1..7 agaci:", kok);

        // Yigin izleme slaytlarindaki 6 dugumlu agac: 3 un yalnizca sag cocugu (6) var
        AgacDugumu kok2 = new AgacDugumu(1);
        kok2.sol = new AgacDugumu(2);
        kok2.sag = new AgacDugumu(3);
        kok2.sol.sol = new AgacDugumu(4);
        kok2.sol.sag = new AgacDugumu(5);
        kok2.sag.sag = new AgacDugumu(6);
        ia.hepsiniYaz("1..6 agaci:", kok2);

        // Seviye sirasi slaytindaki agac: 4 -> (3 -> 5), (7 -> sag 8)
        AgacDugumu kok3 = new AgacDugumu(4);
        kok3.sol = new AgacDugumu(3);
        kok3.sag = new AgacDugumu(7);
        kok3.sol.sol = new AgacDugumu(5);
        kok3.sag.sag = new AgacDugumu(8);
        System.out.print("4 kokunde agac, seviye sirasi: ");
        ia.seviyeSirasi(kok3); // 4 3 7 5 8
        System.out.println();

        // Bos agac: hicbir gezinme hata vermemeli
        ia.hepsiniYaz("Bos agac:", null);
    }
}
