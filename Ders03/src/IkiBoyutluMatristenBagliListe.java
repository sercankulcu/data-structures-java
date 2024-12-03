// Bagli liste dugumu
class MatrisDugum<E> {
	
	E veri; // Dugumde saklanan veri
	MatrisDugum<E> sag; // Sag dugume referans
	MatrisDugum<E> asagi; // Asagi dugume referans
	
	// Dugumun baslatilmasi
	MatrisDugum(E veri) {
		this.veri = veri;
		this.sag = null;
		this.asagi = null;
	}
}

public class IkiBoyutluMatristenBagliListe {

	// 2D matristen olusturulan bagli listenin baslangic isaretcisini dondurur
	MatrisDugum<Integer> olustur(int[][] matris, int i, int j)
	{
		// i veya j sinirlari asildiysa don
		if (i > matris.length - 1 || j > matris[0].length - 1)
			return null;
		
		// Gecerli i ve j icin yeni bir dugum olustur ve
		// alt ve sag isaretcilerini sirayla ayarla
		MatrisDugum<Integer> temp = new MatrisDugum<>(matris[i][j]);
		temp.sag = olustur(matris, i, j + 1); // Sag dugumu ayarla
		temp.asagi = olustur(matris, i + 1, j); // Asagi dugumu ayarla
		return temp;
	}
	
	// Bagli liste verilerini goruntuleme
	void goruntule(MatrisDugum<Integer> baslangic)
	{
		// Saga dogru hareket etmek icin isaretci
		MatrisDugum<Integer> sag;
		// Asagi dogru hareket etmek icin isaretci
		MatrisDugum<Integer> asagi = baslangic;

		int sayac = 0; // Her satirdaki dugum sayisini saklar
		// Asagidaki dugum NULL olana kadar dongu
		while (asagi != null) {
			sayac = 0;
			sag = asagi;
			// Sag dugum NULL olana kadar saga hareket et
			while (sag != null) {
				System.out.print(sag.veri + " --> "); // Dugum verisini yazdir
				sayac++;
				sag = sag.sag;
			}
			System.out.print("null"); // Son dugum isaretcisi
			System.out.println();
			asagi = asagi.asagi; // Bir sonraki satira gec

			// Cizgiler ve oklar ile baglantilari yazdir
			for(int i = 0; i < sayac; i++) {
				System.out.print("|     ");
			}
			System.out.println();
			for(int i = 0; i < sayac; i++) {
				System.out.print("v     ");
			}
			System.out.println();
		}
		// Alt satirdaki NULL degerlerini yazdir
		for(int i = 0; i < sayac; i++) {
			System.out.print("null  ");
		}
	}

	// Ana program
	public static void main(String[] args)
	{
		IkiBoyutluMatristenBagliListe matristenBagliListe = new IkiBoyutluMatristenBagliListe();
		// Iki boyutlu matris
		int[][] matris = { 	{1, 2, 13},
							{14, 5, 6},
							{7, 18, 9} };
		// Bagli listeyi olustur
		MatrisDugum<Integer> baslangic = matristenBagliListe.olustur(matris, 0, 0);
		// Bagli listeyi goruntule
		matristenBagliListe.goruntule(baslangic);
	}
}
