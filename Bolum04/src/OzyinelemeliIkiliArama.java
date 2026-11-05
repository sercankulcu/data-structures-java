// Ozyinelemeli ikili arama: her cagri araligi yariya indirir.
// Cagrilar agac degil zincir olusturur: en fazla floor(log2 n) + 1 tur, O(log n).
public class OzyinelemeliIkiliArama {

	static int ikiliAra(int[] a, int x, int lo, int hi) {
		if (lo > hi) return -1;       // taban: bos aralik
		int mid = (lo + hi) / 2;
		if (a[mid] == x) return mid;  // taban: bulundu
		if (x < a[mid])
			return ikiliAra(a, x, lo, mid - 1); // sol yari
		return ikiliAra(a, x, mid + 1, hi);   // sag yari
	}

	public static void main(String[] args) {
		int[] a = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};   // sirali olmali
		System.out.println(ikiliAra(a, 23, 0, 9));          // 5
		System.out.println(ikiliAra(a, 91, 0, 9));          // 9
		System.out.println(ikiliAra(a, 40, 0, 9));          // -1
	}
}
