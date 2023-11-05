
import java.util.HashSet;

public class EnUzunArdisikAltDizi {
	public static void main(String[] args) {
		int[] dizi = {1, 19, 11, 9, 3, 10, 4, 20, 2, 12, 13};

		int enUzunArdisikAltDiziUzunlugu = enUzunArdisikAltDiziUzunlugunuBul(dizi);

		System.out.println("En uzun ardışık alt dizi uzunluğu: " + enUzunArdisikAltDiziUzunlugu);
	}

	public static int enUzunArdisikAltDiziUzunlugunuBul(int[] dizi) {
		HashSet<Integer> set = new HashSet<>();
		int enUzunArdisikAltDiziUzunlugu = 0;

		for (int num : dizi) {
			set.add(num);
		}

		for (int num : dizi) {
			
			if (!set.contains(num - 1)) {
				int currentNum = num;
				int currentStreak = 1;

				System.out.print("{ " + currentNum + ", ");
				while (set.contains(currentNum + 1)) {
					currentNum++;
					currentStreak++;
					System.out.print(currentNum + ", ");
				}
				System.out.println("}");

				enUzunArdisikAltDiziUzunlugu = Math.max(enUzunArdisikAltDiziUzunlugu, currentStreak);
			}
		}

		return enUzunArdisikAltDiziUzunlugu;
	}
}
