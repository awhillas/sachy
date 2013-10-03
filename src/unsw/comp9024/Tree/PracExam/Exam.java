package unsw.comp9024.Tree.PracExam;

/*
 *  DO NOT ALTER THIS FILE
 */

public interface Exam {
	
	// implement the following function "insertNext()"
	// 
	// this function is to insert the value into a non-empty tree.  
	// HOWEVER values are not inserted in the normal left to right
	// tree order but instead are placed immediately to the left of the most recently 
	// inserted value (unless that row of the tree is entirely completed in which case
	// the next value to be inserted is placed at the left most end of the next row of 
	// the tree.)
	//
	// ie the tree is filled in row by row from left to right.
	//
	// eg if the values 3 4 4 5 6 8 9 10 11 1 7 were inserted into a tree by calling this 
	// function repeatedly on a tree initially containing only the value 2 as the root 
	// then the eventual tree would look like this:


	/*

                    2
           3                4
       4       5        6       8
      9 10   11 1     7


	*/
	public void insertNext (FancyTree tree, int value); 
}