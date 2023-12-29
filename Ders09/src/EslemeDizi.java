
public class EslemeDizi<K, V> {
	
	private Object[] anahtarlar;
	private Object[] degerler;
	private int boyut;
	
	private final int BASLANGIC_BOYUT = 16;

	public EslemeDizi() {
		anahtarlar = new Object[BASLANGIC_BOYUT];
		degerler = new Object[BASLANGIC_BOYUT];
		boyut = 0;
	}

	// Anahtar-değer çifti ekleme
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

	// Anahtara göre değer getirme
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

	// Anahtarı içerip içermediğini kontrol etme
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

	// Anahtar-değer çiftini silme
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

	// Esleme boyutunu döndür
	public int boyut() {
		return boyut;
	}

	// Esleme veri yapısı içeriğini yazdır
	public void yazdir() {
		
		for (int i = 0; i < anahtarlar.length; i++) {
			if (anahtarlar[i] != null) {
				System.out.print(anahtarlar[i] + " -> " + degerler[i] + " | ");
			}
		}
		System.out.println();
	}

	// Esleme veri yapısı boyutunu genişlet
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
		
		EslemeDizi<String, Integer> esleme = new EslemeDizi<>();
		esleme.ekle("Bir", 1);
		esleme.ekle("İki", 2);
		esleme.ekle("Üç", 3);
		esleme.ekle("Dört", 4);
		esleme.ekle("Beş", 5);

		esleme.yazdir();

		System.out.println("Boyut: " + esleme.boyut());

		esleme.sil("İki");
		esleme.yazdir();

		System.out.println("Boyut: " + esleme.boyut());
	}
}
