package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.domain.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Aquesta classe representa un panell per a la visualització d'un teclat.
 */
public class KeyboardDisplayerPanel extends JPanel {

    private ArrayList<Pair> positions;
    private Pair gridSize;
    private char[] characters; // Array per emmagatzemar els caràcters

    /**
     * Crea una instància de KeyboardDisplayerPanel amb les dades del teclat.
     *
     * @param positions  Llista de posicions dels caràcters.
     * @param gridSize   Mida de la graella.
     * @param characters Llista de caràcters a mostrar.
     */
    public KeyboardDisplayerPanel(ArrayList<Pair> positions, Pair gridSize, char[] characters) {
        this.positions = positions;
        this.gridSize = gridSize;
        this.characters = characters; // Inicialitza l'array de caràcters

        setPreferredSize(calculatePreferredSize());
    }

    /**
     * Crea una instància de KeyboardDisplayerPanel sense dades.
     */
    public KeyboardDisplayerPanel() {
        this(null, null, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gridSize == null || positions == null) {
            return; // No dibuixis res si els paràmetres no estan definits
        }

        int cellSize = 10; // Mida més gran de la cel·la

        // Calcula el punt d'inici per centrar la graella
        int startY = (getHeight() - gridSize.getX() * cellSize) / 2;

        // Dibuixa la graella i els caràcters

        FontMetrics fm = g.getFontMetrics();
        for (int j = 0; j < getWidth(); j += gridSize.getY() * cellSize) {
            for (int i = 0; i < positions.size(); i++) {
                g.setColor(UIManager.getColor("textHighlight")); // Defineix el color de la graella
                Pair pair = positions.get(i);
                int x = j + pair.getY() * cellSize;
                int y = startY + pair.getX() * cellSize;
                g.fillRect(x, y, cellSize, cellSize); // Dibuixa un quadrat ple a cada posició del par

                // Dibuixa el caràcter
                char ch = characters[i];
                String s = String.valueOf(ch);
                int stringWidth = fm.stringWidth(s);
                int stringAscent = fm.getAscent();
                int xCenter = x + (cellSize - stringWidth) / 2;
                int yCenter = y + (cellSize + stringAscent) / 2 - fm.getDescent();
                g.setColor(Color.BLACK); // Defineix el color del text
                g.drawString(s, xCenter, yCenter);
            }
        }
    }

    /**
     * Actualitza la visualització del panell amb de noves dades.
     *
     * @param newPositions  Les noves posicions dels caràcters.
     * @param newGridSize   La nova mida de la graella.
     * @param newCharacters Els nous caràcters a mostrar.
     */
    public void updateView(ArrayList<Pair> newPositions, Pair newGridSize, char[] newCharacters) {
        this.positions = newPositions;
        this.gridSize = newGridSize;
        this.characters = newCharacters;
        setPreferredSize(calculatePreferredSize());
        repaint(); // Redibuixa el panell amb les noves dades
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
}
