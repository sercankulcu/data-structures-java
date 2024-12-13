
class HashDugumu {
    // HashDugumu sinifi, Hash tablosunda her bir elemani temsil eder
    Integer anahtar; // Anahatarin tutuldugu degisken
    String deger;    // Degerin tutuldugu degisken
    HashDugumu sonraki; // Baglantiyi saglayan sonraki dugum

    public HashDugumu(Integer anahtar, String deger) {
        this.anahtar = anahtar;
        this.deger = deger;
        this.sonraki = null;
    }
}

public class HashTablosu {

	HashDugumu[] kovalar;
	int kovaSayisi;
	int buyukluk;

	public HashTablosu(int kapasite) {
		// Yapici metot - Belirli kapasiteye sahip bir Hash tablosu olusturur.
		this.kovaSayisi = kapasite;
		this.kovalar = new HashDugumu[kapasite];
		this.buyukluk = 0;
	}

	public int getBuyukluk() {
		// Tablonun icindeki toplam oge sayisini dondurur.
		System.out.println("Tablo buyukluk: " + buyukluk);
		return buyukluk;
	}

	public boolean bosMu() {
		// Tablo bos mu kontrolu - Tablo icinde oge varsa "false", aksi takdirde "true" dondurur.
		return buyukluk == 0;
	}

	public int getKovaIndeksi(Integer anahtar) {
		return anahtar % kovalar.length;
	}

	public void yerlestir(Integer anahtar, String deger) {
		// Anahtar ve deger bos olmamalidir, aksi takdirde bir beklenmeyen hata firlatilir.
		if(anahtar == null || deger == null) {
			throw new IllegalArgumentException("Anahtar veya Deger null!");
		}

		// Kova indeksi alinabilir.
		int kovaIndeksi = getKovaIndeksi(anahtar);

		// Kova indeksindeki ilk dugum alinir.
		HashDugumu ilk = kovalar[kovaIndeksi];

		// Kova icindeki dugumler uzerinde gezilir.
		while(ilk != null) {
			// Eger anahtar eslesirse, degeri guncelle
			if(ilk.anahtar.equals(anahtar)) {
				ilk.deger = deger;
				System.out.println("Tablo guncellendi: " + anahtar + "-" + deger);
				return;
			}
			ilk = ilk.sonraki;
		}

		// Eger eslesen bir anahtar bulunmazsa, yeni dugum olustur
		buyukluk++;
		ilk = kovalar[kovaIndeksi];
		HashDugumu dugum = new HashDugumu(anahtar, deger);
		dugum.sonraki = ilk;
		kovalar[kovaIndeksi] = dugum;
		System.out.println("Tabloya eklendi: " + anahtar + "-" + deger);
	}

	public String getir(Integer anahtar) {
		// Anahtar bos olmamalidir, aksi takdirde bir istisna firlatilir.
		if(anahtar == null) {
			throw new IllegalArgumentException("Anahtar null!");
		}

		// Kova indeksi alinabilir.
		int kovaIndeksi = getKovaIndeksi(anahtar);

		// Kova indeksindeki ilk dugum alinir.
		HashDugumu ilk = kovalar[kovaIndeksi];

		// Kova icindeki dugumler uzerinde gezilir
		while(ilk != null) {
			// Eger anahtar eslesirse, ilgili degeri dondur
			if(ilk.anahtar.equals(anahtar)) {
				System.out.println("Tabloda bulundu: " + anahtar + "-" + ilk.deger);
				return ilk.deger;
			}
			ilk = ilk.sonraki;
		}

		// Eslesen bir anahtar bulunmazsa, null degeri dondur
		System.out.println("Tabloda bulunamadi: " + anahtar);
		return null;
	}

	public String sil(Integer anahtar) {
		// Anahtarin kova indeksini al
		int kovaIndeksi = getKovaIndeksi(anahtar);

		// Kova indeksindeki ilk dugumu al, onceki dugumu null olarak ata.
		HashDugumu ilk = kovalar[kovaIndeksi];
		HashDugumu onceki = null;

		// Kova icindeki dugumler uzerinde gez
		while(ilk != null) {
			// Eger anahtar eslesirse, donguden cik
			if(ilk.anahtar.equals(anahtar)) {
				break;
			}
			onceki = ilk;
			ilk = ilk.sonraki;
		}

		// Eger eslesen bir anahtar bulunmazsa, null dondur
		if(ilk == null) {
			System.out.println("Tabloda bulunamadi: " + anahtar);
			return null;
		}

		// Eslesen bir anahtar bulunursa, ilgili dugumu sil
		buyukluk--;

		// Onceki dugum varsa, baglantilari guncelle
		if(onceki != null) {
			onceki.sonraki = ilk.sonraki;
		}
		// Eger onceki dugum yoksa, kovanin basini guncelle
		else {
			kovalar[kovaIndeksi] = ilk.sonraki;
		}

		// Silinen dugumun degerini dondur
		System.out.println("Tablodan silindi: " + anahtar);
		return ilk.deger;
	}

	public static void main(String[] args) {

		// 10 kapasiteli HashTablosu nesnesi olustur
		HashTablosu tablo = new HashTablosu(10);

		// Anahtar ve degerler ekle
		tablo.yerlestir(105, "Murat");
		tablo.yerlestir(21, "Ali");
		tablo.yerlestir(41, "Sena");
		tablo.yerlestir(105, "Mehmet");
		
		// Tablonun mevcut boyutu
		tablo.getBuyukluk();

		// Anahtar 21 ile iliskilendirilen degeri sil
		tablo.sil(21);
		tablo.sil(21);

		// Tablonun mevcut boyutu
		tablo.getBuyukluk();
		
		tablo.getir(21);
		tablo.getir(41);

		// Anahtar 41 ile iliskilendirilen degeri sil
		tablo.sil(41);

		// Tablonun mevcut boyutu
		tablo.getBuyukluk();
	}
}
