
package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.Grid;
import edu.upc.prop.clusterxx.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KeyboardDisplayerDialog extends JDialog {

    private final ArrayList<Pair> positions;
    private final Pair gridSize;
    private final char[] characters; // Array to hold the characters

    public KeyboardDisplayerDialog(Frame owner, ArrayList<Pair> positions, Pair gridSize, char[] characters) {
        super(owner, "Keyboard Representation", true);
        this.positions = positions;
        this.gridSize = gridSize;
        this.characters = characters; // Initialize the characters array

        int cellSize = 40; // Larger cell size
        int dialogWidth = gridSize.getY() * cellSize + 50;
        int dialogHeight = gridSize.getX() * cellSize + 50;
        setSize(dialogWidth, dialogHeight);
        setLocationRelativeTo(owner);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int cellSize = 40; // Larger cell size

        // Calculate starting point to center the grid
        int startX = (getWidth() - gridSize.getY() * cellSize) / 2;
        int startY = (getHeight() - gridSize.getX() * cellSize) / 2;

        // Draw grid and characters

        FontMetrics fm = g.getFontMetrics();
        for (int i = 0; i < positions.size(); i++) {
            g.setColor(UIManager.getColor("textHighlight")); // Set grid color
            Pair pair = positions.get(i);
            int x = startX + pair.getY() * cellSize;
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
            KeyboardDisplayerDialog dialog = new KeyboardDisplayerDialog(frame, positions, gridSize, characters);
            dialog.setVisible(true);
        });
    }
}
