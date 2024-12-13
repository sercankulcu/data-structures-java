public class Ornek9 {

    // Node sinifi, her bir dugumu temsil eder
    static class Node {
        int veri;
        Node sonraki;

        Node(int veri) {
            this.veri = veri;
            this.sonraki = null;
        }
    }

    // Listeyi dolasarak, verilen dugumun basa ulasip ulasmadigini kontrol eden fonksiyon
    public static int fonksiyon(Node baslangic, Node suanki) {
        
        // Eger liste bossa 1 dondur, yani basa ulasilamaz
        if (baslangic == null)
            return 1;
        
        // Eger suanki dugum null ise 0 dondur, yani sona ulasilmistir
        if(suanki == null)
            return 0;
        
        // Eger suanki dugum basa esitse, 1 dondur
        if(baslangic == suanki)
            return 1;
        
        // Dugumun sonraki elemanina gec ve tekrar kontrol et
        return fonksiyon(baslangic, suanki.sonraki);
    }

    public static void main(String[] args) {

        // Baglantili listeyi baslatiyoruz
        Node baslangic = new Node(1);
        baslangic.sonraki = new Node(2);
        baslangic.sonraki.sonraki = new Node(3);
        baslangic.sonraki.sonraki.sonraki = new Node(4);
        baslangic.sonraki.sonraki.sonraki.sonraki = baslangic; // Listeyi dairesel yapiyoruz

        // Baslangic dugumu ile, ikinci dugumun ayni olup olmadigini kontrol et
        System.out.println(fonksiyon(baslangic, baslangic.sonraki));
    }
}
