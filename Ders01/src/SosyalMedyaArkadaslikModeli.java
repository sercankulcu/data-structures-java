import java.util.*;

// Kullanici sinifi, bir kullanicinin adini ve onun arkadaslarini tutar
class Kullanici {
	String kullaniciAdi; // Kullanici adi
	Set<Kullanici> arkadaslar; // Kullanicinin arkadaslari

	// Constructor: Yeni bir kullanici nesnesi olusturur
	public Kullanici(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
		this.arkadaslar = new HashSet<>(); // Arkadaslar bir HashSet ile tutulur
	}

	// Bir kullaniciya arkadas eklemek icin kullanilir
	public void arkadasEkle(Kullanici arkadas) {
		arkadaslar.add(arkadas); // Kullaniciya arkadas ekle
		arkadas.arkadaslar.add(this); // Cift yonlu arkadaslik iliskisi (ikisi de birbirini arkadas olarak ekler)
	}

	// Kullanici adini yazdirmak icin toString metodu
	@Override
	public String toString() {
		return kullaniciAdi; // Kullanici adini dondur
	}
}

public class SosyalMedyaArkadaslikModeli {

	public static void main(String[] args) {
		
		// Farkli kullanicilar olusturulur
		Kullanici kullanici1 = new Kullanici("Ahmet");
		Kullanici kullanici2 = new Kullanici("Ayse");
		Kullanici kullanici3 = new Kullanici("Mehmet");
		Kullanici kullanici4 = new Kullanici("Fatma");
		Kullanici kullanici5 = new Kullanici("Ali");
		Kullanici kullanici6 = new Kullanici("Zeynep");
		Kullanici kullanici7 = new Kullanici("Emre");
		Kullanici kullanici8 = new Kullanici("Eda");
		Kullanici kullanici9 = new Kullanici("Burak");
		Kullanici kullanici10 = new Kullanici("Seda");

		// Arkadasliklar eklenir
		kullanici1.arkadasEkle(kullanici2); // Ahmet ve Ayse arkadas olur
		kullanici1.arkadasEkle(kullanici3); // Ahmet ve Mehmet arkadas olur
		kullanici2.arkadasEkle(kullanici4); // Ayse ve Fatma arkadas olur
		kullanici3.arkadasEkle(kullanici5); // Mehmet ve Ali arkadas olur
		kullanici4.arkadasEkle(kullanici6); // Fatma ve Zeynep arkadas olur
		kullanici5.arkadasEkle(kullanici7); // Ali ve Emre arkadas olur
		kullanici6.arkadasEkle(kullanici8); // Zeynep ve Eda arkadas olur
		kullanici7.arkadasEkle(kullanici9); // Emre ve Burak arkadas olur
		kullanici8.arkadasEkle(kullanici10); // Eda ve Seda arkadas olur
		kullanici9.arkadasEkle(kullanici1); // Burak ve Ahmet arkadas olur
		kullanici10.arkadasEkle(kullanici2); // Seda ve Ayse arkadas olur

		// Her kullanicinin arkadaslari yazdirilir
		System.out.println("Ahmet'in Arkadaslari: " + kullanici1.arkadaslar); // Ahmet'in arkadaslarini yazdir
		System.out.println("Ayse'nin Arkadaslari: " + kullanici2.arkadaslar); // Ayse'nin arkadaslarini yazdir
		System.out.println("Mehmet'in Arkadaslari: " + kullanici3.arkadaslar); // Mehmet'in arkadaslarini yazdir
		System.out.println("Fatma'nin Arkadaslari: " + kullanici4.arkadaslar); // Fatma'nin arkadaslarini yazdir
		System.out.println("Ali'nin Arkadaslari: " + kullanici5.arkadaslar); // Ali'nin arkadaslarini yazdir
		System.out.println("Zeynep'in Arkadaslari: " + kullanici6.arkadaslar); // Zeynep'in arkadaslarini yazdir
		System.out.println("Emre'nin Arkadaslari: " + kullanici7.arkadaslar); // Emre'nin arkadaslarini yazdir
		System.out.println("Eda'nin Arkadaslari: " + kullanici8.arkadaslar); // Eda'nin arkadaslarini yazdir
		System.out.println("Burak'in Arkadaslari: " + kullanici9.arkadaslar); // Burak'in arkadaslarini yazdir
		System.out.println("Seda'nin Arkadaslari: " + kullanici10.arkadaslar); // Seda'nin arkadaslarini yazdir
	}
}
