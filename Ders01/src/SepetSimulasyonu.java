import java.util.HashSet;
import java.util.Set;

class Urun {
	String ad;
	double fiyat;

	public Urun(String ad, double fiyat) {
		this.ad = ad;
		this.fiyat = fiyat;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Urun urun = (Urun) obj;
		return Double.compare(urun.fiyat, fiyat) == 0 && ad.equals(urun.ad);
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = ad.hashCode();
		temp = Double.doubleToLongBits(fiyat);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}

public class SepetSimulasyonu {
	
	public static void main(String[] args) {
		
		Set<Urun> sepet = new HashSet<>();

		Urun urun1 = new Urun("Kalem", 5.0);
		Urun urun2 = new Urun("Defter", 10.0);
		Urun urun3 = new Urun("Kalem", 5.0);

		sepetEkle(sepet, urun1);
		sepetEkle(sepet, urun2);
		sepetEkle(sepet, urun3);

		sepetiGoster(sepet);
	}

	public static void sepetEkle(Set<Urun> sepet, Urun urun) {
		if (!sepet.contains(urun)) {
			sepet.add(urun);
			System.out.println(urun.ad + " sepete eklendi.");
		} else {
			System.out.println(urun.ad + " zaten sepette bulunuyor.");
		}
	}

	public static void sepetiGoster(Set<Urun> sepet) {
		System.out.println("Sepet İçeriği:");
		for (Urun urun : sepet) {
			System.out.println(urun.ad + " - " + urun.fiyat);
		}
	}
}
