package cal.essentials;

public class IterationStatementNode extends AbstractNode {

	private String loopName;
	private String cond;
	private BlockNode block;
	private String elseif;
	private BlockNode elseBlock;
	private String forStmt1;
	private String forStmt2;
	private String iteration;
	String sreturn;

	public IterationStatementNode(String s, String c, BlockNode b) {
		if (s == "IF") {
			loopName = "if";
		} else {
			loopName = "while";
		}
		cond = c;
		block = b;
		sreturn += loopName + "(" + cond + ")" + block.toJava();
	}

	public IterationStatementNode(String c, BlockNode b, String e) {
		loopName = "if";
		cond = c;
		block = b;
		elseif = e;
		sreturn += loopName + "(" + cond + ")" + block.toJava()
				+ elseif;
	}

	public IterationStatementNode(String c, BlockNode b, BlockNode b2) {
		loopName = "if";
		cond = c;
		block = b;
		elseBlock = b2;
		sreturn += loopName + "(" + cond + ")" + block.toJava()
				+ "else" + elseBlock.toJava();
	}

	public IterationStatementNode(String c, BlockNode b, String e,
			BlockNode b2) {
		loopName = "if";
		cond = c;
		block = b;
		elseif = e;
		elseBlock = b2;
		sreturn += loopName + "(" + cond + ")" + block.toJava()
				+ elseif + "else" + elseBlock.toJava();
	}

	public IterationStatementNode(String fs1, String c, String fs2,
			BlockNode b) {
		loopName = "for";
		forStmt1 = fs1;
		cond = c;
		forStmt2 = fs2;
		block = b;
		sreturn += loopName + "(" + forStmt1 + "; " + cond + "; "
				+ forStmt2 + ")" + block.toJava();
	}

	public IterationStatementNode(String iteration, BlockNode b) {
		this.iteration = iteration;
		loopName = "for";
		block = b;
		sreturn += loopName + "(" + iteration + ")" + block.toJava();
	}

	@Override
	public String toJava() {
		return sreturn;
	}
}