
// OncelikliKuyrukEleman sınıfı, öncelikli kuyruktaki her bir elemanı temsil eder.
public class OncelikliKuyrukEleman {
    // Elemanın veri ve öncelik değerlerini saklamak için kullanılan sınıf değişkenleri.
    String veri;
    int öncelik;

    // Constructor (Yapıcı Metod): Bir elemanı oluşturmak için veri ve öncelik değerlerini alır.
    OncelikliKuyrukEleman(String veri, int öncelik) {
        this.veri = veri;
        this.öncelik = öncelik;
    }
}
