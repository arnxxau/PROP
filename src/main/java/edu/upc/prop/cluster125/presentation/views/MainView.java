package edu.upc.prop.cluster125.presentation.views;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    FrequencyManagerPanel mf;
    AlphabetManagerPanel ma;
    KeyboardManagerPanel mk;
    GridManagerPanel mg;
    InformationPanel mm;

    public MainView() {
        // Set to the system look and feel
        try {
            //
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
            // If system look and feel is not available, fall back to the default look and feel.
        }

        mf = new FrequencyManagerPanel(this);
        ma = new AlphabetManagerPanel(this);
        mk = new KeyboardManagerPanel(this);
        mg = new GridManagerPanel(this);
        mm = new InformationPanel();



        ImageIcon imgicon = new ImageIcon("/home/akira/IdeaProjects/subgrup-prop12.5/src/main/java/edu/upc/prop/clusterxx/views/logo.png");
        setIconImage(imgicon.getImage());
        setName("Distributor");






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
        tp.addTab("keyboards", null, mk, "manage your keyboards here");
        tp.addTab("grids", null, mg, "manage your grids here");
        tp.addTab("alphabets", null, ma, "manage your alphabets here");
        tp.addTab("frequencies", null, mf, "manage your frequencies here");
        tp.addTab("info", null, mm, "load and save your program and get info");
        topPanel.add(tp);

        tp.setSelectedIndex(4);

        tp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                updateTab(selectedIndex);
            }
        });

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

    // Method to update the selected tab
    private void updateTab(int tabIndex) {
        switch (tabIndex) {
            case 0: // keyboard
                mk.updateTab();
                break;
            case 1: // grids
                mg.updateTab();
                break;
            case 2: // alphabet
                ma.updateTab();
                break;
            case 3: // frequency
                mf.updateTab();
                break;
            case 4:
                mm.refreshLabels();
                break;

            default:
                break;
        }
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
            FileSelectorDialog ds = new FileSelectorDialog();
            ds.selectDirectory();
        }
    }
}
