package cal.essentials;

public class IterationStatementNode extends AbstractNode {

	private String loopName;
	private ConditionalNode cond;
	private BlockNode block;
	private ElseIfNode elseif;
	private BlockNode elseBlock;
	private ForStatementNode forStmt1;
	private ForStatementNode forStmt2;
	private IterableNode iterable;
	private IterablesNode iterables;
	String sreturn;

	public IterationStatementNode(String s, ConditionalNode c, BlockNode b) {
		if (s == "IF") {
			loopName = "if";
		} else {
			loopName = "while";
		}
		cond = c;
		block = b;
		sreturn += loopName + "(" + cond.toJava() + ")" + block.toJava();
	}

	public IterationStatementNode(ConditionalNode c, BlockNode b, ElseIfNode e) {
		loopName = "if";
		cond = c;
		block = b;
		elseif = e;
		sreturn += loopName + "(" + cond.toJava() + ")" + block.toJava()
				+ elseif.toJava();
	}

	public IterationStatementNode(ConditionalNode c, BlockNode b, BlockNode b2) {
		loopName = "if";
		cond = c;
		block = b;
		elseBlock = b2;
		sreturn += loopName + "(" + cond.toJava() + ")" + block.toJava()
				+ "else" + elseBlock.toJava();
	}

	public IterationStatementNode(ConditionalNode c, BlockNode b, ElseIfNode e,
			BlockNode b2) {
		loopName = "if";
		cond = c;
		block = b;
		elseif = e;
		elseBlock = b2;
		sreturn += loopName + "(" + cond.toJava() + ")" + block.toJava()
				+ elseif.toJava() + "else" + elseBlock.toJava();
	}

	public IterationStatementNode(ForStatementNode fs1, ConditionalNode c,
			ForStatementNode fs2, BlockNode b) {
		loopName = "for";
		forStmt1 = fs1;
		cond = c;
		forStmt2 = fs2;
		block = b;
		sreturn += loopName + "(" + forStmt1.toJava() + "; " + cond.toJava()
				+ "; " + forStmt2.toJava() + ")" + block.toJava();
	}

	public IterationStatementNode(IterableNode it, IterablesNode its, BlockNode b) {
		loopName = "for";
		iterable = it;
		iterables = its;
		block = b;
		sreturn += loopName + "(" + it.toJava() + " : " + its.toJava() + ")"
				+ block.toJava();
	}

	@Override
	public String toJava() {
		return sreturn;
	}
}