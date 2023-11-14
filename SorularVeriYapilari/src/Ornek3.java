import java.util.ArrayList;

public class Ornek3{
	public static void main(String[] argv){
		ArrayList<String> list = new ArrayList<>(); 
		list.add("dog"); 
		list.add("cat"); 
		list.add("frog"); 
		
		System.out.println(list.contains("cat"));
		//System.out.println(list.hasObject("cat"));
		System.out.println(list.indexOf("cat"));
		System.out.println(list.indexOf(1));
	}
}