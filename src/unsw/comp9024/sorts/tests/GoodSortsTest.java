package unsw.comp9024.sorts.tests;

import static org.junit.Assert.*;

import java.util.Arrays;







//import junit.framework.Assert;
import org.junit.Test;
//import java.util.Arrays;








import unsw.comp9024.sorts.*;

enum SortTypes {
	SELECT,
	INSERT,
	BUBBLE,
	SHELL,
	MERGE,
	QUICK
}

public class GoodSortsTest extends MySorts {

	
	@Test
	public void bubbleSortTest() {
		System.out.println("Bubble sort");
		arraySortTest("bubble");
	}
	
	@Test
	public void insertSortTest() {
		System.out.println("Insertion sort");
		arraySortTest("insert");
	}

	@Test
	public void selectSortTest() {
		System.out.println("Selection sort");
		arraySortTest("select");
	}
	
	@Test
	public void shellSortTest() {
		System.out.println("Shell sort");
		arraySortTest("shell");
	}

	@Test
	public void mergeSortTest() {
		System.out.println("Merge sort");
		arraySortTest("merge");
	}

	@Test
	public void quickSortTest() {
		System.out.println("Quick sort");		
		arraySortTest("quick");
	}

	private void arraySortTest(String type) {
		
		int[] expected = generateTestArray(12);
		int[] result = generateReverseArray(12);
		doSort(result, type);
		System.out.println(Arrays.toString(result));
		assertArrayEquals( expected, result);
		
		int[] expected2 = generateTestArray(13);
		int[] result2 = new int[13];
		System.arraycopy(expected2, 0, result2, 0, expected2.length);;
		randomise(result2);
		doSort(result2, type);
		System.out.println(Arrays.toString(result2));
		assertArrayEquals( expected2, result2);
	}

	private void doSort(int[] sortme, String type) {
		switch (SortTypes.valueOf(type.toUpperCase())) {
			case SHELL: shellSort(sortme);
				break;
			case MERGE: mergeSort(sortme);
				break;
			case QUICK: quickSort(sortme);
				break;
			case SELECT: selectSort(sortme);
				break;
			case INSERT: insertSort(sortme);
				break;
			case BUBBLE: 
			default: bubbleSort(sortme);
				break;
		}
	}
	
	private int[] generateTestArray(int size) {
		int[] sorted = new int[size];
		for(int i = 0; i < size; i++)
			sorted[i] = i;
		return sorted;
	}
	
	private int[] generateReverseArray(int size) {
		int[] sortme = new int[size];
		for (int i = 0; i < size; i++) {
			sortme[size - i - 1] = i;
		}
		return sortme;
	}
	
	private void randomise(int[] data) {
		for (int i = 0; i < data.length; i++) {
			MySorts.swap(data, i, (int)(Math.random() * data.length));
		}
	}
}
