package cal.essentials;

public class AST {

	private AbstractNode root;

	public AST(AbstractNode node) {
		root = node;
	}

	public Node addNode(AbstractNode node) {
		if (root.left == null)
			root.left = node;
		else if (root.right == null)
			root.right = node;

		addNode(root.left);
		addNode(root.right);
	}

}