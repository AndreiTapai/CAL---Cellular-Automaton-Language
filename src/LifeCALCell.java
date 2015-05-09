import java.util.ArrayList;

/**
 *
 * @author Andrei
 */
public class LifeCALCell {

	public int x;
	public int y;
	public boolean life;
	public ArrayList<LifeCALCell> neighbors = new ArrayList<LifeCALCell>();

	public LifeCALCell(int x, int y) {
		this.x = x;
		this.y = y;
		java.util.Random r = new java.util.Random();
		life = r.nextBoolean();
	}
	
	public LifeCALCell(LifeCALCell c) {
		this.x = c.x;
		this.y = c.y;
		this.life = c.life;
	}

	public void addNeighbors(LifeCALCell c) {
		this.neighbors.add(c);
	}

}
