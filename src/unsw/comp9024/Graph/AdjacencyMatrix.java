/**
 * 
 */
package unsw.comp9024.Graph;

/**
 * Adjacency Matrix for an undirected graph i.e. matrix is symmetrical.
 * @author arbw870
 *
 */
public class AdjacencyMatrix implements Graph {

	/**
	 * Vertex list
	 */
	private String [] vertices;
	/**
	 * Matrix of Edges. If there is an edge between 2 Vertices then its cost is 
	 * recorded here, null otherwise.
	 */
	private int [][] edges;
	
	public AdjacencyMatrix(String[] nodes, int [][] edges) {
		this.vertices = nodes;
		this.edges = edges;
	}

	@Override
	public int numVertices() {
		return vertices.length;
	}

	@Override
	public int numEdges() {
		int count = 0;
		for (int i = 0; i < edges.length; i++) {
			// undirected graph so mirrored about the diagonal...
			for (int j = i; j < edges[i].length; j++) {	
				if(edges[i][j] != 0) {
					count++;
				}
			}
		}
		return count;
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Testing
		// For a visual see: http://courses.cs.vt.edu/csonline/DataStructures/Lessons/Graphs/graph.gif
		String[] nodes = {"A", "B", "C", "D", "E", "F"};
		// this should be symmetrical about the diagonal
	    int [][] matrix= {  {0, 1, 1, 1, 0, 0}, 
							{1, 0, 1, 0, 1, 0}, 
							{1, 1, 0, 0, 0, 0}, 
							{1, 0, 0, 0, 1, 1}, 
							{0, 1, 0, 1, 0, 0}, 
							{0, 0, 0, 1, 0, 0}};
	    
	    AdjacencyMatrix graph = new AdjacencyMatrix(nodes, matrix);
	}
}
