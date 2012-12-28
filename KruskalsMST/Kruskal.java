package progAssn1;

/**
 * My implementation of Kruskal's algorithm.
 * I chose this algorithm because it strangely
 * excited me, and it made the most sense to me
 * in terms of the logic behind which it worked.
 * 
 * @author BenGarfield
 */
public class Kruskal {
	/**
	 * Kruskal's Algorithm for finding an MST.
	 * A list of edges used is not kept, as it
	 * was not required for this assignment.
	 * 
	 * @param nodes - Number of nodes in the graph
	 * @param edges - Array of edges to be used
	 * @return Weight of the MST found
	 */
	public static double kruskal(int nodes, Edge [] edges) {
		double treeWeight = 0; //This is what will be returned
		int edgesChosen = 0; //How many connections we have made
		int j = 0;
		//Two pointers for currently interesting nodes
		Node left; 
		Node right;
		//While we haven't made enough connections, AND
		//We haven't checked all available edges
		while ((edgesChosen < (nodes - 1)) && (j < edges.length)) {
			//Examine both endpoints of our current edge
			left = edges[j].getLeft();
			right = edges[j].getRight();
			//If an endpoint isn't the head of its tree
			if (!(left.parent.equals(left))) {
				//Find the head of that tree
				left.findHead();
				//We're only interested in the head
				left = left.parent;
			}
			if ((!right.parent.equals(right))) {
				//Find the head of that tree
				right.findHead();
				//We're only interested in the head
				right = right.parent;
			}
			//If the two nodes aren't from the same tree
			if  (!(left.equals(right))){
				//Then we can join these two trees
				//according to which has more children
				if (left.children >= right.children) {
					right.parent = left;
					left.children += (right.children + 1);
				}
				else {
					left.parent = right;
					right.children += (left.children + 1);
				}
				//Add the weight of the used edge
				treeWeight += edges[j].getWeight();
				edgesChosen++; //Take note of the connection we made
			}
			j++; //Look at the next edge
		}
		//The line below helped me determine bounds for edge weight
		//System.out.println("Connections made: " + edgesChosen + " Max edge weight: " + edges[j-1].getWeight());
		return treeWeight;
	}
}