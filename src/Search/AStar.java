package Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AStar extends AbstractSearch{

	public static void main(String[] args) {
        int[][] graph = new int[][] {
        	{1,1,1,1,0,1,1,1},
        	{1,1,0,1,0,1,0,1},
        	{1,1,0,1,1,1,0,1},
        	{1,1,0,1,0,0,0,1},
        	{1,1,0,1,0,1,0,1},
        	{1,1,0,1,1,1,0,1},
        	{1,1,0,1,0,1,0,1},
        	{1,1,1,1,0,1,1,1},
        };
        PriorityQueue<Node> q = new PriorityQueue<>();
        
        Node start = new Node(0,0);
        Node goal = new Node(7,7);
        AStar aStar = new AStar(start, goal, graph);
        
        Node n = aStar.getGrid()[2][3];
        n.setDisFromGoal(n.distanceFrom(goal));
        n.setDisFromStart(n.distanceFrom(start));
        n.setCost();
        
        Node n2 = aStar.getGrid()[3][3];  
        n2.setDisFromGoal(n2.distanceFrom(goal));
        n2.setDisFromStart(n2.distanceFrom(start));
        n2.setCost();
        
        System.out.println("n cost: " + n.getCost());
        System.out.println("n2 cost: " + n2.getCost());
        System.out.println(Double.compare(n.getCost(), n2.getCost()));
        
//        System.out.println(n.distanceFrom(start));
//        System.out.println(n.distanceFrom(goal));
//        System.out.println(n2.distanceFrom(start));
//        System.out.println(n2.distanceFrom(goal));
//        System.out.println(n.getCost());
//        System.out.println(n2.getCost());
//        System.out.println(n2.compareTo(n));
//      
        aStar.setNodeChildren(n);
        for(Node child : n.getChildren())
        {
        	if(child != null)
        	{
        		q.add(child);
            	child.setDisFromGoal(child.distanceFrom(goal));
            	child.setDisFromStart(child.distanceFrom(start));
            	child.setCost();
        		System.out.println(child);
        	}
        }
        System.out.println();
        while(!q.isEmpty())
        {
        	Node re = q.remove();
        	System.out.println(re + " " + re.getCost());
        }
	}
	
	public PriorityQueue<Node> getQueue() {
		return queue;
	}

	public void setQueue(PriorityQueue<Node> queue) {
		this.queue = queue;
	}

	public ArrayList<Node> getExplored() {
		return explored;
	}

	public void setExplored(ArrayList<Node> explored) {
		this.explored = explored;
	}

	public Node[][] getGrid() {
		return grid;
	}

	public void setGrid(Node[][] grid) {
		this.grid = grid;
	}

	PriorityQueue<Node> queue = new PriorityQueue<>();
	ArrayList<Node> explored = new ArrayList<>();
	Node[][] grid; //flipped x and y coordinate
	
	public AStar(Node startNode, Node goalNode, int[][] map) {
		super(startNode, goalNode);
		grid = new Node[map.length][map.length];
		
		//sets up start and goal nodes
		grid[startNode.getPosX()][startNode.getPosY()] = startNode;
		grid[goalNode.getPosX()][goalNode.getPosY()] = goalNode;
		startNode.setDisFromStart(startNode.distanceFrom(startNode));
		startNode.setDisFromGoal(startNode.distanceFrom(goalNode));
		startNode.setCost();
//		goalNode.setDisFromStart(goalNode.distanceFrom(startNode));
//		goalNode.setDisFromGoal(goalNode.distanceFrom(goalNode));
//		goalNode.setCost();
		
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
				}
			}
		}
		
		//Setting children
