import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueHW919 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Queue<Test> q = new LinkedList<Test>();
		List<String> t = new ArrayList<String>();
		System.out.println("1. Add: Items are added to queue");
		t.add("a");
		t.add("b");
		t.add("c");
		t.add("d");
		for (int i=0; i <10; i++) {
			q.add(new Test(i, ""));
		}
		
		System.out.println("Queue items: " +q);
		System.out.println();
		
		//System.out.println(q);
		//System.out.println();
		
		int size = q.size();
		System.out.println("2. Size: Shows size of queue\n" + "Size of queue: " +size);
		System.out.println();
		
		
		Test peek = q.peek();
		System.out.println("3. Peek: Shows first item\n" + "First in queue: " +peek);
		
		System.out.println();
		
		Test remove  = q.remove();
		System.out.println("4. Remove: Removes front item from queue\n" + "Item removed from queue: " +remove);
		
		System.out.println("\nUpdated queue: " +q);

		System.out.println();
		Test poll = q.poll();
		System.out.println("Updated head of queue:"+ poll);
		
	}
}

class Test{
	public int n;
	public String value;
	public Test(int i, String v) {
		this.n =i;
		this.value =v;
		
	}
	@Override
	public String toString () {
		return "{'key':'" + this.n + "', 'value':'"+ this.value + "'}";
	}
}
