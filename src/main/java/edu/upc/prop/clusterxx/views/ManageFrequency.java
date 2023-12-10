package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageFrequency extends JFrame {

    public ManageFrequency() {
        // Set up the main frame
        setTitle("Frequency manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel tablePanel = createTablePanel();
        JPanel buttonPanel = createButtonPanel();

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

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());

        String[][] data = {{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"}};
        String[] column = {"ID", "ALPHABET", "WEIGHT"};
        JTable table = new JTable(data, column);
        JScrollPane scrollPane = new JScrollPane(table);

        tablePanel.add(scrollPane);

        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new BorderLayout());

        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton modifyButton = createLoadSaveButton("Modify");
        JButton createButton = createLoadSaveButton("Create");
        JButton deleteButton = createLoadSaveButton("Delete");
        JButton backButton = createLoadSaveButton("Back");

        // Add ActionListeners to buttons
        modifyButton.addActionListener(new ModifyButtonListener());
        createButton.addActionListener(new CreateButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        backButton.addActionListener(new BackButtonListener());

        // Add buttons to the panel
        saveLoadPanel.add(modifyButton);
        saveLoadPanel.add(createButton);
        saveLoadPanel.add(deleteButton);
        saveLoadPanel.add(backButton);

        buttonPanel.add(saveLoadPanel, BorderLayout.EAST);

        return buttonPanel;
    }

    private JButton createLoadSaveButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(80, 30));
        return button;
    }

    private class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Replace this with the actual logic for the button
            JOptionPane.showMessageDialog(null, "Modify button clicked");
        }
    }

    private class CreateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Replace this with the actual logic for the button
            CreateFrequency cf = new CreateFrequency();
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Replace this with the actual logic for the button
            JOptionPane.showMessageDialog(null, "Delete button clicked");
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current frame
            setVisible(false);
            // Open the main view
            new MainView().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ManageFrequency::new);
    }
}
