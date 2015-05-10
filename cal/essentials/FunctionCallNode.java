package cal.essentials;

public class FunctionCallNode extends AbstractNode {

	private String sreturn;

	public FunctionCallNode(String s, ActualsNode a) {
		sreturn = s + "(" + a.toJava + ")";
	}

	public FunctionCallNode(RandomActualsNode r) {
		sreturn = "random(" + r.toJava() + ")";
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}