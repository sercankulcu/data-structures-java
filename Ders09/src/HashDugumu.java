
public class HashDugumu {
    // HashDugumu sınıfı, Hash tablosu düğümünün temsilidir.

    Integer anahtar; // Anahtarın tutulduğu değişken
    String deger;    // Değerin tutulduğu değişken
    HashDugumu sonraki; // Bağlantıyı sağlayan sonraki düğüm

    public HashDugumu(Integer anahtar, String deger) {
        // Yapıcı metot - Yeni bir HashDugumu nesnesi oluşturur.
        this.anahtar = anahtar;
        this.deger = deger;
        this.sonraki = null;
    }
}
