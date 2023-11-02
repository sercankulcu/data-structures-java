
import java.util.Scanner;

class Ogrenci {
	int ogrenciNo;
	String adSoyad;

	public Ogrenci(int ogrenciNo, String adSoyad) {
		this.ogrenciNo = ogrenciNo;
		this.adSoyad = adSoyad;
	}
}

class OgrenciAgaci {
	static class AgacDugumu {
		Ogrenci ogrenci;
		AgacDugumu sol;
		AgacDugumu sag;

		public AgacDugumu(Ogrenci ogrenci) {
			this.ogrenci = ogrenci;
			sol = null;
			sag = null;
		}
	}

	private AgacDugumu kok;

	public void ekle(Ogrenci ogrenci) {
		kok = ekleRekursif(kok, ogrenci);
	}

	private AgacDugumu ekleRekursif(AgacDugumu dugum, Ogrenci ogrenci) {
		if (dugum == null) {
			return new AgacDugumu(ogrenci);
		}
		if (ogrenci.ogrenciNo < dugum.ogrenci.ogrenciNo) {
			dugum.sol = ekleRekursif(dugum.sol, ogrenci);
		} else if (ogrenci.ogrenciNo > dugum.ogrenci.ogrenciNo) {
			dugum.sag = ekleRekursif(dugum.sag, ogrenci);
		}
		return dugum;
	}

	public void inorder() {
		inorderDolas(kok);
	}

	private void inorderDolas(AgacDugumu dugum) {
		if (dugum != null) {
			inorderDolas(dugum.sol);
			System.out.println("Öğrenci No: " + dugum.ogrenci.ogrenciNo + ", Ad Soyad: " + dugum.ogrenci.adSoyad);
			inorderDolas(dugum.sag);
		}
	}

	public Ogrenci ogrenciAra(int ogrenciNo) {
		return ogrenciAraRekursif(kok, ogrenciNo);
	}

	private Ogrenci ogrenciAraRekursif(AgacDugumu dugum, int ogrenciNo) {
		if (dugum == null || dugum.ogrenci.ogrenciNo == ogrenciNo) {
			return (dugum != null) ? dugum.ogrenci : null;
		}
		if (ogrenciNo < dugum.ogrenci.ogrenciNo) {
			return ogrenciAraRekursif(dugum.sol, ogrenciNo);
		} else {
			return ogrenciAraRekursif(dugum.sag, ogrenciNo);
		}
	}
}

public class OgrenciAgaciOrnegi {
	public static void main(String[] args) {
		OgrenciAgaci agac = new OgrenciAgaci();

		agac.ekle(new Ogrenci(101, "Ahmet Yılmaz"));
		agac.ekle(new Ogrenci(205, "Mehmet Demir"));
		agac.ekle(new Ogrenci(149, "Ayşe Aktaş"));
		agac.ekle(new Ogrenci(302, "Fatma Şahin"));
		agac.ekle(new Ogrenci(40, "Mustafa Koç"));

		System.out.println("Sıralı Öğrenci Listesi:");
		agac.inorder();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Öğrenci No'sunu girin: ");
		int arananOgrenciNo = scanner.nextInt();
		Ogrenci arananOgrenci = agac.ogrenciAra(arananOgrenciNo);
		if (arananOgrenci != null) {
			System.out.println("Öğrenci Bulundu: " + arananOgrenci.adSoyad);
		} else {
			System.out.println("Öğrenci Bulunamadı.");
		}
		scanner.close();
	}
}
