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
    private int minDistance = Integer.MAX_VALUE, index = -1;
    private Node parent;

    public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public int getIndex()
	{
		return index;
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
    
    public void addChild(Node child)
    {
    	children.add(child);
    }

    public void setCities(String cities) {
		this.cities = cities;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
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
		return this.getMinDistance() - ((Node) arg0).getMinDistance();
	}

	
//	public int compareTo(Object o) {
//		// TODO Auto-generated method stub
//		Node = (Node)o;
//	}

}