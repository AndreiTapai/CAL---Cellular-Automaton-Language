package cal.essentials;

public class VariableStatementNode extends AbstractNode{

	private String sreturn;
	private VariableDeclarationNode vDec;
	private VariableDefinitionNode vDef;
	
	public FunctionStatementNode(VariableDeclarationNode v){
		vDec = v;
		sreturn = vDec.toJava();
	}
	
	public FunctionStatementNode(VariableDefinitionNode v){
		vDef = v;
		sreturn = vDef.toJava();
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}