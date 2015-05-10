package cal.essentials;

import java.math.*;

/**
 * This class represents an expression.
 */
public class ExpressionNode extends AbstractNode {

	private String sreturn;
	private ExpressionNode exp1;
	private ExpressionNode exp2;

	/**
	 * Combination constructor. Takes two other ExpressionNodes, and a
	 * combining operator.
	 * 
	 * @param e1 The left hand side expression.
	 * @param s The operator.
	 * @param e2 The right hand side expression.
	 */
	public ExpressionNode(ExpressionNode e1, String s, ExpressionNode e2) {
		exp1 = e1;
		exp2 = e2;
		if (s == "^") {
			sreturn = "Math.pow(" + exp1.toJava() + ", " + exp2.toJava() + ")";
		} else if (s == "FLOORDIVIDE") {
			sreturn = "Math.floor(" + exp1.toJava() + ", " + exp2.toJava()
					+ ")";
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

	/**
	 * String constructor.
	 * 
	 * @param v The string value of this expression.
	 */
	public ExpressionNode(String v) {
		sreturn = v;
	}

	/**
	 * Double constructor.
	 * 
	 * @param v The double value of this expression.
	 */
	public ExpressionNode(double v) {
		sreturn = String.valueOf(v);
	}

	/**
	 * Int constructor.
	 * 
	 * @param v The int value of this expression.
	 */
	public ExpressionNode(int v) {
		sreturn = String.valueOf(v);
	}

	/**
	 * toJava() method.
	 */
	@Override
	public String toJava() {
		return sreturn;
	}

}