/*
 * Bagli liste tabanli yigin: yiginin ustu (top) listenin basidir.
 * push ve pop listenin basinda calisir, kaydirma yoktur -> O(1).
 * Kapasite siniri yoktur; her oge icin ek bir referans (sonraki) tutulur.
 */
public class BagliListeYigin {

    // Yigindaki her oge bir dugumde tutulur
    static class Dugum {
        int veri;
        Dugum sonraki;
        Dugum(int veri) { this.veri = veri; }   // sonraki = null
    }

    Dugum top;         // yiginin ustu
    int uzunluk;

    public boolean isEmpty() { return top == null; }
    public int size()        { return uzunluk; }

    public void push(int veri) {
        Dugum gecici = new Dugum(veri);
        gecici.sonraki = top;    // once bagla ki eski liste kaybolmasin
        top = gecici;
        uzunluk++;
    }

    public int pop() {
        if (top == null)
            throw new IllegalStateException("Alt tasma: yigin bos");
        int sonuc = top.veri;
        top = top.sonraki;       // eski ust dugum cop toplayiciya kalir
        uzunluk--;
        return sonuc;
    }

    // Ustteki ogeyi cikarmadan dondurur
    public int top() {
        if (top == null)
            throw new IllegalStateException("Alt tasma: yigin bos");
        return top.veri;
    }

    public static void main(String[] args) {
        BagliListeYigin yigin = new BagliListeYigin();
        yigin.push(10);
        yigin.push(15);          // yigin (ustten alta): 15 -> 10
        System.out.println("top(): " + yigin.top() + ", size: " + yigin.size());

        // Iki oge varken uc kez pop: ucuncusu alt tasma
        try {
            for (int k = 0; k < 3; k++)
                System.out.println(yigin.pop());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
