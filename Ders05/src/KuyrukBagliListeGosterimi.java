
public class KuyrukBagliListeGosterimi {
	
	public static void main(String[] args) {
		
		BagliListeKuyruk<String> kuyruk = new BagliListeKuyruk<>();

		// Kuyruğa (queue) öğeleri ekleyelim (enqueue)
		kuyruk.ekle("Öğe 1");
		kuyruk.ekle("Öğe 2");
		kuyruk.ekle("Öğe 3");
		kuyruk.ekle("Öğe 4");

		// Kuyruktaki öğeleri gösterelim
		kuyruk.kuyruğuGöster();

		// Kuyruktan (queue) öğeleri çıkaralım (dequeue)
		String çıkarılanÖğe = kuyruk.çıkar();
		System.out.println("Çıkarılan Öğe: " + çıkarılanÖğe);

		// Kuyruktaki güncel öğeleri gösterelim
		kuyruk.kuyruğuGöster();

		// Kuyruğun önündeki öğeye bakalım (peek) ve çıkartmadan
		String önÖğe = kuyruk.önüneBak();
		System.out.println("Ön Öğe: " + önÖğe);

		// Kuyruğun boş olup olmadığını kontrol edelim
		boolean boşMu = kuyruk.boşMu();
		System.out.println("Kuyruk Boş mu? " + boşMu);

		// Kuyruğun boyutunu alalım
		int boyut = kuyruk.boyut();
		System.out.println("Kuyruk Boyutu: " + boyut);
	}
}

class BagliListeKuyruk<E> {
	private Node<E> baş;
	private Node<E> son;
	private int boyut;

	private static class Node<E> {
		E veri;
		Node<E> sonraki;

		Node(E veri) {
			this.veri = veri;
			this.sonraki = null;
		}
	}

	public void ekle(E öğe) {
		Node<E> yeniNode = new Node<>(öğe);
		if (boşMu()) {
			baş = yeniNode;
			son = yeniNode;
		} else {
			son.sonraki = yeniNode;
			son = yeniNode;
		}
		boyut++;
	}

	public E çıkar() {
		if (boşMu()) {
			throw new IllegalStateException("Kuyruk boş");
		}
		E veri = baş.veri;
		baş = baş.sonraki;
		boyut--;
		return veri;
	}

	public E önüneBak() {
		if (boşMu()) {
			throw new IllegalStateException("Kuyruk boş");
		}
		return baş.veri;
	}

	public boolean boşMu() {
		return boyut == 0;
	}

	public int boyut() {
		return boyut;
	}

	public void kuyruğuGöster() {
		Node<E> şuanki = baş;
		System.out.print("Kuyruk Öğeleri: ");
		while (şuanki != null) {
			System.out.print(şuanki.veri + " ");
			şuanki = şuanki.sonraki;
		}
		System.out.println();
	}
}
