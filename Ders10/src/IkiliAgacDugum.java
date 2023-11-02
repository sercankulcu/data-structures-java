
public class IkiliAgacDugum {
	int veri;                 // Düğümün içerdiği veri
	IkiliAgacDugum solCocuk;   // Sol çocuk düğümün referansı
	IkiliAgacDugum sagCocuk;   // Sağ çocuk düğümün referansı

	public IkiliAgacDugum(int veri) {
		this.veri = veri;       // Düğümün verisini belirle
		this.solCocuk = null;   // Sol çocuk başlangıçta boş
		this.sagCocuk = null;   // Sağ çocuk başlangıçta boş
	}
}
