import java.util.*;

class Kullanici {
	String kullaniciAdi;
	Set<Kullanici> arkadaslar;

	public Kullanici(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
		this.arkadaslar = new HashSet<>();
	}

	public void arkadasEkle(Kullanici arkadas) {
		arkadaslar.add(arkadas);
		arkadas.arkadaslar.add(this); // Çift yönlü arkadaşlık ilişkisi
	}

	@Override
	public String toString() {
		return kullaniciAdi;
	}
}

public class SosyalMedyaArkadaslikModeli {

	public static void main(String[] args) {

		Kullanici kullanici1 = new Kullanici("Ahmet");
		Kullanici kullanici2 = new Kullanici("Ayşe");
		Kullanici kullanici3 = new Kullanici("Mehmet");
		Kullanici kullanici4 = new Kullanici("Fatma");

		kullanici1.arkadasEkle(kullanici2);
		kullanici1.arkadasEkle(kullanici3);
		kullanici2.arkadasEkle(kullanici4);

		System.out.println("Ahmet'in Arkadaşları: " + kullanici1.arkadaslar);
		System.out.println("Ayşe'nin Arkadaşları: " + kullanici2.arkadaslar);
		System.out.println("Mehmet'in Arkadaşları: " + kullanici3.arkadaslar);
		System.out.println("Fatma'nın Arkadaşları: " + kullanici4.arkadaslar);
	}
}
