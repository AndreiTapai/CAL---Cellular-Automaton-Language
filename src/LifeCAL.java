
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Andrei
 */
public class LifeCAL {
    public int gridgx = 10;
    public int gridgy = 10;
    
    ArrayList<Cell> cells = new ArrayList<Cell>();
    
    public LifeCAL() {
        for (int i = 0; i < gridgx; i++)
            for (int j = 0; j < gridgy; j++)
            {
                cells.add(new Cell(i, j));
            }
        
        for (Cell cell : cells)
        {
                if (cell.x > 0)
                    cell.neighbors.add(getNeighbors(cell.x-1, cell.y));
                if (cell.y > 0)
                    cell.neighbors.add(getNeighbors(cell.x, cell.y-1));
                if (cell.x < gridgx-1)
                    cell.neighbors.add(getNeighbors(cell.x+1, cell.y));
                if (cell.y < gridgy-1)
                    cell.neighbors.add(getNeighbors(cell.x, cell.y+1));
                if (cell.x > 0 && cell.y > 0)
                    cell.neighbors.add(getNeighbors(cell.x-1, cell.y-1));
                if (cell.x < gridgx-1 && cell.y > 0)
                    cell.neighbors.add(getNeighbors(cell.x+1, cell.y-1));
                if (cell.x < 0 && cell.y > gridgy-1)
                    cell.neighbors.add(getNeighbors(cell.x-1, cell.y+1));
                if (cell.x < gridgx-1 && cell.y < gridgy-1)
                    cell.neighbors.add(getNeighbors(cell.x+1, cell.y+1));
                               
					
        }
    }
    
    public Cell getNeighbors(int x, int y)
    {
        for (int i = 0; i < cells.size(); i++)
            if (cells.get(i).x == x && cells.get(i).y == y) {
                return (Cell) cells.get(i);
            }
        return null; //Shouldn't be reachable
    }
    
        public int num_live_neighbors(Cell c)
        {
            int live = 0;
            for (Cell cell : c.neighbors)
            {
                if (cell.life == true)
                {
                    live++;
                }
            }
            return live;
        }
        
        public void cal_it()
        {
            int liveneighbors = 0;
            for (Cell c : cells)
            {
                liveneighbors = num_live_neighbors(c);

                if(c.life == true && liveneighbors < 2)
                {
                    c.life = false;
                }
                else if(c.life == true && liveneighbors > 3)
                {
                    c.life = false;
                }
                else if(c.life == false && liveneighbors == 3)
                {
                    c.life = true;
                }
                else
                {
                }
            }
        }
        
    public class Cell {
        int x;
        int y;
        boolean life;
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        
        
        public Cell(int x, int y)
        {
            this.x = x;
            this.y = y;
            Random r = new Random();
            life = r.nextBoolean();
        }
        
        public void addNeighbors(Cell c)
        {
            this.neighbors.add(c);
        }
        
    }
}
