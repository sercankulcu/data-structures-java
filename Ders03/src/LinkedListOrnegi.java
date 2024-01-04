
import java.util.LinkedList;

public class LinkedListOrnegi {
	
    public static void main(String[] args) {
    	
        // LinkedList oluşturuluyor
        LinkedList<String> sepet = new LinkedList<>();

        // LinkedList'e elemanlar ekleniyor
        sepet.add("Elma");
        sepet.add("Armut");
        sepet.add("Muz");
        sepet.add("Çilek");

        // Elemanları ekrana yazdırma
        System.out.print("Sepettekiler : ");
        for (String meyve : sepet) {
            System.out.print(meyve + " ");
        }System.out.println();

        // Belirli bir pozisyona eleman ekleme
        sepet.add(2, "Portakal");

        // Eklemeden sonraki hali
        System.out.print("\nPortakal Eklendikten Sonra: ");
        for (String meyve : sepet) {
            System.out.print(meyve + " ");
        }System.out.println();

        // Belirli bir elemanı kaldırma
        sepet.remove("Armut");

        // Kaldırmadan sonraki hali
        System.out.print("\nArmut Kaldırıldıktan Sonra: ");
        for (String meyve : sepet) {
            System.out.print(meyve + " ");
        }System.out.println();
    }
}

