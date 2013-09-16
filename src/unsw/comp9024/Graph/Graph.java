/**
 * 
 */
package unsw.comp9024.Graph;

/**
 * @author arbw870
 *
 */
public interface Graph {
	/**
	 * Calculate the cheapest path between two vertexes.
	 * @param from
	 * @param to
	 * @return
	 */
	public int calculateCheapestPath(String from, String to);
}
