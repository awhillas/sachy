/**
 * 
 */
package unsw.comp9024.Lock;

/**
 * @author Alexander Whillas (z3446737) <whillas@gmail.com>
 *
 */
public class Wheel implements LockWheel {
	/**
	 * The notch is always assumed to be at the 0 position on the wheel thus
	 * this really stores the offset of the wheel from its upright position.
	 */
	private int notch;
	private int drivePin;
	private int flyWheel;
	private LockWheel above;
	
	public Wheel(int pin, int fly, LockWheel wheelAbove) {
		assert(pin < positionCount && pin >= 0);
		assert(fly < positionCount && fly >= 0);
		drivePin = pin;
		flyWheel = fly;
		above = wheelAbove; 
	}
	
	@Override
	public void rotate(int amount) {
		if (amount == 0) return;
		
		int distance = above.getDistance(notch, amount > 0);
		if (distance < amount) {
			above.rotate(distance);
		}
		notch += amount;
	}
	
	@Override
	public int getDistance(int pin, boolean clockwise) {
		int distance = 0;
		if (pin < flyWheel && clockwise) {
			// -1 because we can't occupy the same space as the pin.
			distance = flyWheel - pin - 1;
		}
		else {
			distance = (positionCount - 1) + (pin - flyWheel);
		}
		return distance;
	}
	
	@Override
	public int getNotch() {
		return this.notch;
	}
}
