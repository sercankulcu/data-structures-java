
public class OgrenciDugum {
	
	Ogrenci ogr;                 // Düğümün içerdiği veri
	OgrenciDugum solCocuk;   // Sol çocuk düğümün referansı
	OgrenciDugum sagCocuk;   // Sağ çocuk düğümün referansı

	public OgrenciDugum(Ogrenci ogr) {
		this.ogr = ogr;       // Düğümün verisini belirle
		this.solCocuk = null;   // Sol çocuk başlangıçta boş
		this.sagCocuk = null;   // Sağ çocuk başlangıçta boş
	}
}