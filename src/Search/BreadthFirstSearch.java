package Search;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch extends AbstractSearch{


	
	public BreadthFirstSearch(Node startNode, Node goalNode) {
		super(startNode, goalNode);
		// TODO Auto-generated constructor stub
	}

	public boolean search(){
		if(startNode.equals(goalNode)){
		System.out.println("Goal node found");
		System.out.println(startNode);
		}
		
		Queue<Node> queue = new LinkedList<>();
		ArrayList<Node> explored = new ArrayList<>();
		queue.add(startNode);
		explored.add(startNode);
		
		while(!queue.isEmpty()){
			Node current = queue.remove();
			if(current.equals(goalNode)){
				explored.add(current);
				explored.remove(0);
				System.out.println(explored);
				return true;
			}
			else{
				if(current.getChildren().isEmpty()){
					return false;
				}
				else{
//					queue.addAll(current.getChildren());
					for(Node n : current.getChildren())
					{
						if(!explored.contains(n) && !queue.contains(n))
						{
							queue.add(n);
						}
					}
				}
				explored.add(current);
			}				
		}
		return false; 
	}
	
}
