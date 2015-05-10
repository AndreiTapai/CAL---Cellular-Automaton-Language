package cal.essentials;

public class ConditionNode extends AbstractNode{

	private String sreturn;
	
	public ConditionNode(String s){
		if (s == "equals"){
			sreturn = " == ";
		}
		else if (s == "greater-than"){
			sreturn = " > ";
		}
		else if (s == "greater-equals"){
			sreturn = " >= ";
		}
		else if (s == "less-than"){
			sreturn = " < ";
		}
		else if (s == "less-equals"){
			sreturn = " <= ";
		}
		else if (s == "not-equals"){
			sreturn = " != ";
		}
	}
	
	@Override
	public String toJava(){
		return sreturn;
	}
	
}