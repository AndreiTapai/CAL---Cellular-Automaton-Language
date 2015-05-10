package cal.essentials;
import java.util.ArrayList;

public class ParametersNode extends AbstractNode{

	private ArrayList<ArrayList<String>> params;

	public ParametersNode(String... args){
		params = new ArrayList<ArrayList<String>>(2);

		String arg;
		for(int i = 0, index = 0; i < args.length; i++){
			arg = args[i];
			if(arg.equals(",")){
				index = 0;
				continue;
			}
			params.get(index).add(arg);
			index++;
		}
	}
	@Override
	public String toString(){

	}
	@Override 
	public String toJava(){
		String sval = "";

		for(int i = 0; i < params.get(0).size(); i++){
			sval += params.get(0).get(i);
			sval += " ";
			sval += params.get(1).get(i);
			sval += ",";
		}
		if(sval.charAt(sval.length() - 1) == ',')
			sval = new String(sval.substring(0, sval.length()-1));

		return sval;
	}


}