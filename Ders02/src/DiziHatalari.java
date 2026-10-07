// Dizilerle sik karsilasilan calisma zamani hatalari.
public class DiziHatalari {

	public static void main(String[] args) {
		// 1) Indis sinir disi: gecerli indisler 0 .. length - 1
		int[] dizi = new int[5];
		try {
			dizi[5] = 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Hata: " + e.getMessage()); // Index 5 out of bounds for length 5
		}

		// Erisimden once indisi denetlemek
		int indis = 7;
		if (indis >= 0 && indis < dizi.length) {
			System.out.println("Deger: " + dizi[indis]);
		} else {
			System.out.println("Gecersiz indis: " + indis);
		}

		// 2) Kovaryans: String[] bir Object[] gibi kullanilabilir,
		//    yanlis turde atama ancak calisma zamaninda yakalanir
		String[] isimler = {"Ali", "Ayse"};
		Object[] nesneler = isimler;    // derlenir
		try {
			nesneler[0] = 42;           // derlenir, ama calisirken istisna
		} catch (ArrayStoreException e) {
			System.out.println("Hata: ArrayStoreException (" + e.getMessage() + ")");
		}

		// 3) Duzensiz dizide henuz olusturulmamis satir null'dir
		int[][] m = new int[3][];
		try {
			m[0][0] = 1;
		} catch (NullPointerException e) {
			System.out.println("Hata: NullPointerException, m[0] = " + m[0]);
		}
		m[0] = new int[2];              // once satir olusturulmali
		m[0][0] = 1;
		System.out.println("m[0][0] = " + m[0][0]);
	}
}
