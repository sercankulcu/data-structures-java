
public class OncelikliKuyruk {

	private OncelikliKuyrukEleman[] dizi;
	private int boyut;
	final int BASLANGIC_KAPASITESI = 10;

	public OncelikliKuyruk() {
		dizi = new OncelikliKuyrukEleman[BASLANGIC_KAPASITESI];
		boyut = 0;
	}

	//Öncelikli kuyruğa yeni bir eleman ekleme
	public void ekle(String veri, int öncelik) {
		// Eğer kuyruğun boyutu dizi uzunluğuna ulaştıysa, diziyi genişlet
		if (boyut == dizi.length) {
			diziGenislet();
		}

		// Yeni bir öncelikli kuyruk elemanı oluştur
		OncelikliKuyrukEleman yeniEleman = new OncelikliKuyrukEleman(veri, öncelik);

		// Elemanın ekleneceği indeksi belirlemek için başlangıç olarak boyut kullanılır.
		int indeks = boyut;

		// Yeni elemanın önceliği, dizideki diğer elemanların öncelikleri ile karşılaştırılarak
		// doğru indeks bulunur ve eleman doğru konuma eklenir.
		while (indeks > 0 && öncelik < dizi[indeks - 1].öncelik) {
			dizi[indeks] = dizi[indeks - 1];
			indeks--;
		}

		// Yeni eleman, uygun indekse eklenir ve kuyruğun boyutu bir artırılır.
		dizi[indeks] = yeniEleman;
		boyut++;
	}

	//Öncelikli kuyruktan bir eleman çıkarma
	public String cikar() {
		// Eğer öncelikli kuyruk boşsa, bir istisna (exception) fırlat
		if (bosMu()) {
			throw new IllegalStateException("Öncelikli kuyruk boş");
		}

		// Çıkarılan elemanın verisini tutacak bir değişken oluştur
		String cikarilan = dizi[0].veri;

		// Dizideki elemanlar bir önceki elemanın yerine kaydırılarak bir eleman çıkarılır.
		for (int i = 0; i < boyut - 1; i++) {
			dizi[i] = dizi[i + 1];
		}

		// Son eleman null olarak ayarlanır ve kuyruğun boyutu bir azaltılır.
		dizi[boyut - 1] = null;
		boyut--;

		// Çıkarılan elemanın verisi döndürülür.
		return cikarilan;
	}

	//Öncelikli kuyruğun boş olup olmadığını kontrol etme
	public boolean bosMu() {
		// Eğer kuyruk boyutu 0 ise, kuyruk boştur ve true değeri döndürülür.
		return boyut == 0;
	}

	//Diziyi genişletme
	private void diziGenislet() {
		// Dizi boyutunu iki katına çıkarmak için yeni bir boyut hesaplanır.
		int yeniBoyut = dizi.length * 2;

		// Yeni bir dizi oluşturulur ve boyutu yeni boyut kadar yapılır.
		OncelikliKuyrukEleman[] yeniDizi = new OncelikliKuyrukEleman[yeniBoyut];

		// Mevcut dizideki elemanlar yeni diziye kopyalanır.
		for (int i = 0; i < boyut; i++) {
			yeniDizi[i] = dizi[i];
		}

		// Yeni dizi, mevcut dizi ile değiştirilir.
		dizi = yeniDizi;
	}

	//Öncelikli kuyruğun içeriğini gösterme
	public void kuyruguGoster() {
		// Eğer öncelikli kuyruk boşsa, "Öncelikli Kuyruk boş." mesajı ekrana yazdırılır
		if (bosMu()) {
			System.out.println("Öncelikli Kuyruk boş.");
			return;
		}

		System.out.print("Öncelikli Kuyruk Öğeleri: ");

		// Kuyruktaki her elemanın verisini ve önceliğini ekrana yazdır
		for (int i = 0; i < boyut; i++) {
			System.out.print("(" + dizi[i].veri + ", Öncelik: " + dizi[i].öncelik + ") ");
		}

		// Öncelikli kuyruktaki tüm elemanlar yazıldıktan sonra bir satır atlanır.
		System.out.println();
	}


	public static void main(String[] args) {

		OncelikliKuyruk kuyruk = new OncelikliKuyruk();

		// Öncelikli kuyruğa (priority queue) öğeleri ekle (enqueue)
		kuyruk.ekle("Öğe 1", 3); // Öncelik: 3
		kuyruk.ekle("Öğe 2", 1); // Öncelik: 1
		kuyruk.ekle("Öğe 3", 2); // Öncelik: 2
		kuyruk.ekle("Öğe 4", 1); // Öncelik: 1

		// Öncelikli kuyruktaki öğeleri göster
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkar (dequeue)
		String cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);

		// Öncelikli kuyruktaki güncel öğeleri göster
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkar (dequeue)
		cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);

		// Öncelikli kuyruktaki güncel öğeleri göster
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruktan (priority queue) öğeleri çıkar (dequeue)
		cikarilanOge = kuyruk.cikar();
		System.out.println("Çıkarılan Öğe: " + cikarilanOge);

		// Öncelikli kuyruktaki güncel öğeleri göster
		kuyruk.kuyruguGoster();

		// Öncelikli kuyruğun boş olup olmadığını kontrol et
		boolean bosMu = kuyruk.bosMu();
		System.out.println("Öncelikli Kuyruk Boş mu? " + bosMu);
	}
}
