package cal.essentials;

/**
 * This class represents a definition of a Cell variable.
 */
public class CellDefinitionNode extends AbstractNode {
	private String sreturn;
	private String type;
	private String name;
	
	/**
	 * Constructor. Takes a variable type and name and returns the appropriate
	 * Java code (which will go within the definition of the Cell Java class).
	 * 
	 * @param type The type of the variable (may be the reserved keyword LIFE,
	 * or any CAL type.)
	 * @param name The name of the variable.
	 */
	public CellDefinitionNode(String type, String name) {
		this.type = type;
		this.name = name;
		if (type == "LIFE") {
			sreturn = "public boolean life;\n";
		} else {
			sreturn = "public " + type + " " + name + ";\n";
		}
	}

	/**
	 * toJava() method.
	 */
	/* print to FileNameCell.java */
	@Override
	public String toJava() {
		return sreturn;
	}
}