public class YigitDiziGosterimi {

    private int kapasite; // Yiginin maksimum kapasitesini belirtir.
    private int tepe; // Yiginin tepe elemanini isaret eder.
    private int[] dizi; // Yiginin elemanlarini saklayan dizi.

    // Constructor - Belirtilen boyutta bir yigin olusturur.
    public YigitDiziGosterimi(int boyut) {
        kapasite = boyut; // Yiginin kapasitesini ayarla.
        dizi = new int[kapasite]; // Elemanlari saklamak icin bir dizi olustur.
        tepe = -1; // Baslangicta yigin bos oldugu icin tepeyi -1 olarak ayarla.
    }

    // Yiginin bos olup olmadigini kontrol eder.
    public boolean bosMu() {
        return (tepe == -1); // Tepe -1 ise yigin bostur.
    }

    // Yiginin dolu olup olmadigini kontrol eder.
    public boolean doluMu() {
        return (tepe == kapasite - 1); // Tepe kapasite - 1 ise yigin doludur.
    }

    // Yigina yeni bir deger ekler.
    public void ekle(int deger) {
        if (doluMu()) {
            // Ekleme yapilamazsa bilgi mesaji yazdirir.
            System.out.println("Yigin dolu. " + deger + " eklenemedi.");
        } else {
            dizi[++tepe] = deger; // Yeni degeri tepeye ekle ve tepeyi arttir.
            System.out.println(deger + " yigina eklenmistir.");
        }
    }

    // Yigindan bir deger cikarir.
    public int cikar() {
        if (bosMu()) {
            // Cikarma yapilamazsa bilgi mesaji yazdirir ve belirleyici bir deger dondurur.
            System.out.println("Yigin bos. Cikarma yapilamaz.");
            return -1;
        } else {
            // Tepedeki elemani cikar ve tepeyi azalt.
            int cikarilanDeger = dizi[tepe--];
            System.out.println(cikarilanDeger + " yigindan cikarilmistir.");
            return cikarilanDeger;
        }
    }

    // Yigindaki tepe elemani dondurur.
    public int bak() {
        if (bosMu()) {
            // Yigin bos ise bilgi mesaji yazdirir ve belirleyici bir deger dondurur.
            System.out.println("Yigin bos. Bakma yapilamaz.");
            return -1;
        } else {
            return dizi[tepe]; // Tepedeki elemani dondur.
        }
    }

    public static void main(String[] args) {

        // Maksimum 5 eleman kapasitesine sahip bir yigin olustur.
        YigitDiziGosterimi yigin = new YigitDiziGosterimi(5);

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

        // Yiginin dolu olup olmadigini kontrol et.
        System.out.println("Yigin dolu mu? " + yigin.doluMu());
    }
}
