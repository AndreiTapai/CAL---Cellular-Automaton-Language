package cal.essentials;

public class VariableDefinitionNode extends AbstractNode {

	private String sreturn, name, op;
	private ExpressionNode enode;
	private FunctionCallNode fnode;

	public VariableDefinitionNode(String name, String op, ExpressionNode node) {
		this.name = name;
		this.op = op;
		enode = node;
		fnode = null;
		sreturn = name + " " + op + " " + node.toJava() + ";\n";
	}

	public VariableDefinitionNode(String name, String op, FunctionCallNode node) {
		this.name = name;
		this.op = op;
		enode = null;
		fnode = node;
		sreturn = name + op + node.toJava() + ";\n";
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}