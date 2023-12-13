package edu.upc.prop.clusterxx.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    public MainView() {

        // Set to the system look and feel
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // If system look and feel is not available, fall back to the default look and feel.
        }

        // Use modern fonts
        setFont(new Font("Arial", Font.PLAIN, 14));


        // Set up the main frame
        setTitle("Main menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // Create panels for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = createBottomPanel();

        JTabbedPane tp = new JTabbedPane();
        FrequencyManagerPanel mf = new FrequencyManagerPanel(this);
        AlphabetManagerPanel ma = new AlphabetManagerPanel(this);
        KeyboardManagerPanel mk = new KeyboardManagerPanel(this);
        GridManagerPanel mg = new GridManagerPanel(this);
        InformationPanel mm = new InformationPanel(this);
        tp.addTab("keyboards", null, mk, "manage your keyboards here");
        tp.addTab("grids", null, mg, "manage your grids here");
        tp.addTab("alphabets", null, ma, "manage your alphabets here");
        tp.addTab("frequencies", null, mf, "manage your frequencies here");
        tp.addTab("info", null, mm, "load and save your program and get info");
        topPanel.add(tp);

        // Add panels to the main panel
        mainPanel.add(tp, BorderLayout.CENTER);
        //mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
        //setVisible(true);


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
            //ManageFrequency mf = new ManageFrequency();
            //setVisible(false);
        }
    }

    private class ManageAlphabetsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Manage Alphabets Button Clicked");
            ManageAlphabets ma = new ManageAlphabets();
            setVisible(false);

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
            DirectorySelectorDialog ds = new DirectorySelectorDialog();
            ds.selectDirectory();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
