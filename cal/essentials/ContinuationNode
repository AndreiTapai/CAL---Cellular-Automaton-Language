package cal.essentials;

public class ContinuationNode extends AbstractNode{

	private String sreturn;
	
	public ContinuationNode(String s){
		if (s == "CONTINUE"){
			sreturn = "continue;\n";
		}
		else if (s == "BREAK"){
			sreturn = "break;\n";
		}
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}