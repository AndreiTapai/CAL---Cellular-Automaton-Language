package cal.essentials;
import java.util.ArrayList;

public class ParametersNode extends AbstractNode{

	private ArrayList<ArrayList<String>> params;

	public ParametersNode(String... args){
		params = new ArrayList<ArrayList<String>>(2);

		String arg;
		for(int i = 0, index = 0; i < args.size(); i++){
			if(arg.equals(",")){
				index = 0;
				continue;
			}
			params.get(index).append(arg);
			index++;
		}
	}
	@Override
	public String toString(){

	}
	@Override 
	public String toJava(){
		String sval = "";

		for(int i = 0; i < params[0].size(); i++){
			sval += params.get(0).get(i);
			sval += " ";
			sval += params.get(1).get(i);
			sval += ",";
		}
		if(sval[sval.length - 1] == ',')
			sval.remove(sval.length -1);

		return sval;
	}


}