import java.util.Stack;

/*
 * "infix, Postfix ve Prefix" terimleri, matematiksel ifadelerin farkli notasyonlarla 
 * ifade edilmesini tanimlar. Bu notasyonlar, ifadelerin okunabilirligini ve 
 * hesaplanabilirligini kolaylastirmak icin kullanilir.
 * 
 * infix Notasyonu (Orta Eklem Notasyonu):
 * Operatorler operandlarin arasina yazilir. Ornegin, "a + b". 
 * islem onceligi kurallari gecerlidir.
 * 
 * Postfix Notasyonu (Ters Eklem Notasyonu):
 * Operatorler operandlarin ardindan gelir. Ornegin, "ab+".
 * islem onceligi karisikliklarini ortadan kaldirir ve daha kolay hesaplanir.
 * 
 * Prefix Notasyonu (On Eklem Notasyonu):
 * Operatorler operandlardan once gelir. Ornegin, "+ab".
 * Parantez kullanimi gerektirmeden islem onceligini belirtir.
 * 
 * Bu notasyonlar, ozellikle bilgisayar programlamasi ve hesaplama problemlerinde 
 * farkli yaklasimlari temsil eder. 
 */

public class InfixToPostfixPrefix {

    // Operatorlerin onceligini belirleyen fonksiyon
    static int oncelik(char karakter) {
        switch (karakter) {
        case '+':
        case '-':
            return 1; // Toplama ve cikarma operatorlerinin onceligi
        case '*':
        case '/':
            return 2; // Carpma ve bolme operatorlerinin onceligi
        case '^':
            return 3; // Us alma operatoru en yuksek oncelige sahiptir
        default:
            return -1; // Tanimsiz operator
        }
    }

    // infix ifadeyi Postfix'e donusturur
    static String infixToPostfix(String ifade) {
        StringBuilder postfix = new StringBuilder(); // Postfix ifadesini depolamak icin StringBuilder
        Stack<Character> stack = new Stack<>(); // Operatorleri depolamak icin yigin

        // infix ifadeyi karakter karakter ele aliriz
        for (int i = 0; i < ifade.length(); i++) {
            char karakter = ifade.charAt(i); // Her bir karakteri al

            System.out.println("islenen karakter: " + karakter);

            // Eger karakter harf ya da rakam ise postfix'e ekle
            if (Character.isLetterOrDigit(karakter)) {
                postfix.append(karakter);
                System.out.println("postfix'e ekle: " + postfix);
            } 
            // Eger karakter '(' ise, parantez acildigi icin yigina ekle
            else if (karakter == '(') {
                stack.push(karakter);
                System.out.println("Yigina koy: " + karakter);
            } 
            // Eger karakter ')' ise, parantez kapandigi icin yigindan cikarip postfix'e ekle
            else if (karakter == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    System.out.println("Yigindan al: " + stack.peek());
                    postfix.append(stack.pop());
                    System.out.println("postfix'e ekle: " + postfix);
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Gecersiz ifade"; // Hatali ifade durumunda
                } else {
                    System.out.println("Yigindan al: " + stack.peek());
                    stack.pop(); // '(' karakterini yigindan cikar
                }
            } 
            // Eger karakter bir operator ise, yigina eklemeden once oncelige gore cikar
            else {
                while (!stack.isEmpty() && oncelik(karakter) <= oncelik(stack.peek())) {
                    System.out.println("Yigindan al: " + stack.peek());
                    postfix.append(stack.pop());
                    System.out.println("postfix'e ekle: " + postfix);
                }
                System.out.println("Yigina koy: " + karakter);
                stack.push(karakter); // Operatoru yigina ekle
            }
        }

        // Yiginda kalan operatorleri postfix'e ekle
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Gecersiz ifade"; // Hatali ifade durumunda
            }
            System.out.println("Yigindan al: " + stack.peek());
            postfix.append(stack.pop());
            System.out.println("postfix'e ekle: " + postfix);
        }

        return postfix.toString(); // Postfix ifadeyi dondur
    }

    // infix ifadeyi Prefix'e donusturur
    static String infixToPrefix(String ifade) {
        // infix ifadeyi ters cevir
        String tersifade = new StringBuilder(ifade).reverse().toString();
        
        System.out.println("tersifade: " + tersifade);
        
        // Ters cevrilen ifadedeki parantezleri degistir
        for (int i = 0; i < tersifade.length(); i++) {
            if (tersifade.charAt(i) == '(') {
                tersifade = tersifade.substring(0, i) + ')' + tersifade.substring(i + 1);
            } else if (tersifade.charAt(i) == ')') {
                tersifade = tersifade.substring(0, i) + '(' + tersifade.substring(i + 1);
            }
        }
        
        System.out.println("tersifade: " + tersifade);
        
        // Ters cevrilmis infix ifadesini postfix'e donustur
        String postfix = infixToPostfix(tersifade);
        
        // Postfix ifadesini tekrar ters cevirerek prefix ifadesi elde et
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        // infix ifadeyi tanimla
        String infixifade = "A*(B+C+D)/E";
        // Sonuclari ekrana yazdir
        System.out.println("Infix ifade: " + infixifade);
        
        System.out.println();
        // infix ifadeyi Postfix ve Prefix ifadelerine donustur
        String postfixifade = infixToPostfix(infixifade); 
        System.out.println("Postfix ifade: " + postfixifade);
        
        System.out.println();
        String prefixifade = infixToPrefix(infixifade);
        System.out.println("Prefix ifade: " + prefixifade);
    }
}
