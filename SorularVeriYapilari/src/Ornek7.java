import java.util.Arrays;
import java.util.NoSuchElementException;

public class Ornek7<E> {

    final int DEFAULT_CAPACITY = 8;
    private int capacity;
    private int N; // eleman sayisi
    private Object[] items;

    // Yapiyi baslatma, varsayilan kapasite ile
    public Ornek7() {
        this.N = 0;
        this.capacity = DEFAULT_CAPACITY;
        this.items = new Object[DEFAULT_CAPACITY];
    }

    // Dahili arrayin boyutunu arttirma veya kisaltma
    private void shrinkStretch() {
        if (N < capacity / 4 && capacity > DEFAULT_CAPACITY) {
            // Boyut, kapasitenin 1/4'unden kucukse, boyut azaltma
            capacity /= 2;
            items = Arrays.copyOf(items, capacity);
        } else if (N > capacity / 2) {
            // Boyut kapasitenin yarısından buyukse, boyut arttirma
            capacity *= 2;
            items = Arrays.copyOf(items, capacity);
        }
    }

    // Diziye yeni bir eleman ekleme
    public void addItem(E e) {
        if (N == capacity) {
            // Array doluysa, kapasiteyi iki katina cikarma
            capacity *= 2;
            items = Arrays.copyOf(items, capacity);
        }
        items[N++] = e;
        shrinkStretch();
    }

    // Diziden bir eleman silme
    public void removeItem() {
        if (N == 0) {
            throw new NoSuchElementException("Array bos. Eleman silinemiyor.");
        }
        items[--N] = null;
        shrinkStretch();
    }

    // Dizinin mevcut boyutunu al
    public int size() {
        return N;
    }

    // Dizinin bos olup olmadigini kontrol et
    public boolean isEmpty() {
        return N == 0;
    }

    // Dizinin icerigini yazdir
    public void printItems() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(items, 0, N)));
    }

    // Diziyi temizle
    public void clear() {
        Arrays.fill(items, 0, N, null);
        N = 0;
        shrinkStretch();
    }

    public static void main(String[] args) {
        Ornek7<Integer> ornek = new Ornek7<>();
        
        // Eleman eklerken, her eklemeden sonra arrayi yazdir
        for (int i = 0; i < 20; i++) {
            ornek.addItem(i);
            System.out.println("Eleman ekledikten sonra: ");
            ornek.printItems();
        }

        // Eleman silerken, her silmeden sonra arrayi yazdir
        for (int i = 0; i < 15; i++) {
            ornek.removeItem();
            System.out.println("Eleman silindikten sonra: ");
            ornek.printItems();
        }

        // Son durumu yazdir
        System.out.println("Son durum: ");
        ornek.printItems();

        // Arrayi temizleyip, sonucu yazdir
        ornek.clear();
        System.out.println("Array temizlendikten sonra: ");
        ornek.printItems();
    }
}
