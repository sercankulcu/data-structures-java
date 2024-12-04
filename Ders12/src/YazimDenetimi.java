import java.util.HashSet;
import java.util.Set;

public class YazimDenetimi {
	
    private Set<String> lexicon; // Dogru yazilan kelimeleri tutan kume

    public YazimDenetimi() {
        lexicon = new HashSet<>(); // Bos bir lexicon olustur
    }

    // Lexicon'a kelime ekler
    public void lexiconaEkle(String kelime) {
        lexicon.add(kelime.toLowerCase()); // Buyuk kucuk harf farkini onlemek icin kelimeleri kucuk harfe donustur
    }

    // Bir kelimenin lexicon icinde olup olmadigini kontrol et (dogru yazilmis mi?)
    public boolean denetle(String kelime) {
    	kelime = kelime.replace(".", "");
        return lexicon.contains(kelime.toLowerCase()); // Lexicon'da var mi kontrol et
    }

    public static void main(String[] args) {
        YazimDenetimi yazimDenetimi = new YazimDenetimi();

        // Lexicon'a kelimeleri ekle
        yazimDenetimi.lexiconaEkle("ben");
        yazimDenetimi.lexiconaEkle("elma");
        yazimDenetimi.lexiconaEkle("ve");
        yazimDenetimi.lexiconaEkle("armut");
        yazimDenetimi.lexiconaEkle("kiraz");
        yazimDenetimi.lexiconaEkle("bir");
        yazimDenetimi.lexiconaEkle("meyve");
        yazimDenetimi.lexiconaEkle("severim");
        yazimDenetimi.lexiconaEkle("meyvedir");

        // Yazim denetimi yap
        String metin = "Ben elma ve armut sevrim."; // Birinci metin
        String[] kelimeler = metin.split("\\s+"); // Metni kelimelere bol
        for (String kelime : kelimeler) {
            if (!yazimDenetimi.denetle(kelime)) { // Kelime lexicon'da yoksa
                System.out.println("Yanlis yazilmis kelime: " + kelime);
            }
        }

        metin = "Kirz bir meyvedir."; // Ikinci metin
        kelimeler = metin.split("\\s+"); // Metni kelimelere bol
        for (String kelime : kelimeler) {
            if (!yazimDenetimi.denetle(kelime)) { // Kelime lexicon'da yoksa
                System.out.println("Yanlis yazilmis kelime: " + kelime);
            }
        }
    }
}
