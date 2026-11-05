// Sezar sifresi: metin char[] dizisine kopyalanir, her harf k sira kaydirilir. O(n)
public class SezarSifresi {

	public static void main(String[] args) {
		String sifreli = sifrele("ZEKA", 3);
		System.out.println("sifrele(\"ZEKA\", 3)  = " + sifreli);                  // CHND
		System.out.println("coz (k = 26 - 3)    = " + sifrele(sifreli, 26 - 3));   // ZEKA
		System.out.println("sifrele(\"XYZ\", 2)   = " + sifrele("XYZ", 2));        // ZAB
	}

	// Yalnizca 'A'..'Z' buyuk harfleri icin; 0 <= k
	static String sifrele(String mesaj, int k) {
		char[] harf = mesaj.toCharArray();
		for (int i = 0; i < harf.length; i++) {
			int sira = harf[i] - 'A';               // 0..25
			harf[i] = (char) ('A' + (sira + k) % 26); // sona tasan sira basa sarar
		}
		return new String(harf);
	}
}
