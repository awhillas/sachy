/**
 * 
 */
package unsw.comp9024.Lock;

/**
 * @author Alexander Whillas (z3446737) <whillas@gmail.com>
 *
 */
public class DriveCam implements LockWheel {

	/**
	 * Assume the drive pin is in the same position as the notch.
	 */
	private int notch = 0;
	
	private LockWheel above;
	
	public DriveCam(LockWheel rear) {
		above = rear;
	}
	
	@Override
	public void rotate(int amount) {
		if (amount == 0) return;
		// distance of the pin from the fly.
		int distance = above.getDistance(notch, amount > 0);
		if (distance < amount) {
			above.rotate(distance);
		}
		notch += amount;
	}

	@Override
	public int getDistance(int pin, boolean clockwise) {
		// no fly on this wheel so its always the notch's offset from zero.
		return notch;
	}
	
	@Override
	public int getNotch() {
		return this.notch;
	}
}
