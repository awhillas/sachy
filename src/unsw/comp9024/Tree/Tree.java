package unsw.comp9024.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import unsw.comp9024.MyOrderedTree;

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
}
