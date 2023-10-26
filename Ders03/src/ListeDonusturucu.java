
public class ListeDonusturucu {

	public static void main(String[] args) {

		TekYonluBagliListe tekli = new TekYonluBagliListe();
		CiftYonluBagliListe ciftli = new CiftYonluBagliListe();
		
		tekli.basaEkle(5);
		tekli.sonaEkle(11);
		tekli.basaEkle(7);
		tekli.sonaEkle(17);

		TekYonluDugum gecici = tekli.bas; // Tek yönlü listenin başından başla
		
		while (gecici != null) { // listenin sonuna gelene dek
			ciftli.sonaEkle(gecici.veri); // Geçici düğümün verisini çift yönlü listeye ekle
			gecici = gecici.sonraki; // Bir sonraki düğüme geç
		}
		
		tekli.listeyiYazdir();
		ciftli.listeyiIleriYazdir();

	}

}
