import java.util.zip.CRC32;

// Hash kodu + sikistirma: ornek hash fonksiyonlari, negatif kod tuzaklari ve HashMap indisi.
public class HashFonksiyonlari {

    // Mod alma (bolme kalani): floorMod negatif anahtarda da 0..diziBoyu-1 verir
    static int modulusHash(int anahtar, int diziBoyu) {
        return Math.floorMod(anahtar, diziBoyu);
    }

    static long crc32Hash(String anahtar) {
        CRC32 crc = new CRC32();
        crc.update(anahtar.getBytes());
        return crc.getValue();
    }

    // DJB2'nin XOR'lu bicimi; sonuc negatif olabilir
    static int djb2Hash(String s) {
        int h = 5381;
        for (int i = 0; i < s.length(); i++) {
            h = (h * 33) ^ s.charAt(i);
        }
        return h;
    }

    // String.hashCode() ile ayni hesap: toplam s[i] * 31^(n-1-i)
    static int hash(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);
        }
        return h;
    }

    // Java HashMap'in kova indisi (n = 2^k): ust bitleri karistir, alt k biti al
    static int indis(Object key, int n) {
        int h = key.hashCode();
        int m = h ^ (h >>> 16);
        return m & (n - 1);
    }

    public static void main(String[] args) {
        // Hash kodu + sikistirma
        int kod = hash("Ali");
        System.out.println("hash(\"Ali\") = " + kod + ", String.hashCode = " + "Ali".hashCode()
                + ", floorMod(kod, 13) = " + Math.floorMod(kod, 13));
        System.out.println("\"abc\".hashCode() = " + "abc".hashCode());
        System.out.println("crc32Hash(\"Ali\") = " + crc32Hash("Ali"));
        System.out.println("djb2Hash(\"Ali\") = " + djb2Hash("Ali")
                + ", djb2Hash(\"Giresun Universitesi\") = " + djb2Hash("Giresun Universitesi"));

        // modulusHash ile 10 hucrelik tablo
        Integer[] tablo = new Integer[10];
        for (int k : new int[]{5, 1, 10, 26, 99}) {
            int j = modulusHash(k, tablo.length);
            tablo[j] = k;
        }
        System.out.println("modulusHash tablosu: " + java.util.Arrays.toString(tablo));

        // N = 10 ile N = 11 (asal): 10'un katlari
        System.out.println("k   mod 10  mod 11");
        for (int k = 10; k <= 50; k += 10) {
            System.out.printf("%-3d %-7d %d%n", k, k % 10, k % 11);
        }

        // Negatif hash kodu tuzaklari
        System.out.println("-7 % 10 = " + (-7 % 10) + ", floorMod(-7, 10) = " + Math.floorMod(-7, 10));
        int enKucuk = "polygenelubricants".hashCode();   // tam olarak Integer.MIN_VALUE
        System.out.println("\"polygenelubricants\".hashCode() = " + enKucuk);
        System.out.println("Math.abs(kod) % 10 = " + Math.abs(enKucuk) % 10
                + " (negatif!), floorMod(kod, 10) = " + Math.floorMod(enKucuk, 10));

        // HashMap: karistirma olmasa ucu de kova 1'de cakisirdi
        for (int k : new int[]{1, 65537, 131073}) {
            System.out.println(k + " -> " + indis(k, 16) + "  (karistirmasiz: " + (k & 15) + ")");
        }
    }
}
