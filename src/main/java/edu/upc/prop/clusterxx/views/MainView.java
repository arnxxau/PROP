package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    public MainView() {
        // Set up the main frame
        setTitle("Main menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = createTopPanel();
        JPanel bottomPanel = createBottomPanel();

        // Add panels to the main panel
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
        setLocationRelativeTo(null);

    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton manageFrequencyButton = createButton("Manage Frequency");
        JButton manageAlphabetsButton = createButton("Manage Alphabets");
        JButton manageKeyboardsButton = createButton("Manage Keyboards");
        JButton manageGridsButton = createButton("Manage Grids");

        manageFrequencyButton.addActionListener(new ManageFrequencyListener());
        manageAlphabetsButton.addActionListener(new ManageAlphabetsListener());
        manageKeyboardsButton.addActionListener(new ManageKeyboardsListener());
        manageGridsButton.addActionListener(new ManageGridsListener());

        topPanel.add(manageFrequencyButton);
        topPanel.add(manageAlphabetsButton);
        topPanel.add(manageKeyboardsButton);
        topPanel.add(manageGridsButton);

        return topPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        // Label Panel
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(new JLabel("Current loaded objects: "));
        bottomPanel.add(labelPanel, BorderLayout.WEST);

        // Save and Load Panel
        JButton saveButton = createLoadSaveButton("Save");
        JButton loadButton = createLoadSaveButton("Load");

        saveButton.addActionListener(new SaveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());

        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveLoadPanel.add(saveButton);
        saveLoadPanel.add(loadButton);
        bottomPanel.add(saveLoadPanel, BorderLayout.EAST);

        return bottomPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }

    private JButton createLoadSaveButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(70, 30));
        return button;
    }

    // Listeners

    private class ManageFrequencyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Manage Frequency Button Clicked");
            ManageFrequency mf = new ManageFrequency();
            setVisible(false);
        }
    }

    private class ManageAlphabetsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Manage Alphabets Button Clicked");
            // Add specific action for Manage Alphabets button
        }
    }

    private class ManageKeyboardsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Manage Keyboards Button Clicked");
            // Add specific action for Manage Keyboards button
        }
    }

    private class ManageGridsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Manage Grids Button Clicked");
            // Add specific action for Manage Grids button
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Save Button Clicked");
            // Add specific action for Save button
        }
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Load Button Clicked");
            DirectorySelector ds = new DirectorySelector();
            ds.selectDirectory();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
