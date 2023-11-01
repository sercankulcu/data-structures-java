
public class ProbedHaritaSinifi<K, V> {
	private K[] anahtarlar;
	private V[] degerler;
	private int boyut;
	private static final int BASLANGIC_BOYUT = 16;

	public ProbedHaritaSinifi() {
		anahtarlar = (K[]) new Object[BASLANGIC_BOYUT];
		degerler = (V[]) new Object[BASLANGIC_BOYUT];
		boyut = 0;
	}

	// Anahtar-değer çifti eklemek
	public void ekle(K anahtar, V deger) {
		if (boyut >= anahtarlar.length) {
			genislet();
		}

		int indeks = sondajHash(anahtar);
		while (anahtarlar[indeks] != null) {
			indeks = (indeks + 1) % anahtarlar.length;
		}

		anahtarlar[indeks] = anahtar;
		degerler[indeks] = deger;
		boyut++;
	}

	// Anahtara göre değeri getirmek
	public V getir(K anahtar) {
		int indeks = sondajHash(anahtar);
		while (anahtarlar[indeks] != null) {
			if (anahtarlar[indeks].equals(anahtar)) {
				return degerler[indeks];
			}
			indeks = (indeks + 1) % anahtarlar.length;
		}
		return null;
	}

	// Anahtarı içerip içermediğini kontrol etmek
	public boolean icerir(K anahtar) {
		int indeks = sondajHash(anahtar);
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
		int indeks = sondajHash(anahtar);
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

	// Probed harita boyutunu döndürmek
	public int boyut() {
		return boyut;
	}

	// Sondaj hash işlemi: Anahtarın hash kodunu kullanarak indeksi hesaplamak
	private int sondajHash(K anahtar) {
		return (anahtar.hashCode() & 0x7FFFFFFF) % anahtarlar.length;
	}

	// Harita boyutunu genişletmek
	private void genislet() {
		int yeniBoyut = anahtarlar.length * 2;
		K[] yeniAnahtarlar = (K[]) new Object[yeniBoyut];
		V[] yeniDegerler = (V[]) new Object[yeniBoyut];

		for (int i = 0; i < anahtarlar.length; i++) {
			if (anahtarlar[i] != null) {
				K anahtar = anahtarlar[i];
				V deger = degerler[i];
				int indeks = sondajHash(anahtar);
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

	public void yazdir() {
		for (int i = 0; i < anahtarlar.length; i++) {
			if (anahtarlar[i] != null) {
				System.out.println("Index " + i + ": [" + anahtarlar[i] + " -> " + degerler[i] + "]");
			}
		}
	}


	public static void main(String[] args) {
		ProbedHaritaSinifi<String, Integer> harita = new ProbedHaritaSinifi<>();
		harita.ekle("Bir", 1);
		harita.ekle("İki", 2);
		harita.ekle("Üç", 3);
		harita.ekle("Dört", 4);
		harita.ekle("Beş", 5);
		harita.ekle("Altı", 6);
		harita.ekle("Yedi", 7);
		harita.ekle("Sekiz", 8);
		harita.ekle("Dokuz", 9);
		harita.ekle("On", 10);
		harita.ekle("Onbir", 11);
		harita.ekle("Oniki", 12);

		harita.yazdir();

		System.out.println("Boyut: " + harita.boyut());

		harita.sil("İki");
		harita.yazdir();

		System.out.println("Boyut: " + harita.boyut());
	}
}
