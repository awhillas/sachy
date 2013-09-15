package unsw.comp9024.Tree;

/**
 * @author alex
 *
 */
public class TreapNode {

	private String value;
	
	private int	heapWeight;
	
	protected TreapNode parent;
	
	protected TreapNode left;
	
	protected TreapNode right;
	
	public TreapNode(String value, TreapNode parent) {
		this.value = value;
		this.heapWeight = (int) (Math.random() * 100);
		this.parent = parent;
	}
	
	public TreapNode(String value, int heapWeight, TreapNode parent) {
		this.value = value;
		this.heapWeight = heapWeight;
		this.parent = parent;
	}

	public boolean contains(String needle) {
		if (value.compareTo(needle) == 0) {
			return true;
		}
		else {
			if (value.compareTo(needle) > 0) {
				return (left != null)? left.contains(needle): false;
			}
			else {
				return (right != null)? right.contains(needle): false;
			}
		}
	}

	/**
	 * Insert a String in the bottom then rotate it up using heap value.
	 * @return	The new root node.
	 */
	public TreapNode insert(String key) {
		return insert(key, (int) (Math.random() * 100));
	}

	public TreapNode insert (String key, int heapWeight) {
		if (key.compareTo(value) <= 0) {
			if(left != null) {
				left.insert(key, heapWeight);
			}
			else {
				left = new TreapNode(key, heapWeight, this);
				if (this.isHeavier(heapWeight)) {
					rotateLeft();
				}
			}
		} else {
			if(right != null) {
				right.insert(key, heapWeight);
			}
			else {
				right = new TreapNode(key, heapWeight, this);
				if (this.isHeavier(heapWeight)) {
					rotateRight();
				}
			}
		}
		return findRoot();
	}
	
	private TreapNode findRoot() {
		if(this.parent == null) {
			return this;
		} else {
			return this.parent.findRoot();
		}
	}

	/**
	 * @param weight	heap weight to compare to.
	 * @return	true if the given weight is heaver than its own heap weight.
	 */
	private boolean isHeavier(int weight) {
		return heapWeight > weight;
	}

	private void rotateLeft() {
		TreapNode up = left;
		// Start from the top, fix parent.
		up.parent = parent;
		if(parent != null)
			if(parent.left == this) {
				parent.left = up;
			} else {
				parent.right = up;
			}
		// then the children
		this.left = up.right;
		if(this.left != null) {
			this.left.parent = this;
		}
		// finally this node
		this.parent = up;
		up.right = this;
	}

	private void rotateRight() {
		TreapNode up = right;
		// Start from the top, fix parent.
		up.parent = parent;
		if(parent != null)
			if(parent.right == this) {
				parent.right = up;
			} else {
				parent.left = up;
			}
		// then the children
		this.right = up.left;
		if(this.right != null) {
			this.right.parent = this;
		}
		// finally this node
		this.parent = up;
		up.left = this;
	}
	
	public String toString() {
		return "("+value+","+heapWeight+")";
	}
	
	public void display(int depth) {
		String indent = String.format("%" + depth + "s", " ");
		System.out.println(indent + this);
		if(left != null) {
			left.display(depth + 1);
		}
		else {
			System.out.println(indent + indent + "-l");
		}
		if(right != null) {
			right.display(depth + 1);
		}
		else {
			System.out.println(indent + indent + "-r");
		}
	}
}
