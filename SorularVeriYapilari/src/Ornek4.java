import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Arrays;

public class Ornek4 {
	
    public static void main(String[] args) {
    	
        // PriorityQueue olustururken ozel bir Comparator tanimlama
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                // Sayilari kucukten buyuge siralamak yerine buyukten kucuge sirala
                return b - a;
            }
        });

        // PriorityQueue'ya eleman ekleme
        pq.add(4);
        pq.add(7);
        pq.add(8);
        pq.add(77);
        pq.add(9);
        System.out.println("Ilk PriorityQueue durumu: " + pq);

        // Peek islemi: En oncelikli elemani getirir
        System.out.println("Peek: " + pq.peek()); // 77

        // Offer islemi: Eleman ekler ve basari durumunu dondurur
        pq.offer(2);
        System.out.println("Offer 2 den sonra Peek: " + pq.peek()); // 77

        // Add islemi: Eleman ekler, basarisiz olursa exception atar
        pq.add(3);
        System.out.println("Add 3 den sonra Peek: " + pq.peek()); // 77

        // Offer ile eleman ekleme
        pq.offer(1);
        System.out.println("Offer 1 den sonra Peek: " + pq.peek()); // 77

        // Remove islemi: Belirli bir elemani siler
        boolean removeResult = pq.remove(1);
        System.out.println("Remove 1 sonucu: " + removeResult); // true
        System.out.println("Remove 1 den sonra PriorityQueue: " + pq);

        // Poll islemi: En oncelikli elemani siler ve dondurur
        System.out.println("Poll: " + pq.poll()); // 77
        System.out.println("Poll dan sonra Peek: " + pq.peek()); // 9

        // Belirli bir elemani silmeye calis
        boolean removeResult2 = pq.remove(2);
        System.out.println("Remove 2 sonucu: " + removeResult2); // true
        System.out.println("Remove 2 den sonra PriorityQueue: " + pq);

        // PriorityQueue'nun son durumu
        System.out.println("Son PriorityQueue durumu: " + pq);

        // Poll ve Peek islemleri
        System.out.println("Poll: " + pq.poll()); // 9
        System.out.println("Peek: " + pq.peek()); // 8

        // PriorityQueue bosaldiktan sonra poll ve peek islemleri
        pq.clear(); // Tum elemanlari sil
        System.out.println("Clear dan sonra PriorityQueue: " + pq);
        System.out.println("Clear dan sonra Poll: " + pq.poll()); // null
        System.out.println("Clear dan sonra Peek: " + pq.peek()); // null

        // Exception durumlarini yakalama
        try {
            pq.element(); // Bos PriorityQueue'da element() kullanmak exception atar
        } catch (Exception e) {
            System.out.println("Exception yakalandi: " + e);
        }

        try {
            pq.remove(); // Bos PriorityQueue'da remove() kullanmak exception atar
        } catch (Exception e) {
            System.out.println("Exception yakalandi: " + e);
        }

        // Farkli bir PriorityQueue olustur ve islem yap
        PriorityQueue<String> stringPq = new PriorityQueue<>();
        stringPq.add("elma");
        stringPq.add("armut");
        stringPq.add("cilek");
        stringPq.add("muz");
        stringPq.add("kiraz");
        System.out.println("String PriorityQueue: " + stringPq);

        // Iterasyonla elemanlari yazdirma
        System.out.println("Iterasyonla elemanlar:");
        Iterator<String> it = stringPq.iterator();
        while (it.hasNext()) {
            System.out.println("- " + it.next());
        }

        // PriorityQueue'yu diziye cevirme
        Object[] array = stringPq.toArray();
        System.out.println("PriorityQueue dizisi: " + Arrays.toString(array));

        // PriorityQueue'nun bos olup olmadigini kontrol etme
        System.out.println("String PriorityQueue bos mu? " + stringPq.isEmpty());

        // PriorityQueue'nun boyutunu bulma
        System.out.println("String PriorityQueue boyutu: " + stringPq.size());

        // PriorityQueue'yu tekrar temizleme
        stringPq.clear();
        System.out.println("Clear dan sonra String PriorityQueue: " + stringPq);
    }
}
