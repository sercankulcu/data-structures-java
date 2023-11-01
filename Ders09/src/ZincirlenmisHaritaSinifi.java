
import java.util.LinkedList;

public class ZincirlenmisHaritaSinifi<K, V> {
	private LinkedList<HaritaDüğümü<K, V>>[] tablo;
	private int boyut;
	private static final int BASLANGIC_BOYUT = 16;

	public ZincirlenmisHaritaSinifi() {
		tablo = new LinkedList[BASLANGIC_BOYUT];
		boyut = 0;
	}

	// Anahtar-değer çifti eklemek
	public void ekle(K anahtar, V deger) {
		int indeks = hash(anahtar);
		if (tablo[indeks] == null) {
			tablo[indeks] = new LinkedList<>();
		}

		for (HaritaDüğümü<K, V> dugum : tablo[indeks]) {
			if (dugum.anahtar.equals(anahtar)) {
				dugum.deger = deger;
				return;
			}
		}

		tablo[indeks].add(new HaritaDüğümü<>(anahtar, deger));
		boyut++;
	}

	// Anahtara göre değeri getirmek
	public V getir(K anahtar) {
		int indeks = hash(anahtar);
		if (tablo[indeks] != null) {
			for (HaritaDüğümü<K, V> dugum : tablo[indeks]) {
				if (dugum.anahtar.equals(anahtar)) {
					return dugum.deger;
				}
			}
		}
		return null;
	}

	// Anahtarı içerip içermediğini kontrol etmek
	public boolean icerir(K anahtar) {
		int indeks = hash(anahtar);
		if (tablo[indeks] != null) {
			for (HaritaDüğümü<K, V> dugum : tablo[indeks]) {
				if (dugum.anahtar.equals(anahtar)) {
					return true;
				}
			}
		}
		return false;
	}

	// Anahtar-değer çiftini silmek
	public void sil(K anahtar) {
		int indeks = hash(anahtar);
		if (tablo[indeks] != null) {
			LinkedList<HaritaDüğümü<K, V>> zincir = tablo[indeks];
			for (HaritaDüğümü<K, V> dugum : zincir) {
				if (dugum.anahtar.equals(anahtar)) {
					zincir.remove(dugum);
					boyut--;
					return;
				}
			}
		}
	}

	// Zincirlenmiş harita boyutunu döndürmek
	public int boyut() {
		return boyut;
	}

	// Hash fonksiyonu: Anahtarın hash kodunu hesaplamak
	private int hash(K anahtar) {
		return anahtar.hashCode() % tablo.length;
	}

	// Inner HaritaDüğümü sınıfı, anahtar-değer çiftlerini tutmak için
	private static class HaritaDüğümü<K, V> {
		K anahtar;
		V deger;

		public HaritaDüğümü(K anahtar, V deger) {
			this.anahtar = anahtar;
			this.deger = deger;
		}
	}

	public void yazdir() {
		for (int i = 0; i < tablo.length; i++) {
			if (tablo[i] != null) {
				System.out.print("Indeks " + i + ": ");
				LinkedList<HaritaDüğümü<K, V>> zincir = tablo[i];
				for (HaritaDüğümü<K, V> dugum : zincir) {
					System.out.print("[" + dugum.anahtar + " -> " + dugum.deger + "] ");
				}
				System.out.println();
			}
		}
	}


	public static void main(String[] args) {
		ZincirlenmisHaritaSinifi<String, Integer> harita = new ZincirlenmisHaritaSinifi<>();
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

