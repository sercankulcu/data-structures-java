public class IkiliAgacOzYinelemeli {

    private Dugum<Integer> kok;

    // Ikili agac olusturma islemi
    public void ikiliAgacOlustur() {

        // Dugumler olusturuluyor
        Dugum<Integer> birinci = new Dugum<>(9);   // Kok dugum
        Dugum<Integer> ikinci = new Dugum<>(2);    // Sol cocuk dugum
        Dugum<Integer> ucuncu = new Dugum<>(3);    // Sag cocuk dugum
        Dugum<Integer> dorduncu = new Dugum<>(4);  // Ikincinin solu
        Dugum<Integer> besinci = new Dugum<>(7);   // Dorduncunun sagi
        Dugum<Integer> altinci = new Dugum<>(5);   // Ucuncunun solu

        // Agacin yapisi tanimlaniyor
        kok = birinci;                // Kok dugumu tanimla
        birinci.sol = ikinci;         // Kokun sol cocugu
        birinci.sag = ucuncu;         // Kokun sag cocugu
        ikinci.sol = dorduncu;        // Ikincinin sol cocugu
        dorduncu.sag = besinci;       // Dorduncunun sag cocugu
        ucuncu.sol = altinci;         // Ucuncunun sol cocugu
    }

    // Kok basta dolasma (preorder traversal)
    public void kokBastaDolas(Dugum<Integer> kok) {
        if (kok != null) {
            System.out.print(kok.veri + " ");  // Dugumun degerini yazdir
            kokBastaDolas(kok.sol);  // Sol alt agaci dolas
            kokBastaDolas(kok.sag);  // Sag alt agaci dolas
        }
    }

    // Kok ortada dolasma (inorder traversal)
    public void kokOrtadaDolas(Dugum<Integer> kok) {
        if (kok == null) {
            return;  // Eger dugum bos ise islemi sonlandir
        }
        kokOrtadaDolas(kok.sol);   // Sol alt agaci dolas
        System.out.print(kok.veri + " ");  // Dugumun degerini yazdir
        kokOrtadaDolas(kok.sag);   // Sag alt agaci dolas
    }

    // Kok sonda dolasma (postorder traversal)
    public void kokSondaDolas(Dugum<Integer> kok) {
        if (kok != null) {
            kokSondaDolas(kok.sol); // Sol alt agaci dolas
            kokSondaDolas(kok.sag); // Sag alt agaci dolas
            System.out.print(kok.veri + " "); // Dugumun degerini yazdir
        }
    }

    // Seviye sirali dolas (level-order traversal)
    public void seviyeSiraliDolas(Dugum<Integer> kok) {
        int derinlik = agacDerinligi(kok); // Agacin derinligini hesapla
        for (int seviye = 0; seviye <= derinlik; seviye++) {
            seviyeSiraliDolas(kok, seviye); // Her seviyeyi sirayla dolas
        }
    }

    // Belirli bir seviyedeki dugumleri dolas
    private void seviyeSiraliDolas(Dugum<Integer> dugum, int hedefSeviye) {
        if (dugum == null) {
            return; // Dugum bos ise islem sonlandirilir
        }
        if (hedefSeviye == 0) {
            System.out.print(dugum.veri + " "); // Hedef seviyedeki dugumu yazdir
        } else {
            seviyeSiraliDolas(dugum.sol, hedefSeviye - 1); // Sol cocuk icin seviyeyle azalt
            seviyeSiraliDolas(dugum.sag, hedefSeviye - 1); // Sag cocuk icin seviyeyle azalt
        }
    }

    // Agacin derinligini hesapla
    private int agacDerinligi(Dugum<Integer> dugum) {
        if (dugum == null) {
            return 0; // Bos agacin derinligi 0'dır
        } else {
            int solDerinlik = agacDerinligi(dugum.sol); // Sol alt agacin derinligi
            int sagDerinlik = agacDerinligi(dugum.sag); // Sag alt agacin derinligi
            return 1 + Math.max(solDerinlik, sagDerinlik); // Maksimum derinlik + 1
        }
    }

    public static void main(String[] args) {

        IkiliAgacOzYinelemeli ia = new IkiliAgacOzYinelemeli();
        ia.ikiliAgacOlustur(); // Agaci olustur

        System.out.print("Kok basta: ");
        ia.kokBastaDolas(ia.kok); // Kok basta dolas
        System.out.println();

        System.out.print("Kok ortada: ");
        ia.kokOrtadaDolas(ia.kok); // Kok ortada dolas
        System.out.println();

        System.out.print("Kok sonda: ");
        ia.kokSondaDolas(ia.kok); // Kok sonda dolas
        System.out.println();

        System.out.print("Seviye sirali: ");
        ia.seviyeSiraliDolas(ia.kok); // Seviye sirali dolas
        System.out.println();

        System.out.println("Agac derinligi: " + ia.agacDerinligi(ia.kok)); // Agacin derinligini yazdir
    }
}
