package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

public class ManageAlphabets extends JFrame {

    private JTable table;
    private JButton modifyButton;
    private JButton createButton;
    private JButton deleteButton;
    private JButton backButton;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;

    public ManageAlphabets() {
        // Set up the main frame
        setTitle("Alphabet manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create panels for better organization
        mainPanel = new JPanel(new BorderLayout());
        tablePanel = new JPanel(new BorderLayout());

        String[] column = {"NAME", "CHARS", "SIZE"};

        table = new JTable(CtrlPresentacio.Demanar_full_Alfabet(), column);

        scrollPane = new JScrollPane(table);

        tablePanel.add(scrollPane);

        buttonPanel = new JPanel(new BorderLayout());
        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        modifyButton = new JButton("Modify");
        createButton = new JButton("Create");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");

        modifyButton.setPreferredSize(new Dimension(80, 30));
        createButton.setPreferredSize(new Dimension(80, 30));
        deleteButton.setPreferredSize(new Dimension(80, 30));
        backButton.setPreferredSize(new Dimension(80, 30));

        // Add ActionListeners to buttons
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace this with the actual logic for the button
                JOptionPane.showMessageDialog(null, "Modify button clicked");
            }
        });

        createButton.addActionListener(e -> {
            // Replace this with the actual logic for the button
            CreateAlphabet ca = new CreateAlphabet(this);

            // Update the table model with the new data
            DefaultTableModel model = new DefaultTableModel(CtrlPresentacio.Demanar_full_Alfabet(), column);
            table.setModel(model);

            // Notify the table that the data has changed
            model.fireTableDataChanged();
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace this with the actual logic for the button
                JOptionPane.showMessageDialog(null, "Delete button clicked");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current frame
                setVisible(false);
                // Open the main view
                new MainView().setVisible(true);
            }
        });

        // Add buttons to the panel
        saveLoadPanel.add(modifyButton);
        saveLoadPanel.add(createButton);
        saveLoadPanel.add(deleteButton);
        saveLoadPanel.add(backButton);

        buttonPanel.add(saveLoadPanel, BorderLayout.EAST);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add panels to the main panel
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
        setLocationRelativeTo(null);
    }


    private String[][] getAlphabetNames() {

        String[][] data = {{"101"},};

        return data;
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(ManageAlphabets::new);
    }
}
