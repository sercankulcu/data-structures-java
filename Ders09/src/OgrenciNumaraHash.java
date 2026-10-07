// Okul numarasi icin hash fonksiyonu.
// Bicim: ab070c0de  ab: kayit yili (20-24), c: bolum (6 veya 7), de: kayit sirasi (00-99)
// 5 yil x 2 bolum x 100 sira = 1000 numara, 1000 farkli indis: mukemmel hash.
public class OgrenciNumaraHash {

    // s: "ab070c0de" bicimindeki okul numarasi; sonuc 0..999
    public static int calculateHash(String s) {
        if (s == null || s.length() != 9) {
            throw new IllegalArgumentException("Gecersiz okul numarasi bicimi: " + s);
        }
        int ab = Integer.parseInt(s.substring(0, 2));   // kayit yili
        int c  = Integer.parseInt(s.substring(5, 6));   // bolum (substring bitisi haric)
        int de = Integer.parseInt(s.substring(7, 9));   // kayit sirasi
        if (ab < 20 || ab > 24 || (c != 6 && c != 7)) {
            throw new IllegalArgumentException("Gecersiz okul numarasi degerleri: " + s);
        }
        int yil   = (ab - 20) * 100;   // yil blogu
        int bolum = (c - 6) * 500;     // bolum: 0/500
        return (yil + bolum + de) % 1000;
    }

    public static void main(String[] args) {
        for (String no : new String[]{"210706003", "240707050", "230707042", "230706015"}) {
            System.out.println("calculateHash(" + no + ") = " + calculateHash(no));
        }
        try {
            calculateHash("190706003");
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
}
