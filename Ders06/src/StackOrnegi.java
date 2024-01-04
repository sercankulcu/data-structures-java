import java.util.Stack;

public class StackOrnegi {
	public static void main(String[] args) {
		// Stack oluşturma
		Stack<String> stack = new Stack<>();

		// Elemanlar eklemek (itme - push)
		stack.push("Eleman 1");
		stack.push("Eleman 2");
		stack.push("Eleman 3");

		// Stack içeriğini görüntüleme
		System.out.println("Stack: " + stack);

		// Stack'ten eleman çıkarmak (çekme - pop)
		String eleman = stack.pop();
		System.out.println("Çıkarılan Eleman: " + eleman);
		System.out.println("Güncel Stack: " + stack);

		// Stack'ten eleman çıkarmadan erişmek
		String tepeEleman = stack.peek();
		System.out.println("Tepe Eleman: " + tepeEleman);
		System.out.println("Stack Hala Aynı: " + stack);

		// Stack boş mu?
		boolean bosMu = stack.isEmpty();
		System.out.println("Stack Boş Mu? " + bosMu);
	}
}
