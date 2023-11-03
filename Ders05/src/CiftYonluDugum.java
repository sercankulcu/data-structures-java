
// Çift yönlü bağlı listenin düğümünü temsil eden iç içe sınıf
public class CiftYonluDugum<E> {
	E veri;
	CiftYonluDugum<E> onceki;
	CiftYonluDugum<E> sonraki;

	// Düğüm sınıfının kurucu fonksiyonu
	CiftYonluDugum(E veri) {
		this.veri = veri;
		this.onceki = null;
		this.sonraki = null;
	}
}