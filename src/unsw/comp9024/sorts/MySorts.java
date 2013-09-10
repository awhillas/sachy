package unsw.comp9024.sorts;

//import java.util.Arrays;

/**
 * @author Alexander Whillas (z3446737)
 * Sort an array in various ways.
 */
public class MySorts {
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
		
		System.out.println("Comparisons: " + MySorts.comparisons);
	}
	
	public static void bubbleSort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			boolean haveSwapped = false;
			for(int j = 0; j < data.length - i - 1; j++) {
				if (data[j] > data[j + 1]) {
					MySorts.swap(data, j, j + 1);
					//haveSwapped = true;
					MySorts.comparisons++;
				}
			}
			//if (!haveSwapped) break;	// This makes best case linear!
		}
	}
	
	public static void insertSort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {	// Start index
			int j = i + 1; 
			while(j > 0) {	// bubble back until hit insertion point.
				if(data[j] < data[j - 1])
					MySorts.swap(data, j, j - 1);
				else
					break;
				j--;
			}
		}
	}
	
	public static void selectSort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			int min = i;
			for (int j = i; j < data.length; j++) {
				if (data[j] < data[min])
					min = j;
			}
			if (min != i)
				MySorts.swap(data, i, min);
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
	
	/**
	 * Shell Sort using [Hibbard, 1963] is gap sequence i.e. 2^k - 1 (where k is the gap term index)
	 * @param data
	 */
	public static void shellSort(int[] data) {
		int k = (int) (Math.log(data.length) / Math.log(2));
		while (k > 0) {
			int gap = (int) Math.pow(2, k) - 1;
			
			// Insertion Sort with gaps...
			for (int i = 0; i < gap; i++) {	// Start index
				int j = i + gap;
				System.out.println("Start:"+j+", gap:"+gap);
				while(j >= 0) {
					if(data[j] < data[j - gap])
						MySorts.swap(data, j, j - gap);
					j -= gap;
				}
			}
			k--;
		}
	}
	
	public static void mergeSort(int[] data) {
		if (data.length == 1) return;
		
		// Divide (the reason for MergeSort's bad memory complexity)
		int middle = data.length / 2;
		int[] left = new int[middle];
		int[] right = new int[data.length - middle];		
		System.arraycopy(data, 0, left, 0, middle);
		System.arraycopy(data, middle, right, 0, data.length - middle);
		
		// Conquer
		mergeSort(left);
		mergeSort(right);
		
		merge(left, right, data);
	}
	
	public static void merge(int[] left, int[] right, int data[]) {
		// merge left and right into data.
		int l = 0;
		int r = 0;
		int i = 0; // we need to keep this for when we merge left in at end.
		while(l < left.length && r < right.length) {
			if(left[l] < right[r]) {
				data[i] = left[l++];
				MySorts.comparisons++;
			} else {
				data[i] = right[r++];
				MySorts.comparisons++;
			}
			i++;
		}
		// copy rest of left/right since we break if hit the end of right.
		if (l < left.length) {
			for (int j = i; j < data.length; j++) {
				data[j] = left[l++];
				MySorts.comparisons++;
			}
		} else {
			for (int j = i; j < data.length; j++) {
				data[j] = right[r++];
				MySorts.comparisons++;
			}
		}
	}
	
	public static void quickSort(int[] data) {
		quickSort(data, 0, data.length - 1);
	}
	private static void quickSort(int[] data, int left, int right) {
		if (left < right) {
			// Choose initial pivot
			int pivot = left + (right - left) / 2;
			int pivotNewIndex = partition(data, left, right, pivot);
			quickSort(data, left, pivotNewIndex - 1);
			quickSort(data, pivotNewIndex + 1, right);
		}
	}
	private static int partition(int[] data, int left, int right, int pivot) {
		int pivotValue = data[pivot];
		MySorts.swap(data, pivot, right);
		int storeIndex = left;
		for (int i = left; i < right - 1; i++)
			if(data[i] < pivotValue) {
				MySorts.swap(data, storeIndex++, i);
			}
		MySorts.swap(data, right, storeIndex);
		return storeIndex;
	}
}