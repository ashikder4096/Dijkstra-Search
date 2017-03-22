package Search;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class DijkstraSearch extends AbstractSearch{

	PriorityQueue<Node> queue = new PriorityQueue<>();
	ArrayList<Node> explored = new ArrayList<>();
	
	public DijkstraSearch(Node startNode, Node goalNode) {
		super(startNode, goalNode);
		startNode.setMinDistance(0);
		queue.add(startNode);
		// TODO Auto-generated constructor stub
	}

	public boolean search(){
		while(!queue.isEmpty()){
			Node parent = queue.remove(); //takes out the node
			
			if(parent.equals(goalNode))
			{
				explored.add(parent);
				printExplored();
				System.out.println("Dijkstra Search Path Found!");
				return true;
			}
			else
			{
				explored.add(parent);
				for(int i = 0 ; i < parent.getChildren().size() ; i++)
				{
					if(!queue.contains(parent.getChildren().get(i)) && !explored.contains(parent.getChildren().get(i)))
					{
						parent.getChildren().get(i).setParent(parent);
						parent.getChildren().get(i).setMinDistance(parent.getMinDistance() + parent.getChildrenCost().get(i));
						queue.add(parent.getChildren().get(i));
					}
				}
			}
		}
		System.out.println("Path not found");
		return false; 
	}

	private void printExplored() {
		System.out.print("[");
		for(int i = 0 ; i < explored.size() - 1 ; i++)
		{
			System.out.print(explored.get(i) +  ", ");
		}
		System.out.print(explored.get(explored.size() - 1) + "]");
		System.out.println();
		
	}
	
}
