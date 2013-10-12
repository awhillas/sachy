/**
 * 
 */
package unsw.comp9024.FourPuzzel;

import java.util.ArrayList;

/**
 * Wrapper for the main class which does the FourPuzzel using the Expression Tree
 *
 */
public class FourPuzzel {
	
	private ExpressionTree[] solutions;
	
	public FourPuzzel () {
		this.solutions = new ExpressionTree[101]; // 0..100
	}
	
	public void findSolutions() {
		// 4 x fours so max 3 operators
		for (Operator op1 : Operator.values()) {
			for (Operator op2 : Operator.values()) {
				for (Operator op3 : Operator.values()) {
					
					// First permutation
					Operator[] ops = {op1, op2, op3};
					int[] pos = {4, 2, 6};
					processTrees(makeTrees(ops, pos));
					
					// Second permutation
					Operator[] ops2 = {op2, op1, op3};
					int[] pos2 = {2, 4, 6};
					processTrees(makeTrees(ops2, pos2));
					
					// Third permutation
					Operator[] ops3 = {op3, op2, op1};
					int[] pos3 = {6, 4, 2};
					processTrees(makeTrees(ops3, pos3));
				}
			}
		}		
	}
	
	private void processTrees(ExpressionTree[] trees) {
		for (ExpressionTree t : trees) {
			double result = t.getValue();
			if(result >= 0 && result < 101 && result % 1 == 0) {
				//System.out.println(result);						
				solutions[(int) result] = t;
			}
		}
	}
	
	private void showSolutions() {
		// Print results
		String notFound = "";
		int i = 0;
		for (ExpressionTree t : solutions) {
			if (t != null) {
				System.out.println(Integer.toString(i) + ": " + t);
			} else {
				notFound += Integer.toString(i) + ", ";
			}
			i++;
		}
		System.out.println("Not found:");
		System.out.println(notFound);		
	}
	
	private static ExpressionTree[] makeTrees(Operator[] ops, int[] order) {		
		ArrayList<ExpressionTree> out = new ArrayList<ExpressionTree>();
				
		// All permutations of DigitNode, FactorialNode & SquareRootNode for the 4 digits
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						ExpressionTree root = new OperationNode(ops[0], order[0]);
						root.insert(new OperationNode(ops[1], order[1]));
						root.insert(new OperationNode(ops[2], order[2]));
						
						root.insert(getDigit(i, 4, 1));
						root.insert(getDigit(j, 4, 3));
						root.insert(getDigit(k, 4, 5));
						root.insert(getDigit(l, 4, 7));
						
						out.add(root);
					}
				}
			}
		}
		return out.toArray(new ExpressionTree[0]);
	}
	
	public static DigitNode getDigit(int index, int value, int order) {
		assert index >= 0 && index < 3: "unknown digit: " + index;
		
		switch (index) {
			case 0:
				return new FactorialNode(value, order);
			case 1:
				return new SquareRootNode(value, order);
			default:
			case 2:
				return new DigitNode(value, order);
		}
	}
	
	public static void main(String[] args) {
		FourPuzzel fp = new FourPuzzel();
		fp.findSolutions();
		fp.showSolutions();
	}
	
	
}
