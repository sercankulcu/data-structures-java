import java.util.LinkedList;
import java.util.Queue;

public class BankaSiraIslemSimulasyonu {
	
	public static void main(String[] args) {
		
		Queue<Musteri> sira = new LinkedList<>();

		sira.offer(new Musteri("Ahmet"));
		sira.offer(new Musteri("Ayşe"));
		sira.offer(new Musteri("Mehmet"));
		sira.offer(new Musteri("Fatma"));
		sira.offer(new Musteri("Ali"));

		System.out.println("Banka Girişi: Müşteri Sırası");
		System.out.println("----------------------------");

		while (!sira.isEmpty()) {
			Musteri musteri = sira.poll();
			System.out.println(musteri.isim + " sıradayken işlem yapıyor.");

			// Burada müşterinin işlemleri simüle edilebilir, örneğin bir süre bekleme gibi.

			System.out.println(musteri.isim + " işlemini tamamladı.\n");
		}
	}
}

class Musteri {
	String isim;

	public Musteri(String isim) {
		this.isim = isim;
	}
}
