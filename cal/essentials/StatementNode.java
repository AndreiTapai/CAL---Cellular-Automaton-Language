package cal.essentials;

public class StatementNode extends AbstractNode {

	public StatementNode(HeaderStatementNode node) {

	}
	public StatementNode(VariableStatementNode node) {

	}
	public StatementNode(FunctionStatementNode node) {

	}
	public StatementNode(ContinuationNode node) {

	}
	public StatementNode(ExpressionStatementNode node) {

	}
	public StatementNode(IterationNode node) {

	}

	@Override
	public String toJava() {
		return ";\n";
	}

}