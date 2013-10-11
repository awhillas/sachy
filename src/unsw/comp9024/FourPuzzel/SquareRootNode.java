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
	
	public int getValue() {
		return (int) Math.sqrt(this.value);
	}
	
	public String toString() {
		return Character.toString((char) 251) + this.value;
	}
}
