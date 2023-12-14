import java.util.ArrayList;
import java.util.List;

class GenelDugum {
    String veri;
    List<GenelDugum> cocuklar;

    public GenelDugum(String veri) {
        this.veri = veri;
        this.cocuklar = new ArrayList<>();
    }
}

public class GenelAgacGorsellestirme {
    public static void agaciYazdir(GenelDugum kok, String onEk, boolean sonCocuk) {
        System.out.println(onEk + (sonCocuk ? "└── " : "├── ") + kok.veri);

        for (int i = 0; i < kok.cocuklar.size(); i++) {
            GenelDugum cocuk = kok.cocuklar.get(i);
            boolean son = (i == kok.cocuklar.size() - 1);
            agaciYazdir(cocuk, onEk + (sonCocuk ? "    " : "│   "), son);
        }
    }

    public static void main(String[] args) {
        GenelDugum kok = new GenelDugum("A");
        GenelDugum dugumB = new GenelDugum("B");
        GenelDugum dugumC = new GenelDugum("C");
        GenelDugum dugumD = new GenelDugum("D");
        GenelDugum dugumE = new GenelDugum("E");
        GenelDugum dugumF = new GenelDugum("F");

        kok.cocuklar.add(dugumB);
        kok.cocuklar.add(dugumC);
        dugumB.cocuklar.add(dugumD);
        dugumB.cocuklar.add(dugumE);
        dugumB.cocuklar.add(dugumF);

        System.out.println("Genel Ağaç Görselleştirme:");
        agaciYazdir(kok, "", true);
    }
}
