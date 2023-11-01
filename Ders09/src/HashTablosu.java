
public class HashTablosu {
	// Hash tablosu sınıfı

	HashDugumu[] kovalar;
	int kovaSayisi;
	int buyukluk;

	public HashTablosu(int kapasite) {
		// Yapıcı metot - Belirli kapasiteye sahip yeni bir Hash tablosu oluşturur.
		this.kovaSayisi = kapasite;
		this.kovalar = new HashDugumu[kapasite];
		this.buyukluk = 0;
	}

	public int getBuyukluk() {
		// Tablonun içindeki toplam öğe sayısını döndürür.
		System.out.println("Tablo büyüklük: " + buyukluk);
		return buyukluk;
	}

	public boolean bosMu() {
		// Tablo boş mu kontrolü - Tablo içinde öğe varsa "false", aksi takdirde "true" döndürür.
		return buyukluk == 0;
	}

	public int getKovaIndeksi(Integer anahtar) {
		return anahtar % kovalar.length;
	}

	public void yerlestir(Integer anahtar, String deger) {
		// Anahtar ve değer boş olmamalıdır, aksi takdirde bir beklenmyen hata fırlatılır.
		if(anahtar == null || deger == null) {
			throw new IllegalArgumentException("Anahtar veya Değer null!");
		}

		// Kova indeksi alınır.
		int kovaIndeksi = getKovaIndeksi(anahtar);

		// Kova indeksindeki ilk düğüm alınır.
		HashDugumu ilk = kovalar[kovaIndeksi];

		// Kova içindeki düğümler üzerinde dönülür.
		while(ilk != null) {
			// Eğer anahtar eşleşirse, değeri güncellenir ve işlem sonlanır.
			if(ilk.anahtar.equals(anahtar)) {
				ilk.deger = deger;
				System.out.println("Tablo güncellendi: " + anahtar + "-" + deger);
				return;
			}
			ilk = ilk.sonraki;
		}

		// Eğer eşleşen bir anahtar bulunmazsa, yeni bir düğüm oluşturulur.
		buyukluk++;
		ilk = kovalar[kovaIndeksi];
		HashDugumu dugum = new HashDugumu(anahtar, deger);
		dugum.sonraki = ilk;
		kovalar[kovaIndeksi] = dugum;
		System.out.println("Tabloya eklendi: " + anahtar + "-" + deger);
	}


	public String getir(Integer anahtar) {
		// Anahtar boş olmamalıdır, aksi takdirde bir istisna fırlatılır.
		if(anahtar == null) {
			throw new IllegalArgumentException("Anahtar null!");
		}

		// Kova indeksi alınır.
		int kovaIndeksi = getKovaIndeksi(anahtar);

		// Kova indeksindeki ilk düğüm alınır.
		HashDugumu ilk = kovalar[kovaIndeksi];

		// Kova içindeki düğümler üzerinde dönülür.
		while(ilk != null) {
			// Eğer anahtar eşleşirse, ilgili değer döndürülür.
			if(ilk.anahtar.equals(anahtar)) {
				System.out.println("Tabloda bulundu: " + anahtar + "-" + ilk.deger);
				return ilk.deger;
			}
			ilk = ilk.sonraki;
		}

		// Eşleşen bir anahtar bulunmazsa, null değeri döndürülür.
		System.out.println("Tabloda bulunamadı: " + anahtar);
		return null;
	}


	public String sil(Integer anahtar) {
		// Anahtarın kova indeksi alınır.
		int kovaIndeksi = getKovaIndeksi(anahtar);

		// Kova indeksindeki ilk düğüm alınır ve bir önceki düğüm başlangıçta null olarak ayarlanır.
		HashDugumu ilk = kovalar[kovaIndeksi];
		HashDugumu onceki = null;

		// Kova içindeki düğümler üzerinde dönülür.
		while(ilk != null) {
			// Eğer anahtar eşleşirse, döngüden çıkılır.
			if(ilk.anahtar.equals(anahtar)) {
				break;
			}
			onceki = ilk;
			ilk = ilk.sonraki;
		}

		// Eğer eşleşen bir anahtar bulunmazsa, null değeri döndürülür.
		if(ilk == null) {
			System.out.println("Tabloda bulunamadı: " + anahtar);
			return null;
		}

		// Eşleşen bir anahtar bulunursa, ilgili düğüm silinir ve tablo boyutu azaltılır.
		buyukluk--;

		// Önceki düğüm varsa, bağlantıları güncellenir.
		if(onceki != null) {
			onceki.sonraki = ilk.sonraki;
		}
		// Eğer önceki düğüm yoksa, kovanın başı güncellenir.
		else {
			kovalar[kovaIndeksi] = ilk.sonraki;
		}

		// Silinen düğümün değeri döndürülür.
		System.out.println("Tablodan silindi: " + anahtar);
		return ilk.deger;
	}


	public static void main(String[] args) {
		// Ana programın başladığı nokta

		// 10 kapasiteli yeni bir HashTablosu nesnesi oluşturulur.
		HashTablosu tablo = new HashTablosu(10);

		// Anahtarlar ve değerler eklenir.
		tablo.yerlestir(105, "Murat");
		tablo.yerlestir(21, "Ali");
		tablo.yerlestir(41, "Sena");

		tablo.yerlestir(105, "Mehmet");
		
		// Tablonun mevcut boyutu yazdırılır.
		tablo.getBuyukluk();

		// Anahtar 21 ile ilişkilendirilen değer silinir ve silinen değer yazdırılır.
		tablo.sil(21);
		tablo.sil(21);

		// Tablonun güncellenmiş boyutu yazdırılır.
		tablo.getBuyukluk();
		
		tablo.getir(21);
		tablo.getir(41);

		// Anahtar 41 ile ilişkilendirilen değer silinir ve silinen değer yazdırılır.
		tablo.sil(41);

		// Tablonun güncellenmiş boyutu yazdırılır.
		tablo.getBuyukluk();
	}
}

