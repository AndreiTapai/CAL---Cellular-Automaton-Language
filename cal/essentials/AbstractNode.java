package cal.essentials;

import java.util.ArrayList;

public class AbstractNode {

	ArrayList<AbstractNode> children;

	String sval = "";

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

}