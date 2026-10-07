import java.util.Arrays;

// Oncelikli kuyruk: sirali dizi ile (buyuk sayi = yuksek oncelik)
// Dizi oncelikleri buyukten kucuge sirali tutulur; en yuksek oncelik dizi[0]'dadir.
// ekle O(n) (kaydirma), cikar O(n). Heap ile ikisi de O(log n) olur (Bolum 8).
public class OncelikliKuyruk<E> {

	// Veri ve onceligini birlikte tutan oge
	static class Oge<T> {
		T veri;
		int oncelik;
		Oge(T v, int p) { veri = v; oncelik = p; }
	}

	@SuppressWarnings("unchecked")
	private Oge<E>[] dizi = (Oge<E>[]) new Oge<?>[10];   // new Oge[10] ham tip (raw type) uyarisi verir
	private int boyut = 0;

	public boolean bosMu() {
		return boyut == 0;
	}

	public void ekle(E veri, int p) {
		if (boyut == dizi.length)
			dizi = Arrays.copyOf(dizi, 2 * boyut);
		int i = boyut;
		// Esit oncelikte durulur: once gelen onde kalir (FIFO)
		while (i > 0 && dizi[i - 1].oncelik < p) {
			dizi[i] = dizi[i - 1];  // saga kaydir
			i--;
		}
		dizi[i] = new Oge<>(veri, p);
		boyut++;
	}

	public E cikar() {
		if (bosMu()) return null;
		E sonuc = dizi[0].veri;
		for (int i = 1; i < boyut; i++)
			dizi[i - 1] = dizi[i];  // sola kaydir
		dizi[--boyut] = null;
		return sonuc;
	}

	// Ogeleri veri:oncelik biciminde yazdirir
	public void kuyruguGoster() {
		System.out.print("Kuyruk:");
		for (int i = 0; i < boyut; i++)
			System.out.print(" " + dizi[i].veri + ":" + dizi[i].oncelik);
		System.out.println();
	}

	public static void main(String[] args) {
		// Slayt 26-30
		OncelikliKuyruk<String> kuyruk = new OncelikliKuyruk<>();
		kuyruk.ekle("A", 2);
		kuyruk.ekle("B", 5);
		kuyruk.ekle("C", 1);
		kuyruk.ekle("D", 5);
		kuyruk.kuyruguGoster();   // B:5 D:5 A:2 C:1

		// Cikis sirasi oncelige gore: B, D, A, C
		while (!kuyruk.bosMu()) {
			System.out.print("cikar() -> " + kuyruk.cikar() + "   ");
			kuyruk.kuyruguGoster();
		}
		System.out.println("Bos kuyrukta cikar() -> " + kuyruk.cikar());
	}
}
