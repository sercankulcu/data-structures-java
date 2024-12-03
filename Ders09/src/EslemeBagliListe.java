import java.util.LinkedList;

// EslemeDugumu sinifi anahtar ve deger ciftini temsil eder
class EslemeDugumu<K, V> {
    K anahtar; // Anahtar
    V deger;   // Deger

    public EslemeDugumu(K anahtar, V deger) {
        this.anahtar = anahtar;
        this.deger = deger;
    }
}

public class EslemeBagliListe<K, V> {

    // Bagli liste kullanilarak bir esleme veri yapisi

    private LinkedList<EslemeDugumu<K, V>>[] tablo; // Hash tablosu
    private int boyut; // Esleme icindeki eleman sayisi
    private static final int BASLANGIC_BOYUT = 16; // Baslangic tablosu boyutu

    @SuppressWarnings("unchecked")
	public EslemeBagliListe() {
        // Yapici metot: Tabloyu baslatir
        tablo = new LinkedList[BASLANGIC_BOYUT];
        boyut = 0;
    }

    // Anahtar-deger cifti ekleme
    public void ekle(K anahtar, V deger) {
        int indeks = hash(anahtar); // Anahtar icin hash fonksiyonu
        if (tablo[indeks] == null) {
            tablo[indeks] = new LinkedList<>();
        }

        // Eger ayni anahtar zaten varsa, degeri guncelle
        for (EslemeDugumu<K, V> dugum : tablo[indeks]) {
            if (dugum.anahtar.equals(anahtar)) {
                dugum.deger = deger;
                return;
            }
        }

        // Yeni anahtar-deger cifti ekle
        tablo[indeks].add(new EslemeDugumu<>(anahtar, deger));
        boyut++;
    }

    // Anahtara gore degeri getir
    public V getir(K anahtar) {
        int indeks = hash(anahtar); // Anahtar icin hash fonksiyonu
        if (tablo[indeks] != null) {
            for (EslemeDugumu<K, V> dugum : tablo[indeks]) {
                if (dugum.anahtar.equals(anahtar)) {
                    return dugum.deger;
                }
            }
        }
        return null; // Anahtar bulunamazsa null dondur
    }

    // Anahtarin mevcut olup olmadigini kontrol et
    public boolean icerir(K anahtar) {
        int indeks = hash(anahtar); // Anahtar icin hash fonksiyonu
        if (tablo[indeks] != null) {
            for (EslemeDugumu<K, V> dugum : tablo[indeks]) {
                if (dugum.anahtar.equals(anahtar)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Anahtar-deger cifti silme
    public void sil(K anahtar) {
        int indeks = hash(anahtar); // Anahtar icin hash fonksiyonu
        if (tablo[indeks] != null) {
            LinkedList<EslemeDugumu<K, V>> zincir = tablo[indeks];
            for (EslemeDugumu<K, V> dugum : zincir) {
                if (dugum.anahtar.equals(anahtar)) {
                    zincir.remove(dugum); // Dugumu zincirden sil
                    boyut--;
                    return;
                }
            }
        }
    }

    // Esleme boyutunu dondur
    public int boyut() {
        return boyut;
    }

    // Hash fonksiyonu: Anahtar icin tablo indeksini hesaplar
    private int hash(K anahtar) {
        return Math.abs(anahtar.hashCode() % tablo.length);
    }

    // Esleme icerigini yazdir
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
        // EslemeBagliListe sinifi test ediliyor
        EslemeBagliListe<String, Integer> esleme = new EslemeBagliListe<>();
        esleme.ekle("Bir", 1);
        esleme.ekle("Iki", 2);
        esleme.ekle("Uc", 3);
        esleme.ekle("Dort", 4);
        esleme.ekle("Bes", 5);
        esleme.ekle("Alti", 6);
        esleme.ekle("Yedi", 7);
        esleme.ekle("Sekiz", 8);
        esleme.ekle("Dokuz", 9);
        esleme.ekle("On", 10);
        esleme.ekle("Onbir", 11);
        esleme.ekle("Oniki", 12);

        // Esleme icerigini yazdir
        esleme.yazdir();

        System.out.println("Boyut: " + esleme.boyut());
        System.out.println();

        // Anahtar silme islemi
        esleme.sil("Iki");
        esleme.yazdir();

        System.out.println("Boyut: " + esleme.boyut());
        System.out.println();
    }
}
