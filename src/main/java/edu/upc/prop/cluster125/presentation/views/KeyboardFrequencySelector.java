package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class KeyboardFrequencySelector extends JDialog {

    private ArrayList<String> selectedStrings;

    public KeyboardFrequencySelector(JFrame parent, Vector<String> stringVector) {
        super(parent, "String Selection", true);
        this.selectedStrings = new ArrayList<>();

        // Create the main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create a panel for checkboxes
        JPanel checkboxPanel = new JPanel(new GridLayout(0, 1));
        for (String str : stringVector) {
            JCheckBox checkBox = new JCheckBox(str);
            checkboxPanel.add(checkBox);
        }

        // Add the checkbox panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(checkboxPanel), gbc);

        // Create a button to confirm selection
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
                dispose(); // Close the dialog
            }
        });

        // Add the select button to the main panel
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(selectButton, gbc);

        // Add the main panel to the dialog
        add(mainPanel);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public ArrayList<String> getSelectedStrings() {
        return selectedStrings;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = new JFrame();
            parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parentFrame.setSize(400, 300);
            parentFrame.setVisible(true);

            Vector<String> stringVector = new Vector<>();
            stringVector.add("Option 1");
            stringVector.add("Option 2");
            stringVector.add("Option 3");

            KeyboardFrequencySelector dialog = new KeyboardFrequencySelector(parentFrame, stringVector);
            ArrayList<String> selectedStrings = dialog.getSelectedStrings();

            System.out.println("Selected Strings:");
            for (String str : selectedStrings) {
                System.out.println(str);
            }
        });
    }
}
