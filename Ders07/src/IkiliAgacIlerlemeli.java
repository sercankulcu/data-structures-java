import java.util.LinkedList; 
import java.util.Queue;
import java.util.Stack;

// Ikili agac veri yapisi ile iliskili islemleri yapan sinif
public class IkiliAgacIlerlemeli {

    private Dugum<Integer> kok; // Agacin kok dugumu

    // Ikili agaci olusturan metot
    public void ikiliAgacOlustur() {
        Dugum<Integer> birinci = new Dugum<>(1);     // Birinci dugumu olustur (kok dugum)
        Dugum<Integer> ikinci = new Dugum<>(2);      // Ikinci dugumu olustur
        Dugum<Integer> ucuncu = new Dugum<>(3);      // Ucuncu dugumu olustur
        Dugum<Integer> dorduncu = new Dugum<>(4);    // Dorduncu dugumu olustur
        Dugum<Integer> besinci = new Dugum<>(5);     // Besinci dugumu olustur
        Dugum<Integer> altinci = new Dugum<>(6);     // Altinci dugumu olustur
        Dugum<Integer> yedinci = new Dugum<>(7);     // Yedinci dugumu olustur

        kok = birinci;               // Kok dugumu belirle
        birinci.sol = ikinci;        // Birinci dugumun sol alt dugumunu ayarla
        birinci.sag = ucuncu;        // Birinci dugumun sag alt dugumunu ayarla
        ikinci.sol = dorduncu;       // Ikinci dugumun sol alt dugumunu ayarla
        ikinci.sag = besinci;        // Ikinci dugumun sag alt dugumunu ayarla
        ucuncu.sol = altinci;        // Ucuncu dugumun sol alt dugumunu ayarla
        ucuncu.sag = yedinci;        // Ucuncu dugumun sag alt dugumunu ayarla
    }

    // Kök basta dolas (preorder traversal)
    public void kokBastaDolas(Dugum<Integer> kok) {
        if (kok == null) {
            return; // Eger kok null ise islem sonlandirilir
        }
        Stack<Dugum<Integer>> yigit = new Stack<>(); // Yigit (stack) olustur
        yigit.push(kok); // Kok dugumu yigina ekle

        while (!yigit.isEmpty()) { // Yigit bos olana kadar islem tekrar edilir
            Dugum<Integer> gecici = yigit.pop(); // Yigindan bir dugum cikar
            System.out.print(gecici.veri + " "); // Dugumun verisini yazdir

            if (gecici.sag != null) {
                yigit.push(gecici.sag); // Sag alt dugumu yigina ekle
            }

            if (gecici.sol != null) {
                yigit.push(gecici.sol); // Sol alt dugumu yigina ekle
            }
        }
    }

    // Kok ortada dolas (inorder traversal)
    public void kokOrtadaDolas(Dugum<Integer> kok) {
        if (kok == null) {
            return; // Eger kok null ise islem sonlandirilir
        }
        Stack<Dugum<Integer>> yigit = new Stack<>();
        Dugum<Integer> gecici = kok;

        while (!yigit.isEmpty() || gecici != null) { 
            if (gecici != null) { 
                yigit.push(gecici); 
                gecici = gecici.sol; // Sol alt dugume gec
            } else {
                gecici = yigit.pop();
                System.out.print(gecici.veri + " "); // Dugumun verisini yazdir
                gecici = gecici.sag; // Sag alt dugume gec
            }
        }
    }

 // Kok sonda dolas (postorder traversal)
    public void kokSondaDolas(Dugum<Integer> kok) {
        Dugum<Integer> simdiki = kok; // Baslangicta kok dugum ile basla
        Stack<Dugum<Integer>> yigit = new Stack<>(); // Dugumleri saklamak icin bir yigit olustur

        // Yigit bos olmadigi surece veya simdiki dugum null olmadigi surece islem devam eder
        while (simdiki != null || !yigit.isEmpty()) {
            if (simdiki != null) {
                // Simdiki dugum null degilse, onu yigina ekle ve sol alt dugume ilerle
                yigit.push(simdiki); 
                simdiki = simdiki.sol; 
            } else {
                // Simdiki dugum null ise, yiginin tepesindeki dugumun sag alt dugumunu kontrol et
                Dugum<Integer> gecici = yigit.peek().sag;

                if (gecici == null) {
                    // Eger sag alt dugum yoksa (null), yiginin tepesindeki dugumu cikar ve yazdir
                    gecici = yigit.pop(); 
                    System.out.print(gecici.veri + " "); 

                    // Eger yigin bos degilse ve gecici dugum yiginin tepesindeki dugumun sag alt dugumu ise
                    while (!yigit.isEmpty() && gecici == yigit.peek().sag) {
                        // Yiginin tepesindeki dugumu cikar ve yazdir
                        gecici = yigit.pop(); 
                        System.out.print(gecici.veri + " "); 
                    }
                } else {
                    // Eger sag alt dugum varsa, ona ilerle
                    simdiki = gecici; 
                }
            }
        }
    }


 // Seviye sirali dolas (level-order traversal)
    public void seviyeSiraliDolas(Dugum<Integer> kok) {
        if (kok == null) 
            return; // Eger agac bos ise, islem sonlandirilir
        
        // Seviye sirali dolasma icin bir kuyruk (queue) olustur
        Queue<Dugum<Integer>> kuyruk = new LinkedList<>();
        kuyruk.add(kok); // Kok dugumu kuyruğa ekle

        // Kuyruk bosalana kadar islem devam eder
        while (!kuyruk.isEmpty()) {
            // Kuyrugun basindaki dugumu al ve yazdir
            Dugum<Integer> tempDugum = kuyruk.poll();
            System.out.print(tempDugum.veri + " "); 

            // Eger sol alt dugum varsa, onu kuyruğa ekle
            if (tempDugum.sol != null) 
                kuyruk.offer(tempDugum.sol); 

            // Eger sag alt dugum varsa, onu kuyruğa ekle
            if (tempDugum.sag != null) 
                kuyruk.offer(tempDugum.sag); 
        }
    }

    // Agacin derinligini hesaplayan metot
    private int agacDerinligi(Dugum<Integer> dugum) {
        if (dugum == null) {
            return 0; // Bos agacin derinligi 0'dir
        } else {
            int solDerinlik = agacDerinligi(dugum.sol); 
            int sagDerinlik = agacDerinligi(dugum.sag); 

            return 1 + Math.max(solDerinlik, sagDerinlik); 
        }
    }

    public static void main(String[] args) {
        IkiliAgacIlerlemeli ia = new IkiliAgacIlerlemeli();
        ia.ikiliAgacOlustur();

        System.out.print("Kok basta: ");
        ia.kokBastaDolas(ia.kok);
        System.out.println();

        System.out.print("Kok ortada: ");
        ia.kokOrtadaDolas(ia.kok);
        System.out.println();

        System.out.print("Kok sonda: ");
        ia.kokSondaDolas(ia.kok);
        System.out.println();

        System.out.print("Seviye sirali: ");
        ia.seviyeSiraliDolas(ia.kok);
        System.out.println();

        System.out.println("Agac derinligi: " + ia.agacDerinligi(ia.kok));
    }
}
