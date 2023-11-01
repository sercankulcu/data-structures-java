
import java.util.HashMap;
import java.util.Scanner;

/*
 * 
 * Sorgu sayısını q olarak okur.
 * Öğrenci adları ve notlarını saklamak için HashMap olan ogrenciNotlari oluşturur.
 * Her sorguyu işler:
 * Eğer sorgu tipi 1 ise, öğrenci adı ve notunu ogrenciNotlari haritasına ekler.
 * Eğer sorgu tipi 2 ise, öğrencinin bilgilerini haritadan siler.
 * Eğer sorgu tipi 3 ise, öğrencinin haritada olup olmadığını kontrol eder, bulunursa notunu yazdırır, bulunmazsa "." yazdırır.
*/

public class NotDuzeltme {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int q = scanner.nextInt();
		HashMap<String, Integer> ogrenciNotlari = new HashMap<>();

		for (int i = 0; i < q; i++) {
			int sorguTipi = scanner.nextInt();
			String ogrenciAdi = scanner.next();

			if (sorguTipi == 1) {
				int not = scanner.nextInt();
				ogrenciNotlari.put(ogrenciAdi, not);
			} else if (sorguTipi == 2) {
				ogrenciNotlari.remove(ogrenciAdi);
			} else if (sorguTipi == 3) {
				if (ogrenciNotlari.containsKey(ogrenciAdi)) {
					System.out.println(ogrenciNotlari.get(ogrenciAdi));
				} else {
					System.out.println(".");
				}
			}
		}

		scanner.close();
	}
}
