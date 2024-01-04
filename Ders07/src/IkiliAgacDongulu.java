import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IkiliAgacDongulu {

	private Dugum<Integer> kok;

	public void ikiliAgacOlustur() {
		Dugum<Integer> birinci = new Dugum<>(1);     // Birinci düğümü oluştur (kök düğüm)
		Dugum<Integer> ikinci = new Dugum<>(2);      // İkinci düğümü oluştur
		Dugum<Integer> ucuncu = new Dugum<>(3);      // Üçüncü düğümü oluştur
		Dugum<Integer> dorduncu = new Dugum<>(4);    // Dördüncü düğümü oluştur
		Dugum<Integer> besinci = new Dugum<>(5);     // Beşinci düğümü oluştur
		Dugum<Integer> altinci = new Dugum<>(6);     // Altıncı düğümü oluştur
		Dugum<Integer> yedinci = new Dugum<>(7);     // Yedinci düğümü oluştur

		kok = birinci;               // Kök düğümü belirle
		birinci.sol = ikinci;        // Birinci düğümün sol alt düğümünü ayarla
		birinci.sag = ucuncu;        // Birinci düğümün sağ alt düğümünü ayarla
		ikinci.sol = dorduncu;       // İkinci düğümün sol alt düğümünü ayarla
		ikinci.sag = besinci;        // İkinci düğümün sağ alt düğümünü ayarla
		ucuncu.sol = altinci;        // Üçüncü düğümün sol alt düğümünü ayarla
		ucuncu.sag = yedinci;        // Üçüncü düğümün sağ alt düğümünü ayarla
	}


	public void kokBastaDolas(Dugum<Integer> kok) {
		if (kok == null) {
			return;  // Eğer kök düğüm null ise, işlem sonlandırılır (temel durum)
		}
		Stack<Dugum<Integer>> yigit = new Stack<>();  // Yığıt (stack) oluştur
		yigit.push(kok);  // Kök düğümü yığına ekleyin

		while (!yigit.isEmpty()) {  // Yığıt boş olana kadar işlemi tekrarla
			Dugum<Integer> gecici = yigit.pop();  // Yığıttan bir düğüm çıkar
			System.out.print(gecici.veri + " ");  // Düğümün verisini ekrana yazdır

			if (gecici.sag != null) {
				yigit.push(gecici.sag);  // Sağ alt düğümü yığına ekleyin
			}

			if (gecici.sol != null) {
				yigit.push(gecici.sol);  // Sol alt düğümü yığına ekleyin
			}
		}
	}


	public void kokOrtadaDolas(Dugum<Integer> kok) {
		if (kok == null) {
			return;  // Eğer kök düğüm null ise, işlem sonlandırılır (temel durum)
		}
		Stack<Dugum<Integer>> yigit = new Stack<>();  // Yığıt (stack) oluştur
		Dugum<Integer> gecici = kok;

		while (!yigit.isEmpty() || gecici != null) {  // Yığıt boş değilse veya gecici düğüm null değilse işlemi tekrarla
			if (gecici != null) {  // Gecici düğüm null değilse
				yigit.push(gecici);  // Gecici düğümü yığına ekleyin
				gecici = gecici.sol;  // Sol alt düğüme geçin
			} else {
				gecici = yigit.pop();  // Yığıttan bir düğüm çıkar
				System.out.print(gecici.veri + " ");  // Düğümün verisini ekrana yazdır
				gecici = gecici.sag;  // Sağ alt düğüme geçin
			}
		}
	}


	public void kokSondaDolas(Dugum<Integer> kok) {
		Dugum<Integer> simdiki = kok;  // Geçerli düğümü kök düğüm olarak ayarla
		Stack<Dugum<Integer>> yigit = new Stack<>();  // Yığıt (stack) oluştur

		while (simdiki != null || !yigit.isEmpty()) {  // Geçerli düğüm null olmadığı veya yığıt boş olmadığı sürece işlemi tekrarla
			if (simdiki != null) {  // Geçerli düğüm null değilse
				yigit.push(simdiki);  // Geçerli düğümü yığına ekleyin
				simdiki = simdiki.sol;  // Sol alt düğüme geçin
			} else {
				Dugum<Integer> gecici = yigit.peek().sag;  // Geçerli düğümün sağ alt düğümünü kontrol edin

				if (gecici == null) {  // Sağ alt düğüm null ise
					gecici = yigit.pop();  // Yığıttan bir düğüm çıkar
					System.out.print(gecici.veri + " ");  // Düğümün verisini ekrana yazdır

					while (!yigit.isEmpty() && gecici == yigit.peek().sag) {  // Yığıt boş değil ve düğüm sağ alt düğümse
						gecici = yigit.pop();  // Yığıttan bir düğüm çıkar
						System.out.print(gecici.veri + " ");  // Düğümün verisini ekrana yazdır
					}
				} else {
					simdiki = gecici;  // Sağ alt düğüme geçin
				}
			}
		}
	}

	public void seviyeSiraliDolas(Dugum<Integer> kok) {
		if (kok == null)
			return; // Kök düğüm boşsa işlemi sonlandır

		Queue<Dugum<Integer>> kuyruk = new LinkedList<>();
		kuyruk.add(kok);

		while (!kuyruk.isEmpty()) {
			Dugum<Integer> tempDugum = kuyruk.poll();
			System.out.print(tempDugum.veri + " "); // Düğümün değerini yazdır

			if (tempDugum.sol != null)
				kuyruk.offer(tempDugum.sol); // Sol düğümü kuyruğa ekle

			if (tempDugum.sag != null)
				kuyruk.offer(tempDugum.sag); // Sağ düğümü kuyruğa ekle
		}
	}

	private int agacDerinligi(Dugum<Integer> dugum) {
		if (dugum == null) {
			return 0; // Boş bir ağacın derinliği 0'dır
		} else {
			int solDerinlik = agacDerinligi(dugum.sol); // Sol alt ağacın derinliği hesaplanır
			int sagDerinlik = agacDerinligi(dugum.sag); // Sağ alt ağacın derinliği hesaplanır

			return 1 + Math.max(solDerinlik, sagDerinlik); // Sol ve sağ alt ağaçların derinliği karşılaştırılıp, maksimum alınır
		}
	}

	public static void main(String[] args) {

		IkiliAgacDongulu ia = new IkiliAgacDongulu();
		ia.ikiliAgacOlustur();

		System.out.print("Kök başta: ");
		ia.kokBastaDolas(ia.kok);
		System.out.println();

		System.out.print("Kök ortada: ");
		ia.kokOrtadaDolas(ia.kok);
		System.out.println();

		System.out.print("Kök Sonda: ");
		ia.kokSondaDolas(ia.kok);
		System.out.println();

		System.out.print("Seviye Sıralı: ");
		ia.seviyeSiraliDolas(ia.kok);
		System.out.println();

		System.out.println("Ağaç derinliği: " + ia.agacDerinligi(ia.kok));
	}
}
