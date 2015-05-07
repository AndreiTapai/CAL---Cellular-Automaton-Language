/**
 * GUI class for the Cellular Automaton Language.
 * 
 * @authors Andrei Tapai, Fei-Tzin Lee
 */

import java.awt.*;
import javax.swing.*;

public class CAL_GUI {
	JFrame window;
	JPanel panel;
	JPanel grid;

	// initialize size
	int windowWidth, windowHeight;
	int boardSize;

	// initialize constants
	static final int numYCells = 10;
	static final int numXCells = 10;

	int cellWidth = 0;
	int cellHeight = 0;

	static final int topPadding = 40;

	Rectangle designatedCell;

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

			if (designatedCell != null) {
				System.out.println(designatedCell.x + " " + designatedCell.y
						+ " " + designatedCell.width + " "
						+ designatedCell.height);
				g2.setPaint(new Color(0, 0, 0));
				g2.fillRect(designatedCell.x, designatedCell.y,
						designatedCell.width, designatedCell.height);
			}
		}

		/**
		 * Draws the grid.
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;

			int x1, y1, x2, y2;

			x1 = (windowWidth - boardSize) / 2;
			y1 = topPadding;
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

			int xOffset = (x2 - (numYCells * cellHeight) + topPadding + 10) / 2;
			int yOffset = (y2 - (numXCells * cellWidth) + topPadding) / 2;

			g2.setPaint(new Color(255, 255, 255));
			g2.fillRect(x1, y1, x2 - x1, y2 - y1);

			for (int row = 0; row < numYCells; row++)
				for (int col = 0; col < numXCells; col++)
					renderCell(g2, xOffset + (col * cellWidth), yOffset
							+ (row * cellHeight), cellWidth, cellHeight);
		}
	}

	/**
	 * Initializes GUI.
	 */
	public CAL_GUI() {
		window = new JFrame("Cellular Automaton Language GUI");
		panel = new JPanel();
		grid = new Grid();

		windowWidth = 750;
		windowHeight = 770;
		boardSize = 650;
		designatedCell = null;

		panel.setLayout(new BorderLayout());
		panel.add(grid, BorderLayout.CENTER);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(panel);
		window.setSize(windowWidth, windowHeight);
		window.setLocation(500, 100);
		window.setVisible(true);
		window.setResizable(false);
	}

	/**
	 * Main method. Creates and calls gui object.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CAL_GUI gui = new CAL_GUI();
		gui.render();
		gui.fillRectangle();
	}

	/**
	 * 
	 */
	public void fillRectangle() {
		System.out.println(0 + ", " + 1);
		designatedCell = new Rectangle(0, 0, cellWidth, cellHeight);
		render();
	}
	
	/**
	 * Repaints grid.
	 */
	public void render() {
		grid.repaint();
	}
}
