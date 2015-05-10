package cal.essentials;

public class StatementsNode extends AbstractNode{

	
	public StatementsNode(StatementNode... nodes){

	}

	@Override 
	public String toJava(){
		return ";\n" ;
	}


}