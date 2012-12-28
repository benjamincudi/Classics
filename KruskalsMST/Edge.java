package progAssn1;

/**
 * This is my Edge class, as necessary for the
 * assignment. Capable of handling nodes in up
 * to 4-space. A generalized class for handling
 * up to n-space would only require minor adjustment,
 * holding Node coordinates in arrays, of
 * length [dimension].
 * 
 * @author BenGarfield
 */
public class Edge {
	//The two nodes that make the endpoints for an edge
	private Node leftSide;
	private Node rightSide;
	//The weight of the edge, usually its Euclidean length.
	private double weight;
	
	/**
	 * Constructor for the Edge class. If weight
	 * is unknown, this will calculate weight
	 * automatically based on node coordinates.
	 * 
	 * @param parent1 - The node on one side
	 * @param parent2 - The node at the other end
	 */
	public Edge(Node parent1, Node parent2){
		leftSide = parent1;
		rightSide = parent2;
		weight = edgeWeight(leftSide, rightSide);
	}
	/**
	 * Constructor for the Edge class. This is
	 * used for when weight is known prior
	 * to construction, i.e dimension = 0.
	 * 
	 * @param parent1
	 * @param parent2
	 * @param length
	 */
	public Edge(Node parent1, Node parent2, double length){
		leftSide = parent1;
		rightSide = parent2;
		weight = length;
	}
	
	/**
	 * Calculates the weight of an edge based 
	 * on its endpoints.
	 * 
	 * @param left - First endpoint 
	 * @param right - Second endpoint
	 * @return A double representing the weight
	 */
	public double edgeWeight(Node left, Node right){
		//These four doubles were used simply to make
		//the actual calculation of length more neat.
		double xDiff = left.getX() - right.getX();
		double yDiff = left.getY() - right.getY();
		double zDiff = left.getZ() - right.getZ();
		double wDiff = left.getW() - right.getW();
		//The formula for Euclidean distance between
		//two points in 4-space. For 2- and 3-space,
		//unused coordinates are simply 0.
		double length = Math.sqrt(Math.pow(xDiff, 2) + 
				Math.pow(yDiff, 2) + Math.pow(zDiff, 2) + 
				Math.pow(wDiff, 2));
		return length;
	}
	
	/**
	 * These three functions return information about
	 * the edge, preventing accidental changes to
	 * the critical information about each edge.
	 * 
	 * @return One of the attributes of the edge
	 */
	public double getWeight() {
		return weight;
	}
		public Node getLeft() {
		return leftSide;
	}
		public Node getRight(){
		return rightSide;
	}
}
