package cal.essentials;

public class ExpressionStatementNode extends AbstractNode {

	private String sreturn;

	public ExpressionStatementNode(String expression) {
		sreturn = expression + ';';
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}