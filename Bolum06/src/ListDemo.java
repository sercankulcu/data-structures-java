import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// List arayuzunun temel metotlari: tekrar ve null, iki remove tuzagi,
// replaceAll / sort ve jenerik tipler ile otomatik kutulama.
public class ListDemo {
	public static void main(String[] args) {
		tekrarVeNull();
		removeTuzagi();
		replaceAllVeSort();
		getVeKutulama();
	}

	// Liste tekrara ve null'a izin verir; indexOf ilk, lastIndexOf son gecisi bulur
	static void tekrarVeNull() {
		System.out.println("--- Tekrar ve null ---");
		List<String> isimler = new ArrayList<>();
		isimler.add("Ali");
		isimler.add("Ali");      // tekrar: izin var
		isimler.add(null);       // null: izin var
		isimler.add("Ali");
		System.out.println(isimler);                      // [Ali, Ali, null, Ali]
		System.out.println(isimler.size());               // 4
		System.out.println(isimler.indexOf("Ali"));       // 0 (ilk gecis)
		System.out.println(isimler.lastIndexOf("Ali"));   // 3
		System.out.println(isimler.indexOf(null));        // 2 (null icin == kullanilir)
	}

	// List<Integer>'da int arguman indis, Integer arguman deger olarak yorumlanir
	static void removeTuzagi() {
		System.out.println("--- remove(int) ve remove(Object) ---");
		List<Integer> sayilar = new ArrayList<>(List.of(10, 20, 30));
		Integer a = sayilar.remove(1);                     // indis 1 silinir
		System.out.println("remove(1) -> " + a + ", liste " + sayilar);   // 20, [10, 30]
		boolean b = sayilar.remove(Integer.valueOf(10));  // 10 degeri silinir
		System.out.println("remove(Integer 10) -> " + b + ", liste " + sayilar);  // true, [30]
		boolean c = sayilar.remove(Integer.valueOf(99));  // yok, liste degismez
		System.out.println("remove(Integer 99) -> " + c + ", liste " + sayilar);  // false, [30]
		try {
			sayilar.remove(5);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("remove(5) -> IndexOutOfBoundsException: " + e.getMessage());
		}
	}

	// Lambda ile toplu islem: her oge yerinde degisir / liste siralanir
	static void replaceAllVeSort() {
		System.out.println("--- replaceAll ve sort ---");
		List<String> isimler = new ArrayList<>(List.of("Ali", "Ahmet", "Mehmet"));
		// Locale.ROOT: Turkce yerelde "Ali" noktali buyuk I ile yazilirdi
		isimler.replaceAll(isim -> isim.toUpperCase(Locale.ROOT));
		List<Integer> sayilar = new ArrayList<>(List.of(5, 3, 1));
		sayilar.sort((s1, s2) -> Integer.compare(s1, s2));
		System.out.println(isimler + " " + sayilar);   // [ALI, AHMET, MEHMET] [1, 3, 5]
	}

	// get O(1); tur parametresi ilkel olamaz, Integer ile kutulama yapilir
	static void getVeKutulama() {
		System.out.println("--- get ve kutulama ---");
		List<String> isimler = new ArrayList<>();
		isimler.add("Ali");
		isimler.add("Ahmet");
		isimler.add("Mehmet");
		String ilkIsim = isimler.get(0);   // donusturme (cast) gerekmez
		System.out.println(ilkIsim);       // Ali
		// isimler.add(5);                 // derleme hatasi: liste yalnizca String alir

		List<Integer> sayilar = new ArrayList<>();
		sayilar.add(5);                    // otomatik kutulama: Integer.valueOf(5)
		int x = sayilar.get(0);            // kutudan cikarma: .intValue()
		System.out.println("x = " + x);
		try {
			isimler.get(3);                // gecerli indisler 0 .. size - 1
		} catch (IndexOutOfBoundsException e) {
			System.out.println("get(3) -> IndexOutOfBoundsException: " + e.getMessage());
		}
	}
}
