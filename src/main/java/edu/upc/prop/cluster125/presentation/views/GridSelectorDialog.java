package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta classe representa un diàleg per seleccionar posicions en una graella.
 */
public class GridSelectorDialog extends JDialog {

    private boolean[][] selectedPositions;

    /**
     * Crea una instància de GridSelectorDialog.
     *
     * @param parent L'objecte JFrame pare.
     * @param gridX  El nombre de columnes de la graella.
     * @param gridY  El nombre de files de la graella.
     */
    public GridSelectorDialog(JFrame parent, int gridX, int gridY) {
        super(parent, "Selecció de Graella", true);
        selectedPositions = new boolean[gridY][gridX];

        // Crea un panell per a la graella
        JPanel gridPanel = new JPanel(new GridLayout(gridY, gridX, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Estil personalitzat dels botons
        for (int y = 0; y < gridY; y++) {
            for (int x = 0; x < gridX; x++) {
                JButton blockButton = new JButton("(" + x + ", " + y + ")");
                blockButton.setFocusPainted(false);
                blockButton.setBorderPainted(false);
                blockButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                blockButton.setOpaque(true);
                int finalX = x;
                int finalY = y;
                blockButton.addActionListener(e -> toggleSelection(blockButton, finalX, finalY));
                gridPanel.add(blockButton);
            }
        }

        // Crea un botó per confirmar i tancar el diàleg
        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(e -> dispose());

        // Afegeix un marge al botó de confirmació
        JPanel confirmButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirmButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmButtonPanel.add(confirmButton);

        // Organitza els components dins del diàleg
        setLayout(new BorderLayout(10, 10));
        add(gridPanel, BorderLayout.CENTER);
        add(confirmButtonPanel, BorderLayout.SOUTH);

        // Configura les propietats del diàleg
        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void toggleSelection(JButton button, int x, int y) {
        selectedPositions[y][x] = !selectedPositions[y][x]; // Alterna el valor booleà
        if (selectedPositions[y][x]) {
            if (UIManager.getLookAndFeel().isNativeLookAndFeel()) {
                button.setBackground(UIManager.getColor("textHighlight")); // Un to blau més suau
                button.setForeground(UIManager.getColor("textHighlightText")); // Text blanc per a millor llegibilitat
            } else {
                button.setBackground(Color.BLUE);
                button.setForeground(Color.WHITE);
            }
        } else {
            button.setBackground(UIManager.getColor("Button.background"));
            button.setForeground(UIManager.getColor("Button.foreground"));
        }
    }

    /**
     * Obté les posicions seleccionades a la graella.
     *
     * @return Una matriu booleà que representa les posicions seleccionades.
     */
    public boolean[][] getSelectedPositions() {
        return selectedPositions;
    }

    /**
     * El mètode principal per provar el GridSelectorDialog.
     *
     * @param args Els arguments de la línia de comandes.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame();
            mainFrame.setSize(500, 500);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);

            GridSelectorDialog dialog = new GridSelectorDialog(mainFrame, 5, 5);
            dialog.setVisible(true);

            boolean[][] selectedPositions = dialog.getSelectedPositions();
            System.out.println("Posicions Seleccionades:");
            for (int y = 0; y < selectedPositions.length; y++) {
                for (int x = 0; x < selectedPositions[y].length; x++) {
                    System.out.print(selectedPositions[y][x] + " ");
                }
                System.out.println();
            }
        });
    }
}
