import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Hanoi kuleleri: n diski A'dan C'ye, B'yi yardimci kullanarak tasi.
 * Her adimda yalnizca en ustteki disk tasinir; buyuk disk kucugun ustune konamaz.
 * Gereken hamle sayisi 2^n - 1.
 *
 * 1. Ozyinelemeli cozum: bekleyen cagrilari cagri yigini tutar.
 * 2. Yiginla cozum: ayni isi acik bir gorev yigini ile yapar.
 */
public class HanoiKuleleri {

    // Ozyinelemeli: k(aynak), y(ardimci), h(edef); her satir "disk: kaynak -> hedef"
    static void hanoi(int n, char k, char y, char h) {
        if (n == 1) {
            System.out.println("1: " + k + " -> " + h);
            return;
        }
        hanoi(n - 1, k, h, y);
        System.out.println(n + ": " + k + " -> " + h);
        hanoi(n - 1, y, k, h);
    }

    // Gorev: n diski k(aynak)'tan h(edef)'e, y(ardimci) ile tasi
    record Gorev(int n, char k, char y, char h) {}

    static void hanoi(int n) {
        Deque<Gorev> yigin = new ArrayDeque<>();
        yigin.push(new Gorev(n, 'A', 'B', 'C'));
        while (!yigin.isEmpty()) {
            Gorev g = yigin.pop();
            if (g.n() == 1)
                System.out.println(g.k() + " -> " + g.h());
            else {                       // ters sirayla: ilk yapilacak en uste
                yigin.push(new Gorev(g.n() - 1, g.y(), g.k(), g.h()));
                yigin.push(new Gorev(1, g.k(), g.y(), g.h()));
                yigin.push(new Gorev(g.n() - 1, g.k(), g.h(), g.y()));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Ozyinelemeli (3 disk):");
        hanoi(3, 'A', 'B', 'C');
        System.out.println("Yiginla (3 disk):");
        hanoi(3);
    }
}
