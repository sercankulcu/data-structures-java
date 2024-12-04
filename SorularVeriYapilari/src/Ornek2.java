import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Ornek2 {
	
    public static void main(String[] argv) {
    	
        // Ana dizi olustur
        int[] array = {6, 9, 8, 4, 3, 7}; // Daha fazla eleman eklendi

        // Liste olustur ve ilk elemanlari ekle
        List<Integer> list = new ArrayList<>();
        list.add(array[0]);
        list.add(array[2]);
        list.add(array[4]); // Daha fazla eleman ekleme
        System.out.println("Baslangic listesi: " + list);

        // Listeyi guncelle
        list.set(1, array[1]); // 2. eleman degistirildi
        System.out.println("Guncellenmis liste: " + list);

        // Listeyi sirala
        Collections.sort(list);
        System.out.println("Siralanmis liste: " + list);

        // Eleman cikarma
        list.remove(0);
        System.out.println("Bir eleman cikarildi: " + list);

        // Eleman kontrolu
        if (list.contains(8)) {
            System.out.println("Liste 8'i iceriyor.");
        } else {
            System.out.println("Liste 8'i icermiyor.");
        }

        // Eleman ekleme
        list.add(array[3]);
        list.add(array[5]);
        System.out.println("Yeni elemanlar eklendi: " + list);

        // Iterator kullanarak listeyi yazdirma
        System.out.println("Iterator ile liste elemanlari:");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("- " + iterator.next());
        }

        // Elemanlari filtreleme (7'den buyuk olanlari al)
        List<Integer> filteredList = new ArrayList<>();
        for (int num : list) {
            if (num > 7) {
                filteredList.add(num);
            }
        }
        System.out.println("Filtrelenmis liste (7'den buyuk): " + filteredList);

        // Ic ice listeler olusturma
        List<List<Integer>> nestedList = new ArrayList<>();
        nestedList.add(new ArrayList<>(List.of(1, 2, 3)));
        nestedList.add(new ArrayList<>(list));
        System.out.println("Ic ice liste: " + nestedList);

        // Ic ice listeden eleman cekme
        System.out.println("Ic ice listedeki ikinci listenin ilk elemani: " + nestedList.get(1).get(0));

        // Ters siralama
        Collections.reverse(list);
        System.out.println("Ters siralanmis liste: " + list);
    }
}
