import java.util.ArrayList;
import java.util.List;

public class AgirlikliCizge {

    // Kenar sinifi bir kaynaktan hedefe bir agirlikla baglanti kurar
    private class Kenar {
        private String kaynak; // Baglantinin basladigi dugum
        private String hedef;  // Baglantinin bittigi dugum
        private int agirlik;   // Baglantinin agirligi

        // Kenar olusturucu metodu
        public Kenar(String kaynak, String hedef, int agirlik) {
            this.kaynak = kaynak;
            this.hedef = hedef;
            this.agirlik = agirlik;
        }

        // Kenari string olarak dondur
        public String toString() {
            return "(" + kaynak + " -> " + hedef + ", w: " + agirlik + ")";
        }
    }

    private List<String> dugumler; // Cizgedeki tum dugumleri saklar
    private List<Kenar> kenarlar; // Cizgedeki tum kenarlari saklar

    // AgirlikliCizge yapici metodu
    public AgirlikliCizge() {
        dugumler = new ArrayList<>(); // Dugumler icin bos liste olustur
        kenarlar = new ArrayList<>(); // Kenarlar icin bos liste olustur
    }

    // Yeni dugum ekleme metodu
    public void dugumEkle(String dugum) {
        dugumler.add(dugum); // Listeye yeni dugum ekle
    }

    // Yeni kenar ekleme metodu
    public void kenarEkle(String kaynak, String hedef, int agirlik) {
        // Kaynak veya hedef cizgede yoksa hata firlat
        if (!dugumler.contains(kaynak) || !dugumler.contains(hedef)) {
            throw new IllegalArgumentException("Baslangic ve hedef dugumler cizgeye eklenmeli.");
        }
        // Yeni kenar olustur ve listeye ekle
        Kenar yeniKenar = new Kenar(kaynak, hedef, agirlik);
        kenarlar.add(yeniKenar);
    }

    // Cizgeyi string olarak yazdirma metodu
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dugumler: "); // Dugumler listesini yaz
        sb.append(String.join(", ", dugumler));
        sb.append("\nKenarlar: "); // Kenarlar listesini yaz
        for (Kenar kenar : kenarlar) {
            sb.append(kenar.toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    // Ana metod: Ornek bir cizge yapisi olustur ve yazdir
    public static void main(String[] args) {

        AgirlikliCizge cizge = new AgirlikliCizge();
        cizge.dugumEkle("A"); // Dugum A ekle
        cizge.dugumEkle("B"); // Dugum B ekle
        cizge.dugumEkle("C"); // Dugum C ekle
        cizge.dugumEkle("D"); // Dugum D ekle
        cizge.dugumEkle("E"); // Dugum E ekle

        cizge.kenarEkle("A", "B", 2); // A'dan B'ye agirlik 2 olan kenar ekle
        cizge.kenarEkle("B", "C", 3); // B'den C'ye agirlik 3 olan kenar ekle
        cizge.kenarEkle("A", "C", 1); // A'dan C'ye agirlik 1 olan kenar ekle
        cizge.kenarEkle("A", "D", 4); // A'dan D'ye agirlik 4 olan kenar ekle
        cizge.kenarEkle("B", "E", 5); // B'den E'ye agirlik 5 olan kenar ekle

        System.out.println(cizge); // Cizgeyi konsola yazdir
    }
}
