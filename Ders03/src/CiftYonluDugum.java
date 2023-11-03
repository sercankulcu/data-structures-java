
// Çift yönlü bağlı listenin düğümünü temsil eden sınıf
public class CiftYonluDugum<E> {
	E veri;               // Düğümün içerdiği veriyi temsil eden değişken.
	CiftYonluDugum<E> onceki;  // Önceki düğümün referansını saklayan değişken.
	CiftYonluDugum<E> sonraki; // Sonraki düğümün referansını saklayan değişken.

	// Düğüm sınıfının kurucu fonksiyonu
	CiftYonluDugum(E veri) {
		this.veri = veri;    // Düğümün veri alanına veriyi ata
		this.onceki = null;  // Önceki düğüm başlangıçta null olarak ayarla
		this.sonraki = null; // Sonraki düğüm başlangıçta null olarak ayarla
	}
}
