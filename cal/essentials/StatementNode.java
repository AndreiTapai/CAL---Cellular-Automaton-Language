package cal.essentials;

public class StatementNode extends AbstractNode {

	public StatementNode(StatementNode... nodes) {

	}

	@Override
	public String toJava() {
		return ";\n";
	}

}