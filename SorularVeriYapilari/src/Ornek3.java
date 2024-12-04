import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Ornek3 {
	
    public static void main(String[] argv) {
    	
        // Ana listeyi olustur ve eleman ekle
        ArrayList<String> list = new ArrayList<>();
        list.add("dog");
        list.add("cat");
        list.add("frog");
        list.add("elephant");
        list.add("tiger");

        // Listenin baslangic durumu
        System.out.println("Baslangic listesi: " + list);

        // Eleman kontrolu
        System.out.println("Listede 'cat' var mi? " + list.contains("cat"));

        // Elemanin indexini bul
        System.out.println("'cat' elemaninin indeksi: " + list.indexOf("cat"));

        // Belirli bir indeksteki elemani getir
        System.out.println("1. indeksteki eleman: " + list.get(1));

        // Listenin boyutunu yazdir
        System.out.println("Listenin boyutu: " + list.size());

        // Siralama
        Collections.sort(list);
        System.out.println("Siralanmis liste: " + list);

        // Ters siralama
        Collections.reverse(list);
        System.out.println("Ters siralanmis liste: " + list);

        // Belirli bir elemani sil
        list.remove("frog");
        System.out.println("'frog' elemani silindi: " + list);

        // Elemanlari filtreleme (3 harften uzun olanlari getir)
        List<String> filteredList = list.stream()
                .filter(s -> s.length() > 3)
                .collect(Collectors.toList());
        System.out.println("3 harften uzun elemanlar: " + filteredList);

        // Ic ice liste olusturma
        ArrayList<ArrayList<String>> nestedList = new ArrayList<>();
        nestedList.add(new ArrayList<>(List.of("lion", "zebra")));
        nestedList.add(list);
        System.out.println("Ic ice liste: " + nestedList);

        // Ic ice liste elemanlarini yazdirma
        System.out.println("Ic ice listedeki 2. listenin ilk elemani: " + nestedList.get(1).get(0));

        // Elemanlari iterasyonla yazdir
        System.out.println("Elemanlar:");
        for (String item : list) {
            System.out.println("- " + item);
        }

        // Her elemani buyuk harfe donustur ve listeyi guncelle
        list = list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Elemanlar buyuk harfe donusturuldu: " + list);

        // Elemanlari index ile yazdir
        System.out.println("Index ile elemanlar:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index " + i + ": " + list.get(i));
        }
    }
}
