import java.util.ArrayList; 
import java.util.List;

// Genel agacin bir dugumunu temsil eden sinif
class GenelDugum {
    String veri; // Dugumde saklanan veri
    List<GenelDugum> cocuklar; // Dugumun cocuklarini iceren liste

    // Yapici metot: Dugumun verisini ve cocuk listesini baslatir
    public GenelDugum(String veri) {
        this.veri = veri; // Dugumdeki veriyi ata
        this.cocuklar = new ArrayList<>(); // Yeni bir cocuklar listesi baslat
    }
}

// Genel agacin gorsellestirilmesini saglayan sinif
public class AgacGorsellestirme {

    // Agaci yazdiran metot
    public static void agaciYazdir(GenelDugum kok, String onEk, boolean sonCocuk) {
        // Mevcut dugumu, uygun on ekle birlikte yazdir
        System.out.println(onEk + (sonCocuk ? "└── " : "├── ") + kok.veri);

        // Dugumun cocuklarini birer birer ele al
        for (int i = 0; i < kok.cocuklar.size(); i++) {
            GenelDugum cocuk = kok.cocuklar.get(i);
            boolean son = (i == kok.cocuklar.size() - 1); // Son cocuk olup olmadigini kontrol et
            // Cocuk dugumu yazdirmak icin rekurzif cagri yap
            agaciYazdir(cocuk, onEk + (sonCocuk ? "    " : "│   "), son);
        }
    }

    // Ana metot: Programin baslangic noktasi
    public static void main(String[] args) {
        
        // Agac yapisini kurmak icin dugumler olusturuluyor
        GenelDugum kok = new GenelDugum("A"); // Kok dugumunu olustur
        GenelDugum dugumB = new GenelDugum("B");
        GenelDugum dugumC = new GenelDugum("C");
        GenelDugum dugumD = new GenelDugum("D");
        GenelDugum dugumE = new GenelDugum("E");
        GenelDugum dugumF = new GenelDugum("F");
        GenelDugum dugumG = new GenelDugum("G");

        // Cocuklar arasindaki iliskileri kur
        kok.cocuklar.add(dugumB);
        kok.cocuklar.add(dugumC);
        dugumB.cocuklar.add(dugumD);
        dugumB.cocuklar.add(dugumE);
        dugumB.cocuklar.add(dugumF);
        dugumE.cocuklar.add(dugumG);

        // Agaci yazdir
        System.out.println("Agac Gorsellestirme:");
        agaciYazdir(kok, "", true);
    }
}
