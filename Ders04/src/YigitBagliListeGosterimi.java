public class YigitBagliListeGosterimi {

    // Dugum sinifi, yigin elemanlarini temsil eder.
    class Dugum {
        int veri; // Dugumun tasidigi deger.
        Dugum sonraki; // Bir sonraki dugumu isaret eder.

        // Dugum constructor - Veriyi alir ve sonraki'yi null olarak ayarlar.
        public Dugum(int veri) {
            this.veri = veri;
            this.sonraki = null;
        }
    }

    private Dugum tepe; // Yiginin tepe elemanini temsil eder.

    // Constructor - Bos bir yigin olusturur.
    public YigitBagliListeGosterimi() {
        tepe = null; // Baslangicta tepe null olarak ayarlanir.
    }

    // Yiginin bos olup olmadigini kontrol eder.
    public boolean bosMu() {
        return (tepe == null); // Tepe null ise yigin bostur.
    }

    // Yigina yeni bir eleman ekler.
    public void ekle(int veri) {
        Dugum yeniDugum = new Dugum(veri); // Yeni bir dugum olustur.
        yeniDugum.sonraki = tepe; // Yeni dugumun sonraki'si eski tepeyi isaret eder.
        tepe = yeniDugum; // Tepeyi yeni dugum olarak ayarla.
        System.out.println(veri + " yigina eklenmistir.");
    }

    // Yigindan bir eleman cikarir.
    public int cikar() {
        if (bosMu()) {
            // Yigin bos ise bilgi mesaji yazdirir ve belirleyici bir deger dondurur.
            System.out.println("Yigin bos. Cikarma yapilamaz.");
            return -1;
        } else {
            int cikarilanDeger = tepe.veri; // Tepedeki degeri al.
            tepe = tepe.sonraki; // Tepeyi bir sonraki dugume kaydir.
            System.out.println(cikarilanDeger + " yigindan cikarilmistir.");
            return cikarilanDeger;
        }
    }

    // Yigindaki tepe elemanini goruntuler.
    public int bak() {
        if (bosMu()) {
            // Yigin bos ise bilgi mesaji yazdirir ve belirleyici bir deger dondurur.
            System.out.println("Yigin bos. Bakma yapilamaz.");
            return -1;
        } else {
            return tepe.veri; // Tepedeki elemani dondur.
        }
    }

    public static void main(String[] args) {

        // Yeni bir yigin olustur.
        YigitBagliListeGosterimi yigin = new YigitBagliListeGosterimi();

        // Yigina elemanlar ekle.
        yigin.ekle(10);
        yigin.ekle(20);
        yigin.ekle(30);

        // Tepedeki elemani goruntule.
        System.out.println("Tepe oge: " + yigin.bak());

        // Yigindan elemanlar cikar.
        yigin.cikar();
        yigin.ekle(40);
        yigin.cikar();
        yigin.cikar();

        // Yiginin bos olup olmadigini kontrol et.
        System.out.println("Yigin bos mu? " + yigin.bosMu());

        // Bos yigindan eleman cikarma denemesi.
        yigin.cikar();
        yigin.cikar();

        // Yiginin bos olup olmadigini bir kez daha kontrol et.
        System.out.println("Yigin bos mu? " + yigin.bosMu());
    }
}
