package cal.essentials;

public class FunctionBlockNode extends AbstractNode {

	private StatementsNode snode;
	private ReturnsNode rnode;

	public FunctionBlockNode(StatementsNode snode, ReturnsNode rnode) {
		this.snode = snode;
		this.rnode = rnode;
	}

	public FunctionBlockNode(StatementsNode snode) {
		this.snode = snode;
		this.rnode = null;
	}

	public FunctionBlockNode(ReturnsNode rnode) {
		this.snode = null;
		this.rnode = rnode;
	}

	public FunctionBlockNode() {
		this.snode = null;
		this.rnode = null;
	}

	@Override
	public String toJava() {
		if (snode != null && rnode != null)
			return "{\n" + snode.toJava() + rnode.toJava() + "}\n";
		else if (snode != null)
			return "{\n" + snode.toJava() + "}\n";
		else if (rnode != null)
			return "{\n" + rnode.toJava() + "}\n";
		else
			return "{" + "}\n";
	}

}