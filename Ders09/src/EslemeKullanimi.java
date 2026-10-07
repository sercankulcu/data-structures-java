import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// java.util.Map ile esleme ADT islemleri ve equals/hashCode sozlesmesi.
public class EslemeKullanimi {

    // record: equals ve hashCode derleyici tarafindan hazir uretilir (Java 16+)
    record Ogrenci(int no, String ad) {}

    // Sik hata: equals ezilmis, hashCode ezilmemis -> Object.hashCode (nesne kimligi)
    @SuppressWarnings("overrides")  // derleyici de bu hatayi uyarir; ornek icin bilerek birakildi
    static class Nokta {
        int x, y;
        Nokta(int x, int y) { this.x = x; this.y = y; }

        @Override
        public boolean equals(Object o) {
            return o instanceof Nokta p && x == p.x && y == p.y;
        }
    }

    // Cozum: hashCode da equals ile ayni alanlara gore ezilir
    static class DuzgunNokta {
        int x, y;
        DuzgunNokta(int x, int y) { this.x = x; this.y = y; }

        @Override
        public boolean equals(Object o) {
            return o instanceof DuzgunNokta p && x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        // Temel islemler: put, get, remove; ayni anahtarla put = guncelleme
        Map<Integer, String> m = new HashMap<>();
        m.put(105, "Murat");
        m.put(21, "Leyla");
        System.out.println("put(21, \"Ali\") eski deger = " + m.put(21, "Ali"));
        System.out.println("get(21) = " + m.get(21));
        System.out.println("remove(105) = " + m.remove(105));
        System.out.println("get(88) = " + m.get(88));
        System.out.println("size() = " + m.size() + ", containsKey(21) = " + m.containsKey(21));

        // record anahtar
        Map<Ogrenci, Double> notlar = new HashMap<>();
        notlar.put(new Ogrenci(42, "Ada"), 87.5);
        System.out.println("notlar.get(Ogrenci(42, Ada)) = " + notlar.get(new Ogrenci(42, "Ada")));

        // hashCode unutulursa esit anahtar bulunamaz
        Map<Nokta, String> hatali = new HashMap<>();
        hatali.put(new Nokta(1, 2), "A");
        System.out.println("Nokta: get(1,2) = " + hatali.get(new Nokta(1, 2)) + "  (hashCode yok)");

        Map<DuzgunNokta, String> dogru = new HashMap<>();
        dogru.put(new DuzgunNokta(1, 2), "A");
        System.out.println("DuzgunNokta: get(1,2) = " + dogru.get(new DuzgunNokta(1, 2)));

        // Kelime sayma: her kelimenin kac kez gectigi, ortalama O(n)
        String[] kelimeler = {"elma", "armut", "elma", "kiraz", "elma", "armut"};
        Map<String, Integer> f = new HashMap<>();
        for (String w : kelimeler) {
            f.put(w, f.getOrDefault(w, 0) + 1);
        }
        System.out.println("elma = " + f.get("elma") + ", armut = " + f.get("armut") + ", kiraz = " + f.get("kiraz"));
    }
}
