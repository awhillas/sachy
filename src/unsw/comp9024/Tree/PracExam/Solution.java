package unsw.comp9024.Tree.PracExam;

public class Solution implements Exam {
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
	public void insertNext (FancyTree tree, int value) {
		assert (tree != null);

		int height = this.getHeight(tree.getMostRecent(), 0);
		FancyTree root = findRoot(tree);
		
		if(!findAndInsert(root, height, value)) {
			findAndInsert(root, height + 1, value);
		}
	}
	
	// Depth Limited Breadth First Search
	private boolean findAndInsert(FancyTree node, int depth, int value) {
		if (depth <= 1) {
			// Insert at the right spot
			if(node.getLeft() == null) {
				node.newLeftChild(value);
				return true;
			} else if (node.getRight() == null) {
				node.newRightChild(value);
				return true;
			}
			return false;
		} else {
			// Left to right search of the tree to the right depth.
			if(!findAndInsert(node.getLeft(), depth - 1, value)) {
				if( findAndInsert(node.getRight(), depth - 1, value) ) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
	}

	private FancyTree findRoot(FancyTree node) {
		if (node.getParent() != null) {
			return findRoot(node.getParent());
		} else {
			return node;
		}
	}
	
	private int getHeight(FancyTree node, int height) {
		if (node.getParent() != null) {
			height += getHeight(node.getParent(), height) + 1;
		}
		return height;
	}

}

