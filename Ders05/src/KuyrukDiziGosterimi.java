
public class KuyrukDiziGosterimi {

	private int kapasite; // Kuyrugun maksimum boyutu
	private int[] kuyrukDizi; // Kuyrugu temsil eden dizi
	private int on; // Kuyrugun basi
	private int arka; // Kuyrugun sonu
	private int boyut; // Kuyruktaki eleman sayisi

	//Bu metod, bir kuyrugu bir dizi uzerinde temsil etmek için kullanilir.
	public KuyrukDiziGosterimi(int boyut) {
		// Kuyrugun kapasitesini belirlemek için kullanicinin verdigi boyutu alir
		kapasite = boyut;

		// Kuyrugu temsil edecek yeni bir dizi olustur
		kuyrukDizi = new int[kapasite];

		// Kuyrugun basi ve sonunu isaretleyen indisleri baslangiç degerlerine ayarla
		on = 0;     // Kuyrugun onu, baslangiçta dizinin ilk elemanini isaret eder.
		arka = -1;  // Kuyrugun sonu, baslangiçta bos bir kuyrugu gosterir.

		// Kuyrugun boyutunu, baslangiçta 0 olarak belirle, çunku kuyruk bos
		boyut = 0;
	}

	//Kuyruga yeni bir eleman ekleme
	public void ekle(int deger) {
		// Eger kuyrugun sonu, dizinin sonuna ulastiysa, arka isaretçisini sifirla
		if (arka == kapasite - 1) {
			arka = -1;
		}

		// Diziye yeni bir eleman eklemek için arka isaretçisini artir ve bu yere veriyi ekle
		kuyrukDizi[++arka] = deger;

		// Kuyrugun boyutunu bir artir çunku yeni bir eleman eklendi
		boyut++;
	}

	//Kuyruktan bir eleman çikarma
	public int cikar() {
		// Kuyrugun basindaki elemani geçici bir degiskende sakla ve bas isaretçisini bir artir
		int gecici = kuyrukDizi[on++];

		// Eger bas isareti dizinin sonuna ulastiysa, bas isaretçisini sifirla
		if (on == kapasite) {
			on = 0;
		}

		// Kuyrugun boyutunu bir azalt çunku bir eleman çikarildi
		boyut--;

		// Çikarilan elemani dondur
		return gecici;
	}

	//Kuyrugun basindaki elemana bakma
	public int basaBak() {
		// Kuyrugun basindaki elemani dondur
		return kuyrukDizi[on];
	}

	//Kuyrugun bos olup olmadigini kontrol etme
	public boolean bosMu() {
		// Kuyrugun boyutu 0 ise, kuyruk bostur ve true degeri dondur
		return boyut == 0;
	}

	//Kuyrugun dolu olup olmadigini kontrol etme
	public boolean doluMu() {
		// Kuyrugun boyutu kapasiteye esitse, kuyruk doludur ve true degeri dondur
		return boyut == kapasite;
	}

	//Kuyrugun boyutunu dondurme
	public int boyut() {
		// Kuyrugun mevcut boyutunu dondur
		return boyut;
	}

	//Ana program baslangici
	public static void main(String[] args) {
		// 5 elemanli bir kuyruk olustur
		KuyrukDiziGosterimi kuyruk = new KuyrukDiziGosterimi(5);

		// Kuyruga elemanlari ekle
		kuyruk.ekle(10);
		kuyruk.ekle(20);
		kuyruk.ekle(30);
		kuyruk.ekle(40);

		// Kuyrugun basindaki elemani ekrana yazdir
		System.out.println("Kuyrugun Basindaki Eleman: " + kuyruk.basaBak());

		// Kuyruktan eleman çikar
		System.out.println("Kuyruktan Çikan Eleman: " + kuyruk.cikar());
		System.out.println("Kuyruktan Çikan Eleman: " + kuyruk.cikar());

		// Kuyrugun bos ve dolu olup olmadigini kontrol et ve sonuçlari ekrana yazdir
		System.out.println("Kuyruk Bos mu? " + kuyruk.bosMu());
		System.out.println("Kuyruk Dolu mu? " + kuyruk.doluMu());

		// Kuyrugun mevcut boyutunu ekrana yazdir
		System.out.println("Kuyruk Boyutu: " + kuyruk.boyut());
		
		System.out.println("Kuyruktan Çikan Eleman: " + kuyruk.cikar());
		System.out.println("Kuyruktan Çikan Eleman: " + kuyruk.cikar());
		
		System.out.println("Kuyruk Bos mu? " + kuyruk.bosMu());
	}
}
