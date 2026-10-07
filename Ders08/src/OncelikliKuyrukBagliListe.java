// Min-oncelikli kuyruk: kucukten buyuge sirali bagli liste; en oncelikli oge bastadir
public class OncelikliKuyrukBagliListe {

	// Dugum: anahtar (oncelik), deger ve sonraki dugum
	static class Dugum {
		int anahtar; // kucuk anahtar = yuksek oncelik
		String deger;
		Dugum sonraki;

		Dugum(int anahtar, String deger) {
			this.anahtar = anahtar;
			this.deger = deger;
		}
	}

	private Dugum bas; // en oncelikli oge
	private int n = 0; // oge sayisi

	// Yer arama O(n), baglama O(1)
	public void insert(int anahtar, String deger) {
		Dugum yeni = new Dugum(anahtar, deger);
		if (bas == null || anahtar < bas.anahtar) { // basa ekle
			yeni.sonraki = bas;
			bas = yeni;
		} else {
			Dugum onceki = bas;
			// Esit anahtarlar arkaya gecer: geldigi sirayi korur
			while (onceki.sonraki != null && onceki.sonraki.anahtar <= anahtar) {
				onceki = onceki.sonraki;
			}
			yeni.sonraki = onceki.sonraki; // once bu bag, yoksa liste kopar
			onceki.sonraki = yeni;
		}
		n++;
	}

	// En oncelikli ogeye bakar: O(1); bossa null
	public String min() {
		return bas == null ? null : "(" + bas.anahtar + "," + bas.deger + ")";
	}

	// Bastan siler: O(1); bossa null
	public String removeMin() {
		String sonuc = min();
		if (bas != null) {
			bas = bas.sonraki;
			n--;
		}
		return sonuc;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{ ");
		for (Dugum d = bas; d != null; d = d.sonraki) {
			sb.append("(").append(d.anahtar).append(",").append(d.deger).append(") ");
		}
		return sb.append("}").toString();
	}

	public static void main(String[] args) {
		OncelikliKuyrukBagliListe pq = new OncelikliKuyrukBagliListe();
		pq.insert(5, "A");
		pq.insert(9, "C");
		pq.insert(3, "B");
		System.out.println("insert x3:   " + pq);
		System.out.println("min()       = " + pq.min());
		System.out.println("removeMin() = " + pq.removeMin() + "  " + pq);
		pq.insert(7, "D");
		System.out.println("insert(7,D): " + pq);
		while (!pq.isEmpty()) {
			System.out.println("removeMin() = " + pq.removeMin() + "  " + pq);
		}
		System.out.println("removeMin() = " + pq.removeMin() + " (bos kuyruk)");
	}
}
