// Jenerik (generic) sinif ornegi (Slayt 14).
// T, nesne olusturulurken belirlenen tip parametresidir.
public class Kutu<T> {
	private T icerik;
	public void koy(T x) { icerik = x; }
	public T al() { return icerik; }

	public static void main(String[] args) {
		Kutu<String> k = new Kutu<>();
		k.koy("veri");
		String s = k.al();      // donusum (cast) gerekmez
		System.out.println("k.al() = " + s);

		Kutu<Integer> m = new Kutu<>();
		m.koy(42);              // int -> Integer (otomatik kutulama)
		// m.koy("x");          // DERLEME HATASI: Kutu<Integer> String almaz
		int sayi = m.al();      // Integer -> int
		System.out.println("m.al() + 1 = " + (sayi + 1));
	}
}
