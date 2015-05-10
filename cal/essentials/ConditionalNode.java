package cal.essentials;

public class ConditionalNode extends AbstractNode{
	
	String sreturn;
	private ExpressionNode exp1;
	private ConditionNode cond;
	private ExpressionNode exp2;
	private LogicNode logic;
	private ConditionalNode conditional;
	
	public ConditionalNode(ExpressionNode e1, ConditionNode c, ExpressionNode e2){
		
		exp1 = e1;
		cond = c;
		exp2 = e2;
		sreturn = exp1.toJava() + cond.toJava() + exp2.toJava();
	}
	
	public ConditionalNode(ExpressionNode e1, ConditionNode c1, ExpressionNode e2, LogicNode l, ConditionalNode c2){
		
		exp1 = e1;
		cond = c;
		exp2 = e2;
		logic = l;
		conditional = c2;
		if (logic.toString() == "NAND"){
			sreturn = "!(" + exp1.toJava() + cond.toJava() + exp2.toJava() + " && " + conditional.toJava() + ")";
		}
		else if (logic.toString() == "NOR"){
			sreturn = "!(" + exp1.toJava() + cond.toJava() + exp2.toJava() + " || " + conditional.toJava() + ")";
		}
		else{
			sreturn = exp1.toJava() + cond.toJava() + exp2.toJava() + logic.toJava() + conditional.toJava();
		}
	}
	
	public ConditionalNode(ConditionalNode c){
		conditional = c;
		sreturn = "!{" + conditional.toJava + ")";
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
}