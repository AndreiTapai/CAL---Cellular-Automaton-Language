import java.util.ArrayList;


public class BoilerplateMain {
    	public int gridgx = /*insert value here */;
	public int gridgy = /*insert value here */;

	public ArrayList<LifeCALCell> cells = new ArrayList<LifeCALCell>();

	public LifeCAL() {
		for (int i = 0; i < gridgx; i++)
			for (int j = 0; j < gridgy; j++) {
				cells.add(new LifeCALCell(i, j));
			}

		for (LifeCALCell cell : cells) {
			if (cell.x > 0)
				cell.addNeighbors(getNeighbors(cell.x - 1, cell.y));
			if (cell.y > 0)
				cell.addNeighbors(getNeighbors(cell.x, cell.y - 1));
			if (cell.x < gridgx - 1)
				cell.addNeighbors(getNeighbors(cell.x + 1, cell.y));
			if (cell.y < gridgy - 1)
				cell.addNeighbors(getNeighbors(cell.x, cell.y + 1));
			if (cell.x > 0 && cell.y > 0)
				cell.addNeighbors(getNeighbors(cell.x - 1, cell.y - 1));
			if (cell.x < gridgx - 1 && cell.y > 0)
				cell.addNeighbors(getNeighbors(cell.x + 1, cell.y - 1));
			if (cell.x > 0 && cell.y < gridgy - 1)                            
				cell.addNeighbors(getNeighbors(cell.x - 1, cell.y + 1));                       
			if (cell.x < gridgx - 1 && cell.y < gridgy - 1)
				cell.addNeighbors(getNeighbors(cell.x + 1, cell.y + 1));

		}
	}

	public LifeCALCell getNeighbors(int x, int y) {
		for (int i = 0; i < cells.size(); i++)
			if (cells.get(i).x == x && cells.get(i).y == y) {                                
                                System.out.println("Neighbor: " + cells.get(i).x + ", " + cells.get(i).y);
				return (LifeCALCell) cells.get(i);
			}
		return null; // Shouldn't be reachable
	}
        
/* BOILERPLATE CODE ENDS HERE! THIS IS WHERE YOU PUT THE FUNCTION FROM THE CAL 
        FILE!!
        */
        
	public int num_live_neighbors(LifeCALCell c) {
		int live = 0;
		for (LifeCALCell cell : c.neighbors) {
			if (cell.life == true) {
				live++;
			}
		}
		return live;
	}

	public void cal_it() {
		int liveneighbors = 0;

		boolean[] ctemp = new boolean[cells.size()];
                /* ctemp has to exist in all cal_it's. The function will always read from
                cells and write to ctemp, and then make cells = ctemp.
                */
		
		for(int i = 0; i < cells.size(); i ++) {
			ctemp[i] = cells.get(i).life;
		}

		for (LifeCALCell c : cells) {
			liveneighbors = num_live_neighbors(c);

			if (c.life == true && liveneighbors < 2) {
				ctemp[cells.indexOf(c)] = false;
			} else if (c.life == true && liveneighbors > 3) {
				ctemp[cells.indexOf(c)] = false;
			} else if (c.life == false && liveneighbors == 3) {
				ctemp[cells.indexOf(c)] = true;
			} else {
			}
		}

                /*Making cells equal to ctemp*/
		for(int i = 0; i < cells.size(); i ++) {
			cells.get(i).life = ctemp[i];
		}
	}
}

}
