import java.util.HashMap; 
import java.util.Map;

public class TekrarEdenSayilariBulma {
	
    public static void main(String[] args) {
    	
        // Yeni 10 sayiyi iceren dizi tanimlandi
        int[] dizi = {3, 7, 2, 5, 7, 3, 8, 1, 3, 7, 9, 7, 2, 10, 10, 5, 12, 5, 3, 8};

        // Sayi frekanslarini tutacak bir HashMap olusturuluyor
        Map<Integer, Integer> sayiFrekanslari = new HashMap<>();

        // Dizideki her bir sayinin frekansi hesaplaniyor
        for (int sayi : dizi) {
            if (sayiFrekanslari.containsKey(sayi)) {
                // Eger sayi daha once eklenmisse, o sayinin frekansi 1 artiriliyor
                sayiFrekanslari.put(sayi, sayiFrekanslari.get(sayi) + 1);
            } else {
                // Eger sayi ilk kez karsilasiyorsa, frekansi 1 olarak baslatiliyor
                sayiFrekanslari.put(sayi, 1);
            }
        }

        // Tekrar eden sayilar ve frekanslari yazdiriliyor
        System.out.println("Tekrar eden sayilar ve frekanslari:");
        
        // HashMap'teki her bir oge (sayi ve frekans) uzerinde donuluyor
        for (Map.Entry<Integer, Integer> entry : sayiFrekanslari.entrySet()) {
            // Eger sayinin frekansi 1'den fazla ise, o sayinin tekrar ettigi yazdiriliyor
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " tekrar ediyor " + entry.getValue() + " kez.");
            }
        }
    }
}
