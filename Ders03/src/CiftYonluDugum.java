
// Çift yönlü bağlı listenin düğümünü temsil eden iç içe sınıf
public class CiftYonluDugum {
	int veri;
	CiftYonluDugum onceki;
	CiftYonluDugum sonraki;

	// Düğüm sınıfının kurucu fonksiyonu
	CiftYonluDugum(int veri) {
		this.veri = veri;
		this.onceki = null;
		this.sonraki = null;
	}
}