
public class BagliListeIleStack {
	
	class Dugum {
		int veri;
		Dugum sonraki;

		public Dugum(int veri) {
			this.veri = veri;
			this.sonraki = null;
		}
	}

	private Dugum tepe;

	public BagliListeIleStack() {
		tepe = null; // Boş yığını temsil etmek için tepe başlangıçta null'dır.
	}

	public boolean bosMu() {
		return (tepe == null);
	}

	public void ekle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		yeniDugum.sonraki = tepe;
		tepe = yeniDugum;
		System.out.println(veri + " yığına eklenmiştir.");
	}

	public int cikar() {
		if (bosMu()) {
			System.out.println("Yığın boş. Çıkarma yapılamaz.");
			return -1; // Boş yığın için belirleyici bir değer döndürün.
		} else {
			int cikarilanDeger = tepe.veri;
			tepe = tepe.sonraki;
			System.out.println(cikarilanDeger + " yığından çıkarılmıştır.");
			return cikarilanDeger;
		}
	}

	public int bak() {
		if (bosMu()) {
			System.out.println("Yığın boş. Bakma yapılamaz.");
			return -1; // Boş yığın için belirleyici bir değer döndürün.
		} else {
			return tepe.veri;
		}
	}

	public static void main(String[] args) {
		BagliListeIleStack yigin = new BagliListeIleStack();

		yigin.ekle(10);
		yigin.ekle(20);
		yigin.ekle(30);

		System.out.println("Tepe öğe: " + yigin.bak());

		yigin.cikar();
		yigin.cikar();

		System.out.println("Yığın boş mu? " + yigin.bosMu());

		yigin.cikar();
		yigin.cikar(); // Boş yığından çıkarmaya çalışma.

		System.out.println("Yığın boş mu? " + yigin.bosMu());
	}
}
