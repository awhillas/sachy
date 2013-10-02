package unsw.comp9024.Tree.PracExam;

/*
 *  Tree.java
 *  2013s2 trial Prac Exam
 *  UNSW comp9024 mid-session
 *
 *  
 */
public class RichardsTree implements FancyTree {

   private int rootValue;
   private RichardsTree parent;
   private RichardsTree left;
   private RichardsTree right;
   private RichardsTree mostRecent;

   private RichardsTree (int value, RichardsTree parent) {
      this.rootValue = value;
      this.parent = parent;
      this.left = null;
      this.right = null;
      this.mostRecent = this;
   }

   public RichardsTree (int value) {
      this(value, null);
   }

   public int getRootValue () {
      return this.rootValue;
   }

   public FancyTree getParent () {
      return this.parent;
   }

   public FancyTree getLeft () {
      return this.left;
   }

   public FancyTree getRight () {
      return this.right;
   }

   public FancyTree getMostRecent () {
      return this.mostRecent;
   }

   private void setMostRecent (RichardsTree mostRecent) {
      this.mostRecent = mostRecent;
      RichardsTree parent = this.parent;
      while (parent != null) {
         parent.mostRecent = mostRecent;
         parent = parent.parent;
      }
   }

   public void newLeftChild (int value) {
      assert (this.getLeft() == null);
      RichardsTree newChild = new RichardsTree (value, this);
      this.left = newChild;
      this.setMostRecent (newChild);
   }

   public void newRightChild (int value) {
      assert (this.getRight() == null);
      RichardsTree newChild = new RichardsTree (value, this);
      this.right = newChild;
      this.setMostRecent (newChild);
   }
   

   public static void main (String[] arguments) {
      System.out.println ("Running unit tests on Tree()...\n");
      testTree();
      System.out.println ("All unit tests passed! You are AWESOME!\n");
      
   }


   public static void testTree () {
	   System.out.println ("testing RichardsTree");
      System.out.println ("testing insert in empty tree");
      RichardsTree testTree = new RichardsTree (5,null);
      assert (testTree.getRootValue() == 5);
      assert (testTree.getParent() == null);
      assert (testTree.getLeft() == null);
      assert (testTree.getRight() == null);
      assert (testTree.getMostRecent() == testTree);

      System.out.println ("testing insert in one element tree");
      testTree.newLeftChild(3);

      RichardsTree left = testTree.left;

      assert (testTree.rootValue == 5);
      assert (testTree.parent == null);
      assert (testTree.left == left);
      assert (testTree.right == null);
      assert (testTree.mostRecent == left);

      assert (testTree.left.rootValue == 3);
      assert (testTree.left.parent == testTree);
      assert (testTree.left.left == null);
      assert (testTree.left.right == null);
      assert (testTree.left.mostRecent == left);


      System.out.println ("testing insert in two element tree");
      testTree.newRightChild(7);

      RichardsTree right = testTree.right;

      assert (testTree.rootValue == 5);
      assert (testTree.parent == null);
      assert (testTree.left == left);
      assert (testTree.right == right);
      assert (testTree.mostRecent == right);

      assert (testTree.left.rootValue == 3);
      assert (testTree.left.parent == testTree);
      assert (testTree.left.left == null);
      assert (testTree.left.right == null);
      assert (testTree.left.mostRecent == left);

      assert (testTree.right.rootValue == 7);
      assert (testTree.right.parent == testTree);
      assert (testTree.right.left == null);
      assert (testTree.right.right == null);
      assert (testTree.right.mostRecent == right);


      System.out.println ("testing insert in three element tree");
      testTree.left.newRightChild(4);

      RichardsTree leftRight = testTree.left.right;

      assert (testTree.rootValue == 5);
      assert (testTree.parent == null);
      assert (testTree.left == left);
      assert (testTree.right == right);
      assert (testTree.mostRecent == leftRight);

      assert (testTree.left.rootValue == 3);
      assert (testTree.left.parent == testTree);
      assert (testTree.left.left == null);
      assert (testTree.left.right == leftRight);
      assert (testTree.left.mostRecent == leftRight);

      assert (testTree.right.rootValue == 7);
      assert (testTree.right.parent == testTree);
      assert (testTree.right.left == null);
      assert (testTree.right.right == null);
      assert (testTree.right.mostRecent == right);

      assert (testTree.left.right.rootValue == 4);
      assert (testTree.left.right.parent == left);
      assert (testTree.left.right.left == null);
      assert (testTree.left.right.right == null);
      assert (testTree.left.right.mostRecent == leftRight);


      System.out.println ("... all tests passed\n");      
   }
}