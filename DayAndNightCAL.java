import java.util.ArrayList;
import java.util.Random;

public class DayAndNightCAL {
public ArrayList<Cell> cells = new ArrayList<Cell>();
public DayAndNightCAL() {
for (int i = 0; i < gridgx; i++)
for (int j = 0; j < gridgy; j++) {
cells.add(new Cell(i, j));
}
for (Cell cell : cells) {
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
public Cell getNeighbors(int x, int y) {
for (int i = 0; i < cells.size(); i++)
if (cells.get(i).x == x && cells.get(i).y == y) {
System.out.println("Neighbor: " + cells.get(i).x + ", " + cells.get(i).y);
return (Cell) cells.get(i);
}
return null; // Shouldn't be reachable
}
public int gridgx = 50;
public int gridgy = 50;
public boolean life;
public int num_live_neighbors(Cell cell){
int live = 0;
for(Cell neighbor : cell.neighbors){
if(neighbor.life == true){
live++;}
}
return live;
}
public void cal_it(){
int liveneighbors = 0;
for(Cell cell : cells){
liveneighbors=num_live_neighbors(cell);
deadneighbors = 8 - num_live_neighbors;
if(cell.life == true){
if(deadneighbors == 1 or deadneighbors == 2 or deadneighbors == 5){
cell.life = false;
}
}
else if(cell.life == false){
if(liveneighbors == 3 or liveneighbors == 6 or liveneighbors == 7 or liveneighbors == 8){
cell.life = true;
}
}
}
}
public class Cell {
/* The below are standard attributes of all Cells: x, y, life, and neighbors */
public int x;
public int y;
public boolean life;
public ArrayList<Cell> neighbors = new ArrayList<Cell>();
public Cell(int x, int y) {
this.x = x;
this.y = y;
/* Random function inserted according to what is needed */
Random r = new Random();
life = r.nextBoolean();
}
// standard function in all Cell classes
public void addNeighbors(Cell c) {
this.neighbors.add(c);
}
}}
