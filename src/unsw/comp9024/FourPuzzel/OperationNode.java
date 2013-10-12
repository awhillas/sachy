/**
 * 
 */
package unsw.comp9024.FourPuzzel;

/**
 * Operation node.
 *
 */
public class OperationNode implements ExpressionTree {

	/**
	 * The operation at this node.
	 */
	protected Operator op;
	/**
	 * Used to control placement in the tree.
	 */
	protected int order;
	protected ExpressionTree left;
	protected ExpressionTree right;
	
	public OperationNode (Operator op, int position) {
		this.op = op;
		this.order = position;
	}
	
	/**
	 * Insert a sub element either left or right.
	 */
	public void insert(ExpressionTree n) {
		if (n.getOrder() <= order) {
			if(left != null) {
				left.insert(n);
			}
			else {
				left = n;
			}
		} else {
			if(right != null) {
				right.insert(n);
			}
			else {
				right = n;
			}			
		}
	}

	@Override
	public int getOrder() {
		return this.order;
	}

	@Override
	public double getValue() {
		assert left != null && right != null : "Can't calculate operation value if children are null.";
		
		double l = left.getValue();
		double r = right.getValue();
		
		if (l != -1 && r != -1) {
			return applyOperation(l, r);
		} else {
			return -1;	// error
		}
	}

	private double applyOperation(double a, double b) {
		switch (this.op) {
			case DIVIDE:
				if (b != 0) {
					return a / b;
				} else {
					return -1;	// error
				}
			case EXPONENT:
				return Math.pow(a, b);
			case MINUS:
				return a - b;
			case MULTIPLY:
				return a * b;
			case PLUS:
				return a + b;
		}
		return 0;
	}

	@Override
	public String toString() {
		String out = "";
		switch (this.op) {
			case DIVIDE:
				out += left + " / " + right;
				break;
			case EXPONENT:
				out += left + " ^ " + right;
				break;
			case MINUS:
				out += left + " - " + right;
				break;
			case MULTIPLY:
				out += left + " * " + right;
				break;
			case PLUS:
				out += left + " + " + right;
		}
		return "(" + out + ")";
	}

	public static void main(String[] args) {
		
		// Test all the operators in different precedence.
		
		// ((4 ^ 4) - (4 + 4))
		
		ExpressionTree root = new OperationNode(Operator.MINUS, 4);
		root.insert(new OperationNode(Operator.PLUS, 6));
		root.insert(new OperationNode(Operator.EXPONENT, 2));
		int[] digitOrder = { 1, 3, 5, 7 };	// order to give digits.
		for (int i : digitOrder) {
			root.insert(new DigitNode(4, i));
		}
		System.out.println(root);
		System.out.println(root.getValue());
		assert root.getValue() == 248 : "Something went wrong :(";

		// (4 * (4 / (4 + 4)))
		
		root = new OperationNode(Operator.MULTIPLY, 2);
		root.insert(new OperationNode(Operator.DIVIDE, 4));
		root.insert(new OperationNode(Operator.PLUS, 6));
		for (int i : digitOrder) {
			root.insert(new DigitNode(4, i));
		}
		System.out.println(root);
		System.out.println(root.getValue());
		assert root.getValue() == 2 : "Something went wrong :(";
		
		
		System.out.println("PASSED!");
	}
		
}
