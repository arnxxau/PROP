
package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.Grid;
import edu.upc.prop.clusterxx.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridDisplayerPanel extends JPanel {

    private ArrayList<Pair> positions;
    private Pair gridSize;

    // Constructor with parameters
    public GridDisplayerPanel(ArrayList<Pair> positions, Pair gridSize) {
        this.positions = positions;
        this.gridSize = gridSize;
        setPreferredSize(calculatePreferredSize());
    }

    // Constructor that allows null parameters
    public GridDisplayerPanel() {
        this(null, null);
    }

    // Method to update view with new parameters
    public void updateView(ArrayList<Pair> newPositions, Pair newGridSize) {
        this.positions = newPositions;
        this.gridSize = newGridSize;
        setPreferredSize(calculatePreferredSize());
        repaint(); // Redraw the panel with new data
    }

    private Dimension calculatePreferredSize() {
        if (gridSize == null) {
            return new Dimension(300, 100); // Default size
        }
        int cellSize = 10;
        int width = gridSize.getY() * cellSize + 50;
        int height = gridSize.getX() * cellSize + 50;
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gridSize == null || positions == null) {
            return; // Do not draw anything if parameters are not set
        }

        int cellSize = 10;
        int startX = (getWidth() - gridSize.getY() * cellSize) / 2;
        int startY = (getHeight() - gridSize.getX() * cellSize) / 2;

        g.setColor(UIManager.getColor("textHighlight"));

        for (int i = 0; i < getWidth(); i +=  gridSize.getX()  * cellSize) {
            for (Pair pair : positions) {
                int x = i + pair.getY() * cellSize;
                int y = startY + pair.getX() * cellSize;
                g.fillRect(x, y, cellSize, cellSize);
            }
        }


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Sample usage with the new JPanel
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GridDisplayerPanel panel = new GridDisplayerPanel(); // Initially with null parameters
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Create a sample grid
            boolean[][] mat = new boolean[][]{
                    {true, false, true,  false, true},
                    {false, true, false, false, true},
                    {true, false, true,  false, true}
            };
            Grid grid = new Grid(1, mat);

            // Extract positions and size from the grid
            ArrayList<Pair> positions = grid.getPositions();
            Pair gridSize = grid.getMaxSize();

            // Update the panel with grid data
            panel.updateView(positions, gridSize);
        });
    }
}
