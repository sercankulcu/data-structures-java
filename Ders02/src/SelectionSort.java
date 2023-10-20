
public class SelectionSort {
	
    public static void main(String[] args) {
    	
        int[] dizi = {64, 25, 12, 22, 11, 77, 5};

        System.out.println("Dizinin sırasız hali: ");
        diziYazdir(dizi);

        selectionSort(dizi);

        System.out.println("\nDizinin sıralı hali (Seçim Sıralaması ile): ");
        diziYazdir(dizi);
    }

    public static void selectionSort(int[] dizi) {

        for (int i = 0; i < dizi.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < dizi.length; j++) {
                if (dizi[j] < dizi[minIndex]) {
                    minIndex = j;
                }
            }

            int gecici = dizi[i];
            dizi[i] = dizi[minIndex];
            dizi[minIndex] = gecici;
        }
    }

    public static void diziYazdir(int[] dizi) {
        for (int eleman : dizi) {
            System.out.print(eleman + " ");
        }
    }
}
