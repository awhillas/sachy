import java.util.Arrays;

public class Iffy {
	
	public static void main(String[] args) {

		int[] d = {4,3,2,1};
		
		System.out.println(Arrays.toString(d));

		// oblivious method...
		
		if (d[0] > d[1])
			Iffy.swap(d, 0, 1);
		if (d[2] > d[3])
			Iffy.swap(d, 2, 3);
		if (d[1] > d[2])
			Iffy.swap(d, 2, 1);
		// Second verse / same as the first!
		if (d[0] > d[1])
			Iffy.swap(d, 0, 1);
		if (d[2] > d[3])
			Iffy.swap(d, 2, 3);
		if (d[1] > d[2])
			Iffy.swap(d, 2, 1);

		System.out.println(Arrays.toString(d));
	}
	
	/**
	 * Switch a for b in arr
	 */
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}	
}