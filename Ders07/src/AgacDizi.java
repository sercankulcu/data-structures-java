
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

	private boolean sil(int indeks, int deger) {

		if (indeks >= boyut || agac[indeks] == -1) {
			System.out.println(deger + " ağaçta bulunamadı.");
			return false; // Eleman ağaçta bulunamadı
		}

		if (deger == agac[indeks]) {
			// Durum 1: Hiç çocuğu olmayan düğüm (yaprak düğüm)
			if (agac[2 * indeks + 1] == -1 && agac[2 * indeks + 2] == -1) {
				agac[indeks] = -1;
			}
			// Durum 2: Bir çocuğu olan düğüm (sol)
			else if (agac[2 * indeks + 1] != -1 && agac[2 * indeks + 2] == -1) {
				agac[indeks] = agac[2 * indeks + 1];
				sil(2 * indeks + 1, agac[2 * indeks + 1]);
			}
			// Durum 3: Bir çocuğu olan düğüm (sağ)
			else if (agac[2 * indeks + 1] == -1 && agac[2 * indeks + 2] != -1) {
				agac[indeks] = agac[2 * indeks + 2];
				sil(2 * indeks + 2, agac[2 * indeks + 2]);
			}
			// Durum 4: İki çocuğu olan düğüm
			else {
				int halef = yerineDugumBul(2 * indeks + 2);
				sil(2 * indeks + 2, halef);
				agac[indeks] = halef;
				System.out.println(deger + " ağaçtan silindi. İndeks: " + indeks);
			}
			
			return true;
		} else if (deger < agac[indeks]) {
			return sil(2 * indeks + 1, deger); // Sol alt ağaçta arama
		} else {
			return sil(2 * indeks + 2, deger); // Sağ alt ağaçta arama
		}
	}

	private int yerineDugumBul(int indeks) {
		// Sağ alt ağacın en soldaki düğümü
		while (agac[2 * indeks + 1] != -1) {
			indeks = 2 * indeks + 1;
		}
		return agac[indeks];
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
		int seviyeDugumSayisi = 1; // Her bir seviyedeki eleman sayısı
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

		int agacBoyutu = 15; // Ağacın maksimum boyutu
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

		System.out.print("Seviye Sıralı Dolaşma: ");
		agac.seviyeSiralama();

		// Ağaçta bir öğeyi arayın
		agac.ara(0, 40);
		agac.sil(0, 330);
		agac.sil(0, 30);
		agac.sil(0, 50);

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

		System.out.print("Seviye Sıralı Dolaşma: ");
		agac.seviyeSiralama();

		// Ağaçta bir öğeyi arayın
		agac.ara(0, 40);

		agac.agacDerinligi();

	}
}
