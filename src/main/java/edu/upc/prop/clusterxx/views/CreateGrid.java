package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class CreateGrid extends JFrame {

    public CreateGrid() {
        // Set up the main frame
        setTitle("Grid Creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new BorderLayout());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 30));
        formPanel.add(new JLabel("Name: "));
        formPanel.add(nameField);

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

        Vector<Object> alphabetElements = CtrlPresentacio.getAlphabets();
        JComboBox<Object> alphabetComboBox = new JComboBox<>(alphabetElements);
        formPanel.add(new JLabel("Alphabet: "));
        formPanel.add(alphabetComboBox);

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
            // Your logic for creating the grid goes here
        });
        saveLoadPanel.add(createButton);
        buttonPanel.add(selectGridButton, BorderLayout.NORTH);
        buttonPanel.add(saveLoadPanel, BorderLayout.SOUTH);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void onSelectPositions(SpinnerModel x, SpinnerModel y) {
        int currentValueX = ((SpinnerNumberModel) x).getNumber().intValue();
        int currentValueY = ((SpinnerNumberModel) y).getNumber().intValue();
        PositionSelector dialog = new PositionSelector(this, currentValueX, currentValueY);
        dialog.setVisible(true);

        List<Point> selectedPositions = dialog.getSelectedPositions();

        System.out.println("Selected Positions: " + selectedPositions);
    }

    private JButton loadSaveButton(String text) {
        JButton button = new JButton(text);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CreateGrid::new);
    }
}
