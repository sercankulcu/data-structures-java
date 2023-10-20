
import java.util.LinkedList;

public class LinkedListOrnegi {
    public static void main(String[] args) {
        // LinkedList oluşturuluyor
        LinkedList<String> linkedList = new LinkedList<String>();

        // LinkedList'e elemanlar ekleniyor
        linkedList.add("Elma");
        linkedList.add("Armut");
        linkedList.add("Muz");
        linkedList.add("Çilek");

        // Elemanları ekrana yazdırma
        System.out.println("LinkedList Elemanları:");
        for (String meyve : linkedList) {
            System.out.println(meyve);
        }

        // Belirli bir pozisyona eleman ekleme
        linkedList.add(2, "Portakal");

        // Eklemeden sonraki hali
        System.out.println("\nPortakal Eklendikten Sonra:");
        for (String meyve : linkedList) {
            System.out.println(meyve);
        }

        // Belirli bir elemanı kaldırma
        linkedList.remove("Armut");

        // Kaldırmadan sonraki hali
        System.out.println("\nArmut Kaldırıldıktan Sonra:");
        for (String meyve : linkedList) {
            System.out.println(meyve);
        }
    }
}

