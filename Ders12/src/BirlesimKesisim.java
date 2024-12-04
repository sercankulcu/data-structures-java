import java.util.HashSet;

public class BirlesimKesisim {

    public static void main(String[] args) {

        int[] dizi1 = {3, 8, 10, 23, 34, 1, 12, 3, 8, 23}; // Ilk dizi
        int[] dizi2 = {10, 34, 7, 2, 8, 12}; // Ikinci dizi

        // Birlesim ve kesisimi bul
        birlesim(dizi1, dizi2); // Birlesim fonksiyonunu cagir
        kesisim(dizi1, dizi2); // Kesisim fonksiyonunu cagir
    }

    // Dizilerin birlesimini bulan fonksiyon
    public static void birlesim(int[] dizi1, int[] dizi2) {
        HashSet<Integer> kume = new HashSet<>(); // Birlesim icin HashSet kullan

        // Ilk dizinin elemanlarini HashSet'e ekle
        for (int num : dizi1) {
            kume.add(num);
        }

        // Ikinci dizinin elemanlarini da HashSet'e ekle
        for (int num : dizi2) {
            kume.add(num);
        }

        // Birlesim sonucu
        System.out.println("Dizilerin birlesimi: " + kume);
    }

    // Dizilerin kesisimini bulan fonksiyon
    public static void kesisim(int[] dizi1, int[] dizi2) {
        HashSet<Integer> kume = new HashSet<>(); // Ilk dizi elemanlari icin HashSet
        HashSet<Integer> kesisim = new HashSet<>(); // Kesisim elemanlari icin HashSet

        // Ilk dizinin elemanlarini HashSet'e ekle
        for (int num : dizi1) {
            kume.add(num);
        }

        // Ikinci dizide, ilk dizide de olan elemanlari kontrol et
        for (int num : dizi2) {
            if (kume.contains(num)) { // Ortak eleman bul
                kesisim.add(num);
            }
        }

        // Kesisim sonucu
        System.out.println("Dizilerin kesisimi: " + kesisim);
    }
}
