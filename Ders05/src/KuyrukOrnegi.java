import java.util.LinkedList; 
import java.util.Queue;

public class KuyrukOrnegi {

	public static void main(String[] args) {
		// Bir Queue olusturalim (Kuyruk yapisi)
		Queue<String> kuyruk = new LinkedList<>();
		
		// Kuyruga eleman ekleyelim
		kuyruk.offer("Eleman 1");
		kuyruk.offer("Eleman 2");
		kuyruk.offer("Eleman 3");
		kuyruk.offer("Eleman 4");
		kuyruk.offer("Eleman 5");

		// Kuyrugun icerigini gosterelim
		System.out.println("Kuyrugun Icerigi: " + kuyruk);

		// Kuyrugun basindaki elemani alalim (poll metodu)
		String basindakiEleman = kuyruk.poll();
		System.out.println("Kuyrugun Basindaki Eleman: " + basindakiEleman);

		// Kuyrugun icerigini tekrar gosterelim
		System.out.println("Guncellenmis Kuyruk Icerigi: " + kuyruk);

		// Kuyrugun basindaki elemana erisim saglayalim, ancak kuyruktan cikarmadan (peek metodu)
		basindakiEleman = kuyruk.peek();
		System.out.println("Kuyrugun Basindaki Eleman (Cikarmadan): " + basindakiEleman);

		// Kuyrugun boyutunu alalim
		int kuyrukBoyutu = kuyruk.size();
		System.out.println("Kuyrugun Boyutu: " + kuyrukBoyutu);

		// Kuyrugu bosaltalim
		kuyruk.clear();
		System.out.println("Kuyruk Temizlendi");

		// Kuyrugun bos olup olmadigini kontrol edelim
		boolean kuyrukBosMu = kuyruk.isEmpty();
		System.out.println("Kuyruk Bos mu? " + kuyrukBosMu);
	}
}