//		for(int i = 1 ; i < grid.length - 1 ; i++) //excludes first and last row
//		{
//			for(int j = 0 ; j < map[i].length - 1 ; j ++)
//			{
//				if(map[i][j] == 1) //populates the grid with nodes if the map is indicated with a 1 
//				{
//					if(map[i][j+1] == 1) //horizontal
//					{
//						grid[i][j].addChild(grid[i][j+1]);
//						grid[i][j+1].addChild(grid[i][j]);
//					}
//					if(map[i+1][j] == 1) //verticle
//					{
//						grid[i][j].addChild(grid[i+1][j]);
//						grid[i+1][j].addChild(grid[i][j]);
//					}
//				}
//			}
//			if(map[i+1][map[i].length-1] == 1) //verticle
//			{
//				grid[i][map[i].length-1].addChild(grid[i+1][map[i].length-1]);
//				grid[i+1][map[i].length-1].addChild(grid[i][map[i].length-1]);
//			}
//		}
//		for(int i = 0 ; i < map[0].length - 1 ; i++) //first row
//		{
//			if(map[0][i] == 1 && map[0][i+1] == 1) //horizontal
//			{
//				grid[0][i].addChild(grid[i][i+1]);
//				grid[0][i+1].addChild(grid[i][i]);
//			}
//		}
//		for(int i = 0 ; i < map[map.length-1].length - 1 ; i++) //last row
//		{
//			if(map[0][i] == 1 && map[0][i+1] == 1) //horizontal
//			{
//				grid[map.length-1][i].addChild(grid[0][i+1]);
//				grid[map.length-1][i+1].addChild(grid[0][i]);
//			}
//		}
		
		//add the first node to the queue
//		setNodeChildren(startNode);
		queue.add(startNode); //will initialize with startNode being added to the queue
		startNode.setParent(null);
		printList(grid);
	}
	
	public boolean search(){
		while(!queue.isEmpty()){
			Node parent = queue.remove(); //takes out the node
			explored.add(parent); //adds the current node to explored
			
			if(parent.equals(goalNode)) //if the goal is found
			{
				printPath(goalNode); //displays the path
				System.out.println("AStar Search Path Found!");
				printExplored();
				return true;
			}
			else
			{
				setNodeChildren(parent);
				for(int i = 0 ; i < parent.getChildren().size() ; i++)
				{
					Node child = parent.getChildren().get(i);
					if(child != null)
					{
						if(!queue.contains(child) && !explored.contains(child))
						{
							child.setParent(parent); //sets node's parent
							child.setDisFromStart(child.distanceFrom(startNode));
							child.setDisFromGoal(child.distanceFrom(goalNode));
							child.setCost();
							queue.add(child); //adds to queue
						}
					}
				}
			}
		}
		System.out.println("Path not found");
		return false; 
	}

	private void printPath(Node goal) {
		int count = 0;
		while(goal.getParent() != null)
		{
			count++;
			System.out.print(goal + " <--- ");
			goal = goal.getParent();
		}
		System.out.print(goal);
		System.out.println();
		System.out.println("Total of " + count + " Nodes");
	}
	
	private void setNodeChildren(Node n) //probably made this more complicated than it has to
	{
		int x = n.getPosX(), y=n.getPosY();
		//edges and their horizontals
		if(n.getPosX() == 0) //Deals with (0,x)
		{
			n.addChild(grid[x + 1][y]); //if(0,0)
			if(n.getPosY() == 0)
			{
				n.addChild(grid[x][y+1]);
			}
			else if(n.getPosY() == grid[x].length-1) //if(0,last)
			{
				n.addChild(grid[x][y - 1]);
			}
			else
			{
				n.addChild(grid[x][y - 1]);
				n.addChild(grid[x][y+1]);
			}
		}
		else if(n.getPosX() == grid.length - 1) //Node [last,x]
		{
			n.addChild(grid[x - 1][y]);
			if(n.getPosY() == 0)
			{
				n.addChild(grid[x][y + 1]);
			}
			else if(n.getPosY() == grid[x].length-1) //if(0,last)
			{
				n.addChild(grid[x][y - 1]);
			}
		}
		else if(n.getPosY() == 0)
		{
			n.addChild(grid[x][y + 1]);
			n.addChild(grid[x - 1][y]);
			n.addChild(grid[x + 1][y]);
		}
		else if(n.getPosY() == grid.length - 1)
		{
			n.addChild(grid[x][y - 1]);
			n.addChild(grid[x - 1][y]);
			n.addChild(grid[x + 1][y]);
		}
		else
		{
			n.addChild(grid[x][y + 1]);
			n.addChild(grid[x][y - 1]);
			n.addChild(grid[x - 1][y]);
			n.addChild(grid[x + 1][y]);
		}
	}
	
	private void printExplored()
	{
		for(Node n : explored)
		{
			System.out.println(n);
		}
	}
	
	private void printList(Node[][] arr)
	{
		for(Node[] a : arr)
		{
			for(Node n : a)
			{
				if(n != null)
				{
					System.out.print("1 ");
				}
				else
					System.out.print("0 ");
			}
			System.out.println();
		}
	}
}
