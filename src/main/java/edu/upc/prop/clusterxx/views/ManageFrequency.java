package edu.upc.prop.clusterxx.views;

import edu.upc.prop.clusterxx.CtrlPresentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageFrequency extends JFrame {

    private JTable table;
    private JButton modifyButton;
    private JButton createButton;
    private JButton deleteButton;
    private JButton backButton;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JPanel saveLoadPanel;
    private JScrollPane scrollPane;

    public ManageFrequency() {
        // Set up the main frame
        setTitle("Frequency manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create panels for better organization
        mainPanel = new JPanel(new BorderLayout());
        tablePanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new BorderLayout());
        saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        String[][] data = {{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"}};
        String[] column = {"NAME", "ALPHABET", "MODE"};

        table = new JTable(CtrlPresentacio.Demanar_full_Freq(), column);

        scrollPane = new JScrollPane(table);

        tablePanel.add(scrollPane);

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
            CreateFrequency cf = new CreateFrequency(this);


            // Update the table model with the new data
            DefaultTableModel model = new DefaultTableModel(CtrlPresentacio.Demanar_full_Freq(), column);
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

        // Add buttons to the saveLoadPanel
        saveLoadPanel.add(modifyButton);
        saveLoadPanel.add(createButton);
        saveLoadPanel.add(deleteButton);
        saveLoadPanel.add(backButton);

        // Add saveLoadPanel to buttonPanel
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ManageFrequency::new);
    }
}
