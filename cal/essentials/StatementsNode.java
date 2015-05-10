package cal.essentials;

public class StatementsNode extends AbstractNode{

	private String sreturn;
	private StatementsNode stmts;
	private StatementNode stmt;
	
	public StatementsNode (StatementNode s){
		stmt = s;
		sreturn = stmt.toJava();
	}
	
	public StatementsNode (StatementNode s, Statements Node s1){
		stmt = s;
		stmts = s1;
		sreturn = stmt.toJava() + stmts.toJava();
	}

	@Override 
	public String toJava(){
		return sreturn;
	}


}