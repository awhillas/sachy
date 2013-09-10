package unsw.comp9024.searches;

public class BinarySearch {

	public static void main(String[] args) {
		int[] test1  = {1,2,3,4,5,6};
		if(search(5, test1))
			System.out.println("Test 1 passed!");
		else
			System.out.println("Test 1 FAILED!");
		if(search(1, test1))
			System.out.println("Test 2 passed!");
		else
			System.out.println("Test 2 FAILED!");
		if(!search(8, test1))
			System.out.println("Test 3 passed!");
		else
			System.out.println("Test 3 FAILED!");
	}

	public static boolean search(int needle, int[] hay) {
		return search(0, hay.length - 1, needle, hay);
	}

	public static boolean search(int start, int end, int needle, int[] hay) {
		if(start - end == 0)
			return false;

		int middle = start + (end - start) / 2;

		if(hay[middle] == needle)
			return true;
		else if (hay[middle] < needle)
			return search(middle + 1, end, needle, hay); // top half
		else
			return search(start,  middle - 1, needle, hay); // bottom half
	} 
}
