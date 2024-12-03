import java.util.Stack;

public class StackOrnegi {
	public static void main(String[] args) {
		// Stack olusturuluyor
		Stack<String> stack = new Stack<>();

		// Stack'e eleman ekleme (itme - push)
		stack.push("Eleman 1");
		stack.push("Eleman 2");
		stack.push("Eleman 3");

		// Stack'in iceriğini ekrana yazdırma
		System.out.println("Stack: " + stack);

		// Stack'ten eleman cikarma (çekme - pop)
		String eleman = stack.pop();
		System.out.println("Cikartilan Eleman: " + eleman);
		System.out.println("Guncellenmis Stack: " + stack);

		// Stack'ten eleman cikarmadan sadece en tepedeki elemana erisim (peek)
		String tepeEleman = stack.peek();
		System.out.println("Tepe Eleman: " + tepeEleman);
		System.out.println("Stack Hala Aynı: " + stack);

		// Stack boş mu? Kontrol ediliyor
		boolean bosMu = stack.isEmpty();
		System.out.println("Stack Bos Mu? " + bosMu);

		// Stack'e yeni elemanlar ekleniyor
		stack.push("Eleman 4");
		stack.push("Eleman 5");
		System.out.println("Yeni Elemanlar Eklendi: " + stack);

		// Stack'ten bir eleman daha çıkartılıyor
		eleman = stack.pop();
		System.out.println("Cikartilan Son Eleman: " + eleman);
		System.out.println("Guncellenmis Stack: " + stack);

		// Stack boyutunu kontrol edelim
		int stackBoyutu = stack.size();
		System.out.println("Stack Boyutu: " + stackBoyutu);

		// Stack'teki elemanlar tek tek yazdırılıyor
		System.out.print("Stack'teki Elemanlar: ");
		for (String s : stack) {
			System.out.print(s + " ");
		}
		System.out.println();

		// Stack'i tamamen temizleyelim
		stack.clear();
		System.out.println("Stack Temizlendi: " + stack);
	}
}
