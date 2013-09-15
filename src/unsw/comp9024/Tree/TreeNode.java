package unsw.comp9024.Tree;

public class TreeNode implements Node {

	private String value;
	
	private TreeNode left;
	
	private TreeNode right;

	public TreeNode (String value) {
		this.value = value;
		//System.out.println("Created Node:"+value);
	}

	@Override
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

	@Override
	public void insert(String key) {
		if (key.compareTo(value) <= 0) {
			if(left != null) {
				left.insert(key);
			}
			else {
				left = new TreeNode(key);
			}
		} else {
			if(right != null) {
				right.insert(key);
			}
			else {
				right = new TreeNode(key);
			}			
		}
	}

	public String toString() {
		return "TreeNode: "+value;
	}
	
	
	@Override
	public int depth() {
		int l, r;
		if (left != null) {
			l = left.depth();
		} else {
			l = 0;
		}
		if (right != null) {
			r = right.depth();
		} else {
			r = 0;
		}
		return Math.max(l, r) + 1;
	}

	public void display(int depth) {
		String indent = String.format("%" + depth + "s", " ");
		System.out.println(indent + "("+value+")");
		if(left != null) {
			left.display(depth + 1);
		}
		else {
			System.out.println(indent + indent + "-");
		}
		if(right != null) {
			right.display(depth + 1);
		}
		else {
			System.out.println(indent + indent + "-");
		}
	}
}
