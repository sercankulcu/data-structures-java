import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CizgeBagliListe {

    // Komsu listelerini tutan bir liste ve ziyaret edilen dugumleri izlemek icin bir liste
    private List<LinkedList<Integer>> komsuListesi;
    private List<Boolean> ziyaretEdildi;

    // Constructor: Cizgeyi olusturur
    public CizgeBagliListe() {
        komsuListesi = new ArrayList<>(); // Cizgenin komsu listelerini tutan listeyi olusturur
        ziyaretEdildi = new ArrayList<>(); // Her dugumun ziyaret edilip edilmedigini tutan listeyi olusturur
    }

    // Ziyaret edilen dugumlerin durumunu temizler (Tüm dugumleri ziyaret edilmemis olarak isaretler)
    public void temizle() {
        for (int i = 0; i < ziyaretEdildi.size(); i++) {
            ziyaretEdildi.set(i, false); // Her dugumu ziyaret edilmemis olarak ayarlar
        }
    }

    // Dinamik olarak dugum kapasitesini kontrol eder ve genisletir. 
    private void kapasiteKontrolEt(int dugum) {
        while (dugum >= komsuListesi.size()) {
            komsuListesi.add(new LinkedList<>()); // Yeni dugum icin komsu listesi ekler
            ziyaretEdildi.add(false); // Yeni dugum icin ziyaret edilmedi olarak isaretler
        }
    }

    // Yeni bir kenar ekler. (Her iki dugumu de komsu listelerine ekler)
    public void kenarEkle(int dugum, int komsu) {
        kapasiteKontrolEt(dugum); // Dugum ve komsu listesinin yeterli kapasiteye sahip oldugundan emin ol
        kapasiteKontrolEt(komsu); // Aynı işlemi komsu dugumu için de yap
        komsuListesi.get(dugum).add(komsu); // Dugumun komsu listesine komsu dugumunu ekler
        komsuListesi.get(komsu).add(dugum); // Komsu dugumun komsu listesine ana dugumu ekler
    }

    // Genislik Oncelikli Arama (BFS): Girilen dugumden baslayarak, komsulari gezer
    public void BFS(int dugum) {
        temizle(); // Ziyaret edilmis dugumleri temizle
        LinkedList<Integer> kuyruk = new LinkedList<>(); // Genişlik öncelikli arama için kuyruk olusturulur

        ziyaretEdildi.set(dugum, true); // Baslangic dugumunu ziyaret edildi olarak isaretle
        kuyruk.add(dugum); // Baslangic dugumunu kuyruga ekle

        // Kuyruk bosalana kadar devam et
        while (!kuyruk.isEmpty()) {
            dugum = kuyruk.poll(); // Kuyruktan bir dugumu cikar
            System.out.print(dugum + " "); // Dugumu ekrana yazdir

            // Dugumun komsularini gez ve ziyaret edilmemis komsulari kuyruga ekle
            Iterator<Integer> iterator = komsuListesi.get(dugum).listIterator();
            while (iterator.hasNext()) {
                int komsu = iterator.next(); // Dugumun komsularini al
                if (!ziyaretEdildi.get(komsu)) { // Komsu daha once ziyaret edilmediyse
                    ziyaretEdildi.set(komsu, true); // Komsuyu ziyaret edildi olarak isaretle
                    kuyruk.add(komsu); // Komsuyu kuyruga ekle
                }
            }
        }
    }

    // Derinlik Oncelikli Arama (DFS) baslatir
    public void DFSBaslat(int dugum) {
        temizle(); // Ziyaret edilmis dugumleri temizle
        DFS(dugum); // Derinlik oncelikli aramayi baslat
    }

    // Derinlik Oncelikli Arama (DFS): Girilen dugumden baslayarak derinlere dogru git
    public void DFS(int dugum) {
        ziyaretEdildi.set(dugum, true); // Dugumu ziyaret edildi olarak isaretle
        System.out.print(dugum + " "); // Dugumu ekrana yazdir

        // Dugumun komsularini gez ve ziyaret edilmemis komsularda DFS'i recursive calistir
        Iterator<Integer> iterator = komsuListesi.get(dugum).listIterator();
        while (iterator.hasNext()) {
            int komsu = iterator.next(); // Dugumun komsularini al
            if (!ziyaretEdildi.get(komsu)) { // Komsu daha once ziyaret edilmediyse
                DFS(komsu); // Derinlik oncelikli aramayi komsu dugumde devam ettir
            }
        }
    }

    public static void main(String[] args) {
        CizgeBagliListe cizge = new CizgeBagliListe();

        // Kenarlari ekleyerek cizgeyi kur
        cizge.kenarEkle(0, 2);
        cizge.kenarEkle(0, 4);
        cizge.kenarEkle(1, 2);
        cizge.kenarEkle(1, 7);
        cizge.kenarEkle(2, 3);
        cizge.kenarEkle(3, 6);
        cizge.kenarEkle(4, 5);
        cizge.kenarEkle(5, 7);
        cizge.kenarEkle(6, 7);
        cizge.kenarEkle(7, 8);

        System.out.println("Genislik Oncelikli Arama:");
        cizge.BFS(0); // 0. dugumden BFS aramasini baslat
        System.out.println("\nDerinlik Oncelikli Arama:");
        cizge.DFSBaslat(0); // 0. dugumden DFS aramasini baslat
        
        // Yeni kenarlar ekle
        cizge.kenarEkle(0, 9);
        cizge.kenarEkle(7, 9);
        
        System.out.println("\nGenislik Oncelikli Arama:");
        cizge.BFS(0); // Yeniden BFS aramasini baslat
        System.out.println("\nDerinlik Oncelikli Arama:");
        cizge.DFSBaslat(0); // Yeniden DFS aramasini baslat
    }
}
