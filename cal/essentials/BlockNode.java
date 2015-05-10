package cal.essentials;

public class BlockNode extends AbstractNode {

	private String sreturn;
	private StatementsNode stmts;
	private StatementNode stmt;

	public BlockNode(StatementsNode s) {
		stmts = s;
		sreturn = "{\n" + stmts.toJava() + "}\n";
	}

	public BlockNode(StatementNode s) {
		stmt = s;
		sreturn = "{\n" + stmt.toJava() + "}\n";
	}

	public BlockNode() {
		sreturn = "{\n}\n";
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}