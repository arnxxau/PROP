package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;
import edu.upc.prop.clusterxx.Pair;
import edu.upc.prop.clusterxx.exceptions.ExisteixID_Exception;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KeyboardManagerPanel extends JPanel {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> list = new JList<>(listModel);

    KeyboardDisplayerPanel keyboardRepresentation = new KeyboardDisplayerPanel();
    public KeyboardManagerPanel(JFrame parent) {


        setLayout(new BorderLayout());

        updateTab();

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel saveLoadPanel = new JPanel();

        // Use BoxLayout to stack buttons vertically in the buttonPanel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));


        JScrollPane scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(keyboardRepresentation, BorderLayout.SOUTH);



        Dimension buttonSize = new Dimension(100, 30); // Adjust width and height as needed

        JButton createButton = new JButton("Create");
        JButton modifyButton = new JButton("Modify");
        JButton propertiesButton = new JButton("Properties");
        JButton deleteButton = new JButton("Delete");
        JButton displayButton = new JButton("Display");

        setButtonSize(modifyButton, buttonSize);
        setButtonSize(propertiesButton, buttonSize);
        setButtonSize(createButton, buttonSize);
        setButtonSize(deleteButton, buttonSize);
        setButtonSize(displayButton, buttonSize);

        // Add buttons to the buttonPanel
        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add separation
        buttonPanel.add(modifyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add separation
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add separation
        buttonPanel.add(propertiesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add separation
        buttonPanel.add(displayButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add rigid vertical struts before and after buttonPanel
        saveLoadPanel.setLayout(new BoxLayout(saveLoadPanel, BoxLayout.Y_AXIS));
        saveLoadPanel.add(Box.createVerticalGlue());
        saveLoadPanel.add(buttonPanel);
        saveLoadPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

        // Add panels to the main panel
        mainPanel.add(saveLoadPanel, BorderLayout.EAST); // Add saveLoadPanel directly to the mainPanel

        // Add the main panel to this JPanel (ManageFrequency)
        add(mainPanel);

        // Add ActionListeners to buttons
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedIndex() != -1) { // This line prevents double events
                String selectedKeyboardName = list.getSelectedValue();
                int gridId = CtrlPresentacio.Obtenir_Nom_Grid_Teclat(selectedKeyboardName);
                ArrayList<Pair> positions = CtrlPresentacio.Obtenir_Reprentacio_Grid(gridId);
                Pair gridSize = CtrlPresentacio.Max_Grid(gridId);
                char[] characters = CtrlPresentacio.Obtenir_Distribucio_Teclat(selectedKeyboardName);

                keyboardRepresentation.updateView(positions, gridSize, characters);
            }
        });

        modifyButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select a keyboard!");
            } else {
                String name = JOptionPane.showInputDialog("What will the new name be?");

                if (name != null && !name.isEmpty()) {
                    try {
                        CtrlPresentacio.Canviar_Nom_Teclat(list.getSelectedValue(), name);
                    } catch (ExisteixID_Exception ex) {
                        JOptionPane.showMessageDialog(parent, "The name already exists!","Name error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                updateTab();
            }
        });

        createButton.addActionListener(e -> {
            KeyboardCreatorDialog cf = new KeyboardCreatorDialog(parent);
            updateTab();
        });

        deleteButton.addActionListener(e -> {

            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select an alphabet!");
            } else {
                int confirmLoad = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this component?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmLoad == JOptionPane.YES_OPTION) {
                    CtrlPresentacio.Esborrar_Teclat(list.getSelectedValue());
                    updateTab();
                }
            }

        });

        propertiesButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) JOptionPane.showMessageDialog(null, "Select a keyboard!");
            else {
                KeyboardPropertiesDialog fid = new KeyboardPropertiesDialog(parent, CtrlPresentacio.Consultar_Teclat(list.getSelectedValue()));
                fid.setVisible(true);
            }

        });

        displayButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select a keyboard!");
            } else {
                String selectedKeyboardName = list.getSelectedValue();
                int gridId = CtrlPresentacio.Obtenir_Nom_Grid_Teclat(selectedKeyboardName);
                ArrayList<Pair> positions = CtrlPresentacio.Obtenir_Reprentacio_Grid(gridId);
                Pair gridSize = CtrlPresentacio.Max_Grid(gridId);
                char[] characters = CtrlPresentacio.Obtenir_Distribucio_Teclat(selectedKeyboardName);
                KeyboardDisplayerDialog dialog = new KeyboardDisplayerDialog(parent, positions, gridSize, characters);
                dialog.setVisible(true);
            }
        });
    }


    private void setButtonSize(JButton button, Dimension size) {
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }

    public void updateTab() {
        // Update the list model with the new data
        listModel.clear();
        for (String freq : CtrlPresentacio.Noms_Teclats()) {
            listModel.addElement(freq);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Frequency manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(new KeyboardManagerPanel(frame));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
