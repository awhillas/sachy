import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;


/**
 * Week 6 exercise.
 */

public class LibrarySort {

	private static int comparisons = 0;

	public static void librarySort(int[] data) {
		//int k = (int) Math.sqrt(data.length);
		int k = data.length / 2;
		
		int[] first = new int[k];
		int[] last = new int[data.length - k];
		System.arraycopy(data, 0, first, 0, k);
		System.arraycopy(data, k, last, 0, data.length - k);
		
		bubbleSort(first);
		bubbleSort(last);
		
		merge(first, last, data);
		
		System.out.println("Comparisons: " + LibrarySort.comparisons);
	}

	public static void bubbleSort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			//boolean haveSwapped = false;
			for(int j = 0; j < data.length - i - 1; j++) {
				if (data[j] > data[j + 1]) {
					LibrarySort.swap(data, j, j + 1);
					//haveSwapped = true;
					LibrarySort.comparisons++;
				}
			}
			//if (!haveSwapped) break;	// This makes best case linear!
		}
	}	
	
	/**
	 * Switch a for b in arr
	 */
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void merge(int[] left, int[] right, int data[]) {
		// merge left and right into data.
		int l = 0;
		int r = 0;
		int i = 0; // we need to keep this for when we merge left in at end.
		while(l < left.length && r < right.length) {
			if(left[l] < right[r]) {
				data[i] = left[l++];
				LibrarySort.comparisons++;
			} else {
				data[i] = right[r++];
				LibrarySort.comparisons++;
			}
			i++;
		}
		// copy rest of left/right since we break if hit the end of right.
		if (l < left.length) {
			for (int j = i; j < data.length; j++) {
				data[j] = left[l++];
				LibrarySort.comparisons++;
			}
		} else {
			for (int j = i; j < data.length; j++) {
				data[j] = right[r++];
				LibrarySort.comparisons++;
			}
		}
	}	
	
	/**
	 * Testing
	 */
	public static void main(String[] args) {
		int[] expected = generateTestArray(12);
		int[] result = generateReverseArray(12);
		librarySort(result);
		System.out.println(Arrays.toString(result));
		assertArrayEquals( expected, result);
		
		int[] expected2 = generateTestArray(13);
		int[] result2 = new int[13];
		System.arraycopy(expected2, 0, result2, 0, expected2.length);;
		randomise(result2);
		librarySort(result2);
		System.out.println(Arrays.toString(result2));
		assertArrayEquals( expected2, result2);	
	}
	
	private static int[] generateTestArray(int size) {
		int[] sorted = new int[size];
		for(int i = 0; i < size; i++)
			sorted[i] = i;
		return sorted;
	}
	
	private static int[] generateReverseArray(int size) {
		int[] sortme = new int[size];
		for (int i = 0; i < size; i++) {
			sortme[size - i - 1] = i;
		}
		return sortme;
	}
	
	private static void randomise(int[] data) {
		for (int i = 0; i < data.length; i++) {
			swap(data, i, (int)(Math.random() * data.length));
		}
	}
}
