
public class Odev {
	
	CiftYonluBagliListe donustur(TekYonluBagliListe liste1)
	{
		CiftYonluBagliListe liste2 = new CiftYonluBagliListe();
		TekYonluDugum gecici = liste1.bas;
		
		while(gecici != null)
		{
			liste2.sonaEkle(gecici.veri);
			gecici = gecici.sonraki;
		}
		return liste2;
	}
	
	public static void main(String[] args)
	{
		Odev odev = new Odev();
		TekYonluBagliListe liste1 = new TekYonluBagliListe();
		liste1.sonaEkle(3);
		liste1.sonaEkle(5);
		liste1.sonaEkle(7);
		
		CiftYonluBagliListe liste2 = odev.donustur(liste1);
		
		liste1.listeyiYazdir();
		liste2.listeyiIleriYazdir();
	}
	

}
