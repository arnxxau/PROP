package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class KeyboardCreatorDialog extends JDialog {

    String content;
    String aName = null;

    String fName = null;

    public KeyboardCreatorDialog(JFrame parent) {
        super(parent, "Keyboard creator", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 280);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Added an extra row for the "Grid" combo box
        JPanel radioBtnPanel = new JPanel(new GridLayout(1, 3));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 30));
        formPanel.add(new JLabel("Name: "));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Alphabet: "));
        Vector<String> alphabetElements = CtrlPresentacio.getAlphabets();
        JComboBox<String> alphabetComboBox = new JComboBox<>(alphabetElements);
        formPanel.add(alphabetComboBox);

        // Add "Grid" combo box
        formPanel.add(new JLabel("Grid: "));
        Vector<String> gridElements = CtrlPresentacio.Noms_Grid();
        JComboBox<String> gridComboBox = new JComboBox<>(gridElements);
        formPanel.add(gridComboBox);


        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(radioBtnPanel, BorderLayout.CENTER);

        // Button Panel
        JButton createButton = loadSaveButton("Create");
        createButton.addActionListener(e -> {

        });
        buttonPanel.add(createButton);

        // Add "Select frequencies" button
        JButton selectFrequenciesButton = new JButton("Select frequencies");
        selectFrequenciesButton.addActionListener(e -> {

            if (alphabetComboBox.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Create an alphabet!");
            else {
                String name = alphabetComboBox.getSelectedItem().toString();
                FrequencySelector fs = new FrequencySelector(parent, CtrlPresentacio.NomsFreqs_alfabet(name));
                if (fs.getSelectedStrings().isEmpty()) JOptionPane.showMessageDialog(null, "You provided 0 frequencies so no fusion frequency was created.");
                else {
                    fName = CtrlPresentacio.FusionarFreqa(fs.getSelectedStrings());
                }

            }

        });

        createButton.addActionListener(e -> {
            String aName = alphabetComboBox.getSelectedItem().toString();
            String gName = gridComboBox.getSelectedItem().toString();
            if (nameField.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Give it a name!");
            else if (alphabetComboBox.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Create an alphabet!");
            else if (gridComboBox.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Create a grid!");
            else if (fName == null) JOptionPane.showMessageDialog(null, "Select some frequencies!");
            else CtrlPresentacio.Afegir_Teclat(nameField.getText(), aName, fName, Integer.parseInt(gName));

        });
        radioBtnPanel.add(selectFrequenciesButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the dialog
        add(mainPanel);

        // Make the dialog visible
        setLocationRelativeTo(parent);
        pack();
        setVisible(true);
    }

    private JButton loadSaveButton(String text) {
        JButton button = new JButton(text);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = new JFrame();
            parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parentFrame.setSize(500, 300); // Increased the width to accommodate new components
            parentFrame.setVisible(true);

            KeyboardCreatorDialog dialog = new KeyboardCreatorDialog(parentFrame);
        });
    }
}
