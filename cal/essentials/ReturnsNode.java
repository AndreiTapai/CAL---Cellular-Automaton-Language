package cal.essentials;

public class ReturnsNode extends AbstractNode {

	private String sval;

	public ReturnsNode(String value) {
		sval = value;
	}

	public ReturnsNode(double value) {
		sval = String.valueOf(value);
	}

	public ReturnsNode(int value) {
		sval = String.valueOf(value);
	}

	public ReturnsNode(char value) {
		sval = String.valueOf(value);
	}

	@Override
	public String toJava() {
		return "return " + sval + ";\n";
	}

}