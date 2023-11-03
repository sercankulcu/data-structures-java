
// Düğüm sınıfı - Tek yönlü bağlı liste düğümlerini temsil eden iç sınıf.
public class TekYonluDugum<E> {
	
	E veri;      // Düğümün içinde saklanan veri.
	TekYonluDugum<E> sonraki;  // Düğümün bir sonraki düğümüne işaret eden referans.

	TekYonluDugum(E veri) {
		this.veri = veri;
		this.sonraki = null;
	}
}
