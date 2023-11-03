
// Bağlı liste düğümü
public class MatrisDugum<E> {
	
	E veri;
	MatrisDugum<E> sag;
	MatrisDugum<E> asagi;
	
	MatrisDugum(E veri) {
		this.veri = veri;
		this.sag = null;
		this.asagi = null;
	}
}