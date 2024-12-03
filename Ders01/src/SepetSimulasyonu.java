import java.util.HashSet;
import java.util.Set;

// Urun sinifi, bir urunun adini ve fiyatini tutar
class Urun {
	String ad; // Urunun adi
	double fiyat; // Urunun fiyati

	// Constructor: Yeni bir urun olusturmak icin kullanilir
	public Urun(String ad, double fiyat) {
		this.ad = ad;
		this.fiyat = fiyat;
	}

	// equals metodu: Iki urunun esit olup olmadigini kontrol eder
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { // Aynı referans ise esit
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) { // Sinif farkli ise esit degil
			return false;
		}
		Urun urun = (Urun) obj; 
		// Fiyatlar ve adlar ayni ise urunler esit kabul edilir
		return Double.compare(urun.fiyat, fiyat) == 0 && ad.equals(urun.ad);
	}

	// hashCode metodu: Urunun benzersiz, tekil, karakteristik bir kodunu olusturur
	@Override
	public int hashCode() {
		int result;
		long temp;
		// Adin hash kodu ve fiyat degeri kullanilarak hashCode olusturulur
		result = ad.hashCode();
		temp = Double.doubleToLongBits(fiyat);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}

public class SepetSimulasyonu {
	
	public static void main(String[] args) {
		
		// HashSet kullanilarak bir urun sepeti olusturulur
		Set<Urun> sepet = new HashSet<>();

		// Sepete eklenmek uzere urunler olusturulur
		Urun urun1 = new Urun("Kalem", 5.0);
		Urun urun2 = new Urun("Defter", 10.0);
		Urun urun3 = new Urun("Kalem", 5.0); // urun1 ile ayni
		Urun urun4 = new Urun("Defter", 15.0);

		// Urunler sepete eklenir
		sepetEkle(sepet, urun1);
		sepetEkle(sepet, urun2);
		sepetEkle(sepet, urun3);
		sepetEkle(sepet, urun4);
		sepetEkle(sepet, urun2);

		// Sepetteki tum urunler gosterilir
		sepetiGoster(sepet);
	}

	/**
	 * Sepete urun ekleyen metod.
	 * Sepette ayni urun yoksa ekler, varsa bilgilendirme mesaji yazdirir.
	 * @param sepet Sepeti temsil eden Set
	 * @param urun Eklenecek urun
	 */
	public static void sepetEkle(Set<Urun> sepet, Urun urun) {
		// Sepette urun yoksa ekle
		if (!sepet.contains(urun)) {
			sepet.add(urun);
			System.out.println(urun.ad + " sepete eklendi.");
		} else {
			// Sepette urun zaten varsa bilgilendirme yap
			System.out.println(urun.ad + " zaten sepette bulunuyor.");
		}
	}

	/**
	 * Sepetteki urunleri ekrana yazdiran metod.
	 * @param sepet Sepeti temsil eden Set
	 */
	public static void sepetiGoster(Set<Urun> sepet) {
		System.out.println("Sepet Icerigi:");
		// Set'teki her bir urun icin ad ve fiyat yazdirilir
		for (Urun urun : sepet) {
			System.out.println(urun.ad + " - " + urun.fiyat);
		}
	}
}
