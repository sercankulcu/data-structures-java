import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class TurkceKarakterDegistirici {

    // Dosyalarin ve klasorlerin rekursif olarak kontrol edilmesi icin metod
    public static void dosyalariTarama(File klasor) {
        // Klasordeki tum dosyalari ve alt klasorleri al
        File[] dosyalar = klasor.listFiles();

        if (dosyalar != null) {
            // Her bir dosya veya klasor uzerinde islem yap
            for (File dosya : dosyalar) {
                // Eger bu bir klasorse, rekursif olarak icine gir
                if (dosya.isDirectory()) {
                    dosyalariTarama(dosya);  // Rekursif cagri
                } else if (dosya.isFile() && dosya.getName().endsWith(".java") && !dosya.getName().startsWith("Turkce")) {
                    try {
                        // Dosyanin icerigini satir satir oku
                        List<String> satirlar = Files.readAllLines(dosya.toPath(), StandardCharsets.UTF_8);
                        boolean degisiklikYapildi = false;

                        // Her satiri kontrol et
                        for (int i = 0; i < satirlar.size(); i++) {
                            String orijinalSatir = satirlar.get(i);
                            // Turkce karakterleri degistirmek icin satiri duzenle
                            String degistirilmisSatir = orijinalSatir
                                .replace("ü", "u")
                                .replace("Ü", "U")
                                .replace("ğ", "g")
                                .replace("Ğ", "G")
                                .replace("ı", "i")
                                .replace("İ", "I")
                                .replace("ş", "s")
                                .replace("Ş", "S")
                                .replace("ç", "c")
                                .replace("Ç", "C")
                                .replace("ö", "o")
                                .replace("Ö", "O");

                            // Eger satirda bir degisiklik yapildiysa
                            if (!orijinalSatir.equals(degistirilmisSatir)) {
                                satirlar.set(i, degistirilmisSatir);  // Satiri degistirdik
                                // Degisiklik yapilan satiri ekrana yazdir
                                System.out.println("Dosya: " + dosya.getAbsolutePath() + " - Satir " + (i + 1) + " degistirildi.");
                                degisiklikYapildi = true;
                            }
                        }

                        // Eger herhangi bir degisiklik yapilmissa, dosyayi guncelle
                        if (degisiklikYapildi) {
                            Files.write(dosya.toPath(), satirlar, StandardCharsets.UTF_8);
                        }
                    } catch (IOException e) {
                        // Hata durumunda ekrana hata mesaji yazdir
                        System.out.println("Hata: " + e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Bulundugunuz dizinin bir ust dizini
        File klasor = new File(System.getProperty("user.dir")).getParentFile();
        // ust dizindeki dosyalari ve klasorleri taramak icin metodu cagir
        dosyalariTarama(klasor);
    }
}