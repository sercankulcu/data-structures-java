/*
 * Sabit kapasiteli dizi tabanli yigin.
 * top, son ogenin indisidir; bos yiginda top = -1, size = top + 1.
 * Dolu yigina push -> tasma (overflow), bos yigindan pop -> alt tasma (underflow).
 * Tum islemler O(1).
 */
public class DiziYigin<E> implements Stack<E> {
    private E[] veri;
    private int top = -1;                    // -1 -> bos yigin

    @SuppressWarnings("unchecked")
    public DiziYigin(int kapasite) { veri = (E[]) new Object[kapasite]; }

    public boolean isEmpty() { return top == -1; }
    public int size()        { return top + 1; }
    public E top()           { return isEmpty() ? null : veri[top]; }

    public void push(E x) {
        if (top == veri.length - 1) throw new IllegalStateException("Tasma");
        veri[++top] = x;
    }

    public E pop() {
        if (isEmpty()) throw new IllegalStateException("Alt tasma");
        E x = veri[top];
        veri[top--] = null;                  // referansi birak
        return x;
    }

    public static void main(String[] args) {
        Stack<Integer> yigin = new DiziYigin<>(4);   // 4 hucrelik dizi

        yigin.push(10);
        yigin.push(15);
        yigin.push(20);
        System.out.println("top(): " + yigin.top() + ", size: " + yigin.size());
        System.out.println("pop(): " + yigin.pop() + ", size: " + yigin.size());
        yigin.push(25);
        yigin.push(30);
        System.out.println("size: " + yigin.size() + " (dizi dolu)");

        // Dolu yigina ekleme -> tasma
        try {
            yigin.push(40);
        } catch (IllegalStateException e) {
            System.out.println("push(40): " + e.getMessage());
        }

        // Yigini bosalt: son giren ilk cikar
        while (!yigin.isEmpty())
            System.out.print(yigin.pop() + " ");
        System.out.println();

        // Bos yiginda top() null dondurur, pop() istisna firlatir
        System.out.println("top(): " + yigin.top());
        try {
            yigin.pop();
        } catch (IllegalStateException e) {
            System.out.println("pop(): " + e.getMessage());
        }
    }
}
