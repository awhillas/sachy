package unsw.comp9024.Tree.PracExam;

public interface FancyTree {
	 
	   // return the value stored in the root of the tree
	   public int  getRootValue ();
	 
	   // return the parent tree
	   public FancyTree getParent ();
	 
	   // return the left child tree
	   public FancyTree getLeft ();
	 
	   // return the right child tree
	   public FancyTree getRight ();
	 
	   // return the (one node) tree most recently added to the tree
	   public FancyTree getMostRecent ();
	 
	   // create a new one node tree with "value" stored at its root, and store it as the left child
	   // of the root node of the tree.  this function can assume that this is currently no left child of
	   // the root node of the tree.
	   public void newLeftChild (int value);
	 
	   // as for newLeftChild, except it adds the node as the right child of the root node of the tree.
	   public void newRightChild (int value);
}