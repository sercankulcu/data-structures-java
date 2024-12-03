
public class OgrenciNumaraHash {

    /**
     * Hash degerini hesaplar.
     * @param ogrenciid Okul iD'si (ab070c0de formatinda, String olarak)
     * @return Hash degeri (0 ile 999 arasinda bir tam sayi)
     */
    public static int hashHesapla(String ogrenciid) {
        // Okul iD'sini dogrula
        if (ogrenciid == null || ogrenciid.length() != 9) {
            throw new IllegalArgumentException("Gecersiz okul iD formati");
        }

        // Parcalari ayikla
        int ab = Integer.parseInt(ogrenciid.substring(0, 2)); // ab: Kayit yili
        int c = Integer.parseInt(ogrenciid.substring(5, 6));  // c: Bolum
        int de = Integer.parseInt(ogrenciid.substring(7, 9)); // de: Kayit sirasi numarasi

        // Yil, bolum ve kayit sirasini kontrol et
        if (ab < 20 || ab > 24 || (c != 6 && c != 7) || de < 1 || de > 100) {
            throw new IllegalArgumentException("Gecersiz okul iD degerleri");
        }

        // Hash fonksiyonunu uygula
        int yilFaktoru = (ab - 20) * 100;
        int bolumFaktoru = (c - 6) * 500;
        int hashDegeri = (yilFaktoru + bolumFaktoru + de) % 1000;

        return hashDegeri;
    }

    public static void main(String[] args) {
        // Test verisi
        String ogrenci1 = "210706003"; // ornek okul iD'si
        String ogrenci2 = "240707050";

        // Hash fonksiyonunu cagir ve sonuclari yazdir
        System.out.println("Hash degeri (ogrenci1): " + hashHesapla(ogrenci1)); // cikti: 103
        System.out.println("Hash degeri (ogrenci2): " + hashHesapla(ogrenci2)); // cikti: 950
    }
}
