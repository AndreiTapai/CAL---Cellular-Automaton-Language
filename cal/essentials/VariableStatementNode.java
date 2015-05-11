package cal.essentials;

public class VariableStatementNode extends AbstractNode {

	private String sreturn;
	private VariableDeclarationNode vDec;
	private VariableDefinitionNode vDef;

	public VariableStatementNode(VariableDeclarationNode v) {
		vDec = v;
		sreturn = vDec.toJava();
	}

	public VariableStatementNode(VariableDefinitionNode v) {
		vDef = v;
		sreturn = vDef.toJava();
	}

	@Override
	public String toJava() {
		return sreturn;
	}
	public String toJavaExpression(){
		return sreturn.replace("\n", "").replace(";", "");
	}

}