package cal.essentials;

public class StatementsNode extends AbstractNode{

	ArrayList<StatementNode> statements;

	public StatementsNode(StatementNode node, ArrayList<StatementNode> statements){
		this.statements = new ArrayList<StatementNode>();
		this.statements.add(node);
		for(StatementsNode statement: statements)
			this.statements.add(statement);
	}

	@Override 
	public String toJava(){
		String sval = "";
		for(StatementsNode statement: statements)
			sval += statement.toJava();
		return ";\n" ;
	}


}