
package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.domain.Grid;
import edu.upc.prop.cluster125.domain.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KeyboardDisplayerPanel extends JPanel {

    private ArrayList<Pair> positions;
    private Pair gridSize;
    private char[] characters; // Array to hold the characters

    public KeyboardDisplayerPanel(Frame owner, ArrayList<Pair> positions, Pair gridSize, char[] characters) {
        // super(owner, "Keyboard Representation", true);
        this.positions = positions;
        this.gridSize = gridSize;
        this.characters = characters; // Initialize the characters array

        setPreferredSize(calculatePreferredSize());
    }

    public KeyboardDisplayerPanel() {
        this(null, null, null, null);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gridSize == null || positions == null) {
            return; // Do not draw anything if parameters are not set
        }


        int cellSize = 10; // Larger cell size

        // Calculate starting point to center the grid
        int startX = (getWidth() - gridSize.getY() * cellSize) / 2;
        int startY = (getHeight() - gridSize.getX() * cellSize) / 2;

        // Draw grid and characters

        FontMetrics fm = g.getFontMetrics();
        for (int j = 0; j < getWidth(); j +=  gridSize.getX()  * cellSize) {
            for (int i = 0; i < positions.size(); i++) {
                g.setColor(UIManager.getColor("textHighlight")); // Set grid color
                Pair pair = positions.get(i);
                int x = j + pair.getY() * cellSize;
                int y = startY + pair.getX() * cellSize;
                g.fillRect(x, y, cellSize, cellSize); // Draw a filled square at each pair position

                // Draw the character
                char ch = characters[i];
                String s = String.valueOf(ch);
                int stringWidth = fm.stringWidth(s);
                int stringAscent = fm.getAscent();
                int xCenter = x + (cellSize - stringWidth) / 2;
                int yCenter = y + (cellSize + stringAscent) / 2 - fm.getDescent();
                g.setColor(Color.BLACK); // Set text color
                g.drawString(s, xCenter, yCenter);
            }
        }

    }


    public void updateView(ArrayList<Pair> newPositions, Pair newGridSize, char[] characters) {
        this.positions = newPositions;
        this.gridSize = newGridSize;
        this.characters = characters;
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


    public static void main(String[] args) {
        // Sample usage
        SwingUtilities.invokeLater(() -> {
            // Create a sample grid and characters
            boolean[][] mat = new boolean[][]{
                    {true, false, true, false, true},
                    {false, true, false, false, true},
                    {true, false, true, false, true}
            };
            Grid grid = new Grid(1, mat);
            ArrayList<Pair> positions = grid.getPositions();
            Pair gridSize = grid.getMaxSize();
            char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'}; // Sample characters

            // Create and display the dialog
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            KeyboardDisplayerPanel dialog = new KeyboardDisplayerPanel(frame, positions, gridSize, characters);
            dialog.setVisible(true);
        });
    }
}
