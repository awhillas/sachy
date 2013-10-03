/**
 * 
 */
package unsw.comp9024.Tree.PracExam;


public class MyTree implements FancyTree {

	public static MyTree mostRecent;
	
	private int rootValue;
	private MyTree parent;
	private MyTree left;
	private MyTree right;
	
	/**
	 * 
	 */
	public MyTree(int value, MyTree parent) {
		this.rootValue = value;
		this.parent = parent;
		MyTree.mostRecent = this;
	}

	public MyTree(int value) {
		this.rootValue = value;
		this.parent = null;
		MyTree.mostRecent = this;
	}

	/**
	 * return the value stored in the root of the tree
	 */
	@Override
	public int getRootValue() {
		return this.rootValue;
	}

	@Override
	public FancyTree getParent() {
		return this.parent;
	}

	@Override
	public FancyTree getLeft() {
		return this.left;
	}

	@Override
	public FancyTree getRight() {
		return this.right;
	}

	@Override
	public FancyTree getMostRecent() {
		return MyTree.mostRecent;
	}

	/**
	 * create a new one node tree with "value" stored at its root, and store it as the left child
	 * of the root node of the tree.  this function can assume that this is currently no left child of
	 * the root node of the tree.
	 */
	@Override
	public void newLeftChild(int value) {
		this.left = new MyTree(value, this);
	}

	/**
	 * as for newLeftChild, except it adds the node as the right child of the root node of the tree.
	 */
	@Override
	public void newRightChild(int value) {
		this.right = new MyTree(value, this);
	}

	  public static void main (String[] arguments) {
	      System.out.println ("Running unit tests on Tree()...\n");
	      testTree();
	      System.out.println ("All unit tests passed! You are AWESOME!\n");
	      
	   }


	   public static void testTree () {
		  System.out.println ("testing MyTree");
	      System.out.println ("testing insert in empty tree");
	      MyTree testTree = new MyTree (5, null);
	      assert (testTree.getRootValue() == 5);
	      assert (testTree.getParent() == null);
	      assert (testTree.getLeft() == null);
	      assert (testTree.getRight() == null);
	      assert (testTree.getMostRecent() == testTree);

	      System.out.println ("testing insert in one element tree");
	      testTree.newLeftChild(3);

	      MyTree left = testTree.left;

	      assert (testTree.rootValue == 5);
	      assert (testTree.parent == null);
	      assert (testTree.left == left);
	      assert (testTree.right == null);
	      assert (testTree.getMostRecent() == left);

	      assert (testTree.left.rootValue == 3);
	      assert (testTree.left.parent == testTree);
	      assert (testTree.left.left == null);
	      assert (testTree.left.right == null);
	      assert (testTree.left.getMostRecent() == left);


	      System.out.println ("testing insert in two element tree");
	      testTree.newRightChild(7);

	      MyTree right = testTree.right;

	      assert (testTree.rootValue == 5);
	      assert (testTree.parent == null);
	      assert (testTree.left == left);
	      assert (testTree.right == right);
	      assert (testTree.getMostRecent() == right);

	      assert (testTree.left.rootValue == 3);
	      assert (testTree.left.parent == testTree);
	      assert (testTree.left.left == null);
	      assert (testTree.left.right == null);
//	      assert (testTree.left.getMostRecent() == left);	// ??

	      assert (testTree.right.rootValue == 7);
	      assert (testTree.right.parent == testTree);
	      assert (testTree.right.left == null);
	      assert (testTree.right.right == null);
	      assert (testTree.right.getMostRecent() == right);


	      System.out.println ("testing insert in three element tree");
	      testTree.left.newRightChild(4);

	      MyTree leftRight = testTree.left.right;

	      assert (testTree.rootValue == 5);
	      assert (testTree.parent == null);
	      assert (testTree.left == left);
	      assert (testTree.right == right);
	      assert (testTree.getMostRecent() == leftRight);

	      assert (testTree.left.rootValue == 3);
	      assert (testTree.left.parent == testTree);
	      assert (testTree.left.left == null);
	      assert (testTree.left.right == leftRight);
	      assert (testTree.left.getMostRecent() == leftRight);

	      assert (testTree.right.rootValue == 7);
	      assert (testTree.right.parent == testTree);
	      assert (testTree.right.left == null);
	      assert (testTree.right.right == null);
//	      assert (testTree.right.getMostRecent() == right);	// ??

	      assert (testTree.left.right.rootValue == 4);
	      assert (testTree.left.right.parent == left);
	      assert (testTree.left.right.left == null);
	      assert (testTree.left.right.right == null);
	      assert (testTree.left.right.getMostRecent() == leftRight);


	      System.out.println ("... all tests passed\n");      
	   }


}
