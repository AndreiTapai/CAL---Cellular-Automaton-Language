package cal.essentials;
import java.math.*;

public class ExpressionNode extends AbstractNode{

	private String sreturn;
	private ExpressionNode exp1;
	private ExpressionNode exp2;
	private VariableNode var;
	private ValueNode value;
	private ArrayIndexNode aIndex;
	
	public ExpressionNode(ExpressionNode e1, String s, ExpressionNode e2){
		exp1 = e1;
		exp2 = e2;
		if (s == "^"){
			sreturn = "Math.pow(" + exp1.toJava() + ", " + exp2.toJava() + ")";  
		}
		else if (s == "FLOORDIVIDE"){
			/* need type checking here ugh */
		}
		else {
			sreturn = exp1.toJava() + " " + s + " " + exp2.toJava();
		}
		/* where do you check to make sure that it's the end of the line to add ";\n"? */
		/* UPDATE: don't think you need ; or \n here based on rules that produce expressions */
	}
	
	public ExpressionNode(VariableNode v){
		var = v;
		sreturn = var.toJava();
	}
	
	public ExpressionNode(VariableNode v, String s){
		var = v;
		if (s == "INCREMENT"){
			sreturn = var.toJava() + "++";
		}
		else if (s == "DECREMENT"){
			sreturn = var.toJava() + "--";
		}
	}
	
	public ExpressionNode(VariableNode v, ArrayIndexNode a){
		var = v;
		aIndex = a;
		sreturn = var.toJava() + "[" + aIndex.toJava() + "]";
	}
	
	public ExpressionNode(ValueNode v){
		value = v;
		sreturn = value.toJava();
	}
	
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}