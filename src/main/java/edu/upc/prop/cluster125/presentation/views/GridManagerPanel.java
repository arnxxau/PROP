package edu.upc.prop.cluster125.presentation.views;

import edu.upc.prop.cluster125.presentation.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta classe representa el panell de gestió de graelles de l'aplicació.
 */
public class GridManagerPanel extends JPanel {
    final DefaultListModel<String> listModel = new DefaultListModel<>();
    final JList<String> list = new JList<>(listModel);

    final GridDisplayerPanel gridRepresentation = new GridDisplayerPanel();

    /**
     * Crea un nou GridManagerPanel.
     *
     * @param parent El JFrame pare.
     */
    public GridManagerPanel(JFrame parent) {
        setLayout(new BorderLayout());

        updateTab();

        // Crea els panells per a una millor organització
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel saveLoadPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(gridRepresentation, BorderLayout.SOUTH);

        Dimension midaBotons = new Dimension(100, 30);
        JButton createButton = new JButton("Create");
        JButton modifyButton = new JButton("Modify");
        JButton propertiesButton = new JButton("Properties");
        JButton deleteButton = new JButton("Delete");
        JButton displayButton = new JButton("Display");

        setButtonSize(createButton, midaBotons);
        setButtonSize(modifyButton, midaBotons);
        setButtonSize(deleteButton, midaBotons);
        setButtonSize(propertiesButton, midaBotons);
        setButtonSize(displayButton, midaBotons);

        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(propertiesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(displayButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        saveLoadPanel.setLayout(new BoxLayout(saveLoadPanel, BoxLayout.Y_AXIS));
        saveLoadPanel.add(Box.createVerticalGlue());
        saveLoadPanel.add(buttonPanel);
        saveLoadPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        mainPanel.add(saveLoadPanel, BorderLayout.EAST);

        add(mainPanel);

        // ActionListeners

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedIndex() != -1) { // Aquesta línia evita esdeveniments duplicats
                int ID = Integer.parseInt(list.getSelectedValue());
                gridRepresentation.updateView(CtrlPresentacio.Obtenir_Reprentacio_Grid(ID), CtrlPresentacio.Max_Grid(ID));
                System.out.println("Selected: " + ID);
            }
        });

        createButton.addActionListener(e -> {
            new GridCreatorDialog(parent);
            updateTab();
        });

        deleteButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Select a grid!");
            } else {
                int confirmLoad = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this component?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmLoad == JOptionPane.YES_OPTION) {
                    CtrlPresentacio.Esborrar_Grid(Integer.parseInt(list.getSelectedValue()));
                    updateTab();
                }
            }
        });

        displayButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) JOptionPane.showMessageDialog(null, "Select a grid!");
            else {
                Integer ID = Integer.parseInt(list.getSelectedValue());
                GridDisplayerDialog fid = new GridDisplayerDialog(parent, CtrlPresentacio.Obtenir_Reprentacio_Grid(ID), CtrlPresentacio.Max_Grid(ID));
                fid.setVisible(true);
            }
        });

        propertiesButton.addActionListener(e -> {
            if (list.getSelectedIndex() == -1) JOptionPane.showMessageDialog(null, "Select a grid!");
            else {
                Integer ID = Integer.parseInt(list.getSelectedValue());
                GridPropertiesDialog fid = new GridPropertiesDialog(parent, CtrlPresentacio.Consultar_Grid(ID));
                fid.setVisible(true);
            }
        });
    }

    private void setButtonSize(JButton button, Dimension mida) {
        button.setMaximumSize(mida);
        button.setPreferredSize(mida);
    }

    /**
     * Actualitza la pestanya de gestió de graella.
     */
    public void updateTab() {
        listModel.clear();
        for (String graella : CtrlPresentacio.Noms_Grid()) {
            listModel.addElement(graella);
        }
    }

}
