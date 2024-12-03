import java.util.Stack; 

// Dugum sinifi: Her bir dugum bir veri, sol ve sag alt dugum icerir
class Dugum<E> {
    E veri;  // Dugumun icerdigi veri
    Dugum<E> sol;  // Sol alt dugum
    Dugum<E> sag;  // Sag alt dugum

    // Yapici metot: Dugumu olustururken veriyi alir
    public Dugum(E veri) {
        this.veri = veri;  // Veriyi ata
        sol = null;  // Baslangicta sol alt dugum bos
        sag = null;  // Baslangicta sag alt dugum bos
    }
}

// Aritmetik ifade Agaci sinifi
public class AritmetikIfadeAgaci {

    // Postfix ifadeyi bir aritmetik agaca cevirir
    public Dugum<Character> ifadeyiAritmetikAgacaCevir(String ifade) {
        Stack<Dugum<Character>> yigit = new Stack<>();

        // Her bir karakter uzerinde dongu
        for (char c : ifade.toCharArray()) {
            if (c == ' ') { 
                continue; // Bosluk karakterlerini atla
            }

            if (c == '+' || c == '-' || c == '*' || c == '/') {
                // Operatorse, bir dugum olustur ve alt dugumleri ata
                Dugum<Character> dugum = new Dugum<>(c);
                dugum.sag = yigit.pop(); // Sag alt dugum
                dugum.sol = yigit.pop(); // Sol alt dugum
                yigit.push(dugum); // Yeni dugumu yigita ekle
            } else {
                // Operator degilse, bir yaprak dugumu olarak yigita ekle
                Dugum<Character> dugum = new Dugum<>(c);
                yigit.push(dugum);
            }
        }
        return yigit.pop(); // Yigittaki son eleman kok dugum
    }

    // Agaci metin formatinda goruntuler
    public void agaciGoruntule(Dugum<Character> dugum, String onEk) {
        if (dugum != null) {
            System.out.println(onEk + dugum.veri); // Mevcut dugumun verisini yazdir
            agaciGoruntule(dugum.sol, onEk + "|-- "); // Sol alt agaci goruntule
            agaciGoruntule(dugum.sag, onEk + "|-- "); // Sag alt agaci goruntule
        }
    }

    // Agacin kok dugumundeki aritmetik ifadeyi hesaplar
    public int agacDegeriniHesapla(Dugum<Character> kok) {
        if (kok == null) {
            return 0; // Bos dugumse 0 dondur
        }

        if (kok.sol == null && kok.sag == null) {
            // Yaprak dugumse, degeri dondur
            return Character.getNumericValue(kok.veri);
        }

        // Sol ve sag alt dugumlerin degerlerini hesapla
        int solDeger = agacDegeriniHesapla(kok.sol);
        int sagDeger = agacDegeriniHesapla(kok.sag);

        // Operatore gore hesaplama yap
        switch (kok.veri) {
            case '+':
                return solDeger + sagDeger;
            case '-':
                return solDeger - sagDeger;
            case '*':
                return solDeger * sagDeger;
            case '/':
                if (sagDeger != 0) {
                    return solDeger / sagDeger;
                } else {
                    throw new ArithmeticException("Sifira bolme hatasi");
                }
            default:
                throw new IllegalArgumentException("Gecersiz operator: " + kok.veri);
        }
    }

    // infix ifadeyi postfix'e cevirir
    public String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder(); // Postfix sonucu tutar
        Stack<Character> operatorStack = new Stack<>(); // Operatorleri saklar

        // infix ifade karakterlerinde dolas
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c); // Sayilari ve harfleri dogrudan ekle
            } else if (c == '(') {
                operatorStack.push(c); // Sol parantezi yigita ekle
            } else if (c == ')') {
                // Sag parantezse, '(' gelene kadar yigittan cikar
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.pop(); // '(' parantezini cikar
            } else {
                // Operatorun onceligine gore islemler
                while (!operatorStack.isEmpty() && oncelik(c) <= oncelik(operatorStack.peek())) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(c);
            }
        }

        // Yigittaki kalan elemanlari ekle
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(') {
                return "invalid infix expression"; // Hatali ifade kontrolu
            }
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    // Operator onceligini dondurur
    public int oncelik(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1; // Gecersiz operator icin
        }
    }
    
    public static void main(String[] args) {
        // Aritmetik ifade Agaci olusturma ve islemleri
    	AritmetikIfadeAgaci agac = new AritmetikIfadeAgaci();

        // infix ifadeyi postfix'e cevirme
        String ifade = agac.infixToPostfix("(3+5)/2*5-(4*2)");

        // Postfix ifadeyi aritmetik agaca cevirme
        Dugum<Character> kok = agac.ifadeyiAritmetikAgacaCevir(ifade);

        // Agaci gorsellestirerek yazdirma
        System.out.println("ikili ifade Agaci:");
        agac.agaciGoruntule(kok, "");

        // Agacin kok dugumundeki degeri hesaplama
        int sonuc = agac.agacDegeriniHesapla(kok);
        System.out.println("\nKokteki Deger: " + sonuc);
    }
}
