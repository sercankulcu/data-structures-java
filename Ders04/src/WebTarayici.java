import java.util.Stack;

public class WebTarayici {
	private Stack<String> ileriGecmis;
	private Stack<String> geriGecmis;
	private String simdikiSayfa;

	public WebTarayici() {
		ileriGecmis = new Stack<>();
		geriGecmis = new Stack<>();
		simdikiSayfa = "Anasayfa";
	}

	public void git(String url) {
		geriGecmis.push(simdikiSayfa);
		simdikiSayfa = url;
		ileriGecmis.clear();
		System.out.println("Gidilen sayfa: " + simdikiSayfa);
	}

	public void geriGit() {
		if (!geriGecmis.isEmpty()) {
			ileriGecmis.push(simdikiSayfa);
			simdikiSayfa = geriGecmis.pop();
			System.out.println("Şu anki sayfa: " + simdikiSayfa);
		} else {
			System.out.println("Geri gidilecek sayfa yok.");
		}
	}

	public void ileriGit() {
		if (!ileriGecmis.isEmpty()) {
			geriGecmis.push(simdikiSayfa);
			simdikiSayfa = ileriGecmis.pop();
			System.out.println("Şu anki sayfa: " + simdikiSayfa);
		} else {
			System.out.println("İleri gidilecek sayfa yok.");
		}
	}

	public String getSimdikiSayfa() {
		return simdikiSayfa;
	}

	public static void main(String[] args) {
		WebTarayici tarayici = new WebTarayici();

		tarayici.git("https://www.google.com");
		tarayici.git("https://www.facebook.com");
		tarayici.git("https://www.twitter.com");

		System.out.println("Şu anki sayfa: " + tarayici.getSimdikiSayfa());

		tarayici.geriGit();
		System.out.println("Geri gidildi, şu anki sayfa: " + tarayici.getSimdikiSayfa());

		tarayici.ileriGit();
		System.out.println("İleri gidildi, şu anki sayfa: " + tarayici.getSimdikiSayfa());
	}
}
