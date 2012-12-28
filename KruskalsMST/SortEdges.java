package progAssn1;

/**
 * A merge sort routine for sorting the edges.
 * This is necessary for Kruskal's algorithm.
 * Only simple adaption were needed to modify
 * this algorithm from numbers to edges.
 * 
 * @author BenGarfield
 */
public class SortEdges {
	public static Edge [] sorting(Edge [] input) {
		int n = input.length; //How long is the array?
		if (n==1) { //If there is only one element
			return input; //There is no sorting to do
		}
		if (n==2) { //If there are only two elements,
			if (input[1].getWeight() < input[0].getWeight()) { //Check them
				Edge key = input[1]; //and order them
				input[1] = input[0]; //if necessary.
				input[0] = key;
			}
		}
		/**
		 * If there are more than two elements, then
		 * merge sort will split the array into two
		 * parts, and call merge sort on each array.
		 * With the returned ordered arrays, it will
		 * order the original array, and return a final
		 * ordered array of all elements.
		 */
		else {
			int r; //This is used to copy array elements
			int i = (int) Math.floor(n/2); //Size of left array
			int k = n - i; //Size of right array
			//These two arrays are used for recursive
			//merge sort calling
			Edge [] left = new Edge [i]; 
			Edge [] right = new Edge [k];
			//Copy the first i elements to the left array
			for (r = 0; r < i; r++) {
				left[r] = input[r];
			}
			//Copy the remaining elements to the right array
			for (r = 0; r < k; r++) {
				right[r] = input[r+i];
			}
			//Call merge sort on both arrays
			left = sorting(left);
			right = sorting(right);
			//These are used to progressively go through
			//each returned array while merging.
			int a = 0;
			int b = 0;
			r = 0;
			//Merge the two arrays together in order
			while (r < n) {
				if (a < i && b < k) {
					if (left[a].getWeight() > right[b].getWeight()) {
						input[r] = right[b];
								b++;
					}
					else {
						input[r] = left[a];
						a++;
					}
					
				}
				else if (b < k) {
					while (b < k) {
						input[r] = right[b];
						b++;
						r++;
					}
				}
				else {
					while (a < i) {
						input[r] = left[a];
						a++;
						r++;
					}
				}
				r++;
			}
		}
		
		return input;
	}
}