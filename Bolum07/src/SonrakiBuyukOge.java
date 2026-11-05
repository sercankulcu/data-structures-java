import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
 * Sonraki buyuk oge: her oge icin saginda kalan, kendisinden buyuk ilk oge; yoksa -1.
 * Dizi sagdan sola gezilir; yigin alttan uste azalan sirada aday degerleri tutar
 * (monoton yigin). Her oge en fazla bir kez push ve bir kez pop edilir -> O(n).
 */
public class SonrakiBuyukOge {

    static int[] sonrakiBuyuk(int[] dizi) {
        int[] sonuc = new int[dizi.length];
        Deque<Integer> yigin = new ArrayDeque<>();
        for (int i = dizi.length - 1; i >= 0; i--) {
            while (!yigin.isEmpty() &&
                   yigin.peek() <= dizi[i])
                yigin.pop();             // kucuk ya da esit adaylar artik cevap olamaz
            sonuc[i] = yigin.isEmpty()
                     ? -1 : yigin.peek();
            yigin.push(dizi[i]);
        }
        return sonuc;
    }

    public static void main(String[] args) {
        int[] dizi = {4, 7, 3, 4, 8, 1};
        System.out.println("dizi : " + Arrays.toString(dizi));
        System.out.println("sonuc: " + Arrays.toString(sonrakiBuyuk(dizi)));

        int[] dizi2 = {2, 5, 3, 1, 4};
        System.out.println("dizi : " + Arrays.toString(dizi2));
        System.out.println("sonuc: " + Arrays.toString(sonrakiBuyuk(dizi2)));
    }
}
