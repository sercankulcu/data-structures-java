
// AVL Ağacı düğüm sınıfı, ağaç yapısının temel yapı taşlarını oluşturur.
public class AvlAgacDugum {

  // Ağaçtaki düğümün değerini temsil eden anahtar değeri
  int anahtar;

  // Düğümün sol çocuğunu temsil eden referans
  AvlAgacDugum solCocuk;

  // Düğümün sağ çocuğunu temsil eden referans
  AvlAgacDugum sagCocuk;

  // AVL ağacında kullanılan yükseklik bilgisini saklayan değişken
  int yukseklik;

  // Yapıcı metod: Belirtilen anahtar değeriyle bir AVL ağacı düğümü oluşturur
  public AvlAgacDugum(int anahtar) {
    this.anahtar = anahtar;
  }
}
