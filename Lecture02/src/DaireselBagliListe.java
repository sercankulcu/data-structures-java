public class DaireselBagliListe {
	// Dairesel bağlı listenin düğümünü temsil eden iç içe sınıf
	class Dugum {
		int veri;
		Dugum sonraki;

		Dugum(int veri) {
			this.veri = veri;
			this.sonraki = null;
		}
	}

	private Dugum bas;
	private Dugum son;


	// Dairesel bağlı listenin başlatılması
	public DaireselBagliListe() {
		bas = null;
		son = null;
	}

	// Listeye yeni bir düğüm eklemek
	public void ekle(int veri) {
		Dugum yeniDugum = new Dugum(veri);
		if (bas == null) {
			bas = yeniDugum;
			son = yeniDugum;
			yeniDugum.sonraki = bas; // Döngüyü oluşturmak için son düğümü başa bağla
		} else {
			yeniDugum.sonraki = bas;
			son.sonraki = yeniDugum;
			son = yeniDugum;
		}
	}

	// Dairesel bağlı listeyi yazdırmak
	public void listeyiYazdir() {
		if (bas == null) {
			System.out.println("Boş Dairesel Bağlı Liste");
			return;
		}

		Dugum current = bas;
		System.out.print("Dairesel Bağlı Liste: ");
		do {
			System.out.print(current.veri + " -> ");
			current = current.sonraki;
		} while (current != bas);
		System.out.println("Başa Dön");
	}

	public static void main(String[] args) {
		DaireselBagliListe liste = new DaireselBagliListe();

		liste.ekle(10);
		liste.ekle(20);
		liste.ekle(30);

		liste.listeyiYazdir();
	}
}
