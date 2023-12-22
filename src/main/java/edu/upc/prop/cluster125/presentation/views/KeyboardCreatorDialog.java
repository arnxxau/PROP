package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;
import edu.upc.prop.cluster125.domain.Keyboard;
import edu.upc.prop.cluster125.exceptions.ExisteixID_Exception;
import edu.upc.prop.cluster125.exceptions.alphNotCompatible_Exception;
import edu.upc.prop.cluster125.exceptions.gridAndAlphabetNotSameSize_Exception;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Aquesta classe representa un diàleg per a la creació de teclats.
 */
public class KeyboardCreatorDialog extends JDialog {

    String content;
    String aName = null;

    String fName = null;

    /**
     * Crea una instància de KeyboardCreatorDialog.
     *
     * @param parent El frame pare.
     */
    public KeyboardCreatorDialog(JFrame parent) {
        super(parent, "Creador de Teclats", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 280);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Added an extra row for the "Grid" combo box
        JPanel radioBtnPanel = new JPanel(new GridLayout(1, 3)); // Updated the grid layout to 1 row and 3 columns
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(80, 30));
        formPanel.add(new JLabel("Nom: "));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Alfabet: "));
        Vector<String> alphabetElements = CtrlPresentacio.getAlphabets();
        JComboBox<String> alphabetComboBox = new JComboBox<>(alphabetElements);
        formPanel.add(alphabetComboBox);

        // Add "Grid" combo box
        formPanel.add(new JLabel("Graella: "));
        Vector<String> gridElements = CtrlPresentacio.Noms_Grid();
        JComboBox<String> gridComboBox = new JComboBox<>(gridElements);
        formPanel.add(gridComboBox);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        // Add radio buttons
        JRadioButton algorithm1RadioButton = new JRadioButton("QAP");
        JRadioButton algorithm2RadioButton = new JRadioButton("Local Search");
        ButtonGroup algorithmGroup = new ButtonGroup();
        algorithmGroup.add(algorithm1RadioButton);
        algorithmGroup.add(algorithm2RadioButton);
        radioBtnPanel.add(algorithm1RadioButton);
        radioBtnPanel.add(algorithm2RadioButton);

        // Button Panel
        JButton createButton = loadSaveButton("Crear");
        createButton.addActionListener(e -> {

        });
        buttonPanel.add(createButton);

        // Add "Select frequencies" button
        JButton selectFrequenciesButton = new JButton("Seleccionar freqüències");
        selectFrequenciesButton.addActionListener(e -> {

            if (alphabetComboBox.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Creeu un alfabet!");
            else {
                String name = alphabetComboBox.getSelectedItem().toString();
                KeyboardFrequencySelector fs = new KeyboardFrequencySelector(parent, CtrlPresentacio.NomsFreqs_alfabet(name));
                if (fs.getSelectedStrings().isEmpty()) JOptionPane.showMessageDialog(null, "No heu proporcionat cap freqüència, per tant no s'ha creat cap freqüència de fusió.");
                else {
                    try {
                        fName = CtrlPresentacio.FusionarFreqa(fs.getSelectedStrings());
                    } catch (alphNotCompatible_Exception ex) {
                        JOptionPane.showMessageDialog(null, "Els alfabet no són compatibles.");
                    }
                }

            }

        });

        createButton.addActionListener(e -> {
            String aName = alphabetComboBox.getSelectedItem().toString();
            String gName = gridComboBox.getSelectedItem().toString();
            if (nameField.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Proporcioneu un nom!");
            else if (!algorithm1RadioButton.isSelected() && !algorithm2RadioButton.isSelected()) JOptionPane.showMessageDialog(null, "Seleccioneu un algorisme!");
            else if (alphabetComboBox.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Creeu un alfabet!");
            else if (gridComboBox.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Creeu una graella!");
            else if (fName == null) JOptionPane.showMessageDialog(null, "Seleccioneu algunes freqüències!");
            else {
                try {
                    if (algorithm1RadioButton.isSelected()) CtrlPresentacio.Afegir_Teclat(nameField.getText(), aName, fName, Integer.parseInt(gName), Keyboard.QAPAlgorithm);
                    else CtrlPresentacio.Afegir_Teclat(nameField.getText(), aName, fName, Integer.parseInt(gName), Keyboard.LocalSearchAlgorithm);

                    dispose();
                } catch (ExisteixID_Exception ex) {
                    JOptionPane.showMessageDialog(parent, "El nom ja existeix!", "Error de nom", JOptionPane.ERROR_MESSAGE);
                } catch (gridAndAlphabetNotSameSize_Exception ex) {
                    fName = null;
                    JOptionPane.showMessageDialog(parent, "Les posicions seleccionades de la graella i la mida de l'alfabet han de ser iguals!", "Error de mida", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        radioBtnPanel.add(selectFrequenciesButton);

        mainPanel.add(radioBtnPanel, BorderLayout.CENTER);
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

}
