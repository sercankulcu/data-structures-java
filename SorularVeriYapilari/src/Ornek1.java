
import java.util.Stack;

public class Ornek1 {
	
    public static void main(String args[]) {
    	
        // Ana stack olustur
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        // Stack'i yazdir
        System.out.println("Baslangic stack: " + stack);

        // En ustteki elemani cikar ve yazdir
        System.out.println("Pop islemi (ustten eleman alindi): " + stack.pop());

        // En ustteki elemani kontrol et
        System.out.println("Peek islemi (ustteki eleman): " + stack.peek());

        // "A" elemanini cikar
        System.out.println("Remove islemi (A cikarildi): " + stack.remove("A"));

        // Kalan elemanlardan birini al
        System.out.println("Pop islemi (ustten eleman alindi): " + stack.pop());

        // Yeni bir eleman ekle
        System.out.println("Add islemi (D eklendi): " + stack.add("D"));

        // "B" elemanini cikarmaya calis
        System.out.println("Remove islemi (B cikarilmasi denendi): " + stack.remove("B"));

        // Stack durumunu yazdir
        System.out.println("Guncel stack: " + stack);

        // Yeni stack olustur ve kopyala
        Stack<String> yeniStack = new Stack<>();
        yeniStack.addAll(stack);
        System.out.println("Yeni stack (kopyalanmis): " + yeniStack);

        // Yeni stack'e eleman ekle ve farki goster
        yeniStack.push("E");
        System.out.println("Yeni stack'e eleman eklendi (E): " + yeniStack);
        System.out.println("Eski stack ayni kaldi: " + stack);

        stack.push("F");
        System.out.println("Push islemi (F eklendi): ");
        
        // Stack'i iterasyonla yazdir
        System.out.println("Stack elemanlari iterasyonla:");
        for (String eleman : stack) {
            System.out.println("- " + eleman);
        }

        // Stack'i ters cevir ve yazdir
        Stack<String> tersStack = new Stack<>();
        while (!stack.isEmpty()) {
            tersStack.push(stack.pop());
        }
        System.out.println("Ters cevrilmis stack: " + tersStack);

        // Ters stack'ten eleman cikararak yazdir
        System.out.println("Ters stack'ten eleman cikar:");
        while (!tersStack.isEmpty()) {
            System.out.println("Cikarilan eleman: " + tersStack.pop());
        }
    }
}
