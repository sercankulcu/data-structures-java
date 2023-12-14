
public class Dugum<E> {
	E veri;  // Düğümün içerdiği veri
	Dugum<E> sol;  // Sol alt düğüm
	Dugum<E> sag;  // Sağ alt düğüm

	public Dugum(E veri) {
		this.veri = veri;  // Yapıcı metot: Düğümün verisini ata
		sol = null;  // Başlangıçta sol alt düğümü boş olarak işaretle
		sag = null;  // Başlangıçta sağ alt düğümü boş olarak işaretle
	}
}

