import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

// Kopya mi, gorunum mu, degistirilemez mi?
// subList, List.of / List.copyOf, Collections.unmodifiableList ve Arrays.asList.
public class GorunumOrnegi {
	public static void main(String[] args) {
		subListGorunumu();
		degistirilemezVeGorunum();
		arraysAsList();
	}

	// subList kopya degil, asil listenin [1, 3) araligina bakan bir gorunumdur
	static void subListGorunumu() {
		System.out.println("--- subList ---");
		List<String> l = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
		List<String> alt = l.subList(1, 3);
		System.out.println(alt);   // [B, C]
		alt.set(0, "X");           // asil listede l[1] = X
		alt.add("Y");              // l'nin 3. indisine eklenir
		System.out.println(l);     // [A, X, C, Y, D, E]
		alt.clear();               // gorunumdeki ogeler asil listeden silinir
		System.out.println(l);     // [A, D, E]

		// l dogrudan yapisal degisirse eski gorunum gecersizlesir
		l.add("F");
		try {
			System.out.println(alt);
		} catch (ConcurrentModificationException e) {
			System.out.println("Eski gorunum -> ConcurrentModificationException");
		}
	}

	// Salt okunur gorunum != degistirilemez liste
	static void degistirilemezVeGorunum() {
		System.out.println("--- List.of, kopya, unmodifiableList ---");
		List<String> sabit = List.of("Ali", "Veli");
		try {
			sabit.add("Can");
		} catch (UnsupportedOperationException e) {
			System.out.println("sabit.add -> UnsupportedOperationException");
		}
		List<String> yeni = new ArrayList<>(sabit);   // degistirilebilir kopya
		yeni.add("Can");
		System.out.println(sabit + " " + yeni);       // [Ali, Veli] [Ali, Veli, Can]

		List<String> gorunum = Collections.unmodifiableList(yeni);
		List<String> kopya = List.copyOf(yeni);      // gercek degistirilemez kopya
		try {
			gorunum.set(0, "Ece");
		} catch (UnsupportedOperationException e) {
			System.out.println("gorunum.set -> UnsupportedOperationException");
		}
		yeni.set(0, "Ece");          // alttaki liste degisebilir
		System.out.println("gorunum: " + gorunum);   // [Ece, Veli, Can]
		System.out.println("kopya:   " + kopya);     // [Ali, Veli, Can]
	}

	// Arrays.asList diziyi kopyalamaz: sabit boyutlu, diziyle ayni bellegi paylasan liste
	static void arraysAsList() {
		System.out.println("--- Arrays.asList ---");
		String[] dizi = {"a", "b", "c"};
		List<String> liste = Arrays.asList(dizi);
		liste.set(0, "x");     // dizi[0] da artik "x"
		dizi[2] = "z";         // liste de degisir
		System.out.println(liste + " " + Arrays.toString(dizi));   // [x, b, z] [x, b, z]
		try {
			liste.add("d");    // boyut sabit
		} catch (UnsupportedOperationException e) {
			System.out.println("liste.add -> UnsupportedOperationException");
		}
		List<String> esnek = new ArrayList<>(liste);   // bagimsiz kopya
		esnek.add("d");
		System.out.println(liste + " " + esnek);       // [x, b, z] [x, b, z, d]
	}
}
