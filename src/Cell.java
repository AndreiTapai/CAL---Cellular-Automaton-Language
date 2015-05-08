
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Andrei
 */
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