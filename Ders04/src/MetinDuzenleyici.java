import java.util.Stack;

public class MetinDuzenleyici {
	
	private StringBuilder metin;
	private Stack<String> geriAlYigin;
	private Stack<String> yenidenUygulaYigin;

	public MetinDuzenleyici() {
		metin = new StringBuilder();
		geriAlYigin = new Stack<>();
		yenidenUygulaYigin = new Stack<>();
	}

	public void metinEkle(String yeniMetin) {
		geriAlYigin.push(metin.toString());
		metin.append(yeniMetin);
		yenidenUygulaYigin.clear();
	}

	public void geriAl() {
		if (!geriAlYigin.isEmpty()) {
			yenidenUygulaYigin.push(metin.toString());
			metin = new StringBuilder(geriAlYigin.pop());
		}
	}

	public void yenidenUygula() {
		if (!yenidenUygulaYigin.isEmpty()) {
			geriAlYigin.push(metin.toString());
			metin = new StringBuilder(yenidenUygulaYigin.pop());
		}
	}

	public String getMetin() {
		return metin.toString();
	}

	public static void main(String[] args) {
		MetinDuzenleyici duzenleyici = new MetinDuzenleyici();

		duzenleyici.metinEkle("Merhaba, ");
		duzenleyici.metinEkle("Dünya!");

		System.out.println("Metin: " + duzenleyici.getMetin());

		duzenleyici.geriAl();
		System.out.println("Geri Alındı: " + duzenleyici.getMetin());

		duzenleyici.yenidenUygula();
		System.out.println("Yeniden Uygulandı: " + duzenleyici.getMetin());
		
		duzenleyici.metinEkle(" Java. ");
		System.out.println("Metin: " + duzenleyici.getMetin());
	}
}
