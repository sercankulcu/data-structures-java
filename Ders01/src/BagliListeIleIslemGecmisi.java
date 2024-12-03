// Islem sinifi, her islemi bir dugum olarak temsil eder.
class Islem { 

	String ad; // Islem adini tutar.
	Islem sonraki; // Bagli listedeki bir sonraki islemi isaret eder.

	// Constructor: Yeni bir Islem nesnesi olusturur ve adini atar.
	public Islem(String ad) {
		this.ad = ad;
		this.sonraki = null; // Varsayilan olarak sonraki islem bos (null) olarak ayarlanir.
	}
}

// Bagli liste kullanarak islem gecmisi yonetimi yapan sinif
class IslemGecmisi {
	Islem bas; // Bagli listenin baslangicini tutar.

	// Constructor: Islem gecmisini baslangicta bos olarak olusturur.
	public IslemGecmisi() {
		this.bas = null;
	}

	// Yeni bir islem ekler ve bu islemi bagli listenin basina koyar.
	public void islemEkle(String ad) {
		Islem yeniIslem = new Islem(ad); // Yeni bir Islem nesnesi olusturulur.
		yeniIslem.sonraki = bas; // Yeni islem mevcut listenin basina baglanir.
		bas = yeniIslem; // Yeni islem listenin basina atanir.
	}

	// Bagli listedeki tum islemleri ekrana yazdirir.
	public void gecmisiGoster() {
		Islem gecmisIslem = bas; // Gecmisi baslangictan itibaren kontrol eder.
		while (gecmisIslem != null) {
			System.out.println(gecmisIslem.ad); // Her islem adini ekrana yazdirir.
			gecmisIslem = gecmisIslem.sonraki; // Siradaki isleme gecer.
		}
	}
}

// Bagli liste yapisini kullanarak islem gecmisi yonetimi yapan ana program
public class BagliListeIleIslemGecmisi {

	public static void main(String[] args) {

		// Islem gecmisi yonetimi icin bir nesne olusturulur.
		IslemGecmisi islemGecmisi = new IslemGecmisi();

		// Islem gecmisine farkli islemler eklenir.
		islemGecmisi.islemEkle("Giris yapildi");
		islemGecmisi.islemEkle("Kullanici profilini guncelle");
		islemGecmisi.islemEkle("Mesaj gonderildi");

		// Islem gecmisi ekrana yazdirilir.
		System.out.println("Islem Gecmisi:");
		islemGecmisi.gecmisiGoster();
	}
}
