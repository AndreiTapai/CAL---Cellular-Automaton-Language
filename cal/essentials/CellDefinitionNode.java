package cal.essentials;

public class CellDefinitionNode extends AbstractNode {

	private String sreturn;
	private String type;
	private String name;

	public CellDefinitionNode(String type, String name) {
		this.type = type;
		this.name = name;
		if (type == "LIFE"){
			sreturn = "public boolean life;\n";
		}
		else{
			sreturn = "public " + type + " " + name + ";\n";
		}
	}

	/*print to FileNameCell.java*/
	@Override
	public String toJava() {
		return sreturn;
	}
}