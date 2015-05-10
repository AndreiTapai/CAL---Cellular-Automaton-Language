package cal.essentials;

public class VariableDeclarationNode extends AbstractNode {

	private String sreturn;

	public VariableDeclarationNode(String declaration) {
		sreturn = declaration;
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}