package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.domain.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Aquesta classe representa un diàleg per a la visualització d'un teclat.
 */
public class KeyboardDisplayerDialog extends JDialog {

    private final ArrayList<Pair> positions;
    private final Pair gridSize;
    private final char[] characters; // Array per emmagatzemar els caràcters

    /**
     * Crea una instància de KeyboardDisplayerDialog.
     *
     * @param owner      El frame pare.
     * @param positions  Llista de posicions dels caràcters.
     * @param gridSize   Mida de la graella.
     * @param characters Llista de caràcters a mostrar.
     */
    public KeyboardDisplayerDialog(Frame owner, ArrayList<Pair> positions, Pair gridSize, char[] characters) {
        super(owner, "Keyboard Representation", true);
        this.positions = positions;
        this.gridSize = gridSize;
        this.characters = characters; // Inicialitza l'array de caràcters

        int cellSize = 40; // Mida més gran de la cel·la
        int dialogWidth = gridSize.getY() * cellSize + 50;
        int dialogHeight = gridSize.getX() * cellSize + 50;
        setSize(dialogWidth, dialogHeight);
        setLocationRelativeTo(owner);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int cellSize = 40; // Mida més gran de la cel·la

        // Calcula el punt d'inici per centrar la graella
        int startX = (getWidth() - gridSize.getY() * cellSize) / 2;
        int startY = (getHeight() - gridSize.getX() * cellSize) / 2;

        // Dibuixa la graella i els caràcters

        FontMetrics fm = g.getFontMetrics();
        for (int i = 0; i < positions.size(); i++) {
            g.setColor(UIManager.getColor("textHighlight")); // Defineix el color de la graella
            Pair pair = positions.get(i);
            int x = startX + pair.getY() * cellSize;
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
