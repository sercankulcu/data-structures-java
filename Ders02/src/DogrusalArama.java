// Siralanmamis dizide dogrusal arama: en iyi durum O(1), en kotu durum O(n).
public class DogrusalArama {

	public static void main(String[] args) {
		int[] dizi = {7, 2, 9, 5, 1, 8};
		System.out.println("dogrusalArama(dizi, 5) = " + dogrusalArama(dizi, 5)); // true, 4 karsilastirma
		System.out.println("dogrusalArama(dizi, 3) = " + dogrusalArama(dizi, 3)); // false, 6 karsilastirma
	}

	// Ogeler bastan sona tek tek hedefle karsilastirilir
	static boolean dogrusalArama(int[] dizi, int hedef) {
		for (int oge : dizi)
			if (oge == hedef)
				return true; // bulundu, kalan ogelere bakilmaz
		return false;
	}
}
