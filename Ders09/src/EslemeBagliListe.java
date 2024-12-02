
import java.util.LinkedList;

class EslemeDugumu<K, V> {
	K anahtar;
	V deger;

	public EslemeDugumu(K anahtar, V deger) {
		this.anahtar = anahtar;
		this.deger = deger;
	}
}

public class EslemeBagliListe<K, V> {
	
	private LinkedList<EslemeDugumu<K, V>>[] tablo;
	private int boyut;
	private static final int BASLANGIC_BOYUT = 16;

	public EslemeBagliListe() {
		tablo = new LinkedList[BASLANGIC_BOYUT];
		boyut = 0;
	}

	// Anahtar-değer çifti ekleme
	public void ekle(K anahtar, V deger) {
		int indeks = hash(anahtar);
		if (tablo[indeks] == null) {
			tablo[indeks] = new LinkedList<>();
		}

		for (EslemeDugumu<K, V> dugum : tablo[indeks]) {
			if (dugum.anahtar.equals(anahtar)) {
				dugum.deger = deger;
				return;
			}
		}

		tablo[indeks].add(new EslemeDugumu<>(anahtar, deger));
		boyut++;
	}

	// Anahtara göre değer getirme
	public V getir(K anahtar) {
		int indeks = hash(anahtar);
		if (tablo[indeks] != null) {
			for (EslemeDugumu<K, V> dugum : tablo[indeks]) {
				if (dugum.anahtar.equals(anahtar)) {
					return dugum.deger;
				}
			}
		}
		return null;
	}

	// Anahtarı içerip içermediğini kontrol etme
	public boolean icerir(K anahtar) {
		int indeks = hash(anahtar);
		if (tablo[indeks] != null) {
			for (EslemeDugumu<K, V> dugum : tablo[indeks]) {
				if (dugum.anahtar.equals(anahtar)) {
					return true;
				}
			}
		}
		return false;
	}

	// Anahtar-değer çiftini silme
	public void sil(K anahtar) {
		int indeks = hash(anahtar);
		if (tablo[indeks] != null) {
			LinkedList<EslemeDugumu<K, V>> zincir = tablo[indeks];
			for (EslemeDugumu<K, V> dugum : zincir) {
				if (dugum.anahtar.equals(anahtar)) {
					zincir.remove(dugum);
					boyut--;
					return;
				}
			}
		}
	}

	// Esleme boyutunu döndür
	public int boyut() {
		return boyut;
	}

	// Hash fonksiyonu
	private int hash(K anahtar) {
		return anahtar.hashCode() % tablo.length;
	}

	public void yazdir() {
		for (int i = 0; i < tablo.length; i++) {
			if (tablo[i] != null) {
				System.out.print("Indeks " + i + ": ");
				LinkedList<EslemeDugumu<K, V>> zincir = tablo[i];
				for (EslemeDugumu<K, V> dugum : zincir) {
					System.out.print("[" + dugum.anahtar + " -> " + dugum.deger + "] ");
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		EslemeBagliListe<String, Integer> esleme = new EslemeBagliListe<>();
		esleme.ekle("Bir", 1);
		esleme.ekle("İki", 2);
		esleme.ekle("Üç", 3);
		esleme.ekle("Dört", 4);
		esleme.ekle("Beş", 5);
		esleme.ekle("Altı", 6);
		esleme.ekle("Yedi", 7);
		esleme.ekle("Sekiz", 8);
		esleme.ekle("Dokuz", 9);
		esleme.ekle("On", 10);
		esleme.ekle("Onbir", 11);
		esleme.ekle("Oniki", 12);
		
		esleme.yazdir();

		System.out.println("Boyut: " + esleme.boyut());
		System.out.println();
		
		esleme.sil("İki");
		esleme.yazdir();

		System.out.println("Boyut: " + esleme.boyut());
		System.out.println();
	}
}

