/**
 * 
 */
package unsw.comp9024.FourPuzzel;

/**
 * Interface for ExpressionTree nodes.
 * 
 * @author Alexander Whillas <z3446737@student.unsw.edu.au>
 */
public interface ExpressionTree {
	public int getOrder();
	public double getValue();
	public void insert(ExpressionTree n);
}
