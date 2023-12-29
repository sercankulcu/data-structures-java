
public class HashTablosu {

	HashDugumu[] kovalar;
	int kovaSayisi;
	int buyukluk;

	public HashTablosu(int kapasite) {
		// Yapıcı metot - Belirli kapasiteye sahip bir Hash tablosu oluşturur.
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

		// Kova içindeki düğümler üzerinde gezilir.
		while(ilk != null) {
			// Eğer anahtar eşleşirse, değeri güncelle
			if(ilk.anahtar.equals(anahtar)) {
				ilk.deger = deger;
				System.out.println("Tablo güncellendi: " + anahtar + "-" + deger);
				return;
			}
			ilk = ilk.sonraki;
		}

		// Eğer eşleşen bir anahtar bulunmazsa, yeni düğüm oluştur
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

		// Kova içindeki düğümler üzerinde gezilir
		while(ilk != null) {
			// Eğer anahtar eşleşirse, ilgili değeri döndür
			if(ilk.anahtar.equals(anahtar)) {
				System.out.println("Tabloda bulundu: " + anahtar + "-" + ilk.deger);
				return ilk.deger;
			}
			ilk = ilk.sonraki;
		}

		// Eşleşen bir anahtar bulunmazsa, null değeri döndür
		System.out.println("Tabloda bulunamadı: " + anahtar);
		return null;
	}

	public String sil(Integer anahtar) {
		// Anahtarın kova indeksini al
		int kovaIndeksi = getKovaIndeksi(anahtar);

		// Kova indeksindeki ilk düğümü al, önceki düğümü null olarak ata.
		HashDugumu ilk = kovalar[kovaIndeksi];
		HashDugumu onceki = null;

		// Kova içindeki düğümler üzerinde gez
		while(ilk != null) {
			// Eğer anahtar eşleşirse, döngüden çık
			if(ilk.anahtar.equals(anahtar)) {
				break;
			}
			onceki = ilk;
			ilk = ilk.sonraki;
		}

		// Eğer eşleşen bir anahtar bulunmazsa, null döndür
		if(ilk == null) {
			System.out.println("Tabloda bulunamadı: " + anahtar);
			return null;
		}

		// Eşleşen bir anahtar bulunursa, ilgili düğümü sil
		buyukluk--;

		// Önceki düğüm varsa, bağlantıları güncelle
		if(onceki != null) {
			onceki.sonraki = ilk.sonraki;
		}
		// Eğer önceki düğüm yoksa, kovanın başını güncelle
		else {
			kovalar[kovaIndeksi] = ilk.sonraki;
		}

		// Silinen düğümün değerini döndür
		System.out.println("Tablodan silindi: " + anahtar);
		return ilk.deger;
	}

	public static void main(String[] args) {

		// 10 kapasiteli HashTablosu nesnesi oluştur
		HashTablosu tablo = new HashTablosu(10);

		// Anahtar ve değerler ekle
		tablo.yerlestir(105, "Murat");
		tablo.yerlestir(21, "Ali");
		tablo.yerlestir(41, "Sena");
		tablo.yerlestir(105, "Mehmet");
		
		// Tablonun mevcut boyutu
		tablo.getBuyukluk();

		// Anahtar 21 ile ilişkilendirilen değeri sil
		tablo.sil(21);
		tablo.sil(21);

		// Tablonun mevcut boyutu
		tablo.getBuyukluk();
		
		tablo.getir(21);
		tablo.getir(41);

		// Anahtar 41 ile ilişkilendirilen değeri sil
		tablo.sil(41);

		// Tablonun mevcut boyutu
		tablo.getBuyukluk();
	}
}

