package Search;
import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends AbstractSearch{
	
	Stack nodeStack = new Stack<>();
	ArrayList<Node> visitedNode = new ArrayList<>();

	public DepthFirstSearch(Node startNode, Node goalNode) {
		super(startNode, goalNode);
		// TODO Auto-generated constructor stub
	}

	public boolean search()
	{
		nodeStack.add(startNode);
		while(!nodeStack.isEmpty())
		{
			Node current = (Node) nodeStack.pop();
			if(current.equals(goalNode))
			{
				visitedNode.add(current);
				System.out.println(visitedNode);
//				System.out.println("Goal node found");
				return true;
			}
			else
			{
				if(!visitedNode.contains(current) && !nodeStack.contains(current))
				{
					visitedNode.add(current);
					nodeStack.addAll(current.getChildren());
				}
			}
		}
		return false;
	}
}
