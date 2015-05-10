package cal.essentials;

public class ExpressionStatementNode extends AbstractNode{

	private String sreturn;
	private VariableNode var;
	private ValueNode value;
	
	public ExpressionStatementNode(VariableNode v){
		var = v;
		sreturn = var.toJava();
	}
	
	public ExpressionStatementNode(VariableNode v, String s){
		var = v;
		if (s == "INCREMENT"){
			sreturn = var.toJava() + "++";
		}
		else if (s == "DECREMENT"){
			sreturn = var.toJava() + "--";
		}
	}
	
	public ExpressionStatementNode(ValueNode v){
		value = v;
		sreturn = value.toJava();
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}