/**
 * 
 */
package unsw.comp9024.FourPuzzel;

/**
 * @author alex
 *
 */
public class DigitNode implements ExpressionTree {

	int value;
	int order;
	
	public DigitNode(int value, int order) {
		this.value = value;
		this.order = order;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getOrder() {
		return order;
	}	
	
	public String toString() {
		return Integer.toString(value);
	}

	@Override
	public void insert(ExpressionTree n) {
		// Digit has no children!
		System.out.println("Digit has no children!");
	}
}
