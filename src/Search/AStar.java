package Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AStar extends AbstractSearch{

	PriorityQueue<Node> queue = new PriorityQueue<>();
	ArrayList<Node> explored = new ArrayList<>();
	Node[][] grid;
	
	public AStar(Node startNode, Node goalNode, int[][] map) {
		super(startNode, goalNode);
		grid = new Node[map.length][map.length];
		
		//sets up start and goal nodes
		grid[startNode.getPosX()][startNode.getPosY()] = startNode;
		grid[goalNode.getPosX()][goalNode.getPosY()] = goalNode;
		startNode.setDisFromStart(startNode.distanceFrom(startNode));
		startNode.setDisFromGoal(startNode.distanceFrom(goalNode));
		startNode.setCost();
		goalNode.setDisFromStart(goalNode.distanceFrom(startNode));
		goalNode.setDisFromGoal(goalNode.distanceFrom(goalNode));
		goalNode.setCost();
		
		/**
		 * Creating basic map structure
		 */
		//populating grid
		for(int i = 0 ; i < map.length ; i++)
		{
			for(int j = 0 ; j < map[i].length ; j ++)
			{
				if(grid[i][j] !=startNode && grid[i][j] != goalNode && map[i][j] == 1) //populates the grid with nodes if the map is indicated with a 1 
				{
					grid[i][j] = new Node(i,j);
					grid[i][j].setDisFromStart(startNode.distanceFrom(startNode));
					grid[i][j].setDisFromGoal(startNode.distanceFrom(goalNode));
					grid[i][j].setCost();					
				}
			}
		}
		
		//Setting children
		for(int i = 1 ; i < grid.length - 1 ; i++) //excludes first and last row
		{
			for(int j = 0 ; j < map[i].length - 1 ; j ++)
			{
				if(map[i][j] == 1) //populates the grid with nodes if the map is indicated with a 1 
				{
					if(map[i][j+1] == 1) //horizontal
					{
						grid[i][j].addChild(grid[i][j+1]);
						grid[i][j+1].addChild(grid[i][j]);
					}
					if(map[i+1][j] == 1) //verticle
					{
						grid[i][j].addChild(grid[i+1][j]);
						grid[i+1][j].addChild(grid[i][j]);
					}
				}
			}
		}
		for(int i = 0 ; i < map[0].length - 1 ; i++) //first row
		{
			grid[0][i].addChild(grid[0][i+1]);
			grid[0][i+1].addChild(grid[0][i]);
		}
		for(int i = 0 ; i < map[map.length-1].length - 1 ; i++) //last row
		{
			grid[map.length-1][i].addChild(grid[0][i+1]);
			grid[map.length-1][i+1].addChild(grid[0][i]);
		}
		
		//add the first node to the queue
		queue.add(startNode); //will initialize with startNode being added to the queue
		startNode.setParent(null);
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
