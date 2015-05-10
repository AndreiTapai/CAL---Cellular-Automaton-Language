package cal.essentials;
import java.util.ArrayList;

public class ParametersNode extends AbstractNode{

	private ArrayList<ArrayList<String>> params;
	public String[] baseParams;

	public ParametersNode(String type, String name, String[]...args){
		params = new ArrayList<ArrayList<String>>(2);
		ArrayList<String> types = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		baseParams = new String[2];
		baseParams[0] = type;
		baseParams[1] = name;
		params.add(types);
		params.add(names);

		if(type != null && name != null){
			params.get(0).add(type);
			params.get(1).add(name);
		}
		for(String[] arg : args){
			params.get(0).add(arg[0]);
			params.get(1).add(arg[1]);
		}
	}
	public ParametersNode(){
		this(null, null);
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
		if(sval.length() > 0 && sval.charAt(sval.length() - 1) == ',')
			sval = new String(sval.substring(0, sval.length()-1));

		return sval;
	}


}