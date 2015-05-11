package cal.essentials;

/**
 * This class represents a continuation keyword (i.e. "continue" or "break").
 */
public class ContinuationNode extends AbstractNode {

	private String sreturn;

	/**
	 * Constructor. Takes the CAL version of the keyword.
	 * 
	 * @param s The CAL keyword.
	 */
	public ContinuationNode(String s) {
		if (s.equals("CONTINUE")) {
			sreturn = "continue;\n";
		} else if (s.equals("BREAK")) {
			sreturn = "break;\n";
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