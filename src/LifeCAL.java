import java.util.ArrayList;

/**
 *
 * @author Andrei
 */
public class LifeCAL {
	public int gridgx = 50;
	public int gridgy = 50;

	public ArrayList<LifeCALCell> cells = new ArrayList<LifeCALCell>();

	public LifeCAL() {
		for (int i = 0; i < gridgx; i++)
			for (int j = 0; j < gridgy; j++) {
				cells.add(new LifeCALCell(i, j));
			}

		for (LifeCALCell cell : cells) {
			System.out.println("Initial Cell: " + cell.x + ", " + cell.y);
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
				System.out.println("Neighbor: " + cells.get(i).x + ", "
						+ cells.get(i).y);
				return (LifeCALCell) cells.get(i);
			}
		return null; // Shouldn't be reachable
	}

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

		for (int i = 0; i < cells.size(); i++) {
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

		for (int i = 0; i < cells.size(); i++) {
			cells.get(i).life = ctemp[i];
		}
	}
}
