import java.util.ArrayList;
import java.util.List;

public class BirlesimKesisim {

    // Sirali dizilerle tutulan iki kumenin birlesimi ve kesisimi (iki imlec).
    // Birlestirme siralamasindaki merge gibi tek geciste calisir: O(n + m)
    static void birlesimKesisim(int[] a, int[] b, List<Integer> bir, List<Integer> kes) {
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) bir.add(a[i++]);       // yalnizca a'da
            else if (a[i] > b[j]) bir.add(b[j++]);  // yalnizca b'de
            else {                                  // ortak oge
                bir.add(a[i]);
                kes.add(a[i]);
                i++;
                j++;
            }
        }
        while (i < a.length) bir.add(a[i++]);  // a'da kalanlar
        while (j < b.length) bir.add(b[j++]);  // b'de kalanlar
    }

    // Ayni yontemle fark A - B: a'da olup b'de olmayanlar
    static List<Integer> fark(int[] a, int[] b) {
        List<Integer> sonuc = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) sonuc.add(a[i++]);  // b'de yok
            else if (a[i] > b[j]) j++;
            else { i++; j++; }                   // ortak: atlanir
        }
        while (i < a.length) sonuc.add(a[i++]);
        return sonuc;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9}; // sirali ve tekrarsiz olmali
        int[] b = {3, 4, 5, 8};

        List<Integer> bir = new ArrayList<>();
        List<Integer> kes = new ArrayList<>();
        birlesimKesisim(a, b, bir, kes);

        System.out.println("Birlesim : " + bir);         // [1, 3, 4, 5, 7, 8, 9]
        System.out.println("Kesisim  : " + kes);         // [3, 5]
        System.out.println("Fark a-b : " + fark(a, b));  // [1, 7, 9]
    }
}
