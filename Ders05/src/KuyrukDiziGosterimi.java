
public class KuyrukDiziGosterimi {

	private int kapasite; // Kuyruğun maksimum boyutu
	private int[] kuyrukDizi; // Kuyruğu temsil eden dizi
	private int on; // Kuyruğun başı
	private int arka; // Kuyruğun sonu
	private int boyut; // Kuyruktaki eleman sayısı

	//Bu metod, bir kuyruğu bir dizi üzerinde temsil etmek için kullanılır.
	public KuyrukDiziGosterimi(int boyut) {
		// Kuyruğun kapasitesini belirlemek için kullanıcının verdiği boyutu alır
		kapasite = boyut;

		// Kuyruğu temsil edecek yeni bir dizi oluştur
		kuyrukDizi = new int[kapasite];

		// Kuyruğun başı ve sonunu işaretleyen indisleri başlangıç değerlerine ayarla
		on = 0;     // Kuyruğun önü, başlangıçta dizinin ilk elemanını işaret eder.
		arka = -1;  // Kuyruğun sonu, başlangıçta boş bir kuyruğu gösterir.

		// Kuyruğun boyutunu, başlangıçta 0 olarak belirle, çünkü kuyruk boş
		boyut = 0;
	}

	//Kuyruğa yeni bir eleman ekleme
	public void ekle(int deger) {
		// Eğer kuyruğun sonu, dizinin sonuna ulaştıysa, arka işaretçisini sıfırla
		if (arka == kapasite - 1) {
			arka = -1;
		}

		// Diziye yeni bir eleman eklemek için arka işaretçisini artır ve bu yere veriyi ekle
		kuyrukDizi[++arka] = deger;

		// Kuyruğun boyutunu bir artır çünkü yeni bir eleman eklendi
		boyut++;
	}

	//Kuyruktan bir eleman çıkarma
	public int cikar() {
		// Kuyruğun başındaki elemanı geçici bir değişkende sakla ve baş işaretçisini bir artır
		int gecici = kuyrukDizi[on++];

		// Eğer baş işareti dizinin sonuna ulaştıysa, baş işaretçisini sıfırla
		if (on == kapasite) {
			on = 0;
		}

		// Kuyruğun boyutunu bir azalt çünkü bir eleman çıkarıldı
		boyut--;

		// Çıkarılan elemanı döndür
		return gecici;
	}

	//Kuyruğun başındaki elemana bakma
	public int basaBak() {
		// Kuyruğun başındaki elemanı döndür
		return kuyrukDizi[on];
	}

	//Kuyruğun boş olup olmadığını kontrol etme
	public boolean bosMu() {
		// Kuyruğun boyutu 0 ise, kuyruk boştur ve true değeri döndür
		return boyut == 0;
	}

	//Kuyruğun dolu olup olmadığını kontrol etme
	public boolean doluMu() {
		// Kuyruğun boyutu kapasiteye eşitse, kuyruk doludur ve true değeri döndür
		return boyut == kapasite;
	}

	//Kuyruğun boyutunu döndürme
	public int boyut() {
		// Kuyruğun mevcut boyutunu döndür
		return boyut;
	}

	//Ana program başlangıcı
	public static void main(String[] args) {
		// 5 elemanlı bir kuyruk oluştur
		KuyrukDiziGosterimi kuyruk = new KuyrukDiziGosterimi(5);

		// Kuyruğa elemanları ekle
		kuyruk.ekle(10);
		kuyruk.ekle(20);
		kuyruk.ekle(30);
		kuyruk.ekle(40);

		// Kuyruğun başındaki elemanı ekrana yazdır
		System.out.println("Kuyruğun Başındaki Eleman: " + kuyruk.basaBak());

		// Kuyruktan eleman çıkar
		System.out.println("Kuyruktan Çıkan Eleman: " + kuyruk.cikar());
		System.out.println("Kuyruktan Çıkan Eleman: " + kuyruk.cikar());

		// Kuyruğun boş ve dolu olup olmadığını kontrol et ve sonuçları ekrana yazdır
		System.out.println("Kuyruk Boş mu? " + kuyruk.bosMu());
		System.out.println("Kuyruk Dolu mu? " + kuyruk.doluMu());

		// Kuyruğun mevcut boyutunu ekrana yazdır
		System.out.println("Kuyruk Boyutu: " + kuyruk.boyut());
	}
}
