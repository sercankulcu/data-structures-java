
import java.util.HashSet;

public class IlkTekrarEdenEleman {
	public static void main(String[] args) {
		int[] dizi = {3, 4, 2, 7, 4, 2, 8};

		int ilkTekrarEden = ilkTekrarEdeniBul(dizi);
		if (ilkTekrarEden != -1) {
			System.out.println("Ilk tekrar eden eleman: " + ilkTekrarEden);
		} else {
			System.out.println("Tekrar eden eleman bulunamadı.");
		}
	}

	public static int ilkTekrarEdeniBul(int[] dizi) {
		HashSet<Integer> kume = new HashSet<>();

		int ilkTekrarEden = -1;

		for (int eleman : dizi) {
			if (kume.contains(eleman)) {
				ilkTekrarEden = eleman;
				break;
			} else {
				kume.add(eleman);
			}
		}

		return ilkTekrarEden;
	}
}
