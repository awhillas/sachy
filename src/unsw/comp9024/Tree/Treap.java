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

/**
 * @author arbw870
 *
 */
public class Treap implements MyOrderedTree, MyHeap {

	private String value;
	
	private int heapValue;
	
	private Treap left;
	
	private Treap right;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		
	}

	public void insert (String key, int heapWeight) {
		
	}

	public boolean contains (String key) {
		// TODO Auto-generated method stub.
		return false;
	}
	
}
