import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Ornek5 {

    // Liste elemanlarini sayan ozyinelemeli fonksiyon
    int elemanSayisi(ListIterator<String> it) {
        if (it.hasNext()) {
            it.next();
            return 1 + elemanSayisi(it);
        }
        return 0;
    }

    // Elemanlari ters sirayla yazdiran ozyinelemeli fonksiyon
    void tersYazdir(ListIterator<String> it) {
        if (it.hasNext()) {
            String deger = it.next();
            tersYazdir(it);
            System.out.println("Eleman: " + deger);
        }
    }

    // Elemanlari degistiren ozyinelemeli fonksiyon (ornegin, sonlarina ek bir ek eklemek)
    void elemanlariDegistir(ListIterator<String> it, String ek) {
        if (it.hasNext()) {
            String deger = it.next();
            it.set(deger + ek);
            elemanlariDegistir(it, ek);
        }
    }

    public static void main(String args[]) {
    	
        List<String> liste = new LinkedList<>();
        liste.add("Elma");
        liste.add("Muz");
        liste.add("Kiraz");
        liste.add("Hurma");
        liste.add("Karakiz");

        ListIterator<String> it = liste.listIterator();

        Ornek5 ornek = new Ornek5();

        // Elemanlari sayma
        System.out.println("Listedeki eleman sayisi: " + ornek.elemanSayisi(it));

        // iteratoru sifirlama
        it = liste.listIterator();

        // Elemanlari ters sirayla yazdirma
        System.out.println("\nTers sirayla:");
        ornek.tersYazdir(it);

        // iteratoru sifirlama
        it = liste.listIterator();

        // Elemanlari "-Meyve" ekleyerek degistirme
        ornek.elemanlariDegistir(it, "-Meyve");
        System.out.println("\nDegistirilmis liste: " + liste);

        // iteratoru sifirlama
        it = liste.listIterator();

        // Listeyi ters sirayla gezme
        System.out.println("\niterator kullanarak ters sirayla gezinme:");
        while (it.hasPrevious()) {
            System.out.println("Geriye dogru Eleman: " + it.previous());
        }

        // iteratoru sifirlama
        it = liste.listIterator();

        // "-Meyve" eklenmis elemanlar olup olmadigini kontrol etme
        boolean meyveVar = false;
        while (it.hasNext()) {
            if (it.next().endsWith("-Meyve")) {
                meyveVar = true;
                break;
            }
        }
        System.out.println("\nListe '-Meyve' eki olan elemanlari iceriyor mu? " + meyveVar);
    }
}
