/**
 * 
 */
package unsw.comp9024.FourPuzzel;

/**
 * @author alex
 *
 */
public class SquareRootNode extends DigitNode {

	/**
	 * @param value
	 * @param order
	 */
	public SquareRootNode(int value, int order) {
		super(value, order);
	}
	
	public double getValue() {
		return (double) Math.sqrt(this.value);
	}
	
	public String toString() {
		//return Character.toString((char) 251) + this.value;
		return "\u221A" + (int) this.value;
	}
}
