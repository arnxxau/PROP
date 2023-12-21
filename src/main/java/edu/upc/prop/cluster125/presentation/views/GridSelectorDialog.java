package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.awt.*;

public class GridSelectorDialog extends JDialog {

    private boolean[][] selectedPositions;

    public GridSelectorDialog(JFrame parent, int gridX, int gridY) {
        super(parent, "Grid Selection", true);
        selectedPositions = new boolean[gridY][gridX];


        // Create a panel for the grid
        JPanel gridPanel = new JPanel(new GridLayout(gridY, gridX, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Custom button styling
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

        // Create a button to confirm and close the dialog
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> dispose());

        // Add a margin to the confirm button
        JPanel confirmButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirmButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmButtonPanel.add(confirmButton);

        // Arrange components in the dialog
        setLayout(new BorderLayout(10, 10));
        add(gridPanel, BorderLayout.CENTER);
        add(confirmButtonPanel, BorderLayout.SOUTH);

        // Set dialog properties
        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }

    private void toggleSelection(JButton button, int x, int y) {
        selectedPositions[y][x] = !selectedPositions[y][x]; // Toggle the boolean value
        if (selectedPositions[y][x]) {

            System.out.println(UIManager.getColor("List.selectionBackground"));
            if (UIManager.getLookAndFeel().isNativeLookAndFeel()) {
                button.setBackground(UIManager.getColor("textHighlight")); // A softer shade of blue}
                button.setForeground(UIManager.getColor("textHighlightText")); // White text for better readability
            }
            else {
                button.setBackground(Color.BLUE);
                button.setForeground(Color.WHITE);
            }


        } else {
            button.setBackground(UIManager.getColor("Button.background"));
            button.setForeground(UIManager.getColor("Button.foreground"));
        }
    }

    public boolean[][] getSelectedPositions() {
        return selectedPositions;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame();
            mainFrame.setSize(500, 500);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);

            GridSelectorDialog dialog = new GridSelectorDialog(mainFrame, 5, 5);
            dialog.setVisible(true);

            boolean[][] selectedPositions = dialog.getSelectedPositions();
            System.out.println("Selected Positions:");
            for (int y = 0; y < selectedPositions.length; y++) {
                for (int x = 0; x < selectedPositions[y].length; x++) {
                    System.out.print(selectedPositions[y][x] + " ");
                }
                System.out.println();
            }
        });
    }
}
