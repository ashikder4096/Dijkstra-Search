package Search;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The Node class represents a station
 * in this tutorial and will as such have
 * a string representing the station's name.
 * As well as an ArrayList of nodes that will store
 * any instantiated nodes children.
 */
public class Node implements Comparable{

    //    A Unique Identifier for our node
    public String cities;
    //    An arraylist containing a list of Nodes that
    //    This node is directly connected to - It's child nodes.
    private ArrayList<Node> children = new ArrayList<>(); 
    private ArrayList<Integer> childrenCost = new ArrayList<>(); //acts as the row for the adjMatrix
    private int minDistance = Integer.MAX_VALUE;
    private int posX, posY;
    private double disFromGoal, disFromStart, cost;
    private Node parent;

    public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public double getDisFromGoal() {
		return disFromGoal;
	}

	public void setDisFromGoal(double disFromGoal) {
		this.disFromGoal = disFromGoal;
	}

	public double getDisFromStart() {
		return disFromStart;
	}

	public void setDisFromStart(double disFromStart) {
		this.disFromStart = disFromStart;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node(String stationName, ArrayList<Node> children){
        this.cities = stationName;
        this.children = children;
    }
	
//	public Node(String stationName, ArrayList<Node> children, int index){
//        this.cities = stationName;
//        this.children = children;
//        this.index = index;
//    }
    
    public void addChild(Node child, int cost) //From the parents node, takes the child node nd it's cost
    {
    	children.add(child);
    	childrenCost.add(cost);
    }

    public void setCities(String cities) {
		this.cities = cities;
	}

	public void setChildren(ArrayList<Node> children, ArrayList<Integer> cost) {
		this.children = children;
		setChildrenCost(cost);
	}

	public ArrayList<Integer> getChildrenCost() {
		return childrenCost;
	}

	public void setChildrenCost(ArrayList<Integer> childrenCost) {
		this.childrenCost = childrenCost;
	}

	public ArrayList<Node> getChildren(){
    	return children;
    }

    //    An auxiliary function which allows
//    us to remove any child nodes from
//    our list of child nodes.
    public void removeChild(Node n){
        children.remove(n);
    }
    
    public String toString()
    {
    	return cities;
    }
    
    @Override
    /**
     * 
     * @param arg0 compared node
     * @return positive if this is greater, negative is this is less, 0 if they are equal
     */
	public int compareTo(Object arg0) {
		return (int) (this.cost - ((Node) arg0).getCost());
	}

	private double getCost() {
		// TODO Auto-generated method stub
		return cost;
	}
	
	private void setCost(double cost) {
		// TODO Auto-generated method stub
		this.cost = cost;
	}
	
	private double distanceFrom (Node n)
	{
		return Math.sqrt((posX - n.getPosX())*(posX - n.getPosX()) + (posY - n.getPosY())*(posY - n.getPosY()));
	}

	
//	public int compareTo(Object o) {
//		// TODO Auto-generated method stub
//		Node = (Node)o;
//	}

}