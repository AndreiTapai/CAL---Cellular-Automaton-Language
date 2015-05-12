package cal.essentials;

public class LogicNode extends AbstractNode {

	private String sreturn;
	private String logicOp;

	public LogicNode(String s) {
		logicOp = s;
		if (s.equals("and")) {
			sreturn = " && ";
		}
		if (s.equals("or")) {
			sreturn = " || ";
		}
		if (s.equals("xor")) {
			sreturn = " ^ ";
		}
		/*
		 * NAND and NOR handled by function which calls them--they require
		 * different syntax than just converting to a given symbol
		 */
	}

	/*
	 * toString() returns the LogicNode's sval, which was passed in the constructor.
	 * It does NOT return the Java code--this is so we can handle nand/nor and or/xor/and
	 * differently. toString just returns "and", "or", etc.
	 */
	@Override
	public String toString() {
		return logicOp;
	}

	/*
	 * toJava() returns the Java code--"&&", "||", etc. This does not handle nand/nor
	 * which have different formats. These are handled by calling toString() in 
	 * the ConditionalNode constructor.
	 */
	@Override
	public String toJava() {
		return sreturn;
	}

}