package cal.essentials;

public class ActualsNode extends AbstractNode {

	private String sreturn;

	public ActualsNode(VariableNode v) {
		sreturn = v.toJava();
	}

	public ActualsNode(VariableNode v, ActualsNode a) {
		sreturn = v.toJava() + ", " + a.toJava();
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}