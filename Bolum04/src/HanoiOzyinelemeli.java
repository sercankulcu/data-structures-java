// Hanoi kuleleri, ozyinelemeli cozum: n - 1 diski yardimciya, en buyugu hedefe,
// n - 1 diski yardimcidan hedefe. Hamle sayisi T(n) = 2 T(n - 1) + 1 = 2^n - 1.
// (Ayni problemin acik bir gorev yigini ile cozumu: Bolum07/src/HanoiKuleleri.java)
public class HanoiOzyinelemeli {

	static int hamle = 0;

	// k(aynak), y(ardimci), h(edef); her satir "disk: kaynak -> hedef"
	static void hanoi(int n, char k, char y, char h) {
		if (n == 1) {
			hamle++;
			System.out.println("1: " + k + " -> " + h);
			return;
		}
		hanoi(n - 1, k, h, y);
		hamle++;
		System.out.println(n + ": " + k + " -> " + h);
		hanoi(n - 1, y, k, h);
	}

	public static void main(String[] args) {
		hanoi(3, 'A', 'B', 'C');
		System.out.println("Hamle sayisi: " + hamle);   // 7 = 2^3 - 1
	}
}

/* Beklenen cikti:
1: A -> C
2: A -> B
1: C -> B
3: A -> C
1: B -> A
2: B -> C
1: A -> C
Hamle sayisi: 7
*/
