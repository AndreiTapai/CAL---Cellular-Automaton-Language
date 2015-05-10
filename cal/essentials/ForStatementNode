package cal.essentials;

public class ForStatementNode extends AbstractNode{

	private String sreturn;
	private VariableStatementNode varStmt;
	private VariableNode var;
	
	public ForStatementNode(VariableStatementNode vs){
		varStmt = vs;
		sreturn = varStmt.toJava();
	}
	
	public ForStatementNode(VariableNode v, String s){
		var = v;
		if (s == "INCREMENT"){
			sreturn = var.toJava() + "++";
		}
		else if (s == "DECREMENT"){
			sreturn = var.toJava() + "--";
		}
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}