
public class AgacDizi { 
	
	int[] agac;   // Agac dizi veri yapisi ile tutulur
	int boyut;    // Agacin maksimum boyutunu saklar

	public AgacDizi(int boyut) {
		this.boyut = boyut;  // Agacin maksimum boyutunu atar
		agac = new int[boyut];  // Diziyi olusturur
		for (int i = 0; i < boyut; i++) {
			agac[i] = -1; // Baslangicta agaci bos olarak isaretler
		}
	}

	// Belirtilen indekse veri ekler
	private void ekle(int indeks, int veri) {
		if (indeks >= boyut) { // indeks boyutu asarsa veri eklenemez
			System.out.println("Agac dolu, oge eklenemedi: " + veri);
			return;
		}

		if (agac[indeks] == -1) { // indeks bos ise veri eklenir
			agac[indeks] = veri;
			System.out.println(veri + " agaca eklendi. indeks: " + indeks);
		} else {
			// Veriyi sol alt agaca ekle
			if (veri < agac[indeks]) {
				ekle(2 * indeks + 1, veri);
			} 
			// Veriyi sag alt agaca ekle
			else {
				ekle(2 * indeks + 2, veri);
			}
		}
	}

	// Belirtilen degeri agactan siler
	private boolean sil(int indeks, int deger) {
		if (indeks >= boyut || agac[indeks] == -1) { // Agac bos veya oge yoksa
			System.out.println(deger + " agacta bulunamadi.");
			return false;
		}

		if (deger == agac[indeks]) { // Silinecek dugum bulundu
			// Durum 1: Yaprak dugum
			if (agac[2 * indeks + 1] == -1 && agac[2 * indeks + 2] == -1) {
				agac[indeks] = -1;
			}
			// Durum 2: Sol cocugu var
			else if (agac[2 * indeks + 1] != -1 && agac[2 * indeks + 2] == -1) {
				agac[indeks] = agac[2 * indeks + 1];
				sil(2 * indeks + 1, agac[2 * indeks + 1]);
			}
			// Durum 3: Sag cocugu var
			else if (agac[2 * indeks + 1] == -1 && agac[2 * indeks + 2] != -1) {
				agac[indeks] = agac[2 * indeks + 2];
				sil(2 * indeks + 2, agac[2 * indeks + 2]);
			}
			// Durum 4: iki cocugu var
			else {
				int halef = yerineDugumBul(2 * indeks + 2); // Sag alt agactan halef bul
				sil(2 * indeks + 2, halef);
				agac[indeks] = halef; // Dugumu halef ile degistir
				System.out.println(deger + " agactan silindi. indeks: " + indeks);
			}
			return true;
		} else if (deger < agac[indeks]) {
			return sil(2 * indeks + 1, deger); // Sol alt agacta arama
		} else {
			return sil(2 * indeks + 2, deger); // Sag alt agacta arama
		}
	}

	// En soldaki dugumu bulur
	private int yerineDugumBul(int indeks) {
		while (agac[2 * indeks + 1] != -1) { // Sol cocuk varsa ilerle
			indeks = 2 * indeks + 1;
		}
		return agac[indeks];
	}

	// Preorder (kok onde) dolasma
	public void kokOndeDolasma(int indeks) {
		if (indeks < boyut && agac[indeks] != -1) {
			System.out.print(agac[indeks] + " "); // Kok dugumu yazdir
			kokOndeDolasma(2 * indeks + 1); // Sol alt agaci dolas
			kokOndeDolasma(2 * indeks + 2); // Sag alt agaci dolas
		}
	}

	// inorder (kok ortada) dolasma
	public void kokOrtadaDolasma(int indeks) {
		if (indeks < boyut && agac[indeks] != -1) {
			kokOrtadaDolasma(2 * indeks + 1); // Sol alt agaci dolas
			System.out.print(agac[indeks] + " "); // Kok dugumu yazdir
			kokOrtadaDolasma(2 * indeks + 2); // Sag alt agaci dolas
		}
	}

	// Postorder (kok sonda) dolasma
	public void kokSondaDolasma(int indeks) {
		if (indeks < boyut && agac[indeks] != -1) {
			kokSondaDolasma(2 * indeks + 1); // Sol alt agaci dolas
			kokSondaDolasma(2 * indeks + 2); // Sag alt agaci dolas
			System.out.print(agac[indeks] + " "); // Kok dugumu yazdir
		}
	}

