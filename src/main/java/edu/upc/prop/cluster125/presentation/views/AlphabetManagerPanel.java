package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;
import edu.upc.prop.cluster125.exceptions.ExisteixID_Exception;

import javax.swing.*;
import java.awt.*;

/**
 * La classe AlphabetManagerPanel és un panell de gestió d'alfabets que permet als usuaris crear, modificar, esborrar i consultar alfabets.
 */
public class AlphabetManagerPanel extends JPanel {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> list = new JList<>(listModel);

    /**
     * Crea un nou panell de gestió d'alfabets.
     *
     * @param parent El marc pare en el qual es mostra el panell.
     */
    public AlphabetManagerPanel(JFrame parent) {
        setLayout(new BorderLayout());
        updateTab();

        // Crear panells per a una millor organització
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel saveLoadPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        Dimension buttonSize = new Dimension(100, 30);
        JButton createButton = new JButton("Create");
        JButton modifyButton = new JButton("Modify");
        JButton propertiesButton = new JButton("Properties");
        JButton deleteButton = new JButton("Delete");

        setButtonSize(createButton, buttonSize);
        setButtonSize(modifyButton, buttonSize);
        setButtonSize(deleteButton, buttonSize);
        setButtonSize(propertiesButton, buttonSize);

        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(modifyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(propertiesButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        saveLoadPanel.setLayout(new BoxLayout(saveLoadPanel, BoxLayout.Y_AXIS));
        saveLoadPanel.add(Box.createVerticalGlue());
        saveLoadPanel.add(buttonPanel);
        saveLoadPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        mainPanel.add(saveLoadPanel, BorderLayout.EAST);
        add(mainPanel);

        // ActionListeners
        modifyButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select an alphabet!");
            } else {
                String name = JOptionPane.showInputDialog("What will the new name be?");

                if (name != null && !name.isEmpty()) {
                    try {
                        CtrlPresentacio.CanviarNom_Alfabet(list.getSelectedValue(), name);
                    } catch (ExisteixID_Exception ex) {
                        JOptionPane.showMessageDialog(parent, "The name already exists!","Name error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                updateTab();
            }
        });

        createButton.addActionListener(e -> {
            AlphabetCreatorDialog cf = new AlphabetCreatorDialog(parent);
            updateTab();
        });

        deleteButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select an alphabet!");
            } else {
                int confirmLoad = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this component?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmLoad == JOptionPane.YES_OPTION) {
                    CtrlPresentacio.Esborrar_Alfabet(list.getSelectedValue());
                    updateTab();
                }
            }
        });

        propertiesButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select an alphabet!");
            } else {
                AlphabetPropertiesDialog fid = new AlphabetPropertiesDialog(parent, CtrlPresentacio.Consultar_Alfabet(list.getSelectedValue()));
                fid.setVisible(true);
            }
        });
    }

    private void setButtonSize(JButton button, Dimension size) {
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }

    /**
     * Actualitza la llista d'alfabets amb els noms dels alfabets disponibles.
     */
    public void updateTab() {
        listModel.clear();
        for (String alphabet : CtrlPresentacio.Noms_Alfabet()) {
            listModel.addElement(alphabet);
        }
    }

}
