
// Kırmızı-Siyah Ağaç (Red-Black Tree) düğüm yapısı
public class KirmiziSiyahDugum {
  
    int anahtar; // Düğümün anahtar değeri
    KirmiziSiyahDugum solCocuk; // Sol çocuk düğümü
    KirmiziSiyahDugum sagCocuk; // Sağ çocuk düğümü
    KirmiziSiyahDugum ebeveyn; // Ebeveyn düğüm
    int renk; // Renk değeri (0: siyah, 1: kırmızı)

    public KirmiziSiyahDugum(int anahtar) {
        this.anahtar = anahtar;
    }
}
