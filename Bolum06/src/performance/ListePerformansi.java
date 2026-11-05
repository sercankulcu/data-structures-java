package performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

// ArrayList, LinkedList ve CopyOnWriteArrayList: ayni islemin suresi.
// Her hucre once ISINMA kez olcumsuz calistirilir (JIT derlemesi biter),
// sonra TEKRAR olcumun en iyisi alinir. Sayilar makineye gore degisir;
// onemli olan mutlak sure degil, siniflar arasindaki kat farkidir.
public class ListePerformansi {

	static final int N = 20_000;
	static final int ISINMA = 5;    // JIT derleyicisinin isinmasi icin olcumsuz turlar
	static final int TEKRAR = 5;    // olculen turlar; en iyisi alinir (gurultu azalir)
	static long cop;                // sonuclar buraya yazilir: JIT donguyu silemesin

	interface Islem {
		void calistir(List<Integer> liste, int n);
	}

	static void sonaEkle(List<Integer> l, int n) {
		for (int i = 0; i < n; i++) l.add(i);
	}

	static void basaEkle(List<Integer> l, int n) {
		for (int i = 0; i < n; i++) l.add(0, i);
	}

	static void indisliGez(List<Integer> l, int n) {
		long t = 0;
		for (int i = 0; i < l.size(); i++) t += l.get(i);
		cop += t;
	}

	static void okuyucuIleGez(List<Integer> l, int n) {
		long t = 0;
		for (int x : l) t += x;
		cop += t;
	}

	// Bos listeyle baslayan islemler (ekleme) icin dolu = false
	static double olc(Supplier<List<Integer>> uret, Islem islem, int n, boolean dolu) {
		long enIyi = Long.MAX_VALUE;
		for (int tur = 0; tur < ISINMA + TEKRAR; tur++) {
			List<Integer> liste = uret.get();
			if (dolu) {
				List<Integer> gecici = new ArrayList<>(n);
				sonaEkle(gecici, n);
				liste.addAll(gecici);   // hazirlik suresi olcume girmez
			}
			long bas = System.nanoTime();
			islem.calistir(liste, n);
			long sure = System.nanoTime() - bas;
			if (tur >= ISINMA)
				enIyi = Math.min(enIyi, sure);
		}
		return enIyi / 1e6;
	}

	// Bir islem icin uc sinifin surelerini tek satirda yazar (ms)
	static void satir(String ad, Islem islem, boolean dolu) {
		double a = olc(ArrayList::new, islem, N, dolu);
		double l = olc(LinkedList::new, islem, N, dolu);
		double c = olc(CopyOnWriteArrayList::new, islem, N, dolu);
		System.out.printf(Locale.ROOT, "%-22s %10.2f %11.2f %11.2f%n", ad, a, l, c);
	}

	public static void main(String[] args) {
		System.out.println("n = " + N + " oge, sure (ms), en iyi " + TEKRAR + " olcum ("
				+ ISINMA + " isinma turundan sonra)");
		System.out.printf("%-22s %10s %11s %11s%n", "Islem", "ArrayList", "LinkedList", "COWList");
		satir("n x add(e) (sona)", ListePerformansi::sonaEkle, false);
		satir("n x add(0, e) (basa)", ListePerformansi::basaEkle, false);
		satir("for + get(i)", ListePerformansi::indisliGez, true);
		satir("for-each (okuyucu)", ListePerformansi::okuyucuIleGez, true);
		System.out.println("Beklenen: ArrayList get ve sona eklemede hizli; LinkedList basa");
		System.out.println("eklemede hizli ama get(i) dongusunde O(n^2); CopyOnWriteArrayList");
		System.out.println("her yazmada diziyi kopyalar, okumada ArrayList kadar hizlidir.");
		if (cop == 42) System.out.println();   // cop degiskeni kullanilsin
	}
}
