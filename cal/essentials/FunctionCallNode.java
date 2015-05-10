package cal.essentials;

public class FunctionCallNode extends AbstractNode {

	private String sreturn;

	public FunctionCallNode(String name, String params) {
		sreturn = name + "(" + params + ")";
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}