package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Aquesta classe representa un diàleg per a seleccionar cadenes de text.
 */
public class KeyboardFrequencySelector extends JDialog {

    private Vector<String> stringVector;
    private ArrayList<String> selectedStrings;

    /**
     * Crea una instància de KeyboardFrequencySelector amb el vector de cadenes de text.
     *
     * @param parent       El frame pare del diàleg.
     * @param stringVector Vector de cadenes de text a seleccionar.
     */
    public KeyboardFrequencySelector(JFrame parent, Vector<String> stringVector) {
        super(parent, "String Selection", true);
        this.stringVector = stringVector;
        this.selectedStrings = new ArrayList<>();

        // Crea el panell principal amb GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Crea un panell per als checkboxes
        JPanel checkboxPanel = new JPanel(new GridLayout(0, 1));
        for (String str : stringVector) {
            JCheckBox checkBox = new JCheckBox(str);
            checkboxPanel.add(checkBox);
        }

        // Afegeix el panell de checkboxes al panell principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(checkboxPanel), gbc);

        // Crea un botó per confirmar la selecció
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : checkboxPanel.getComponents()) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) component;
                        if (checkBox.isSelected()) {
                            selectedStrings.add(checkBox.getText());
                        }
                    }
                }
                dispose(); // Tanca el diàleg
            }
        });

        // Afegeix el botó de selecció al panell principal
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(selectButton, gbc);

        // Afegeix el panell principal al diàleg
        add(mainPanel);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    /**
     * Obté les cadenes de text seleccionades.
     *
     * @return Una llista d'strings seleccionats.
     */
    public ArrayList<String> getSelectedStrings() {
        return selectedStrings;
    }

}
