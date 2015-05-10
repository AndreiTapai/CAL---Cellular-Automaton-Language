package cal.essentials;

/**
 * This class represents a condition operator (>, >=, <, <=, !=).
 */
public class ConditionNode extends AbstractNode {

	private String sreturn;

	/**
	 * Constructor. Takes CAL conditional operator (the string name of the
	 * operator, e.g. "greater-than", "not-equals").
	 * 
	 * @param s The CAL conditional.
	 */
	public ConditionNode(String s) {
		if (s == "equals") {
			sreturn = " == ";
		} else if (s == "greater-than") {
			sreturn = " > ";
		} else if (s == "greater-equals") {
			sreturn = " >= ";
		} else if (s == "less-than") {
			sreturn = " < ";
		} else if (s == "less-equals") {
			sreturn = " <= ";
		} else if (s == "not-equals") {
			sreturn = " != ";
		}
	}

	/**
	 * toJava() method.
	 */
	@Override
	public String toJava() {
		return sreturn;
	}

}