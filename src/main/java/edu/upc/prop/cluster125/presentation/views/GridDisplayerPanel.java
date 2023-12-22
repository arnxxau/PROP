package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.domain.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Aquesta classe representa un panell per a la visualització d'una graella.
 */
public class GridDisplayerPanel extends JPanel {

    private ArrayList<Pair> positions;
    private Pair gridSize;

    /**
     * Crea una nova instància de GridDisplayerPanel amb les posicions i la mida de la graella especificades.
     *
     * @param positions Les posicions de les cel·les de la graella a mostrar.
     * @param gridSize  La mida de la graella.
     */
    public GridDisplayerPanel(ArrayList<Pair> positions, Pair gridSize) {
        this.positions = positions;
        this.gridSize = gridSize;
        setPreferredSize(calculatePreferredSize());
    }

    /**
     * Crea una nova instància de GridDisplayerPanel amb paràmetres nuls.
     */
    public GridDisplayerPanel() {
        this(null, null);
    }

    /**
     * Actualitza la vista amb les noves posicions i la nova mida de la graella especificades.
     *
     * @param newPositions Les noves posicions de les cel·les de la graella.
     * @param newGridSize  La nova mida de la graella.
     */
    public void updateView(ArrayList<Pair> newPositions, Pair newGridSize) {
        this.positions = newPositions;
        this.gridSize = newGridSize;
        setPreferredSize(calculatePreferredSize());
        repaint(); // Torna a dibuixar el panell amb les noves dades
    }

    private Dimension calculatePreferredSize() {
        if (gridSize == null) {
            return new Dimension(300, 100); // Mida per defecte
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
            return; // No dibuixis res si els paràmetres no estan definits
        }

        int cellSize = 10;
        int startY = (getHeight() - gridSize.getX() * cellSize) / 2;

        g.setColor(UIManager.getColor("textHighlight"));

        for (int i = 0; i < getWidth(); i += gridSize.getX() * cellSize) {
            for (Pair pair : positions) {
                int x = i + pair.getY() * cellSize;
                int y = startY + pair.getX() * cellSize;
                g.fillRect(x, y, cellSize, cellSize);
            }
        }
    }

}
