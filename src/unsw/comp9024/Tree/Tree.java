package unsw.comp9024.Tree;

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

	private String value;
	
	private Tree left;
	
	private Tree right;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Tree (String value) {
		this.value = value;
	}
	
	public void insert (String key) {
		Tree side;
		if (key.compareTo(this.value) <= 0) {
			side = this.left;
		} else {
			side = this.right;
		}
		if (side == null)
			side = new Tree(key);
		else
			side.insert(key);

	}
	
	public boolean contains (String key) {
		if(key.compareTo(this.getValue()) == 0)
			return true;
		else
			return this.left.contains(key) || this.right.contains(key);
	}

	public String getValue() {
		return value;
	}
}
