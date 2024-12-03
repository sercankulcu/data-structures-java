import java.util.PriorityQueue;

class Hasta {
    String ad;  // Hastanin adi
    int oncelik;  // Hastanin onceligi
    int islemSuresi;  // Hastanin islem suresi (milisaniye cinsinden)

    // Hasta sinifinin yapici metodu
    public Hasta(String ad, int oncelik, int islemSuresi) {
        this.ad = ad;  // Hastanin adini ata
        this.oncelik = oncelik;  // Hastanin onceligini ata
        this.islemSuresi = islemSuresi;  // Hastanin islem suresini ata
    }
}

public class OncelikliKuyrukOrnek {
    public static void main(String[] args) {
        // PriorityQueue olusturuluyor. Siralama, yuksek oncelige sahip hastalarin once gelmesi icin ters siralama yapiliyor
        PriorityQueue<Hasta> oncelikliKuyruk = new PriorityQueue<>((hasta1, hasta2) -> hasta2.oncelik - hasta1.oncelik);

        // Acil servise gelen hastalar ekleniyor (her birinin adi, onceligi ve islem suresi ile)
        oncelikliKuyruk.offer(new Hasta("Ahmet", 2, 2000));  // Ahmet'in islem suresi 2000 ms
        oncelikliKuyruk.offer(new Hasta("Mehmet", 5, 1000));  // Mehmet'in islem suresi 1000 ms
        oncelikliKuyruk.offer(new Hasta("Ayse", 3, 1500));  // Ayse'nin islem suresi 1500 ms
        oncelikliKuyruk.offer(new Hasta("Zeynep", 1, 2500));  // Zeynep'in islem suresi 2500 ms
        oncelikliKuyruk.offer(new Hasta("Emre", 4, 1800));  // Emre'nin islem suresi 1800 ms

        // Hastalari onceliklerine gore sirayla islemi aliyoruz
        while (!oncelikliKuyruk.isEmpty()) {
            // En yuksek oncelige sahip hasta cikarilir (poll())
            Hasta hasta = oncelikliKuyruk.poll();
            // Cikan hastayi ekrana yazdiriyoruz
            System.out.println("Acil Servise Gelen Hasta: " + hasta.ad + " - Oncelik: " + hasta.oncelik);

            // Hastanin islem suresi kadar bekleme yapiyoruz
            try {
                System.out.println(hasta.ad + " icin islem yapilacak. Islem suresi: " + hasta.islemSuresi + " ms");
                Thread.sleep(hasta.islemSuresi);  // Hastanin islem suresi kadar bekleme yapiyoruz
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
