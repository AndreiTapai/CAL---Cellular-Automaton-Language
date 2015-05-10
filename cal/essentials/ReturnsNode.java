package cal.essentials;

public class ReturnsNode extends AbstractNode{

	private String sval;

	
	public ReturnsNode(String value){
		sval = value;
	}
	public ReturnsNode(double value){
		sval = String.parseString(value);
	}
	public ReturnsNode(int value){
		sval = String.parseString(value);
	}
	public ReturnsNode(char value){
		sval = String.parseString(value);
	}

	@Override 
	public String toJava(){
		return "return " + sval + ";\n" ;
	}


}