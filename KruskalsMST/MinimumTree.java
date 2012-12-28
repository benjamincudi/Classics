package progAssn1;
import java.util.Random;
import java.util.Scanner;

/**
 * Programming assignment 1 for CSCI E-124.
 * Finding the average weight of a complete graph
 * in various dimensions for number of nodes.
 * Guaranteed to work for up to 65356 nodes at minimum.
 * 
 * @author BenGarfield
 */
public class MinimumTree {

	/**
	 * @param args - 4 integers, separated by spaces
	 * 			in the order: debugFlag, numNodes,
	 * 			numTrials, numDimension
	 */
	public static void main(String[] args) {
		Scanner keyInput = new Scanner(System.in);
		System.out.print("Enter number of nodes: ");
//		int nodeCount = Integer.parseInt(args[1]);
		int nodeCount = keyInput.nextInt();
		System.out.print("Enter the number of trials: ");
//		int trials = Integer.parseInt(args[2]);
		int trials = keyInput.nextInt();
		System.out.print("Enter the dimension (0, 2, 3, or 4): ");
//		int dimension = Integer.parseInt(args[3]);
		int dimension = keyInput.nextInt();
		//RNG seeded by system time
		Random coord = new Random(System.currentTimeMillis());
		Node [] nodeList = new Node[nodeCount];
		//Preliminary array for holding edges, close to maximum size
		Edge [] edgePrelim = new Edge[6000000];
		double averageWeight = 0;
		
		//Repeat the following until we have
		//performed the requested number of trials
		for (int j = 0; j < trials; j++) {
			//For 0-dimension, node coordinates are unnecessary
			if (dimension == 0) {
				for (int i = 0; i < nodeCount; i++) {
					nodeList[i] = new Node();
				}
			}
			//For 2-dimension and above, we need to
			//create our nodes at random points.
			else if (dimension == 2) {
				for (int i = 0; i < nodeCount; i++) {
					nodeList[i] = new Node(coord.nextDouble(), coord.nextDouble());
				}
			}
			else if (dimension == 3) {
				for (int i = 0; i < nodeCount; i++) {
					nodeList[i] = new Node(coord.nextDouble(), 
							coord.nextDouble(), coord.nextDouble());
				}
			}
			else {
				for (int i = 0; i < nodeCount; i++) {
					nodeList[i] = new Node(coord.nextDouble(), 
							coord.nextDouble(), coord.nextDouble(),
							coord.nextDouble());
				}
			}
			//Next, we'll create or calculate all our
			//edges. For large amounts of nodes, we can
			//throw out edges larger than certain sizes
			int e = 0;
			//If we actually use the distance for weight:
			if (dimension != 0) {
				for (int i = 0; i < nodeCount; i++) {
					for (int k = i + 1; k < nodeCount; k++) {
						edgePrelim[e] = new Edge(nodeList[i], nodeList[k]);
						if ((dimension == 4) && 
								(((nodeCount > 16384) && (edgePrelim[e].getWeight() < 0.12)) || 
								((nodeCount <= 16384) && (edgePrelim[e].getWeight() < 0.15)) || 
								((nodeCount <= 8192) && (edgePrelim[e].getWeight() < 0.22)) ||
								(nodeCount <= 2048))) {
							e++; //If we use the edge, keep track of how many we add!
						}
						if ((dimension == 3) && 
								(((nodeCount > 16384) && (edgePrelim[e].getWeight() < 0.07)) || 
								((nodeCount <= 16384) && (edgePrelim[e].getWeight() < 0.09)) || 
								((nodeCount <= 8192) && (edgePrelim[e].getWeight() < 0.172)) ||
								(nodeCount <= 1024))) {	
							e++; //If we use the edge, keep track of how many we add!
						}
						if ((dimension == 2) && 
								(((nodeCount > 16384) && (edgePrelim[e].getWeight() < 0.02)) || 
								((nodeCount <= 16384) && (edgePrelim[e].getWeight() < 0.03)) || 
								((nodeCount <= 8192) && (edgePrelim[e].getWeight() < 0.07)) ||
								(nodeCount <= 1024))) {
							e++; //If we use the edge, keep track of how many we add!
						}
					}
				}
			}
			//Otherwise, we're using random weights
			else {
				double weightCheck;
				for (int i = 0; i < nodeCount; i++) {
					for (int k = i + 1; k < nodeCount; k++) {
						//Before creating the edge, check its weight
						weightCheck = coord.nextDouble();
						if (((nodeCount > 16384) && (weightCheck < 0.001)) || 
								((nodeCount <= 16384) && (weightCheck < 0.015)) ||
								((nodeCount <= 8192) && (weightCheck < .15)) ||
								(nodeCount <= 1024)) {
							edgePrelim[e] = new Edge(nodeList[i], nodeList[k], weightCheck);
							e++; //If we use the edge, keep track of how many we add!
						}
					}
				}
			}
			
			//Now that we have all our edges, we can
			//shrink our array to only be as big as we need
			Edge [] edgeList = new Edge [e];
			System.arraycopy(edgePrelim, 0, edgeList, 0, e);
			//Sort our list of edges according to weight
			edgeList = SortEdges.sorting(edgeList);
			//Find the weight of the MST, and add it to our total
			averageWeight += Kruskal.kruskal(nodeCount, edgeList);
		}
		//Requested output as per the assignment
		System.out.println((averageWeight / trials) + " " + nodeCount + " " + trials + " " + dimension);
	}

}
