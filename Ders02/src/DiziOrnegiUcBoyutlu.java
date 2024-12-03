import java.util.Scanner;

public class DiziOrnegiUcBoyutlu {

    public static void main(String[] args) {

        // Kullanicidan giris alabilmek icin Scanner nesnesi olusturuluyor
        Scanner scanner = new Scanner(System.in);

        // Katman sayisi kullanicidan aliniyor
        System.out.print("Katman sayisini girin: ");
        int katmanSayisi = scanner.nextInt();

        // Satir sayisi kullanicidan aliniyor
        System.out.print("Satir sayisini girin: ");
        int satirSayisi = scanner.nextInt();

        // Sutun sayisi kullanicidan aliniyor
        System.out.print("Sutun sayisini girin: ");
        int sutunSayisi = scanner.nextInt();

        // Kullanici tarafindan girilen boyutlarda uc boyutlu dizi olusturuluyor
        int[][][] ucBoyutluDizi = new int[katmanSayisi][satirSayisi][sutunSayisi];

        // Diziyi dolduran fonksiyon cagriliyor
        diziDoldur(ucBoyutluDizi, katmanSayisi, satirSayisi, sutunSayisi);

        // Diziyi ekrana yazdiran fonksiyon cagriliyor
        System.out.println("\nuc Boyutlu Dizi:");
        yazdir(ucBoyutluDizi, katmanSayisi, satirSayisi, sutunSayisi);

        // Kullanicidan veri almak icin actigimiz Scanner nesnesi kapatiliyor
        scanner.close();
    }

    // Dizi elemanlarini belirli bir formulle dolduran fonksiyon
    public static void diziDoldur(int[][][] dizi, int katmanSayisi, int satirSayisi, int sutunSayisi) {
        // Dizi her katman, satir ve sutun icin sirayla dolduruluyor
        for (int i = 0; i < katmanSayisi; i++) {
            for (int j = 0; j < satirSayisi; j++) {
                for (int k = 0; k < sutunSayisi; k++) {
                    // ornek formulle her hucreye deger ataniyor
                    dizi[i][j][k] = i * 2 + j * 3 + k;
                }
            }
        }
    }

    // uc boyutlu diziyi ekrana yazdiran fonksiyon
    public static void yazdir(int[][][] dizi, int katmanSayisi, int satirSayisi, int sutunSayisi) {
        // Dizinin her katmanini yazdiriyoruz
        for (int i = 0; i < katmanSayisi; i++) {
            // Her katmani yazdirirken basinda katman numarasini ekliyoruz
            System.out.println("Katman " + (i + 1) + ":");
            // Katmandaki her satiri yazdiriyoruz
            for (int j = 0; j < satirSayisi; j++) {
                // Satirdaki her sutun degerini yazdiriyoruz
                for (int k = 0; k < sutunSayisi; k++) {
                    System.out.print(dizi[i][j][k] + " "); // Hucreyi yazdir
                }
                System.out.println(); // Bir satirin yazdirilmasi tamamlandiktan sonra yeni satira geciyoruz
            }
            System.out.println(); // Bir katmanin yazdirilmasi tamamlandiktan sonra yeni katmana geciyoruz
        }
    }
}
