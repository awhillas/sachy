/**
 * 
 */
package unsw.comp9024.FourPuzzel;

/**
 * @author alex
 *
 */
public class DigitNode implements ExpressionTree {

	double value;
	int order;
	
	public DigitNode(double value, int order) {
		this.value = value;
		this.order = order;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public int getOrder() {
		return order;
	}	
	
	public String toString() {
		return Integer.toString((int) value);
	}

	@Override
	public void insert(ExpressionTree n) {
		// Digit has no children!
		System.out.println("Digit has no children!");
	}
}
