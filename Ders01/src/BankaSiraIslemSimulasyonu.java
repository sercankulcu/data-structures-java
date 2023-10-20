import java.util.LinkedList;
import java.util.Queue;

public class BankaSiraIslemSimulasyonu {
	
	public static void main(String[] args) {
		
		Queue<Musteri> musteriSirası = new LinkedList<>();

		musteriSirası.offer(new Musteri("Ahmet"));
		musteriSirası.offer(new Musteri("Ayşe"));
		musteriSirası.offer(new Musteri("Mehmet"));
		musteriSirası.offer(new Musteri("Fatma"));
		musteriSirası.offer(new Musteri("Ali"));

		System.out.println("Banka Girişi: Müşteri Sırası");
		System.out.println("----------------------------");

		while (!musteriSirası.isEmpty()) {
			Musteri mevcutMusteri = musteriSirası.poll();
			System.out.println(mevcutMusteri.isim + " sıradayken işlem yapıyor.");

			// Burada müşterinin işlemleri simüle edilebilir, örneğin bir süre bekleme gibi.

			System.out.println(mevcutMusteri.isim + " işlemini tamamladı.\n");
		}
	}
}

class Musteri {
	String isim;

	public Musteri(String isim) {
		this.isim = isim;
	}
}
