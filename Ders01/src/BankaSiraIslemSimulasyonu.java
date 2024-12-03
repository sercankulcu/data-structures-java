import java.util.LinkedList; 
import java.util.Queue;

// Musteri sinifi, musterilerin isimlerini ve islem surelerini saklar.
class Musteri {

	String isim; // Musterinin ismi
	Integer sure; // Musterinin islem suresi (saniye cinsinden)

	// Constructor: Yeni bir Musteri nesnesi olustururken isim ve sure bilgileri alinir.
	public Musteri(String isim, Integer sure) {
		this.isim = isim;
		this.sure = sure;
	}
}

// Banka sira islemlerini simulasyon olarak gosteren ana sinif
public class BankaSiraIslemSimulasyonu {

	public static void main(String[] args) throws InterruptedException {

		// Musteri sirasi tutmak icin bir kuyruk olusturuyoruz.
		Queue<Musteri> sira = new LinkedList<>();

		// Kuyruga musteriler ekleniyor.
		sira.offer(new Musteri("Ahmet", 1)); // Ahmet'in islem suresi 1 saniye
		sira.offer(new Musteri("Ayse", 2)); // Ayse'nin islem suresi 2 saniye
		sira.offer(new Musteri("Mehmet", 1)); // Mehmet'in islem suresi 1 saniye
		sira.offer(new Musteri("Fatma", 3)); // Fatma'nin islem suresi 3 saniye
		sira.offer(new Musteri("Ali", 2)); // Ali'nin islem suresi 2 saniye

		// Baslik bilgileri ekrana yazdiriliyor.
		System.out.println("Banka Girisi: Musteri Sirasi");
		System.out.println("----------------------------");

		// Kuyruk bosalana kadar musteriler sirayla islem yapar.
		while (!sira.isEmpty()) {
			// Siradaki musteri kuyruktan alinir.
			Musteri musteri = sira.poll();

			// Islem yapan musterinin bilgileri ekrana yazdirilir.
			System.out.println(musteri.isim + " siradayken islem yapiyor.");

			// Musterinin islem suresi boyunca beklenir.
			Thread.sleep(musteri.sure * 1000);

			// Musterinin islemi tamamlandiginda bilgi yazdirilir.
			System.out.println(musteri.isim + " islemini tamamladi.\n");
		}
	}
}
