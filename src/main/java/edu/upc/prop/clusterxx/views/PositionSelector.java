package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PositionSelector extends JDialog {

    private List<Point> selectedPositions;

    public PositionSelector(JFrame parent, int gridX, int gridY) {
        super(parent, "Grid Selection", true);
        selectedPositions = new ArrayList<>();

        // Create a panel for the grid
        JPanel gridPanel = new JPanel(new GridLayout(gridY, gridX, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add buttons representing each block in the grid
        for (int y = 0; y < gridY; y++) {
            for (int x = 0; x < gridX; x++) {
                JButton blockButton = new JButton("(" + x + ", " + y + ")");
                int finalX = x;
                int finalY = y;
                blockButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        toggleSelection(blockButton, finalX, finalY);
                    }
                });
                gridPanel.add(blockButton);
            }
        }

        // Create a button to confirm and close the dialog
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

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
        Point position = new Point(x, y);
        if (selectedPositions.contains(position)) {
            selectedPositions.remove(position);
            button.setBackground(null); // Reset the background color
        } else {
            selectedPositions.add(position);
            button.setBackground(Color.BLUE); // Set the background color for selected buttons
        }
    }

    public List<Point> getSelectedPositions() {
        return selectedPositions;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame();
            mainFrame.setSize(500, 500);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);

            PositionSelector dialog = new PositionSelector(mainFrame, 5, 5);
            dialog.setVisible(true);

            List<Point> selectedPositions = dialog.getSelectedPositions();
            System.out.println("Selected Positions: " + selectedPositions);
        });
    }
}
