import java.util.Stack;

public class MetinDuzenleyici {
	
	private StringBuilder metin;
	private Stack<String> geriAlYigin;
	private Stack<String> yenidenUygulaYigin;

	// Yapici metod: MetinDuzenleyici nesnesi olusturuluyor
	public MetinDuzenleyici() {
		metin = new StringBuilder(); // Metin alanini baslat
		geriAlYigin = new Stack<>(); // Geri alma islemleri icin yigin
		yenidenUygulaYigin = new Stack<>(); // Yeniden uygulama islemleri icin yigin
	}

	// Metin ekleme fonksiyonu
	public void metinEkle(String yeniMetin) {
		geriAlYigin.push(metin.toString()); // Mevcut metni geri al yiginina ekle
		metin.append(yeniMetin); // Yeni metni mevcut metne ekle
		yenidenUygulaYigin.clear(); // Yeniden uygulama yiginini temizle
	}

	// Geri alma fonksiyonu
	public void geriAl() {
		if (!geriAlYigin.isEmpty()) { // Geri al yigininda veri varsa
			yenidenUygulaYigin.push(metin.toString()); // Mevcut metni yeniden uygula yiginina ekle
			metin = new StringBuilder(geriAlYigin.pop()); // Geri al yiginindan metni al
		}
	}

	// Yeniden uygulama fonksiyonu
	public void yenidenUygula() {
		if (!yenidenUygulaYigin.isEmpty()) { // Yeniden uygula yigininda veri varsa
			geriAlYigin.push(metin.toString()); // Mevcut metni geri al yiginina ekle
			metin = new StringBuilder(yenidenUygulaYigin.pop()); // Yeniden uygula yiginindan metni al
		}
	}

	// Mevcut metni donduren fonksiyon
	public String getMetin() {
		return metin.toString(); // Metni dondur
	}

	// Main metodu: Programin baslangici
	public static void main(String[] args) {
		MetinDuzenleyici duzenleyici = new MetinDuzenleyici(); // MetinDuzenleyici nesnesi olustur

		// Metin ekleme islemleri
		System.out.println("Metin Ekleme Islemleri:");
		duzenleyici.metinEkle("Merhaba, "); // "Merhaba, " metnini ekle
		duzenleyici.metinEkle("Dunya!"); // "Dunya!" metnini ekle
		System.out.println("Metin: " + duzenleyici.getMetin()); // Metni yazdir

		// Geri alma islemi
		System.out.println("\nGeri Alma Islemi:");
		duzenleyici.geriAl(); // Son eklemeyi geri al
		System.out.println("Geri Alindi: " + duzenleyici.getMetin()); // Geri alindiktan sonra metni yazdir

		// Yeniden uygulama islemi
		System.out.println("\nYeniden Uygulama Islemi:");
		duzenleyici.yenidenUygula(); // Geri alinan metni yeniden uygula
		System.out.println("Yeniden Uygulandi: " + duzenleyici.getMetin()); // Yeniden uygulama yapildiktan sonra metni yazdir
		
		// Yeni metin ekleme
		System.out.println("\nYeni Metin Ekleme Islemi:");
		duzenleyici.metinEkle(" Java. "); // " Java. " metnini ekle
		System.out.println("Metin: " + duzenleyici.getMetin()); // Son haliyle metni yazdir

		// Metin ekleme ve geri alma islemleri
		System.out.println("\nMetin Ekleme ve Geri Alma Islemleri:");
		duzenleyici.metinEkle(" Programlama dersi."); // Yeni metin ekle
		System.out.println("Metin: " + duzenleyici.getMetin()); // Yeni metni yazdir
		duzenleyici.geriAl(); // Son eklemeyi geri al
		System.out.println("Geri Alindi: " + duzenleyici.getMetin()); // Geri alindiktan sonra metni yazdir

		// Yeniden uygulama islemi
		System.out.println("\nYeniden Uygulama Islemi:");
		duzenleyici.yenidenUygula(); // Geri alinan metni yeniden uygula
		System.out.println("Yeniden Uygulandi: " + duzenleyici.getMetin()); // Yeniden uygulama yapildiktan sonra metni yazdir
	}
}
