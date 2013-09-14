/**
 * Write a class Treap which stores a (string,int) tuple in the root node, 
 * and has two child trees of tuples left and right. 
 * Give it a method void insert (String key, int heapWeight) which inserts 
 * a string into the treap and keeps the treap ordered left to right by 
 * string value, and in heap order by the heapWeight.
 * Give it method boolean contains (String key) which returns True if the 
 * key is in the treap, and false otherwise.
 * Your class should implement the interfaces MyOrderedTree and MyHeap - 
 * which you should design and implement too.
 */
package unsw.comp9024.Tree;

import java.util.Arrays;
import java.util.Collections;

import unsw.comp9024.MyHeap;
import unsw.comp9024.MyOrderedTree;

/**
 * @author arbw870
 *
 */
public class Treap implements MyOrderedTree, MyHeap {

	private Node root;

	@Override
	public boolean contains (String s) {
		if (root != null) {
			return root.contains(s);
		}
		else {
			return false;
		}
	}

	@Override
	public void insert(String s) {
		if (root == null) {
			root = new TreapNode(s, null);
		}
		else {
			root.insert(s);
		}
	}
	
	@Override
	public void insert (String key, int heapWeight) {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Testing...
		String[] data = {"1", "2", "3", "4", "1a", "2a", "3a", "4a"};
		Tree t1 = new Tree(data);

		// Test inserting and lookup works.
		boolean hasAllData = true;
		for (int i = 0; i < data.length; i++) {
			if (!t1.contains(data[i])) {
				System.out.println("Missing: "+ data[i]);
				hasAllData = false;
			}
		}
		if(!hasAllData)
			System.out.println("1.FAIL: Not all data found?");
		else
			System.out.println("1.PASSED: All data found!");
		
		// Try in reverse...
		Collections.reverse(Arrays.asList(data));
		hasAllData = true;
		for (int i = 0; i < data.length; i++) {
			if (!t1.contains(data[i])) {
				System.out.println("Missing: "+ data[i]);
				hasAllData = false;
			}
		}
		if(!hasAllData)
			System.out.println("2.FAIL: Not all data found?");
		else
			System.out.println("2.PASSED: All data found!");
	}

	/* (non-Javadoc)
	 * @see unsw.comp9024.MyOrderedTree#depth()
	 */
	@Override
	public int depth() {
		// TODO Auto-generated method stub
		return 0;
	}
}
