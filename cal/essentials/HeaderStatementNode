package cal.essentials;

public class HeaderStatementNode extends AbstractNode{

	private String sreturn;
	private GridDefinitionNode gdef;
	private CellDefinitionNode cdef;
	
	public HeaderStatementNode(GridDefinitionNode g){
		gdef = g;
		sreturn = gdef.toJava();
	}
	
	public HeaderStatementNode(CellDefinitionNode c){
		cdef = c;
		sreturn = cdef.toJava();
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}