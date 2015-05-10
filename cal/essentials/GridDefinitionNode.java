package cal.essentials;

public class GridDefinitionNode extends AbstractNode {

	private String name;
	private String type;
	private int[] gridSize;

	public GridDefinitionNode(String name, String type) {
		gridSize = new int[2];		
                gridSize[0] = Integer.parseInt(type.split("x")[0]);
		gridSize[1] = Integer.parseInt(type.split("x")[1]);
                
		this.name = name;
		this.type = "grid";
	}

	@Override
	public String toJava() {
		String javaCode = "";
		
		javaCode = "public int gridgx = " + gridSize[0] + ";" + "\n"
			 + "public int gridgy = " + gridSize[1] + ";" + "\n";
		
		return javaCode;
	}

}