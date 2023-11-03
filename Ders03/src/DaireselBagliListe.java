
public class DaireselBagliListe {
	
	private TekYonluDugum<Integer> bas; // Dairesel bağlı listenin baş düğümü
	private TekYonluDugum<Integer> son; // Dairesel bağlı listenin son düğümü

	// Dairesel bağlı listenin başlatılması
	public DaireselBagliListe() {
		bas = null;
		son = null;
	}

	//Listeye yeni bir düğüm ekleme
	public void ekle(int veri) {
		TekYonluDugum<Integer> yeniDugum = new TekYonluDugum<Integer>(veri); // Yeni bir düğüm oluştur

		if (bas == null) {
			bas = yeniDugum;         // Liste boşsa, yeni düğümü baş olarak ayarla
			son = yeniDugum;         // Liste boşsa, yeni düğümü son olarak ayarla
			yeniDugum.sonraki = bas; // Dairesel bağlantıyı oluşturmak için son düğümü başa bağla
		} else {
			yeniDugum.sonraki = bas;   // Yeni düğümün sonraki referansını başa bağla
			son.sonraki = yeniDugum;   // Listenin sonundaki düğümün sonraki referansını yeni düğümü bağla
			son = yeniDugum;           // Yeni düğümü son olarak ayarla
		}
	}


	//Verilen bir düğümü dairesel bağlı listeden silme
	public void elemanSil(int hedefVeri) {
		if (bas == null) {
			System.out.println("Liste boş. Düğüm kaldırma işlemi yapılamaz.");
			return;
		}

		TekYonluDugum<Integer> onceki = null;
		TekYonluDugum<Integer> gecici = bas;

		do {
			if (gecici.veri == hedefVeri) {
				if (onceki != null) {
					onceki.sonraki = gecici.sonraki;
					if (gecici == bas) {
						bas = gecici.sonraki; // Baş düğümü kaldırıyorsak başı güncelle
					}
					gecici = null;
					return;
				} else {
					TekYonluDugum<Integer> son = bas;
					while (son.sonraki != bas) {
						son = son.sonraki;
					}
					if (bas == son) {
						bas = null;
					} else {
						son.sonraki = bas.sonraki;
						bas = bas.sonraki; // Baş düğümü kaldırıyorsak başı güncelle
					}
					return;
				}
			}

			onceki = gecici;
			gecici = gecici.sonraki;
		} while (gecici != bas);

		System.out.println("Verilen anahtar ile eleman bulunamadı.");
	}


	//Dairesel bağlı listeyi yazdırma
	public void listeyiYazdir() {
		if (bas == null) {
			System.out.println("Boş Dairesel Bağlı Liste"); // Liste boşsa bildir
			return;
		}

		TekYonluDugum<Integer> simdiki = bas; // Başlangıç düğümü ile başla
		System.out.print("Dairesel Bağlı Liste: ");
		do {
			System.out.print(simdiki.veri + " -> "); // Düğümün verisini yazdır
			simdiki = simdiki.sonraki; // Bir sonraki düğüme geç
		} while (simdiki != bas); // Başa dönene kadar döngüyü devam ettir
		System.out.println("Başa Dön"); // Liste döngüyü tamamladığında başa dönüldüğünü belirt
	}


	public static void main(String[] args) {

		DaireselBagliListe liste = new DaireselBagliListe();

		liste.ekle(10);
		liste.ekle(20);
		liste.ekle(30);
		liste.listeyiYazdir(); //Dairesel Bağlı Liste: 10 -> 20 -> 30 -> Başa Dön
		
		liste.elemanSil(20); 
		liste.listeyiYazdir(); //Dairesel Bağlı Liste: 10 -> 30 -> Başa Dön
		
		liste.elemanSil(25); //Verilen anahtar ile eleman bulunamadı.
		liste.listeyiYazdir(); //Dairesel Bağlı Liste: 10 -> 30 -> Başa Dön
	}
}