	public void seviyeSiralama() {
	    int derinlik = agacDerinligi(); // Agac derinligini al
	    int sonindeks = 0; // Her bir seviyenin baslangic indeksini tut
	    int seviyeDugumSayisi = 1; // Her bir seviyedeki eleman sayisi
	    int maxGenislik = (int) Math.pow(2, derinlik - 1); // En alt seviyedeki maksimum dugum sayisi

	    for (int seviye = 0; seviye < derinlik; seviye++) {
	        int bosluk = maxGenislik / seviyeDugumSayisi; // Bosluk miktarini hesapla
	        
	        // Seviyeyi hizalamak icin bosluklari yazdir
	        for (int i = 0; i < bosluk / 2; i++) {
	            System.out.print(" ");
	        }

	        // O seviyedeki dugumleri yazdir
	        for (int indeks = sonindeks; indeks < sonindeks + seviyeDugumSayisi; indeks++) {
	            if (indeks < boyut && agac[indeks] != -1) {
	                System.out.print(agac[indeks]);
	            } else {
	                System.out.print(" "); // Bos dugum yerlerini yazdir
	            }

	            // Her dugum arasinda bosluk birak
	            for (int i = 0; i < bosluk - 1; i++) {
	                System.out.print(" ");
	            }
	        }

	        System.out.println(); // Seviyeyi bitirip bir alt satira gec
	        sonindeks += seviyeDugumSayisi;
	        seviyeDugumSayisi *= 2; // Bir sonraki seviyedeki dugum sayisi
	    }
	}

	// Belirtilen degeri arar
	private boolean ara(int indeks, int veri) {
		if (indeks >= boyut || agac[indeks] == -1) {
			System.out.println(veri + " agacta bulunamadi.");
			return false;
		}

		if (agac[indeks] == veri) {
			System.out.println(veri + " agacta bulundu. indeks: " + indeks);
			return true;
		}

		if (veri < agac[indeks]) {
			return ara(2 * indeks + 1, veri); // Sol alt agacta ara
		} else {
			return ara(2 * indeks + 2, veri); // Sag alt agacta ara
		}
	}

	// Agacin derinligini hesaplar
	public int agacDerinligi() {
		int derinlik = 0;
		int indeks = 0;
		int seviyeDugumSayisi = 1;

		while (indeks < boyut) {
			derinlik++;
			indeks += seviyeDugumSayisi;
			seviyeDugumSayisi *= 2;
		}

		System.out.println("Agac derinligi: " + derinlik);
		return derinlik;
	}

	public static void main(String[] args) {

		int agacBoyutu = 64; // Agacin maksimum boyutu
		AgacDizi agac = new AgacDizi(agacBoyutu);

		// Agaca ogeleri ekle
		agac.ekle(0, 27);
		agac.ekle(0, 17);
		agac.ekle(0, 32);
		agac.ekle(0, 7);
		agac.ekle(0, 23);
		agac.ekle(0, 35);
		agac.ekle(0, 20);
		agac.ekle(0, 25);
		agac.ekle(0, 33);
		agac.ekle(0, 55);

		System.out.print("Kok onde Dolasma: ");
		agac.kokOndeDolasma(0);
		System.out.println();

		// Elemanlari sirayla dolasarak goruntule
		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolasma(0);
		System.out.println();

		System.out.print("Kok Sonda Dolasma: ");
		agac.kokSondaDolasma(0);
		System.out.println();

		System.out.print("Seviye Sirali Dolasma: ");
		agac.seviyeSiralama();

		// Agacta bir ogeyi ara
		agac.ara(0, 40);
		//agac.sil(0, 330);
		//agac.sil(0, 30);
		//agac.sil(0, 50);
		agac.ekle(0, 51);
		agac.ekle(0, 5);
		agac.ekle(0, 19);
		agac.sil(0, 35);

		System.out.print("Kok onde Dolasma: ");
		agac.kokOndeDolasma(0);
		System.out.println();

		System.out.print("Kok Ortada Dolasma: ");
		agac.kokOrtadaDolasma(0);
		System.out.println();

		System.out.print("Kok Sonda Dolasma: ");
		agac.kokSondaDolasma(0);
		System.out.println();

		System.out.print("Seviye Sirali Dolasma: ");
		agac.seviyeSiralama();

		agac.ara(0, 40);

		agac.agacDerinligi();
	}
}
