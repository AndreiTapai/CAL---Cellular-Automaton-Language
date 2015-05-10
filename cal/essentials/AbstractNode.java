package cal.essentials;

import java.util.ArrayList;

/**
 * Abstract Node class. Superclass for all other Node types.
 */
public abstract class AbstractNode {

	ArrayList<AbstractNode> children;

	String sval = "";

	/**
	 * Default constructor.
	 */
	public AbstractNode() {
		children = new ArrayList<AbstractNode>();
	}

	/**
	 * toString() method. Provides basic toString() functionality for nodes,
	 * concatenating children's toString()s with the Java code for this node.
	 * 
	 * @return The Java code for the subtree rooted at this node.
	 */
	public String toString() {
		for (AbstractNode child : children)
			sval += child.toString();

		sval += toJava();

		return sval;
	}

	/**
	 * Essentially the toString() method for this node only.
	 * 
	 * @return The Java code associated with this node.
	 */
	public abstract String toJava();

	/**
	 * Returns the "left child" of this node.
	 * 
	 * @return The leftmost child of this node.
	 */
	public AbstractNode getLeft() {
		if (children == null || children.size() == 0) {
			return null;
		}

		return children.get(0);
	}

	/**
	 * Setter method for the left child of this node.
	 * 
	 * @param left The node to set the leftmost child of this node to.
	 */
	public void setLeft(AbstractNode left) {
		if (children == null) {
			children = new ArrayList<AbstractNode>();
		}

		if (children.size() == 0) {
			children.add(left);
		}

		else {
			children.set(0, left);
		}
	}

	/**
	 * Getter method for the "right child" of this node.
	 * 
	 * @return The rightmost child of this node, if there are at least two
	 * children.
	 */
	public AbstractNode getRight() {
		if (children == null || children.size() < 2) {
			return null;
		}

		return children.get(children.size() - 1);
	}

	/**
	 * Setter method for the right child of this node.
	 * 
	 * @param right The node to set the rightmost child of this node to.
	 */
	public void setRight(AbstractNode right) {
		if (children == null) {
			children = new ArrayList<AbstractNode>();
		}

		if (children.size() == 0) {
			children.set(0, null);
			children.set(1, right);
		}

		else if (children.size() == 1) {
			children.add(right);
		}

		else {
			children.set(children.size() - 1, right);
		}
	}

}