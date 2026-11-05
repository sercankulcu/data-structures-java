import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
 * Stock span (hisse senedi sicrama) problemi:
 * bir gunun span degeri, o gun dahil geriye dogru fiyati bugunku fiyattan
 * kucuk ya da esit olan ardisik gunlerin sayisidir.
 * Yigin, henuz asilmamis daha yuksek fiyatli gunlerin indislerini tutar.
 * Her indis bir kez eklenir, en cok bir kez cikar -> O(n).
 */
public class StockSpan {

    static int[] span(int[] fiyatlar) {
        int[] sicrama = new int[fiyatlar.length];
        Deque<Integer> yigin = new ArrayDeque<>();
        yigin.push(0); sicrama[0] = 1;              // ilk gun
        for (int i = 1; i < fiyatlar.length; i++) {
            while (!yigin.isEmpty() &&
                fiyatlar[i] >= fiyatlar[yigin.peek()])
                yigin.pop();                         // asilan gunler sinir olamaz
            sicrama[i] = yigin.isEmpty() ? i + 1
                                  : i - yigin.peek();
            yigin.push(i);
        }
        return sicrama;
    }

    public static void main(String[] args) {
        int[] fiyatlar = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Fiyatlar: " + Arrays.toString(fiyatlar));
        System.out.println("Span    : " + Arrays.toString(span(fiyatlar)));

        int[] fiyatlar2 = {10, 4, 5, 90, 120, 80};
        System.out.println("Fiyatlar: " + Arrays.toString(fiyatlar2));
        System.out.println("Span    : " + Arrays.toString(span(fiyatlar2)));
    }
}
