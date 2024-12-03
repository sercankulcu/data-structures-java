
class Eleman<T> {
	// Elemanin veri ve oncelik degerlerini saklamak icin kullanilan sinif degiskenleri.
	T veri;
	int oncelik;
	// Constructor (Yapici Metod): Bir elemani olusturmak icin veri ve oncelik degerlerini alir.
	Eleman(T veri, int oncelik) {
		this.veri = veri;
		this.oncelik = oncelik;
	}
}

public class OncelikliKuyrukDizi<E> {

	private Object[] dizi;
	private int boyut;

	public OncelikliKuyrukDizi() {
		dizi = new Object[10];
		boyut = 0;
	}

	//oncelikli kuyruga yeni bir eleman ekleme
	public void ekle(E veri, int oncelik) {
		// Eger kuyrugun boyutu dizi uzunluguna ulastiysa, diziyi genislet
		if (boyut == dizi.length) {
			diziGenislet();
		}
		// Yeni bir oncelikli kuyruk elemani olustur
		Eleman<E> yeniEleman = new Eleman<>(veri, oncelik);
		// Elemanin eklenecegi indeksi belirlemek icin baslangic olarak boyut kullanilir.
		int indeks = boyut;
		// Yeni elemanin onceligi, dizideki diger elemanlarin oncelikleri ile karsilastirilarak
		// dogru indeks bulunur ve eleman dogru konuma eklenir.
		while (indeks > 0 && oncelik < ((Eleman<?>) dizi[indeks - 1]).oncelik) {
			dizi[indeks] = dizi[indeks - 1];
			indeks--;
		}
		// Yeni eleman, uygun indekse eklenir ve kuyrugun boyutu bir artirilir.
		dizi[indeks] = yeniEleman;
		boyut++;
	}

	//oncelikli kuyruktan bir eleman cikarma
	public E cikar() {
		// Eger oncelikli kuyruk bossa, bir istisna (exception) firlat
		if (bosMu()) {
			throw new IllegalStateException("oncelikli kuyruk bos");
		}
		// cikarilan elemanin verisini tutacak bir degisken olustur
		@SuppressWarnings("unchecked")
		E cikarilan = (E) ((Eleman<?>) dizi[0]).veri;
		// Dizideki elemanlar bir onceki elemanin yerine kaydirilarak bir eleman cikarilir.
		for (int i = 0; i < boyut - 1; i++) {
			dizi[i] = dizi[i + 1];
		}
		// Son eleman null olarak ayarlanir ve kuyrugun boyutu bir azaltilir.
		dizi[boyut - 1] = null;
		boyut--;
		// cikarilan elemanin verisi dondurulur.
		return cikarilan;
	}

	//oncelikli kuyrugun bos olup olmadigini kontrol etme
	public boolean bosMu() {
		// Eger kuyruk boyutu 0 ise, kuyruk bostur ve true degeri dondurulur.
		return boyut == 0;
	}

	//Diziyi genisletme
	private void diziGenislet() {
		// Dizi boyutunu iki katina cikarmak icin yeni bir boyut hesaplanir.
		int yeniBoyut = dizi.length * 2;

		// Yeni bir dizi olusturulur ve boyutu yeni boyut kadar yapilir.
		Object[] yeniDizi = new Object[yeniBoyut];

		// Mevcut dizideki elemanlar yeni diziye kopyalanir.
		for (int i = 0; i < boyut; i++) {
			yeniDizi[i] = dizi[i];
		}

		// Yeni dizi, mevcut dizi ile degistirilir.
		dizi = yeniDizi;
	}

	//oncelikli kuyrugun icerigini gosterme
	public void kuyruguGoster() {
		// Eger oncelikli kuyruk bossa, "oncelikli Kuyruk bos." mesaji ekrana yazdirilir
		if (bosMu()) {
			System.out.println("oncelikli Kuyruk bos.");
			return;
		}

		System.out.print("oncelikli Kuyruk ogeleri: ");

		// Kuyruktaki her elemanin verisini ve onceligini ekrana yazdir
		for (int i = 0; i < boyut; i++) {
			System.out.print("(" + (String)((Eleman<?>) dizi[i]).veri + ", oncelik: " + ((Eleman<?>) dizi[i]).oncelik + ") ");
		}

		// oncelikli kuyruktaki tum elemanlar yazildiktan sonra bir satir atlanir.
		System.out.println();
	}


	public static void main(String[] args) {

		OncelikliKuyrukDizi<String> kuyruk = new OncelikliKuyrukDizi<String>();

		// oncelikli kuyruga (priority queue) ogeleri ekle (enqueue)
		kuyruk.ekle("oge 1", 3); // oncelik: 3
		kuyruk.ekle("oge 2", 1); // oncelik: 1
		kuyruk.ekle("oge 3", 2); // oncelik: 2
		kuyruk.ekle("oge 4", 1); // oncelik: 1
		kuyruk.ekle("oge 5", 2); // oncelik: 2

		// oncelikli kuyruktaki ogeleri goster
		kuyruk.kuyruguGoster();

		// oncelikli kuyruktan (priority queue) ogeleri cikar (dequeue)
		String cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);

		// oncelikli kuyruktaki guncel ogeleri goster
		kuyruk.kuyruguGoster();

		// oncelikli kuyruktan (priority queue) ogeleri cikar (dequeue)
		cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);

		// oncelikli kuyruktaki guncel ogeleri goster
		kuyruk.kuyruguGoster();

		// oncelikli kuyruktan (priority queue) ogeleri cikar (dequeue)
		cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);

		// oncelikli kuyruktaki guncel ogeleri goster
		kuyruk.kuyruguGoster();

		// oncelikli kuyruktan (priority queue) ogeleri cikar (dequeue)
		cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);

		// oncelikli kuyruktaki guncel ogeleri goster
		kuyruk.kuyruguGoster();

		// oncelikli kuyrugun bos olup olmadigini kontrol et
		boolean bosMu = kuyruk.bosMu();
		System.out.println("oncelikli Kuyruk Bos mu? " + bosMu);

		// oncelikli kuyruktan (priority queue) ogeleri cikar (dequeue)
		cikarilanOge = kuyruk.cikar();
		System.out.println("cikarilan oge: " + cikarilanOge);

		// oncelikli kuyruktaki guncel ogeleri goster
		kuyruk.kuyruguGoster();

		// oncelikli kuyrugun bos olup olmadigini kontrol et
		bosMu = kuyruk.bosMu();
		System.out.println("oncelikli Kuyruk Bos mu? " + bosMu);
	}
}
