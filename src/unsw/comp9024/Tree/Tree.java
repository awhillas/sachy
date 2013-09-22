package unsw.comp9024.Tree;

import java.util.Arrays;
import java.util.Collections;


/**
 * Write a class Tree which stores a string which is the string in the root node, and has two child trees left and right.
 * Give it a method void insert (String key) which inserts a string into the tree and keeps the tree ordered.
 * Give it method boolean contains (String key) which returns True if the key is in the tree, and false otherwise.
 * Your class should implement the interfaces MyOrderedTree - which you should design and implement too.
 * @author arbw870
 *
 */
public class Tree implements MyOrderedTree {

	private Node root;
	
	public Tree () {
		root = null;
	}
	
	public Tree (String[] data) {
		root = new TreeNode(data[0]);
		for (int i = 1; i < data.length; i++) {
			root.insert(data[i]);
		}
	}
	
	@Override
	public void insert (String key) {
		if (root == null) {
			root = new TreeNode(key);
		}
		else {
			root.insert(key);
		}
	}
	
	@Override
	public boolean contains (String key) {
		if (root != null) {
			return root.contains(key);
		}
		else
			return false;
	}

	@Override
	public int depth() {
		if (root != null) {
			return root.depth() - 1;
		}
		else {
			return 0;
		}
	}
	
	public void display() {
		if (root != null) {
			root.display(1);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Testing Tree");
		
		// Testing...
		
		String[] data = {"a", "b", "c", "d", "e", "f", "g", "h"};
		Tree t1 = new Tree(data);
		t1.display();
		test(t1, data, 1);
		
		// Try in reverse...
		Collections.reverse(Arrays.asList(data));
		Tree t2 = new Tree(data);
		t2.display();
		test(t2, data, 2);
		
		// Randomise
		Collections.shuffle(Arrays.asList(data));
		Tree t3 = new Tree(data);
		t3.display();
		test(t3, data, 3);
	}
	
	private static void test(Tree t, String[] data, int n) {
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
}
