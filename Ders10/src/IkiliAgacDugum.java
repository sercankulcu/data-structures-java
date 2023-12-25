

public class IkiliAgacDugum<E extends Comparable<E>> {
	E veri;                 // Düğümün içerdiği veri
	IkiliAgacDugum<E> solCocuk;   // Sol çocuk düğümün referansı
	IkiliAgacDugum<E> sagCocuk;   // Sağ çocuk düğümün referansı
	public IkiliAgacDugum(E veri) {
		this.veri = veri;       // Düğümün verisini belirle
		this.solCocuk = null;   // Sol çocuk başlangıçta boş
		this.sagCocuk = null;   // Sağ çocuk başlangıçta boş
	}
}
