package cal.essentials;

public class AST {

	private AbstractNode root;

	public AST(AbstractNode node) {
		root = node;
	}

	public void addNode(AbstractNode node) {
		if (root.getLeft() == null)
			root.setLeft(node);
		else if (root.getRight() == null)
			root.setRight(node);

		addNode(root.getLeft());
		addNode(root.getRight());
	}
	
	public String toString() {
		return root.toString();
	}
}