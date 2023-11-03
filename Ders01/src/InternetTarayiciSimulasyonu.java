class WebSayfasi {
	String url; // Web sayfasının URL'si
	WebSayfasi sol; // Sol çocuk web sayfası
	WebSayfasi sag; // Sağ çocuk web sayfası

	public WebSayfasi(String url) {
		this.url = url; // URL'i ayarla
		this.sol = null; // Sol çocuk başlangıçta boş
		this.sag = null; // Sağ çocuk başlangıçta boş
	}
}


public class InternetTarayiciSimulasyonu {
	private WebSayfasi kok;

	public void ziyaretEkle(String url) {
		kok = ekle(kok, url);
	}

	private WebSayfasi ekle(WebSayfasi simdikiSayfa, String url) {
		if (simdikiSayfa == null) {
			return new WebSayfasi(url);
		}

		int sonuc = url.compareTo(simdikiSayfa.url);

		if (sonuc < 0) {
			simdikiSayfa.sol = ekle(simdikiSayfa.sol, url);
		} else if (sonuc > 0) {
			simdikiSayfa.sag = ekle(simdikiSayfa.sag, url);
		}

		return simdikiSayfa;
	}

	public void gezinmeGecmisiGoster() {
		System.out.println("Internet Tarayıcı Gezinme Geçmişi (Alfabetik Sıra):");
		gezinmeGecmisiYazdir(kok);
	}

	private void gezinmeGecmisiYazdir(WebSayfasi simdikiSayfa) {
		if (simdikiSayfa != null) {
			gezinmeGecmisiYazdir(simdikiSayfa.sol);
			System.out.println(simdikiSayfa.url);
			gezinmeGecmisiYazdir(simdikiSayfa.sag);
		}
	}

	public static void main(String[] args) {
		InternetTarayiciSimulasyonu tarayici = new InternetTarayiciSimulasyonu();
		tarayici.ziyaretEkle("www.google.com");
		tarayici.ziyaretEkle("www.openai.org");
		tarayici.ziyaretEkle("www.yahoo.com");
		tarayici.ziyaretEkle("www.apple.com");
		tarayici.ziyaretEkle("www.facebook.com");

		tarayici.gezinmeGecmisiGoster();
	}
}
