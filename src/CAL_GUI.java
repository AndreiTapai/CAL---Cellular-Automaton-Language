/**
 * GUI class for the Cellular Automaton Language.
 * 
 * @authors Andrei Tapai, Fei-Tzin Lee
 */

import java.awt.*;
import java.lang.reflect.*;
import java.util.ArrayList;

import javax.swing.*;

public class CAL_GUI {
	JFrame window;
	JPanel panel;
	JPanel grid;

	// initialize size
	int windowWidth, windowHeight;
	int boardSize;

	// initialize constants
	final int numYCells;
	final int numXCells;

	int cellWidth = 0;
	int cellHeight = 0;

	static final int padding = 40;

	ArrayList<?> cellList;
	Field life;
        Field neighbors;
	ArrayList<Rectangle> referenceList;
	boolean listChanged;
	boolean isFirstPass;

	/**
	 * Private cell grid class.
	 */
	@SuppressWarnings("serial")
	private class Grid extends JPanel {

		/**
		 * Draws the specified cell.
		 * 
		 * @param g2
		 * @param x
		 * @param y
		 * @param width
		 * @param height
		 */
		private void renderCell(Graphics2D g2, int x, int y, int width,
				int height) {
			g2.setPaint(new Color(128, 128, 128));
			g2.setStroke(new BasicStroke());

			g2.drawRect(x, y, width, height);

			if (listChanged) {
				for (int i = 0; i < cellList.size(); i++) {
					try {
						if (life.getBoolean(cellList.get(i)) == true) {
							g2.setPaint(new Color(0, 0, 0));
							g2.fillRect(referenceList.get(i).x,
									referenceList.get(i).y,
									referenceList.get(i).width,
									referenceList.get(i).height);
						} else {
							g2.setPaint(new Color(255, 255, 255));
							g2.fillRect(referenceList.get(i).x,
									referenceList.get(i).y,
									referenceList.get(i).width,
									referenceList.get(i).height);                                                        
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		private void drawCenteredString(Graphics2D g2, String string, int x,
				int y) {
			Font font = new Font("SansSerif", Font.PLAIN, 10);
			Color color = new Color(0, 0, 255);
			int numLines = string.split("\n", -1).length;

			g2.setFont(font);
			g2.setPaint(color);

			FontMetrics fm = g2.getFontMetrics();

			int num = 1;
			for (String line : string.split("\n")) {
				int w = fm.stringWidth(line);
				int h = fm.getAscent() + fm.getDescent();

				g2.drawString(line, x - w / 2, y
						+ (int) (((double) num - ((double) numLines + 1) / 2)
								* h * 0.75) - h / 2 + fm.getAscent());
				num++;
			}
		}

		/**
		 * Draws the grid.
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;

			int x1, y1, x2, y2;

			x1 = (windowWidth - boardSize) / 2;
			y1 = padding;
			x2 = x1 + boardSize;
			y2 = y1 + boardSize;

			if (numYCells == numXCells) {
				cellWidth = boardSize / numYCells;
				cellHeight = boardSize / numXCells;
			} else {
				if (numYCells > numXCells) {
					cellWidth = boardSize / numXCells;
					cellHeight = boardSize / numYCells;
				} else {
					cellWidth = boardSize / numXCells;
					cellHeight = boardSize / numYCells;
				}
			}

			int xOffset = (x2 - (numYCells * cellHeight) + padding + 10) / 2;
			int yOffset = (y2 - (numXCells * cellWidth) + padding) / 2;

			g2.setPaint(new Color(255, 255, 255));
			g2.fillRect(x1, y1, x2 - x1, y2 - y1);

			if (isFirstPass) {
				for (int row = 0; row < numYCells; row++)
					for (int col = 0; col < numXCells; col++) {

						Rectangle rec = new Rectangle(xOffset
								+ (col * cellWidth), yOffset
								+ (row * cellHeight), cellWidth, cellHeight);
						referenceList.add(rec);
					}
				isFirstPass = false;
			} else {
				for (int row = 0; row < numYCells; row++)
					for (int col = 0; col < numXCells; col++)
						renderCell(g2, xOffset + (col * cellWidth), yOffset
								+ (row * cellHeight), cellWidth, cellHeight);
			}
		}
	}

	/**
	 * Constructor. Initializes GUI.
	 * 
	 * @param rows
	 * @param cols
	 */
	public CAL_GUI(int rows, int cols, Class cellclass) {
		window = new JFrame("Cellular Automaton Language GUI");
		panel = new JPanel();
		grid = new Grid();

		windowWidth = 750;
		windowHeight = 770;
		boardSize = 650;
		cellList = new ArrayList();
		referenceList = new ArrayList<Rectangle>();
		listChanged = false;
		isFirstPass = true;

		try {
			life = cellclass.getDeclaredField("life");
                        neighbors = cellclass.getDeclaredField("neighbors");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel.setLayout(new BorderLayout());
		panel.add(grid, BorderLayout.CENTER);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(panel);
		window.setSize(windowWidth, windowHeight);
		window.setLocation(500, 100);
		window.setVisible(true);
		window.setResizable(false);

		numYCells = rows;
		numXCells = cols;
	}

	public void retrieveCellList(ArrayList<?> list) {
		cellList = list;
		listChanged = true;
	}

	/**
	 * Repaints grid.
	 */
	public void render() {
		grid.repaint(250);
	}
}
