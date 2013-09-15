/**
 * 
 */
package unsw.comp9024.Lock;

/**
 * @author Alexander Whillas (z3446737) <whillas@gmail.com>
 *
 */
public class Lock {

	private static final int wheelCount = 4;
	
	private LockWheel[] wheels;
	
	public static void main(String[] args) {
		// Tests...
		Lock l1 = new Lock(1,1, 2,2, 3,3);
		if(l1.isLocked()) System.out.println("FAILED: isLocked after constructor"); else System.out.println("PASSED!");
		l1.turnClockwise(LockWheel.maxIndex);
		if(l1.isLocked()) System.out.println("FAILED: isLocked after full rotation."); else System.out.println("PASSED!");
		l1.turnClockwise(1);
		if(!l1.isLocked()) System.out.println("FAILED: isLocked after 1 rotation"); else System.out.println("PASSED!");
	}
	
	public Lock (int rearPin, int rearFly, int midPin, int midFly, int frontPin, int frontFly) {
		wheels = new LockWheel[wheelCount];
		// Start at the bottom and chain the previous wheel to the one bellow it.
		wheels[3] = new Wheel(frontPin, frontFly, null);
		wheels[2] = new Wheel(midPin, midFly, wheels[3]);
		wheels[1] = new Wheel(rearPin, rearFly, wheels[2]);
		wheels[0] = new Wheel(0, 0, wheels[1]);	// drive cam
	}
	
	/**
	 * Checks that all the wheels are in the 0 position.
	 * @return true if the Lock is locked, false otherwise.
	 */
	public boolean isLocked() {
		for(int i = 0; i < wheelCount; i++) {
			if (wheels[i].getNotch() != 0) {
				//System.out.println(i+" is out." + wheels[i].getNotch());
				return true;
			}
		}
		return false;
	}
	
	public void turnClockwise(int units) {
		turn(units);
	}
	
	public void turnAntiClockwise(int units) {
		turn(-units);
	}
	
	private void turn(int amount) {
		wheels[0].rotate(amount);	// cam wheel
	}
}
