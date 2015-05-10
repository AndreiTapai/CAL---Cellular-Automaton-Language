package cal.essentials;

/**
 * This class represents a block of code.
 */
public class BlockNode extends AbstractNode {
	private String sreturn;
	private StatementsNode stmts;
	private StatementNode stmt;
	
	/**
	 * Constructor. Takes the Node for a series of statements.
	 * 
	 * @param s The StatementsNode representing the statements within this
	 * block.
	 */
	public BlockNode(StatementsNode s) {
		stmts = s;
		sreturn = "{\n" + stmts.toJava() + "}\n";
	}
	
	/**
	 * Constructor. Takes the Node for a single statement.
	 * 
	 * @param s The StatementNode representing the statement in this block.
	 */
	public BlockNode(StatementNode s) {
		stmt = s;
		sreturn = "{\n" + stmt.toJava() + "}\n";
	}

	/**
	 * Constructor for an empty block.
	 */
	public BlockNode() {
		sreturn = "{\n}\n";
	}

	/**
	 * toJava() method. Returns the code for this block.
	 */
	@Override
	public String toJava() {
		return sreturn;
	}

}