import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Ornek8 {

    // Kuyrugu Stack kullanarak tersine ceviren metot
    public static void kuyruguTersCevir(Queue<String> kuyruk) {
        Stack<String> stack = new Stack<>();
        // Kuyrugun tum elemanlarini stack'e aktar
        while (!kuyruk.isEmpty()) {
            stack.push(kuyruk.poll());
        }
        // Stack'teki elemanlari tekrar kuyruga aktar
        while (!stack.isEmpty()) {
            kuyruk.offer(stack.pop());
        }
    }

    // Kuyrugun ilk elemanini görup silen metot
    public static String gorVeSil(Queue<String> kuyruk) {
        String eleman = kuyruk.peek();
        kuyruk.poll();  // Elemani sil
        return eleman;
    }

    // Kuyrugundaki belirli bir elemanin kac kez tekrar ettigini bulan metot
    public static int tekrarSayisi(Queue<String> kuyruk, String eleman) {
        int sayac = 0;
        for (String item : kuyruk) {
            if (item.equals(eleman)) {
                sayac++;
            }
        }
        return sayac;
    }

    public static void main(String args[]) {
        Stack<String> stack = new Stack<>();
        Queue<String> kuyruk = new LinkedList<>();

        // Kuyruga eleman ekle
        kuyruk.offer("a");
        kuyruk.offer("b");
        kuyruk.offer("c");
        kuyruk.offer("d");
        kuyruk.offer("b"); // Test amacli tekrar eden eleman ekle

        // Kuyrugu stack kullanarak tersine cevir
        System.out.println("Kuyrugu Stack ile tersine ceviriyoruz...");
        kuyruguTersCevir(kuyruk);
        System.out.println("Tersine cevrilmis Kuyruk: " + kuyruk);

        // Kuyrugun ilk elemanini gör ve sil
        System.out.println("\nilk elemani görup siliyoruz...");
        String silinenEleman = gorVeSil(kuyruk);
        System.out.println("Silinen Eleman: " + silinenEleman);
        System.out.println("ilk eleman silindikten sonraki Kuyruk: " + kuyruk);

        // Kuyrugundaki belirli bir elemanin tekrar sayisini bul
        String arananEleman = "b";
        int tekrarSayisi = tekrarSayisi(kuyruk, arananEleman);
        System.out.println("\n'" + arananEleman + "' elemaninin tekrar sayisi: " + tekrarSayisi);

        // Kuyrugun elemanlarini Stack'e aktarma
        System.out.println("\nKuyrugun elemanlarini Stack'e aktariyoruz...");
        while (!kuyruk.isEmpty()) {
            stack.push(kuyruk.poll());
        }
        System.out.println("Stack: " + stack + " --- Kuyruk: " + kuyruk);

        // Stack'teki elemanlari tekrar Kuyruga aktarma
        while (!stack.isEmpty()) {
            kuyruk.offer(stack.pop());
        }

        System.out.println("\nStack: " + stack + " --- Kuyruk: " + kuyruk);
    }
}
