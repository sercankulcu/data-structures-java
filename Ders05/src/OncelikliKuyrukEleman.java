
// OncelikliKuyrukEleman sınıfı, öncelikli kuyruktaki her bir elemanı temsil eder.
public class OncelikliKuyrukEleman<E> {
	// Elemanın veri ve öncelik değerlerini saklamak için kullanılan sınıf değişkenleri.
	E veri;
	int oncelik;

	// Constructor (Yapıcı Metod): Bir elemanı oluşturmak için veri ve öncelik değerlerini alır.
	OncelikliKuyrukEleman(E veri, int oncelik) {
		this.veri = veri;
		this.oncelik = oncelik;
	}
}
