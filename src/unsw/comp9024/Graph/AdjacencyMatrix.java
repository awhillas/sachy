/**
 * 
 */
package unsw.comp9024.Graph;

import java.util.ArrayList;

/**
 * @author arbw870
 *
 */
public class AdjacencyMatrix implements Graph {

	private ArrayList<ArrayList<Vertex>> matrix;
	
	private ArrayList<String> lookup;
	
	public AdjacencyMatrix(int ... edges) {
		try {
			assert edges.length % 2 == 0: "Expected even number of arguments idiot!";
			for(String e : edges) {
				
			}
		} catch (AssertionError e) {
			
		}
	}
	
	public void add(int vertex) {
		if(matrix.contains(vertex)) {
			
		} else {
			// Not found so create them.
			if (matrix.size() > 0) {
				// Have to clone one of the existing and then add to all of them.
				ArrayList<Vertex> template = (ArrayList<Vertex>) matrix.get(0).clone();
				matrix.;
			} else {
				// Edge case, empty matrix
			}
		}
	}
	
	private boolean isKnownVertex(String v) {
		lookup.contains(o)
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO testing
		
	}

	@Override
	public int calculateCheapestPath(String from, String to) {
		// TODO Auto-generated method stub
		return 0;
	}

}
