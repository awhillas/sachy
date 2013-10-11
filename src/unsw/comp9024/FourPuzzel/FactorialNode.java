/**
 * 
 */
package unsw.comp9024.FourPuzzel;

/**
 * @author alex
 *
 */
public class FactorialNode extends DigitNode {

	public FactorialNode(int value, int order) {
		super(value, order);
	}
	
	public int getValue() {
		return this.factorial(this.value);
	}

	private int factorial(int x) {
		int out = x;
		while(x-- > 0) {
			out *= x;
		}
		return out;
	}
	
	public String toString() {
		return this.value + "!";
	}
}
