package unsw.comp9024.Tree.PracExam;

/*
 * TestSolution.java
 *  DO NOT ALTER THIS FILE

 * some simple unit tests for an implementation of Solution
 * you can use this to help you test your solution.
 *
 *  run with:
 *     javac *.java
 *     java -ea TestSolution
 */

public class TestSolution {
   public static void main (String[] args) {

   	   Solution s = new Solution();
   	   FancyTree tree = new MyTree (2);
   	   s.insertNext (tree, 3);
   	   s.insertNext (tree, 4);
   	   s.insertNext (tree, 4);
   	   s.insertNext (tree, 5);
   	   s.insertNext (tree, 6);
   	   s.insertNext (tree, 8);
   	   s.insertNext (tree, 9);
   	   s.insertNext (tree, 10);
   	   s.insertNext (tree, 11);
   	   s.insertNext (tree, 1);
   	   s.insertNext (tree, 7);
       
       assert (tree.getRootValue() == 2);

       assert (tree.getLeft().getRootValue() == 3);
       assert (tree.getRight().getRootValue() == 4);

       assert (tree.getLeft().getLeft().getRootValue() == 4);
       assert (tree.getLeft().getRight().getRootValue() == 5);
       assert (tree.getRight().getLeft().getRootValue() == 6);
       assert (tree.getRight().getRight().getRootValue() == 8);

       assert (tree.getLeft().getLeft().getLeft().getRootValue() == 9);
       assert (tree.getLeft().getLeft().getRight().getRootValue() == 10);
       assert (tree.getLeft().getRight().getLeft().getRootValue() == 11);
       assert (tree.getLeft().getRight().getRight().getRootValue() == 1);
       assert (tree.getRight().getLeft().getLeft().getRootValue() == 7);
       assert (tree.getRight().getLeft().getRight() == null);
       assert (tree.getRight().getRight().getLeft() == null);
       assert (tree.getRight().getRight().getRight() == null);

   	   System.out.println ("All unit tests passed.  You are Awesome!");
   }

}