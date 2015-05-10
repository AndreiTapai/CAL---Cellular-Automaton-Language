package cal.essentials;

public class CellDefinitionNode extends AbstractNode {

	private String sreturn;
	private String type;

	public CellDefinitionNode(String type) {
		this.type = type;
		if (type == "LIFE"){
			sreturn = "public boolean life;\n";
		}
		else{
			sreturn = "public " + /*type here*/ + type + ";\n";
		}
	}

	/*print to FileNameCell.java*/
	@Override
	public String toJava() {
		return sreturn;
	}
}