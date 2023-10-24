
public class KuyrukDiziGosterimi {
	
	private int maxBoyut; // Kuyruğun maksimum boyutu
	private int[] kuyrukDizi; // Kuyruğu temsil eden dizi
	private int on; // Kuyruğun başı
	private int arka; // Kuyruğun sonu
	private int elemanSayisi; // Kuyruktaki eleman sayısı

	public KuyrukDiziGosterimi(int boyut) {
		maxBoyut = boyut;
		kuyrukDizi = new int[maxBoyut];
		on = 0;
		arka = -1;
		elemanSayisi = 0;
	}

	public void ekle(int deger) {
		if (arka == maxBoyut - 1) {
			arka = -1;
		}
		kuyrukDizi[++arka] = deger;
		elemanSayisi++;
	}

	public int cikar() {
		int gecici = kuyrukDizi[on++];
		if (on == maxBoyut) {
			on = 0;
		}
		elemanSayisi--;
		return gecici;
	}

	public int basaBak() {
		return kuyrukDizi[on];
	}

	public boolean bosMu() {
		return elemanSayisi == 0;
	}

	public boolean doluMu() {
		return elemanSayisi == maxBoyut;
	}

	public int boyut() {
		return elemanSayisi;
	}

	public static void main(String[] args) {
		KuyrukDiziGosterimi kuyruk = new KuyrukDiziGosterimi(5);

		kuyruk.ekle(10);
		kuyruk.ekle(20);
		kuyruk.ekle(30);
		kuyruk.ekle(40);

		System.out.println("Kuyruğun Başındaki Eleman: " + kuyruk.basaBak());

		System.out.println("Kuyruktan Çıkan Eleman: " + kuyruk.cikar());
		System.out.println("Kuyruktan Çıkan Eleman: " + kuyruk.cikar());

		System.out.println("Kuyruk Boş mu? " + kuyruk.bosMu());
		System.out.println("Kuyruk Dolu mu? " + kuyruk.doluMu());
		System.out.println("Kuyruk Boyutu: " + kuyruk.boyut());
	}
}
