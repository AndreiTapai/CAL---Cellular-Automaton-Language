package cal.essentials;
import java.util.ArrayList;

public class StatementsNode extends AbstractNode{

	public ArrayList<StatementNode> statements;

	public StatementsNode(StatementNode node, ArrayList<StatementNode> statements){
		this.statements = new ArrayList<StatementNode>();
		this.statements.add(node);
		for(StatementNode statement: statements)
			this.statements.add(statement);
	}
	public StatementsNode(StatementNode node){
		statements = new ArrayList<StatementNode>();
		statements.add(node);
	}

	@Override 
	public String toJava(){
		String sval = "";
		for(StatementNode statement: statements)
			sval += statement.toJava();
		return sval ;
	}


}