import java.util.HashMap;
import java.util.Map;

public class TekrarEdenSayilariBulma {
	
    public static void main(String[] args) {
    	
        int[] dizi = {3, 7, 2, 5, 7, 3, 8, 1, 3, 7, 9, 7, 2};

        Map<Integer, Integer> sayiFrekanslari = new HashMap<>();

        for (int sayi : dizi) {
            if (sayiFrekanslari.containsKey(sayi)) {
                sayiFrekanslari.put(sayi, sayiFrekanslari.get(sayi) + 1);
            } else {
                sayiFrekanslari.put(sayi, 1);
            }
        }

        System.out.println("Tekrar eden sayılar ve frekansları:");
        for (Map.Entry<Integer, Integer> entry : sayiFrekanslari.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " tekrar ediyor " + entry.getValue() + " kez.");
            }
        }
    }
}
