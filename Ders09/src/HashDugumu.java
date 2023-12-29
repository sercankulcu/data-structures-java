
public class HashDugumu {
    // HashDugumu sınıfı, Hash tablosunda her bir elemanı temsil eder
    Integer anahtar; // Anahtarın tutulduğu değişken
    String deger;    // Değerin tutulduğu değişken
    HashDugumu sonraki; // Bağlantıyı sağlayan sonraki düğüm

    public HashDugumu(Integer anahtar, String deger) {
        this.anahtar = anahtar;
        this.deger = deger;
        this.sonraki = null;
    }
}
