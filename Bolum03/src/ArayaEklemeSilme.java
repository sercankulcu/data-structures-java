import java.util.Arrays;

// Dizide araya ekleme (saga kaydirma) ve aradan silme (sola kaydirma): O(n).
// Dizinin kapasitesi a.length, dolu kismi ise ilk "boyut" hucredir.
public class ArayaEklemeSilme {

	public static void main(String[] args) {
		int[] a = new int[6];                    // kapasite 6
		int[] ilk = {7, 2, 9, 5, 1};
		for (int i = 0; i < ilk.length; i++)
			a[i] = ilk[i];
		int boyut = ilk.length;                  // dolu 5 hucre
		System.out.println("Baslangic        : " + Arrays.toString(a) + " boyut = " + boyut);

		boyut = ekle(a, boyut, 1, 4);            // 4'u indis 1'e ekle
		System.out.println("ekle(indis 1, 4) : " + Arrays.toString(a) + " boyut = " + boyut);

		boyut = sil(a, boyut, 1);                // indis 1'deki ogeyi sil
		System.out.println("sil(indis 1)     : " + Arrays.toString(a) + " boyut = " + boyut);
	}

	// On kosul: boyut < a.length ve 0 <= indis <= boyut
	static int ekle(int[] a, int boyut, int indis, int deger) {
		for (int k = boyut; k > indis; k--)
			a[k] = a[k - 1];   // saga kaydir (sagdan baslanir)
		a[indis] = deger;
		return boyut + 1;      // yeni boyut
	}

	// On kosul: 0 <= indis < boyut
	static int sil(int[] a, int boyut, int indis) {
		for (int k = indis; k < boyut - 1; k++)
			a[k] = a[k + 1];   // sola kaydir (soldan baslanir)
		a[boyut - 1] = 0;      // eski son hucreyi temizle
		return boyut - 1;      // yeni boyut
	}
}
