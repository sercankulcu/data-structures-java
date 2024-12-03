// Oyuncu kaydi tutmak icin bir record tipi tanimliyoruz.
record OyuncuKaydi (String isim, int puan) {}

public class Puanlar {
	private int boyut;               // Kayitlar dizisinin kapasitesi
	private int elemanSayisi;        // Dizideki mevcut eleman sayisi
	private OyuncuKaydi[] kayitlar;  // Oyuncu kayitlarini tutan dizi

	// Puanlar constructor'i: Dizinin kapasitesini 10 olarak ayarliyoruz
	public Puanlar() {
		boyut = 10;                  // Dizinin boyutunu 10 olarak belirliyoruz
		elemanSayisi = 0;            // Baslangicta dizide hic eleman yok
		kayitlar = new OyuncuKaydi[boyut]; // Diziyi boyut kadar ayarliyoruz
	}

	// Oyuncu kaydi ekleyen fonksiyon
	public void ekle(OyuncuKaydi e) {
		int puan = e.puan(); // Eklenecek oyuncunun puani

		// Dizi doluysa ve eklenecek puan, dizinin son elemanindan kucukse
		if (elemanSayisi == boyut) { 
			if (puan <= kayitlar[boyut - 1].puan()) 
				return; // Puan yeterli degilse ekleme yapma
		} else {
			elemanSayisi++; // Dizi bossa, eleman sayisini artir
		}

		// Yeni kaydi uygun pozisyona yerlestiriyoruz
		int i = elemanSayisi - 2; // Sondan bir onceki elemandan basla
		while (i >= 0 && puan > kayitlar[i].puan()) {
			kayitlar[i + 1] = kayitlar[i]; // Kucukse saga kaydir
			i--; // Bir sonraki elemani kontrol et
		}
		kayitlar[i + 1] = e; // Oyuncuyu dogru konuma ekle
	}

	// Belirli bir indekste bulunan kaydi silme fonksiyonu
	public OyuncuKaydi kaldir(int i) {
		// Gecersiz indeks kontrolu
		if (i < 0 || i >= elemanSayisi) {
			System.out.println("Gecersiz indeks !!!"); // Gecersizse hata mesaji
			return null;
		}

		// Kaldirilan kaydi sakla
		OyuncuKaydi e = kayitlar[i]; 
		// Sonraki tum elemanlari sola kaydirarak boslugu doldur
		for (int j = i + 1; j < elemanSayisi; j++)
			kayitlar[j - 1] = kayitlar[j];

		elemanSayisi--; // Eleman sayisini bir azalt
		return e; // Kaldirilan kaydi geri dondur
	}

	// Kayitlari yazdirma fonksiyonu
	public void yazdir() {
		// Dizinin mevcut elemanlarini ekrana yazdir
		for (int i = 0; i < elemanSayisi; i++)
			System.out.println(kayitlar[i].isim() + "\t" + kayitlar[i].puan());
	}

	public static void main(String[] args) {
		// Puanlar nesnesi olusturuluyor
		Puanlar puanlar = new Puanlar();

		// Oyuncu kayitlari ekleniyor
		puanlar.ekle(new OyuncuKaydi("Anna", 660));
		puanlar.ekle(new OyuncuKaydi("Rob", 750));
		puanlar.ekle(new OyuncuKaydi("Jack", 510));
		puanlar.ekle(new OyuncuKaydi("Mike", 1105));
		puanlar.ekle(new OyuncuKaydi("Rose", 590));
		puanlar.ekle(new OyuncuKaydi("Paul", 720));
		puanlar.ekle(new OyuncuKaydi("Jill", 740));

		// Belirli bir kaydi (indeks 3) siliyoruz
		puanlar.kaldir(3);

		// Diziyi yazdiriyoruz
		puanlar.yazdir();
	}
}
