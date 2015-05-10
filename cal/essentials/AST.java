package cal.essentials;

/**
 * Abstract Syntax Tree class. Represents the entire abstract syntax tree for
 * a CAL program.
 */
public class AST {
	
	// the root node
	private AbstractNode root;

	/**
	 * Constructor. Initializes root with a provided Node.
	 * 
	 * @param node
	 */
	public AST(AbstractNode node) {
		root = node;
	}

	/**
	 * toString() method for the AST.
	 * 
	 * @return The Java code string for the entire AST.
	 */
	public String toString() {
		return root.toString();
	}
}