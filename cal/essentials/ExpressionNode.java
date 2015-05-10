package cal.essentials;

import java.math.*;

public class ExpressionNode extends AbstractNode {

	private String sreturn;
	private ExpressionNode exp1;
	private ExpressionNode exp2;

	public ExpressionNode(ExpressionNode e1, String s, ExpressionNode e2) {
		exp1 = e1;
		exp2 = e2;
		if (s == "^") {
			sreturn = "Math.pow(" + exp1.toJava() + ", " + exp2.toJava() + ")";
		} else if (s == "FLOORDIVIDE") {
			sreturn = "Math.floor(" + exp1.toJava() + ", " + exp2.toJava() + ")";
		} else {
			sreturn = exp1.toJava() + " " + s + " " + exp2.toJava();
		}
		/*
		 * where do you check to make sure that it's the end of the line to add
		 * ";\n"?
		 */
		/*
		 * UPDATE: don't think you need ; or \n here based on rules that produce
		 * expressions
		 */
	}

	public ExpressionNode(String v) {
		sreturn = v;
	}
	public ExpressionNode(double v) {
		sreturn = String.valueOf(v);
	}
	public ExpressionNode(int v) {
		sreturn = String.valueOf(v);
	}

	@Override
	public String toJava() {
		return sreturn;
	}

}