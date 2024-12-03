public class EslemeDizi<K, V> {

    // EslemeDizi sinifi, anahtar-deger cifti depolayan basit bir veri yapisidir.

    private Object[] anahtarlar; // Anahtarlari tutan dizi
    private Object[] degerler;   // Degerleri tutan dizi
    private int boyut;           // Esleme icerisindeki eleman sayisi

    private static final int BASLANGIC_BOYUT = 16; // Baslangic dizisi boyutu

    public EslemeDizi() {
        // Yapici metot: Anahtarlar ve degerler dizisini baslatir
        anahtarlar = new Object[BASLANGIC_BOYUT];
        degerler = new Object[BASLANGIC_BOYUT];
        boyut = 0;
    }

    // Anahtar-deger cifti ekleme
    public void ekle(K anahtar, V deger) {
        // Eger mevcut boyut dizinin kapasitesini astiysa, genisletme islemi yapilir
        if (boyut >= anahtarlar.length) {
            genislet();
        }

        // Anahtarin hash koduna gore indeks belirlenir
        int indeks = anahtar.hashCode() % anahtarlar.length;
        while (anahtarlar[indeks] != null) {
            // Eger ayni anahtar varsa, sadece degeri guncelle
            if (anahtarlar[indeks].equals(anahtar)) {
                degerler[indeks] = deger;
                return;
            }
            indeks = (indeks + 1) % anahtarlar.length;
        }

        // Yeni anahtar-deger cifti ekle
        anahtarlar[indeks] = anahtar;
        degerler[indeks] = deger;
        boyut++;
    }

    // Anahtara gore degeri getir
    @SuppressWarnings("unchecked")
	public V getir(K anahtar) {
        // Anahtar hash koduna gore dizide aranir
        int indeks = anahtar.hashCode() % anahtarlar.length;
        while (anahtarlar[indeks] != null) {
            if (anahtarlar[indeks].equals(anahtar)) {
                return (V) degerler[indeks];
            }
            indeks = (indeks + 1) % anahtarlar.length;
        }
        return null; // Anahtar bulunamazsa null dondur
    }

    // Anahtarin mevcut olup olmadigini kontrol et
    public boolean icerir(K anahtar) {
        // Anahtar hash koduna gore kontrol edilir
        int indeks = anahtar.hashCode() % anahtarlar.length;
        while (anahtarlar[indeks] != null) {
            if (anahtarlar[indeks].equals(anahtar)) {
                return true;
            }
            indeks = (indeks + 1) % anahtarlar.length;
        }
        return false;
    }

    // Anahtar-deger cifti silme
    public void sil(K anahtar) {
        // Anahtar hash koduna gore dizide aranir
        int indeks = anahtar.hashCode() % anahtarlar.length;
        while (anahtarlar[indeks] != null) {
            if (anahtarlar[indeks].equals(anahtar)) {
                // Anahtar ve deger null yapilarak silinir
                anahtarlar[indeks] = null;
                degerler[indeks] = null;
                boyut--;
                return;
            }
            indeks = (indeks + 1) % anahtarlar.length;
        }
    }

    // Esleme boyutunu dondur
    public int boyut() {
        return boyut;
    }

    // Esleme icerigini yazdir
    public void yazdir() {
        for (int i = 0; i < anahtarlar.length; i++) {
            if (anahtarlar[i] != null) {
                System.out.print(anahtarlar[i] + " -> " + degerler[i] + " | ");
            }
        }
        System.out.println();
    }

    // Esleme kapasitesini genislet
    private void genislet() {
        int yeniBoyut = anahtarlar.length * 2; // Yeni dizi boyutu
        Object[] yeniAnahtarlar = new Object[yeniBoyut];
        Object[] yeniDegerler = new Object[yeniBoyut];

        // Eski dizilerdeki elemanlari yeni dizilere tasi
        for (int i = 0; i < anahtarlar.length; i++) {
            if (anahtarlar[i] != null) {
                @SuppressWarnings("unchecked")
				K anahtar = (K) anahtarlar[i];
                @SuppressWarnings("unchecked")
				V deger = (V) degerler[i];
                int indeks = anahtar.hashCode() % yeniBoyut;
                while (yeniAnahtarlar[indeks] != null) {
                    indeks = (indeks + 1) % yeniBoyut;
                }
                yeniAnahtarlar[indeks] = anahtar;
                yeniDegerler[indeks] = deger;
            }
        }

        // Yeni diziler eski dizilerin yerine gecer
        anahtarlar = yeniAnahtarlar;
        degerler = yeniDegerler;
    }

    public static void main(String[] args) {
        // EslemeDizi sinifi test 
        EslemeDizi<String, Integer> esleme = new EslemeDizi<>();
        esleme.ekle("Bir", 1);
        esleme.ekle("Iki", 2);
        esleme.ekle("Uc", 3);
        esleme.ekle("Dort", 4);
        esleme.ekle("Bes", 5);

        // Esleme icerigini yazdir
        esleme.yazdir();

        System.out.println("Boyut: " + esleme.boyut());

        // Bir anahtar sil
        esleme.sil("Iki");
        esleme.yazdir();

        System.out.println("Boyut: " + esleme.boyut());
    }
}
