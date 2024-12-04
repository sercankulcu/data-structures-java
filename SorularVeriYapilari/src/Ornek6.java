import java.util.Stack;

public class Ornek6 {

    // Onluk sayiyi ikilik sisteme donusturmek icin recursive fonksiyon
    public static String decimalToBinary(int n) {
        if (n == 0) {
            return "";
        } else {
            return decimalToBinary(n / 2) + (n % 2);
        }
    }

    // Onluk sayiyi ikilik sisteme donusturmek icin stack kullanan fonksiyon
    public static void stackBinaryConversion(int n) {
        Stack<Integer> stack = new Stack<>();
        while (n > 0) {
            stack.push(n % 2);
            n /= 2;
        }

        System.out.print("Stack kullanarak ikilik sistem: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    // Onluk sayiyi farkli sayi sistemlerine donusturen fonksiyon
    public static void convertToOtherSystems(int n) {
        System.out.println("Onluk: " + n);
        System.out.println("Ikilik: " + decimalToBinary(n));
        System.out.println("Sekizlik: " + Integer.toOctalString(n));
        System.out.println("Onaltilik: " + Integer.toHexString(n).toUpperCase());
    }

    // Edge case'leri ele alarak donusum yapma
    public static void handleEdgeCasesAndConvert(int n) {
        if (n == 0) {
            System.out.println("Ikilik sistem (edge case - sifir): 0");
        } else if (n < 0) {
            System.out.println("Negatif sayi tespit edildi. Mutlak degerini donusturuyoruz.");
            n = Math.abs(n);
        }

        // Ikilik sistemde stack kullanarak donustur
        stackBinaryConversion(n);

        // Farkli sayi sistemlerine donustur
        convertToOtherSystems(n);
    }

    public static void main(String[] args) {
    	
        int[] numbers = {12, 0, -25, 30};

        // Birden fazla sayiyi isleme
        for (int n : numbers) {
            System.out.println("\nSayiyi isliyoruz: " + n);
            handleEdgeCasesAndConvert(n);
        }
    }
}
