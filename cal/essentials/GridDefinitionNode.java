package cal.essentials;

public class GridDefinitionNode extends AbstractNode {

	private String name;
	private String type;
	private int[] gridSize;

	public GridDefinitionNode(String name, String type, boolean isGridsize) {
		gridSize = new int[2];
		if (isGridsize) {
			gridSize[0] = Integer.parseInt(type.split("x")[0]);
			gridSize[1] = Integer.parseInt(type.split("x")[1]);
			this.name = name;
			this.type = "grid";
		} else {
			this.name = name;
			this.type = type;
		}
	}

	@Override
	public String toJava() {
		// output some java code that translates to either grid g is GRIDSIZE
		// or g is square (ignore other gridtypes)
	}

}