package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.Grid;
import edu.upc.prop.clusterxx.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridDisplayerDialog extends JDialog {

    private final ArrayList<Pair> positions;
    private final Pair gridSize;

    public GridDisplayerDialog(Frame owner, ArrayList<Pair> positions, Pair gridSize) {
        super(owner, "Grid Representation", true);
        this.positions = positions;
        this.gridSize = gridSize;

        // Calculate the size of the dialog based on the grid size and cell size
        int cellSize = 40; // Larger cell size
        int dialogWidth = gridSize.getY() * cellSize + 50; // Extra space for margins
        int dialogHeight = gridSize.getX() * cellSize + 50; // Extra space for margins
        setSize(dialogWidth, dialogHeight);
        setLocationRelativeTo(owner); // Center on screen
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int cellSize = 40; // Larger cell size

        // Calculate starting point to center the grid
        int startX = (getWidth() - gridSize.getY() * cellSize) / 2;
        int startY = (getHeight() - gridSize.getX() * cellSize) / 2;

        // Draw grid
        g.setColor(UIManager.getColor("textHighlight")); // Set grid color
        for (Pair pair : positions) {
            int x = startX + pair.getY() * cellSize;
            int y = startY + pair.getX() * cellSize;
            g.fillRect(x, y, cellSize, cellSize); // Draw a filled square at each pair position
        }
    }
    public static void main(String[] args) {
        // Sample usage
        SwingUtilities.invokeLater(() -> {
            // Create a sample grid
            boolean[][] mat = new boolean[][]{
                    {true, false, true,  false, true},
                    {false, true, false,  false, true},
                    {true, false, true,  false, true}
            };
            Grid grid = new Grid(1, mat);

            // Extract positions and size from the grid
            ArrayList<Pair> positions = grid.getPositions();
            Pair gridSize = grid.getMaxSize();

            // Create and display the dialog
            JFrame frame = new JFrame(); // Replace with your main application frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GridDisplayerDialog dialog = new GridDisplayerDialog(frame, positions, gridSize);
            dialog.setVisible(true);
        });
    }
}
