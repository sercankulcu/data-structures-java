import java.util.Arrays;
import java.util.Stack;

/*
 * "Stock Span Problem" veya Türkçe adıyla "Hisse Senedi Sıçrama Problemi," 
 * hisse senedi veya finansal veri analizinde kullanılan bir problemdir. 
 * 
 * Bu problem, belirli bir süre içindeki hisse senedi fiyatlarını inceleyerek, 
 * her bir günün hangi günlerden daha yüksek bir kapanış fiyatına sahip olduğunu 
 * bulmayı amaçlar. Yani, her günün o ana kadar kaç ardışık gün boyunca kapanış 
 * fiyatının arttığını hesaplar. 
 * 
 * Problem şu şekilde açıklanabilir: 
 * 
 * Verilen bir dizi hisse senedi fiyatı var. Her günün kapanış fiyatı sırasıyla 
 * dizi içinde bulunuyor. Her gün için, o güne kadar olan en uzun ardışık gün 
 * sayısını hesaplamamız gerekiyor, bu günün fiyatı, tüm önceki günlerin fiyatlarından 
 * yüksek veya eşitse. 
 * 
 * Örnek: Diyelim ki elimizde aşağıdaki hisse senedi fiyatları bulunuyor: 
 * 
 * [100, 80, 60, 70, 60, 75, 85] 
 * 
 * Bu verilere göre, her günün ardışık gün sayısı şu şekilde hesaplanabilir:
 * 
 * gün: 1 (ilk gün olduğu için)
 * gün: 1 (80 > 100 değil)
 * gün: 1 (60 > 80 değil)
 * gün: 2 (70 > 60)
 * gün: 1 (60 > 70 değil)
 * gün: 4 (75 > 60, 70, 60)
 * gün: 6 (85 > 75, 60, 70, 60)
 * 
 * Bu problemin amacı, hisse senedi fiyatlarının geçmiş performansını anlamak ve 
 * gelecekteki yatırım kararlarına yardımcı olmaktır. Ayrıca, teknik analizde ve 
 * finansal piyasa analizinde kullanılan bir önemli ölçümdür. 
 * 
 * */

public class HisseSenediSicrama {

	public static int[] hesaplaSicrama(int[] fiyatlar) {
		int[] sicrama = new int[fiyatlar.length];
		Stack<Integer> yigin = new Stack<>();

		yigin.push(0); 
		System.out.println("Yığına ekle " + 0);
		sicrama[0] = 1;

		for (int i = 1; i < fiyatlar.length; i++) {
			System.out.println("Adım " + i);
			while (!yigin.isEmpty() && fiyatlar[i] >= fiyatlar[yigin.peek()]) {
				System.out.println("Yığından al " + yigin.peek());
				yigin.pop();
			}

			sicrama[i] = yigin.isEmpty() ? (i + 1) : (i - yigin.peek());
			System.out.println("Yığına ekle " + i + ", sicrama[" + i + "] " + sicrama[i]);
			yigin.push(i);
		}

		return sicrama;
	}

	public static void main(String[] args) {
		int[] fiyatlar = {100, 80, 60, 70, 60, 75, 85};
		int[] sicrama = hesaplaSicrama(fiyatlar);

		System.out.println("Hisse Senedi Fiyatları: " + Arrays.toString(fiyatlar));
		System.out.println("Sıçrama Değerleri: " + Arrays.toString(sicrama));
	}
}
