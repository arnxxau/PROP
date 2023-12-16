package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;
import edu.upc.prop.clusterxx.Pair;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridManagerPanel extends JPanel {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> list = new JList<>(listModel);

    GridDisplayerPanel gridRepresentation = new GridDisplayerPanel();


    public GridManagerPanel(JFrame parent) {
        setLayout(new BorderLayout());

        updateTab();

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel saveLoadPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);


        mainPanel.add(gridRepresentation, BorderLayout.SOUTH);

        Dimension buttonSize = new Dimension(100, 30);
        JButton createButton = new JButton("Create");
        JButton modifyButton = new JButton("Modify");
        JButton propertiesButton = new JButton("Properties");
        JButton deleteButton = new JButton("Delete");
        JButton displayButton = new JButton("Display");

        setButtonSize(createButton, buttonSize);
        setButtonSize(modifyButton, buttonSize);
        setButtonSize(deleteButton, buttonSize);
        setButtonSize(propertiesButton, buttonSize);
        setButtonSize(displayButton, buttonSize);

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
            if (!e.getValueIsAdjusting() && list.getSelectedIndex() != -1) { // This line prevents double events
                int ID = Integer.parseInt(list.getSelectedValue());
                gridRepresentation.updateView(CtrlPresentacio.Obtenir_Reprentacio_Grid(ID), CtrlPresentacio.Max_Grid(ID));
                System.out.println("Selected: " + ID);
            }
        });


        createButton.addActionListener(e -> {
            GridCreatorDialog cf = new GridCreatorDialog(parent);
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

    private void setButtonSize(JButton button, Dimension size) {
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }

    public void updateTab() {
        listModel.clear();
        for (String grid : CtrlPresentacio.Noms_Grid()) {
            listModel.addElement(grid);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grid Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(new GridManagerPanel(frame));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
