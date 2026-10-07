import java.util.ArrayList;
import java.util.List;

public class AyrikKumeDugum {

    // Ayrik kume elemanlarini temsil eden dugum sinifi
    static class SetDugum {
        int veri;          // dugumun degeri
        int sira;          // rutbe (yukseklik ust siniri)
        SetDugum ebeveyn;  // ebeveyn dugum; kok kendini gosterir

        SetDugum(int veri) {
            this.veri = veri;
            this.ebeveyn = this; // makeSet: tek ogeli kume
            this.sira = 0;
        }
    }

    // Dugumun kokunu bulur ve yol kisaltma yapar
    static SetDugum find(SetDugum d) {
        if (d != d.ebeveyn)
            d.ebeveyn = find(d.ebeveyn); // kisaltma
        return d.ebeveyn;
    }

    // Rutbeye gore birlestirme
    static void union(SetDugum x, SetDugum y) {
        SetDugum kokX = find(x);
        SetDugum kokY = find(y);
        if (kokX == kokY) return; // zaten ayni kumede
        if (kokX.sira < kokY.sira) {
            kokX.ebeveyn = kokY;
        } else if (kokX.sira > kokY.sira) {
            kokY.ebeveyn = kokX;
        } else { // esit: kokX, kokY'nin altina; rutbe artar
            kokX.ebeveyn = kokY;
            kokY.sira++;
        }
    }

    // Her dugumun kokunu yazdirir
    static void kokleriYazdir(List<SetDugum> dugumler) {
        for (SetDugum d : dugumler)
            System.out.print(d.veri + "->" + find(d).veri + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        List<SetDugum> d = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            d.add(new SetDugum(i)); // 6 tekil kume

        union(d.get(0), d.get(1)); // kok 1, sira 1
        union(d.get(2), d.get(3)); // kok 3, sira 1
        union(d.get(0), d.get(2)); // esit rutbe: 1 -> 3, sira[3] = 2
        union(d.get(4), d.get(0)); // 4 -> 3
        kokleriYazdir(d);          // 0..4 -> 3, 5 -> 5
        System.out.println("0 ile 4 ayni kumede mi? " + (find(d.get(0)) == find(d.get(4)))); // true
        System.out.println("0 ile 5 ayni kumede mi? " + (find(d.get(0)) == find(d.get(5)))); // false
        System.out.println("Kokun rutbesi: " + find(d.get(0)).sira); // 2
    }
}
