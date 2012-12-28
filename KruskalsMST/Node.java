package progAssn1;
/**
 * The Node class as was necessary for the
 * assignment. Generalized class for n-space
 * could be handled by passing an array for 
 * coordinates, with length = [dimension].
 * 
 * The functions for returning coordinate
 * values would have to be modified. A single
 * function, requiring an integer for index,
 * would solve this.
 * 
 * @author BenGarfield
 */
public class Node {
	//These four are private to ensure no
	//accidental unwanted changes occur.
	private double xCoord;
	private double yCoord;
	private double zCoord;
	private double wCoord;
	public Node parent; //Who is the parent of this node?
	public int children = 0; //How many nodes are connected?
	
	/**
	 * Default constructor
	 */
	public Node() {
		this(0,0,0,0);
	}
	
	/**
	 * Constructor for a 4-space Node. For
	 * nodes in 3- or 2- space, the respecively
	 * unused coordinates are set to 0.
	 * 
	 * @param x - x coordinate of node
	 * @param y - y coordinate of node
	 * @param z - z coordinate of node
	 * @param w - w coordinate of node
	 */
	public Node(double x, double y, double z, double w) {
		xCoord = x;
		yCoord = y;
		zCoord = z;
		wCoord = w;
		parent = this;
	}
	//3-space constructor
	public Node(double x, double y, double z) {
		xCoord = x;
		yCoord = y;
		zCoord = z;
		wCoord = 0;
		parent = this;
	}
	
	//2-space constructor
	public Node(double x, double y) {
		xCoord = x;
		yCoord = y;
		zCoord = 0;
		wCoord = 0;
		parent = this;
	}
	
	/**
	 * Methods for retrieving the various coordinates.
	 * 
	 * @return A double representing the value of the
	 * desired coordinate.
	 */
	public double getX(){
		return xCoord;
	}
	public double getY(){
		return yCoord;
	}
	public double getZ(){
		return zCoord;
	}
	public double getW(){
		return wCoord;
	}
	
	/**
	 * Method for locating the head of a tree of
	 * connected nodes. Sets the parent of all
	 * nodes between initial and head to the head
	 * while performing this search.
	 */
	public void findHead() {
		//If my parent's parent is my parent, then 
		//I am pointing to the head already.
		if (parent.parent.equals(parent)) {
			return;
		}
		//Otherwise, I'm not the head
		else {
			//Search recursively to find the head
			parent.findHead();
			//My parent is my parent's parent, which
			//is the head of the tree after recursion.
			parent = parent.parent;
			return;
		}
	}
}
