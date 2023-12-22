package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;
import edu.upc.prop.cluster125.exceptions.ExisteixID_Exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe GridCreatorDialog és un diàleg que permet crear una graella amb identificadors únics per a les caselles.
 */
public class GridCreatorDialog extends JDialog {

    JFrame parent;
    boolean[][] pos = null;

    /**
     * Crea un nou diàleg GridCreatorDialog.
     *
     * @param parent El marc pare en el qual es mostra el diàleg.
     */
    public GridCreatorDialog(JFrame parent) {
        super(parent, "Grid Creator", true);
        this.parent = parent;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new BorderLayout());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JSpinner idSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        formPanel.add(new JLabel("ID: "));
        formPanel.add(idSpinner);

        formPanel.add(new JLabel("Size: "));

        JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        SpinnerModel valueX = new SpinnerNumberModel(5, 0, 10, 1);
        SpinnerModel valueY = new SpinnerNumberModel(5, 0, 10, 1);
        JSpinner spinnerX = new JSpinner(valueX);
        JSpinner spinnerY = new JSpinner(valueY);
        spinnerPanel.add(new JLabel("X:"));
        spinnerPanel.add(spinnerX);
        spinnerPanel.add(new JLabel("Y:"));
        spinnerPanel.add(spinnerY);
        formPanel.add(spinnerPanel);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton selectGridButton = loadSaveButton("Select Positions");
        selectGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectPositions(valueX, valueY);
            }
        });

        // Button Panel
        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton createButton = loadSaveButton("Create");
        createButton.addActionListener(e -> {
            if (pos == null) JOptionPane.showMessageDialog(this, "Select the positions!");
            else {
                try {
                    CtrlPresentacio.Afegir_Grid((Integer) idSpinner.getValue(), pos);
                    dispose();
                } catch (ExisteixID_Exception ex) {
                    JOptionPane.showMessageDialog(parent, "The ID already exists!", "Name error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        saveLoadPanel.add(createButton);
        buttonPanel.add(selectGridButton, BorderLayout.NORTH);
        buttonPanel.add(saveLoadPanel, BorderLayout.SOUTH);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the dialog
        add(mainPanel);

        // Make the dialog visible
        setLocationRelativeTo(parent);
        pack();
        setVisible(true);
    }

    private void onSelectPositions(SpinnerModel x, SpinnerModel y) {
        int currentValueX = ((SpinnerNumberModel) x).getNumber().intValue();
        int currentValueY = ((SpinnerNumberModel) y).getNumber().intValue();
        GridSelectorDialog dialog = new GridSelectorDialog(parent, currentValueX, currentValueY);
        dialog.setVisible(true);

        boolean[][] selectedPositions = dialog.getSelectedPositions();

        pos = selectedPositions;

        System.out.println("Selected Positions: " + selectedPositions);
    }

    private JButton loadSaveButton(String text) {
        JButton button = new JButton(text);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = new JFrame();
            parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parentFrame.setSize(500, 320);
            parentFrame.setVisible(true);

            GridCreatorDialog dialog = new GridCreatorDialog(parentFrame);
        });
    }
}
