
// WebSayfasi sinifi, bir web sayfasini ve cocuk sayfalarini temsil eder.
class WebSayfasi { 

	String url; // Web sayfasinin URL'sini tutar.
	WebSayfasi sol; // Sol cocuk web sayfasi (URL alfabetik siralamada daha kucuk)
	WebSayfasi sag; // Sag cocuk web sayfasi (URL alfabetik siralamada daha buyuk)

	// Constructor: Yeni bir WebSayfasi nesnesi olusturur ve URL'ini atar.
	public WebSayfasi(String url) {
		this.url = url; // URL ayarlanir.
		this.sol = null; // Baslangicta sol cocuk yok.
		this.sag = null; // Baslangicta sag cocuk yok.
	}
}

// Internet tarayicisini ve gezinme gecmisini yoneten ana sinif
public class InternetTarayiciSimulasyonu {

	private WebSayfasi kok; // Gezinti agacinin kok dugumu

	// Yeni bir URL'yi agaca ekler.
	public void ziyaretEkle(String url) {
		kok = ekle(kok, url); // Ekleme islemi recursive olarak yapilir.
	}

	// Recursive olarak URL'yi dogru pozisyona ekleyen yardimci metod
	private WebSayfasi ekle(WebSayfasi simdikiSayfa, String url) {
		// Eger mevcut dugum bos ise, yeni bir WebSayfasi olusturulur.
		if (simdikiSayfa == null) {
			return new WebSayfasi(url);
		}

		// Mevcut URL ile karsilastir.
		int sonuc = url.compareTo(simdikiSayfa.url);

		// Alfabetik siralamaya gore sol veya sag alt agaca ekle.
		if (sonuc < 0) {
			simdikiSayfa.sol = ekle(simdikiSayfa.sol, url); // Sol cocuga ekle.
		} else if (sonuc > 0) {
			simdikiSayfa.sag = ekle(simdikiSayfa.sag, url); // Sag cocuga ekle.
		}

		return simdikiSayfa; // Mevcut sayfa geri dondurulur.
	}

	// Gezinti gecmisini ekrana alfabetik sirayla yazdirir.
	public void gezinmeGecmisiGoster() {
		System.out.println("Internet Tarayici Gezinme Gecmisi (Alfabetik Sira):");
		gezinmeGecmisiYazdir(kok); // Recursive yardimci metod cagrilir.
	}

	// Recursive olarak agaci gezip URL'leri yazdiran yardimci metod
	private void gezinmeGecmisiYazdir(WebSayfasi simdikiSayfa) {
		if (simdikiSayfa != null) {
			gezinmeGecmisiYazdir(simdikiSayfa.sol); // Sol cocugu yazdir.
			System.out.println(simdikiSayfa.url); // Mevcut URL'yi yazdir.
			gezinmeGecmisiYazdir(simdikiSayfa.sag); // Sag cocugu yazdir.
		}
	}

	// Programin calisma noktasi
	public static void main(String[] args) {
		// Yeni bir tarayici simulasyonu olusturulur.
		InternetTarayiciSimulasyonu tarayici = new InternetTarayiciSimulasyonu();

		// Gezinme gecmisine URL'ler eklenir.
		tarayici.ziyaretEkle("www.google.com");
		tarayici.ziyaretEkle("www.openai.org");
		tarayici.ziyaretEkle("www.yahoo.com");
		tarayici.ziyaretEkle("www.apple.com");
		tarayici.ziyaretEkle("www.facebook.com");

		// Gezinme gecmisi alfabetik sirayla ekrana yazdirilir.
		tarayici.gezinmeGecmisiGoster();
	}
}
