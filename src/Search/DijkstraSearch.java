package Search;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class DijkstraSearch extends AbstractSearch{


	
	public DijkstraSearch(Node startNode, Node goalNode) {
		super(startNode, goalNode);
		// TODO Auto-generated constructor stub
	}

	public boolean search(){
			if(startNode.equals(goalNode)){
			System.out.println("Goal node found");
			System.out.println(startNode);
		}
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		ArrayList<ArrayList<Integer>> aMatrix = new ArrayList<>();
		
		queue.add(startNode);
		
		while(!queue.isEmpty()){
			
		}
		return false; 
	}
	
}
