package cal.essentials;

public class FunctionStatementNode extends AbstractNode{

	private String sreturn;
	private FunctionDeclarationNode fDec;
	private FunctionCallNode fCall;
	
	public FunctionStatementNode(FunctionDeclarationNode f){
		fDec = f;
		sreturn = fDec.toJava();
	}
	
	public FunctionStatementNode(FunctionCallNode f){
		fCall = f;
		sreturn = fCall.toJava();
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}