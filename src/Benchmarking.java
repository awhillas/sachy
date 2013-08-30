/**
 * Homework 1: Benchmarking Java
 */
public class Benchmarking {
	
	public static void main(String [] args) {		
		int size = 0;
		if (args.length > 0) {
			size = Integer.parseInt(args[0]);
		}
		int[] arr = new int[size];
		long total = 0;
		int j = 0;
		while (j++ < 12) {
			for (int i = size - 1; i >= 0; i--)
				arr[i] = size - i;	// look Ma, no braces!
			long t1 = System.nanoTime();
			Benchmarking.bubbleSort(arr);
			long t2 = System.nanoTime();
			total += (t2 - t1);
            System.out.println(((t2 - t1) * 1e-6) + "\n");
		}
		System.out.println("Execution time: " + total * 1e-6 / 12  + " milliseconds");
	}
	
	/**
	 * @see: http://www.algolist.net/Algorithms/Sorting/Bubble_sort
	 */ 
	public static void bubbleSort(int[] arr) {
		boolean swapped = true;
		int j = 0;
		int tmp;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < arr.length - j; i++) {
				if (arr[i] > arr[i + 1]) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					swapped = true;
				}
			}
		}
	}
}
