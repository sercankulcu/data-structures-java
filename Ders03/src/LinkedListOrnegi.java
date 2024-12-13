
import java.util.LinkedList;

public class LinkedListOrnegi {
	
    public static void main(String[] args) {
    	
        // LinkedList olusturuluyor
        LinkedList<String> sepet = new LinkedList<>();

        // LinkedList'e elemanlar ekleniyor
        sepet.add("Elma");
        sepet.add("Armut");
        sepet.add("Muz");
        sepet.add("Cilek");

        // Elemanlari ekrana yazdirma
        System.out.print("Sepettekiler : ");
        for (String meyve : sepet) {
            System.out.print(meyve + " , ");
        }System.out.println();

        // Belirli bir pozisyona eleman ekleme
        sepet.add(2, "Portakal");

        // Eklemeden sonraki hali
        System.out.print("\nPortakal Eklendikten Sonra: ");
        for (String meyve : sepet) {
            System.out.print(meyve + " , ");
        }System.out.println();

        // Belirli bir elemani kaldirma
        sepet.remove("Armut");

        // Kaldirmadan sonraki hali
        System.out.print("\nArmut Kaldirildiktan Sonra: ");
        for (String meyve : sepet) {
            System.out.print(meyve + " , ");
        }System.out.println();
    }
}

