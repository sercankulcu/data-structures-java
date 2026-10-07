import java.util.ArrayDeque;
import java.util.Deque;

/*
 * getMin() islemi O(1) olan yigin.
 * y tum ogeleri, min ise her an en kucuk ogeyi ustte tutar.
 * Esit ogeler de min'e push edilir (<=); yoksa ikinci kopya cikinca en kucuk kaybolur.
 */
public class MinYigin {
    Deque<Integer> y = new ArrayDeque<>();
    Deque<Integer> min = new ArrayDeque<>();

    void push(int x) {
        y.push(x);
        if (min.isEmpty() || x <= min.peek())
            min.push(x);
    }

    int pop() {
        int x = y.pop();                 // bossa NoSuchElementException
        if (x == min.peek()) min.pop();  // x int oldugu icin deger karsilastirilir
        return x;
    }

    int getMin() { return min.peek(); }

    public static void main(String[] args) {
        MinYigin yigin = new MinYigin();
        yigin.push(5);
        yigin.push(3);
        yigin.push(7);
        yigin.push(3);
        System.out.println("getMin(): " + yigin.getMin());
        System.out.println("pop(): " + yigin.pop() + ", getMin(): " + yigin.getMin());
        System.out.println("pop(): " + yigin.pop() + ", getMin(): " + yigin.getMin());
        System.out.println("pop(): " + yigin.pop() + ", getMin(): " + yigin.getMin());
    }
}
