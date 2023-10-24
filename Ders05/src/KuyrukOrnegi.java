import java.util.LinkedList;
import java.util.Queue;

public class KuyrukOrnegi {

	public static void main(String[] args) {
		// Bir Queue oluşturalım
		Queue<String> kuyruk = new LinkedList<>();
		
		// Kuyruğa eleman ekleyelim
		kuyruk.offer("Eleman 1");
		kuyruk.offer("Eleman 2");
		kuyruk.offer("Eleman 3");
		kuyruk.offer("Eleman 4");
		kuyruk.offer("Eleman 5");

		// Kuyruğun içeriğini gösterelim
		System.out.println("Kuyruğun İçeriği: " + kuyruk);

		// Kuyruğun başındaki elemanı alalım
		String basindakiEleman = kuyruk.poll();
		System.out.println("Kuyruğun Başındaki Eleman: " + basindakiEleman);

		// Kuyruğun içeriğini tekrar gösterelim
		System.out.println("Güncellenmiş Kuyruk İçeriği: " + kuyruk);

		// Kuyruğun başındaki elemana erişelim, ancak kuyruktan çıkarmadan
		String basindakiEleman2 = kuyruk.peek();
		System.out.println("Kuyruğun Başındaki Eleman (Çıkarılmadan): " + basindakiEleman2);

		// Kuyruğun boyutunu alalım
		int kuyrukBoyutu = kuyruk.size();
		System.out.println("Kuyruğun Boyutu: " + kuyrukBoyutu);

		// Kuyruğu boşaltalım
		kuyruk.clear();
		System.out.println("Kuyruk Temizlendi");

		// Kuyruğun boş olduğunu kontrol edelim
		boolean kuyrukBosMu = kuyruk.isEmpty();
		System.out.println("Kuyruk Boş mu? " + kuyrukBosMu);
	}
}
