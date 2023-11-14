import java.util.ArrayList;
import java.util.List;

public class Ornek2{
	public static void main(String[] argv){
		int[] array = {6,9,8}; 
		List<Integer> list = new ArrayList<>(); 
		list.add(array[0]); 
		list.add(array[2]); 
		System.out.println(list); 
		list.set(1, array[1]); 
		list.remove(0); 
		System.out.println(list); 
	}
}