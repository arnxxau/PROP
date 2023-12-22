package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.domain.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Aquesta classe representa un diàleg per a la visualització d'una graella.
 */
public class GridDisplayerDialog extends JDialog {

    private final ArrayList<Pair> positions;
    private final Pair gridSize;

    /**
     * Crea una nova instància de GridDisplayerDialog.
     *
     * @param owner     El frame pare en el qual es mostra el diàleg.
     * @param positions La llista de posicions de les caselles a mostrar.
     * @param gridSize  La mida de la graella.
     */
    public GridDisplayerDialog(Frame owner, ArrayList<Pair> positions, Pair gridSize) {
        super(owner, "Grid Representation", true);
        this.positions = positions;
        this.gridSize = gridSize;

        // Calcular la mida del diàleg basada en la mida de la graella i la mida de les cel·les
        int cellSize = 40; // Mida més gran de les cel·les
        int dialogWidth = gridSize.getY() * cellSize + 50; // Espai extra per marges
        int dialogHeight = gridSize.getX() * cellSize + 50; // Espai extra per marges
        setSize(dialogWidth, dialogHeight);
        setLocationRelativeTo(owner); // Centrar a la pantalla
    }

    /**
     * Sobrescriu el mètode paint per a dibuixar la graella a la finestra.
     *
     * @param g L'objecte Graphics utilitzat per dibuixar.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int cellSize = 40; // Mida més gran de les cel·les

        // Calcular el punt d'inici per centrar la graella
        int startX = (getWidth() - gridSize.getY() * cellSize) / 2;
        int startY = (getHeight() - gridSize.getX() * cellSize) / 2;

        // Dibuixar la graella
        g.setColor(UIManager.getColor("textHighlight")); // Establir el color de la graella
        for (Pair pair : positions) {
            int x = startX + pair.getY() * cellSize;
            int y = startY + pair.getX() * cellSize;
            g.fillRect(x, y, cellSize, cellSize); // Dibuixar un quadrat omplert a cada posició de parella
        }
    }

}
