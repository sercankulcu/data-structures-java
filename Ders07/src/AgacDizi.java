
public class AgacDizi {
	int[] agac;   // Ağacı temsil eden diziyi tanımlar
	int boyut;    // Ağacın maksimum boyutunu saklar

	public AgacDizi(int boyut) {
		this.boyut = boyut;  // Ağacın maksimum boyutunu belirler
		agac = new int[boyut];  // Ağacı temsil eden diziyi oluşturur
		for (int i = 0; i < boyut; i++) {
			agac[i] = -1; // Ağacı başlangıçta boş olarak işaretle
		}
	}

	private void ekle(int indeks, int veri) {
		if (indeks >= boyut) {
			System.out.println("Ağaç dolu, öğe eklenemedi: " + veri);
			return;
		}

		if (agac[indeks] == -1) {
			agac[indeks] = veri;
			System.out.println(veri + " ağaca eklendi. index: " + indeks);
		} else {
			if (veri < agac[indeks]) {
				ekle(2 * indeks + 1, veri); // Sol alt ağaca ekle
			} else {
				ekle(2 * indeks + 2, veri); // Sağ alt ağaca ekle
			}
		}
	}

	private boolean sil(int indeks, int veri) {
		if (indeks >= boyut || agac[indeks] == -1) {
			System.out.println(veri + " ağaçta bulunamdı.");
			return false; // Eleman ağaçta bulunamadı
		}

		if (veri == agac[indeks]) {
			agac[indeks] = -1; // Elemanı sil
			System.out.println(veri + " ağaçta silindi. index: " + indeks);
			return true;
		} else if (veri < agac[indeks]) {
			return sil(2 * indeks + 1, veri); // Sol alt ağaçta silme işlemi
		} else {
			return sil(2 * indeks + 2, veri); // Sağ alt ağaçta silme işlemi
		}
	}

	public void kokOndeDolasma(int indeks) {
		if (indeks < boyut && agac[indeks] != -1) {
			System.out.print(agac[indeks] + " ");
			kokOndeDolasma(2 * indeks + 1); // Sol alt ağacı dolaş
			kokOndeDolasma(2 * indeks + 2); // Sağ alt ağacı dolaş
		}
	}

	public void kokOrtadaDolasma(int indeks) {
		if (indeks < boyut && agac[indeks] != -1) {
			kokOrtadaDolasma(2 * indeks + 1); // Sol alt ağacı dolaş
			System.out.print(agac[indeks] + " ");
			kokOrtadaDolasma(2 * indeks + 2); // Sağ alt ağacı dolaş
		}
	}

	public void kokSondaDolasma(int indeks) {
		if (indeks < boyut && agac[indeks] != -1) {
			kokSondaDolasma(2 * indeks + 1); // Sol alt ağacı dolaş
			kokSondaDolasma(2 * indeks + 2); // Sağ alt ağacı dolaş
			System.out.print(agac[indeks] + " ");
		}
	}

	public void seviyeSiralama() {
		int derinlik = agacDerinligi(); // Ağaç derinliğini al
		int sonIndeks = 0; // Her bir seviyenin başlangıç indeksini tut
		int seviyeDugumSayisi = 1; // Her bir sviyedeki eleman sayısı
		for (int seviye = 0; seviye < derinlik; seviye++) {
			for (int indeks = sonIndeks; indeks < sonIndeks + seviyeDugumSayisi; indeks++) {
				if (indeks < boyut && agac[indeks] != -1) {
					System.out.print(agac[indeks] + " "); // Düğüm verisini ekrana yazdır
				}
			}
			sonIndeks += seviyeDugumSayisi;
			seviyeDugumSayisi *= 2;
			System.out.println();
		}
	}

	private boolean ara(int indeks, int veri) {
		if (indeks >= boyut || agac[indeks] == -1) {
			System.out.println(veri + " ağaçta bulunamadı.");
			return false;
		}

		if (agac[indeks] == veri) {
			System.out.println(veri + " ağaçta bulundu. index: " + indeks);
			return true;
		}

		if (veri < agac[indeks]) {
			return ara(2 * indeks + 1, veri); // Sol alt ağaçta ara
		} else {
			return ara(2 * indeks + 2, veri); // Sağ alt ağaçta ara
		}
	}

	//Ağaç derinliğini hesaplayan metot
	public int agacDerinligi() {
		int derinlik = 0;
		int indeks = 0;
		int seviyeDugumSayisi = 1;

		while (indeks < boyut) {
			derinlik++;
			indeks += seviyeDugumSayisi;
			seviyeDugumSayisi *= 2;
		}

		System.out.println("Ağaç derinliği: " + derinlik);
		return derinlik;
	}

	public static void main(String[] args) {

		int agacBoyutu = 10; // Ağacın maksimum boyutu
		AgacDizi agac = new AgacDizi(agacBoyutu);

		// Ağaca öğeleri ekleyin
		agac.ekle(0, 50);
		agac.ekle(0, 30);
		agac.ekle(0, 70);
		agac.ekle(0, 20);
		agac.ekle(0, 40);
		agac.ekle(0, 60);
		agac.ekle(0, 80);

		System.out.print("Kök Önde Dolaşma: ");
		agac.kokOndeDolasma(0);
		System.out.println();

		// Elemanları sırayla dolaşarak görüntüleyin
		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolasma(0);
		System.out.println();

		System.out.print("Kök Sonda Dolaşma: ");
		agac.kokSondaDolasma(0);
		System.out.println();
		

		agac.seviyeSiralama();

		// Ağaçta bir öğeyi arayın
		agac.ara(0, 40);

		agac.sil(0, 30);

		System.out.print("Kök Önde Dolaşma: ");
		agac.kokOndeDolasma(0);
		System.out.println();

		// Elemanları sırayla dolaşarak görüntüleyin
		System.out.print("Kök Ortada Dolaşma: ");
		agac.kokOrtadaDolasma(0);
		System.out.println();

		System.out.print("Kök Sonda Dolaşma: ");
		agac.kokSondaDolasma(0);
		System.out.println();

		// Ağaçta bir öğeyi arayın
		agac.ara(0, 40);

		agac.agacDerinligi();
		
	}
}
