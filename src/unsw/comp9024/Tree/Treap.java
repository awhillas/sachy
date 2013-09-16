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


/**
 * @author arbw870
 *
 */
public class Treap implements MyOrderedTree, MyHeap {

	private TreapNode root;

	public Treap (String[] data) {
		root = new TreapNode(data[0], null);
		for (int i = 1; i < data.length; i++) {
			root = root.insert(data[i]);
		}
	}
	
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
			root = root.insert(s);
		}
	}
	
	@Override
	public void insert (String key, int heapWeight) {
		if (root == null) {
			root = new TreapNode(key, heapWeight, null);
		}
		else {
			root = root.insert(key, heapWeight);
		}
	}
	
	public void setRoot(TreapNode n) {
		this.root = n;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Testing Treap");
		
		// Testing...
		
		String[] data = {"a", "b", "c", "d", "e", "f", "g", "h"};
		Treap t1 = new Treap(data);
		t1.display();
		test(t1, data, 1);
		
		// Try in reverse...
		Collections.reverse(Arrays.asList(data));
		Treap t2 = new Treap(data);
		t2.display();
		test(t2, data, 2);
		
		// Randomise
		Collections.shuffle(Arrays.asList(data));
		Treap t3 = new Treap(data);
		t3.display();
		test(t3, data, 3);
	}
	
	private static void test(Treap t, String[] data, int n) {
		// Test inserting and lookup works.
		boolean hasAllData = true;
		for (int i = 0; i < data.length; i++) {
			if (!t.contains(data[i])) {
				System.out.println("Missing: "+ data[i]);
				hasAllData = false;
			}
		}
		if(!hasAllData)
			System.out.println(n+".FAIL: Not all data found?");
		else
			System.out.println(n+".PASSED: All data found!");		
	}


	/**
	 * @param i
	 */
	private void display() {
		if (root != null) {
			root.display(1);
		}
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
