/*
 * Dolunca iki katina buyuyen dizi tabanli yigin (dinamik dizi).
 * Buyutme O(n) surer ama seyrek olur: n push'un toplam maliyeti < 3n,
 * yani push amortize O(1).
 */
public class DinamikDiziYigin<E> implements Stack<E> {
    private E[] veri;
    private int top = -1;                    // -1 -> bos yigin

    @SuppressWarnings("unchecked")
    public DinamikDiziYigin(int kapasite) { veri = (E[]) new Object[kapasite]; }

    public boolean isEmpty() { return top == -1; }
    public int size()        { return top + 1; }
    public E top()           { return isEmpty() ? null : veri[top]; }
    public int kapasite()    { return veri.length; }   // dizinin o anki boyu

    public void push(E x) {
        if (top == veri.length - 1)
            buyut(2 * veri.length);          // tasma yerine buyut
        veri[++top] = x;
    }

    public E pop() {
        if (isEmpty()) throw new IllegalStateException("Alt tasma");
        E x = veri[top];
        veri[top--] = null;                  // referansi birak
        return x;
    }

    @SuppressWarnings("unchecked")
    private void buyut(int yeniKap) {
        E[] yeni = (E[]) new Object[yeniKap];
        for (int i = 0; i <= top; i++)
            yeni[i] = veri[i];
        veri = yeni;
        System.out.println("  buyut(" + yeniKap + "): " + (top + 1) + " oge kopyalandi");
    }

    public static void main(String[] args) {
        DinamikDiziYigin<Integer> yigin = new DinamikDiziYigin<>(2);   // kapasite 2

        for (int x = 10; x <= 50; x += 10) {
            System.out.println("push(" + x + ")");
            yigin.push(x);
        }
        System.out.println("size: " + yigin.size() + ", kapasite: " + yigin.kapasite());

        while (!yigin.isEmpty())
            System.out.print(yigin.pop() + " ");
        System.out.println();
    }
}
