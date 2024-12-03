/* 
 * "Hanoi Kuleleri," matematik ve bilgisayar bilimlerinde onemli bir problem ve 
 * ayni zamanda bir bulmaca oyunudur. Hanoi Kuleleri problemi, farkli boyutlardaki 
 * diskleri bir cubuktan digerine tasima problemi olarak tanimlanir. Bu problem 
 * genellikle uc cubuk veya direk kullanilarak modellestirilir.
 * 
 * Hanoi Kuleleri probleminin kurallari:
 * 
 * 1. Diskler baslangicta buyukten kucuge sirali olarak bir cubuk uzerindedir.
 * 2. Diskleri tasirken bos bir cubugu yardimci olarak kullanabilirsiniz.
 * 3. Her adimda sadece en ustteki disk bir cubuktan digerine tasinabilir.
 * 4. Buyuk bir disk, kucuk bir diskin uzerine koyulamaz.
 * 
 * Bu problem, tum diskleri birinci cubuktan ucuncu cubuga minimum adimda 
 * tasima islemini bulmayi hedefler. En basit haliyle, uc cubuk kullanilarak 
 * ifade edilir.
 */

import java.util.Stack;

// Disk sınıfı, diskin bilgilerini tutar
class Disk {
	int diskSayisi;  // Disk sayisi
	char kaynak;     // Kaynak cubuk
	char yardimci;   // Yardimci cubuk
	char hedef;      // Hedef cubuk

	// Constructor - Disk bilgilerini alir
	Disk(int diskSayisi, char kaynak, char yardimci, char hedef) {
		this.diskSayisi = diskSayisi;
		this.kaynak = kaynak;
		this.yardimci = yardimci;
		this.hedef = hedef;
	}
}

public class HanoiKulesi {

	// Hanoi Kulesi isleminde diskleri tasir
	static void hanoi(int diskSayisi) {
		Stack<Disk> yigin = new Stack<>(); // Diskleri tutacak bir yigin olustur

		// Baslangic durumu - Tum diskleri yiginin ustune ekler
		yigin.push(new Disk(diskSayisi, 'A', 'B', 'C'));
		System.out.println("Yigina ekle " + diskSayisi + " A B C");

		// Yigin bosalana kadar isleme devam et
		while (!yigin.isEmpty()) {
			Disk disk = yigin.pop(); // Yigindan bir disk cikar
			System.out.println("Yigindan al " + disk.diskSayisi + " " + disk.kaynak + " " + disk.yardimci + " " + disk.hedef);

			// Tek disk varsa direk tasima yap
			if (disk.diskSayisi == 1) {
				System.out.println("Diski " + disk.kaynak + " cubugundan " + disk.hedef + " cubuguna tasi.");
			} else {
				// Daha buyuk problemleri kucuk parcalara ayir
				yigin.push(new Disk(disk.diskSayisi - 1, disk.yardimci, disk.kaynak, disk.hedef));
				System.out.println("Yigina ekle " + (disk.diskSayisi - 1) + " " + disk.yardimci + " " + disk.kaynak + " " + disk.hedef);

				yigin.push(new Disk(1, disk.kaynak, disk.yardimci, disk.hedef));
				System.out.println("Yigina ekle 1 " + disk.kaynak + " " + disk.yardimci + " " + disk.hedef);

				yigin.push(new Disk(disk.diskSayisi - 1, disk.kaynak, disk.hedef, disk.yardimci));
				System.out.println("Yigina ekle " + (disk.diskSayisi - 1) + " " + disk.kaynak + " " + disk.hedef + " " + disk.yardimci);
			}
		}
	}

	public static void main(String[] args) {
		int diskSayisi = 3; // Toplam disk sayisi
		hanoi(diskSayisi);  // Hanoi Kulesi islemine basla
	}
}
