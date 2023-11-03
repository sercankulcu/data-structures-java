
public class TekCiftYonluBagliListeDonusturucu {

	CiftYonluBagliListe donustur(TekYonluBagliListe liste1) {
		// Çift yönlü bağlı listeyi oluştur
		CiftYonluBagliListe liste2 = new CiftYonluBagliListe();

		// İlk düğümü işaret et
		TekYonluDugum<Integer> gecici = liste1.bas;

		// Tek yönlü listedeki her düğümü çift yönlü listeye ekleyin
		while (gecici != null) {
			liste2.sonaEkle(gecici.veri); // Çift yönlü listeye düğümü ekle
			gecici = gecici.sonraki; // Tek yönlü listede bir sonraki düğüme geç
		}

		return liste2; // Dönüştürülmüş çift yönlü listeyi döndür
	}


	public static void main(String[] args) {

		TekCiftYonluBagliListeDonusturucu donusturucu = new TekCiftYonluBagliListeDonusturucu();
		TekYonluBagliListe tekli = new TekYonluBagliListe();

		tekli.basaEkle(5);
		tekli.sonaEkle(11);
		tekli.basaEkle(7);
		tekli.sonaEkle(17);

		CiftYonluBagliListe ciftli = donusturucu.donustur(tekli);

		tekli.listeyiYazdir();
		ciftli.listeyiIleriYazdir();

	}

}
