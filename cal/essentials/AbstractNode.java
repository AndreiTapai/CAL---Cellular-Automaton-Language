package cal.essentials;

import java.util.ArrayList;

public class AbstractNode {

	ArrayList<AbstractNode> children;

	String sval = "";

	public AbstractNode() {
		children = new ArrayList<AbstractNode>();
	}

	public AbstractNode(StatementsNode node) {
		children = new ArrayList<AbstractNode>();
	}

	public String toString() {
		for (AbstractNode child : children)
			sval += child.toString();

		sval += toJava();

		return sval;
	}

	public String toJava() {
		return null;
	}

	public AbstractNode getLeft() {
		if (children == null || children.size() == 0) {
			return null;
		}

		return children.get(0);
	}

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

	public AbstractNode getRight() {
		if (children == null || children.size() < 2) {
			return null;
		}

		return children.get(children.size() - 1);
	}

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