
public class OncelikliKuyruk<E> {

	public class Eleman<T> {
		// Elemanın veri ve öncelik değerlerini saklamak için kullanılan sınıf değişkenleri.
		T veri;
		int oncelik;
		// Constructor (Yapıcı Metod): Bir elemanı oluşturmak için veri ve öncelik değerlerini alır.
		Eleman(T veri, int oncelik) {
			this.veri = veri;
			this.oncelik = oncelik;
		}
	}

	private Object[] dizi;
	private int boyut;

	public OncelikliKuyruk() {
		dizi = new Object[10];
		boyut = 0;
	}

	//Öncelikli kuyruğa yeni bir eleman ekleme
	public void ekle(E veri, int oncelik) {
		// Eğer kuyruğun boyutu dizi uzunluğuna ulaştıysa, diziyi genişlet
		if (boyut == dizi.length) {
			diziGenislet();
		}
		// Yeni bir öncelikli kuyruk elemanı oluştur
		Eleman<E> yeniEleman = new Eleman<>(veri, oncelik);
		// Elemanın ekleneceği indeksi belirlemek için başlangıç olarak boyut kullanılır.
		int indeks = boyut;
		// Yeni elemanın önceliği, dizideki diğer elemanların öncelikleri ile karşılaştırılarak
		// doğru indeks bulunur ve eleman doğru konuma eklenir.
		while (indeks > 0 && oncelik < ((Eleman) dizi[indeks - 1]).oncelik) {
			dizi[indeks] = dizi[indeks - 1];
			indeks--;
		}
		// Yeni eleman, uygun indekse eklenir ve kuyruğun boyutu bir artırılır.
		dizi[indeks] = yeniEleman;
		boyut++;
	}

	//Öncelikli kuyruktan bir eleman çıkarma
	public E cikar() {
		// Eğer öncelikli kuyruk boşsa, bir istisna (exception) fırlat
		if (bosMu()) {
			throw new IllegalStateException("Öncelikli kuyruk boş");
		}
		// Çıkarılan elemanın verisini tutacak bir değişken oluştur
		E cikarilan = (E) ((Eleman) dizi[0]).veri;
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
		Object[] yeniDizi = new Object[yeniBoyut];

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
			System.out.print("(" + (String)((Eleman) dizi[i]).veri + ", Öncelik: " + ((Eleman) dizi[i]).oncelik + ") ");
		}

		// Öncelikli kuyruktaki tüm elemanlar yazıldıktan sonra bir satır atlanır.
		System.out.println();
	}


	public static void main(String[] args) {

		OncelikliKuyruk<String> kuyruk = new OncelikliKuyruk<>();

		// Öncelikli kuyruğa (priority queue) öğeleri ekle (enqueue)
		kuyruk.ekle("Öğe 1", 3); // Öncelik: 3
		kuyruk.ekle("Öğe 2", 1); // Öncelik: 1
		kuyruk.ekle("Öğe 3", 2); // Öncelik: 2
		kuyruk.ekle("Öğe 4", 1); // Öncelik: 1
		kuyruk.ekle("Öğe 5", 2); // Öncelik: 2

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
