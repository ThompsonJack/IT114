import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class IT114Homework917 {
	public static void main(String[] args) {
		//1.
		List<String> newList = Arrays.asList("Eel", "Cat", "Dog", "Fish", "Lizard", "Ant");
		System.out.println(newList);
		//2. 
		Collections.reverse(newList);
		System.out.println(newList);
		//3. 
		Collections.shuffle(newList);
		System.out.println(newList);
		
		//4. 
		int total = 0;
		int count = 0;
		List<Integer> NumList = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10);
		for (int i=0; i < NumList.size();i++ ) {
			count = total;
			total = count + NumList.get(i);
		}
		System.out.println(total);
		
		HashMap<Integer, String> OddEven = new HashMap<Integer,String>();
		for (int i=0; i < NumList.size();i++ ) {
			count = NumList.get(i);
			if (count % 2 == 0) {
				OddEven.put(NumList.get(i), "Even");
			}
			else {
				OddEven.put(NumList.get(i), "Odd");
			}
		}
		System.out.println(OddEven);
		
		
		//5.
		Random randomele = new Random(); 
		int randInt = randomele.nextInt(newList.size());
		
		for (int r = 0; r < newList.size(); r++) {
			Collections.swap(newList, r, randInt);
		}
		
		System.out.println(newList);
	}
}



