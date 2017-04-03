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
		startNode.setDisFromStart(0);
		queue.add(startNode); //will initialize with startNode being added to the queue
		startNode.setParent(null);
		// TODO Auto-generated constructor stub
	}

	public boolean search(){
		while(!queue.isEmpty()){
			Node parent = queue.remove(); //takes out the node
			explored.add(parent); //adds the current node to explored
			
			if(parent.equals(goalNode)) //if the goal is found
			{
				printPath(goalNode); //displays the path
				System.out.println("Dijkstra Search Path Found!");
				return true;
			}
			else
			{
				for(int i = 0 ; i < parent.getChildren().size() ; i++)
				{
					if(!queue.contains(parent.getChildren().get(i)) && !explored.contains(parent.getChildren().get(i)))
					{
						parent.getChildren().get(i).setParent(parent); //sets node's parent
						parent.getChildren().get(i).setDisFromStart(parent.getDisFromStart() + parent.getChildrenCost().get(i)); //sets the distance
						queue.add(parent.getChildren().get(i)); //adds to queue
					}
				}
			}
		}
		System.out.println("Path not found");
		return false; 
	}

	private void printPath(Node goal) {
		while(goal.getParent() != null)
		{
			System.out.print(goal + " <--- ");
			goal = goal.getParent();
		}
		System.out.print(goal);
		System.out.println();
	}
	
}
