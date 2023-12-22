package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;
import edu.upc.prop.cluster125.domain.Pair;
import edu.upc.prop.cluster125.exceptions.ExisteixID_Exception;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Aquesta classe representa un panell de gestió de teclats.
 */
public class KeyboardManagerPanel extends JPanel {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> list = new JList<>(listModel);

    KeyboardDisplayerPanel keyboardRepresentation = new KeyboardDisplayerPanel();

    /**
     * Crea una instància de KeyboardManagerPanel amb un frame pare.
     *
     * @param parent El frame pare del panell de gestió de teclats.
     */
    public KeyboardManagerPanel(JFrame parent) {

        setLayout(new BorderLayout());

        updateTab();

        // Crea panells per a una millor organització
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel saveLoadPanel = new JPanel();

        // Utilitza BoxLayout per apilar botons verticalment a buttonPanel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(keyboardRepresentation, BorderLayout.SOUTH);

        Dimension buttonSize = new Dimension(100, 30); // Ajusta l'amplada i l'altura segons sigui necessari

        JButton createButton = new JButton("Create");
        JButton modifyButton = new JButton("Modify");
        JButton propertiesButton = new JButton("Properties");
        JButton deleteButton = new JButton("Delete");
        JButton displayButton = new JButton("Display");
        JButton updateButton = new JButton("Update");

        setButtonSize(modifyButton, buttonSize);
        setButtonSize(updateButton, buttonSize);
        setButtonSize(propertiesButton, buttonSize);
        setButtonSize(createButton, buttonSize);
        setButtonSize(deleteButton, buttonSize);
        setButtonSize(displayButton, buttonSize);

        // Afegeix botons a buttonPanel
        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Afegeix espai
        buttonPanel.add(modifyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Afegeix espai
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Afegeix espai
        buttonPanel.add(propertiesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Afegeix espai
        buttonPanel.add(displayButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Afegeix espai
        buttonPanel.add(updateButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Afegeix rigides verticals abans i després de buttonPanel
        saveLoadPanel.setLayout(new BoxLayout(saveLoadPanel, BoxLayout.Y_AXIS));
        saveLoadPanel.add(Box.createVerticalGlue());
        saveLoadPanel.add(buttonPanel);
        saveLoadPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

        // Afegeix panells al panell principal
        mainPanel.add(saveLoadPanel, BorderLayout.EAST); // Afegeix saveLoadPanel directament al mainPanel

        // Afegeix el panell principal a aquest JPanel (ManageFrequency)
        add(mainPanel);

        // Afegeix ActionListeners als botons
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedIndex() != -1) { // Aquesta línia evita esdeveniments dobles
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
                        JOptionPane.showMessageDialog(parent, "The name already exists!", "Name error", JOptionPane.ERROR_MESSAGE);
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

        updateButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select a keyboard!");
            } else {
                String selectedKeyboardName = list.getSelectedValue();
                CtrlPresentacio.Actualitzar_Teclat(selectedKeyboardName);

                int gridId = CtrlPresentacio.Obtenir_Nom_Grid_Teclat(selectedKeyboardName);
                ArrayList<Pair> positions = CtrlPresentacio.Obtenir_Reprentacio_Grid(gridId);
                Pair gridSize = CtrlPresentacio.Max_Grid(gridId);
                char[] characters = CtrlPresentacio.Obtenir_Distribucio_Teclat(selectedKeyboardName);

                keyboardRepresentation.updateView(positions, gridSize, characters);
            }
        });
    }

    private void setButtonSize(JButton button, Dimension size) {
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }

    public void updateTab() {
        // Actualitza el model de la llista amb les noves dades
        listModel.clear();
        for (String freq : CtrlPresentacio.Noms_Teclats()) {
            listModel.addElement(freq);
        }
    }

}
