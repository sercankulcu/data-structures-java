
public class BagliListeIleIslemGecmisi {
	
	public static void main(String[] args) {
		
		IslemGecmisi islemGecmisi = new IslemGecmisi();

		islemGecmisi.islemEkle("Giriş yapıldı");
		islemGecmisi.islemEkle("Kullanıcı profilini güncelle");
		islemGecmisi.islemEkle("Mesaj gönderildi");

		System.out.println("İşlem Geçmişi:");
		islemGecmisi.gecmisiGoster();
	}
}

class Islem {
	String ad;
	Islem sonraki;

	public Islem(String ad) {
		this.ad = ad;
		this.sonraki = null;
	}
}

class IslemGecmisi {
	Islem bas;

	public IslemGecmisi() {
		this.bas = null;
	}

	public void islemEkle(String ad) {
		Islem yeniIslem = new Islem(ad);
		yeniIslem.sonraki = bas;
		bas = yeniIslem;
	}

	public void gecmisiGoster() {
		Islem gecmisIslem = bas;
		while (gecmisIslem != null) {
			System.out.println(gecmisIslem.ad);
			gecmisIslem = gecmisIslem.sonraki;
		}
	}
}
