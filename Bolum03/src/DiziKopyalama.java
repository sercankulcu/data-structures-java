import java.util.Arrays;

// Referans mi, kopya mi? java.util.Arrays ve 2B dizide sig/derin kopya.
public class DiziKopyalama {

	public static void main(String[] args) {
		// 1) = yalnizca referansi kopyalar, Arrays.copyOf yeni dizi olusturur
		int[] dizi = {1, 2, 3, 4, 5};
		int[] referans = dizi;                            // ayni nesne
		int[] kopya = Arrays.copyOf(dizi, dizi.length);   // yeni nesne, O(n)
		referans[0] = 99;
		System.out.println("dizi     = " + Arrays.toString(dizi));     // [99, 2, 3, 4, 5]
		System.out.println("referans = " + Arrays.toString(referans)); // [99, 2, 3, 4, 5]
		System.out.println("kopya    = " + Arrays.toString(kopya));    // [1, 2, 3, 4, 5]

		// 2) == referanslari, Arrays.equals icerigi karsilastirir
		int[] a = {1, 2, 3};
		int[] b = {1, 2, 3};
		System.out.println("a == b              : " + (a == b));            // false
		System.out.println("Arrays.equals(a, b) : " + Arrays.equals(a, b)); // true
		System.out.println("println(a) yazdirir : " + a);                   // [I@... (tur@adres)

		// 3) Diger hazir islemler
		int[] c = new int[5];
		Arrays.fill(c, 7);
		System.out.println("fill(c, 7)          : " + Arrays.toString(c));
		System.out.println("copyOf(a, 5)        : " + Arrays.toString(Arrays.copyOf(a, 5)));
		int[] d = {9, 4, 7, 1, 5};
		Arrays.sort(d);                                   // yerinde, artan sira
		System.out.println("sort(d)             : " + Arrays.toString(d));
		System.out.println("binarySearch(d, 7)  : " + Arrays.binarySearch(d, 7)); // indis 3
		System.out.println("binarySearch(d, 6)  : " + Arrays.binarySearch(d, 6)); // yok -> negatif

		// 4) 2B dizide sig ve derin kopya
		int[][] m = { {1, 2}, {3, 4} };
		int[][] s = m.clone();          // sig kopya: satirlar ortak
		s[0][0] = 9;                    // m[0][0] de 9!
		s[1] = new int[] {7, 8};        // yalniz s degisir
		int[][] dk = new int[2][];      // derin kopya:
		for (int i = 0; i < 2; i++)
			dk[i] = m[i].clone();       // satirlar da kopyalanir
		dk[0][1] = 5;                   // m etkilenmez
		System.out.println("m  = " + Arrays.deepToString(m));  // [[9, 2], [3, 4]]
		System.out.println("s  = " + Arrays.deepToString(s));  // [[9, 2], [7, 8]]
		System.out.println("dk = " + Arrays.deepToString(dk)); // [[9, 5], [3, 4]]
	}
}

/* Beklenen cikti:
dizi     = [99, 2, 3, 4, 5]
referans = [99, 2, 3, 4, 5]
kopya    = [1, 2, 3, 4, 5]
a == b              : false
Arrays.equals(a, b) : true
println(a) yazdirir : [I@681a9515
fill(c, 7)          : [7, 7, 7, 7, 7]
copyOf(a, 5)        : [1, 2, 3, 0, 0]
sort(d)             : [1, 4, 5, 7, 9]
binarySearch(d, 7)  : 3
binarySearch(d, 6)  : -4
m  = [[9, 2], [3, 4]]
s  = [[9, 2], [7, 8]]
dk = [[9, 5], [3, 4]]
*/
