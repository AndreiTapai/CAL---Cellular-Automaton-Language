package cal.essentials;
import java.util.ArrayList;

public class ParametersNode extends AbstractNode{

	public ArrayList<ArrayList<String>> params;

	public ParametersNode(String type, String name, ArrayList<ArrayList<String>> params){
		this.params = new ArrayList<ArrayList<String>>(2);
		ArrayList<String> types = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		this.params.add(types);
		this.params.add(names);

		if(type != null && name != null){
			this.params.get(0).add(type);
			this.params.get(1).add(name);
		}
		if(params == null)
			return;
		for(String param : params.get(0))
			this.params.get(0).add(param);
		for(String param : params.get(1))
			this.params.get(1).add(param);
	}
	public ParametersNode(String type, String name){
		this(type, name, null);
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