package unsw.comp9024.Tree;

public interface MyOrderedTree {

	public void insert (String s);
	
	public boolean contains (String key);

	/**
	 * @return	The depth of the tree.
	 */
	public int depth();
}
