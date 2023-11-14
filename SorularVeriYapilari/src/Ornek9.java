
public class Ornek9 {

	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	public static int fonksiyon(Node head, Node curr) {
		
		if (head == null)
			return 1;
		
		if(curr == null)
			return 0;
		
		if(head == curr)
			return 1;
		
		return fonksiyon(head, curr.next);
	}

	public static void main(String[] args) {

		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = head;
		System.out.println(fonksiyon(head, head.next));
	}
}
