
public class DaireselKuyrukOrnegi {
	
	public static void main(String[] args) {
		
		DaireselKuyruk<String> kuyruk = new DaireselKuyruk<>(5);

		// Kuyruğa (queue) öğeleri ekleyelim (enqueue)
		kuyruk.ekle("Öğe 1");
		kuyruk.ekle("Öğe 2");
		kuyruk.ekle("Öğe 3");
		kuyruk.ekle("Öğe 4");

		// Kuyruktaki öğeleri gösterelim
		kuyruk.kuyruğuGöster();

		// Kuyruktan (queue) öğeleri çıkaralım (dequeue)
		String çıkarılanÖğe = kuyruk.çıkar();
		System.out.println("Çıkarılan Öğe: " + çıkarılanÖğe);

		// Kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruğuGöster();

		// Kuyruğun önündeki öğeye bakalım (peek) ve çıkartmadan
		String önÖğe = kuyruk.önüneBak();
		System.out.println("Ön Öğe: " + önÖğe);

		// Kuyruğun boş olup olmadığını kontrol edelim
		boolean boşMu = kuyruk.boşMu();
		System.out.println("Kuyruk Boş mu? " + boşMu);

		// Kuyruğun boyutunu alalım
		int boyut = kuyruk.boyut();
		System.out.println("Kuyruk Boyutu: " + boyut);
	}
}

class DaireselKuyruk<E> {
	private Object[] dizi;
	private int baş;
	private int son;
	private int boyut;
	private int kapasite;

	public DaireselKuyruk(int kapasite) {
		this.kapasite = kapasite;
		dizi = new Object[kapasite];
		baş = -1;
		son = -1;
		boyut = 0;
	}

	public void ekle(E öğe) {
		if (boyut == kapasite) {
			System.out.println("Kuyruk dolu, öğe eklenemedi.");
			return;
		}
		if (baş == -1) {
			baş = 0;
		}
		son = (son + 1) % kapasite;
		dizi[son] = öğe;
		boyut++;
	}

	public E çıkar() {
		if (boşMu()) {
			System.out.println("Kuyruk boş, öğe çıkarılamaz.");
			return null;
		}
		E öğe = (E) dizi[baş];
		baş = (baş + 1) % kapasite;
		boyut--;
		return öğe;
	}

	public E önüneBak() {
		if (boşMu()) {
			System.out.println("Kuyruk boş, öğe yok.");
			return null;
		}
		return (E) dizi[baş];
	}

	public boolean boşMu() {
		return boyut == 0;
	}

	public int boyut() {
		return boyut;
	}

	public void kuyruğuGöster() {
		if (boşMu()) {
			System.out.println("Kuyruk boş.");
			return;
		}
		int i = baş;
		int sayac = 0;
		System.out.print("Kuyruk Öğeleri: ");
		while (sayac < boyut) {
			System.out.print(dizi[i] + " ");
			i = (i + 1) % kapasite;
			sayac++;
		}
		System.out.println();
	}
}
