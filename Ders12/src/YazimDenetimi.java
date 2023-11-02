
import java.util.HashSet;
import java.util.Set;

public class YazimDenetimi {
	private Set<String> lexicon;

	public YazimDenetimi() {
		lexicon = new HashSet<>();
	}

	// Lexicon'a kelime eklemek
	public void lexiconaEkle(String kelime) {
		lexicon.add(kelime.toLowerCase()); // Büyük küçük harf farkını önlemek için kelimeleri küçük harfe dönüştürüyoruz
	}

	// Bir kelimenin lexicon içinde olup olmadığını kontrol etmek (yani doğru yazılmış mı?)
	public boolean denetle(String kelime) {
		return lexicon.contains(kelime.toLowerCase());
	}

	public static void main(String[] args) {
		YazimDenetimi yazimDenetimi = new YazimDenetimi();

		// Lexicon'a kelimeleri eklemek
		yazimDenetimi.lexiconaEkle("ben");
		yazimDenetimi.lexiconaEkle("elma");
		yazimDenetimi.lexiconaEkle("ve");
		yazimDenetimi.lexiconaEkle("armut");
		yazimDenetimi.lexiconaEkle("kiraz");
		yazimDenetimi.lexiconaEkle("bir");
		yazimDenetimi.lexiconaEkle("meyve");

		// Yazım denetimi yapmak
		String metin1 = "Ben elma ve armut severim.";
		String[] kelimeler = metin1.split("\\s+"); // Metni kelimelere böler
		for (String kelime : kelimeler) {
			if (!yazimDenetimi.denetle(kelime)) {
				System.out.println("Yanlış yazılmış kelime: " + kelime);
			}
		}

		String metin2 = "Kirz bir meyvedir.";
		String[] kelimeler2 = metin2.split("\\s+");
		for (String kelime : kelimeler2) {
			if (!yazimDenetimi.denetle(kelime)) {
				System.out.println("Yanlış yazılmış kelime: " + kelime);
			}
		}
	}
}
