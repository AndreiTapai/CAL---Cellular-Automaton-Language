package cal.essentials;

public class FunctionDeclarationNode extends AbstractNode{


	private String type;
	private String name;
	private ParametersNode params;
	private FunctionBlockNode block;
	//from production functionStatement --> functionDeclaration
	public FunctionDeclarationNode(String type, String name, ParametersNode params, FunctionBlockNode block){
		this.type = type;
		this.name = name;
		this.params = params;
		this.block = block;
	}
	@Override 
	public String toJava(){
		return "public static " + type + " " + name + '(' + params.toJava() + ')' + block.toJava();
	}


}