import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Ornek5 {
	
	int fonksiyon(ListIterator<String> it)
	{
		if(it.hasNext()) {
			it.next();
			return 1 + fonksiyon(it);
		} 
		return 0;
	}

	public static void main(String args[]) {
		List<String> list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		ListIterator<String> it = list.listIterator();
		System.out.println((new Ornek5()).fonksiyon(it));
	}
}