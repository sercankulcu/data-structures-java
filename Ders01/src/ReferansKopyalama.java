import java.util.Arrays;

// Ilkel tip ile referans tip arasindaki fark (Slayt 12, 13, 53, 54).
// Kural: = ilkel tipte degeri, referans tipte adresi kopyalar.
public class ReferansKopyalama {

	public static void main(String[] args) {

		// Slayt 13: deger mi, referans mi?
		int x = 5;
		int y = x;          // degerin kopyasi alinir
		y = 7;              // yalniz y degisir; x hala 5
		int[] a = {1, 2, 3};
		int[] b = a;        // dizi degil, referans kopyalanir
		b[0] = 9;           // ayni nesne degisir: a[0] de 9 olur (takma ad)
		b = new int[2];     // b yeni nesneyi gosterir; a etkilenmez
		System.out.println(x + " " + a[0]);   // 5 9
		System.out.println("y = " + y + ", b = " + Arrays.toString(b));

		// Slayt 54: diziye indis ile tek adimda erisim
		int[] d = {7, 2, 9, 5, 1};   // 5 ogeli dizi, indisler 0..4
		int z = d[2];                // z = 9
		d[4] = 8;                    // indis 4'teki deger guncellenir
		System.out.println("d[2] = " + z + ", d = " + Arrays.toString(d));

		// Slayt 53: == referanslari, Arrays.equals icerigi karsilastirir
		int[] p = {1, 2, 3};
		int[] q = {1, 2, 3};
		System.out.println("p == q          : " + (p == q));             // false
		System.out.println("Arrays.equals   : " + Arrays.equals(p, q));  // true
	}
}
