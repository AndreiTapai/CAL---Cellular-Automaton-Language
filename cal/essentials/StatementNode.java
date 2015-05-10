package cal.essentials;

public class StatementNode extends AbstractNode {

	String sreturn;
	public StatementNode(HeaderStatementNode node) {
		sreturn = node.toJava();
	}
	public StatementNode(VariableStatementNode node) {
		sreturn = node.toJava();
	}
	public StatementNode(FunctionStatementNode node) {
		sreturn = node.toJava();
	}
	public StatementNode(String continuation) {
		sreturn = continuation + ";\n";
	}
	public StatementNode(ExpressionStatementNode node) {
		sreturn = node.toJava();
	}
	public StatementNode(IterationStatementNode node) {
		sreturn = node.toJava();
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}