
public class Session3 {
	public static void main(String[] args) {
		String name = null;
		if("Bob".contentEquals(name)) {
			System.out.print("Bob equals name \n");
		}
		if(name.contentEquals("Bob")) {
			System.out.print("Name equals Bob \n");
		}
		if("Bob"==name) {
			System.out.print("Bob is name \n");
		}
		int count = 0;
		float fcount = 0.0f;
		
		float total = 0;
		
		for (int i = 0; i < 10; i++){
			count++;
			fcount +=0.1f;
			
		}
		
		System.out.print("Count: " + count);
		System.out.print("fcount: " + fcount);
		if (fcount ==1) {
			System.out.print("It's actually 10!");
		}
	}
}
