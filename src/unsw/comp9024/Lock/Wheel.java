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
	/**
	 * Relative position of the drive pin to the notch.
	 */
	private int drivePin;
	/**
	 * Relative position of the fly wheel to the notch.
	 */
	private int flyWheel;
	/**
	 * Reference to the LockWheel above this one. 
	 * This wheels drive pin touches its fly wheel.
	 */
	private LockWheel above;
	
	/**
	 * Constructor
	 * @param pin	Relative position of the drive pin to the notch.
	 * @param fly	Relative position of the fly wheel to the notch.
	 * @param wheelAbove	Reference to the LockWheel above this one.
	 */
	public Wheel(int pin, int fly, LockWheel wheelAbove) {
		assert(pin < maxIndex + 1 && pin >= 0);
		assert(fly < maxIndex + 1 && fly >= 0);
		drivePin = pin;
		flyWheel = fly;
		above = wheelAbove;
//		System.out.println("Pin: "+drivePin+" Fly:"+flyWheel);
//		if(above != null)
//			System.out.println("Dis to above CW: "+above.getDistance(drivePin, true));
	}
	
	@Override
	public void rotate(int amount) {
		System.out.println("Rotating by: "+amount);
		if (amount == 0) return;
		if(above != null) {
			boolean clockwise = amount > 0;
			int distance = above.getDistance(getPin(), clockwise);
			if (distance < Math.abs(amount)) {
				if(clockwise) {
					above.rotate(amount - distance);
				}
				else {
					above.rotate(distance - amount);
				}
			}
		}
		System.out.println("Old pos: "+notch+" amount: "+amount);
		notch = (notch + amount) % LockWheel.maxIndex;
		System.out.println("New pos: "+notch);
	}
	
	/**
	 * @return	Distance of this wheel's fly from the given pin position.
	 */
	@Override
	public int getDistance(int pin, boolean clockwise) {
		try {
			assert pin <= maxIndex && pin >= 0: "Pin position out of range: "+pin;
			assert pin != flyWheel: "The pin can not have the same value as the fly:" + pin;
			if (clockwise) {
				if(flyWheel > pin) {
					return flyWheel - pin - 1;
				}
				else {
					return maxIndex - pin + flyWheel;
				}
			}
			else {
				if(flyWheel > pin) {
					return maxIndex - flyWheel + pin;
				}
				else {
					//System.out.println("CCW here");
					return pin - flyWheel - 1;
				}
			}
		} catch (AssertionError e) {
			System.out.println(e);
		}
		return -1;
	}
	
	@Override
	public int getNotch() {
		return notch;
	}
	
	/**
	 * @return	The absolute position of the drive pin relative to zero (north).
	 */
	private int getPin() {
		return (drivePin + notch) % maxIndex;
	}
	
	public static void main(String[] args) {
		// Testing
		Wheel w1 = new Wheel(0, 1, null);
		for (int i = 0; i < maxIndex; i++) {
			System.out.println("Dist. fly: 1 pin: "+i+" CW is:"+w1.getDistance(i, true));
			System.out.println("Dist. fly: 1 pin: "+i+" CCW is:"+w1.getDistance(i, false));
		}
		Wheel w2 = new Wheel(0, 5, null);
		for (int i = 0; i < maxIndex; i++) {
			System.out.println("Dist. fly: 5 pin: "+i+" CW is:"+w2.getDistance(i, true));
			System.out.println("Dist. fly: 5 pin: "+i+" CCW is:"+w2.getDistance(i, false));
		}

	}
}
