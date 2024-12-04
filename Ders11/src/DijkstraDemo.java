import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraDemo {

    // Cizge sinifi: Dugumler arasindaki kenarlari ve agirliklari tutar
    public static class Cizge {
        Map<Integer, Map<Integer, Integer>> dugumler = new HashMap<>();

        // Kenar ekler: Kaynak ve hedef arasindaki agirligi belirler
        public void kenarEkle(int kaynak, int hedef, int agirlik) {
            // Yönsüz cizge oldugu icin her iki tarafa da kenar eklenir
            dugumler.computeIfAbsent(kaynak, k -> new HashMap<>()).put(hedef, agirlik);
            dugumler.computeIfAbsent(hedef, k -> new HashMap<>()).put(kaynak, agirlik);
        }
    }

    // Dijkstra algoritmasi: En kisa mesafeleri hesaplar
    public static Map<Integer, Integer> dijkstra(Cizge cizge, int kaynak) {
        // Her dugum icin mesafe degerini tutan bir map veri yapisi
        Map<Integer, Integer> mesafeler = new HashMap<>();
        // Ziyaret edilen dugumleri tutan bir set
        Set<Integer> ziyaretEdilen = new HashSet<>();
        // Mesafelere gore siralama yapmak icin oncelik kuyrugu
        PriorityQueue<Integer> kuyruk = new PriorityQueue<>(Comparator.comparingInt(mesafeler::get));

        // Cizgedeki tum dugumler icin mesafeyi baslangicta sonsuz yap
        for (int dugum : cizge.dugumler.keySet()) {
            mesafeler.put(dugum, Integer.MAX_VALUE);
        }
        mesafeler.put(kaynak, 0); // Baslangic dugumunun mesafesini 0 yap
        kuyruk.offer(kaynak); // Kuyruga baslangic dugumunu ekle
        
        // Kuyrukta eleman kaldigi surece devam et
        while (!kuyruk.isEmpty()) {
            int mevcutDugum = kuyruk.poll(); // Kuyruktan bir dugumu al
            ziyaretEdilen.add(mevcutDugum); // Dugumu ziyaret edilmis olarak isaretle
            
            // Dugumun komsularini gez
            for (Map.Entry<Integer, Integer> komsu : cizge.dugumler.get(mevcutDugum).entrySet()) {
                // Komsu dugum daha once ziyaret edilmediyse
                if (!ziyaretEdilen.contains(komsu.getKey())) {
                    // Alternatif mesafeyi hesapla
                    int alternatif = mesafeler.get(mevcutDugum) + komsu.getValue();
                    // Alternatif mesafe daha kucukse, mesafeyi guncelle
                    if (alternatif < mesafeler.get(komsu.getKey())) {
                        mesafeler.put(komsu.getKey(), alternatif);
                        kuyruk.offer(komsu.getKey()); // Kuyruga ekle
                    }
                }
            }
        }
        return mesafeler; // Hesaplanan en kisa mesafeleri geri dondur
    }

    // Ana metod: Cizgeyi olusturur, kenarlari ekler ve Dijkstra algoritmasini calistirir
    public static void main(String[] args) {
        
        Cizge cizge = new Cizge(); // Yeni bir cizge nesnesi olustur
        // Cizgeye kenarlar ekle
        cizge.kenarEkle(1, 2, 3);
        cizge.kenarEkle(1, 3, 5);
        cizge.kenarEkle(2, 3, 1);
        cizge.kenarEkle(2, 4, 7);
        cizge.kenarEkle(3, 4, 2);
        cizge.kenarEkle(4, 5, 4);
        cizge.kenarEkle(5, 6, 3);
        cizge.kenarEkle(2, 6, 5);
        cizge.kenarEkle(2, 7, 2);

        int kaynak = 1; // Dijkstra algoritmasinin baslangic dugumu

        // Dijkstra algoritmasini calistir ve mesafeleri al
        Map<Integer, Integer> mesafeler = dijkstra(cizge, kaynak);

        // Sonuclari ekrana yazdir
        System.out.println("Baslangic dugumu " + kaynak + " icin en kisa mesafeler:");
        for (int dugum : mesafeler.keySet()) {
            System.out.println("Dugum " + dugum + ": " + mesafeler.get(dugum)); // Dugum ve mesafeyi yazdir
        }
    }
}
