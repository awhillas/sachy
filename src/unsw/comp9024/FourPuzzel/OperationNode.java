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
	public int getValue() {
		assert left != null && right != null : "Can't calculate operation value if children are null.";
		
		int l = left.getValue();
		int r = right.getValue();
		
		return applyOperation(l, r);
	}

	private int applyOperation(int a, int b) {
		switch (this.op) {
			case DIVIDE:
				return a / b;
			case EXPONENT:
				return a ^ b;
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
		switch (this.op) {
			case DIVIDE:
				return left + " / " + right;
			case EXPONENT:
				return left + "^" + right;
			case MINUS:
				return left + " - " + right;
			case MULTIPLY:
				return left + " * " + right;
			case PLUS:
				return left + " + " + right;
		}
		return "";
	}

		
}
