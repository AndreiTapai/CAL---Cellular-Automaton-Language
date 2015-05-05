
/**
 *
 * @authors Andrei Tapai, Fei-Tzin Lee
 */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class CAL_GUI {

    JFrame window;
    JPanel panel;
    JPanel grid;

    int windowWidth, windowHeight;

    public CAL_GUI() {
        window = new JFrame();
        panel = new JPanel();
        grid = new Grid();

        windowWidth = 750;
        windowHeight = 750;

        panel.setLayout(new BorderLayout());
        panel.add(grid, BorderLayout.CENTER);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(panel);
        window.setSize(windowWidth, windowHeight);
        window.setLocation(500, 100);
        window.setVisible(true);
        window.setResizable(false);
    }

    public void render() {
        grid.repaint();
    }

    public static void main(String[] args) {
        CAL_GUI gui = new CAL_GUI();
    }

    @SuppressWarnings("serial")
    private class Grid extends JPanel {

        private final int numXCells = 100;
        private final int numYCells = 100;
        private ArrayList<Rectangle> cells;

        public Grid() {
            cells = new ArrayList<>(numXCells * numYCells);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            int cellWidth = width / numXCells;
            int cellHeight = height / numYCells;

            int xOffset = (width - (numXCells * cellWidth)) / 2;
            int yOffset = (height - (numYCells * cellHeight)) / 2;

            if (cells.isEmpty()) {
                for (int row = 0; row < numYCells; row++) {
                    for (int col = 0; col < numXCells; col++) {
                        Rectangle cell = new Rectangle(
                                xOffset + (col * cellWidth),
                                yOffset + (row * cellHeight),
                                cellWidth,
                                cellHeight);
                        cells.add(cell);
                    }
                }
            }

            g2d.setColor(Color.GRAY);
            for (Rectangle cell : cells) {
                g2d.draw(cell);
            }

            g2d.dispose();
        }

    }

}
