
public class DaireselBagliListe {
	
	private TekYonluDugum<Integer> ilk; // Dairesel bagli listenin ilk dugumu
	private TekYonluDugum<Integer> son; // Dairesel bagli listenin son dugumu

	// Dairesel bagli listenin baslatilmasi
	public DaireselBagliListe() {
		ilk = null;
		son = null;
	}

	//Listeye yeni bir dugum ekleme
	public void ekle(int veri) {
		TekYonluDugum<Integer> yeniDugum = new TekYonluDugum<>(veri); // Yeni bir dugum olustur

		if (ilk == null) {
			ilk = yeniDugum;         // Liste bossa, yeni dugumu ilk olarak ayarla
			son = yeniDugum;         // Liste bossa, yeni dugumu son olarak ayarla
			yeniDugum.sonraki = ilk; // Dairesel baglantiyi olusturmak icin son dugumu basa bagla
		} else {
			yeniDugum.sonraki = ilk;   // Yeni dugumun sonraki referansini basa bagla
			son.sonraki = yeniDugum;   // Listenin sonundaki dugumun sonraki referansini yeni dugumu bagla
			son = yeniDugum;           // Yeni dugumu son olarak ayarla
		}
	}


	//Verilen bir dugumu dairesel bagli listeden silme
	public void sil(int hedef) {
		if (ilk == null) {
			System.out.println("Liste bos. Dugum kaldirma islemi yapilamaz.");
			return;
		}

		TekYonluDugum<Integer> onceki = null;
		TekYonluDugum<Integer> gecici = ilk;

		do {
			if (gecici.veri == hedef) {
				if (onceki != null) {
					onceki.sonraki = gecici.sonraki;
					if (gecici == ilk) {
						ilk = gecici.sonraki; // Bas dugumu kaldiriyorsak ilki guncelle
					}
					return;
				} else {
					TekYonluDugum<Integer> son = ilk;
					while (son.sonraki != ilk) {
						son = son.sonraki;
					}
					if (ilk == son) {
						ilk = null;
					} else {
						son.sonraki = ilk.sonraki;
						ilk = ilk.sonraki; // Bas dugumu kaldiriyorsak ilki guncelle
					}
					return;
				}
			}

			onceki = gecici;
			gecici = gecici.sonraki;
		} while (gecici != ilk);

		System.out.println("Verilen anahtar ile eleman bulunamadi.");
	}


	//Dairesel bagli listeyi yazdirma
	public void yazdir() {
		if (ilk == null) {
			System.out.println("Bos Dairesel Bagli Liste"); // Liste bossa bildir
			return;
		}

		TekYonluDugum<Integer> simdiki = ilk; // Baslangic dugumu ile basla
		System.out.print("Dairesel Bagli Liste: ");
		do {
			System.out.print(simdiki.veri + " -> "); // Dugumun verisini yazdir
			simdiki = simdiki.sonraki; // Bir sonraki dugume gec
		} while (simdiki != ilk); // Basa donene kadar donguyu devam ettir
		System.out.println("Basa Don"); // Liste donguyu tamamladiginda basa donuldugunu belirt
	}


	public static void main(String[] args) {

		DaireselBagliListe liste = new DaireselBagliListe();

		liste.ekle(10);
		liste.ekle(20);
		liste.ekle(30);
		liste.yazdir(); //Dairesel Bagli Liste: 10 -> 20 -> 30 -> Basa Don
		
		liste.sil(20); 
		liste.yazdir(); //Dairesel Bagli Liste: 10 -> 30 -> Basa Don
		
		liste.sil(25); //Verilen anahtar ile eleman bulunamadi.
		liste.yazdir(); //Dairesel Bagli Liste: 10 -> 30 -> Basa Don
	}
}
