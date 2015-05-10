package cal.essentials;

public class ElseIfNode extends AbstractNode{

	private String sreturn;
	private ConditionalNode cond;
	private BlockNode block;
	private ElseIfNode elseif;
	
	public ElseIfNode(ConditionalNode c, BlockNode b){
		cond = c;
		block = b;
		sreturn = "else if(" + cond.toJava() + ")" + block.toJava();
	}
	
	public ElseIfNode(ConditionalNode c, BlockNode b, ElseIfNode e){
		cond = c;
		block = b;
		elseif = e;
		sreturn = "else if(" + cond.toJava() + ")" + block.toJava() + elseif.toJava();
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}