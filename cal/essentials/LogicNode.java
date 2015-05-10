package cal.essentials;

public class LogicNode extends AbstractNode {

	private String sreturn;
	private String logicOp;

	public LogicNode(String s) {
		logicOp = s;
		if (s == "AND") {
			sreturn = " && ";
		}
		if (s == "OR") {
			sreturn = " || ";
		}
		if (s == "XOR") {
			sreturn = " ^ ";
		}
		/*
		 * NAND and NOR handled by function which calls them--they require
		 * different syntax than just converting to a given symbol
		 */
	}

	@Override
	public String toString() {
		return logicOp;
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}