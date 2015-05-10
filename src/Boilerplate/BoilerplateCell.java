
package Boilerplate;

import java.util.ArrayList;


public class BoilerplateCell {
    /* The below are standard attributes of all Cells: x, y, life, and neighbors */
	public int x;
	public int y;
	public boolean life;
	public ArrayList<LifeCALCell> neighbors = new ArrayList<LifeCALCell>();

	public LifeCALCell(int x, int y) {
		this.x = x;
		this.y = y;
                /*Random function inserted according to what is needed */
		java.util.Random r = new java.util.Random();
		life = r.nextBoolean();
	}
        //standard function in all Cell classes
	public void addNeighbors(LifeCALCell c) {
		this.neighbors.add(c);
	}

}
