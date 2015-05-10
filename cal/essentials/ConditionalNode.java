package cal.essentials;

/**
 * This class represents a conditional statement.
 */
public class ConditionalNode extends AbstractNode {
	
	String sreturn;
	private ExpressionNode exp1;
	private String cond;
	private ExpressionNode exp2;
	private LogicNode logic;
	private ConditionalNode conditional;

	/**
	 * Constructor. Takes expressions and conditional operator.
	 * 
	 * @param e1 The left hand side expression.
	 * @param c The conditional operator on the two expressions.
	 * @param e2 The right hand side expression.
	 */
	public ConditionalNode(ExpressionNode e1, String c, ExpressionNode e2) {
		exp1 = e1;
		cond = c;
		exp2 = e2;
		sreturn = exp1.toJava() + cond + exp2.toJava();
	}

	/**
	 * More complex constructor. Includes NAND/NOR operators, allowing for
	 * chaining of conditional expressions operated on by ANDs/ORs.
	 * 
	 * @param e1 First boolean expression.
	 * @param c1 First conditional operator.
	 * @param e2 Second boolean expression.
	 * @param l Logical operator (NAND or NOR).
	 * @param c2 Other conditional statement.
	 */
	public ConditionalNode(ExpressionNode e1, String c1,
			ExpressionNode e2, LogicNode l, ConditionalNode c2) {

		exp1 = e1;
		cond = c1;
		exp2 = e2;
		logic = l;
		conditional = c2;
		if (logic.toString() == "NAND") {
			sreturn = "!(" + exp1.toJava() + cond + exp2.toJava()
					+ " && " + conditional.toJava() + ")";
		} else if (logic.toString() == "NOR") {
			sreturn = "!(" + exp1.toJava() + cond + exp2.toJava()
					+ " || " + conditional.toJava() + ")";
		} else {
			sreturn = exp1.toJava() + cond + exp2.toJava()
					+ logic.toJava() + conditional.toJava();
		}
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param c The ConditionalNode to copy.
	 */
	public ConditionalNode(ConditionalNode c) {
		conditional = c;
		sreturn = "!{" + conditional.toJava() + ")";
	}

	/**
	 * toJava() method.
	 */
	@Override
	public String toJava() {
		return sreturn;
	}
}