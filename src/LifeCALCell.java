
import java.util.ArrayList;

/**
 *
 * @author Andrei
 */
public class LifeCALCell {

    int x;
    int y;
    boolean life;
    ArrayList<LifeCALCell> neighbors = new ArrayList<LifeCALCell>();

    public LifeCALCell(int x, int y) {
        this.x = x;
        this.y = y;
        java.util.Random r = new java.util.Random();
        life = r.nextBoolean();
    }

    public void addNeighbors(LifeCALCell c) {
        this.neighbors.add(c);
    }

}
