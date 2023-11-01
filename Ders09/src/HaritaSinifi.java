
public class HaritaSinifi<K, V> {
	private Object[] anahtarlar;
	private Object[] degerler;
	private int boyut;
	private final int BASLANGIC_BOYUT = 16;

	public HaritaSinifi() {
		anahtarlar = new Object[BASLANGIC_BOYUT];
		degerler = new Object[BASLANGIC_BOYUT];
		boyut = 0;
	}

	// Anahtar-değer çifti eklemek
	public void ekle(K anahtar, V deger) {
		if (boyut >= anahtarlar.length) {
			genislet();
		}

		int indeks = anahtar.hashCode() % anahtarlar.length;
		while (anahtarlar[indeks] != null) {
			if (anahtarlar[indeks].equals(anahtar)) {
				degerler[indeks] = deger;
				return;
			}
			indeks = (indeks + 1) % anahtarlar.length;
		}

		anahtarlar[indeks] = anahtar;
		degerler[indeks] = deger;
		boyut++;
	}

	// Anahtara göre değeri getirmek
	public V getir(K anahtar) {
		int indeks = anahtar.hashCode() % anahtarlar.length;
		while (anahtarlar[indeks] != null) {
			if (anahtarlar[indeks].equals(anahtar)) {
				return (V) degerler[indeks];
			}
			indeks = (indeks + 1) % anahtarlar.length;
		}
		return null;
	}

	// Anahtarı içerip içermediğini kontrol etmek
	public boolean icerir(K anahtar) {
		int indeks = anahtar.hashCode() % anahtarlar.length;
		while (anahtarlar[indeks] != null) {
			if (anahtarlar[indeks].equals(anahtar)) {
				return true;
			}
			indeks = (indeks + 1) % anahtarlar.length;
		}
		return false;
	}

	// Anahtar-değer çiftini silmek
	public void sil(K anahtar) {
		int indeks = anahtar.hashCode() % anahtarlar.length;
		while (anahtarlar[indeks] != null) {
			if (anahtarlar[indeks].equals(anahtar)) {
				anahtarlar[indeks] = null;
				degerler[indeks] = null;
				boyut--;
				return;
			}
			indeks = (indeks + 1) % anahtarlar.length;
		}
	}

	// Harita boyutunu döndürmek
	public int boyut() {
		return boyut;
	}

	// Harita içeriğini yazdırmak
	public void yazdir() {
		for (int i = 0; i < anahtarlar.length; i++) {
			if (anahtarlar[i] != null) {
				System.out.print(anahtarlar[i] + " -> " + degerler[i] + " | ");
			}
		}
		System.out.println();
	}

	// Harita boyutunu genişletmek
	private void genislet() {
		int yeniBoyut = anahtarlar.length * 2;
		Object[] yeniAnahtarlar = new Object[yeniBoyut];
		Object[] yeniDegerler = new Object[yeniBoyut];

		for (int i = 0; i < anahtarlar.length; i++) {
			if (anahtarlar[i] != null) {
				K anahtar = (K) anahtarlar[i];
				V deger = (V) degerler[i];
				int indeks = anahtar.hashCode() % yeniBoyut;
				while (yeniAnahtarlar[indeks] != null) {
					indeks = (indeks + 1) % yeniBoyut;
				}
				yeniAnahtarlar[indeks] = anahtar;
				yeniDegerler[indeks] = deger;
			}
		}

		anahtarlar = yeniAnahtarlar;
		degerler = yeniDegerler;
	}

	public static void main(String[] args) {
		HaritaSinifi<String, Integer> harita = new HaritaSinifi<>();
		harita.ekle("Bir", 1);
		harita.ekle("İki", 2);
		harita.ekle("Üç", 3);
		harita.ekle("Dört", 4);
		harita.ekle("Beş", 5);

		harita.yazdir();

		System.out.println("Boyut: " + harita.boyut());

		harita.sil("İki");
		harita.yazdir();

		System.out.println("Boyut: " + harita.boyut());
	}
}
