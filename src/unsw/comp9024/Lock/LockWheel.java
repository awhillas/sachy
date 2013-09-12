/**
 * 
 */
package unsw.comp9024.Lock;

/**
 * @author Alexander Whillas (z3446737) <whillas@gmail.com>
 *
 */
public interface LockWheel {
	
	/**
	 * The number of positions on the wheel (and Locks dial).
	 */
	public static final int positionCount = 20;
	
	/**
	 * Rotate the wheel position by 'amount'.
	 * @param amount	Number of positions to rotate the wheel. Positive is
	 * 					is clockwise.
	 */
	public void rotate(int amount);
	
	/**
	 * @param 	pin			position to measure from
	 * @param	clockwise	counting clockwise or anti
	 * @return	Distance of the pin to this wheels fly
	 */
	public int getDistance(int pin, boolean clockwise);
	
	/**
	 * @return	The position of the notch i.e. the amount the wheel is rotated.
	 */
	public int getNotch();
}
