
public class MaksimumYigin {
	
	Integer[] heap; // Integer dizisi heap
	int elemanSayisi; // dugum sayisi

	// Yapici metot, kapasiteyi alir ve heap dizisini baslatir
	public MaksimumYigin(int kapasite) {
		heap = new Integer[kapasite + 1]; // heap dizisi olusturulur, kapasite + 1 uzunlugunda
		elemanSayisi = 0; // baslangicta eleman sayisi 0
	}

	// Kuyrugun bos olup olmadigini kontrol eder
	public boolean bosMu() {
		return elemanSayisi == 0; // eger n 0'a esitse, kuyruk bostur
	}

	// Kuyrugun mevcut boyutunu dondurur
	public int buyukluk() {
		return elemanSayisi; // eleman sayisini dondur
	}

	// Yeni bir eleman ekler
	public void ekle(int x) {
		if (elemanSayisi == heap.length - 1) { 
			boyutAyarla(2 * heap.length); // eger dizinin kapasitesi doluysa kapasiteyi iki katina cikar
		}
		elemanSayisi++; // yeni eleman icin indeks ayarla
		heap[elemanSayisi] = x; // elemani dizinin sonuna ekle
		yuzdur(elemanSayisi); // eklenen elemani uygun yere yerlestir
	}

	// Elemani yukari dogru hareket ettirerek dogru yere yerlestirir
	private void yuzdur(int k) {
		while (k > 1 && heap[k / 2] < heap[k]) { 
			int gecici = heap[k]; // gecici degiskende elemani sakla
			heap[k] = heap[k / 2]; // ebeveyn ile yer degistir
			heap[k / 2] = gecici; // ebeveyn elemani degistir
			k = k / 2; // bir ust seviyeye cik
		}
	}

	// En buyuk elemani cikartir
	public int sil() {
		int max = heap[1]; // kok elemani sakla
		yerDegistir(1, elemanSayisi); // kok elemani son elemanla degistir
		elemanSayisi--; // eleman sayisini azalt
		batir(1); // kok elemani uygun yere yerlestir
		heap[elemanSayisi + 1] = null; // eski kok elemani temizle
		if (elemanSayisi > 0 && (elemanSayisi == (heap.length - 1) / 4)) {
			boyutAyarla(heap.length / 2); // kapasiteyi gerekirse azalt
		}
		return max; // cikartilan elemani dondur
	}

	// Elemani asagi dogru hareket ettirerek dogru yere yerlestirir
	public void batir(int k) {
		while (2 * k <= elemanSayisi) { 
			int j = 2 * k; // sol cocugun indeksini al
			if (j < elemanSayisi && heap[j] < heap[j + 1]) {
				j++; // sag cocuk daha buyukse indeksini al
			}
			if (heap[k] >= heap[j]) {
				break; // dogru pozisyondaysa cik
			}
			yerDegistir(k, j); // elemanlari yer degistir
			k = j; // bir alt seviyeye in
		}
	}

	// Kapasiteyi ayarlamak icin yeni bir dizi olusturur
	private void boyutAyarla(int kapasite) {
		Integer[] gecici = new Integer[kapasite];
		for (int i = 0; i < heap.length; i++) {
			gecici[i] = heap[i]; // eski dizinin elemanlarini yeni diziye kopyala
		}
		heap = gecici; // yeni diziyi kullan
	}

	// Iki elemanin yerini degistirir
	public void yerDegistir(int a, int b) {
		int gecici = heap[a];
		heap[a] = heap[b];
		heap[b] = gecici;
	}

	// Dizi elemanlarini yazdirir
	public void yazdir() {
		for (int i = 1; i <= elemanSayisi; i++) {
			System.out.print(heap[i] + " ");
		}
	}

	public static void main(String[] args) {
		MaksimumYigin ok = new MaksimumYigin(3); // Kapasitesi 3 olan kuyruk olustur

		// Kuyruga eleman ekle
		ok.ekle(20);
		ok.ekle(15);
		ok.ekle(10);
		ok.ekle(13);
		ok.ekle(9);
		ok.ekle(7);
		ok.ekle(2);
		ok.ekle(5);
		ok.ekle(11);
		ok.ekle(8);
		ok.ekle(1);
		ok.ekle(23);
		ok.ekle(22);
		ok.ekle(8);

		System.out.println(ok.buyukluk()); // Kuyruk boyutunu yazdir
		ok.yazdir(); // Kuyrugu yazdir
		System.out.println();

		ok.sil(); // Maksimum elemani sil
		ok.yazdir(); // Kuyrugu yazdir
		System.out.println();

		ok.yazdir(); // Kuyrugu tekrar yazdir
	}
}
