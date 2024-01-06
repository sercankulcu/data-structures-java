
record OyuncuKaydi (String isim, int puan) {}

public class Puanlar {
	private int boyut;
	private int elemanSayisi;
	private OyuncuKaydi[] kayitlar;

	public Puanlar() {
		boyut = 10;
		elemanSayisi = 0;
		kayitlar = new OyuncuKaydi[boyut];
	}

	public void ekle(OyuncuKaydi e) {
		int puan = e.puan(); // eklenecek puan
		if (elemanSayisi == boyut) { // dizi dolu ise
			if (puan <= kayitlar[boyut - 1].puan()) 
				return; // yeterince yüksek değil - yok say
		} else {
			elemanSayisi++; // dolu değilse, ekle
		}

		int i = elemanSayisi - 2; // sondan bir öncekiyle başla
		while (i >= 0 && puan > kayitlar[i].puan()) {
			kayitlar[i + 1] = kayitlar[i]; // daha küçükse sağa kaydır
			i--;
		}
		kayitlar[i + 1] = e; // e'yi boş yere koy
	}

	public OyuncuKaydi kaldir(int i) {
		if (i < 0 || i >= elemanSayisi) {
			System.out.println("Geçersiz indeks !!!");
			return null;
		}

		OyuncuKaydi e = kayitlar[i]; // kaldırılan nesneyi sakla
		for (int j = i + 1; j < elemanSayisi; j++)
			kayitlar[j - 1] = kayitlar[j]; // diğer nesneleri sola kaydır

		elemanSayisi--;
		return e; 
	}

	public void yazdir() {
		for (int i = 0; i < elemanSayisi; i++)
			System.out.println(kayitlar[i].isim() + "\t" + kayitlar[i].puan());
	}

	public static void main(String[] args) {

		Puanlar puanlar = new Puanlar();
		puanlar.ekle(new OyuncuKaydi("Anna", 660));
		puanlar.ekle(new OyuncuKaydi("Rob", 750));
		puanlar.ekle(new OyuncuKaydi("Jack", 510));
		puanlar.ekle(new OyuncuKaydi("Mike", 1105));
		puanlar.ekle(new OyuncuKaydi("Rose", 590));
		puanlar.ekle(new OyuncuKaydi("Paul", 720));
		puanlar.ekle(new OyuncuKaydi("Jill", 740));
		puanlar.kaldir(3);
		puanlar.yazdir();
	}
}
