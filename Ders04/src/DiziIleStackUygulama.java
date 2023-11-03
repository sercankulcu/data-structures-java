
public class DiziIleStackUygulama {
	
	private int kapasite;
	private int tepe;
	private int[] dizi;

	public DiziIleStackUygulama(int boyut) {
		kapasite = boyut;
		dizi = new int[kapasite];
		tepe = -1; // Tepe işaretçisini (-1 boş yığın) başlangıçta tanımla.
	}

	public boolean bosMu() {
		return (tepe == -1);
	}

	public boolean doluMu() {
		return (tepe == kapasite - 1);
	}

	public void ekle(int deger) {
		if (doluMu()) {
			System.out.println("Yığın dolu. " + deger + " eklenemedi.");
		} else {
			dizi[++tepe] = deger;
			System.out.println(deger + " yığına eklenmiştir.");
		}
	}

	public int cikar() {
		if (bosMu()) {
			System.out.println("Yığın boş. Çıkarma yapılamaz.");
			return -1; // Boş yığın için bir belirleyici değer döndürün.
		} else {
			int cikarilanDeger = dizi[tepe--];
			System.out.println(cikarilanDeger + " yığından çıkarılmıştır.");
			return cikarilanDeger;
		}
	}

	public int bak() {
		if (bosMu()) {
			System.out.println("Yığın boş. Bakma yapılamaz.");
			return -1; // Boş yığın için bir belirleyici değer döndürün.
		} else {
			return dizi[tepe];
		}
	}

	public static void main(String[] args) {
		DiziIleStackUygulama yigin = new DiziIleStackUygulama(5); // Maksimum 5 öğeli bir yığın oluşturun.

		yigin.ekle(10);
		yigin.ekle(20);
		yigin.ekle(30);

		System.out.println("Tepe öğe: " + yigin.bak());

		yigin.cikar();
		yigin.cikar();

		System.out.println("Yığın boş mu? " + yigin.bosMu());

		yigin.cikar();
		yigin.cikar(); // Boş yığından çıkarmaya çalışma.

		System.out.println("Yığın dolu mu? " + yigin.doluMu());
	}
}
