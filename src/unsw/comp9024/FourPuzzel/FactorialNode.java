/**
 * 
 */
package unsw.comp9024.FourPuzzel;

/**
 * @author alex
 *
 */
public class FactorialNode extends DigitNode {

	public FactorialNode(double value, int order) {
		super(value, order);
	}
	
	public double getValue() {
		return this.factorial(this.value);
	}

	private double factorial(double value) {
		return value == 1 ? 1 : value * factorial (value - 1);
	}
	
	public String toString() {
		return (int) this.value + "!";
	}
}
