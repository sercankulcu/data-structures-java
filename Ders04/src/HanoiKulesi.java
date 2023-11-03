/*
 * "Hanoi Kuleleri," matematik ve bilgisayar bilimlerinde önemli bir problem olan 
 * ve oyun olarak da bilinen bir bulmacadır. Hanoi Kuleleri problemi, birçok diski 
 * farklı boyutlarda ve büyükten küçüğe sıralı bir şekilde bir çubuktan diğerine 
 * taşıma problemi olarak tanımlanır. Bu problem genellikle üç çubuk veya direk 
 * aracılığıyla modellemektedir.
 * 
 * Hanoi Kuleleri problemi şu temel kurallarla tanımlanır:
 * 
 * Başlangıçta, diskler büyükten küçüğe sıralı bir şekilde bir çubuk üzerindedir.
 * Disksiz olan çubuklardan birini yardımcı çubuk olarak kullanabilirsiniz.
 * Sadece en üstteki diski bir çubuktan diğerine taşıma yeteneğiniz vardır.
 * Her adımda, daha büyük bir disk daha küçük bir diskin üzerine konulamaz.
 * 
 * Problem, tüm diskleri birinci çubuktan üçüncü çubuğa taşımanın nasıl yapılabileceğini 
 * ve kaç adım gerektiğini bulmayı amaçlar. İdeal olarak, minimum adımda tüm diskleri 
 * taşımak istenir. Hanoi Kuleleri probleminin en basit hali üç çubuk kullanılarak 
 * ifade edilir.
 * 
 * */

import java.util.Stack;

class Disk {
	int diskSayisi;
	char kaynak, yardimci, hedef;

	Disk(int diskSayisi, char kaynak, char yardimci, char hedef) {
		this.diskSayisi = diskSayisi;
		this.kaynak = kaynak;
		this.yardimci = yardimci;
		this.hedef = hedef;
	}
}

public class HanoiKulesi {

	// Hanoi Kulesi işlemini yapan fonksiyon
	static void hanoi(int diskSayisi) {
		Stack<Disk> yigin = new Stack<>();

		// Başlangıç durumu
		yigin.push(new Disk(diskSayisi, 'A', 'B', 'C'));
		System.out.println("Yığına ekle 3 A B C");
		while (!yigin.isEmpty()) {
			Disk disk = yigin.pop();
			System.out.println("Yığından al " + disk.diskSayisi + " " + disk.kaynak + " " + disk.yardimci + " " + disk.hedef + " ");
			if (disk.diskSayisi == 1) {
				System.out.println("Diski " + disk.kaynak + " çubuğundan " + disk.hedef + " çubuğuna taşı.");
			} else {
				// Yardımcı çubuğu kullanarak diskleri geçici olarak taşı
				yigin.push(new Disk(disk.diskSayisi - 1, disk.yardimci, disk.kaynak, disk.hedef));
				System.out.println("Yığına ekle " + (disk.diskSayisi - 1) + " " + disk.yardimci + " " + disk.kaynak + " " + disk.hedef + " ");
				yigin.push(new Disk(1, disk.kaynak, disk.yardimci, disk.hedef));
				System.out.println("Yığına ekle " + (1) + " " + disk.kaynak + " " + disk.yardimci + " " + disk.hedef + " ");
				yigin.push(new Disk(disk.diskSayisi - 1, disk.kaynak, disk.hedef, disk.yardimci));
				System.out.println("Yığına ekle " + (disk.diskSayisi - 1) + " " + disk.kaynak + " " + disk.hedef + " " + disk.yardimci + " ");
			}
		}
	}

	public static void main(String[] args) {
		int diskSayisi = 3;
		hanoi(diskSayisi);
	}
}
